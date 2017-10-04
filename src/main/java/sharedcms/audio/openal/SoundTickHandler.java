/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  paulscode.sound.Source
 *  paulscode.sound.Vector3D
 */
package sharedcms.audio.openal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import paulscode.sound.Source;
import sharedcms.Info;
import sharedcms.controller.client.AudioController;

public class SoundTickHandler
{
	private Minecraft mc = Minecraft.getMinecraft();
	private static int profileTickCountdown = 13;
	private static float prevDecayFactor = 0.0f;
	private static float prevRoomFactor = 0.0f;
	public static float baseLowPassGain = 1.0f;
	public static float baseLowPassGainHF = 1.0f;
	public static boolean waterSound = false;
	public static boolean lavaSound = false;
	public static Map<Source, Double> sourceOcclusionMap = new HashMap<Source, Double>();
	public static Comparator<ComparablePosition> CPcomparator;

	/*
	 * WARNING - Removed try catching itself - possible behaviour change.
	 */
	@SubscribeEvent
	public void tickStart(TickEvent.ClientTickEvent event)
	{
		if(event.phase == TickEvent.Phase.START)
		{
			if(this.mc.theWorld != null && this.mc.thePlayer != null)
			{
				AudioController.flush();

				if(this.mc.thePlayer.isInsideOfMaterial(Material.water))
				{
					if(!waterSound)
					{
						baseLowPassGain = 1.0f;
						baseLowPassGainHF = 0.4f;
						lavaSound = false;
						waterSound = true;
					}
				}
				else if(waterSound)
				{
					baseLowPassGain = 1.0f;
					baseLowPassGainHF = 1.0f;
					waterSound = false;
				}
				if(this.mc.thePlayer.isInsideOfMaterial(Material.lava))
				{
					if(!lavaSound)
					{
						baseLowPassGain = 0.6f;
						baseLowPassGainHF = 0.2f;
						lavaSound = true;
						waterSound = false;
					}
				}
				else if(lavaSound)
				{
					baseLowPassGain = 1.0f;
					baseLowPassGainHF = 1.0f;
					lavaSound = false;
				}
			}
			else
			{
				baseLowPassGain = 1.0f;
				baseLowPassGainHF = 1.0f;
				ProxySoundFilter.reverbFilter.decayTime = 0.1f;
				ProxySoundFilter.reverbFilter.reflectionsDelay = 0.0f;
				ProxySoundFilter.reverbFilter.lateReverbDelay = 0.0f;
				lavaSound = false;
				waterSound = false;
			}
		}
		if(event.phase == TickEvent.Phase.END)
		{
			ArrayList<Source> toRemove = new ArrayList<Source>();
			if(ProxySoundFilter.doOcclusion)
			{
				Map<Source, Double> map = sourceOcclusionMap;
				synchronized(map)
				{
					for(Source source : sourceOcclusionMap.keySet())
					{
						if(!source.playing())
						{
							toRemove.add(source);
							continue;
						}
						if(this.mc.theWorld != null && this.mc.thePlayer != null)
						{
							Vec3 playerVec = Vec3.createVectorHelper((double) this.mc.thePlayer.posX, (double) (this.mc.thePlayer.posY + (double) this.mc.thePlayer.getEyeHeight()), (double) this.mc.thePlayer.posZ);
							double occlusion = SoundTickHandler.getSoundOcclusion((World) this.mc.theWorld, Vec3.createVectorHelper((double) source.position.x, (double) source.position.y, (double) source.position.z), playerVec);
							sourceOcclusionMap.put(source, occlusion);
							continue;
						}
						sourceOcclusionMap.put(source, 0.0);
					}
					for(Source sourceToRemove : toRemove)
					{
						sourceOcclusionMap.remove((Object) sourceToRemove);
					}
				}
			}
			if(this.mc.theWorld != null && this.mc.thePlayer != null && ProxySoundFilter.doReverb && --profileTickCountdown <= 0)
			{
				profileTickCountdown = 13;
				Random rand = new Random();
				TreeSet<ComparablePosition> visited = new TreeSet<ComparablePosition>(CPcomparator);
				ArrayList<Block> blocksFound = new ArrayList<Block>();
				LinkedList<ComparablePosition> toVisit = new LinkedList<ComparablePosition>();
				toVisit.add(new ComparablePosition(MathHelper.floor_double((double) this.mc.thePlayer.posX), MathHelper.floor_double((double) this.mc.thePlayer.posY), MathHelper.floor_double((double) this.mc.thePlayer.posZ)));
				for(int i = 0; i < ProxySoundFilter.profileSize && !toVisit.isEmpty(); ++i)
				{
					ComparablePosition current = (ComparablePosition) toVisit.remove(rand.nextInt(toVisit.size()));
					visited.add(current);
					ComparablePosition nextPosition = new ComparablePosition(current.x, current.y, current.z + 1);
					Block block = this.mc.theWorld.getBlock(nextPosition.x, nextPosition.y, nextPosition.z);
					Material material = block.getMaterial();
					if(!material.blocksMovement())
					{
						if(!visited.contains(nextPosition) && !toVisit.contains(nextPosition))
						{
							toVisit.add(nextPosition);
						}
						if(material != Material.air)
						{
							blocksFound.add(block);
						}
					}
					else
					{
						blocksFound.add(block);
					}
					nextPosition = new ComparablePosition(current.x, current.y, current.z - 1);
					block = this.mc.theWorld.getBlock(nextPosition.x, nextPosition.y, nextPosition.z);
					material = block.getMaterial();
					if(!material.blocksMovement())
					{
						if(!visited.contains(nextPosition) && !toVisit.contains(nextPosition))
						{
							toVisit.add(nextPosition);
						}
						if(material != Material.air)
						{
							blocksFound.add(block);
						}
					}
					else
					{
						blocksFound.add(block);
					}
					nextPosition = new ComparablePosition(current.x, current.y + 1, current.z);
					block = this.mc.theWorld.getBlock(nextPosition.x, nextPosition.y, nextPosition.z);
					material = block.getMaterial();
					if(!material.blocksMovement())
					{
						if(!visited.contains(nextPosition) && !toVisit.contains(nextPosition))
						{
							toVisit.add(nextPosition);
						}
						if(material != Material.air)
						{
							blocksFound.add(block);
						}
					}
					else
					{
						blocksFound.add(block);
					}
					nextPosition = new ComparablePosition(current.x, current.y - 1, current.z);
					block = this.mc.theWorld.getBlock(nextPosition.x, nextPosition.y, nextPosition.z);
					material = block.getMaterial();
					if(!material.blocksMovement())
					{
						if(!visited.contains(nextPosition) && !toVisit.contains(nextPosition))
						{
							toVisit.add(nextPosition);
						}
						if(material != Material.air)
						{
							blocksFound.add(block);
						}
					}
					else
					{
						blocksFound.add(block);
					}
					nextPosition = new ComparablePosition(current.x + 1, current.y, current.z);
					block = this.mc.theWorld.getBlock(nextPosition.x, nextPosition.y, nextPosition.z);
					material = block.getMaterial();
					if(!material.blocksMovement())
					{
						if(!visited.contains(nextPosition) && !toVisit.contains(nextPosition))
						{
							toVisit.add(nextPosition);
						}
						if(material != Material.air)
						{
							blocksFound.add(block);
						}
					}
					else
					{
						blocksFound.add(block);
					}
					nextPosition = new ComparablePosition(current.x - 1, current.y, current.z);
					block = this.mc.theWorld.getBlock(nextPosition.x, nextPosition.y, nextPosition.z);
					material = block.getMaterial();
					if(!material.blocksMovement())
					{
						if(!visited.contains(nextPosition) && !toVisit.contains(nextPosition))
						{
							toVisit.add(nextPosition);
						}
						if(material == Material.air)
							continue;
						blocksFound.add(block);
						continue;
					}
					blocksFound.add(block);
				}
				int roomSize = visited.size();
				int highReverb = 0;
				int midReverb = 0;
				int lowReverb = 0;
				for(Block b : blocksFound)
				{
					if(ProxySoundFilter.highReverbSet.contains(Block.getIdFromBlock((Block) b)))
					{
						++highReverb;
						continue;
					}
					if(ProxySoundFilter.midReverbSet.contains(Block.getIdFromBlock((Block) b)))
					{
						++midReverb;
						continue;
					}
					if(ProxySoundFilter.lowReverbSet.contains(Block.getIdFromBlock((Block) b)))
					{
						++lowReverb;
						continue;
					}
					if(b.getMaterial() == Material.rock || b.getMaterial() == Material.glass || b.getMaterial() == Material.ice || b.getMaterial() == Material.iron)
					{
						++highReverb;
						continue;
					}
					if(b.getMaterial() == Material.cactus || b.getMaterial() == Material.cake || b.getMaterial() == Material.cloth || b.getMaterial() == Material.coral || b.getMaterial() == Material.grass || b.getMaterial() == Material.leaves || b.getMaterial() == Material.carpet || b.getMaterial() == Material.plants || b.getMaterial() == Material.gourd || b.getMaterial() == Material.snow || b.getMaterial() == Material.sponge || b.getMaterial() == Material.vine || b.getMaterial() == Material.web)
					{
						++lowReverb;
						continue;
					}
					++midReverb;
				}
				float decayFactor = 0.0f;
				float roomFactor = (float) roomSize / (float) ProxySoundFilter.profileSize;
				if(highReverb + midReverb + lowReverb > 0)
				{
					decayFactor += (float) (highReverb - lowReverb) / (float) (highReverb + midReverb + lowReverb);
				}
				if(decayFactor < 0.0f)
				{
					decayFactor = 0.0f;
				}
				if(decayFactor > 1.0f)
				{
					decayFactor = 1.0f;
				}
				decayFactor = (decayFactor + prevDecayFactor) / 2.0f;
				roomFactor = (roomFactor + prevRoomFactor) / 2.0f;
				prevDecayFactor = decayFactor;
				prevRoomFactor = roomFactor;
				ProxySoundFilter.reverbFilter.decayTime = Info.REVERB_DECAY * decayFactor * roomFactor;
				ProxySoundFilter.reverbFilter.gain = Info.REVERB_GAIN * roomFactor;
				ProxySoundFilter.reverbFilter.diffusion = Info.REVERB_DIFFUSION * roomFactor;
				ProxySoundFilter.reverbFilter.roomRolloffFactor = Info.REVERB_ROLLOFF * roomFactor;
				
				if(ProxySoundFilter.reverbFilter.decayTime < 0.001f)
				{
					ProxySoundFilter.reverbFilter.decayTime = 0.001f;
				}

				ProxySoundFilter.reverbFilter.reflectionsDelay = Info.REVERB_REFLECTOR * roomFactor;
				ProxySoundFilter.reverbFilter.lateReverbDelay = Info.REVERB_DELAY * roomFactor;
			}
		}
	}

	public static double getSoundOcclusion(World world, Vec3 sound, Vec3 listener)
	{
		double occludedPercent = 0.0;
		if(!(Double.isNaN(sound.xCoord) || Double.isNaN(sound.yCoord) || Double.isNaN(sound.zCoord)))
		{
			if(!(Double.isNaN(listener.xCoord) || Double.isNaN(listener.yCoord) || Double.isNaN(listener.zCoord)))
			{
				int listenerX = MathHelper.floor_double((double) listener.xCoord);
				int listenerY = MathHelper.floor_double((double) listener.yCoord);
				int listenerZ = MathHelper.floor_double((double) listener.zCoord);
				int soundX = MathHelper.floor_double((double) sound.xCoord);
				int soundY = MathHelper.floor_double((double) sound.yCoord);
				int soundZ = MathHelper.floor_double((double) sound.zCoord);
				int countDown = 200;
				while(countDown-- >= 0)
				{
					MovingObjectPosition mop;
					int whichToChange;
					if(Double.isNaN(sound.xCoord) || Double.isNaN(sound.yCoord) || Double.isNaN(sound.zCoord))
					{
						return occludedPercent;
					}
					if(soundX == listenerX && soundY == listenerY && soundZ == listenerZ)
					{
						return occludedPercent;
					}
					boolean shouldChangeX = true;
					boolean shouldChangeY = true;
					boolean shouldChangeZ = true;
					double newX = 999.0;
					double newY = 999.0;
					double newZ = 999.0;
					if(listenerX == soundX)
					{
						shouldChangeX = false;
					}
					if(shouldChangeX)
					{
						newX = (double) soundX + (listenerX > soundX ? 1.0 : 0.0);
					}
					if(listenerY == soundY)
					{
						shouldChangeY = false;
					}
					if(shouldChangeY)
					{
						newY = (double) soundY + (listenerY > soundY ? 1.0 : 0.0);
					}
					if(listenerZ == soundZ)
					{
						shouldChangeZ = false;
					}
					if(shouldChangeZ)
					{
						newZ = (double) soundZ + (listenerZ > soundZ ? 1.0 : 0.0);
					}
					double xPercentChange = 999.0;
					double yPercentChange = 999.0;
					double zPercentChange = 999.0;
					double xDifference = listener.xCoord - sound.xCoord;
					double yDifference = listener.yCoord - sound.yCoord;
					double zDifference = listener.zCoord - sound.zCoord;
					if(shouldChangeX)
					{
						xPercentChange = (newX - sound.xCoord) / xDifference;
					}
					if(shouldChangeY)
					{
						yPercentChange = (newY - sound.yCoord) / yDifference;
					}
					if(shouldChangeZ)
					{
						zPercentChange = (newZ - sound.zCoord) / zDifference;
					}
					if(xPercentChange < yPercentChange && xPercentChange < zPercentChange)
					{
						whichToChange = listenerX > soundX ? 4 : 5;
						sound.xCoord = newX;
						sound.yCoord += yDifference * xPercentChange;
						sound.zCoord += zDifference * xPercentChange;
					}
					else if(yPercentChange < zPercentChange)
					{
						whichToChange = listenerY > soundY ? 0 : 1;
						sound.xCoord += xDifference * yPercentChange;
						sound.yCoord = newY;
						sound.zCoord += zDifference * yPercentChange;
					}
					else
					{
						whichToChange = listenerZ > soundZ ? 2 : 3;
						sound.xCoord += xDifference * zPercentChange;
						sound.yCoord += yDifference * zPercentChange;
						sound.zCoord = newZ;
					}
					Vec3 vec32 = Vec3.createVectorHelper((double) sound.xCoord, (double) sound.yCoord, (double) sound.zCoord);
					vec32.xCoord = MathHelper.floor_double((double) sound.xCoord);
					soundX = (int) vec32.xCoord;
					if(whichToChange == 5)
					{
						--soundX;
						vec32.xCoord += 1.0;
					}
					vec32.yCoord = MathHelper.floor_double((double) sound.yCoord);
					soundY = (int) vec32.yCoord;
					if(whichToChange == 1)
					{
						--soundY;
						vec32.yCoord += 1.0;
					}
					vec32.zCoord = MathHelper.floor_double((double) sound.zCoord);
					soundZ = (int) vec32.zCoord;
					if(whichToChange == 3)
					{
						--soundZ;
						vec32.zCoord += 1.0;
					}
					Block block = world.getBlock(soundX, soundY, soundZ);
					int blockID = Block.getIdFromBlock((Block) block);
					int meta = world.getBlockMetadata(soundX, soundY, soundZ);
					Material material = block.getMaterial();
					if(block == null || block == Blocks.air || block.getCollisionBoundingBoxFromPool(world, soundX, soundY, soundZ) == null || !block.canCollideCheck(meta, false) || (mop = block.collisionRayTrace(world, soundX, soundY, soundZ, sound, listener)) == null)
						continue;
					if(occludedPercent < 0.7)
					{
						occludedPercent += material.isOpaque() ? 0.1 : 0.05;
					}
					if(ProxySoundFilter.highOcclusionSet.contains(blockID))
					{
						occludedPercent += 0.15;
					}
					if(occludedPercent <= 0.95)
						continue;
					return 0.95;
				}
				return occludedPercent;
			}
			return occludedPercent;
		}
		return occludedPercent;
	}

	static
	{
		sourceOcclusionMap = Collections.synchronizedMap(sourceOcclusionMap);
		CPcomparator = new Comparator<ComparablePosition>()
		{

			@Override
			public int compare(ComparablePosition first, ComparablePosition second)
			{
				return first.compareTo(second);
			}
		};
	}

	public class ComparablePosition implements Comparable
	{
		int x;
		int y;
		int z;

		ComparablePosition(int xToSet, int yToSet, int zToSet)
		{
			this.x = xToSet;
			this.y = yToSet;
			this.z = zToSet;
		}

		public boolean equals(Object object)
		{
			if(!(object instanceof ComparablePosition))
			{
				return false;
			}
			ComparablePosition toCompare = (ComparablePosition) object;
			return toCompare.x == this.x && toCompare.y == this.y && toCompare.z == this.z;
		}

		public int compareTo(Object object)
		{
			if(!(object instanceof ComparablePosition))
			{
				return 0;
			}
			ComparablePosition toCompare = (ComparablePosition) object;
			if(toCompare.x - this.x != 0)
			{
				return toCompare.x - this.x;
			}
			if(toCompare.y - this.y != 0)
			{
				return toCompare.y - this.y;
			}
			if(this.z - toCompare.z != 0)
			{
				return toCompare.z - this.z;
			}
			return 0;
		}
	}

}

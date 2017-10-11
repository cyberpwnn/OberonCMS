package sharedcms.content.world.buffer.scatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import sharedcms.content.Content;
import sharedcns.api.biome.BiomeHumidity;
import sharedcns.api.biome.BiomeHumidityModifier;
import sharedcns.api.biome.BiomeTemperature;
import sharedcns.api.biome.BiomeTemperatureModifier;
import sharedcns.api.biome.DecorationPass;
import sharedcns.api.biome.LudicrousScatterBuffer;

public class TreeBuffer extends LudicrousScatterBuffer
{
	private List<Block> soil;
	private Block logBlock;
	private Block leavesBlock;
	private int treeHeight;
	private TreeModel model;
	private int wbonus;
	private int wrbonus;

	public TreeBuffer(TreeModel model, int strength, Block logBlock, Block leavesBlock)
	{
		super(strength, 1);

		this.wbonus = 0;
		this.wrbonus = 0;
		this.model = model;
		this.treeHeight = 7;
		this.leavesBlock = leavesBlock;
		this.logBlock = logBlock;
		soil = new ArrayList<Block>();
	}
	
	public void updateForGen(World w, int x, int z)
	{
		
	}

	public List<Block> getSoil()
	{
		return soil;
	}

	public void setSoil(List<Block> soil)
	{
		this.soil = soil;
	}

	public TreeModel getModel()
	{
		return model;
	}

	public void setModel(TreeModel model)
	{
		this.model = model;
	}

	public int getWbonus()
	{
		return wbonus;
	}

	public void setWbonus(int wbonus)
	{
		this.wbonus = wbonus;
	}

	public int getWrbonus()
	{
		return wrbonus;
	}

	public void setWrbonus(int wrbonus)
	{
		this.wrbonus = wrbonus;
	}

	public Block getLogBlock()
	{
		return logBlock;
	}

	public void setLogBlock(Block logBlock)
	{
		this.logBlock = logBlock;
	}

	public Block getLeavesBlock()
	{
		return leavesBlock;
	}

	public void setLeavesBlock(Block leavesBlock)
	{
		this.leavesBlock = leavesBlock;
	}

	public int getTreeHeight()
	{
		return treeHeight;
	}

	public void setTreeHeight(int treeHeight)
	{
		this.treeHeight = treeHeight;
	}

	public void addSoil(Block b)
	{
		soil.add(b);
	}

	@Override
	public boolean canDecorate(World w, Random r, DecorationPass pass, Block surface, int x, int y, int z, BiomeTemperatureModifier temperatureModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity hum)
	{
		if(!soil.contains(surface))
		{
			return false;
		}

		return true;
	}

	@Override
	public void decorate(World w, Random r, DecorationPass pass, Block surface, int x, int y, int z, BiomeTemperatureModifier temperatureModifier, BiomeHumidityModifier humidityModifier, BiomeTemperature temp, BiomeHumidity hum)
	{
		updateForGen(w, x, z);
		
		if(model.equals(TreeModel.BASIC))
		{
			generateBasic(w, r, x, y, z);
		}

		if(model.equals(TreeModel.CANOPY))
		{
			generateCanopy(w, r, x, y, z);
		}
	}

	public Block getLeavesBlock(Random rand)
	{
		return leavesBlock;
	}

	protected boolean func_150523_a(Block p_150523_1_)
	{
		return p_150523_1_.getMaterial() == Material.air || p_150523_1_.getMaterial() == Material.leaves || p_150523_1_ == Blocks.grass || p_150523_1_ == Blocks.dirt || p_150523_1_ == Blocks.log || p_150523_1_ == Blocks.log2 || p_150523_1_ == Blocks.sapling || p_150523_1_ == Blocks.vine;
	}

	protected boolean isReplaceable(World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		return block.isAir(world, x, y, z) || block.isLeaves(world, x, y, z) || block.isWood(world, x, y, z) || func_150523_a(block);
	}

	public void generateBasic(World world, Random random, int x, int y, int z)
	{
		for(int interval = 0; interval < 1; interval++)
		{
			int l = random.nextInt(3) + treeHeight;
			boolean flag = true;

			if(y >= 1 && y + l + 1 <= 256)
			{
				byte b0;
				int k1;
				Block block;

				for(int i1 = y; i1 <= y + 1 + l; ++i1)
				{
					b0 = 1;

					if(i1 == y)
					{
						b0 = 0;
					}

					if(i1 >= y + 1 + l - 2)
					{
						b0 = 2;
					}

				}

				if(!flag)
				{
					continue;
				}

				else
				{
					Block block2 = world.getBlock(x, y - 1, z);

					boolean isSoil = isSoil(block2) || block2.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (BlockSapling) Blocks.sapling);
					if(isSoil && y < 256 - l - 1)
					{
						block2.onPlantGrow(world, x, y - 1, z, x, y, z);
						b0 = 3;
						byte b1 = 0;
						int l1;
						int i2;
						int j2;
						int i3;

						for(k1 = y - b0 + l; k1 <= y + l; ++k1)
						{
							i3 = k1 - (y + l);
							l1 = b1 + 1 - i3 / 2;

							for(i2 = x - l1; i2 <= x + l1; ++i2)
							{
								j2 = i2 - x;

								for(int k2 = z - l1; k2 <= z + l1; ++k2)
								{
									int l2 = k2 - z;

									if(Math.abs(j2) != l1 || Math.abs(l2) != l1 || random.nextInt(2) != 0 && i3 != 0)
									{
										Block block1 = world.getBlock(i2, k1, k2);

										if(block1.isAir(world, i2, k1, k2) || block1.isLeaves(world, i2, k1, k2))
										{
											world.setBlock(i2, k1, k2, getLeavesBlock(random));
										}
									}
								}
							}
						}

						for(k1 = 0; k1 < l; ++k1)
						{
							block = world.getBlock(x, y + k1, z);

							if(block.isAir(world, x, y + k1, z) || block.isLeaves(world, x, y + k1, z))
							{
								world.setBlock(x, y + k1, z, logBlock);
							}
						}

						continue;
					}

					else
					{
						continue;
					}
				}
			}

			else
			{
				continue;
			}
		}
	}

	public boolean isSoil(Block b)
	{
		if(b.equals(Content.Block.PODZOL))
		{
			return true;
		}

		if(b.equals(Content.Block.ARID_SAND))
		{
			return true;
		}

		if(b.equals(Content.Block.GLACIAL_GRASS))
		{
			return true;
		}

		if(b.equals(Blocks.dirt))
		{
			return true;
		}

		if(b.equals(Blocks.grass))
		{
			return true;
		}

		if(b.equals(Content.Block.COLD_DIRT))
		{
			return true;
		}

		if(b.equals(Content.Block.COLD_GRASS))
		{
			return true;
		}

		return false;
	}

	public boolean generateCanopy(World w, Random r, int x, int y, int z)
	{
		int l = r.nextInt(3) + r.nextInt(2) + treeHeight;
		boolean flag = true;

		if(y >= 1 && y + l + 1 <= 256)
		{
			int j1;
			int k1;

			for(int i1 = y; i1 <= y + 1 + l; ++i1)
			{
				byte b0 = 1;

				if(i1 == y)
				{
					b0 = 0;
				}

				if(i1 >= y + 1 + l - 2)
				{
					b0 = 2;
				}

				for(j1 = x - b0; j1 <= x + b0 && flag; ++j1)
				{
					for(k1 = z - b0; k1 <= z + b0 && flag; ++k1)
					{
						if(i1 >= 0 && i1 < 256)
						{
							Block block = w.getBlock(j1, i1, k1);
							flag = true;
						}

						else
						{
							flag = false;
						}
					}
				}
			}

			if(!flag)
			{
				return false;
			}

			else
			{
				Block block2 = w.getBlock(x, y - 1, z);

				boolean isSoil = isSoil(block2);

				if(isSoil && y < 256 - l - 1)
				{
					onPlantGrow(w, x, y - 1, z, x, y, z);
					onPlantGrow(w, x + 1, y - 1, z, x, y, z);
					onPlantGrow(w, x + 1, y - 1, z + 1, x, y, z);
					onPlantGrow(w, x, y - 1, z + 1, x, y, z);
					int j3 = r.nextInt(4);
					j1 = l - r.nextInt(4);
					k1 = 2 - r.nextInt(3);
					int k3 = x;
					int l1 = z;
					int i2 = 0;
					int j2;
					int k2;

					for(j2 = 0; j2 < l; ++j2)
					{
						k2 = y + j2 - 3;

						if(j2 >= j1 && k1 > 0)
						{
							k3 += Direction.offsetX[j3];
							l1 += Direction.offsetZ[j3];
							--k1;
						}

						Block block1 = w.getBlock(k3, k2, l1);

						if(block1.isAir(w, k3, k2, l1) || block1.isLeaves(w, k3, k2, l1))
						{
							this.setBlockAndNotifyAdequately(w, k3, k2, l1, logBlock, 1);
							this.setBlockAndNotifyAdequately(w, k3 + 1, k2, l1, logBlock, 1);
							this.setBlockAndNotifyAdequately(w, k3, k2, l1 + 1, logBlock, 1);
							this.setBlockAndNotifyAdequately(w, k3 + 1, k2, l1 + 1, logBlock, 1);

							if(j2 < 4)
							{
								this.setBlockAndNotifyAdequately(w, k3 - 1, k2, l1, logBlock, 1);
								this.setBlockAndNotifyAdequately(w, k3, k2, l1 - 1, logBlock, 1);
								this.setBlockAndNotifyAdequately(w, k3 - 1, k2, l1 - 1, logBlock, 1);
								this.setBlockAndNotifyAdequately(w, k3 + 2, k2, l1, logBlock, 1);
								this.setBlockAndNotifyAdequately(w, k3, k2, l1 + 2, logBlock, 1);
								this.setBlockAndNotifyAdequately(w, k3 + 2, k2, l1 + 2, logBlock, 1);
							}

							i2 = k2;
						}
					}

					for(j2 = -2; j2 <= 0; ++j2)
					{
						for(k2 = -2; k2 <= 0; ++k2)
						{
							byte b1 = -1;
							this.func_150526_a(w, k3 + j2, i2 + b1, l1 + k2, r);
							this.func_150526_a(w, 1 + k3 - j2, i2 + b1, l1 + k2, r);
							this.func_150526_a(w, k3 + j2, i2 + b1, 1 + l1 - k2, r);
							this.func_150526_a(w, 1 + k3 - j2, i2 + b1, 1 + l1 - k2, r);

							if((j2 > -2 || k2 > -1) && (j2 != -1 || k2 != -2))
							{
								byte b2 = 1;
								this.func_150526_a(w, k3 + j2, i2 + b2, l1 + k2, r);
								this.func_150526_a(w, 1 + k3 - j2, i2 + b2, l1 + k2, r);
								this.func_150526_a(w, k3 + j2, i2 + b2, 1 + l1 - k2, r);
								this.func_150526_a(w, 1 + k3 - j2, i2 + b2, 1 + l1 - k2, r);
							}
						}
					}

					if(r.nextBoolean())
					{
						this.func_150526_a(w, k3, i2 + 2, l1, r);
						this.func_150526_a(w, k3 + 1, i2 + 2, l1, r);
						this.func_150526_a(w, k3 + 1, i2 + 2, l1 + 1, r);
						this.func_150526_a(w, k3, i2 + 2, l1 + 1, r);
					}

					for(j2 = -3; j2 <= 4; ++j2)
					{
						for(k2 = -3; k2 <= 4; ++k2)
						{
							if((j2 != -3 || k2 != -3) && (j2 != -3 || k2 != 4) && (j2 != 4 || k2 != -3) && (j2 != 4 || k2 != 4) && (Math.abs(j2) < 3 || Math.abs(k2) < 3))
							{
								this.func_150526_a(w, k3 + j2, i2, l1 + k2, r);
							}
						}
					}

					for(j2 = -1; j2 <= 2; ++j2)
					{
						for(k2 = -1; k2 <= 2; ++k2)
						{
							if((j2 < 0 || j2 > 1 || k2 < 0 || k2 > 1) && r.nextInt(3) <= 0)
							{
								int l3 = r.nextInt(3) + 2;
								int l2;

								for(l2 = 0; l2 < l3; ++l2)
								{
									w.setBlock(x + j2, i2 - l2 - 1, z + k2, logBlock);
								}

								int i3;

								for(l2 = -3 - wbonus; l2 <= 3 + wbonus; ++l2)
								{
									for(i3 = -3 - wbonus; i3 <= 3 + wbonus; ++i3)
									{
										this.func_150526_a(w, k3 + j2 + l2, i2 - 0, l1 + k2 + i3, r);
									}
								}

								for(l2 = -6 - wbonus; l2 <= 6 + wbonus; ++l2)
								{
									for(i3 = -6 - wbonus; i3 <= 6 + wbonus; ++i3)
									{
										if(Math.abs(l2) != 6 + wbonus || Math.abs(i3) != 6 + wbonus)
										{
											if(Math.abs(l2) + Math.abs(i3) < 8 + wbonus + r.nextInt(2 + wrbonus))
											{
												this.func_150526_a(w, k3 + j2 + l2, i2 - 1, l1 + k2 + i3, r);
											}
										}
									}
								}

								if(wbonus > 0)
								{
									int vv = wbonus;

									while(vv > -4)
									{
										for(l2 = -6 - vv; l2 <= 6 + vv; ++l2)
										{
											for(i3 = -6 - vv; i3 <= 6 + vv; ++i3)
											{
												if(Math.abs(l2) != 6 + vv || Math.abs(i3) != 6 + vv)
												{
													if(Math.abs(l2) + Math.abs(i3) < 8 + vv + r.nextInt(2 + wrbonus))
													{
														this.func_150526_a(w, k3 + j2 + l2, i2 - 1, l1 + k2 + i3, r);
													}
												}
											}
										}

										vv -= wbonus / 2;
									}
								}

								for(l2 = -4; l2 <= 4; ++l2)
								{
									for(i3 = -4; i3 <= 4; ++i3)
									{
										if(Math.abs(l2) != 4 || Math.abs(i3) != 4)
										{
											if(r.nextInt(3) == 0)
											{
												this.func_150526_a(w, k3 + j2 + l2, i2 - 2, l1 + k2 + i3, r);
											}
										}
									}
								}
							}
						}
					}

					return true;
				}

				else
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
	}

	private void setBlockAndNotifyAdequately(World w, int k3, int k2, int l1, Block logBlock2, int i)
	{
		w.setBlock(k3, k2, l1, logBlock2);
	}

	private void func_150526_a(World p_150526_1_, int p_150526_2_, int p_150526_3_, int p_150526_4_, Random rand)
	{
		Block block = p_150526_1_.getBlock(p_150526_2_, p_150526_3_, p_150526_4_);

		if(block.isAir(p_150526_1_, p_150526_2_, p_150526_3_, p_150526_4_))
		{

			p_150526_1_.setBlock(p_150526_2_, p_150526_3_, p_150526_4_, getLeavesBlock(rand));
		}
	}

	private void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
	{
		world.getBlock(x, y, z).onPlantGrow(world, x, y, z, sourceX, sourceY, sourceZ);
	}
}

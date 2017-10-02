/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  paulscode.sound.Channel
 *  paulscode.sound.FilenameURL
 *  paulscode.sound.SoundBuffer
 *  paulscode.sound.Source
 *  paulscode.sound.Vector3D
 *  paulscode.sound.libraries.ChannelLWJGLOpenAL
 *  paulscode.sound.libraries.SourceLWJGLOpenAL
 */
package sharedcms.audio.openal;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Map;
import javax.sound.sampled.AudioFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import paulscode.sound.Channel;
import paulscode.sound.FilenameURL;
import paulscode.sound.SoundBuffer;
import paulscode.sound.Source;
import paulscode.sound.Vector3D;
import paulscode.sound.libraries.ChannelLWJGLOpenAL;
import paulscode.sound.libraries.SourceLWJGLOpenAL;
import sharedcms.audio.filter.BaseFilter;
import sharedcms.audio.filter.FilterLowPass;
import sharedcms.audio.filter.FilterReverb;
import sharedcms.audio.openal.ProxySoundFilter;
import sharedcms.audio.openal.SoundTickHandler;

public class ModifiedLWJGLOpenALSource extends SourceLWJGLOpenAL
{
	public ModifiedLWJGLOpenALSource(FloatBuffer listenerPosition, IntBuffer myBuffer, Source old, SoundBuffer soundBuffer)
	{
		super(listenerPosition, myBuffer, old, soundBuffer);
	}

	public ModifiedLWJGLOpenALSource(FloatBuffer listenerPosition, IntBuffer myBuffer, boolean priority, boolean toStream, boolean toLoop, String sourcename, FilenameURL filenameURL, SoundBuffer soundBuffer, float x, float y, float z, int attModel, float distOrRoll, boolean temporary)
	{
		super(listenerPosition, myBuffer, priority, toStream, toLoop, sourcename, filenameURL, soundBuffer, x, y, z, attModel, distOrRoll, temporary);
	}

	public ModifiedLWJGLOpenALSource(FloatBuffer listenerPosition, AudioFormat audioFormat, boolean priority, String sourcename, float x, float y, float z, int attModel, float distOrRoll)
	{
		super(listenerPosition, audioFormat, priority, sourcename, x, y, z, attModel, distOrRoll);
	}

	public boolean stopped()
	{
		boolean stopped = super.stopped();
		if(this.channel != null && this.channel.attachedSource == this && !stopped && !this.paused())
		{
			this.updateFilters();
		}
		return stopped;
	}

	private void updateFilters()
	{
		try
		{
			boolean isMusic;
			ChannelLWJGLOpenAL alChannel = (ChannelLWJGLOpenAL) this.channel;
			Minecraft mc = Minecraft.getMinecraft();
			boolean bl = isMusic = this.toStream && this.position.x == 0.0f && this.position.y == 0.0f && this.position.z == 0.0f && this.attModel == 0;
			if(alChannel != null && !isMusic && mc.theWorld != null)
			{
				boolean isOccluded = false;
				double amount = 0.0;
				if(ProxySoundFilter.doOcclusion)
				{
					Map<Source, Double> map = SoundTickHandler.sourceOcclusionMap;
					synchronized(map)
					{
						if(SoundTickHandler.sourceOcclusionMap.containsKey((Object) this))
						{
							amount = SoundTickHandler.sourceOcclusionMap.get((Object) this);
						}
						else
						{
							if(mc.theWorld != null && mc.thePlayer != null)
							{
								amount = SoundTickHandler.getSoundOcclusion((World) mc.theWorld, Vec3.createVectorHelper((double) this.position.x, (double) this.position.y, (double) this.position.z), Vec3.createVectorHelper((double) mc.thePlayer.posX, (double) mc.thePlayer.posY, (double) mc.thePlayer.posZ));
							}
							SoundTickHandler.sourceOcclusionMap.put((Source) this, amount);
						}
						isOccluded = amount > 0.05;
					}
				}
				ProxySoundFilter.lowPassFilter.gain = SoundTickHandler.baseLowPassGain;
				ProxySoundFilter.lowPassFilter.gainHF = SoundTickHandler.baseLowPassGainHF;
				if(isOccluded && this.attModel != 0)
				{
					ProxySoundFilter.lowPassFilter.gain = (float) ((double) ProxySoundFilter.lowPassFilter.gain * (1.0 - 1.0 * amount));
					ProxySoundFilter.lowPassFilter.gainHF = (float) ((double) ProxySoundFilter.lowPassFilter.gainHF * (1.0 - 1.0 * (double) MathHelper.sqrt_double((double) amount)));
				}
				if(ProxySoundFilter.lowPassFilter.gain < 1.0f || ProxySoundFilter.lowPassFilter.gainHF < 1.0f)
				{
					ProxySoundFilter.lowPassFilter.enable();
					ProxySoundFilter.lowPassFilter.loadParameters();
				}
				else
				{
					ProxySoundFilter.lowPassFilter.disable();
				}
				if(ProxySoundFilter.reverbFilter.reflectionsDelay > 0.0f || ProxySoundFilter.reverbFilter.lateReverbDelay > 0.0f)
				{
					ProxySoundFilter.reverbFilter.enable();
					ProxySoundFilter.reverbFilter.loadParameters();
				}
				else
				{
					ProxySoundFilter.reverbFilter.disable();
				}
				BaseFilter.loadSourceFilter(alChannel.ALSource.get(0), 131077, ProxySoundFilter.lowPassFilter);
				BaseFilter.load3SourceFilters(alChannel.ALSource.get(0), 131078, ProxySoundFilter.reverbFilter, null, ProxySoundFilter.lowPassFilter);
			}
			else
			{
				ProxySoundFilter.lowPassFilter.disable();
				ProxySoundFilter.reverbFilter.disable();
				BaseFilter.loadSourceFilter(alChannel.ALSource.get(0), 131077, null);
				BaseFilter.load3SourceFilters(alChannel.ALSource.get(0), 131078, null, null, null);
			}
		}

		catch(Exception e)
		{

		}
	}
}

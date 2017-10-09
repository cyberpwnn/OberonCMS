package sharedcms.renderer.layer;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import sharedcms.Ares;
import sharedcms.Colors;
import sharedcms.Status;
import sharedcms.audio.openal.ProxySoundFilter;
import sharedcms.base.AresBiome;
import sharedcms.content.Content;
import sharedcms.content.world.meta.objects.MetaWorld;
import sharedcms.controller.shared.WorldHostController;
import sharedcms.util.DimensionalLevel;
import sharedcms.util.F;
import sharedcms.util.GList;
import sharedcms.util.Location;
import sharedcns.api.biome.BiomeHumidity;
import sharedcns.api.biome.BiomeTemperature;

public class RenderLayerDebug extends RenderLayer
{
	public RenderLayerDebug()
	{
		GList<TextElement> k = new GList<TextElement>();
		Color cc = fromTo(Colors.HUD_SHIELDS, Colors.HUD_HEALTH, (double) Status.CHANNEL_USE / (double) Status.CHANNEL_MAX);
		Color cp = fromTo(Colors.HUD_SHIELDS, Colors.HUD_HEALTH, (double) Status.PARTICLE_USE / 2000.0);
		String bw = F.pc((double) Status.CHANNEL_USE / (double) Status.CHANNEL_MAX);
		String pw = F.pc((double) Status.PARTICLE_USE / 2000.0);
		String rvDecay = F.fd(ProxySoundFilter.reverbFilter.decayTime, 2);
		String rvGain = F.fd(ProxySoundFilter.reverbFilter.gain, 2);
		String rvDiff = F.fd(ProxySoundFilter.reverbFilter.diffusion, 2);
		String rvRefl = F.fd(ProxySoundFilter.reverbFilter.reflectionsDelay, 2);
		String rvDel = F.fd(ProxySoundFilter.reverbFilter.lateReverbDelay, 2);
		String rvRoll = F.fd(ProxySoundFilter.reverbFilter.roomRolloffFactor, 2);

		EntityPlayer ep = Minecraft.getMinecraft().thePlayer;
		Location o = new Location(ep);
		BiomeGenBase b = ep.worldObj.getBiomeGenForCoords((int) o.x, (int) o.z);
		String biome = b.biomeName + " (" + b.biomeID + ")";
		int l = DimensionalLevel.getLevel(o);
		MetaWorld mw = WorldHostController.getWorldMeta(ep.worldObj);
		String temp = "???";
		String humi = "???";

		if(mw != null)
		{
			BiomeTemperature t =  (BiomeTemperature) BiomeTemperature.of((int) ((b.temperature / 2) * BiomeTemperature.length()));
			BiomeHumidity h =  (BiomeHumidity) BiomeHumidity.of((int) (b.rainfall * BiomeHumidity.length()));
			temp = mw.getCsx().get(t, o.x, o.z).toString() + " (" + mw.getCsx().getNormal(t, o.x, o.z) + ")";
			humi = mw.getCsx().get(h, o.x, o.z).toString() + " (" + mw.getCsx().getNormal(h, o.x, o.z) + ")";
		}

		k.add(new TextElement("Channel Use: " + Status.CHANNEL_USE + " / " + Status.CHANNEL_MAX + " (" + bw + ")", cc));
		k.add(new TextElement("Particle Use : " + Status.PARTICLE_USE + " / 2000 (" + pw + ")", cp));
		k.add(new TextElement("AL Status : " + "DEC: " + rvDecay + " GAN: " + rvGain + " DIF: " + rvDiff + " REF: " + rvRefl + " DEL: " + rvDel + " ROL: " + rvRoll, Color.GREEN));
		k.add(new TextElement("World: " + ep.worldObj.provider.terrainType.getWorldTypeName()));
		k.add(new TextElement("  Generation Time: " + Status.CHUNK_GEN_TIME + " ms"));
		k.add(new TextElement("  Biome: " + biome));
		k.add(new TextElement("  Level: " + l));
		k.add(new TextElement("  Temperature: " + temp));
		k.add(new TextElement("  Humidity: " + humi));

		new RenderLayerMultiText(k, new SuperPosition(0, height / 2), 1f);

	}

	protected MovingObjectPosition getMovingObjectPositionFromPlayer(World p_77621_1_, EntityPlayer p_77621_2_, boolean p_77621_3_)
	{
		float f = 1.0F;
		float f1 = p_77621_2_.prevRotationPitch + (p_77621_2_.rotationPitch - p_77621_2_.prevRotationPitch) * f;
		float f2 = p_77621_2_.prevRotationYaw + (p_77621_2_.rotationYaw - p_77621_2_.prevRotationYaw) * f;
		double d0 = p_77621_2_.prevPosX + (p_77621_2_.posX - p_77621_2_.prevPosX) * (double) f;
		double d1 = p_77621_2_.prevPosY + (p_77621_2_.posY - p_77621_2_.prevPosY) * (double) f + (double) (p_77621_1_.isRemote ? p_77621_2_.getEyeHeight() - p_77621_2_.getDefaultEyeHeight() : p_77621_2_.getEyeHeight());
		double d2 = p_77621_2_.prevPosZ + (p_77621_2_.posZ - p_77621_2_.prevPosZ) * (double) f;
		Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = 5.0D;

		if(p_77621_2_ instanceof EntityPlayerMP)
		{
			d3 = ((EntityPlayerMP) p_77621_2_).theItemInWorldManager.getBlockReachDistance();
		}

		Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
		return p_77621_1_.func_147447_a(vec3, vec31, p_77621_3_, !p_77621_3_, false);
	}
}

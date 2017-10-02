package sharedcms.renderer.layer;

import java.awt.Color;

import net.minecraft.client.gui.ScaledResolution;
import sharedcms.Colors;
import sharedcms.mutex.client.ClientHostProxy;
import sharedcms.shuriken.api.health.HealthType;
import sharedcms.shuriken.api.health.IHealthLayer;
import sharedcms.shuriken.api.health.IHealthPool;
import sharedcms.util.F;

public class RenderLayerVitality extends RenderLayer
{
	public static double lastHP = 0;
	public static double lastSP = 0;

	public RenderLayerVitality(IHealthPool pool)
	{
		ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int w = res.getScaledWidth();
		int h = res.getScaledHeight();
		double hp = 0;
		double sh = 0;
		double ar = 0;
		double shm = 0;

		for(IHealthLayer i : ClientHostProxy.health.getLayers())
		{
			hp += i.getType().equals(HealthType.HEALTH) ? i.getCurrent() : 0;
			sh += i.getType().equals(HealthType.ENERGY) ? i.getCurrent() : 0;
			shm += i.getType().equals(HealthType.ENERGY) ? i.getMaximum() : 0;
			ar += i.getType().equals(HealthType.ARMOR) ? i.getCurrent() : 0;
		}

		if(lastHP > hp + ar)
		{
			lastHP -= (lastHP - hp + ar) / 40;
		}

		if(lastSP > sh)
		{
			lastSP -= (lastSP - sh) / 40;
		}

		if(lastHP < hp + ar)
		{
			lastHP += (hp + ar - lastHP) / 40;
		}

		if(lastSP < sh)
		{
			lastSP += (sh - lastSP) / 40;
		}
		
		if(Math.abs(lastSP - sh) < 0.7)
		{
			lastSP = sh;
		}
		
		if(Math.abs(lastHP - (ar + hp)) < 0.7)
		{
			lastHP = (ar + hp);
		}

		double skf = Math.abs(lastSP - sh) / shm;
		double ou = (lastSP - sh) / shm;
		double in = -(lastSP - sh) / shm;
		Color dav = Colors.HUD_SHIELDS;

		if(ou > 0)
		{
			dav = fromTo(dav, Colors.HUD_HEALTH, ou);
		}

		else if(in > 0)
		{
			dav = fromTo(dav, Colors.HUD_REGEN, in);
		}

		glScale(2.2f);
		String shp = F.f((int) lastHP);
		String ssh = F.f((int) lastSP);
		int hpw = mc.fontRenderer.getStringWidth(shp);
		int shw = mc.fontRenderer.getStringWidth(ssh);
		int pad = (int) ((hpw + shw) / 1.3);
		Color shc = fromTo(dav, Colors.HUD_SPLIT, skf);
		drawString(mc.fontRenderer, shp, g(w - 20 - (pad * 1) - hpw, 2.2f), g(10, 2.2f), ar > 0 ? Colors.HUD_ARMOR.getRGB() : Colors.HUD_HEALTH.getRGB());
		drawString(mc.fontRenderer, ssh, g(w - 20 - (pad * 2) - hpw - shw, 2.2f), g(10, 2.2f), shc.getRGB());
		glUnScale(2.2f);
	}
}

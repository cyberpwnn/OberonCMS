package sharedcms.content.effect;

import java.awt.Color;

import net.minecraft.world.World;
import sharedcms.base.AresEffect;
import sharedcms.content.particle.ParticleDust;

public class EffectDust extends AresEffect
{
	@Override
	public void play(World w, int x, int y, int z, Object... data)
	{
		double dx = (Math.random() - Math.random()) / 200;
		double dy = (Math.random() - Math.random()) + 0.01;
		double dz = (Math.random() - Math.random()) / 200;
		
		new ParticleDust(w, x + dx, y + dy, z + dz, (Color) data[0]).spawn();
	}
}

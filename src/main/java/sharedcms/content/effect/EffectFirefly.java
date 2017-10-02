package sharedcms.content.effect;

import java.awt.Color;

import net.minecraft.world.World;
import sharedcms.base.AresEffect;
import sharedcms.content.particle.ParticleFirefly;

public class EffectFirefly extends AresEffect
{
	@Override
	public void play(World w, int x, int y, int z, Object... data)
	{
		if(w.isRaining())
		{
			return;
		}
		
		if(Math.random() * 50 > 1)
		{
			return;
		}
		
		double dx = Math.random() - Math.random();
		double dy = (Math.random() - Math.random()) + 0.2;
		double dz = Math.random() - Math.random();
		
		new ParticleFirefly(w, x + dx, y + dy, z + dz, (Color) data[0]).spawn();
	}
}

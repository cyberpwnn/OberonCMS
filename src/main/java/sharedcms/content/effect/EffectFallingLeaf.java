package sharedcms.content.effect;

import java.awt.Color;

import net.minecraft.world.World;
import sharedcms.base.AresEffect;
import sharedcms.content.particle.ParticleFallingLeaf;

public class EffectFallingLeaf extends AresEffect
{
	@Override
	public void play(World w, int x, int y, int z, Object... data)
	{
		if(Math.random() * 180 > 1)
		{
			return;
		}
		
		double dx = (Math.random() - Math.random()) / 200;
		double dy = (Math.random() - Math.random()) - 0.2;
		double dz = (Math.random() - Math.random()) / 200;
		
		new ParticleFallingLeaf(w, x + dx, y + dy, z + dz, (Color) data[0]).spawn();
	}
}

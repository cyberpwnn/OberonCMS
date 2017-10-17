package sharedcms.content.effect;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import sharedcms.base.AresEffect;
import sharedcms.content.particle.ParticleBlockDust;

public class EffectBlockDust extends AresEffect
{
	@Override
	public void play(World w, int x, int y, int z, Object... data)
	{
		double dx = (Math.random() - Math.random()) / 2;
		double dy = (Math.random() - Math.random()) - 0.01;
		double dz = (Math.random() - Math.random()) / 2;
		Block b = w.getBlock(x, y, z);
		
		new ParticleBlockDust(w, dx, dy, dz, dx, dy, dz, b, 0);
	}
}

package sharedcms.content.particle;

import java.awt.Color;

import net.minecraft.world.World;
import scala.tools.nsc.doc.base.comment.Paragraph;
import sharedcms.base.AresFX;

public class ParticleFirefly extends AresFX
{
	private Color mainColor;

	public ParticleFirefly(World par1World, double x, double y, double z, Color color)
	{
		super("florb", par1World, x, y, z);

		particleScale = (float) (0.85f + (Math.random() / 8));
		particleMaxAge = 10 + (int) (20 * Math.random());
		this.color = color;
		mainColor = color;
		lightingMode = LightingMode.BRIGHT_AS_FUCK;
		this.motionX += ((Math.random() - Math.random()) / 6);
		this.motionY += 0.03;
		this.motionZ += ((Math.random() - Math.random()) / 6);
	}

	public void onUpdate()
	{
		super.onUpdate();

		double tkf = (particleAge / (double) particleMaxAge);
		int kc = particleMaxAge / 2;
		double r = mainColor.getRed();
		double g = mainColor.getGreen();
		double b = mainColor.getBlue();
		double t = particleAge <= kc ? (particleAge / (double) kc) : (1.0 - ((particleAge - kc) / (double) kc));
		double xf = particleAge <= kc ? (particleAge / (double) kc) : 1;
		r *= xf;
		g *= xf;
		b *= xf;
		color = new Color((int) Math.abs(r), (int) Math.abs(g), (int) Math.abs(b), 255);
		particleScale = (float) (particleScale * t) + 0.2f;

		this.motionX = ((0.37 * t) * (Math.random() - Math.random())) + windX;
		this.motionY = ((0.17 * t) * (Math.random() - Math.random())) + 0.05;
		this.motionZ = ((0.37 * t) * (Math.random() - Math.random())) + windZ;
	}
}

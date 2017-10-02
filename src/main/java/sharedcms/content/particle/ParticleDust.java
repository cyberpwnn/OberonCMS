package sharedcms.content.particle;

import java.awt.Color;

import net.minecraft.world.World;
import scala.tools.nsc.doc.base.comment.Paragraph;
import sharedcms.base.AresFX;

public class ParticleDust extends AresFX
{
	private Color mainColor;

	public ParticleDust(World par1World, double x, double y, double z, Color color)
	{
		super(rand("dust", "dust2", "dust3"), par1World, x, y, z);
		
		particleScale = (float) (0.95f + (Math.random() / 4));
		particleMaxAge = 20 + (int) (100 * Math.random());
		this.color = color;
		mainColor = color;
		lightingMode = LightingMode.BRIGHT_AS_FUCK;
		this.motionX += ((Math.random() - Math.random()) / 200);
		this.motionZ += ((Math.random() - Math.random()) / 200);
	}

	public void onUpdate()
	{
		super.onUpdate();

		this.motionX = (windX / 2) + (motionX + ((Math.random() - 0.5)) / 4) / 100;
		this.motionY += 0.0001;
		this.motionZ = (windZ / 2) + (motionZ + ((Math.random() - 0.5)) / 4) / 100;
	}
}

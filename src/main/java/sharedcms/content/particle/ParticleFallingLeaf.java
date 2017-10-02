package sharedcms.content.particle;

import java.awt.Color;

import net.minecraft.world.World;
import scala.tools.nsc.doc.base.comment.Paragraph;
import sharedcms.base.AresFX;

public class ParticleFallingLeaf extends AresFX
{
	private Color mainColor;

	public ParticleFallingLeaf(World par1World, double x, double y, double z, Color color)
	{
		super(rand("leaf", "leaf2", "leaf3"), par1World, x, y, z);
		
		particleScale = (float) (0.25f + (Math.random() / 4));
		particleMaxAge = 150 + (int) (100 * Math.random());
		this.color = color;
		mainColor = color;
		lightingMode = LightingMode.BRIGHT_AS_FUCK;
		this.motionX += ((Math.random() - Math.random()) / 200);
		this.motionY = -0.001;
		this.motionZ += ((Math.random() - Math.random()) / 200);
	}

	public void onUpdate()
	{
		super.onUpdate();

		this.motionX = (windX / 9) + (motionX + ((Math.random() - 0.5)) / 4) / 180;
		this.motionY += -0.00041 * Math.random();
		this.motionZ = (windZ / 9) + (motionZ + ((Math.random() - 0.5)) / 4) / 180;
	}
}

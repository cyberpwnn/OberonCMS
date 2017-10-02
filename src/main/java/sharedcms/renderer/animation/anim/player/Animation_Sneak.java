package sharedcms.renderer.animation.anim.player;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import sharedcms.renderer.animation.anim.Animation;
import sharedcms.renderer.animation.client.model.ModelRendererBends;
import sharedcms.renderer.animation.client.model.entity.ModelBendsPlayer;
import sharedcms.renderer.animation.data.EntityData;
import sharedcms.renderer.animation.util.SmoothVector3f;

public class Animation_Sneak extends Animation
{
	@Override
	public String getName()
	{
		return "sneak";
	}

	@Override
	public void animate(EntityLivingBase argEntity, ModelBase argModel, EntityData argData)
	{
		ModelBendsPlayer model = (ModelBendsPlayer) argModel;
		float var = (float) ((double) (model.armSwing * 0.6662f) / 3.141592653589793) % 2.0f;
		((ModelRendererBends) model.bipedRightLeg).rotation.setSmoothX(-5.0f + 1.1f * (float) ((double) (MathHelper.cos((float) (model.armSwing * 0.6662f)) * 1.4f * model.armSwingAmount) / 3.141592653589793 * 180.0), 1.0f);
		((ModelRendererBends) model.bipedLeftLeg).rotation.setSmoothX(-5.0f + 1.1f * (float) ((double) (MathHelper.cos((float) (model.armSwing * 0.6662f + 3.1415927f)) * 1.4f * model.armSwingAmount) / 3.141592653589793 * 180.0), 1.0f);
		((ModelRendererBends) model.bipedRightLeg).rotation.setSmoothZ(10.0f);
		((ModelRendererBends) model.bipedLeftLeg).rotation.setSmoothZ(-10.0f);
		((ModelRendererBends) model.bipedRightArm).rotation.setSmoothX(-20.0f + 20.0f * MathHelper.cos((float) (model.armSwing * 0.6662f + 3.1415927f)));
		((ModelRendererBends) model.bipedLeftArm).rotation.setSmoothX(-20.0f + 20.0f * MathHelper.cos((float) (model.armSwing * 0.6662f)));
		((ModelRendererBends) model.bipedLeftForeLeg).rotation.setSmoothX(var > 1.0f ? 45 : 10, 0.3f);
		((ModelRendererBends) model.bipedRightForeLeg).rotation.setSmoothX(var > 1.0f ? 10 : 45, 0.3f);
		((ModelRendererBends) model.bipedLeftForeArm).rotation.setSmoothX(var > 1.0f ? -10 : -45, 0.01f);
		((ModelRendererBends) model.bipedRightForeArm).rotation.setSmoothX(var > 1.0f ? -45 : -10, 0.01f);
		float var2 = 25.0f + (float) Math.cos(model.armSwing * 0.6662f * 2.0f) * 5.0f;
		((ModelRendererBends) model.bipedBody).rotation.setSmoothX(var2);
		((ModelRendererBends) model.bipedHead).rotation.setSmoothX(model.headRotationX - var2, 0.3f);
	}
}

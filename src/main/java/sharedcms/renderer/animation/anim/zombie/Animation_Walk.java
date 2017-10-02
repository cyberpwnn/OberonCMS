/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.util.MathHelper
 */
package sharedcms.renderer.animation.anim.zombie;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.MathHelper;
import sharedcms.renderer.animation.anim.Animation;
import sharedcms.renderer.animation.client.model.ModelRendererBends;
import sharedcms.renderer.animation.client.model.entity.ModelBendsZombie;
import sharedcms.renderer.animation.data.Data_Zombie;
import sharedcms.renderer.animation.data.EntityData;
import sharedcms.renderer.animation.util.SmoothVector3f;

public class Animation_Walk
extends Animation {
    @Override
    public String getName() {
        return "walk";
    }

    @Override
    public void animate(EntityLivingBase argEntity, ModelBase argModel, EntityData argData) {
        EntityZombie zombie = (EntityZombie)argEntity;
        ModelBendsZombie model = (ModelBendsZombie)argModel;
        Data_Zombie data = (Data_Zombie)argData;
        model.renderOffset.setSmoothY(-3.0f);
        float var2 = 30.0f + MathHelper.cos((float)(model.armSwing * 0.6662f * 2.0f)) * 10.0f;
        ((ModelRendererBends)model.bipedBody).rotation.setSmoothX(var2, 0.3f);
        ((ModelRendererBends)model.bipedRightArm).rotation.setSmoothX(0.9f * (float)((double)(MathHelper.cos((float)(model.armSwing * 0.6662f + 3.1415927f)) * 2.0f * model.armSwingAmount * 0.5f) / 3.141592653589793 * 180.0));
        ((ModelRendererBends)model.bipedLeftArm).rotation.setSmoothX(0.9f * (float)((double)(MathHelper.cos((float)(model.armSwing * 0.6662f)) * 2.0f * model.armSwingAmount * 0.5f) / 3.141592653589793 * 180.0));
        ((ModelRendererBends)model.bipedRightArm).rotation.setSmoothZ(5.0f, 0.3f);
        ((ModelRendererBends)model.bipedLeftArm).rotation.setSmoothZ(-5.0f, 0.3f);
        ((ModelRendererBends)model.bipedRightLeg).rotation.setSmoothX(-5.0f + 0.9f * (float)((double)(MathHelper.cos((float)(model.armSwing * 0.6662f)) * 1.4f * model.armSwingAmount) / 3.141592653589793 * 180.0), 1.0f);
        ((ModelRendererBends)model.bipedLeftLeg).rotation.setSmoothX(-5.0f + 0.9f * (float)((double)(MathHelper.cos((float)(model.armSwing * 0.6662f + 3.1415927f)) * 1.4f * model.armSwingAmount) / 3.141592653589793 * 180.0), 1.0f);
        ((ModelRendererBends)model.bipedRightLeg).rotation.setSmoothY(0.0f);
        ((ModelRendererBends)model.bipedLeftLeg).rotation.setSmoothY(0.0f);
        ((ModelRendererBends)model.bipedRightLeg).rotation.setSmoothZ(10.0f, 0.2f);
        ((ModelRendererBends)model.bipedLeftLeg).rotation.setSmoothZ(-10.0f, 0.2f);
        float var = (float)((double)(model.armSwing * 0.6662f) / 3.141592653589793) % 2.0f;
        ((ModelRendererBends)model.bipedLeftForeLeg).rotation.setSmoothX(var > 1.0f ? 45 : 0, 0.3f);
        ((ModelRendererBends)model.bipedRightForeLeg).rotation.setSmoothX(var > 1.0f ? 0 : 45, 0.3f);
        ((ModelRendererBends)model.bipedLeftForeArm).rotation.setSmoothX((float)(Math.cos((double)(model.armSwing * 0.6662f) + 1.5707963267948966) + 1.0) / 2.0f * -20.0f, 1.0f);
        ((ModelRendererBends)model.bipedRightForeArm).rotation.setSmoothX((float)(Math.cos(model.armSwing * 0.6662f) + 1.0) / 2.0f * -20.0f, 0.3f);
        float var1 = MathHelper.cos((float)(model.armSwing * 0.6662f + 3.1415927f)) / 3.1415927f * 180.0f * 0.5f;
        ((ModelRendererBends)model.bipedBody).rotation.setSmoothY(MathHelper.cos((float)(model.armSwing * 0.6662f + 3.1415927f)) / 3.1415927f * 180.0f * 0.5f, 0.3f);
        if (data.currentWalkingState == 1) {
            ((ModelRendererBends)model.bipedRightArm).rotation.setSmoothX(-120.0f);
            ((ModelRendererBends)model.bipedLeftArm).rotation.setSmoothX(-120.0f);
        }
        ((ModelRendererBends)model.bipedHead).rotation.setX(model.headRotationX - 30.0f);
        ((ModelRendererBends)model.bipedHead).rotation.setY(model.headRotationY - var1);
    }
}


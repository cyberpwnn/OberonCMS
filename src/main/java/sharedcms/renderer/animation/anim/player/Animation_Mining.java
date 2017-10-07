package sharedcms.renderer.animation.anim.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import sharedcms.audio.SFX;
import sharedcms.renderer.animation.anim.Animation;
import sharedcms.renderer.animation.client.model.ModelRendererBends;
import sharedcms.renderer.animation.client.model.entity.ModelBendsPlayer;
import sharedcms.renderer.animation.data.Data_Player;
import sharedcms.renderer.animation.data.EntityData;
import sharedcms.renderer.animation.util.SmoothVector3f;

public class Animation_Mining
extends Animation {
    @Override
    public String getName() {
        return "mining";
    }

    @Override
    public void animate(EntityLivingBase argEntity, ModelBase argModel, EntityData argData) {
        ModelBendsPlayer model = (ModelBendsPlayer)argModel;
        Data_Player data = (Data_Player)argData;
        EntityPlayer player = (EntityPlayer)argEntity;
        ((ModelRendererBends)model.bipedRightLeg).rotation.setSmoothZ(10.0f, 0.3f);
        ((ModelRendererBends)model.bipedLeftLeg).rotation.setSmoothZ(-10.0f, 0.3f);
        model.renderOffset.setSmoothY(-1.5f, 0.3f);
        if (player.isSwingInProgress) {

            float speed = 1.8f;
            float progress = (float)player.ticksExisted * speed / 20.0f % 1.0f;
            float progress2 = (float)(player.ticksExisted - 2) * speed / 20.0f % 1.0f;
            float armSwing = (MathHelper.cos((float)(progress * 3.1415927f * 2.0f)) + 1.0f) / 2.0f * -60.0f - 30.0f + model.headRotationX * 0.5f - 30.0f;
            float armYRot = 30.0f + MathHelper.cos((float)((armSwing - 90.0f) / 180.0f * 3.14f)) * -5.0f;
            ((ModelRendererBends)model.bipedRightArm).rotation.setSmoothX(armSwing, 0.7f);
            ((ModelRendererBends)model.bipedRightArm).rotation.setSmoothY(- armYRot, 0.7f);
            model.renderItemRotation.setSmoothZ(-30.0f, 0.3f);
            ((ModelRendererBends)model.bipedBody).rotation.setSmoothY(MathHelper.sin((float)(progress2 * 3.1415927f * 2.0f)) * -20.0f);
            ((ModelRendererBends)model.bipedHead).rotation.setSmoothX(model.headRotationX - model.bipedBody.rotateAngleX);
            ((ModelRendererBends)model.bipedHead).rotation.setSmoothY(model.headRotationY - model.bipedBody.rotateAngleY);
        }
    }
}


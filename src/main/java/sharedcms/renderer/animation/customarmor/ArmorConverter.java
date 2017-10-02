/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 */
package sharedcms.renderer.animation.customarmor;

import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import sharedcms.renderer.animation.client.model.ModelCustomArmor;
import sharedcms.renderer.animation.client.model.ModelRendererBends;
import sharedcms.renderer.animation.customarmor.CustomArmor;

public class ArmorConverter {
    public static CustomArmor convert(float argModelBuffnes, ModelBiped original, String argTexture) {
        ModelCustomArmor newModel = new ModelCustomArmor(argModelBuffnes, 0.0f, original.textureWidth, original.textureHeight);
        newModel.bipedBody = ArmorConverter.convertBox((ModelRendererBends)newModel.bipedBody, original.bipedBody);
        newModel.bipedCloak = ArmorConverter.convertBox((ModelRendererBends)newModel.bipedCloak, original.bipedCloak);
        newModel.bipedEars = ArmorConverter.convertBox((ModelRendererBends)newModel.bipedEars, original.bipedEars);
        newModel.bipedHead = ArmorConverter.convertBox((ModelRendererBends)newModel.bipedHead, original.bipedHead);
        newModel.bipedHeadwear = ArmorConverter.convertBox((ModelRendererBends)newModel.bipedHeadwear, original.bipedHeadwear);
        newModel.bipedLeftArm = ArmorConverter.convertBox((ModelRendererBends)newModel.bipedLeftArm, original.bipedLeftArm);
        newModel.bipedLeftLeg = ArmorConverter.convertBox((ModelRendererBends)newModel.bipedBody, original.bipedBody);
        newModel.bipedRightArm = ArmorConverter.convertBox((ModelRendererBends)newModel.bipedBody, original.bipedBody);
        newModel.bipedRightLeg = ArmorConverter.convertBox((ModelRendererBends)newModel.bipedBody, original.bipedBody);
        CustomArmor newArmor = new CustomArmor(newModel, argTexture);
        return newArmor;
    }

    public static ModelRendererBends convertBox(ModelRendererBends argBox, ModelRenderer argOld) {
        if (argOld.childModels != null) {
            for (int i = 0; i < argOld.childModels.size(); ++i) {
                argBox.childModels.add(argOld.childModels.get(i));
            }
        }
        return argBox;
    }
}


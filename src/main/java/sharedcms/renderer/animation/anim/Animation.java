package sharedcms.renderer.animation.anim;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import sharedcms.renderer.animation.data.EntityData;

public abstract class Animation {
    public abstract void animate(EntityLivingBase var1, ModelBase var2, EntityData var3);

    public abstract String getName();
}


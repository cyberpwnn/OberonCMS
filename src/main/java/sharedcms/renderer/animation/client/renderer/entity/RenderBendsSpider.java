/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderSpider
 *  net.minecraft.entity.EntityLiving
 */
package sharedcms.renderer.animation.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.entity.EntityLiving;
import sharedcms.renderer.animation.MoBends;
import sharedcms.renderer.animation.client.model.entity.ModelBendsSpider;

public class RenderBendsSpider
extends RenderSpider {
    public int refreshModel = 0;

    public RenderBendsSpider() {
        this.mainModel = new ModelBendsSpider();
        this.setRenderPassModel((ModelBase)new ModelBendsSpider());
    }

    public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        if (this.refreshModel != MoBends.refreshModel) {
            this.mainModel = new ModelBendsSpider();
            this.setRenderPassModel((ModelBase)new ModelBendsSpider());
            this.refreshModel = MoBends.refreshModel;
        }
        super.doRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}


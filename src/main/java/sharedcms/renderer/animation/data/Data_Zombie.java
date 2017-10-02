/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.Entity
 */
package sharedcms.renderer.animation.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import sharedcms.renderer.animation.client.model.ModelRendererBends;
import sharedcms.renderer.animation.client.model.entity.ModelBendsZombie;
import sharedcms.renderer.animation.data.EntityData;
import sharedcms.renderer.animation.util.SmoothVector3f;

public class Data_Zombie
extends EntityData {
    public static List<Data_Zombie> dataList = new ArrayList<Data_Zombie>();
    public ModelRendererBends head;
    public ModelRendererBends headwear;
    public ModelRendererBends body;
    public ModelRendererBends rightArm;
    public ModelRendererBends leftArm;
    public ModelRendererBends rightLeg;
    public ModelRendererBends leftLeg;
    public ModelRendererBends ears;
    public ModelRendererBends cloak;
    public ModelRendererBends rightForeArm;
    public ModelRendererBends leftForeArm;
    public ModelRendererBends rightForeLeg;
    public ModelRendererBends leftForeLeg;
    public SmoothVector3f renderOffset = new SmoothVector3f();
    public SmoothVector3f renderRotation = new SmoothVector3f();
    public int currentWalkingState = 0;
    public float ticksBeforeStateChange = 0.0f;

    public Data_Zombie(int argEntityID) {
        super(argEntityID);
    }

    public void syncModelInfo(ModelBendsZombie argModel) {
        if (this.head == null) {
            this.head = new ModelRendererBends((ModelBase)argModel);
        }
        this.head.sync((ModelRendererBends)argModel.bipedHead);
        if (this.headwear == null) {
            this.headwear = new ModelRendererBends((ModelBase)argModel);
        }
        this.headwear.sync((ModelRendererBends)argModel.bipedHeadwear);
        if (this.body == null) {
            this.body = new ModelRendererBends((ModelBase)argModel);
        }
        this.body.sync((ModelRendererBends)argModel.bipedBody);
        if (this.rightArm == null) {
            this.rightArm = new ModelRendererBends((ModelBase)argModel);
        }
        this.rightArm.sync((ModelRendererBends)argModel.bipedRightArm);
        if (this.leftArm == null) {
            this.leftArm = new ModelRendererBends((ModelBase)argModel);
        }
        this.leftArm.sync((ModelRendererBends)argModel.bipedLeftArm);
        if (this.rightLeg == null) {
            this.rightLeg = new ModelRendererBends((ModelBase)argModel);
        }
        this.rightLeg.sync((ModelRendererBends)argModel.bipedRightLeg);
        if (this.leftLeg == null) {
            this.leftLeg = new ModelRendererBends((ModelBase)argModel);
        }
        this.leftLeg.sync((ModelRendererBends)argModel.bipedLeftLeg);
        if (this.rightForeArm == null) {
            this.rightForeArm = new ModelRendererBends((ModelBase)argModel);
        }
        this.rightForeArm.sync((ModelRendererBends)argModel.bipedRightForeArm);
        if (this.leftForeArm == null) {
            this.leftForeArm = new ModelRendererBends((ModelBase)argModel);
        }
        this.leftForeArm.sync((ModelRendererBends)argModel.bipedLeftForeArm);
        if (this.rightForeLeg == null) {
            this.rightForeLeg = new ModelRendererBends((ModelBase)argModel);
        }
        this.rightForeLeg.sync((ModelRendererBends)argModel.bipedRightForeLeg);
        if (this.leftForeLeg == null) {
            this.leftForeLeg = new ModelRendererBends((ModelBase)argModel);
        }
        this.leftForeLeg.sync((ModelRendererBends)argModel.bipedLeftForeLeg);
        this.renderOffset.set(argModel.renderOffset);
        this.renderRotation.set(argModel.renderRotation);
    }

    public static void add(Data_Zombie argData) {
        dataList.add(argData);
    }

    public static Data_Zombie get(int argEntityID) {
        for (int i = 0; i < dataList.size(); ++i) {
            if (Data_Zombie.dataList.get((int)i).entityID != argEntityID) continue;
            return dataList.get(i);
        }
        Data_Zombie newData = new Data_Zombie(argEntityID);
        if (Minecraft.getMinecraft().theWorld.getEntityByID(argEntityID) != null) {
            dataList.add(newData);
        }
        return newData;
    }

    @Override
    public void update(float argPartialTicks) {
        super.update(argPartialTicks);
        this.ticksBeforeStateChange -= argPartialTicks;
        if (this.ticksBeforeStateChange <= 0.0f) {
            Random random = new Random();
            this.currentWalkingState = random.nextInt(2);
            this.ticksBeforeStateChange = 80 + random.nextInt(20);
        }
    }

    @Override
    public void onLiftoff() {
        super.onLiftoff();
    }
}


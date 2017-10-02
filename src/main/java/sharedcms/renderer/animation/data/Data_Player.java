/*
 * Decompiled with CFR 0_122.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 */
package sharedcms.renderer.animation.data;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import sharedcms.renderer.animation.client.model.ModelRendererBends;
import sharedcms.renderer.animation.client.model.entity.ModelBendsPlayer;
import sharedcms.renderer.animation.client.renderer.SwordTrail;
import sharedcms.renderer.animation.data.EntityData;
import sharedcms.renderer.animation.util.SmoothVector3f;

public class Data_Player extends EntityData
{
	public static List<Data_Player> dataList = new ArrayList<Data_Player>();
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
	public SmoothVector3f renderItemRotation = new SmoothVector3f();
	public SwordTrail swordTrail = new SwordTrail();
	public boolean sprintJumpLeg = false;
	public boolean fistPunchArm = false;
	public int currentAttack = 0;

	public Data_Player(int argEntityID)
	{
		super(argEntityID);
	}

	public void syncModelInfo(ModelBendsPlayer argModel)
	{
		if(this.head == null)
		{
			this.head = new ModelRendererBends((ModelBase) argModel);
		}
		this.head.sync((ModelRendererBends) argModel.bipedHead);
		if(this.headwear == null)
		{
			this.headwear = new ModelRendererBends((ModelBase) argModel);
		}
		this.headwear.sync((ModelRendererBends) argModel.bipedHeadwear);
		if(this.body == null)
		{
			this.body = new ModelRendererBends((ModelBase) argModel);
		}
		this.body.sync((ModelRendererBends) argModel.bipedBody);
		if(this.rightArm == null)
		{
			this.rightArm = new ModelRendererBends((ModelBase) argModel);
		}
		this.rightArm.sync((ModelRendererBends) argModel.bipedRightArm);
		if(this.leftArm == null)
		{
			this.leftArm = new ModelRendererBends((ModelBase) argModel);
		}
		this.leftArm.sync((ModelRendererBends) argModel.bipedLeftArm);
		if(this.rightLeg == null)
		{
			this.rightLeg = new ModelRendererBends((ModelBase) argModel);
		}
		this.rightLeg.sync((ModelRendererBends) argModel.bipedRightLeg);
		if(this.leftLeg == null)
		{
			this.leftLeg = new ModelRendererBends((ModelBase) argModel);
		}
		this.leftLeg.sync((ModelRendererBends) argModel.bipedLeftLeg);
		if(this.rightForeArm == null)
		{
			this.rightForeArm = new ModelRendererBends((ModelBase) argModel);
		}
		this.rightForeArm.sync((ModelRendererBends) argModel.bipedRightForeArm);
		if(this.leftForeArm == null)
		{
			this.leftForeArm = new ModelRendererBends((ModelBase) argModel);
		}
		this.leftForeArm.sync((ModelRendererBends) argModel.bipedLeftForeArm);
		if(this.rightForeLeg == null)
		{
			this.rightForeLeg = new ModelRendererBends((ModelBase) argModel);
		}
		this.rightForeLeg.sync((ModelRendererBends) argModel.bipedRightForeLeg);
		if(this.leftForeLeg == null)
		{
			this.leftForeLeg = new ModelRendererBends((ModelBase) argModel);
		}
		this.leftForeLeg.sync((ModelRendererBends) argModel.bipedLeftForeLeg);
		this.renderOffset.set(argModel.renderOffset);
		this.renderRotation.set(argModel.renderRotation);
		this.renderItemRotation.set(argModel.renderItemRotation);
		this.swordTrail = argModel.swordTrail;
	}

	public static void add(Data_Player argData)
	{
		dataList.add(argData);
	}

	public static Data_Player get(int argEntityID)
	{
		for(int i = 0; i < dataList.size(); ++i)
		{
			if(Data_Player.dataList.get((int) i).entityID != argEntityID)
				continue;
			return dataList.get(i);
		}
		Data_Player newData = new Data_Player(argEntityID);
		if(Minecraft.getMinecraft().theWorld.getEntityByID(argEntityID) != null)
		{
			dataList.add(newData);
		}
		return newData;
	}

	@Override
	public void update(float argPartialTicks)
	{
		super.update(argPartialTicks);
		if(this.ticksAfterPunch > 20.0f)
		{
			this.currentAttack = 0;
		}
	}

	@Override
	public void onLiftoff()
	{
		super.onLiftoff();
		this.sprintJumpLeg = !this.sprintJumpLeg;
	}

	@Override
	public void onPunch()
	{
		if(this.getEntity().getHeldItem() != null)
		{
			if(this.ticksAfterPunch > 6.0f)
			{
				if(this.currentAttack == 0)
				{
					this.currentAttack = 1;
					this.ticksAfterPunch = 0.0f;
				}
				
				else if(this.ticksAfterPunch < 15.0f)
				{
					if(this.currentAttack == 1)
					{
						this.currentAttack = 2;
					}
					
					else if(this.currentAttack == 2)
					{
						this.currentAttack = this.getEntity().isRiding() ? 1 : 3;
					}
					
					else if(this.currentAttack == 3)
					{
						this.currentAttack = 4;
					}

					else if(this.currentAttack == 4)
					{
						this.currentAttack = 1;
					}
					
					this.ticksAfterPunch = 0.0f;
				}
			}
		}
		else
		{
			this.fistPunchArm = !this.fistPunchArm;
			this.ticksAfterPunch = 0.0f;
		}
	}
}

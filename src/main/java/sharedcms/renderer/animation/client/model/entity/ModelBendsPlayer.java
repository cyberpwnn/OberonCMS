package sharedcms.renderer.animation.client.model.entity;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import sharedcms.renderer.animation.AnimatedEntity;
import sharedcms.renderer.animation.anim.Animation;
import sharedcms.renderer.animation.client.model.ModelBoxBends;
import sharedcms.renderer.animation.client.model.ModelRendererBends;
import sharedcms.renderer.animation.client.model.ModelRendererBends_SeperatedChild;
import sharedcms.renderer.animation.client.renderer.SwordTrail;
import sharedcms.renderer.animation.data.Data_Player;
import sharedcms.renderer.animation.data.EntityData;
import sharedcms.renderer.animation.pack.BendsPack;
import sharedcms.renderer.animation.pack.BendsVar;
import sharedcms.renderer.animation.util.SmoothVector3f;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class ModelBendsPlayer extends ModelBiped
{
	public ModelRenderer bipedRightForeArm;
	public ModelRenderer bipedLeftForeArm;
	public ModelRenderer bipedRightForeLeg;
	public ModelRenderer bipedLeftForeLeg;
	public SmoothVector3f renderOffset = new SmoothVector3f();
	public SmoothVector3f renderRotation = new SmoothVector3f();
	public SmoothVector3f renderItemRotation = new SmoothVector3f();
	public SwordTrail swordTrail = new SwordTrail();
	public float headRotationX;
	public float headRotationY;
	public float armSwing;
	public float armSwingAmount;

	public ModelBendsPlayer()
	{
		this(0.0f);
	}

	public ModelBendsPlayer(float p_i1148_1_)
	{
		this(p_i1148_1_, 0.0f, 64, 32);
	}

	public ModelBendsPlayer(float p_i1149_1_, float p_i1149_2_, int p_i1149_3_, int p_i1149_4_)
	{
		this.textureWidth = p_i1149_3_;
		this.textureHeight = p_i1149_4_;
		this.bipedCloak = new ModelRendererBends((ModelBase) this, 0, 0);
		this.bipedCloak.addBox(-5.0f, 0.0f, -1.0f, 10, 16, 1, p_i1149_1_);
		this.bipedEars = new ModelRendererBends((ModelBase) this, 24, 0);
		this.bipedEars.addBox(-3.0f, -6.0f, -1.0f, 6, 6, 1, p_i1149_1_);
		this.bipedHead = new ModelRendererBends((ModelBase) this, 0, 0);
		this.bipedHead.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, p_i1149_1_);
		this.bipedHead.setRotationPoint(0.0f, 0.0f + p_i1149_2_ - 12.0f, 0.0f);
		this.bipedHeadwear = new ModelRendererBends((ModelBase) this, 32, 0);
		this.bipedHeadwear.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, p_i1149_1_ + 0.5f);
		this.bipedHeadwear.setRotationPoint(0.0f, 0.0f, 0.0f);
		this.bipedBody = new ModelRendererBends((ModelBase) this, 16, 16).setShowChildIfHidden(true);
		this.bipedBody.addBox(-4.0f, -12.0f, -2.0f, 8, 12, 4, p_i1149_1_);
		this.bipedBody.setRotationPoint(0.0f, 0.0f + p_i1149_2_ + 12.0f, 0.0f);
		this.bipedRightArm = new ModelRendererBends_SeperatedChild((ModelBase) this, 40, 16).setMother((ModelRendererBends) this.bipedBody);
		this.bipedRightArm.addBox(-3.0f, -2.0f, -2.0f, 4, 6, 4, p_i1149_1_);
		this.bipedRightArm.setRotationPoint(-5.0f, 2.0f + p_i1149_2_ - 12.0f, 0.0f);
		this.bipedLeftArm = new ModelRendererBends_SeperatedChild((ModelBase) this, 40, 16).setMother((ModelRendererBends) this.bipedBody);
		this.bipedLeftArm.mirror = true;
		this.bipedLeftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 6, 4, p_i1149_1_);
		this.bipedLeftArm.setRotationPoint(5.0f, 2.0f + p_i1149_2_ - 12.0f, 0.0f);
		this.bipedRightLeg = new ModelRendererBends((ModelBase) this, 0, 16);
		this.bipedRightLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, p_i1149_1_);
		this.bipedRightLeg.setRotationPoint(-1.9f, 12.0f + p_i1149_2_, 0.0f);
		this.bipedLeftLeg = new ModelRendererBends((ModelBase) this, 0, 16);
		this.bipedLeftLeg.mirror = true;
		this.bipedLeftLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, p_i1149_1_);
		this.bipedLeftLeg.setRotationPoint(1.9f, 12.0f + p_i1149_2_, 0.0f);
		this.bipedRightForeArm = new ModelRendererBends((ModelBase) this, 40, 22);
		this.bipedRightForeArm.addBox(0.0f, 0.0f, -4.0f, 4, 6, 4, p_i1149_1_);
		this.bipedRightForeArm.setRotationPoint(-3.0f, 4.0f, 2.0f);
		((ModelRendererBends) this.bipedRightForeArm).getBox().offsetTextureQuad(this.bipedRightForeArm, 3, 0.0f, -6.0f);
		this.bipedLeftForeArm = new ModelRendererBends((ModelBase) this, 40, 22);
		this.bipedLeftForeArm.mirror = true;
		this.bipedLeftForeArm.addBox(0.0f, 0.0f, -4.0f, 4, 6, 4, p_i1149_1_);
		this.bipedLeftForeArm.setRotationPoint(-1.0f, 4.0f, 2.0f);
		((ModelRendererBends) this.bipedLeftForeArm).getBox().offsetTextureQuad(this.bipedRightForeArm, 3, 0.0f, -6.0f);
		this.bipedRightForeLeg = new ModelRendererBends((ModelBase) this, 0, 22);
		this.bipedRightForeLeg.addBox(-2.0f, 0.0f, 0.0f, 4, 6, 4, p_i1149_1_);
		this.bipedRightForeLeg.setRotationPoint(0.0f, 6.0f, -2.0f);
		((ModelRendererBends) this.bipedRightForeLeg).getBox().offsetTextureQuad(this.bipedRightForeLeg, 3, 0.0f, -6.0f);
		this.bipedLeftForeLeg = new ModelRendererBends((ModelBase) this, 0, 22);
		this.bipedLeftForeLeg.mirror = true;
		this.bipedLeftForeLeg.addBox(-2.0f, 0.0f, 0.0f, 4, 6, 4, p_i1149_1_);
		this.bipedLeftForeLeg.setRotationPoint(0.0f, 6.0f, -2.0f);
		((ModelRendererBends) this.bipedLeftForeLeg).getBox().offsetTextureQuad(this.bipedLeftForeLeg, 3, 0.0f, -6.0f);
		this.bipedBody.addChild(this.bipedHead);
		this.bipedBody.addChild(this.bipedRightArm);
		this.bipedBody.addChild(this.bipedLeftArm);
		this.bipedHead.addChild(this.bipedHeadwear);
		this.bipedRightArm.addChild(this.bipedRightForeArm);
		this.bipedLeftArm.addChild(this.bipedLeftForeArm);
		this.bipedRightLeg.addChild(this.bipedRightForeLeg);
		this.bipedLeftLeg.addChild(this.bipedLeftForeLeg);
		((ModelRendererBends_SeperatedChild) this.bipedRightArm).setSeperatedPart((ModelRendererBends) this.bipedRightForeArm);
		((ModelRendererBends_SeperatedChild) this.bipedLeftArm).setSeperatedPart((ModelRendererBends) this.bipedLeftForeArm);
		((ModelRendererBends) this.bipedRightArm).offsetBox_Add(-0.01f, 0.0f, -0.01f).resizeBox(4.02f, 6.0f, 4.02f).updateVertices();
		((ModelRendererBends) this.bipedLeftArm).offsetBox_Add(-0.01f, 0.0f, -0.01f).resizeBox(4.02f, 6.0f, 4.02f).updateVertices();
		((ModelRendererBends) this.bipedRightLeg).offsetBox_Add(-0.01f, 0.0f, -0.01f).resizeBox(4.02f, 6.0f, 4.02f).updateVertices();
		((ModelRendererBends) this.bipedLeftLeg).offsetBox_Add(-0.01f, 0.0f, -0.01f).resizeBox(4.02f, 6.0f, 4.02f).updateVertices();
	}

	public void render(Entity argEntity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
	{
		this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, argEntity);
		if(this.isChild)
		{
			float f6 = 2.0f;
			GL11.glPushMatrix();
			GL11.glScalef((float) (1.5f / f6), (float) (1.5f / f6), (float) (1.5f / f6));
			GL11.glTranslatef((float) 0.0f, (float) (16.0f * p_78088_7_), (float) 0.0f);
			this.bipedHead.render(p_78088_7_);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef((float) (1.0f / f6), (float) (1.0f / f6), (float) (1.0f / f6));
			GL11.glTranslatef((float) 0.0f, (float) (24.0f * p_78088_7_), (float) 0.0f);
			this.bipedBody.render(p_78088_7_);
			this.bipedRightArm.render(p_78088_7_);
			this.bipedLeftArm.render(p_78088_7_);
			this.bipedRightLeg.render(p_78088_7_);
			this.bipedLeftLeg.render(p_78088_7_);
			this.bipedHeadwear.render(p_78088_7_);
			GL11.glPopMatrix();
		}
		else
		{
			this.bipedBody.render(p_78088_7_);
			this.bipedRightLeg.render(p_78088_7_);
			this.bipedLeftLeg.render(p_78088_7_);
		}
	}

	public void setRotationAngles(float argSwingTime, float argSwingAmount, float argArmSway, float argHeadY, float argHeadX, float argNr6, Entity argEntity)
	{
		if(Minecraft.getMinecraft().theWorld == null)
		{
			return;
		}
		if(Minecraft.getMinecraft().theWorld.isRemote && Minecraft.getMinecraft().isGamePaused())
		{
			return;
		}
		Data_Player data = Data_Player.get(argEntity.getEntityId());
		this.armSwing = argSwingTime;
		this.armSwingAmount = argSwingAmount;
		this.headRotationX = argHeadX;
		this.headRotationY = argHeadY;
		if(Minecraft.getMinecraft().currentScreen != null)
		{
			this.headRotationY = 0.0f;
		}
		((ModelRendererBends) this.bipedHead).sync(data.head);
		((ModelRendererBends) this.bipedHeadwear).sync(data.headwear);
		((ModelRendererBends) this.bipedBody).sync(data.body);
		((ModelRendererBends) this.bipedRightArm).sync(data.rightArm);
		((ModelRendererBends) this.bipedLeftArm).sync(data.leftArm);
		((ModelRendererBends) this.bipedRightLeg).sync(data.rightLeg);
		((ModelRendererBends) this.bipedLeftLeg).sync(data.leftLeg);
		((ModelRendererBends) this.bipedRightForeArm).sync(data.rightForeArm);
		((ModelRendererBends) this.bipedLeftForeArm).sync(data.leftForeArm);
		((ModelRendererBends) this.bipedRightForeLeg).sync(data.rightForeLeg);
		((ModelRendererBends) this.bipedLeftForeLeg).sync(data.leftForeLeg);
		this.renderOffset.set(data.renderOffset);
		this.renderRotation.set(data.renderRotation);
		this.renderItemRotation.set(data.renderItemRotation);
		this.swordTrail = data.swordTrail;
		if(Data_Player.get(argEntity.getEntityId()).canBeUpdated())
		{
			this.renderOffset.setSmooth(new Vector3f(0.0f, -1.0f, 0.0f), 0.5f);
			this.renderRotation.setSmooth(new Vector3f(0.0f, 0.0f, 0.0f), 0.5f);
			this.renderItemRotation.setSmooth(new Vector3f(0.0f, 0.0f, 0.0f), 0.5f);
			((ModelRendererBends) this.bipedHead).resetScale();
			((ModelRendererBends) this.bipedHeadwear).resetScale();
			((ModelRendererBends) this.bipedBody).resetScale();
			((ModelRendererBends) this.bipedRightArm).resetScale();
			((ModelRendererBends) this.bipedLeftArm).resetScale();
			((ModelRendererBends) this.bipedRightLeg).resetScale();
			((ModelRendererBends) this.bipedLeftLeg).resetScale();
			((ModelRendererBends) this.bipedRightForeArm).resetScale();
			((ModelRendererBends) this.bipedLeftForeArm).resetScale();
			((ModelRendererBends) this.bipedRightForeLeg).resetScale();
			((ModelRendererBends) this.bipedLeftForeLeg).resetScale();
			BendsVar.tempData = Data_Player.get(argEntity.getEntityId());
			if(argEntity.isRiding())
			{
				AnimatedEntity.getByEntity(argEntity).get("riding").animate((EntityLivingBase) argEntity, (ModelBase) this, Data_Player.get(argEntity.getEntityId()));
				BendsPack.animate(this, "player", "riding");
			}
			else if(argEntity.isInWater())
			{
				AnimatedEntity.getByEntity(argEntity).get("swimming").animate((EntityLivingBase) argEntity, (ModelBase) this, Data_Player.get(argEntity.getEntityId()));
				BendsPack.animate(this, "player", "swimming");
			}
			else if(!Data_Player.get(argEntity.getEntityId()).isOnGround() | Data_Player.get((int) argEntity.getEntityId()).ticksAfterTouchdown < 2.0f)
			{
				AnimatedEntity.getByEntity(argEntity).get("jump").animate((EntityLivingBase) argEntity, (ModelBase) this, Data_Player.get(argEntity.getEntityId()));
				BendsPack.animate(this, "player", "jump");
			}
			else
			{
				if(Data_Player.get((int) argEntity.getEntityId()).motion.x == 0.0f & Data_Player.get((int) argEntity.getEntityId()).motion.z == 0.0f)
				{
					AnimatedEntity.getByEntity(argEntity).get("stand").animate((EntityLivingBase) argEntity, (ModelBase) this, Data_Player.get(argEntity.getEntityId()));
					BendsPack.animate(this, "player", "stand");
				}
				else if(argEntity.isSprinting())
				{
					AnimatedEntity.getByEntity(argEntity).get("sprint").animate((EntityLivingBase) argEntity, (ModelBase) this, Data_Player.get(argEntity.getEntityId()));
					BendsPack.animate(this, "player", "sprint");
				}
				else
				{
					AnimatedEntity.getByEntity(argEntity).get("walk").animate((EntityLivingBase) argEntity, (ModelBase) this, Data_Player.get(argEntity.getEntityId()));
					BendsPack.animate(this, "player", "walk");
				}
				if(argEntity.isSneaking())
				{
					AnimatedEntity.getByEntity(argEntity).get("sneak").animate((EntityLivingBase) argEntity, (ModelBase) this, Data_Player.get(argEntity.getEntityId()));
					BendsPack.animate(this, "player", "sneak");
				}
			}
			if(this.aimedBow)
			{
				AnimatedEntity.getByEntity(argEntity).get("bow").animate((EntityLivingBase) argEntity, (ModelBase) this, Data_Player.get(argEntity.getEntityId()));
				BendsPack.animate(this, "player", "bow");
			}
			else if(((EntityPlayer) argEntity).getCurrentEquippedItem() != null && ((EntityPlayer) argEntity).getCurrentEquippedItem().getItem() instanceof ItemPickaxe || ((EntityPlayer) argEntity).getCurrentEquippedItem() != null && Block.getBlockFromItem((Item) ((EntityPlayer) argEntity).getCurrentEquippedItem().getItem()) != Blocks.air)
			{
				AnimatedEntity.getByEntity(argEntity).get("mining").animate((EntityLivingBase) argEntity, (ModelBase) this, Data_Player.get(argEntity.getEntityId()));
				BendsPack.animate(this, "player", "mining");
			}
			else if(((EntityPlayer) argEntity).getCurrentEquippedItem() != null && ((EntityPlayer) argEntity).getCurrentEquippedItem().getItem() instanceof ItemAxe)
			{
				AnimatedEntity.getByEntity(argEntity).get("axe").animate((EntityLivingBase) argEntity, (ModelBase) this, Data_Player.get(argEntity.getEntityId()));
				BendsPack.animate(this, "player", "axe");
			}
			else
			{
				AnimatedEntity.getByEntity(argEntity).get("attack").animate((EntityLivingBase) argEntity, (ModelBase) this, Data_Player.get(argEntity.getEntityId()));
				BendsPack.animate(this, "player", "attack");
			}
			((ModelRendererBends) this.bipedHead).update(data.ticksPerFrame);
			((ModelRendererBends) this.bipedHeadwear).update(data.ticksPerFrame);
			((ModelRendererBends) this.bipedBody).update(data.ticksPerFrame);
			((ModelRendererBends) this.bipedLeftArm).update(data.ticksPerFrame);
			((ModelRendererBends) this.bipedRightArm).update(data.ticksPerFrame);
			((ModelRendererBends) this.bipedLeftLeg).update(data.ticksPerFrame);
			((ModelRendererBends) this.bipedRightLeg).update(data.ticksPerFrame);
			((ModelRendererBends) this.bipedLeftForeArm).update(data.ticksPerFrame);
			((ModelRendererBends) this.bipedRightForeArm).update(data.ticksPerFrame);
			((ModelRendererBends) this.bipedLeftForeLeg).update(data.ticksPerFrame);
			((ModelRendererBends) this.bipedRightForeLeg).update(data.ticksPerFrame);
			this.renderOffset.update(data.ticksPerFrame);
			this.renderRotation.update(data.ticksPerFrame);
			this.renderItemRotation.update(data.ticksPerFrame);
			this.swordTrail.update(data.ticksPerFrame);
			data.updatedThisFrame = true;
		}
		Data_Player.get(argEntity.getEntityId()).syncModelInfo(this);
	}

	public void postRender(float argScale)
	{
		GL11.glTranslatef((float) (this.renderOffset.vSmooth.x * argScale), (float) (this.renderOffset.vSmooth.y * argScale), (float) (this.renderOffset.vSmooth.z * argScale));
		GL11.glRotatef((float) (-this.renderRotation.getX()), (float) 1.0f, (float) 0.0f, (float) 0.0f);
		GL11.glRotatef((float) (-this.renderRotation.getY()), (float) 0.0f, (float) 1.0f, (float) 0.0f);
		GL11.glRotatef((float) this.renderRotation.getZ(), (float) 0.0f, (float) 0.0f, (float) 1.0f);
	}

	public void postRenderArm(float argScale)
	{
		this.bipedRightArm.postRender(argScale);
		this.bipedRightForeArm.postRender(argScale);
		GL11.glTranslatef((float) (2.0f * argScale), (float) (4.0f * argScale), (float) (2.0f * argScale));
		GL11.glRotatef((float) this.renderItemRotation.vSmooth.x, (float) 1.0f, (float) 0.0f, (float) 0.0f);
		GL11.glRotatef((float) this.renderItemRotation.vSmooth.y, (float) 0.0f, (float) -1.0f, (float) 0.0f);
		GL11.glRotatef((float) this.renderItemRotation.vSmooth.z, (float) 0.0f, (float) 0.0f, (float) 1.0f);
	}

	public void updateWithEntityData(AbstractClientPlayer argPlayer)
	{
		Data_Player data = Data_Player.get(argPlayer.getEntityId());
		if(data != null)
		{
			this.renderOffset.set(data.renderOffset);
			this.renderRotation.set(data.renderRotation);
			this.renderItemRotation.set(data.renderItemRotation);
		}
	}
}

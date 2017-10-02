package sharedcms.renderer.animation;

import cpw.mods.fml.client.registry.RenderingRegistry;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import sharedcms.renderer.animation.anim.Animation;
import sharedcms.renderer.animation.anim.player.Animation_Attack;
import sharedcms.renderer.animation.anim.player.Animation_Axe;
import sharedcms.renderer.animation.anim.player.Animation_Bow;
import sharedcms.renderer.animation.anim.player.Animation_Jump;
import sharedcms.renderer.animation.anim.player.Animation_Mining;
import sharedcms.renderer.animation.anim.player.Animation_Riding;
import sharedcms.renderer.animation.anim.player.Animation_Sneak;
import sharedcms.renderer.animation.anim.player.Animation_Sprint;
import sharedcms.renderer.animation.anim.player.Animation_Swimming;
import sharedcms.renderer.animation.anim.zombie.Animation_Stand;
import sharedcms.renderer.animation.anim.zombie.Animation_Walk;
import sharedcms.renderer.animation.client.renderer.entity.RenderBendsPlayer;
import sharedcms.renderer.animation.client.renderer.entity.RenderBendsSpider;
import sharedcms.renderer.animation.client.renderer.entity.RenderBendsZombie;
import sharedcms.renderer.animation.util.BendsLogger;

public class AnimatedEntity
{
	public static AnimatedEntity[] animatedEntities = new AnimatedEntity[] { new AnimatedEntity("player", "Player", (Entity) Minecraft.getMinecraft().thePlayer, EntityPlayer.class, (Render) new RenderBendsPlayer()).add(new sharedcms.renderer.animation.anim.player.Animation_Stand()).add(new sharedcms.renderer.animation.anim.player.Animation_Walk()).add(new Animation_Sneak()).add(new Animation_Sprint()).add(new Animation_Jump()).add(new Animation_Attack()).add(new Animation_Swimming()).add(new Animation_Bow()).add(new Animation_Riding()).add(new Animation_Mining()).add(new Animation_Axe()), new AnimatedEntity("zombie", "Zombie", (Entity) new EntityZombie(null), EntityZombie.class, (Render) new RenderBendsZombie()).add(new Animation_Stand()).add(new Animation_Walk()), new AnimatedEntity("spider", "Spider", (Entity) new EntitySpider(null), EntitySpider.class, (Render) new RenderBendsSpider()) };
	public String id;
	public String displayName;
	public Entity entity;
	public Class<? extends Entity> entityClass;
	public Render renderer;
	public List<Animation> animations = new ArrayList<Animation>();
	public boolean animate = true;

	public AnimatedEntity(String argID, String argDisplayName, Entity argEntity, Class<? extends Entity> argClass, Render argRenderer)
	{
		this.id = argID;
		this.displayName = argDisplayName;
		this.entityClass = argClass;
		this.renderer = argRenderer;
		this.entity = argEntity;
		this.animate = true;
	}

	public AnimatedEntity add(Animation argGroup)
	{
		this.animations.add(argGroup);
		return this;
	}

	public static void registerRendering()
	{
		for(int i = 0; i < animatedEntities.length; ++i)
		{
			if(!AnimatedEntity.animatedEntities[i].animate)
				continue;
			RenderingRegistry.registerEntityRenderingHandler(AnimatedEntity.animatedEntities[i].entityClass, (Render) AnimatedEntity.animatedEntities[i].renderer);
		}
		BendsLogger.log("Registering Animated Entities...", BendsLogger.INFO);
	}

	public Animation get(String argName)
	{
		for(int i = 0; i < this.animations.size(); ++i)
		{
			if(!this.animations.get(i).getName().equalsIgnoreCase(argName))
				continue;
			return this.animations.get(i);
		}
		return null;
	}

	public static AnimatedEntity getByEntity(Entity argEntity)
	{
		for(int i = 0; i < animatedEntities.length; ++i)
		{
			if(!AnimatedEntity.animatedEntities[i].entityClass.isInstance((Object) argEntity))
				continue;
			return animatedEntities[i];
		}
		return null;
	}
}

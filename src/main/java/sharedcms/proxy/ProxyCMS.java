package sharedcms.proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import sharedcms.Ares;
import sharedcms.Info;
import sharedcms.L;
import sharedcms.asm.util.Clicker;
import sharedcms.base.AresBiome;
import sharedcms.base.AresBiomeDecorator;
import sharedcms.base.AresEffect;
import sharedcms.base.AresEntitySet;
import sharedcms.base.AresGenLayerBiome;
import sharedcms.base.AresWorldGenerator;
import sharedcms.base.AresWorldType;
import sharedcms.base.TabIconChange;
import sharedcms.registry.BaseRegistrar;
import sharedcms.registry.IRegistrant;
import sharedcms.registry.IRegistrar;
import sharedcms.registry.RegistryPhase;

public class ProxyCMS implements IProxy
{
	private static List<IRegistrant> registrants = new ArrayList<IRegistrant>();
	private Map<IRegistrar, List<Object>> registrarQueue;

	public ProxyCMS()
	{
		registrarQueue = new HashMap<IRegistrar, List<Object>>();
		createDefaultRegistrars();
		Clicker.clip();
	}

	public void createRegistrar(IRegistrar r)
	{
		L.l(this, "Adding Registrar " + r.getRegistryClassType().getSimpleName() + " target:" + r.getRegistryClassType().getSimpleName() + " phase:" + r.getPhase());
		registrarQueue.put(r, new ArrayList<Object>());
	}

	public void register(Object o)
	{
		for(IRegistrar i : registrarQueue.keySet())
		{
			if(i.getRegistryClassType().isAssignableFrom(o.getClass()))
			{
				L.l(this, "Queued " + o.getClass().getSimpleName() + "<" + i.getRegistryClassType().getSimpleName() + "> to the " + i.getRegistryClassType().getSimpleName() + " registry.");
				registrarQueue.get(i).add(o);
			}
		}
	}

	private void createDefaultRegistrars()
	{
		createRegistrar(new BaseRegistrar<Block>(Block.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(Block o)
			{
				GameRegistry.registerBlock(o, o.getUnlocalizedName().substring(5));
			}
		});

		createRegistrar(new BaseRegistrar<Item>(Item.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(Item t)
			{
				GameRegistry.registerItem(t, t.getUnlocalizedName().substring(5));
				t.setTextureName(Info.ID + ":" + t.getUnlocalizedName().substring(5));
			}
		});

		createRegistrar(new BaseRegistrar<AresEntitySet>(AresEntitySet.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(AresEntitySet t)
			{
				int colorBackground = Integer.parseInt(t.getBackgroundColor(), 16);
				int colorSpot = Integer.parseInt(t.getSpotColor(), 16);
				int rid = EntityRegistry.findGlobalUniqueEntityId();
				EntityRegistry.registerGlobalEntityID(t.getE(), t.getName(), rid);
				EntityRegistry.registerModEntity(t.getE(), t.getName(), rid, Ares.instance, 24, 1, true);
				EntityList.entityEggs.put(Integer.valueOf(rid), new EntityList.EntityEggInfo(rid, colorBackground, colorSpot));
			}
		});
		
		createRegistrar(new BaseRegistrar<TabIconChange>(TabIconChange.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(TabIconChange o)
			{
				o.apply();
			}
		});
		
		createRegistrar(new BaseRegistrar<AresBiome>(AresBiome.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(AresBiome o)
			{
				o.postInit();
			}
		});
		
		createRegistrar(new BaseRegistrar<AresWorldType>(AresWorldType.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(AresWorldType o)
			{
				
			}
		});
		
		createRegistrar(new BaseRegistrar<AresGenLayerBiome>(AresGenLayerBiome.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(AresGenLayerBiome o)
			{
				
			}
		});
		
		createRegistrar(new BaseRegistrar<AresBiomeDecorator>(AresBiomeDecorator.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(AresBiomeDecorator o)
			{
				
			}
		});
		
		createRegistrar(new BaseRegistrar<AresWorldGenerator>(AresWorldGenerator.class, RegistryPhase.PRE_INIT)
		{
			@Override
			public void register(AresWorldGenerator o)
			{
				
			}
		});
		
		createRegistrar(new BaseRegistrar<AresEffect>(AresEffect.class, RegistryPhase.INIT)
		{
			@Override
			public void register(AresEffect o)
			{
				
			}
		});
	}

	private void registerFor(RegistryPhase phase)
	{
		L.l(this, "Begin Registration Phase: " + phase);
		for(IRegistrar i : registrarQueue.keySet())
		{
			if(i.getPhase().equals(phase))
			{
				List<Object> oms = new ArrayList<Object>(registrarQueue.get(i));

				L.l("Registering " + oms.size() + " queued " + i.getRegistryClassType().getSimpleName() + "s...");
				registrarQueue.get(i).clear();

				for(Object j : oms)
				{
					i.register(j);
					L.l(this, "Registered " + j.getClass().getSimpleName() + "<" + i.getRegistryClassType().getSimpleName() + "> to the " + i.getRegistryClassType().getSimpleName() + " registry.");
				}
			}
		}
		
		Clicker.clip();
	}

	@Override
	public void onPreInit(FMLPreInitializationEvent e)
	{
		registerFor(RegistryPhase.PRE_INIT);
	}

	@Override
	public void onInit(FMLInitializationEvent e)
	{
		registerFor(RegistryPhase.INIT);
	}

	@Override
	public void onPostInit(FMLPostInitializationEvent e)
	{
		registerFor(RegistryPhase.POST_INIT);
	}

	public static void addRegistrant(IRegistrant r)
	{
		L.l("Adding " + r.getClass().getSimpleName() + " to ProxyCMS registry bus");
		registrants.add(r);
	}

	public static List<IRegistrant> getRegistrants()
	{
		return registrants;
	}
}

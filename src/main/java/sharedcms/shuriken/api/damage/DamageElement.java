package sharedcms.shuriken.api.damage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import sharedcms.shuriken.damage.Damage;
import sharedcms.shuriken.damage.DamageLayer;
import sharedcms.shuriken.damage.DamageModifier;

public enum DamageElement
{
	SLASH("Slash", "SLA", new Color(255, 255, 255)),
	PUNCTURE("Puncture", "PUN", new Color(255, 255, 255)),
	BLUNT("Blunt", "BLT", new Color(255, 255, 255)),
	HEAT("Heat", "HET", new Color(255, 255, 255)),
	COLD("Cold", "CLD", new Color(255, 255, 255)),
	AIR("Air", "AIR", new Color(255, 255, 255)),
	FLOW("Flow", "FLO", new Color(255, 255, 255)),
	BIOLOGICAL("Bio", "BIO", new Color(255, 255, 255)),
	ENERGY("Energy", "ENG", new Color(255, 255, 255)),
	PLASMA("Plasma", "PLA", new Color(255, 255, 255)),
	LIGHT("Lux", "LUX", new Color(255, 255, 255)),
	DARK("Umbra", "UMB", new Color(255, 255, 255)),
	LIGHTNING("Lightning", "ZAP", new Color(255, 255, 255), PLASMA, ENERGY),
	FIRE("Fire", "FRE", new Color(255, 255, 255), PLASMA, HEAT),
	LAVA("Lava", "LAV", new Color(255, 255, 255), FIRE, FLOW),
	POISON("Poison", "PSN", new Color(255, 255, 255), DARK, BIOLOGICAL, FLOW),
	VIRAL("Viral", "VRL", new Color(255, 255, 255), DARK, BIOLOGICAL, AIR),
	SOLAR("Solar", "SLR", new Color(255, 255, 255), LIGHT, HEAT),
	FROST("Frost", "FRS", new Color(255, 255, 255), COLD, AIR),
	EXPLOSIVE("Explosive", "EXP", new Color(255, 255, 255), FIRE, LIGHTNING),
	DEMONIC("Demonic", "DEM", new Color(255, 255, 255), DARK, ENERGY),
	CHAOS("Chaos", "CHS", new Color(255, 255, 255), DEMONIC, SOLAR),
	RADIANT("Radiant", "RAD", new Color(255, 255, 255), LIGHTNING, SOLAR),
	SONIC("Sonic", "SNC", new Color(255, 255, 255), AIR, ENERGY),
	WRAITH("Wraith", "WTH", new Color(255, 255, 255), DEMONIC, BIOLOGICAL),
	CATASTROPHIC("Catastrophic", "CAT", new Color(255, 255, 255), DEMONIC, VIRAL),
	NOVA("Nova", "NVA", new Color(255, 255, 255), RADIANT, EXPLOSIVE),
	SAVAGE("Savage", "SVG", new Color(255, 255, 255), WRAITH, CATASTROPHIC),
	GRAVITON("Graviton", "GRV", new Color(255, 255, 255), FLOW, ENERGY),
	VACUUM("Vacuum", "VUM", new Color(255, 255, 255), GRAVITON, DEMONIC),
	VAMPIRIC("Vampiric", "VAM", new Color(255, 255, 255), VACUUM, POISON);
	
	private String name;
	private String shorten;
	private Color color;
	private List<DamageElement> composition;
	
	private DamageElement(String name, String shorten, Color color, DamageElement... composition)
	{
		this.name = name;
		this.shorten = shorten;
		this.color = color;
		this.composition = Arrays.asList(composition);
	}
	
	public static List<DamageElement> getBaseElements()
	{
		List<DamageElement> d = new ArrayList<DamageElement>();
		
		for(DamageElement i : DamageElement.values())
		{
			if(i.isBase())
			{
				d.add(i);
			}
		}
		
		return d;
	}
	
	public static IDamage createRandomBaseDamage(int maxTypes, double maxTotal, double maxModPct)
	{
		IDamage d = new Damage();
		List<DamageElement> elements = getBaseElements();
		maxTypes = maxTypes > elements.size() ? elements.size() : maxTypes;
		int types = (int) ((Math.random() * (maxTypes - 1)) + 1);
		double l = maxTotal;
		Collections.shuffle(elements);
		
		for(int i = 1; i <= types; i++)
		{
			double pct = i == types ? 1 : Math.random();
			double dx = l * pct;
			DamageElement e = elements.get(0);
			l -= dx;
			elements.remove(e);
			d.addDamage(new DamageLayer(e, dx));
			d.addModifier(new DamageModifier(e, Math.random() * maxModPct));
		}
		
		d.compile();
		
		return d;
	}
	
	public boolean isBase()
	{
		return composition.isEmpty();
	}
	
	public String getName()
	{
		return name;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public List<DamageElement> getComposition()
	{
		return composition;
	}
	
	public String getShorten()
	{
		return shorten;
	}
}

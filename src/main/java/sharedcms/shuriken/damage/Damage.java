package sharedcms.shuriken.damage;

import java.util.ArrayList;
import java.util.List;

import sharedcms.shuriken.api.damage.DamageElement;
import sharedcms.shuriken.api.damage.IDamage;
import sharedcms.shuriken.api.damage.IDamageLayer;
import sharedcms.shuriken.api.damage.IDamageModifier;

public class Damage implements IDamage
{
	private List<IDamageModifier> modifiers;
	private List<IDamageLayer> damage;
	
	public Damage()
	{
		modifiers = new ArrayList<IDamageModifier>();
		damage = new ArrayList<IDamageLayer>();
	}
	
	public List<IDamageModifier> getModifiers()
	{
		return modifiers;
	}
	
	public List<IDamageLayer> getDamage()
	{
		return damage;
	}
	
	public void addDamage(IDamageLayer layer)
	{
		damage.add(layer);
	}
	
	public void addModifier(IDamageModifier modifier)
	{
		modifiers.add(modifier);
	}
	
	public void compile()
	{
		List<IDamageLayer> d = new ArrayList<IDamageLayer>();
		List<IDamageLayer> b = new ArrayList<IDamageLayer>();
		List<IDamageModifier> m = new ArrayList<IDamageModifier>();
		
		for(IDamageLayer i : getDamage())
		{
			boolean a = false;
			
			for(IDamageLayer j : d)
			{
				if(j.getType().equals(i.getType()))
				{
					j.add(i.getDamage());
					a = true;
				}
			}
			
			if(!a)
			{
				d.add(i);
			}
		}
		
		for(IDamageModifier i : getModifiers())
		{
			boolean a = false;
			
			for(IDamageModifier j : m)
			{
				if(j.getTargetType().equals(i.getTargetType()))
				{
					j.add(i.getModifier());
					a = true;
				}
			}
			
			if(!a)
			{
				m.add(i);
			}
		}
		
		damage = d;
		modifiers = m;
		
		for(DamageElement i : DamageElement.values())
		{
			for(IDamageLayer j : getDamage())
			{
				if(!j.getType().equals(i))
				{
					List<DamageElement> n = new ArrayList<DamageElement>(i.getComposition());
					List<IDamageLayer> l = new ArrayList<IDamageLayer>();
					
					for(IDamageLayer k : getDamage())
					{
						if(n.contains(k.getType()))
						{
							n.remove(k.getType());
							l.add(k);
						}
					}
					
					if(n.isEmpty())
					{
						IDamageLayer lowest = null;
						
						for(IDamageLayer k : l)
						{
							if(lowest == null)
							{
								lowest = k;
							}
							
							else if(lowest.getDamage() > k.getDamage())
							{
								lowest = k;
							}
						}
						
						if(lowest != null)
						{
							DamageLayer dl = new DamageLayer(i, lowest.getDamage() / 2);
							b.add(dl);
						}
					}
				}
			}
		}
		
		if(!b.isEmpty())
		{
			int x = 0;
			
			for(IDamageLayer i : b)
			{
				boolean bx = true;
				
				for(IDamageLayer j : getDamage())
				{
					if(i.getType().equals(j.getType()))
					{
						bx = false;
					}
				}
				
				if(bx)
				{
					x++;
					damage.add(i);
				}
			}
			
			if(x > 0)
			{
				compile();
			}
		}
	}
	
	@Override
	public String toString()
	{
		String m = "";
		
		m = (int) getTotalDamage() + " ";
		
		for(IDamageLayer i : getDamage())
		{
			double mod = 0;
			
			for(IDamageModifier j : getModifiers())
			{
				if(j.getTargetType().equals(i.getType()))
				{
					mod = j.getModifier();
					break;
				}
			}
			
			m += "[" + (int) i.getDamage() + " " + i.getType().getShorten() + (mod > 0 ? " +" + ((int) (mod * 100)) + "%" : "") + "] ";
		}
		
		return m;
	}
	
	public double getTotalDamage()
	{
		double total = 0;
		
		for(IDamageLayer i : getDamage())
		{
			double dam = i.getDamage();
			double mod = 0;
			
			for(IDamageModifier j : getModifiers())
			{
				if(j.getTargetType().equals(i.getType()))
				{
					mod = j.getModifier();
					break;
				}
			}
			
			total += (dam + (dam * mod));
		}
		
		return total;
	}
}

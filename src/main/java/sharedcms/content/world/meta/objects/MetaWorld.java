package sharedcms.content.world.meta.objects;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.World;
import sharedcms.renderer.layer.SuperPosition;

public class MetaWorld
{
	private World world;
	private List<ICity> cities;

	public MetaWorld(World world)
	{
		this.world = world;
		this.cities = new ArrayList<ICity>();
		load();
	}

	public List<ICity> getCities()
	{
		return cities;
	}

	public void addCity(ICity city)
	{
		System.out.println("City Count: " + getCities().size());
		getCities().add(city);
	}

	public void save()
	{
		File worldDir = world.getSaveHandler().getWorldDirectory();
		File metaDir = new File(worldDir, "metadata");
		File cityDir = new File(metaDir, "cities");

		for(ICity i : getCities())
		{
			int x = i.getPosition().getX();
			int z = i.getPosition().getY();
			File cityFolder = new File(cityDir, "C." + x + "." + z);
			cityFolder.mkdirs();
			
			for(IVillage j : i.getVillages())
			{
				x = j.getPosition().getX();
				z = j.getPosition().getY();
				File villageFolder = new File(cityFolder, "V." + x + "." + z);
				villageFolder.mkdirs();
			}
			
			System.out.println("Saved City: " + i.getPosition().x + ", " + i.getPosition().y);
		}
	}

	public void load()
	{
		File worldDir = world.getSaveHandler().getWorldDirectory();
		File metaDir = new File(worldDir, "metadata");
		File cityDir = new File(metaDir, "cities");
		cityDir.mkdirs();
		
		for(File i : cityDir.listFiles())
		{
			if(i.isDirectory() && i.getName().startsWith("C."))
			{
				String v = i.getName().substring(2);
				String[] a = v.split("\\.");
				int x = Integer.valueOf(a[0]);
				int z = Integer.valueOf(a[1]);
				ICity c = new City(new SuperPosition(x, z));
				
				for(File j : i.listFiles())
				{
					if(j.isDirectory() && j.getName().startsWith("V."))
					{
						v = j.getName().substring(2);
						a = v.split("\\.");
						x = Integer.valueOf(a[0]);
						z = Integer.valueOf(a[1]);
						IVillage l = new Village(new SuperPosition(x, z), c);
					}
				}
				
				addCity(c);
				System.out.println("Loaded City: " + c.getPosition().x + ", " + c.getPosition().y);
			}
		}
	}
}

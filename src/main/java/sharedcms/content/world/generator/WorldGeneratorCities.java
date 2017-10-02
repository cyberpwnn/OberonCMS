package sharedcms.content.world.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import sharedcms.base.AresWorldGenerator;
import sharedcms.content.Content;
import sharedcms.content.world.meta.objects.City;
import sharedcms.content.world.meta.objects.ICity;
import sharedcms.content.world.meta.objects.Village;
import sharedcms.mutex.shared.SharedHostProxy;
import sharedcms.renderer.layer.SuperPosition;
import sharedcms.util.GList;
import sharedcms.util.M;
import sharedcms.util.SuperLocation;
import sharedcms.util.Vector;

public class WorldGeneratorCities extends AresWorldGenerator
{
	private SimplexNoiseGenerator gen;
	public static final int VILLAGES_MIN = 3;
	public static final int VILLAGES_MAX = 12;
	public static final int CITIES_DIST_MIN = 612;
	public static final int VILLAGE_DISTANCE_CITY_MIN = 100;
	public static final int VILLAGE_DISTANCE_CITY_MAX = 160;
	public static final int VILLAGE_DISTANCE_VILLAGE_MIN = 90;
	public static final int CITY_CONNECT_THRESHOLD = 700;
	public static final double CITY_CONNECT_CHANCE = 0.99;
	public static final double PATH_OFFSET = 0.87;
	public static final double PATH_OPACITY_MAX = 0.75;
	public static final double PATH_OPACITY_MIN = 0.15;
	public static final int PATH_WIDTH = 2;
	public static final Block[] PATH_MATERIAL_CITIES = new Block[] {Content.Block.PATH_STONE, Content.Block.PATH_STONE_BRICK, Content.Block.PATH_STONE_BRICK_CRACKED, Content.Block.PATH_STONE_BRICK_CRUSHED, Content.Block.PATH_GRAVEL_ROUGH};
	public static final Block[] PATH_MATERIAL_NORMAL = new Block[] {Blocks.gravel, Content.Block.PATH_GRAVEL_ROUGH, Content.Block.PATH_GRAVEL_WARM};
	public static final Block[] PATH_MATERIAL_BRIDGE = new Block[] {Content.Block.PLANKS_DARK, Content.Block.PLANKS_OAK, Content.Block.PLANKS_BIRCH};
	private Random r;
	private World world;

	public WorldGeneratorCities(long seed)
	{
		gen = new SimplexNoiseGenerator(new Random(seed));
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		this.world = world;
		r = random;

		if(r.nextInt(8) == 0)
		{
			writeCity(world, x, z);
		}

		return true;
	}

	public void writeCity(World world, int x, int z)
	{
		try
		{
			SuperPosition px = new SuperPosition(x, z);
			int min = Integer.MAX_VALUE;

			for(ICity i : SharedHostProxy.meta.get(world).getCities())
			{
				int md = (int) i.getPosition().distance(px);

				if(md < min)
				{
					min = md;
				}

				if(i.getPosition().distanceSquared(px) < Math.pow(CITIES_DIST_MIN, 2))
				{
					return;
				}
			}

			if(min == Integer.MAX_VALUE)
			{
				min = -1;
			}

			ICity city = new City(px);
			SharedHostProxy.meta.get(world).addCity(city);
			List<SuperPosition> mc = new ArrayList<SuperPosition>();
			GList<String> f = new GList<String>();
			f.add("");
			f.add("-> City @ " + x + " " + z + " (" + min + " blocks from all other cities)");

			for(int i = 0; i < mm(VILLAGES_MIN, VILLAGES_MAX); i++)
			{
				int distance = mm(VILLAGE_DISTANCE_CITY_MIN, VILLAGE_DISTANCE_CITY_MAX);
				Vector v = Vector.getRandom().subtract(Vector.getRandom());
				v.setY(0);
				v.normalize();
				v.multiply(distance);
				SuperPosition p = new SuperPosition(v.getBlockX() + x, v.getBlockZ() + z);
				boolean ok = true;

				for(SuperPosition j : mc)
				{
					if(j.distanceSquared(p) < Math.pow(VILLAGE_DISTANCE_VILLAGE_MIN, 2))
					{
						ok = false;
						break;
					}
				}

				if(ok)
				{
					mc.add(p);
					city.addVillage(new Village(p, city));
					f.add(" | Village @ " + p.x + " " + p.y);
					drawPath(p, px, PATH_WIDTH, mm(PATH_OPACITY_MIN, PATH_OPACITY_MAX), PATH_MATERIAL_NORMAL);
					drawCarpetCircle(p.x, p.y, 6, 0.87, Blocks.wool);
				}
			}

			for(ICity i : SharedHostProxy.meta.get(world).getCities())
			{
				if(i.getPosition().distance(px) < CITY_CONNECT_THRESHOLD && i.getPosition().distance(px) > CITIES_DIST_MIN)
				{
					if(M.r(CITY_CONNECT_CHANCE))
					{
						drawPath(px, i.getPosition(), 4, 0.88, PATH_MATERIAL_CITIES);
						f.add(" & Connecting with City @ " + i.getPosition().x + " " + i.getPosition().y);
					}

					return;
				}
			}

			drawCarpetCircle(px.x, px.y, 12, 0.87, Blocks.stonebrick);

			System.out.println(f.toString("\n"));
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void drawPath(SuperPosition aa, SuperPosition bb, int rad, double opacity, Block... blocks)
	{
		SuperPosition a = aa.copy();
		SuperPosition b = bb.copy();
		Vector v = new Vector();

		while(a.distance(b) > 4)
		{
			drawCarpetCircle(a.x, a.y, rad, opacity, blocks);

			if(M.r(0.25))
			{
				int x = a.x + mm(-rad - 4, rad + 4);
				int y = a.y + mm(-rad - 4, rad + 4);
				int h = height(x, y);
				draw(x, h + 1, y, r(blocks));
				draw(x, h + 2, y, r(Blocks.glowstone, Blocks.torch));
				// world.markBlockForUpdate(x, h + 2, y);
				// world.markBlockForUpdate(x, h + 1, y);
			}

			v.zero();
			v.setX(b.x - a.x);
			v.setZ(b.y - a.y);
			v.normalize();
			Vector vmul = Vector.getRandom().subtract(Vector.getRandom()).normalize().multiply(PATH_OFFSET);
			v.add(vmul);
			v.setY(0);
			v.normalize();
			v.multiply(rad);
			a.x += v.getBlockX();
			a.y += v.getBlockZ();
		}
	}

	public void drawCircle(int xx, int yy, int rad, int h, double opacity, Block... blocks)
	{
		for(int y = -rad; y <= rad; y++)
		{
			for(int x = -rad; x <= rad; x++)
			{
				if(x * x + y * y <= rad * rad)
				{
					draw(xx + x, h, yy + y, opacity, blocks);
				}
			}
		}
	}

	public void drawCarpetCircle(int xx, int yy, int rad, double opacity, Block... blocks)
	{
		drawCircle(xx, yy, rad, -1, opacity, blocks);
	}

	public void drawCarpetPlane(SuperPosition a, SuperPosition b, double opacity, Block... blocks)
	{
		for(int i = mi(a.x, b.x); i < ma(a.x, b.x); i++)
		{
			for(int j = mi(a.y, b.y); j < ma(a.y, b.y); j++)
			{
				draw(i, j, opacity, blocks);
			}
		}
	}

	public void drawCuboid(SuperLocation a, SuperLocation b, double opacity, Block... blocks)
	{
		for(int i = mi(a.x, b.x); i < ma(a.x, b.x); i++)
		{
			for(int j = mi(a.y, b.y); j < ma(a.y, b.y); j++)
			{
				for(int k = mi(a.z, b.z); k < ma(a.z, b.z); k++)
				{
					draw(i, j, k, opacity, blocks);
				}
			}
		}
	}

	public void draw(int x, int z, double opacity, Block... blocks)
	{
		if(heightWasWater(x, z))
		{
			int k = height(x, z);

			for(int i = k; i > k - 12; i--)
			{
				draw(x, i, z, r(PATH_MATERIAL_BRIDGE));
			}
		}

		else if(M.r(opacity))
		{
			draw(x, height(x, z), z, r(blocks));
		}
	}

	public void draw(int x, int y, int z, double opacity, Block... blocks)
	{
		if(y < 0)
		{
			draw(x, z, opacity, blocks);
			return;
		}

		if(M.r(opacity))
		{
			draw(x, y, z, r(blocks));
		}
	}

	public void draw(int x, int z, Block block)
	{
		world.setBlock(x, height(x, z), z, block);
	}

	public void draw(int x, int y, int z, Block block)
	{
		if(y < 0)
		{
			draw(x, z, block);
			return;
		}

		world.setBlock(x, y, z, block);
	}

	public int mi(int... ints)
	{
		int k = Integer.MAX_VALUE;

		for(int i : ints)
		{
			if(i < k)
			{
				k = i;
			}
		}

		return k;
	}

	public int ma(int... ints)
	{
		int k = Integer.MIN_VALUE;

		for(int i : ints)
		{
			if(i > k)
			{
				k = i;
			}
		}

		return k;
	}

	public int mi(double... ints)
	{
		double k = Double.MAX_VALUE;

		for(double i : ints)
		{
			if(i < k)
			{
				k = i;
			}
		}

		return (int) k;
	}

	public int ma(double... ints)
	{
		double k = Double.MIN_VALUE;

		for(double i : ints)
		{
			if(i > k)
			{
				k = i;
			}
		}

		return (int) k;
	}

	public int mm(int min, int max)
	{
		return r.nextInt(max - min) + min;
	}

	public double mm(double min, double max)
	{
		return (r.nextDouble() * (max - min)) + min;
	}

	public Block r(Block... b)
	{
		return b[(int) (Math.random() * b.length)];
	}

	public boolean heightWasWater(int x, int z)
	{
		for(int i = 255; i > 0; i--)
		{
			if(world.getBlock(x, i, z).equals(Blocks.water) || world.getBlock(x, i, z).equals(Blocks.flowing_water))
			{
				return true;
			}

			if(world.getBlockLightOpacity(x, i, z) == 255)
			{
				return false;
			}
		}

		return false;
	}

	public int height(int x, int z)
	{
		for(int i = 255; i > 0; i--)
		{
			if(world.getBlock(x, i, z).equals(Blocks.water) || world.getBlock(x, i, z).equals(Blocks.flowing_water))
			{
				return i;
			}

			if(world.getBlockLightOpacity(x, i, z) == 255)
			{
				return i;
			}
		}

		return 0;
	}
}

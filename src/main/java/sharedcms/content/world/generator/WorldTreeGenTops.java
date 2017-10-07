package sharedcms.content.world.generator;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.common.util.ForgeDirection;
import sharedcms.content.Content;

public class WorldTreeGenTops extends WorldGenTrees
{
	private Block log;
	private Block leaves;
	private int height;
	private int wbonus;
	private int wrbonus;

	public WorldTreeGenTops(Block log, Block leaves)
	{
		super(false);

		this.log = log;
		this.leaves = leaves;
		this.height = 14;
		this.wbonus = 0;
		this.wrbonus = 0;
	}

	public WorldTreeGenTops(Block log, Block leaves, int height, int wbonus, int wrbonus)
	{
		this(log, leaves);

		this.height = height;
		this.wbonus = wbonus;
		this.wrbonus = wrbonus;
	}

	public boolean generate(World w, Random r, int x, int y, int z)
	{
		int l = r.nextInt(3) + r.nextInt(2) + height;
		boolean flag = true;

		if(y >= 1 && y + l + 1 <= 256)
		{
			int j1;
			int k1;

			for(int i1 = y; i1 <= y + 1 + l; ++i1)
			{
				byte b0 = 1;

				if(i1 == y)
				{
					b0 = 0;
				}

				if(i1 >= y + 1 + l - 2)
				{
					b0 = 2;
				}

				for(j1 = x - b0; j1 <= x + b0 && flag; ++j1)
				{
					for(k1 = z - b0; k1 <= z + b0 && flag; ++k1)
					{
						if(i1 >= 0 && i1 < 256)
						{
							Block block = w.getBlock(j1, i1, k1);
							flag = true;
						}

						else
						{
							flag = false;
						}
					}
				}
			}

			if(!flag)
			{
				return false;
			}

			else
			{
				Block block2 = w.getBlock(x, y - 1, z);

				boolean isSoil = isSoil(block2);

				if(isSoil && y < 256 - l - 1)
				{
					onPlantGrow(w, x, y - 1, z, x, y, z);
					onPlantGrow(w, x + 1, y - 1, z, x, y, z);
					onPlantGrow(w, x + 1, y - 1, z + 1, x, y, z);
					onPlantGrow(w, x, y - 1, z + 1, x, y, z);
					int j3 = r.nextInt(4);
					j1 = l - r.nextInt(4);
					k1 = 2 - r.nextInt(3);
					int k3 = x;
					int l1 = z;
					int i2 = 0;
					int j2;
					int k2;

					for(j2 = 0; j2 < l; ++j2)
					{
						k2 = y + j2 - 3;

						if(j2 >= j1 && k1 > 0)
						{
							k3 += Direction.offsetX[j3];
							l1 += Direction.offsetZ[j3];
							--k1;
						}

						Block block1 = w.getBlock(k3, k2, l1);

						if(block1.isAir(w, k3, k2, l1) || block1.isLeaves(w, k3, k2, l1))
						{
							this.setBlockAndNotifyAdequately(w, k3, k2, l1, log, 1);
							this.setBlockAndNotifyAdequately(w, k3 + 1, k2, l1, log, 1);
							this.setBlockAndNotifyAdequately(w, k3, k2, l1 + 1, log, 1);
							this.setBlockAndNotifyAdequately(w, k3 + 1, k2, l1 + 1, log, 1);
							
							if(j2 < 4)
							{
								this.setBlockAndNotifyAdequately(w, k3 - 1, k2, l1, log, 1);
								this.setBlockAndNotifyAdequately(w, k3, k2, l1 - 1, log, 1);
								this.setBlockAndNotifyAdequately(w, k3 - 1, k2, l1 - 1, log, 1);
								this.setBlockAndNotifyAdequately(w, k3 + 2, k2, l1, log, 1);
								this.setBlockAndNotifyAdequately(w, k3, k2, l1 + 2, log, 1);
								this.setBlockAndNotifyAdequately(w, k3 + 2, k2, l1 + 2, log, 1);
							}
							

							i2 = k2;
						}
					}

					for(j2 = -2; j2 <= 0; ++j2)
					{
						for(k2 = -2; k2 <= 0; ++k2)
						{
							byte b1 = -1;
							this.func_150526_a(w, k3 + j2, i2 + b1, l1 + k2, r);
							this.func_150526_a(w, 1 + k3 - j2, i2 + b1, l1 + k2, r);
							this.func_150526_a(w, k3 + j2, i2 + b1, 1 + l1 - k2, r);
							this.func_150526_a(w, 1 + k3 - j2, i2 + b1, 1 + l1 - k2, r);

							if((j2 > -2 || k2 > -1) && (j2 != -1 || k2 != -2))
							{
								byte b2 = 1;
								this.func_150526_a(w, k3 + j2, i2 + b2, l1 + k2, r);
								this.func_150526_a(w, 1 + k3 - j2, i2 + b2, l1 + k2, r);
								this.func_150526_a(w, k3 + j2, i2 + b2, 1 + l1 - k2, r);
								this.func_150526_a(w, 1 + k3 - j2, i2 + b2, 1 + l1 - k2, r);
							}
						}
					}

					if(r.nextBoolean())
					{
						this.func_150526_a(w, k3, i2 + 2, l1, r);
						this.func_150526_a(w, k3 + 1, i2 + 2, l1, r);
						this.func_150526_a(w, k3 + 1, i2 + 2, l1 + 1, r);
						this.func_150526_a(w, k3, i2 + 2, l1 + 1, r);
					}

					for(j2 = -3; j2 <= 4; ++j2)
					{
						for(k2 = -3; k2 <= 4; ++k2)
						{
							if((j2 != -3 || k2 != -3) && (j2 != -3 || k2 != 4) && (j2 != 4 || k2 != -3) && (j2 != 4 || k2 != 4) && (Math.abs(j2) < 3 || Math.abs(k2) < 3))
							{
								this.func_150526_a(w, k3 + j2, i2, l1 + k2, r);
							}
						}
					}

					for(j2 = -1; j2 <= 2; ++j2)
					{
						for(k2 = -1; k2 <= 2; ++k2)
						{
							if((j2 < 0 || j2 > 1 || k2 < 0 || k2 > 1) && r.nextInt(3) <= 0)
							{
								int l3 = r.nextInt(3) + 2;
								int l2;

								for(l2 = 0; l2 < l3; ++l2)
								{
									this.setBlockAndNotifyAdequately(w, x + j2, i2 - l2 - 1, z + k2, log, 1);
								}

								int i3;

								for(l2 = -3 - wbonus; l2 <= 3 + wbonus; ++l2)
								{
									for(i3 = -3 - wbonus; i3 <= 3 + wbonus; ++i3)
									{
										this.func_150526_a(w, k3 + j2 + l2, i2 - 0, l1 + k2 + i3, r);
									}
								}

								for(l2 = -6 - wbonus; l2 <= 6 + wbonus; ++l2)
								{
									for(i3 = -6 - wbonus; i3 <= 6 + wbonus; ++i3)
									{
										if(Math.abs(l2) != 6 + wbonus || Math.abs(i3) != 6 + wbonus)
										{
											if(Math.abs(l2) + Math.abs(i3) < 8 + wbonus + r.nextInt(2 + wrbonus))
											{
												this.func_150526_a(w, k3 + j2 + l2, i2 - 1, l1 + k2 + i3, r);
											}
										}
									}
								}
								
								if(wbonus > 0)
								{
									int vv = wbonus;
									
									while(vv > -4)
									{
										for(l2 = -6 - vv; l2 <= 6 + vv; ++l2)
										{
											for(i3 = -6 - vv; i3 <= 6 + vv; ++i3)
											{
												if(Math.abs(l2) != 6 + vv || Math.abs(i3) != 6 + vv)
												{
													if(Math.abs(l2) + Math.abs(i3) < 8 + vv + r.nextInt(2 + wrbonus))
													{
														this.func_150526_a(w, k3 + j2 + l2, i2 - 1, l1 + k2 + i3, r);
													}
												}
											}
										}
										
										vv -= wbonus / 2;
									}
								}

								for(l2 = -4; l2 <= 4; ++l2)
								{
									for(i3 = -4; i3 <= 4; ++i3)
									{
										if(Math.abs(l2) != 4 || Math.abs(i3) != 4)
										{
											if(r.nextInt(3) == 0)
											{
												this.func_150526_a(w, k3 + j2 + l2, i2 - 2, l1 + k2 + i3, r);
											}
										}
									}
								}
							}
						}
					}

					return true;
				}

				else
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
	}

	public boolean isSoil(Block b)
	{
		if(b.equals(Content.Block.PODZOL))
		{
			return true;
		}

		if(b.equals(Content.Block.PODZOL_MOSSY))
		{
			return true;
		}

		if(b.equals(Content.Block.ARID_SAND))
		{
			return true;
		}

		if(b.equals(Content.Block.GLACIAL_GRASS))
		{
			return true;
		}

		if(b.equals(Blocks.dirt))
		{
			return true;
		}

		if(b.equals(Blocks.grass))
		{
			return true;
		}

		return false;
	}

	public Block getLeavesBlock(Random rand)
	{
		if(rand.nextInt(4) == 0)
		{
			return Content.Block.LEAVES_ACACIA;
		}

		if(rand.nextInt(4) == 0)
		{
			return Content.Block.LEAVES_DARK;
		}

		if(rand.nextInt(4) == 0)
		{
			return Content.Block.LEAVES_SPRUCE;
		}

		if(rand.nextInt(4) == 0)
		{
			return Content.Block.LEAVES_OAK;
		}

		return leaves;
	}

	private void func_150526_a(World p_150526_1_, int p_150526_2_, int p_150526_3_, int p_150526_4_, Random rand)
	{
		Block block = p_150526_1_.getBlock(p_150526_2_, p_150526_3_, p_150526_4_);

		if(block.isAir(p_150526_1_, p_150526_2_, p_150526_3_, p_150526_4_))
		{
			this.setBlockAndNotifyAdequately(p_150526_1_, p_150526_2_, p_150526_3_, p_150526_4_, getLeavesBlock(rand), 1);
		}
	}

	// Just a helper macro
	private void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
	{
		world.getBlock(x, y, z).onPlantGrow(world, x, y, z, sourceX, sourceY, sourceZ);
	}
}

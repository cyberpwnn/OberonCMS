package sharedcms.voxel;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import sharedcms.Info;
import sharedcms.content.world.generator.SimplexNoiseGenerator;
import sharedcms.controller.shared.BoxelController;
import sharedcms.gui.util.R;

public class SoftBlockRenderer
{
	public static boolean hit = false;
	public static SimplexNoiseGenerator simplex = new SimplexNoiseGenerator(1337);
	public static SimplexNoiseGenerator perlin = new SimplexNoiseGenerator(-1337);

	public boolean renderSoftBlock(Block block, int x, int y, int z, RenderBlocks renderer, IBlockAccess world)
	{
		Tessellator tessellator = Tessellator.instance;
		int meta = world.getBlockMetadata(x, y, z);
		int color = block.colorMultiplier(world, x, y, z);
		float colorRed = (float) (color >> 16 & 255) / 255.0f;
		float colorGreen = (float) (color >> 8 & 255) / 255.0f;
		float colorBlue = (float) (color & 255) / 255.0f;
		float shadowBottom = 0.6f;
		float shadowTop = 1.0f;
		float shadowLeft = 0.9f;
		float shadowRight = 0.8f;
		IIcon icon = !renderer.hasOverrideBlockTexture() ? renderer.getBlockIconFromSideAndMetadata(block, 1, meta) : renderer.overrideBlockTexture;
		double minU = icon.getMinU();
		double minV = icon.getMinV();
		double maxU = icon.getMaxU();
		double maxV = icon.getMaxV();
		Vec3[] points = new Vec3[] {Vec3.createVectorHelper((double) 0.0, (double) 0.0, (double) 0.0), Vec3.createVectorHelper((double) 1.0, (double) 0.0, (double) 0.0), Vec3.createVectorHelper((double) 1.0, (double) 0.0, (double) 1.0), Vec3.createVectorHelper((double) 0.0, (double) 0.0, (double) 1.0), Vec3.createVectorHelper((double) 0.0, (double) 1.0, (double) 0.0), Vec3.createVectorHelper((double) 1.0, (double) 1.0, (double) 0.0), Vec3.createVectorHelper((double) 1.0, (double) 1.0, (double) 1.0), Vec3.createVectorHelper((double) 0.0, (double) 1.0, (double) 1.0)};

		for(int point = 0; point < 8; ++point)
		{
			Vec3 vec3 = points[point];
			vec3.xCoord += (double) x;
			Vec3 vec4 = points[point];
			vec4.yCoord += (double) y;
			Vec3 vec5 = points[point];
			vec5.zCoord += (double) z;

			if(SoftBlockRenderer.doesPointIntersectWithManufactured(world, points[point]))
			{
				continue;
			}

			if(point < 4 && SoftBlockRenderer.doesPointBottomIntersectWithAir(world, points[point]))
			{
				points[point].yCoord = (double) y + 1.0;
			}

			else if(point >= 4 && SoftBlockRenderer.doesPointTopIntersectWithAir(world, points[point]))
			{
				points[point].yCoord = y;
			}

			points[point] = this.givePointRoughness(points[point], block, x, y, z, world);
		}

		for(int side = 0; side < 6; ++side)
		{
			int facingX = x;
			int facingY = y;
			int facingZ = z;

			if(side == 0)
			{
				--facingY;
			}

			else if(side == 1)
			{
				++facingY;
			}

			else if(side == 2)
			{
				--facingZ;
			}

			else if(side == 3)
			{
				++facingX;
			}

			else if(side == 4)
			{
				++facingZ;
			}

			else if(side == 5)
			{
				--facingX;
			}

			if(!renderer.renderAllFaces && !block.shouldSideBeRendered(world, facingX, facingY, facingZ, side))
			{
				continue;
			}

			float colorFactor = 1.0f;
			Vec3 vertex0 = null;
			Vec3 vertex2 = null;
			Vec3 vertex3 = null;
			Vec3 vertex4 = null;

			if(side == 0)
			{
				colorFactor = 0.6f;
				vertex0 = points[0];
				vertex2 = points[1];
				vertex3 = points[2];
				vertex4 = points[3];
			}

			else if(side == 1)
			{
				colorFactor = 1.0f;
				vertex0 = points[7];
				vertex2 = points[6];
				vertex3 = points[5];
				vertex4 = points[4];
			}

			else if(side == 2)
			{
				colorFactor = 0.9f;
				vertex0 = points[1];
				vertex2 = points[0];
				vertex3 = points[4];
				vertex4 = points[5];
			}

			else if(side == 3)
			{
				colorFactor = 0.8f;
				vertex0 = points[2];
				vertex2 = points[1];
				vertex3 = points[5];
				vertex4 = points[6];
			}

			else if(side == 4)
			{
				colorFactor = 0.9f;
				vertex0 = points[3];
				vertex2 = points[2];
				vertex3 = points[6];
				vertex4 = points[7];
			}

			else if(side == 5)
			{
				colorFactor = 0.8f;
				vertex0 = points[0];
				vertex2 = points[3];
				vertex3 = points[7];
				vertex4 = points[4];
			}

			tessellator.setBrightness(block.getMixedBrightnessForBlock(world, facingX, facingY, facingZ));
			tessellator.setColorOpaque_F(1.0f * colorFactor * colorRed, 1.0f * colorFactor * colorGreen, 1.0f * colorFactor * colorBlue);
			tessellator.addVertexWithUV(vertex0.xCoord, vertex0.yCoord, vertex0.zCoord, minU, maxV);
			tessellator.addVertexWithUV(vertex2.xCoord, vertex2.yCoord, vertex2.zCoord, maxU, maxV);
			tessellator.addVertexWithUV(vertex3.xCoord, vertex3.yCoord, vertex3.zCoord, maxU, minV);
			tessellator.addVertexWithUV(vertex4.xCoord, vertex4.yCoord, vertex4.zCoord, minU, minV);
		}

		return true;
	}

	private Vec3 givePointRoughness(Vec3 point, Block block, int x, int y, int z, IBlockAccess world)
	{
		double rh = Info.TESSELLATION_RATE_HEIGHT * Info.TESSELLATION_VERTEX_MODIFIER;
		double rw = Info.TESSELLATION_RATE_WIDTH * Info.TESSELLATION_VERTEX_MODIFIER;
		double tsx = Info.TESSELLATION_SIMPLEX_X;
		double tsy = Info.TESSELLATION_SIMPLEX_Y;
		double tsz = Info.TESSELLATION_SIMPLEX_Z;
		double tpx = Info.TESSELLATION_PERLIN_X;
		double tpy = Info.TESSELLATION_PERLIN_Y;
		double tpz = Info.TESSELLATION_PERLIN_Z;
		double thx = Info.TESSELLATION_SHIFT_X;
		double thy = Info.TESSELLATION_SHIFT_Y;
		double thz = Info.TESSELLATION_SHIFT_Z;
		double tax = Info.TESSELLATION_RAD_X;
		double tay = Info.TESSELLATION_RAD_Y;
		double taz = Info.TESSELLATION_RAD_Z;
		double mod = Info.TESSELLATION_SIMPLEX_MODIFIER;
		double mof = R.CLIP(Info.TESSELLATION_SIMPLEX_MODIFIER, 0, 1);
		boolean tfk = Info.TESSELLATION_SIMPLEX;
		
		if(!Info.TESSELLATION_VERTEX)
		{
			rh = 0;
			rw = 0;
		}
		
		long i = (long) (point.xCoord * 3129871.0) ^ (long) point.yCoord * 116129781 ^ (long) point.zCoord;
		i = i * i * 42317861 + i * 11;
		point.xCoord += (double) (((float) (i >> 16 & 15) / 15.0f - 0.5f) * rw);
		point.yCoord += (double) (((float) (i >> 20 & 15) / 15.0f - 0.5f) * rh);
		point.zCoord += (double) (((float) (i >> 24 & 15) / 15.0f - 0.5f) * rw);
		
		if(tfk)
		{
			point.xCoord += ((simplex.getNoise((double) point.xCoord / tsx, (double) point.yCoord / tsx, (double) point.zCoord / tsx)) * thx);
			point.xCoord -= mod * ((perlin.getNoise((double) point.xCoord / tpx, (double) point.yCoord / tpx, (double) point.zCoord / tpx)) * tax);
			point.yCoord += 0.01 * ((simplex.getNoise((double) point.yCoord / tsy, (double) point.zCoord / tsy, (double) point.xCoord / tsy)) * thy);
			point.yCoord -= 0.01 * mof * ((perlin.getNoise((double) point.yCoord / tpy, (double) point.zCoord / tpy, (double) point.xCoord / tpy)) * tay);
			point.zCoord += ((simplex.getNoise((double) point.zCoord / tsz, (double) point.yCoord / tsz, (double) point.xCoord / tsz)) * thz);
			point.zCoord -= mod * ((perlin.getNoise((double) point.zCoord / tpz, (double) point.yCoord / tpz, (double) point.xCoord / tpz)) * taz);
		}

		return point;
	}

	public static boolean isBlockAirOrPlant(Block block)
	{
		Material material = block.getMaterial();

		return material == Material.air || material == Material.plants || material == Material.vine || BoxelController.isBlockLiquid(block);
	}

	public static boolean doesPointTopIntersectWithAir(IBlockAccess world, Vec3 point)
	{
		boolean intersects = false;

		for(int i = 0; i < 4; ++i)
		{
			int x1 = (int) (point.xCoord - (double) (i & 1));
			int z1 = (int) (point.zCoord - (double) (i >> 1 & 1));

			if(!SoftBlockRenderer.isBlockAirOrPlant(world.getBlock(x1, (int) point.yCoord, z1)))
			{
				return false;
			}

			if(!SoftBlockRenderer.isBlockAirOrPlant(world.getBlock(x1, (int) point.yCoord - 1, z1)))
			{
				continue;
			}

			intersects = true;
		}

		return intersects;
	}

	public static boolean doesPointBottomIntersectWithAir(IBlockAccess world, Vec3 point)
	{
		boolean intersects = false;
		boolean notOnly = false;

		for(int i = 0; i < 4; ++i)
		{
			int x1 = (int) (point.xCoord - (double) (i & 1));
			int z1 = (int) (point.zCoord - (double) (i >> 1 & 1));

			if(!SoftBlockRenderer.isBlockAirOrPlant(world.getBlock(x1, (int) point.yCoord - 1, z1)))
			{
				return false;
			}

			if(!SoftBlockRenderer.isBlockAirOrPlant(world.getBlock(x1, (int) point.yCoord + 1, z1)))
			{
				notOnly = true;
			}

			if(!SoftBlockRenderer.isBlockAirOrPlant(world.getBlock(x1, (int) point.yCoord, z1)))
			{
				continue;
			}

			intersects = true;
		}

		return intersects && notOnly;
	}

	public static boolean doesPointIntersectWithManufactured(IBlockAccess world, Vec3 point)
	{
		for(int i = 0; i < 4; ++i)
		{
			int x1 = (int) (point.xCoord - (double) (i & 1));
			int z1 = (int) (point.zCoord - (double) (i >> 1 & 1));
			Block block = world.getBlock(x1, (int) point.yCoord, z1);

			if(!SoftBlockRenderer.isBlockAirOrPlant(block) && !BoxelController.isBlockSoft(block))
			{
				return true;
			}

			Block block2 = world.getBlock(x1, (int) point.yCoord - 1, z1);

			if(SoftBlockRenderer.isBlockAirOrPlant(block2) || BoxelController.isBlockSoft(block2))
			{
				continue;
			}

			return true;
		}

		return false;
	}

	public boolean renderLiquidBlock(Block block, int x, int y, int z, RenderBlocks renderer, IBlockAccess world)
	{
		boolean rendered = renderer.renderBlockLiquid(block, x, y, z);

		if(BoxelController.isBlockLiquid(world.getBlock(x, y + 1, z)))
		{
			return rendered;
		}

		int brightness = block.getMixedBrightnessForBlock(world, x, y, z);

		if(BoxelController.isBlockSoft(world.getBlock(x + 1, y, z)))
		{
			this.renderGhostLiquid(block, x + 1, y, z, brightness, renderer, world);
		}

		if(BoxelController.isBlockSoft(world.getBlock(x, y, z + 1)) && !BoxelController.isBlockLiquid(world.getBlock(x - 1, y, z + 1)))
		{
			this.renderGhostLiquid(block, x, y, z + 1, brightness, renderer, world);
		}

		if(BoxelController.isBlockSoft(world.getBlock(x - 1, y, z)) && !BoxelController.isBlockLiquid(world.getBlock(x - 2, y, z)) && !BoxelController.isBlockLiquid(world.getBlock(x - 1, y, z - 1)))
		{
			this.renderGhostLiquid(block, x - 1, y, z, brightness, renderer, world);
		}

		if(BoxelController.isBlockSoft(world.getBlock(x, y, z - 1)) && !BoxelController.isBlockLiquid(world.getBlock(x - 1, y, z - 1)) && !BoxelController.isBlockLiquid(world.getBlock(x, y, z - 2)) && !BoxelController.isBlockLiquid(world.getBlock(x + 1, y, z - 1)))
		{
			this.renderGhostLiquid(block, x, y, z - 1, brightness, renderer, world);
		}

		if(!(!BoxelController.isBlockSoft(world.getBlock(x + 1, y, z + 1)) || BoxelController.isBlockLiquid(world.getBlock(x, y, z + 1)) || BoxelController.isBlockLiquid(world.getBlock(x + 1, y, z)) || BoxelController.isBlockLiquid(world.getBlock(x + 2, y, z + 1)) || BoxelController.isBlockLiquid(world.getBlock(x + 1, y, z + 2))))
		{
			this.renderGhostLiquid(block, x + 1, y, z + 1, brightness, renderer, world);
		}

		if(!(!BoxelController.isBlockSoft(world.getBlock(x + 1, y, z - 1)) || BoxelController.isBlockLiquid(world.getBlock(x, y, z - 1)) || BoxelController.isBlockLiquid(world.getBlock(x + 1, y, z - 2)) || BoxelController.isBlockLiquid(world.getBlock(x + 2, y, z - 1)) || BoxelController.isBlockLiquid(world.getBlock(x + 1, y, z)) || BoxelController.isBlockLiquid(world.getBlock(x, y, z - 2))))
		{
			this.renderGhostLiquid(block, x + 1, y, z - 1, brightness, renderer, world);
		}

		if(!(!BoxelController.isBlockSoft(world.getBlock(x - 1, y, z - 1)) || BoxelController.isBlockLiquid(world.getBlock(x - 2, y, z - 1)) || BoxelController.isBlockLiquid(world.getBlock(x - 1, y, z - 2)) || BoxelController.isBlockLiquid(world.getBlock(x, y, z - 1)) || BoxelController.isBlockLiquid(world.getBlock(x - 1, y, z)) || BoxelController.isBlockLiquid(world.getBlock(x - 2, y, z - 2)) || BoxelController.isBlockLiquid(world.getBlock(x - 2, y, z))))
		{
			this.renderGhostLiquid(block, x - 1, y, z - 1, brightness, renderer, world);
		}

		if(!(!BoxelController.isBlockSoft(world.getBlock(x - 1, y, z + 1)) || BoxelController.isBlockLiquid(world.getBlock(x - 2, y, z + 1)) || BoxelController.isBlockLiquid(world.getBlock(x - 1, y, z)) || BoxelController.isBlockLiquid(world.getBlock(x, y, z + 1)) || BoxelController.isBlockLiquid(world.getBlock(x - 1, y, z + 2)) || BoxelController.isBlockLiquid(world.getBlock(x - 2, y, z)) || BoxelController.isBlockLiquid(world.getBlock(x - 2, y, z + 2)) || BoxelController.isBlockLiquid(world.getBlock(x, y, z + 2))))
		{
			this.renderGhostLiquid(block, x - 1, y, z + 1, brightness, renderer, world);
		}

		return rendered;
	}

	public boolean doesPointIntersectWithLiquid(int x, int y, int z, IBlockAccess world)
	{
		return BoxelController.isBlockLiquid(world.getBlock(x, y, z)) || BoxelController.isBlockLiquid(world.getBlock(x - 1, y, z)) || BoxelController.isBlockLiquid(world.getBlock(x, y, z - 1)) || BoxelController.isBlockLiquid(world.getBlock(x - 1, y, z - 1)) || BoxelController.isBlockLiquid(world.getBlock(x, y + 1, z)) || BoxelController.isBlockLiquid(world.getBlock(x - 1, y + 1, z)) || BoxelController.isBlockLiquid(world.getBlock(x, y + 1, z - 1)) || BoxelController.isBlockLiquid(world.getBlock(x - 1, y + 1, z - 1));
	}

	public boolean renderGhostLiquid(Block block, int x, int y, int z, int brightness, RenderBlocks renderer, IBlockAccess world)
	{
		Tessellator tessellator = Tessellator.instance;
		Material material = block.getMaterial();
		double height0 = 0.7;
		double height2 = 0.7;
		double height3 = 0.7;
		double height4 = 0.7;

		if(this.doesPointIntersectWithLiquid(x, y, z, world))
		{
			height0 = renderer.getLiquidHeight(x, y, z, material);
		}

		if(this.doesPointIntersectWithLiquid(x, y, z + 1, world))
		{
			height2 = renderer.getLiquidHeight(x, y, z + 1, material);
		}

		if(this.doesPointIntersectWithLiquid(x + 1, y, z + 1, world))
		{
			height3 = renderer.getLiquidHeight(x + 1, y, z + 1, material);
		}

		if(this.doesPointIntersectWithLiquid(x + 1, y, z, world))
		{
			height4 = renderer.getLiquidHeight(x + 1, y, z, material);
		}

		IIcon icon = renderer.getBlockIconFromSide(block, 1);
		double minU = icon.getInterpolatedU(0.0);
		double minV = icon.getInterpolatedV(0.0);
		double maxU = icon.getInterpolatedU(16.0);
		double maxV = icon.getInterpolatedV(16.0);
		tessellator.setBrightness(brightness);
		tessellator.setColorOpaque_I(block.colorMultiplier(world, x, y, z));
		tessellator.addVertexWithUV((double) (x + 0), (double) y + (height0 -= 0.0010000000474974513), (double) (z + 0), minU, minV);
		tessellator.addVertexWithUV((double) (x + 0), (double) y + (height2 -= 0.0010000000474974513), (double) (z + 1), minU, maxV);
		tessellator.addVertexWithUV((double) (x + 1), (double) y + (height3 -= 0.0010000000474974513), (double) (z + 1), maxU, maxV);
		tessellator.addVertexWithUV((double) (x + 1), (double) y + (height4 -= 0.0010000000474974513), (double) (z + 0), maxU, minV);

		return true;
	}

	public static boolean shouldHookRenderer(Block block)
	{
		return BoxelController.isNoCubesEnabled && (BoxelController.isBlockSoft(block) || BoxelController.isBlockLiquid(block));
	}

	public boolean directRenderHook(Block block, int x, int y, int z, RenderBlocks renderer)
	{
		block.setBlockBoundsBasedOnState(renderer.blockAccess, x, y, z);
		renderer.setRenderBoundsFromBlock(block);
		IBlockAccess world = renderer.blockAccess;

		if(BoxelController.isBlockLiquid(block))
		{
			return this.renderLiquidBlock(block, x, y, z, renderer, world);
		}

		return this.renderSoftBlock(block, x, y, z, renderer, world);
	}

	public static void inject(Block block, World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity)
	{
		float stepHeight = 0.5f;
		float f = SmoothBlockRenderer2.getSmoothBlockHeightForCollision((IBlockAccess) world, block, x, y, z);
		float f2 = SmoothBlockRenderer2.getSmoothBlockHeightForCollision((IBlockAccess) world, block, x, y, z + 1);
		float f3 = SmoothBlockRenderer2.getSmoothBlockHeightForCollision((IBlockAccess) world, block, x + 1, y, z + 1);
		float f4 = SmoothBlockRenderer2.getSmoothBlockHeightForCollision((IBlockAccess) world, block, x + 1, y, z);
		SoftBlockRenderer.addBBoundsToList(x, y, z, 0.0f, 0.0f, 0.0f, stepHeight, f, stepHeight, aabb, list);
		SoftBlockRenderer.addBBoundsToList(x, y, z, 0.0f, 0.0f, stepHeight, stepHeight, f2, 1.0f, aabb, list);
		SoftBlockRenderer.addBBoundsToList(x, y, z, stepHeight, 0.0f, stepHeight, 1.0f, f3, 1.0f, aabb, list);
		SoftBlockRenderer.addBBoundsToList(x, y, z, stepHeight, 0.0f, 0.0f, 1.0f, f4, stepHeight, aabb, list);
	}

	public static void addBBoundsToList(int x, int y, int z, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, AxisAlignedBB aabb, List list)
	{
		AxisAlignedBB aabb2 = AxisAlignedBB.getBoundingBox((double) ((float) x + minX), (double) ((float) y + minY), (double) ((float) z + minZ), (double) ((float) x + maxX), (double) ((float) y + maxY), (double) ((float) z + maxZ));

		if(aabb2 != null && aabb.intersectsWith(aabb2))
		{
			list.add(aabb2);
		}
	}
}

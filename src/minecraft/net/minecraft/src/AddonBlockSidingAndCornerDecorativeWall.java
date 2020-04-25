package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

public class AddonBlockSidingAndCornerDecorativeWall extends AddonBlockSidingAndCornerAndDecorative
{
	static ArrayList<AddonBlockSidingAndCornerDecorativeWall> wallBlocks = new ArrayList<AddonBlockSidingAndCornerDecorativeWall>();
	public AddonBlockSidingAndCornerDecorativeWall(int var1, Material var2, String var3, float var4, float var5, StepSound var6, String var7, String originalName)
	{
		super(var1, var2, var3, var4, var5, var6, var7);
		AddonManager.Name(getUnlocalizedName() + ".fence" + ".name", originalName + " Wall");
		wallBlocks.add(this);
	}

	/**
	 * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
	 * x, y, z, startVec, endVec
	 */
	public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
	{
		int var7 = var1.getBlockId(var2, var3, var4);
		return this.IsBlockBench(var1, var2, var3, var4) && this.DoesBenchHaveLeg(var1, var2, var3, var4) ? this.CollisionRayTraceBenchWithLeg(var1, var2, var3, var4, var5, var6) : ((var7 != this.blockID || var1.getBlockMetadata(var2, var3, var4) != 14) && var7 != Block.fenceGate.blockID ? super.collisionRayTrace(var1, var2, var3, var4, var5, var6) : this.CollisionRayTraceFence(var1, var2, var3, var4, var5, var6));
	}

	public MovingObjectPosition CollisionRayTraceFence(World world, int x, int y, int z, Vec3 var5, Vec3 var6)
	{
		FCUtilsRayTraceVsComplexBlock var7 = new FCUtilsRayTraceVsComplexBlock(world, x, y, z, var5, var6);
		boolean east = this.CanConnectToBlockToFacing(world, x, y, z, 4);
		boolean west = this.CanConnectToBlockToFacing(world, x, y, z, 5);
		boolean north = this.CanConnectToBlockToFacing(world, x, y, z, 2);
		boolean south = this.CanConnectToBlockToFacing(world, x, y, z, 3);

		boolean EW = false;

		if (east || west)
		{
			EW = true;
		}

		boolean NS = false;

		if (north || south)
		{
			NS = true;
		}

		if (EW == NS || east != west || north != south || !world.isAirBlock(x, y+1, z)) {
			var7.AddBoxWithLocalCoordsToIntersectionList(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
		}

		float var14 = 0.3125F;
		float var15 = 0.6875F;
		float var16 = 0F;
		float var17 = 0.8125F;
		float var18 = east ? 0.0F : var14;
		float var19 = west ? 1.0F : var15;
		float var20 = north ? 0.0F : var14;
		float var21 = south ? 1.0F : var15;

		if (EW)
		{
			var7.AddBoxWithLocalCoordsToIntersectionList((double)var18, (double)var16, (double)var14, (double)var19, (double)var17, (double)var15);
		}

		if (NS)
		{
			var7.AddBoxWithLocalCoordsToIntersectionList((double)var14, (double)var16, (double)var20, (double)var15, (double)var17, (double)var21);
		}

		return var7.GetFirstIntersection();
	}

	@Override
	public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
	{
		int var5 = var1.getBlockMetadata(var2, var3, var4);
		return var5 == 12 ? this.GetBlockBoundsFromPoolForBench(var1, var2, var3, var4) : (var5 == 14 ? this.GetBlockBoundsFromPoolForFence(var1, var2, var3, var4) : super.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4));
	}

	@Override
	public AxisAlignedBB GetBlockBoundsFromPoolForFence(IBlockAccess var1, int var2, int var3, int var4)
	{
		boolean var5 = this.CanConnectToBlockToFacing(var1, var2, var3, var4, 2);
		boolean var6 = this.CanConnectToBlockToFacing(var1, var2, var3, var4, 3);
		boolean var7 = this.CanConnectToBlockToFacing(var1, var2, var3, var4, 4);
		boolean var8 = this.CanConnectToBlockToFacing(var1, var2, var3, var4, 5);
		float var9 = 0.25F;
		float var10 = 0.75F;
		float var11 = 0.25F;
		float var12 = 0.75F;
		float var13 = 1.0F;

		if (var5)
		{
			var11 = 0.0F;
		}

		if (var6)
		{
			var12 = 1.0F;
		}

		if (var7)
		{
			var9 = 0.0F;
		}

		if (var8)
		{
			var10 = 1.0F;
		}

		if (var5 && var6 && !var7 && !var8)
		{
			var13 = 0.8125F;
			var9 = 0.3125F;
			var10 = 0.6875F;
		}
		else if (!var5 && !var6 && var7 && var8)
		{
			var13 = 0.8125F;
			var11 = 0.3125F;
			var12 = 0.6875F;
		}

		return AxisAlignedBB.getAABBPool().getAABB((double)var9, 0.0D, (double)var11, (double)var10, (double)var13, (double)var12);
	}

	/**
	 * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
	 * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
	 */
	@Override
	public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7)
	{
		int var8 = var1.getBlockMetadata(var2, var3, var4);

		if (var8 == 14)
		{
			this.AddCollisionBoxesToListForFence(var1, var2, var3, var4, var5, var6, var7);
		}
		else
		{
			super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
		}
	}

	@Override
	public void AddCollisionBoxesToListForFence(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7)
	{
		boolean var8 = this.CanConnectToBlockToFacing(var1, var2, var3, var4, 4);
		boolean var9 = this.CanConnectToBlockToFacing(var1, var2, var3, var4, 5);
		boolean var10 = this.CanConnectToBlockToFacing(var1, var2, var3, var4, 2);
		boolean var11 = this.CanConnectToBlockToFacing(var1, var2, var3, var4, 3);
		float var12 = 0.25F;
		float var13 = 0.75F;
		float var14 = 0.25F;
		float var15 = 0.75F;

		if (var10)
		{
			var14 = 0.0F;
		}

		if (var11)
		{
			var15 = 1.0F;
		}

		if (var10 || var11)
		{
			AxisAlignedBB.getAABBPool().getAABB((double)var12, 0.0D, (double)var14, (double)var13, 1.5D, (double)var15).offset((double)var2, (double)var3, (double)var4).AddToListIfIntersects(var5, var6);
		}

		if (var8)
		{
			var12 = 0.0F;
		}

		if (var9)
		{
			var13 = 1.0F;
		}

		if (var8 || var9 || !var10 && !var11)
		{
			AxisAlignedBB.getAABBPool().getAABB((double)var12, 0.0D, 0.375D, (double)var13, 1.5D, 0.625D).offset((double)var2, (double)var3, (double)var4).AddToListIfIntersects(var5, var6);
		}
	}

	protected boolean CanConnectToBlockToFacing(IBlockAccess blockAccess, int x, int y, int z, int facing)
	{
		FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z, facing);
		return AddonUtilsBlock.canWallConnect(blockAccess, blockPos.i, blockPos.j, blockPos.k, facing, this);
	}

	protected boolean CanConnectToBlockToFacing(World world, int x, int y, int z, int facing)
	{
		FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z, facing);
		return AddonUtilsBlock.canWallConnect(world, blockPos.i, blockPos.j, blockPos.k, facing, this);
	}

	protected boolean wallHasPost(IBlockAccess blockAccess, int x, int y, int z, boolean checkAbove, boolean checkBelow) {
		int idAbove = blockAccess.getBlockId(x, y + 1, z);
		int metaAbove = blockAccess.getBlockMetadata(x, y + 1, z);
		int idBelow = blockAccess.getBlockId(x, y - 1, z);
		int metaBelow = blockAccess.getBlockMetadata(x, y - 1, z);

		//Get whether the wall should connect to each facing
		boolean east = this.CanConnectToBlockToFacing(blockAccess, x, y, z, 4);
		boolean west = this.CanConnectToBlockToFacing(blockAccess, x, y, z, 5);
		boolean north = this.CanConnectToBlockToFacing(blockAccess, x, y, z, 2);
		boolean south = this.CanConnectToBlockToFacing(blockAccess, x, y, z, 3);
		boolean NS = north && south && !east && !west;
		boolean EW = !north && !south && east && west;

		//If the wall does not have exactly 2 connections it must have a post
		if (!NS && !EW)
			return true;

		boolean wallAbove = false;
		boolean wallBelow = false;
		
		//Recursively checks wall above
		if (AddonUtilsBlock.isWall(idAbove, metaAbove) && checkAbove) {
			return wallHasPost(blockAccess, x, y + 1, z, true, false);
		}
		
		//Checks wall below
		if (AddonUtilsBlock.isWall(idBelow, metaBelow) && checkBelow) {
			//return wallHasPost(blockAccess, x, y + 1, z, false, true);
		}
		
		if (wallAbove || wallBelow)
			return true;

		boolean airAbove = blockAccess.isAirBlock(x, y + 1, z) || FCUtilsWorld.IsGroundCoverOnBlock(blockAccess, x, y, z);
		Block blockAbove = Block.blocksList[idAbove];
		boolean solidSurface = blockAbove == null ? false : blockAbove.HasLargeCenterHardPointToFacing(blockAccess, x, y, z, 0);
		
		//No post if air above
		if (airAbove)
			return false;

		//No post if solid surface and BOTH connections are full height walls
		if (solidSurface) {
			if (NS) {
				boolean northFullWall = shouldRenderFullHeightWallToFacing(blockAccess, x, y, z, 2);
				boolean southFullWall = shouldRenderFullHeightWallToFacing(blockAccess, x, y, z, 3);

				return !(northFullWall && southFullWall);
			}
			else {
				boolean eastFullWall = shouldRenderFullHeightWallToFacing(blockAccess, x, y, z, 4);
				boolean westFullWall = shouldRenderFullHeightWallToFacing(blockAccess, x, y, z, 5);

				return !(eastFullWall && westFullWall);
			}
		}

		return true;
	}

	protected boolean shouldRenderFullHeightWallToFacing(IBlockAccess blockAccess, int x, int y, int z, int facing) {
		boolean connect = this.CanConnectToBlockToFacing(blockAccess, x, y, z, facing);

		//Sanity check for non-connecting facing
		if (!connect)
			return false;

		int idAbove = blockAccess.getBlockId(x, y + 1, z);
		int metaAbove = blockAccess.getBlockMetadata(x, y + 1, z);
		boolean airAbove = blockAccess.isAirBlock(x, y + 1, z) || FCUtilsWorld.IsGroundCoverOnBlock(blockAccess, x, y, z);
		Block blockAbove = Block.blocksList[idAbove];
		boolean solidSurface = blockAbove == null ? false : blockAbove.HasLargeCenterHardPointToFacing(blockAccess, x, y, z, 0);

		//Gets coordinates for block in facing direction
		FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z);
		blockPos.AddFacingAsOffset(facing);

		int idAboveOffset = blockAccess.getBlockId(blockPos.i, blockPos.j + 1, blockPos.k);
		int metaAboveOffset = blockAccess.getBlockMetadata(blockPos.i, blockPos.j + 1, blockPos.k);
		Block blockAboveOffset = Block.blocksList[idAboveOffset];
		boolean solidSurfaceOffset = blockAboveOffset == null ? false : blockAboveOffset.HasLargeCenterHardPointToFacing(blockAccess, blockPos.i, blockPos.j, blockPos.k, 0);

		//Both parts of connection need to satisfy requirements for a full height wall
		return (AddonUtilsBlock.isWall(idAboveOffset, metaAboveOffset) || solidSurfaceOffset) && (AddonUtilsBlock.isWall(idAbove, metaAbove) || solidSurface);
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		int i = x, j = y, k = z;
		int id = world.getBlockId(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		
		while (AddonUtilsBlock.isWall(id, meta)) {
			world.markBlockForRenderUpdate(i, j, k);
			
			j++;
			id = world.getBlockId(i, j, k);
			meta = world.getBlockMetadata(i, j, k);
		}
		
		j = y;
		id = world.getBlockId(x, y, z);
		meta = world.getBlockMetadata(x, y, z);
		
		while (AddonUtilsBlock.isWall(id, meta)) {
			world.markBlockForRenderUpdate(i, j, k);
			
			j--;
			id = world.getBlockId(i, j, k);
			meta = world.getBlockMetadata(i, j, k);
		}
	}

	//CLIENT ONLY
	@Override public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
	{
		IBlockAccess var5 = var1.blockAccess;
		int var6 = var5.getBlockMetadata(var2, var3, var4);
		return var6 == 12 ? RenderBench(var1, var5, var2, var3, var4, this) : (var6 == 14 ? this.renderBlockFence(var1, var2, var3, var4) : super.RenderBlock(var1, var2, var3, var4));
	}

	public boolean renderBlockFence(RenderBlocks render, int x, int y, int z) {
		boolean post = wallHasPost(render.blockAccess, x, y, z, true, true);

		boolean east = this.CanConnectToBlockToFacing(render.blockAccess, x, y, z, 4);
		boolean west = this.CanConnectToBlockToFacing(render.blockAccess, x, y, z, 5);
		boolean north = this.CanConnectToBlockToFacing(render.blockAccess, x, y, z, 2);
		boolean south = this.CanConnectToBlockToFacing(render.blockAccess, x, y, z, 3);

		boolean eastFullWall = shouldRenderFullHeightWallToFacing(render.blockAccess, x, y, z, 4);
		boolean westFullWall = shouldRenderFullHeightWallToFacing(render.blockAccess, x, y, z, 5);
		boolean northFullWall = shouldRenderFullHeightWallToFacing(render.blockAccess, x, y, z, 2);
		boolean southFullWall = shouldRenderFullHeightWallToFacing(render.blockAccess, x, y, z, 3);

		if (post) {
			render.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
			render.renderStandardBlock(this, x, y, z);
		}
		
		if (east)
		{
			double height = 0.8125;
			if (eastFullWall)
				height = 1.0;

			render.setRenderBounds(0.0D, 0.0D, 0.3125D, 0.5D, height, 0.6875D);
			render.renderStandardBlock(this, x, y, z);
		}

		if (west)
		{
			double height = 0.8125;
			if (westFullWall)
				height = 1.0;

			render.setRenderBounds(0.5D, 0.0D, 0.3125D, 1.0D, height, 0.6875D);
			render.renderStandardBlock(this, x, y, z);
		}

		if (north)
		{
			double height = 0.8125;
			if (northFullWall)
				height = 1.0;

			render.setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, height, 0.5D);
			render.renderStandardBlock(this, x, y, z);
		}

		if (south)
		{
			double height = 0.8125;
			if (southFullWall)
				height = 1.0;

			render.setRenderBounds(0.3125D, 0.0D, 0.5D, 0.6875D, height, 1.0D);
			render.renderStandardBlock(this, x, y, z);
		}

		return true;
	}

	public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
	{
		int var4 = var2;
		Object var5 = this;

		if (var4 == 12)
		{
			RenderBenchInvBlock(var1, (Block)var5, var4);
		}
		else if (var4 == 14)
		{
			RenderFenceInvBlock(var1, (Block)var5, var4);
		}
		else
		{
			var1.setRenderBounds(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D);

			FCClientUtilsRender.RenderInvBlockWithMetadata(var1, this, -0.5F, -0.5F, -0.5F, 0);
		}
	}

	public static void RenderFenceInvBlock(RenderBlocks var0, Block var1, int var2)
	{
		Tessellator var3 = Tessellator.instance;

		for (int var4 = 0; var4 < 2; ++var4)
		{
			float var5 = 0.125F;

			if (var4 == 0)
			{
				var0.setRenderBounds(.25, 0.0D, .25, .75, 1.0D, .75);
			}

			if (var4 == 1)
			{
				var0.setRenderBounds(0.0D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
			}

			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
			var3.startDrawingQuads();
			var3.setNormal(0.0F, -1.0F, 0.0F);
			var0.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(0));
			var3.draw();
			var3.startDrawingQuads();
			var3.setNormal(0.0F, 1.0F, 0.0F);
			var0.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(1));
			var3.draw();
			var3.startDrawingQuads();
			var3.setNormal(0.0F, 0.0F, -1.0F);
			var0.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(2));
			var3.draw();
			var3.startDrawingQuads();
			var3.setNormal(0.0F, 0.0F, 1.0F);
			var0.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(3));
			var3.draw();
			var3.startDrawingQuads();
			var3.setNormal(-1.0F, 0.0F, 0.0F);
			var0.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(4));
			var3.draw();
			var3.startDrawingQuads();
			var3.setNormal(1.0F, 0.0F, 0.0F);
			var0.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(5));
			var3.draw();
			GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		}

		var0.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	}
	//
}
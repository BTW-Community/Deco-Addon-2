package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

public class DecoBlockSidingAndCornerDecorativeWall extends DecoBlockSidingAndCornerAndDecorative
{
	static ArrayList<DecoBlockSidingAndCornerDecorativeWall> wallBlocks = new ArrayList<DecoBlockSidingAndCornerDecorativeWall>();
	public DecoBlockSidingAndCornerDecorativeWall(int var1, Material var2, String var3, float var4, float var5, StepSound var6, String var7, String originalName)
	{
		super(var1, var2, var3, var4, var5, var6, var7);
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
	
	public MovingObjectPosition CollisionRayTraceFence(World world, int x, int y, int z, Vec3 var5, Vec3 var6) {
		boolean post = wallHasPost(world, x, y, z, true, true);

		boolean east = this.CanConnectToBlockToFacing(world, x, y, z, 4);
		boolean west = this.CanConnectToBlockToFacing(world, x, y, z, 5);
		boolean north = this.CanConnectToBlockToFacing(world, x, y, z, 2);
		boolean south = this.CanConnectToBlockToFacing(world, x, y, z, 3);

		boolean eastFullWall = shouldRenderFullHeightWallToFacing(world, x, y, z, 4);
		boolean westFullWall = shouldRenderFullHeightWallToFacing(world, x, y, z, 5);
		boolean northFullWall = shouldRenderFullHeightWallToFacing(world, x, y, z, 2);
		boolean southFullWall = shouldRenderFullHeightWallToFacing(world, x, y, z, 3);

		FCUtilsRayTraceVsComplexBlock raytracer = new FCUtilsRayTraceVsComplexBlock(world, x, y, z, var5, var6);
		
		if (post) {
			raytracer.AddBoxWithLocalCoordsToIntersectionList(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
		}

		if (east)
		{
			double height = 0.8125;
			if (eastFullWall)
				height = 1.0;

			raytracer.AddBoxWithLocalCoordsToIntersectionList(0.0D, 0.0D, 0.3125D, 0.5D, height, 0.6875D);
		}

		if (west)
		{
			double height = 0.8125;
			if (westFullWall)
				height = 1.0;

			raytracer.AddBoxWithLocalCoordsToIntersectionList(0.5D, 0.0D, 0.3125D, 1.0D, height, 0.6875D);
		}

		if (north)
		{
			double height = 0.8125;
			if (northFullWall)
				height = 1.0;

			raytracer.AddBoxWithLocalCoordsToIntersectionList(0.3125D, 0.0D, 0.0D, 0.6875D, height, 0.5D);
		}

		if (south)
		{
			double height = 0.8125;
			if (southFullWall)
				height = 1.0;

			raytracer.AddBoxWithLocalCoordsToIntersectionList(0.3125D, 0.0D, 0.5D, 0.6875D, height, 1.0D);
		}
		
		return raytracer.GetFirstIntersection();
	}

	@Override
	public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
	{
		int var5 = var1.getBlockMetadata(var2, var3, var4);
		return var5 == 12 ? this.GetBlockBoundsFromPoolForBench(var1, var2, var3, var4) : (var5 == 14 ? this.GetBlockBoundsFromPoolForFence(var1, var2, var3, var4) : super.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4));
	}

	@Override
	public AxisAlignedBB GetBlockBoundsFromPoolForFence(IBlockAccess blockAccess, int x, int y, int z) {
		boolean post = wallHasPost(blockAccess, x, y, z, true, true);

		boolean east = this.CanConnectToBlockToFacing(blockAccess, x, y, z, 4);
		boolean west = this.CanConnectToBlockToFacing(blockAccess, x, y, z, 5);
		boolean north = this.CanConnectToBlockToFacing(blockAccess, x, y, z, 2);
		boolean south = this.CanConnectToBlockToFacing(blockAccess, x, y, z, 3);

		boolean eastFullWall = shouldRenderFullHeightWallToFacing(blockAccess, x, y, z, 4);
		boolean westFullWall = shouldRenderFullHeightWallToFacing(blockAccess, x, y, z, 5);
		boolean northFullWall = shouldRenderFullHeightWallToFacing(blockAccess, x, y, z, 2);
		boolean southFullWall = shouldRenderFullHeightWallToFacing(blockAccess, x, y, z, 3);

		double minX = .3125;
		double minZ = .3125;
		double maxX = .6875;
		double maxZ = .6875;
		
		double height = 0.8125;
		if (eastFullWall || westFullWall || northFullWall || southFullWall || post)
			height = 1.0;
		
		if (post) {
			minX = .25;
			minZ = .25;
			maxX = .75;
			maxZ = .75;
		}
		
		if (east)
			minX = 0;
		if (west)
			maxX = 1;
		if (north)
			minZ = 0;
		if (south)
			maxZ = 1;

		return AxisAlignedBB.getAABBPool().getAABB(minX, 0.0D, minZ, maxX, height, maxZ);
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

	public void AddCollisionBoxesToListForFence(World world, int x, int y, int z, AxisAlignedBB aabb, List collisionList, Entity entity) {
		boolean post = wallHasPost(world, x, y, z, true, true);

		boolean east = this.CanConnectToBlockToFacing(world, x, y, z, 4);
		boolean west = this.CanConnectToBlockToFacing(world, x, y, z, 5);
		boolean north = this.CanConnectToBlockToFacing(world, x, y, z, 2);
		boolean south = this.CanConnectToBlockToFacing(world, x, y, z, 3);
		
		if (post) {
			AxisAlignedBB.getAABBPool().getAABB(0.25D, 0.0D, 0.25D, 0.75D, 1.5D, 0.75D).offset((double) x, (double) y, (double) z).AddToListIfIntersects(aabb, collisionList);
		}
		
		double height = 1.5;
		
		if (east)
		{
			AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.3125D, 0.5D, height, 0.6875D).offset((double) x, (double) y, (double) z).AddToListIfIntersects(aabb, collisionList);
		}

		if (west)
		{
			AxisAlignedBB.getAABBPool().getAABB(0.5D, 0.0D, 0.3125D, 1.0D, height, 0.6875D).offset((double) x, (double) y, (double) z).AddToListIfIntersects(aabb, collisionList);
		}

		if (north)
		{
			AxisAlignedBB.getAABBPool().getAABB(0.3125D, 0.0D, 0.0D, 0.6875D, height, 0.5D).offset((double) x, (double) y, (double) z).AddToListIfIntersects(aabb, collisionList);
		}

		if (south)
		{
			AxisAlignedBB.getAABBPool().getAABB(0.3125D, 0.0D, 0.5D, 0.6875D, height, 1.0D).offset((double) x, (double) y, (double) z).AddToListIfIntersects(aabb, collisionList);
		}
	}

	protected boolean CanConnectToBlockToFacing(IBlockAccess blockAccess, int x, int y, int z, int facing)
	{
		FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z, facing);
		return DecoUtilsBlock.canWallConnect(blockAccess, blockPos.i, blockPos.j, blockPos.k, facing, this);
	}

	protected boolean CanConnectToBlockToFacing(World world, int x, int y, int z, int facing)
	{
		FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z, facing);
		return DecoUtilsBlock.canWallConnect(world, blockPos.i, blockPos.j, blockPos.k, facing, this);
	}

	protected boolean wallHasPost(IBlockAccess blockAccess, int x, int y, int z, boolean checkAbove, boolean checkBelow) {
		int idAbove = blockAccess.getBlockId(x, y + 1, z);
		int metaAbove = blockAccess.getBlockMetadata(x, y + 1, z);
		int idBelow = blockAccess.getBlockId(x, y - 1, z);
		int metaBelow = blockAccess.getBlockMetadata(x, y - 1, z);

		//Get whether the wall should connect to each facing
		boolean north = this.CanConnectToBlockToFacing(blockAccess, x, y, z, 2);
		boolean south = this.CanConnectToBlockToFacing(blockAccess, x, y, z, 3);
		boolean east = this.CanConnectToBlockToFacing(blockAccess, x, y, z, 4);
		boolean west = this.CanConnectToBlockToFacing(blockAccess, x, y, z, 5);
		boolean NS = north && south && !east && !west;
		boolean EW = !north && !south && east && west;

		//If the wall does not have exactly 2 connections it must have a post
		if (!NS && !EW)
			return true;

		if (DecoUtilsBlock.isWall(idAbove, metaAbove) || DecoUtilsBlock.isWall(idBelow, metaBelow)) {
			boolean wallAbove = false;
			boolean wallBelow = false;

			//Recursively checks wall above
			if (DecoUtilsBlock.isWall(idAbove, metaAbove) && checkAbove) {
				return wallHasPost(blockAccess, x, y + 1, z, true, false);
			}

			//Checks wall below
			if (DecoUtilsBlock.isWall(idBelow, metaBelow) && checkBelow) {
				//return wallHasPost(blockAccess, x, y - 1, z, false, true);
			}

			if (wallAbove || wallBelow)
				return true;
		}

		boolean airAbove = blockAccess.isAirBlock(x, y + 1, z) || FCUtilsWorld.IsGroundCoverOnBlock(blockAccess, x, y, z);
		Block blockAbove = Block.blocksList[idAbove];
		boolean solidSurface = blockAbove == null ? false : blockAbove.HasLargeCenterHardPointToFacing(blockAccess, x, y + 1, z, 0);
		boolean paneAbove = blockAbove instanceof BlockPane;

		boolean paneToSide = false;
		
		for (int i = 0; i < 4; i++) {
			FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z);
			blockPos.AddFacingAsOffset(i + 2);
			int idOffset = blockAccess.getBlockId(blockPos.i, blockPos.j, blockPos.k);
			if (Block.blocksList[idOffset] instanceof BlockPane)
				paneToSide = true;
		}
		
		//No post if air above
		if (airAbove && !paneToSide)
			return false;

		//No post if solid surface and BOTH connections are full height walls
		if (solidSurface || paneAbove) {
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
		
		if (DecoUtilsBlock.blockIsWallConnectionAboveException(blockAccess, x, y + 1, z, blockAbove))
			return DecoUtilsBlock.getWallConnectionAboveException(blockAccess, x, y + 1, z, blockAbove);
		
		return true;
	}

	protected boolean shouldRenderFullHeightWallToFacing(IBlockAccess blockAccess, int x, int y, int z, int facing) {
		boolean connect = this.CanConnectToBlockToFacing(blockAccess, x, y, z, facing);

		//Sanity check for non-connecting facing
		if (!connect)
			return false;

		int idAbove = blockAccess.getBlockId(x, y + 1, z);
		int metaAbove = blockAccess.getBlockMetadata(x, y + 1, z);
		Block blockAbove = Block.blocksList[idAbove];
		boolean solidSurface = blockAbove == null ? false : blockAbove.HasLargeCenterHardPointToFacing(blockAccess, x, y + 1, z, 0);
		boolean paneAbove = blockAbove instanceof BlockPane;
		boolean canPaneAboveConnectToFacing = false;

		if (paneAbove) {
			FCUtilsBlockPos blockPosPane = new FCUtilsBlockPos(x, y + 1, z, facing);
			canPaneAboveConnectToFacing = DecoUtilsBlock.canPaneConnect(blockAccess, x, y, z, facing, blockAbove);
		}
		
		//Gets coordinates for block in facing direction
		FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z);
		blockPos.AddFacingAsOffset(facing);

		Block sideBlock = Block.blocksList[blockAccess.getBlockId(blockPos.i, blockPos.j, blockPos.k)];
		boolean solidSide = sideBlock.HasLargeCenterHardPointToFacing(blockAccess, blockPos.i, blockPos.j, blockPos.k, Facing.oppositeSide[facing]);

		int idOffset = blockAccess.getBlockId(blockPos.i, blockPos.j, blockPos.k);
		boolean paneToSide = Block.blocksList[idOffset] instanceof BlockPane;
		
		int idAboveOffset = blockAccess.getBlockId(blockPos.i, blockPos.j + 1, blockPos.k);
		int metaAboveOffset = blockAccess.getBlockMetadata(blockPos.i, blockPos.j + 1, blockPos.k);
		Block blockAboveOffset = Block.blocksList[idAboveOffset];
		boolean solidSurfaceOffset = blockAboveOffset == null ? false : blockAboveOffset.HasLargeCenterHardPointToFacing(blockAccess, blockPos.i, blockPos.j + 1, blockPos.k, 0);
		boolean paneAboveOffset = blockAboveOffset instanceof BlockPane;
		
		if (DecoUtilsBlock.isWall(idAbove, metaAbove) && !solidSurfaceOffset) {
			return CanConnectToBlockToFacing(blockAccess, x, y + 1, z, facing);
		}

		//Both parts of connection need to satisfy requirements for a full height wall, or the wall needs to be connecting to a pane
		return (DecoUtilsBlock.isWall(idAboveOffset, metaAboveOffset) || DecoUtilsBlock.isBenchOrTable(idAboveOffset, metaAboveOffset) || solidSurfaceOffset || (solidSide && (!paneAbove || canPaneAboveConnectToFacing)) || paneAboveOffset) //Checks offsets
				&& (DecoUtilsBlock.isWall(idAbove, metaAbove) || DecoUtilsBlock.isBenchOrTable(idAbove, metaAbove) || solidSurface || (paneAbove && canPaneAboveConnectToFacing)) //Checks block above
				|| paneToSide;
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		int i = x, j = y, k = z;
		int id = world.getBlockId(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);

		while (DecoUtilsBlock.isWall(id, meta)) {
			world.markBlockForRenderUpdate(i, j, k);

			j++;
			id = world.getBlockId(i, j, k);
			meta = world.getBlockMetadata(i, j, k);
		}

		j = y;
		id = world.getBlockId(x, y, z);
		meta = world.getBlockMetadata(x, y, z);

		while (DecoUtilsBlock.isWall(id, meta)) {
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
    
    public static boolean RenderBench(RenderBlocks renderBlocks, IBlockAccess blockAccess, int x, int y, int z, Block block)
    {
    	DecoBlockSidingAndCornerDecorativeWall blockSiding = (DecoBlockSidingAndCornerDecorativeWall)block;
        renderBlocks.setRenderBounds(0.0D, 0.375D, 0.0D, 1.0D, 0.5D, 1.0D);
        renderBlocks.renderStandardBlock(block, x, y, z);

        if (blockSiding.DoesBenchHaveLeg(blockAccess, x, y, z))
        {
            renderBlocks.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 0.375D, 0.75D);
            renderBlocks.renderStandardBlock(block, x, y, z);
            
            if (blockSiding.DoesBenchHaveLeg(blockAccess, x - 1, y, z) && Block.blocksList[blockAccess.getBlockId(x - 1, y, z)] instanceof DecoBlockSidingAndCornerDecorativeWall && blockAccess.getBlockMetadata(x - 1, y, z) == 12) {
                renderBlocks.setRenderBounds(0.25D, 0.0D, 0.6875D, 0D, 0.375D, 0.3125D);
                renderBlocks.renderStandardBlock(block, x, y, z);
            }
            if (blockSiding.DoesBenchHaveLeg(blockAccess, x, y, z - 1) && Block.blocksList[blockAccess.getBlockId(x, y, z - 1)] instanceof DecoBlockSidingAndCornerDecorativeWall && blockAccess.getBlockMetadata(x, y, z - 1) == 12) {
                renderBlocks.setRenderBounds(0.6875D, 0.0D, 0.25D, 0.3125D, 0.375D, 0D);
                renderBlocks.renderStandardBlock(block, x, y, z);
            }
            if (blockSiding.DoesBenchHaveLeg(blockAccess, x + 1, y, z) && Block.blocksList[blockAccess.getBlockId(x + 1, y, z)] instanceof DecoBlockSidingAndCornerDecorativeWall && blockAccess.getBlockMetadata(x + 1, y, z) == 12) {
                renderBlocks.setRenderBounds(1D, 0.0D, 0.6875D, 0.75D, 0.375D, 0.3125D);
                renderBlocks.renderStandardBlock(block, x, y, z);
            }
            if (blockSiding.DoesBenchHaveLeg(blockAccess, x, y, z + 1) && Block.blocksList[blockAccess.getBlockId(x, y, z + 1)] instanceof DecoBlockSidingAndCornerDecorativeWall && blockAccess.getBlockMetadata(x, y, z + 1) == 12) {
                renderBlocks.setRenderBounds(0.6875D, 0.0D, 1D, 0.3125D, 0.375D, 0.75D);
                renderBlocks.renderStandardBlock(block, x, y, z);
            }
        }

        return true;
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
			render.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 1.0001D, 0.75D);
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
            super.RenderBlockAsItem(var1, var2, var3);
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
package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class AddonBlockWall extends FCBlockWall {
	public AddonBlockWall(int var1, Block var2) {
		super(var1, var2);
	}

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return this.blockID;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        if (!par1World.isRemote)
        {
            int var8 = 1;

            for (int var9 = 0; var9 < var8; ++var9)
            {
                if (par1World.rand.nextFloat() <= par6)
                {
                    int var10 = this.idDropped(par5, par1World.rand, par7);

                    if (var10 > 0)
                    {
                        this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(var10, 1, this.damageDropped(par5)));
                    }
                }
            }
        }
    }
    
    @Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 var5, Vec3 var6) {
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

	@Override
	public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
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
	
	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List collisionList, Entity entity) {
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
	}protected boolean wallHasPost(IBlockAccess blockAccess, int x, int y, int z, boolean checkAbove, boolean checkBelow) {
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

		if (AddonUtilsBlock.isWall(idAbove, metaAbove) || AddonUtilsBlock.isWall(idBelow, metaBelow)) {
			boolean wallAbove = false;
			boolean wallBelow = false;

			//Recursively checks wall above
			if (AddonUtilsBlock.isWall(idAbove, metaAbove) && checkAbove) {
				return wallHasPost(blockAccess, x, y + 1, z, true, false);
			}

			//Checks wall below
			if (AddonUtilsBlock.isWall(idBelow, metaBelow) && checkBelow) {
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
		
		if (AddonUtilsBlock.blockIsWallConnectionAboveException(blockAccess, x, y + 1, z, blockAbove))
			return AddonUtilsBlock.getWallConnectionAboveException(blockAccess, x, y + 1, z, blockAbove);
		
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
			canPaneAboveConnectToFacing = AddonUtilsBlock.canPaneConnect(blockAccess, x, y, z, facing, blockAbove);
		}
		
		if (AddonUtilsBlock.isWall(idAbove, metaAbove)) {
			return CanConnectToBlockToFacing(blockAccess, x, y + 1, z, facing);
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

		//Both parts of connection need to satisfy requirements for a full height wall
		return (AddonUtilsBlock.isWall(idAboveOffset, metaAboveOffset) || solidSurfaceOffset || (solidSide && (!paneAbove || canPaneAboveConnectToFacing)) || paneAboveOffset) && (AddonUtilsBlock.isWall(idAbove, metaAbove) || solidSurface || (paneAbove && canPaneAboveConnectToFacing)) || paneToSide;
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
	public boolean RenderBlock(RenderBlocks render, int x, int y, int z) {
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
}
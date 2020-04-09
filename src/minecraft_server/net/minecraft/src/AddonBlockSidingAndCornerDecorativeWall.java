package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class AddonBlockSidingAndCornerDecorativeWall extends AddonBlockSidingAndCornerAndDecorative
{
	static ArrayList<AddonBlockSidingAndCornerDecorativeWall> wallBlocks = new ArrayList<AddonBlockSidingAndCornerDecorativeWall>();
	public AddonBlockSidingAndCornerDecorativeWall(int var1, Material var2, String var3, float var4, float var5, StepSound var6, String var7, String originalName)
	{
		super(var1, var2, var3, var4, var5, var6, var7);
		setCreativeTab(CreativeTabs.tabDecorations);
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
}
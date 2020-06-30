package net.minecraft.src;

public class FCBlockDoor extends BlockDoor
{
    private boolean m_bSuppressBottomDrop = false;

    protected FCBlockDoor(int var1, Material var2)
    {
        super(var1, var2);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (this.blockMaterial == Material.iron)
        {
            return true;
        }
        else
        {
            int var10 = this.getFullMetadata(var1, var2, var3, var4);
            int var11 = var10 & 7;
            var11 ^= 4;

            if ((var10 & 8) == 0)
            {
                var1.SetBlockMetadataWithNotify(var2, var3, var4, var11, 3);
                var1.notifyBlockChange(var2, var3 + 1, var4, this.blockID);
                var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
            }
            else
            {
                var1.SetBlockMetadataWithNotify(var2, var3 - 1, var4, var11, 3);
                var1.notifyBlockChange(var2, var3, var4, this.blockID);
                var1.markBlockRangeForRenderUpdate(var2, var3 - 1, var4, var2, var3, var4);
            }

            var1.playAuxSFXAtEntity(var5, 1003, var2, var3, var4, 0);
            return true;
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if ((var6 & 8) == 0)
        {
            boolean var7 = false;
            int var8 = var1.getBlockId(var2, var3 + 1, var4);

            if (var8 != this.blockID)
            {
                var1.setBlockToAir(var2, var3, var4);
                var7 = true;
            }
            else if (!var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4))
            {
                var1.setBlockToAir(var2, var3, var4);
                var7 = true;

                if (var1.getBlockId(var2, var3 + 1, var4) == this.blockID)
                {
                    var1.setBlockToAir(var2, var3 + 1, var4);
                }
            }

            if (var7)
            {
                if (!this.m_bSuppressBottomDrop && !var1.isRemote)
                {
                    this.dropBlockAsItem(var1, var2, var3, var4, var6, 0);
                }

                this.m_bSuppressBottomDrop = false;
            }
            else
            {
                boolean var9 = var1.isBlockIndirectlyGettingPowered(var2, var3, var4) || var1.isBlockIndirectlyGettingPowered(var2, var3 + 1, var4);

                if ((var9 || var5 > 0 && Block.blocksList[var5].canProvidePower()) && var5 != this.blockID)
                {
                    this.onPoweredBlockChange(var1, var2, var3, var4, var9);
                }
            }
        }
        else
        {
            if (var1.getBlockId(var2, var3 - 1, var4) != this.blockID)
            {
                var1.setBlockToAir(var2, var3, var4);
            }

            if (var5 > 0 && var5 != this.blockID)
            {
                this.onNeighborBlockChange(var1, var2, var3 - 1, var4, var5);
            }
        }
    }

    public void OnDestroyedByFire(World var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        if ((var1.getBlockMetadata(var2, var3, var4) & 8) != 0)
        {
            this.m_bSuppressBottomDrop = true;
        }

        super.OnDestroyedByFire(var1, var2, var3, var4, var5, var6);
    }

    /**
     * A function to open a door.
     */
    public void onPoweredBlockChange(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.getFullMetadata(var1, var2, var3, var4);
        boolean var7 = (var6 & 4) != 0;

        if (var7 != var5)
        {
            int var8 = var6 & 7;
            var8 ^= 4;

            if ((var6 & 8) == 0)
            {
                var1.SetBlockMetadataWithNotify(var2, var3, var4, var8, 3);
                var1.notifyBlockChange(var2, var3 + 1, var4, this.blockID);
                var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
            }
            else
            {
                var1.SetBlockMetadataWithNotify(var2, var3 - 1, var4, var8, 3);
                var1.notifyBlockChange(var2, var3, var4, this.blockID);
                var1.markBlockRangeForRenderUpdate(var2, var3 - 1, var4, var2, var3, var4);
            }

            var1.playAuxSFXAtEntity((EntityPlayer)null, 1003, var2, var3, var4, 0);
        }
    }

    public boolean GetCanBlockBeIncinerated(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean GetPreventsFluidFlow(World var1, int var2, int var3, int var4, Block var5)
    {
        return true;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return this.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4).offset((double)var2, (double)var3, (double)var4);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.getFullMetadata(var1, var2, var3, var4);
        float var6 = 0.1875F;
        int var7 = var5 & 3;
        boolean var8 = (var5 & 4) != 0;
        boolean var9 = (var5 & 16) != 0;
        return var7 == 0 ? (var8 ? (!var9 ? AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, (double)var6) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, (double)(1.0F - var6), 1.0D, 1.0D, 1.0D)) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, (double)var6, 1.0D, 1.0D)) : (var7 == 1 ? (var8 ? (!var9 ? AxisAlignedBB.getAABBPool().getAABB((double)(1.0F - var6), 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, (double)var6, 1.0D, 1.0D)) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, (double)var6)) : (var7 == 2 ? (var8 ? (!var9 ? AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, (double)(1.0F - var6), 1.0D, 1.0D, 1.0D) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, (double)var6)) : AxisAlignedBB.getAABBPool().getAABB((double)(1.0F - var6), 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)) : (var7 == 3 ? (var8 ? (!var9 ? AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, (double)var6, 1.0D, 1.0D) : AxisAlignedBB.getAABBPool().getAABB((double)(1.0F - var6), 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, (double)(1.0F - var6), 1.0D, 1.0D, 1.0D)) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 2.0D, 1.0D))));
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        AxisAlignedBB var7 = this.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4).offset((double)var2, (double)var3, (double)var4);
        MovingObjectPosition var8 = var7.calculateIntercept(var5, var6);

        if (var8 != null)
        {
            var8.blockX = var2;
            var8.blockY = var3;
            var8.blockZ = var4;
        }

        return var8;
    }

    public boolean ShouldOffsetPositionIfPathingOutOf(IBlockAccess var1, int var2, int var3, int var4, Entity var5, PathFinder var6)
    {
        return false;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return var1.renderBlockDoor(this, var2, var3, var4);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.m_currentBlockRenderer.ShouldSideBeRenderedBasedOnCurrentBounds(var2, var3, var4, var5);
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return this.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4).offset((double)var2, (double)var3, (double)var4);
    }
}

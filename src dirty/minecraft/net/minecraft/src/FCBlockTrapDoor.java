package net.minecraft.src;

public class FCBlockTrapDoor extends BlockTrapDoor
{
    protected static final double m_dThickness = 0.1875D;
    protected static final double m_dHalfThickness = 0.09375D;
    private Icon m_filterIcon;

    protected FCBlockTrapDoor(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(1.5F);
        this.SetAxesEffectiveOn();
        this.SetBuoyant();
        this.InitBlockBounds(0.0D, 0.40625D, 0.0D, 1.0D, 0.59375D, 1.0D);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("trapdoor");
        this.disableStats();
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {}

    public void onPoweredBlockChange(World var1, int var2, int var3, int var4, boolean var5) {}

    /**
     * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
     */
    public boolean canPlaceBlockOnSide(World var1, int var2, int var3, int var4, int var5)
    {
        return true;
    }

    public int GetWeightOnPathBlocked(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -4;
    }

    public boolean CanPathThroughBlock(IBlockAccess var1, int var2, int var3, int var4, Entity var5, PathFinder var6)
    {
        return var6.CanPathThroughClosedWoodDoor() || var6.CanPathThroughOpenWoodDoor() && this.getBlocksMovement(var1, var2, var3, var4);
    }

    public boolean IsBreakableBarricade(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean IsBreakableBarricadeOpen(IBlockAccess var1, int var2, int var3, int var4)
    {
        return isTrapdoorOpen(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean getBlocksMovement(IBlockAccess var1, int var2, int var3, int var4)
    {
        return isTrapdoorOpen(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 2, 0, var6);
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

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender() {}

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);

        if (isTrapdoorOpen(var5))
        {
            int var6 = var5 & 3;

            switch (var6)
            {
                case 0:
                    return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);

                case 1:
                    return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D);

                case 2:
                    return AxisAlignedBB.getAABBPool().getAABB(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

                default:
                    return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);
            }
        }
        else
        {
            return (var5 & 8) != 0 ? AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.8125D, 0.0D, 1.0D, 1.0D, 1.0D) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D);
        }
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

    public boolean CanItemPassIfFilter(ItemStack var1)
    {
        int var2 = var1.getItem().GetFilterableProperties(var1);
        return (var2 & 14) != 0;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_filterIcon = var1.registerIcon("fcBlockHopper_trap");
    }

    public Icon GetHopperFilterIcon()
    {
        return this.m_filterIcon;
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return this.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4).offset((double)var2, (double)var3, (double)var4);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.m_currentBlockRenderer.ShouldSideBeRenderedBasedOnCurrentBounds(var2, var3, var4, var5);
    }
}

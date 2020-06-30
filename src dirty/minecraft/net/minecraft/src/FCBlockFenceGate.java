package net.minecraft.src;

public class FCBlockFenceGate extends BlockFenceGate
{
    public FCBlockFenceGate(int var1)
    {
        super(var1);
        this.SetBlockMaterial(FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(1.5F);
        this.setResistance(5.0F);
        this.SetAxesEffectiveOn();
        this.SetBuoyant();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.WOOD_BASED_BLOCK);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fenceGate");
    }

    public int GetWeightOnPathBlocked(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -3;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        int var10 = var1.getBlockMetadata(var2, var3, var4);

        if (isFenceGateOpen(var10))
        {
            var1.SetBlockMetadataWithNotify(var2, var3, var4, var10 & -5, 3);
        }
        else
        {
            int var11 = (MathHelper.floor_double((double)(var5.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3) % 4;
            int var12 = getDirection(var10);

            if (var12 == (var11 + 2) % 4)
            {
                var10 = var11;
            }

            var1.SetBlockMetadataWithNotify(var2, var3, var4, var10 | 4, 3);
        }

        var1.playAuxSFXAtEntity(var5, 1003, var2, var3, var4, 0);
        return true;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {}

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
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return isFenceGateOpen(var5);
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
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = getDirection(var1.getBlockMetadata(var2, var3, var4));
        return var5 != 2 && var5 != 0 ? AxisAlignedBB.getAABBPool().getAABB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return var1.renderBlockFenceGate(this, var2, var3, var4);
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

package net.minecraft.src;

public class FCBlockPressurePlate extends BlockPressurePlate
{
    protected static final double m_dHorizontalBorder = 0.0625D;
    protected static final double m_dHeightDepressed = 0.03125D;
    protected static final double m_dHeightResting = 0.0625D;
    protected static final double m_dHeightItem = 0.25D;
    protected static final double m_dHalfHeightItem = 0.125D;

    protected FCBlockPressurePlate(int var1, String var2, Material var3, EnumMobType var4)
    {
        super(var1, var2, var3, var4);
        this.InitBlockBounds(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.03125D, 0.9375D);
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}

    protected void func_94353_c_(int var1) {}

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender() {}

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        boolean var5 = this.getPowerSupply(var1.getBlockMetadata(var2, var3, var4)) > 0;
        return var5 ? AxisAlignedBB.getAABBPool().getAABB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.03125D, 0.9375D) : AxisAlignedBB.getAABBPool().getAABB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.0625D, 0.9375D);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        boolean var6 = false;

        if (!var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4))
        {
            var6 = true;
        }

        if (var6 && var1.getBlockId(var2, var3, var4) == this.blockID)
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockToAir(var2, var3, var4);
        }
    }

    private boolean IsModFence(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);
        Block var6 = Block.blocksList[var5];

        if (var6 != null && var6 instanceof FCBlockSidingAndCornerAndDecorative)
        {
            int var7 = var1.getBlockMetadata(var2, var3, var4);
            return var7 == 14;
        }
        else
        {
            return false;
        }
    }
}

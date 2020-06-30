package net.minecraft.src;

public class FCBlockEnchantmentTable extends BlockEnchantmentTable
{
    protected FCBlockEnchantmentTable(int var1)
    {
        super(var1);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return var5 != 1 ? super.shouldSideBeRendered(var1, var2, var3, var4, var5) : true;
    }
}

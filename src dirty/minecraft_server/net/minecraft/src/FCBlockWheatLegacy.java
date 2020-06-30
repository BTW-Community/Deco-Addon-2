package net.minecraft.src;

public class FCBlockWheatLegacy extends BlockCrops
{
    protected FCBlockWheatLegacy(int var1)
    {
        super(var1);
    }

    public boolean DoesBlockDropAsItemOnSaw(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        return var1.getBlockMetadata(var2, var3, var4) >= 7 ? super.GetStackRetrievedByBlockDispenser(var1, var2, var3, var4) : null;
    }
}

package net.minecraft.src;

public class FCBlockPotato extends BlockPotato
{
    public FCBlockPotato(int var1)
    {
        super(var1);
    }

    public float GetBaseGrowthChance(World var1, int var2, int var3, int var4)
    {
        return 0.1F;
    }

    protected void IncrementGrowthLevel(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetGrowthLevel(var1, var2, var3, var4) + 1;

        if (var5 != 7 && (var5 & 1) != 0)
        {
            this.SetGrowthLevelNoNotify(var1, var2, var3, var4, var5);
        }
        else
        {
            super.IncrementGrowthLevel(var1, var2, var3, var4);
        }
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 >= 7 ? super.GetStackRetrievedByBlockDispenser(var1, var2, var3, var4) : null;
    }
}

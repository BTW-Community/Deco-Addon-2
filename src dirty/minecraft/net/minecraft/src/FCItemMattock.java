package net.minecraft.src;

public class FCItemMattock extends FCItemRefinedPickAxe
{
    protected FCItemMattock(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcItemMattock");
    }

    public float getStrVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        float var7 = super.getStrVsBlock(var1, var2, var3, var4, var5, var6);
        float var8 = ((FCItemRefinedShovel)((FCItemRefinedShovel)FCBetterThanWolves.fcItemRefinedShovel)).getStrVsBlock(var1, var2, var3, var4, var5, var6);
        return var8 > var7 ? var8 : var7;
    }

    public boolean canHarvestBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return super.canHarvestBlock(var1, var2, var3, var4, var5, var6) || ((FCItemRefinedShovel)((FCItemRefinedShovel)FCBetterThanWolves.fcItemRefinedShovel)).canHarvestBlock(var1, var2, var3, var4, var5, var6);
    }

    public boolean IsEfficientVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
        return super.IsEfficientVsBlock(var1, var2, var3, var4, var5, var6) || ((FCItemRefinedShovel)((FCItemRefinedShovel)FCBetterThanWolves.fcItemRefinedShovel)).IsEfficientVsBlock(var1, var2, var3, var4, var5, var6);
    }
}

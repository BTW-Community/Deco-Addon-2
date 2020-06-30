package net.minecraft.src;

public class FCBlockSoulSand extends BlockSoulSand
{
    public FCBlockSoulSand(int var1)
    {
        super(var1);
        this.SetShovelsEffectiveOn();
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileSoulSand.itemID, 3, 0, var6);
        return true;
    }

    public boolean CanItemPassIfFilter(ItemStack var1)
    {
        return var1.itemID == FCBetterThanWolves.fcItemGroundNetherrack.itemID || var1.itemID == FCBetterThanWolves.fcItemSoulDust.itemID || var1.itemID == Item.lightStoneDust.itemID;
    }

    public boolean CanTransformItemIfFilter(ItemStack var1)
    {
        return true;
    }

    public boolean CanNetherWartGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }
}

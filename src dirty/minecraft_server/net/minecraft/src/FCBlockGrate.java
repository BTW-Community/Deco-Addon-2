package net.minecraft.src;

public class FCBlockGrate extends FCBlockPane
{
    protected FCBlockGrate(int var1)
    {
        super(var1, "fcBlockGrate", "fcBlockGrate", Material.wood, false);
        this.setHardness(0.5F);
        this.SetAxesEffectiveOn();
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.setLightOpacity(2);
        Block.useNeighborBrightness[var1] = true;
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockGrate");
    }

    public boolean DoesBlockBreakSaw(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, Item.stick.itemID, 2, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 2, 0, var6);
        return true;
    }

    public boolean CanItemPassIfFilter(ItemStack var1)
    {
        int var2 = var1.getItem().GetFilterableProperties(var1);
        return (var2 & 10) != 0;
    }
}

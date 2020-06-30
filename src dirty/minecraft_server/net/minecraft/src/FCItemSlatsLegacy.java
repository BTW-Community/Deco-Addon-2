package net.minecraft.src;

public class FCItemSlatsLegacy extends FCItemPlacesAsBlock
{
    public FCItemSlatsLegacy(int var1)
    {
        super(var1, FCBetterThanWolves.fcAestheticNonOpaque.blockID, 8, "fcItemSlats");
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
    }

    public boolean CanItemPassIfFilter(ItemStack var1)
    {
        int var2 = var1.getItem().GetFilterableProperties(var1);
        return (var2 & 24) != 0;
    }
}

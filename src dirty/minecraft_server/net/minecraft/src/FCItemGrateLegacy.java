package net.minecraft.src;

public class FCItemGrateLegacy extends FCItemPlacesAsBlock
{
    public FCItemGrateLegacy(int var1)
    {
        super(var1, FCBetterThanWolves.fcAestheticNonOpaque.blockID, 6, "fcItemGrate");
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
    }

    public boolean CanItemPassIfFilter(ItemStack var1)
    {
        int var2 = var1.getItem().GetFilterableProperties(var1);
        return (var2 & 10) != 0;
    }
}

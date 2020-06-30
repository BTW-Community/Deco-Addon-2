package net.minecraft.src;

public class FCItemWickerPaneLegacy extends FCItemPlacesAsBlock
{
    public FCItemWickerPaneLegacy(int var1)
    {
        super(var1, FCBetterThanWolves.fcAestheticNonOpaque.blockID, 7, "fcItemWicker");
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
    }

    public boolean CanItemPassIfFilter(ItemStack var1)
    {
        int var2 = var1.getItem().GetFilterableProperties(var1);
        return (var2 & 8) != 0;
    }

    public boolean CanTransformItemIfFilter(ItemStack var1)
    {
        return var1.itemID == Block.gravel.blockID;
    }
}

package net.minecraft.src;

public class FCItemFishingRod extends ItemFishingRod
{
    public FCItemFishingRod(int var1)
    {
        super(var1);
        this.setMaxDamage(32);
        this.SetBuoyant();
        this.SetFilterableProperties(4);
        this.setUnlocalizedName("fishingRod");
    }
}

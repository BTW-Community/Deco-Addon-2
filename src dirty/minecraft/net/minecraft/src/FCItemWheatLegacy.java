package net.minecraft.src;

public class FCItemWheatLegacy extends Item
{
    public FCItemWheatLegacy(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
        this.SetBellowsBlowDistance(1);
        this.SetFilterableProperties(4);
        this.SetAsBasicHerbivoreFood();
        this.SetAsBasicPigFood();
        this.setUnlocalizedName("wheat");
    }
}

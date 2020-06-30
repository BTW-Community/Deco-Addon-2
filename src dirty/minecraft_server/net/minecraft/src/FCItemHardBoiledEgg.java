package net.minecraft.src;

public class FCItemHardBoiledEgg extends ItemFood
{
    private static final int iHardBoiledEggHealthHealed = 3;
    private static final float iHardBoiledEggSaturationModifier = 0.25F;

    public FCItemHardBoiledEgg(int var1)
    {
        super(var1, 3, 0.25F, false);
        this.SetNeutralBuoyant();
        this.SetFilterableProperties(2);
        this.setUnlocalizedName("fcItemEggPoached");
    }
}

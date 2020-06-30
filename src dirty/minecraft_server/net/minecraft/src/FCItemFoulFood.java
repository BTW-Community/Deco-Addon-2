package net.minecraft.src;

public class FCItemFoulFood extends FCItemFood
{
    private static final int m_iHealthHealed = 1;
    private static final float m_iSaturationModifier = 0.0F;

    public FCItemFoulFood(int var1)
    {
        super(var1, 1, 0.0F, false, "fcItemFoulFood");
        this.SetIncreasedFoodPoisoningEffect();
    }
}

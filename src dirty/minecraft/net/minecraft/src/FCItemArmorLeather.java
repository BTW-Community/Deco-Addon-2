package net.minecraft.src;

public class FCItemArmorLeather extends FCItemArmor
{
    private static final int m_iRenderIndex = 0;
    private static final int m_iWornWeight = 0;

    public FCItemArmorLeather(int var1, int var2)
    {
        super(var1, EnumArmorMaterial.CLOTH, 0, var2, 0);
        this.SetInfernalMaxEnchantmentCost(10);
        this.SetInfernalMaxNumEnchants(2);
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
    }
}

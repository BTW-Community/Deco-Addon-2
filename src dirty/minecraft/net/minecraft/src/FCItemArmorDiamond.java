package net.minecraft.src;

public class FCItemArmorDiamond extends FCItemArmor
{
    private static final int m_iRenderIndex = 3;

    public FCItemArmorDiamond(int var1, int var2, int var3)
    {
        super(var1, EnumArmorMaterial.DIAMOND, 3, var2, var3);
        this.SetInfernalMaxEnchantmentCost(30);
        this.SetInfernalMaxNumEnchants(2);
    }
}

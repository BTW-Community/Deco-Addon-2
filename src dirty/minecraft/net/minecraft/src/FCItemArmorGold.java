package net.minecraft.src;

public class FCItemArmorGold extends FCItemArmor
{
    private static final int m_iRenderIndex = 4;

    public FCItemArmorGold(int var1, int var2, int var3)
    {
        super(var1, EnumArmorMaterial.GOLD, 4, var2, var3);
        this.SetInfernalMaxEnchantmentCost(30);
        this.SetInfernalMaxNumEnchants(3);
    }
}

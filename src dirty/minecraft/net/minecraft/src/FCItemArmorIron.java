package net.minecraft.src;

public class FCItemArmorIron extends FCItemArmor
{
    private static final int m_iRenderIndex = 2;

    public FCItemArmorIron(int var1, int var2, int var3)
    {
        super(var1, EnumArmorMaterial.IRON, 2, var2, var3);
        this.SetInfernalMaxEnchantmentCost(25);
        this.SetInfernalMaxNumEnchants(2);
    }
}

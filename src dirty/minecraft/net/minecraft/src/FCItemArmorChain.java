package net.minecraft.src;

public class FCItemArmorChain extends FCItemArmorMod
{
    static final int m_iRenderIndex = 1;

    public FCItemArmorChain(int var1, int var2, int var3)
    {
        super(var1, EnumArmorMaterial.CHAIN, 1, var2, var3);
        this.SetInfernalMaxEnchantmentCost(30);
        this.SetInfernalMaxNumEnchants(2);
    }

    public String GetWornTexturePrefix()
    {
        return "chain";
    }
}

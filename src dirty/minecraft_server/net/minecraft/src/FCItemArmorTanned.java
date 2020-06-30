package net.minecraft.src;

public class FCItemArmorTanned extends FCItemArmorMod
{
    static final int m_iRenderIndex = 1;
    static final int m_iWornWeight = 0;

    public FCItemArmorTanned(int var1, int var2)
    {
        super(var1, EnumArmorMaterial.CLOTH, 1, var2, 0);
        this.setMaxDamage(this.getMaxDamage() << 1);
        this.SetInfernalMaxEnchantmentCost(10);
        this.SetInfernalMaxNumEnchants(2);
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
    }

    public String GetWornTexturePrefix()
    {
        return "fcTanned";
    }
}

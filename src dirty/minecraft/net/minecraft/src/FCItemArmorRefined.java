package net.minecraft.src;

public class FCItemArmorRefined extends FCItemArmorMod
{
    static final int m_iRenderIndex = 1;
    private final int m_iEnchantability = 0;
    static final int m_iMaxDamage = 576;

    public FCItemArmorRefined(int var1, int var2, int var3)
    {
        super(var1, EnumArmorMaterial.DIAMOND, 1, var2, var3);
        this.setMaxDamage(576);
        this.SetInfernalMaxEnchantmentCost(30);
        this.SetInfernalMaxNumEnchants(4);
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 0;
    }

    public String GetWornTexturePrefix()
    {
        return "plate";
    }
}

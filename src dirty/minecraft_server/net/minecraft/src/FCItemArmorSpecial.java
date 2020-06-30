package net.minecraft.src;

public class FCItemArmorSpecial extends FCItemArmorMod
{
    static final int m_iRenderIndex = 1;
    static final int m_iArmorLevel = 1;
    static final int m_iMaxDamage = 12;

    public FCItemArmorSpecial(int var1, int var2)
    {
        super(var1, EnumArmorMaterial.IRON, 1, var2, 0);
        this.setMaxDamage(12);
        this.damageReduceAmount = EnumArmorMaterial.CLOTH.getDamageReductionAmount(var2);
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

    public String GetWornTexturePrefix()
    {
        return "special";
    }
}

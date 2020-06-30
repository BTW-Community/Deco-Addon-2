package net.minecraft.src;

public class FCItemArmorPadded extends FCItemArmorMod
{
    static final int m_iRenderIndex = 1;
    private final int m_iEnchantability = 0;

    public FCItemArmorPadded(int var1, int var2)
    {
        super(var1, EnumArmorMaterial.CLOTH, 1, var2, 0);
        this.setMaxDamage(this.getMaxDamage() >> 1);
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
    }

    public boolean HasCustomColors()
    {
        return true;
    }

    public int GetDefaultColor()
    {
        return 10063743;
    }

    public String GetWornTexturePrefix()
    {
        return "fcPadded";
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 0;
    }
}

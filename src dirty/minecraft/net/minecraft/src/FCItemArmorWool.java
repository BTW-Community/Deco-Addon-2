package net.minecraft.src;

public class FCItemArmorWool extends FCItemArmorMod
{
    static final int m_iRenderIndex = 1;
    private final int m_iEnchantability = 0;
    private Icon m_IconOverlay = null;

    public FCItemArmorWool(int var1, int var2)
    {
        super(var1, EnumArmorMaterial.CLOTH, 1, var2, 0);
        this.setMaxDamage(this.getMaxDamage() >> 2);
        this.damageReduceAmount = 1;
        this.SetBuoyant();
        this.SetFurnaceBurnTime(this.GetNumWoolKnitMadeOf() * FCEnumFurnaceBurnTime.WOOL_KNIT.m_iBurnTime / 2);
    }

    public boolean HasCustomColors()
    {
        return true;
    }

    public boolean HasSecondRenderLayerWhenWorn()
    {
        return true;
    }

    public int GetDefaultColor()
    {
        return 8421504;
    }

    public String GetWornTexturePrefix()
    {
        return "fcWool";
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 0;
    }

    private int GetNumWoolKnitMadeOf()
    {
        switch (this.armorType)
        {
            case 0:
                return 2;

            case 1:
                return 4;

            case 2:
                return 3;

            default:
                return 2;
        }
    }

    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);

        if (this.armorType == 0)
        {
            this.m_IconOverlay = var1.registerIcon("fcItemWoolHelm_overlay");
        }
    }

    public boolean requiresMultipleRenderPasses()
    {
        return this.m_IconOverlay != null;
    }

    /**
     * Gets an icon index based on an item's damage value and the given render pass
     */
    public Icon getIconFromDamageForRenderPass(int var1, int var2)
    {
        return var2 == 1 && this.m_IconOverlay != null ? this.m_IconOverlay : this.getIconFromDamage(var1);
    }
}

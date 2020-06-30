package net.minecraft.src;

public class FCItemWoolKnit extends Item
{
    public FCItemWoolKnit(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.SetBuoyant();
        this.SetBellowsBlowDistance(1);
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.WOOL_KNIT);
        this.SetFilterableProperties(16);
        this.setUnlocalizedName("fcItemWoolKnit");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    public String getItemDisplayName(ItemStack var1)
    {
        int var2 = MathHelper.clamp_int(var1.getItemDamage(), 0, 15);
        return ("" + FCItemWool.m_sWoolColorNames[var2] + " " + StringTranslate.getInstance().translateNamedKey(this.getLocalizedName(var1))).trim();
    }
}

package net.minecraft.src;

import java.util.List;

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

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < 16; ++var4)
        {
            var3.add(new ItemStack(var1, 1, var4));
        }
    }

    public int getColorFromItemStack(ItemStack var1, int var2)
    {
        return FCItemWool.m_iWoolColors[var1.getItemDamage()];
    }
}

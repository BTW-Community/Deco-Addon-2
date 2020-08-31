package net.minecraft.src;

import java.util.List;

public class DecoItemBark extends Item {
    public static final int m_iSubtypeOak = 0;
    public static final int m_iSubtypeSpruce = 1;
    public static final int m_iSubtypeBirch = 2;
    public static final int m_iSubtypeJungle = 3;
    public static final int m_iSubtypeBloodWood = 4;
    public static final int m_iSubtypeCherry = 5;
    public static final int m_iSubtypeCrimson = 6;
    public static final int m_iSubtypeWarped = 7;
    public static final int m_iNumSubtypes = 8;
    private String[] m_sNameExtensionsBySubtype = new String[] {"oak", "spruce", "birch", "jungle", "bloodwood", "cherry", "crimson", "warped"};
    private Icon[] m_IconBySubtype = new Icon[m_iNumSubtypes];
    private String[] m_sIconNamesBySubtype = new String[] {"fcItemBarkOak", "fcItemBarkSpruce", "fcItemBarkBirch", "fcItemBarkJungle", "fcItemBarkBloodWood", "ginger_barkCherry", "ginger_barkCrimson", "ginger_barkWarped"};

    public DecoItemBark(int var1)
    {
        super(var1);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.SetBuoyant();
        this.SetBellowsBlowDistance(2);
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.KINDLING);
        this.SetFilterableProperties(18);
        this.setUnlocalizedName("fcItemBark");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack var1)
    {
        int var2 = MathHelper.clamp_int(var1.getItemDamage(), 0, m_iNumSubtypes - 1);
        return super.getUnlocalizedName() + "." + this.m_sNameExtensionsBySubtype[var2];
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < m_iNumSubtypes; ++var4)
        {
            var3.add(new ItemStack(var1, 1, var4));
        }
    }
    
    //CLIENT ONLY
    public void registerIcons(IconRegister var1)
    {
        for (int var2 = 0; var2 < this.m_sIconNamesBySubtype.length; ++var2)
        {
            this.m_IconBySubtype[var2] = var1.registerIcon(this.m_sIconNamesBySubtype[var2]);
        }
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int var1)
    {
        int var2 = MathHelper.clamp_int(var1, 0, m_iNumSubtypes - 1);
        return this.m_IconBySubtype[var2];
    }

}

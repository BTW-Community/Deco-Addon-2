package net.minecraft.src;

public class FCItemBark extends Item
{
    public static final int m_iSubtypeOak = 0;
    public static final int m_iSubtypeSpruce = 1;
    public static final int m_iSubtypeBirch = 2;
    public static final int m_iSubtypeJungle = 3;
    public static final int m_iSubtypeBloodWood = 4;
    public static final int m_iNumSubtypes = 5;
    private String[] m_sNameExtensionsBySubtype = new String[] {"oak", "spruce", "birch", "jungle", "bloodwood"};

    public FCItemBark(int var1)
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
        int var2 = MathHelper.clamp_int(var1.getItemDamage(), 0, 4);
        return super.getUnlocalizedName() + "." + this.m_sNameExtensionsBySubtype[var2];
    }
}

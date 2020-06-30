package net.minecraft.src;

public class FCItemCandle extends FCItemPlacesAsBlock
{
    public static final int[] m_iCandeColors = new int[] {1052688, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 8618883, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 16777215};
    public static final String[] m_sCandleColorNames = new String[] {"Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Light Gray", "Gray", "Pink", "Lime", "Yellow", "Light Blue", "Magenta", "Orange", "White"};

    public FCItemCandle(int var1)
    {
        super(var1, FCBetterThanWolves.fcBlockCandle.blockID, 0, "fcItemCandle");
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.SetBuoyant();
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return var1;
    }

    public String getItemDisplayName(ItemStack var1)
    {
        int var2 = MathHelper.clamp_int(var1.getItemDamage(), 0, 15);
        return ("" + m_sCandleColorNames[var2] + " " + StringTranslate.getInstance().translateNamedKey(this.getLocalizedName(var1))).trim();
    }
}

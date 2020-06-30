package net.minecraft.src;

public class FCItemAncientProphecy extends ItemEditableBook
{
    public static String[] sProphecyTexts = new String[] {"Prophecy Text 1", "Prophecy Text 2", "Prophecy Text 3", "Prophecy Text 4", "Prophecy Text 5", "Prophecy Text 6", "Prophecy Text 7", "Prophecy Text 8", "Prophecy Text 9", "Prophecy Text 10", "Prophecy Text 11", "Prophecy Text 12", "Prophecy Text 13", "Prophecy Text 14", "Prophecy Text 15", "Prophecy Text 16"};
    public static String sCheatProphecyText = "...and on the third day of the fourth month, the prophet descended from on high and spoke onto his flock: \n\n\'Woe be onto the cheater and his kin, for verily...he does suck\'...";

    public FCItemAncientProphecy(int var1)
    {
        super(var1);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.SetBuoyant();
        this.setUnlocalizedName("fcItemAncientProphecy");
    }

    public void InitializeProphecyDataFromEnchantmentID(ItemStack var1, int var2)
    {
        if (var2 < 0)
        {
            this.InitializeProphecyData(var1, sCheatProphecyText);
        }
        else
        {
            int var3 = this.ConvertEnchantmentIDToEnchantmentNumber(var2);
            int var4 = var3 % sProphecyTexts.length;
            this.InitializeProphecyData(var1, sProphecyTexts[var4]);
        }
    }

    private int ConvertEnchantmentIDToEnchantmentNumber(int var1)
    {
        int var2 = 0;

        for (int var3 = 0; var3 < Enchantment.field_92090_c.length; ++var3)
        {
            if (Enchantment.field_92090_c[var3].effectId == var1)
            {
                var2 = var3;
                break;
            }
        }

        return var2;
    }

    private void InitializeProphecyData(ItemStack var1, String var2)
    {
        NBTTagCompound var3 = new NBTTagCompound();
        var3.setString("title", "Prophecy Fragment");
        var3.setString("author", "Unknown");
        NBTTagList var4 = new NBTTagList("pages");
        var4.appendTag(new NBTTagString("1", var2));
        var3.setTag("pages", var4);
        var1.setTagCompound(var3);
    }
}

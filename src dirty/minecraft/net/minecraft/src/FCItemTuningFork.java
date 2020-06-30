package net.minecraft.src;

import java.util.List;

public class FCItemTuningFork extends Item
{
    public static final String[] pitchNames = new String[] {"0 - F#", "1 - G", "2 - G#", "3 - A", "4 - A#", "5 - B", "6 - C", "7 - C#", "8 - D", "9 - D#", "10 - E", "11 - F", "12 - F#", "13 - G", "14 - G#", "15 - A", "16 - A#", "17 - B", "18 - C", "19 - C#", "20 - D", "21 - D#", "22 - E", "23 - F", "24 - F#"};

    public FCItemTuningFork(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("fcItemTuningFork");
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        byte var11 = (byte)var1.getItemDamage();
        float var12 = (float)Math.pow(2.0D, (double)(var11 - 12) / 12.0D);
        String var13 = "harp";
        Material var14 = var3.getBlockMaterial(var4, var5, var6);
        byte var15 = 0;

        if (var14 == Material.rock)
        {
            var15 = 1;
        }

        if (var14 == Material.sand)
        {
            var15 = 2;
        }

        if (var14 == Material.glass)
        {
            var15 = 3;
        }

        if (var14 == Material.wood)
        {
            var15 = 4;
        }

        if (var15 == 1)
        {
            var13 = "bd";
        }

        if (var15 == 2)
        {
            var13 = "snare";
        }

        if (var15 == 3)
        {
            var13 = "hat";
        }

        if (var15 == 4)
        {
            var13 = "bassattack";
        }

        var3.playSoundEffect((double)var4 + 0.5D, (double)var5 + 0.5D, (double)var6 + 0.5D, "note." + var13, 3.0F, var12);
        var3.spawnParticle("note", (double)var4 + 0.5D, (double)var5 + 1.2D, (double)var6 + 0.5D, (double)var11 / 24.0D, 0.0D, 0.0D);
        return true;
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4)
    {
        var3.add("Pitch: " + pitchNames[var1.getItemDamage()]);
    }
}

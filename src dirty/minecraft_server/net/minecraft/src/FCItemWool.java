package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FCItemWool extends Item
{
    public static final int[] m_iWoolColors = new int[] {1052688, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 8618883, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 16777215};
    public static final String[] m_sWoolColorNames = new String[] {"Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Light Gray", "Gray", "Pink", "Lime", "Yellow", "Light Blue", "Magenta", "Orange", "White"};
    private static List m_colorConversionArray = null;

    public FCItemWool(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.SetBuoyant();
        this.SetBellowsBlowDistance(1);
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.KINDLING);
        this.SetFilterableProperties(18);
        this.setUnlocalizedName("fcItemWool");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    public String getItemDisplayName(ItemStack var1)
    {
        int var2 = MathHelper.clamp_int(var1.getItemDamage(), 0, 15);
        return ("" + m_sWoolColorNames[var2] + " " + StringTranslate.getInstance().translateNamedKey(this.getLocalizedName(var1))).trim();
    }

    public static int AverageWoolColorsInGrid(InventoryCrafting var0)
    {
        int var1 = 0;
        int var2 = 0;
        int var3 = 0;
        int var4 = 0;
        int var5 = 0;
        int var6;
        int var8;

        for (var6 = 0; var6 < var0.getSizeInventory(); ++var6)
        {
            ItemStack var7 = var0.getStackInSlot(var6);

            if (var7 != null && (var7.itemID == FCBetterThanWolves.fcItemWool.itemID || var7.itemID == FCBetterThanWolves.fcItemWoolKnit.itemID))
            {
                var8 = MathHelper.clamp_int(var7.getItemDamage(), 0, 15);
                int var9 = m_iWoolColors[var8];
                ++var5;
                var2 += var9 >> 16 & 255;
                var3 += var9 >> 8 & 255;
                var4 += var9 & 255;
            }
        }

        if (var5 > 0)
        {
            var6 = var2 / var5;
            int var10 = var3 / var5;
            var8 = var4 / var5;
            var1 = var6 << 16 | var10 << 8 | var8;
        }

        return var1;
    }

    private static void InitColorConversionArray()
    {
        m_colorConversionArray = new ArrayList(16);

        for (int var0 = 0; var0 < 16; ++var0)
        {
            LinkedList var1 = new LinkedList();
            m_colorConversionArray.add(var0, var1);
            var1.add(Integer.valueOf(m_iWoolColors[var0]));
        }

        SetHardColorConversionPoint(8, 0, 15);
        SetHardColorConversionPoint(9, 1, 15);
        SetHardColorConversionPoint(14, 1, 11);
        SetHardColorConversionPoint(9, 2, 10);
        SetHardColorConversionPoint(5, 4, 1);
        SetHardColorConversionPoint(6, 4, 2);
        SetHardColorConversionPoint(12, 4, 15);
        SetHardColorConversionPoint(13, 5, 9);
        SetHardColorConversionPoint(7, 8, 15);
    }

    private static void SetHardColorConversionPoint(int var0, int var1, int var2)
    {
        int var3 = m_iWoolColors[var1];
        int var4 = m_iWoolColors[var2];
        int var5 = ((var3 >> 16 & 255) + (var4 >> 16 & 255)) / 2;
        int var6 = ((var3 >> 8 & 255) + (var4 >> 8 & 255)) / 2;
        int var7 = ((var3 & 255) + (var4 & 255)) / 2;
        int var8 = var5 << 16 | var6 << 8 | var7;
        ((List)m_colorConversionArray.get(var0)).add(Integer.valueOf(var8));
    }

    public static int GetClosestColorIndex(int var0)
    {
        int var1 = -1;
        int var2 = 0;
        int var3 = var0 >> 16 & 255;
        int var4 = var0 >> 8 & 255;
        int var5 = var0 & 255;

        if (m_colorConversionArray == null)
        {
            InitColorConversionArray();
        }

        int var6;

        if (MathHelper.abs_int(var3 - var4) > 5 || MathHelper.abs_int(var3 - var5) > 5)
        {
            for (var6 = 0; var6 < 16; ++var6)
            {
                List var7 = (List)m_colorConversionArray.get(var6);
                Iterator var8 = var7.iterator();

                while (var8.hasNext())
                {
                    int var9 = ((Integer)var8.next()).intValue();
                    int var10 = var9 >> 16 & 255;
                    int var11 = var9 >> 8 & 255;
                    int var12 = var9 & 255;
                    int var13 = var10 - var3;
                    int var14 = var11 - var4;
                    int var15 = var12 - var5;
                    int var16 = 2 * var13 * var13 + 4 * var14 * var14 + 3 * var15 * var15;

                    if (var1 == -1 || var16 < var2)
                    {
                        var1 = var6;
                        var2 = var16;
                    }
                }
            }
        }

        if (var1 == -1 || var2 > 15000)
        {
            var6 = var3 + var4 + var5;

            if (var6 < 125)
            {
                var1 = 0;
            }
            else if (var6 < 297)
            {
                var1 = 8;
            }
            else if (var6 < 579)
            {
                var1 = 7;
            }
            else
            {
                var1 = 15;
            }
        }

        return var1;
    }
}

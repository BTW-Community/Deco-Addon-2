package net.minecraft.src;

import java.util.List;

public class FCItemMap extends ItemMap
{
    protected FCItemMap(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetBellowsBlowDistance(3);
        this.SetFilterableProperties(16);
        this.setUnlocalizedName("map");
    }

    public void updateMapData(World var1, Entity var2, MapData var3)
    {
        if (var1.provider.dimensionId == var3.dimension && var2 instanceof EntityPlayer && var3.IsEntityLocationVisibleOnMap(var2))
        {
            super.updateMapData(var1, var2, var3);
        }
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack var1, EntityPlayer var2, List var3, boolean var4)
    {
        MapData var5 = this.getMapData(var1, var2.worldObj);

        if (var5 != null)
        {
            var3.add("Scale: x" + (1 << var5.scale));
        }
    }
}

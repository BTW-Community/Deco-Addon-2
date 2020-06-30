package net.minecraft.src;

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
}

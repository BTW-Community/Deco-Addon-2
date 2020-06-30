package net.minecraft.src;

public class FCItemShaft extends ItemReed
{
    public FCItemShaft(int var1)
    {
        super(var1, 0);
        this.setFull3D();
        this.SetBuoyant();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.SHAFT);
        this.SetIncineratedInCrucible();
        this.SetFilterableProperties(4);
        this.setUnlocalizedName("stick");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    /**
     * Returns the blockID for this Item
     */
    public int getBlockID()
    {
        return FCBetterThanWolves.fcBlockShaft.blockID;
    }
}

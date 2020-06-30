package net.minecraft.src;

public class FCItemWickerPiece extends Item
{
    public FCItemWickerPiece(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetBellowsBlowDistance(2);
        this.SetIncineratedInCrucible();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.WICKER_PIECE);
        this.SetFilterableProperties(16);
        this.setUnlocalizedName("fcItemWickerPiece");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    public boolean GetCanBeFedDirectlyIntoCampfire(int var1)
    {
        return false;
    }

    public boolean GetCanBeFedDirectlyIntoBrickOven(int var1)
    {
        return false;
    }
}

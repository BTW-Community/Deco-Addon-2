package net.minecraft.src;

public class FCItemKnittingNeedles extends Item
{
    public FCItemKnittingNeedles(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.SHAFT);
        this.SetFilterableProperties(4);
        this.setUnlocalizedName("fcItemKnittingNeedles");
        this.setCreativeTab(CreativeTabs.tabTools);
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

package net.minecraft.src;

public class FCItemMould extends Item
{
    protected FCItemMould(int var1)
    {
        super(var1);
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    public boolean IsConsumedInCrafting()
    {
        return false;
    }
}

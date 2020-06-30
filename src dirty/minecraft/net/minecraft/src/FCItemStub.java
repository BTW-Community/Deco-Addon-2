package net.minecraft.src;

public class FCItemStub extends Item
{
    public FCItemStub(int var1)
    {
        super(var1);
        this.setCreativeTab((CreativeTabs)null);
    }

    public void registerIcons(IconRegister var1)
    {
        this.itemIcon = var1.registerIcon("fcItemDung");
    }
}

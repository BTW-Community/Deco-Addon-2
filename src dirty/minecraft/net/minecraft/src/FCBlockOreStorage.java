package net.minecraft.src;

public class FCBlockOreStorage extends FCBlockFullBlock
{
    public FCBlockOreStorage(int var1)
    {
        super(var1, Material.iron);
        this.SetPicksEffectiveOn();
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
}

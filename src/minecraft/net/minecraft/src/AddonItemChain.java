package net.minecraft.src;

public class AddonItemChain extends FCItemPlacesAsBlock
{
    public AddonItemChain(int var1)
    {
        super(var1, AddonDefs.chain.blockID);
        this.setUnlocalizedName("ginger_chainItem");
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }
}

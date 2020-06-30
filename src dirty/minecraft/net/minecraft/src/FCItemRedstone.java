package net.minecraft.src;

public class FCItemRedstone extends FCItemPlacesAsBlock
{
    public FCItemRedstone(int var1)
    {
        super(var1, Block.redstoneWire.blockID);
        this.SetBellowsBlowDistance(3);
        this.SetFilterableProperties(8);
        this.setUnlocalizedName("redstone");
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }
}

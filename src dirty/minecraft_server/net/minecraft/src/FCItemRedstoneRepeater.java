package net.minecraft.src;

public class FCItemRedstoneRepeater extends FCItemPlacesAsBlock
{
    public FCItemRedstoneRepeater(int var1)
    {
        super(var1, Block.redstoneRepeaterIdle.blockID);
        this.setUnlocalizedName("diode");
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }
}

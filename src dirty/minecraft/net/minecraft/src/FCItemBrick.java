package net.minecraft.src;

public class FCItemBrick extends FCItemPlacesAsBlock
{
    public FCItemBrick(int var1)
    {
        super(var1);
        this.setUnlocalizedName("brick");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    /**
     * Returns the blockID for this Item
     */
    public int getBlockID()
    {
        return FCBetterThanWolves.fcBlockCookedBrick.blockID;
    }

    public boolean IsPistonPackable(ItemStack var1)
    {
        return true;
    }

    public int GetRequiredItemCountToPistonPack(ItemStack var1)
    {
        return 8;
    }

    public int GetResultingBlockIDOnPistonPack(ItemStack var1)
    {
        return FCBetterThanWolves.fcBlockBrickLoose.blockID;
    }
}

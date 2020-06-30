package net.minecraft.src;

public class FCItemChunkOreGold extends FCItemPlacesAsBlock
{
    public FCItemChunkOreGold(int var1)
    {
        super(var1, FCBetterThanWolves.fcBlockChunkOreGold.blockID);
        this.SetFilterableProperties(2);
        this.setUnlocalizedName("fcItemChunkGoldOre");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    public boolean IsPistonPackable(ItemStack var1)
    {
        return true;
    }

    public int GetRequiredItemCountToPistonPack(ItemStack var1)
    {
        return 9;
    }

    public int GetResultingBlockIDOnPistonPack(ItemStack var1)
    {
        return FCBetterThanWolves.fcBlockChunkOreStorageGold.blockID;
    }
}

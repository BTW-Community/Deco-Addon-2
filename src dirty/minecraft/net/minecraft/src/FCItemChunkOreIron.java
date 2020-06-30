package net.minecraft.src;

public class FCItemChunkOreIron extends FCItemPlacesAsBlock
{
    public FCItemChunkOreIron(int var1)
    {
        super(var1, FCBetterThanWolves.fcBlockChunkOreIron.blockID);
        this.SetFilterableProperties(2);
        this.setUnlocalizedName("fcItemChunkIronOre");
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
        return FCBetterThanWolves.fcBlockChunkOreStorageIron.blockID;
    }
}

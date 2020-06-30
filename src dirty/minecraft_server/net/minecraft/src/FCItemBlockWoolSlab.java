package net.minecraft.src;

public class FCItemBlockWoolSlab extends FCItemBlockSlab
{
    public FCItemBlockWoolSlab(int var1)
    {
        super(var1);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("fcBlockWoolSlab");
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return var1;
    }

    public int GetBlockIDToPlace(int var1, int var2, float var3, float var4, float var5)
    {
        return var2 != 0 && (var2 == 1 || (double)var4 <= 0.5D) ? FCBetterThanWolves.fcWoolSlab.blockID : FCBetterThanWolves.fcBlockWoolSlabTop.blockID;
    }

    public boolean canCombineWithBlock(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockId(var2, var3, var4);
        int var7 = var1.getBlockMetadata(var2, var3, var4);
        return (var6 == FCBetterThanWolves.fcWoolSlab.blockID || var6 == FCBetterThanWolves.fcBlockWoolSlabTop.blockID) && var7 == var5;
    }
}

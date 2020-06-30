package net.minecraft.src;

public class FCItemBlockIce extends ItemBlock
{
    public FCItemBlockIce(int var1)
    {
        super(var1);
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return 8;
    }
}

package net.minecraft.src;

public class FCBlockLilyPad extends BlockLilyPad
{
    protected FCBlockLilyPad(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.015625D, 1.0D);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return this.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4).offset((double)var2, (double)var3, (double)var4);
    }

    protected boolean CanGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.getBlockId(var2, var3, var4) == Block.waterStill.blockID;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return false;
    }
}

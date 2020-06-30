package net.minecraft.src;

public class FCItemBlockDirtLooseSlab extends FCItemBlockSlab
{
    public FCItemBlockDirtLooseSlab(int var1)
    {
        super(var1);
    }

    public boolean canCombineWithBlock(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockId(var2, var3, var4);

        if (var6 == FCBetterThanWolves.fcBlockDirtSlab.blockID)
        {
            int var7 = var1.getBlockMetadata(var2, var3, var4);
            int var8 = FCBetterThanWolves.fcBlockDirtSlab.GetSubtype(var7);
            return var8 != 3 && !FCBetterThanWolves.fcBlockDirtSlab.GetIsUpsideDown(var7);
        }
        else
        {
            return var6 == FCBetterThanWolves.fcBlockMyceliumSlab.blockID ? true : super.canCombineWithBlock(var1, var2, var3, var4, var5);
        }
    }

    public boolean convertToFullBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = ((FCBlockSlab)Block.blocksList[this.getBlockID()]).GetCombinedBlockID(0);
        return var1.setBlockWithNotify(var2, var3, var4, var5);
    }
}

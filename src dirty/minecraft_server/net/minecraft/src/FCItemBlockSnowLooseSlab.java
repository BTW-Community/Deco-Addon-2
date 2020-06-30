package net.minecraft.src;

public class FCItemBlockSnowLooseSlab extends FCItemBlockSlab
{
    public FCItemBlockSnowLooseSlab(int var1)
    {
        super(var1);
    }

    public boolean canCombineWithBlock(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockId(var2, var3, var4);
        return var6 == this.getBlockID();
    }
}

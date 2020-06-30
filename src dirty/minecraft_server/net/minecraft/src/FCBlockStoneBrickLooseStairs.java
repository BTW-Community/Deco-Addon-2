package net.minecraft.src;

public class FCBlockStoneBrickLooseStairs extends FCBlockMortarReceiverStairs
{
    protected FCBlockStoneBrickLooseStairs(int var1)
    {
        super(var1, FCBetterThanWolves.fcBlockStoneBrickLoose, 0);
        this.SetPicksEffectiveOn();
        this.setUnlocalizedName("fcBlockStoneBrickLooseStairs");
    }

    public boolean OnMortarApplied(World var1, int var2, int var3, int var4)
    {
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, Block.stairsStoneBrick.blockID, var1.getBlockMetadata(var2, var3, var4));
        return true;
    }
}

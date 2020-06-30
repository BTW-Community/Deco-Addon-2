package net.minecraft.src;

public class FCBlockBrickLooseStairs extends FCBlockMortarReceiverStairs
{
    protected FCBlockBrickLooseStairs(int var1)
    {
        super(var1, FCBetterThanWolves.fcBlockBrickLoose, 0);
        this.SetPicksEffectiveOn();
        this.setUnlocalizedName("fcBlockBrickLooseStairs");
    }

    public boolean OnMortarApplied(World var1, int var2, int var3, int var4)
    {
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, Block.stairsBrick.blockID, var1.getBlockMetadata(var2, var3, var4));
        return true;
    }
}

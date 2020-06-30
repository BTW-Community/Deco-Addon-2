package net.minecraft.src;

public class FCBlockNetherBrickLooseStairs extends FCBlockMortarReceiverStairs
{
    protected FCBlockNetherBrickLooseStairs(int var1)
    {
        super(var1, FCBetterThanWolves.fcBlockNetherBrickLoose, 0);
        this.SetPicksEffectiveOn();
        this.setUnlocalizedName("fcBlockNetherBrickLooseStairs");
    }

    public boolean OnMortarApplied(World var1, int var2, int var3, int var4)
    {
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, Block.stairsNetherBrick.blockID, var1.getBlockMetadata(var2, var3, var4));
        return true;
    }
}

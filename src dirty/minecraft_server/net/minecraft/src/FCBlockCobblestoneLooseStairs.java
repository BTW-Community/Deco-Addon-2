package net.minecraft.src;

public class FCBlockCobblestoneLooseStairs extends FCBlockMortarReceiverStairs
{
    protected FCBlockCobblestoneLooseStairs(int var1)
    {
        super(var1, FCBetterThanWolves.fcBlockCobblestoneLoose, 0);
        this.SetPicksEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.setUnlocalizedName("fcBlockCobblestoneLooseStairs");
    }

    public boolean OnMortarApplied(World var1, int var2, int var3, int var4)
    {
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, Block.stairsCobblestone.blockID, var1.getBlockMetadata(var2, var3, var4));
        return true;
    }
}

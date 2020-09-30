package net.minecraft.src;

public class DecoBlockNetherBrickRedLooseStairs extends FCBlockMortarReceiverStairs
{
    protected DecoBlockNetherBrickRedLooseStairs(int var1)
    {
        super(var1, DecoDefs.netherBrickRedLoose, 0);
        this.SetPicksEffectiveOn();
        this.setUnlocalizedName("decoBlockNetherBrickRedStairsLoose");
    }

    public boolean OnMortarApplied(World var1, int var2, int var3, int var4)
    {
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, DecoDefs.netherBrickRedStairs.blockID, var1.getBlockMetadata(var2, var3, var4));
        return true;
    }
}

package net.minecraft.src;

public class AddonBlockNetherBrickRedLooseStairs extends FCBlockMortarReceiverStairs
{
    protected AddonBlockNetherBrickRedLooseStairs(int var1)
    {
        super(var1, AddonDefs.netherBrickLoose, 0);
        this.SetPicksEffectiveOn();
        this.setUnlocalizedName("ginger_netherBrickLooseStairs");
    }

    public boolean OnMortarApplied(World var1, int var2, int var3, int var4)
    {
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, AddonDefs.netherBrickStairs.blockID, var1.getBlockMetadata(var2, var3, var4));
        return true;
    }
}

package net.minecraft.src;

public class FCBlockNetherBrickLoose extends FCBlockMortarReceiver
{
    public FCBlockNetherBrickLoose(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialNetherRock);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("fcBlockNetherBrickLoose");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public boolean OnMortarApplied(World var1, int var2, int var3, int var4)
    {
        var1.setBlockWithNotify(var2, var3, var4, Block.netherBrick.blockID);
        return true;
    }
}

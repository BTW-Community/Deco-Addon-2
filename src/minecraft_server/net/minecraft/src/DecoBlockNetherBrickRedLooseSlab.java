package net.minecraft.src;

public class DecoBlockNetherBrickRedLooseSlab extends FCBlockMortarReceiverSlab
{
    public DecoBlockNetherBrickRedLooseSlab(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialNetherRock);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("decoBlockNetherBrickRedSlabLoose");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public int GetCombinedBlockID(int var1)
    {
        return DecoDefs.netherBrickRedLoose.blockID;
    }

    public boolean OnMortarApplied(World var1, int var2, int var3, int var4)
    {
        int var5 = 7;

        if (this.GetIsUpsideDown(var1, var2, var3, var4))
        {
            var5 |= 8;
        }

        var1.setBlockAndMetadataWithNotify(var2, var3, var4, DecoDefs.stoneSlab.blockID, var5);
        return true;
    }
}

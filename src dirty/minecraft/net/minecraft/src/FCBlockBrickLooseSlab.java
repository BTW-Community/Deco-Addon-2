package net.minecraft.src;

public class FCBlockBrickLooseSlab extends FCBlockMortarReceiverSlab
{
    public FCBlockBrickLooseSlab(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetPicksEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("fcBlockBrickLooseSlab");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public int GetCombinedBlockID(int var1)
    {
        return FCBetterThanWolves.fcBlockBrickLoose.blockID;
    }

    public boolean OnMortarApplied(World var1, int var2, int var3, int var4)
    {
        int var5 = 4;

        if (this.GetIsUpsideDown(var1, var2, var3, var4))
        {
            var5 |= 8;
        }

        var1.setBlockAndMetadataWithNotify(var2, var3, var4, Block.stoneSingleSlab.blockID, var5);
        return true;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("fcBlockBrickLoose");
    }
}

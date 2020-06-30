package net.minecraft.src;

public class FCBlockStoneBrickLooseSlab extends FCBlockMortarReceiverSlab
{
    public FCBlockStoneBrickLooseSlab(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("fcBlockStoneBrickLooseSlab");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public int GetCombinedBlockID(int var1)
    {
        return FCBetterThanWolves.fcBlockStoneBrickLoose.blockID;
    }

    public boolean OnMortarApplied(World var1, int var2, int var3, int var4)
    {
        int var5 = 5;

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
        this.blockIcon = var1.registerIcon("fcBlockStoneBrickLoose");
    }
}

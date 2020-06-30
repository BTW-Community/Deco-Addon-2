package net.minecraft.src;

public class FCBlockStoneBrickLoose extends FCBlockLavaReceiver
{
    private Icon m_iconLavaCracks;

    public FCBlockStoneBrickLoose(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("fcBlockStoneBrickLoose");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public boolean OnMortarApplied(World var1, int var2, int var3, int var4)
    {
        var1.setBlockWithNotify(var2, var3, var4, Block.stoneBrick.blockID);
        return true;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_iconLavaCracks = var1.registerIcon("fcOverlayStoneBrickLava");
    }

    protected Icon GetLavaCracksOverlay()
    {
        return this.m_iconLavaCracks;
    }
}

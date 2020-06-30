package net.minecraft.src;

public class FCBlockCobblestoneLoose extends FCBlockLavaReceiver
{
    public FCBlockCobblestoneLoose(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetPicksEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("fcBlockCobblestoneLoose");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public boolean OnMortarApplied(World var1, int var2, int var3, int var4)
    {
        var1.setBlockWithNotify(var2, var3, var4, Block.cobblestone.blockID);
        return true;
    }
}

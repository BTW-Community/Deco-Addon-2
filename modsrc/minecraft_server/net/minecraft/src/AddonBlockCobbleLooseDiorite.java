package net.minecraft.src;

public class AddonBlockCobbleLooseDiorite extends FCBlockLavaReceiver
{

    public AddonBlockCobbleLooseDiorite(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetPicksEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("ginger_dioriteCobbleLoose");
        this.setCreativeTab(CreativeTabs.tabBlock);
        AddonManager.Register(this, "Loose Diorite Cobblestone");
    }

    public boolean OnMortarApplied(World world, int x, int y, int z)
    {
        world.setBlockAndMetadataWithNotify(x, y, z, AddonDefs.stoneTypesCobble.blockID, 2);
        return true;
    }
}

package net.minecraft.src;

public class AddonBlockCobbleLooseGranite extends FCBlockLavaReceiver
{

    public AddonBlockCobbleLooseGranite(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetPicksEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("ginger_graniteCobbleLoose");
        this.setCreativeTab(CreativeTabs.tabBlock);
        AddonManager.Register(this, "Loose Granite Cobblestone");
    }

    public boolean OnMortarApplied(World world, int x, int y, int z)
    {
        world.setBlockAndMetadataWithNotify(x, y, z, AddonDefs.stoneTypesCobble.blockID, 0);
        return true;
    }
}

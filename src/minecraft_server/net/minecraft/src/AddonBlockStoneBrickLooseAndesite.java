package net.minecraft.src;

public class AddonBlockStoneBrickLooseAndesite extends FCBlockLavaReceiver
{
    public AddonBlockStoneBrickLooseAndesite(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetPicksEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("ginger_andesiteBrickLoose");
        this.setCreativeTab(CreativeTabs.tabBlock);
        AddonManager.Register(this, "Loose Andesite  Brick");
    }

    public boolean OnMortarApplied(World world, int x, int y, int z)
    {
        world.setBlockAndMetadataWithNotify(x, y, z, AddonDefs.stoneTypesStoneBrick.blockID, 1);
        return true;
    }
}

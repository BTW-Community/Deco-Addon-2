package net.minecraft.src;

public class AddonBlockStoneBrickLooseGranite extends FCBlockLavaReceiver
{

    public AddonBlockStoneBrickLooseGranite(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetPicksEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("ginger_graniteBrickLoose");
        this.setCreativeTab(CreativeTabs.tabBlock);
        AddonManager.Register(this, "Loose Granite Brick");
    }

    public boolean OnMortarApplied(World world, int x, int y, int z)
    {
        world.setBlockAndMetadataWithNotify(x, y, z, AddonDefs.stoneTypesStoneBrick.blockID, 0);
        return true;
    }

    //CLIENT ONLY
    private Icon lavaCrackOverlay;
    
    public void registerIcons(IconRegister Register)
    {
    	super.registerIcons(Register);
    	lavaCrackOverlay = Register.registerIcon("ginger_overlay_graniteBrickLava");
    }

    protected Icon GetLavaCracksOverlay()
    {
        return lavaCrackOverlay;
    }
    //
}
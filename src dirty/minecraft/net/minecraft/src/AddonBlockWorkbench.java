package net.minecraft.src;

public class AddonBlockWorkbench extends Block {
	protected AddonBlockWorkbench(int ID) {
		super(ID, FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(1.5F);
        this.SetBuoyant();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.WOOD_BASED_BLOCK);
        this.SetAxesEffectiveOn();
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("workbench");
        this.setCreativeTab(CreativeTabs.tabDecorations);
	}

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
            par5EntityPlayer.displayGUIAnvil(par2, par3, par4);
            return true;
        }
    }
}

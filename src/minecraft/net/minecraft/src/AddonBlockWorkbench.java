package net.minecraft.src;

public class AddonBlockWorkbench extends Block {
	protected AddonBlockWorkbench(int ID) {
		super(ID, FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(1.5F);
        this.SetBuoyant();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.WOOD_BASED_BLOCK);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("workbench");
        this.setCreativeTab(CreativeTabs.tabDecorations);
	}

    //CLIENT ONLY
    private Icon workbenchIconTop;
    private Icon workbenchIconFront;
    
    public Icon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.workbenchIconTop : (par1 == 0 ? Block.planks.getBlockTextureFromSide(par1) : (par1 != 2 && par1 != 4 ? this.blockIcon : this.workbenchIconFront));
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("workbench_side");
        this.workbenchIconTop = par1IconRegister.registerIcon("workbench_top");
        this.workbenchIconFront = par1IconRegister.registerIcon("workbench_front");
    }
    //
}

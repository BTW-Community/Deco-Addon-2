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

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 2, 0, var6);
        return true;
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

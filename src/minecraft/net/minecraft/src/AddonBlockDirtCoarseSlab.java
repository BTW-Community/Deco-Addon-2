package net.minecraft.src;

public class AddonBlockDirtCoarseSlab extends FCBlockSlabAttached {

	protected AddonBlockDirtCoarseSlab(int ID) {
        super(ID, Material.ground);
        this.setHardness(0.5F);
        this.SetShovelsEffectiveOn();
        this.SetHoesEffectiveOn();
        this.setStepSound(soundGravelFootstep);
        this.setUnlocalizedName("ginger_coarseDirtSlab");
        this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileDirt.itemID, 3, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileGravel.itemID, 3, 0, var6);
        return true;
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }

	@Override
	protected void OnAnchorBlockLost(World var1, int var2, int var3, int var4) {
        this.DropComponentItemsOnBadBreak(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 1.0F);
	}

	@Override
	public int GetCombinedBlockID(int var1) {
		return AddonDefs.coarseDirt.blockID;
	}
	
	//CLIENT ONLY
	public Icon getIcon(int Side, int Meta)
	{
		return AddonDefs.coarseDirt.getIcon(Side, Meta);
	}
    
    public void registerIcons(IconRegister register) {}
}

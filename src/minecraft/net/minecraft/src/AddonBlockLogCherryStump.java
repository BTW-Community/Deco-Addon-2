package net.minecraft.src;

public class AddonBlockLogCherryStump extends Block {
	public AddonBlockLogCherryStump(int id) {
		super(id, FCBetterThanWolves.fcMaterialLog);
        this.setHardness(1.25F);
        this.setResistance(3.33F);
        this.SetAxesEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.LOGS);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("cherryLogStump");
	}

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return false;
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return AddonDefs.cherryLog.blockID;
    }
	
	public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
    	return new ItemStack(AddonDefs.cherryLog, 1, 0);
    }

	public boolean GetIsProblemToRemove(IBlockAccess var1, int var2, int var3, int var4)
	{
		return true;
	}

	public boolean GetDoesStumpRemoverWorkOnBlock(IBlockAccess var1, int var2, int var3, int var4)
	{
		return true;
	}

	public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
	{
		return true;
	}

	public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
	{
		int var7 = var2.getBlockMetadata(var3, var4, var5);
		byte var8 = 0;
		int var10;
		
		if (this.IsWorkStumpItemConversionTool(var1, var2, var3, var4, var5))
		{
			var2.playAuxSFX(2268, var3, var4, var5, 0);
			var2.setBlockAndMetadataWithNotify(var3, var4, var5, FCBetterThanWolves.fcBlockWorkStump.blockID, 4);
			return true;
		}

		var10 = FCBetterThanWolves.fcBlockLogDamaged.SetIsStump(var8);

		return true;
	}

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 6, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemBark.itemID, 1, var5 & 3, var6);
        return true;
    }

    public void OnDestroyedByFire(World var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        this.ConvertToSmouldering(var1, var2, var3, var4);
    }

    public void ConvertToSmouldering(World var1, int var2, int var3, int var4)
    {
        int var5 = FCBetterThanWolves.fcBlockLogSmouldering.SetIsStump(0, true);
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockLogSmouldering.blockID, var5);
    }

	public boolean IsWorkStumpItemConversionTool(ItemStack var1, World var2, int var3, int var4, int var5)
	{
		if (var1 != null && var1.getItem() instanceof FCItemChisel)
		{
			int var6 = ((FCItemChisel)var1.getItem()).toolMaterial.getHarvestLevel();
			return var6 >= 2;
		}
		else
		{
			return false;
		}
	}
	
	//CLIENT ONLY
	private Icon sideIcon;
	private Icon topIcon;
	
	@Override
	public Icon getIcon(int side, int meta) {
		return (side == 1 || side == 0) ? topIcon : sideIcon;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		sideIcon = register.registerIcon("ginger_trunkCherrySide");
		topIcon = register.registerIcon("ginger_trunkCherryTop");
	}
}
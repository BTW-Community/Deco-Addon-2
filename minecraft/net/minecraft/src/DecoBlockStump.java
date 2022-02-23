package net.minecraft.src;

public class DecoBlockStump extends Block {
	public int logID;
	public int logMeta;
	public int workStumpID;
	public int workStumpMeta;
	
	public DecoBlockStump(int id, int logID, int logMeta, int workStumpID, int workStumpMeta) {
		super(id, FCBetterThanWolves.fcMaterialLog);
        this.setHardness(1.25F);
        this.setResistance(3.33F);
        this.SetAxesEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.LOGS);
        this.setStepSound(soundWoodFootstep);
        
        this.logID = logID;
        this.logMeta = logMeta;
        this.workStumpID = workStumpID;
        this.workStumpMeta = workStumpMeta;
	}

    @Override
    protected boolean canSilkHarvest() {
        return false;
    }

    @Override
	public ItemStack GetStackRetrievedByBlockDispenser(World world, int x, int y, int z) {
    	return new ItemStack(this.logID, 1, this.logMeta);
    }

    @Override
	public boolean GetIsProblemToRemove(IBlockAccess blockAccess, int x, int y, int z) {
		return true;
	}

    @Override
	public boolean GetDoesStumpRemoverWorkOnBlock(IBlockAccess blockAccess, int x, int y, int z) {
		return true;
	}

    @Override
	public boolean CanConvertBlock(ItemStack stack, World world, int x, int y, int z) {
		return true;
	}

    @Override
	public boolean ConvertBlock(ItemStack stack, World world, int x, int y, int z, int side) {
		int metadata = world.getBlockMetadata(x, y, z);
		int var10;
		
		if (this.isWorkStumpItemConversionTool(stack, world, x, y, z)) {
			world.playAuxSFX(2268, x, y, z, 0);
			world.setBlockAndMetadataWithNotify(x, y, z, FCBetterThanWolves.fcBlockWorkStump.blockID, 4);
			return true;
		}
		else {
			var10 = FCBetterThanWolves.fcBlockLogDamaged.SetIsStump(0);

			return true;
		}
	}

    @Override
    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 6, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemBark.itemID, 1, 5, var6);
        return true;
    }

    @Override
    public void OnDestroyedByFire(World var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        this.convertToSmouldering(var1, var2, var3, var4);
    }
    
    //------------- Class Specific Methods ------------//

    public void convertToSmouldering(World var1, int var2, int var3, int var4)
    {
        int var5 = FCBetterThanWolves.fcBlockLogSmouldering.SetIsStump(0, true);
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockLogSmouldering.blockID, var5);
    }

	public boolean isWorkStumpItemConversionTool(ItemStack var1, World var2, int var3, int var4, int var5)
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
	
	//------ Client side functionality ------//
	
	private Icon sideIcon;
	private Icon topIcon;
	
	@Override
	public Icon getIcon(int side, int meta) {
		return (side == 1 || side == 0) ? topIcon : sideIcon;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		sideIcon = register.registerIcon("decoBlockTrunkCherry_side");
		topIcon = register.registerIcon("decoBlockTrunkCherry_top");
	}

    @Override
    public int idPicked(World world, int x, int y, int z) {
        return this.logID;
    }
}
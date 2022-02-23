package net.minecraft.src;

public class DecoBlockLogBase extends FCBlockDirectional {
	private boolean[] hasBark;
	private int[] barkMetas;
	private int[] damagedIDs;
	
	public DecoBlockLogBase(int id, boolean[] hasBark, int[] barkMetas, int[] damagedIDs, String[] topTextures, String[] sideTextures) {
		super(id, FCBetterThanWolves.fcMaterialLog, topTextures, sideTextures);
		
		this.setHardness(1.25F);
		this.setResistance(3.33F);
		
		this.SetAxesEffectiveOn();
		this.SetChiselsEffectiveOn();
		
		this.SetBuoyant();
		
		this.SetFireProperties(FCEnumFlammability.LOGS);
		
		this.setStepSound(soundWoodFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		
		this.hasBark = hasBark;
		this.barkMetas = barkMetas;
		this.damagedIDs = damagedIDs;
		
	}
    
    @Override
    public boolean CanConvertBlock(ItemStack stack, World world, int x, int y, int z) {
    	return true;
    }

	public boolean ConvertBlock(ItemStack stack, World world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);

		int type = meta & 3;
		int orientation = meta >> 2 & 3;
		
		int newMeta = FCBetterThanWolves.fcBlockLogDamaged.SetOrientation(0, orientation);

		world.setBlockAndMetadataWithNotify(x, y, z, damagedIDs[type], newMeta);

		if (!world.isRemote && hasBark[type]) {
			FCUtilsItem.EjectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(FCBetterThanWolves.fcItemBark, 1, barkMetas[type]), side);
		}

		return true;
	}
    
    @Override
    public boolean GetCanBlockBeIncinerated(World world, int x, int y, int z) {
    	return true;
    }
    
	@Override
	public boolean DropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		DropItemsIndividualy(world, x, y, z, FCBetterThanWolves.fcItemSawDust.itemID, 6, 0, chanceOfDrop);
		DropItemsIndividualy(world, x, y, z, FCBetterThanWolves.fcItemBark.itemID, 1, metadata & 3, chanceOfDrop);
		
		return true;
	}
	
	@Override
    public void OnDestroyedByFire(World world, int x, int y, int z, int fireAge, boolean forcedFireSpread) {
		convertToSmouldering(world, x, y, z);
    }
    
    @Override
    public int GetFurnaceBurnTime(int itemDamage) {
    	return FCBlockPlanks.GetFurnaceBurnTimeByWoodType(itemDamage) * 4;
    }
    
    //------------- Class Specific Methods ------------//
	
	public void convertToSmouldering(World world, int x, int y, int z) {
		int newMetadata = FCBetterThanWolves.fcBlockLogSmouldering.SetIsStump(0, false);
		world.setBlockAndMetadataWithNotify(x, y, z, FCBetterThanWolves.fcBlockLogSmouldering.blockID, newMetadata);
	}
}
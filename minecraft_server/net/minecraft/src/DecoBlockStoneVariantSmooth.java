package net.minecraft.src;

public class DecoBlockStoneVariantSmooth extends Block{
	public DecoBlockStoneVariantSmooth(int ID) {
		super(ID, Material.rock);
		this.setHardness(2.25F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.SetChiselsEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("decoBlockStoneSmooth");
		this.setCreativeTab(CreativeTabs.tabBlock);

		DecoManager.register(this, new String[] {"granite", "andesite", "diorite", "slate"});
	}

	public int damageDropped(int Meta)
	{
		return Meta;
	}

	public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
	{
		return 3;
	}

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        var1.playAuxSFX(2272, var3, var4, var5, this.blockID + (var6 << 12));
        
        ItemStack curItem = var2.getCurrentEquippedItem();
        
        if (curItem != null && (curItem.itemID == Item.pickaxeStone.itemID || curItem.itemID == Item.pickaxeIron.itemID)) {
        	this.DropComponentItemsOnBadBreak(var1, var3, var4, var5, var6, 1.0F);
        }
    }

	public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
	{
		if (var5 == 0) {
			this.DropItemsIndividualy(var1, var2, var3, var4, DecoDefs.graniteCobbleLoose.blockID, 1, 0, var6);
		}
		else if (var5 == 1) {
			this.DropItemsIndividualy(var1, var2, var3, var4, DecoDefs.andesiteCobbleLoose.blockID, 1, 0, var6);
		}
		else if (var5 == 2) {
			this.DropItemsIndividualy(var1, var2, var3, var4, DecoDefs.dioriteCobbleLoose.blockID, 1, 0, var6);
		}
		else if (var5 == 2) {
			this.DropItemsIndividualy(var1, var2, var3, var4, DecoDefs.slateCobbleLoose.blockID, 1, 0, var6);
		}
		return true;
	}

	@Override
    public int getItemIDDroppedOnStonecutter(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        
        switch (meta) {
        default:
        case 0:
        	return DecoDefs.stoneTypesSmoothSidingAndCorner[0].blockID;
        case 1:
        	return DecoDefs.stoneTypesSmoothSidingAndCorner[1].blockID;
        case 2:
        	return DecoDefs.stoneTypesSmoothSidingAndCorner[2].blockID;
        case 3:
        	return DecoDefs.slateSmoothSidingAndCorner.blockID;
        }
    }

	@Override
    public int getItemCountDroppedOnStonecutter(World world, int x, int y, int z) {
        return 2;
    }

	@Override
    public int getItemDamageDroppedOnStonecutter(World world, int x, int y, int z)  {
        return 0;
    }
}

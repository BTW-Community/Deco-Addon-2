package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockStoneVariants extends Block {
	public DecoBlockStoneVariants(int ID) {
		super(ID, Material.rock);
		this.setHardness(2.25F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.SetChiselsEffectiveOn(false);
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("decoBlockStone");
		this.setCreativeTab(CreativeTabs.tabBlock);

		DecoManager.register(this, new String[] {"granite", "andesite", "diorite"});
	}

	public int damageDropped(int meta) {
		return meta;
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
		
		return true;
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	public void getSubBlocks(int var1, CreativeTabs var2, List var3)
	{
		var3.add(new ItemStack(var1, 1, 0));
		var3.add(new ItemStack(var1, 1, 1));
		var3.add(new ItemStack(var1, 1, 2));
	}

	/**
	 * Get the block's damage value (for use with pick block).
	 */
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	public boolean IsNaturalStone(IBlockAccess blockAccess, int i, int j, int k)
	{
		return true;
	}

	@Override
    public int getItemIDDroppedOnStonecutter(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        
        switch (meta) {
        default:
        case 0:
        	return DecoDefs.stoneTypesSidingAndCorner[0].blockID;
        case 1:
        	return DecoDefs.stoneTypesSidingAndCorner[1].blockID;
        case 2:
        	return DecoDefs.stoneTypesSidingAndCorner[2].blockID;
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

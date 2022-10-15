package deco.block.blocks;

import btw.item.items.ShearsItem;
import com.prupe.mcpatcher.cc.ColorizeBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class DoubleTallGrassBlock extends TallPlantBlock {
	public static final int GRASS_TYPE = 0;
	public static final int FERN_TYPE = 1;
	
	public DoubleTallGrassBlock(int blockID) {
		super(blockID, "decoBlockGrass",
				new String [] {
						"grass",
						"fern"
				});
	}
	
	@Override
	public int idDropped(int metadata, Random rand, int fortunateModifier) {
		return 0;
	}
	
	@Override
	public boolean canSpitWebReplaceBlock(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public boolean isReplaceableVegetation(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		if (!world.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() instanceof ShearsItem) {
			player.addStat(StatList.mineBlockStatArray[this.blockID], 1);
			player.getHeldItem().damageItem(1, player);
			
			if (!isTopBlock(metadata)) {
				this.dropBlockAsItem_do(world, x, y, z, new ItemStack(this.blockID, 1, metadata & 7));
			}
		}
		else {
			super.harvestBlock(world, player, x, y, z, metadata);
		}
	}
	
	@Override
	public int getHerbivoreItemFoodValue(int itemDamage) {
		return Item.BASE_HERBIVORE_ITEM_FOOD_VALUE / 2;
	}
	
	@Override
	public float getHalfWidth() {
		return 0.4F;
	}
	
	@Override
	public float getHeight() {
		return 0.8F;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public int getBlockColor() {
		if (ColorizeBlock.colorizeBlock(this)) {
			return ColorizeBlock.blockColor;
		}
		else {
			double var1 = 0.5D;
			double var3 = 1.0D;
			return ColorizerGrass.getGrassColor(var1, var3);
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public int getRenderColor(int metadata) {
		if (ColorizeBlock.colorizeBlock(this, metadata)) {
			return ColorizeBlock.blockColor;
		}
		else {
			return ColorizerFoliage.getFoliageColorBasic();
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
		if (ColorizeBlock.colorizeBlock(this, blockAccess, x, y, z)) {
			return ColorizeBlock.blockColor;
		}
		else {
			return blockAccess.getBiomeGenForCoords(x, z).getBiomeGrassColor();
		}
	}
}

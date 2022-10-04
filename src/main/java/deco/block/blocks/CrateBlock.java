package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.util.Flammability;
import btw.item.BTWItems;
import deco.block.util.WoodTypeHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;

public class CrateBlock extends Block {
	public CrateBlock(int blockID) {
		super(blockID, BTWBlocks.plankMaterial);
		this.setAxesEffectiveOn();
		this.setHardness(1.0F);
		this.setResistance(5.0F);
		this.setFireProperties(Flammability.PLANKS);
		this.setBuoyant();
		this.setStepSound(soundWoodFootstep);
		this.setUnlocalizedName("decoBlockCrate");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public int getHarvestToolLevel(IBlockAccess blockAccess, int x, int y, int z) {
		return 2;
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.sawDust.itemID, 2, 0, chanceOfDrop);
		return true;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] iconArray;
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		return iconArray[meta];
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		iconArray = new Icon[8];
		
		iconArray[0] = register.registerIcon("decoBlockCrateOak");
		iconArray[1] = register.registerIcon("decoBlockCrateSpruce");
		iconArray[2] = register.registerIcon("decoBlockCrateBirch");
		iconArray[3] = register.registerIcon("decoBlockCrateJungle");
		iconArray[4] = register.registerIcon("decoBlockCrateBlood");
		iconArray[5] = register.registerIcon("decoBlockCrateCherry");
		iconArray[6] = register.registerIcon("decoBlockCrateAcacia");
		iconArray[7] = register.registerIcon("decoBlockCrateMahogany");
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < WoodTypeHelper.NUM_TOTAL_WOOD; i++) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}
}

package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.PillarBlock;
import btw.block.util.Flammability;
import btw.item.BTWItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

import java.util.List;

public class BarrelBlock extends PillarBlock {
	public BarrelBlock(int id, String[] topTextures, String[] sideTextures) {
		super(id, BTWBlocks.plankMaterial, topTextures, sideTextures);
		this.setAxesEffectiveOn();
		this.setHardness(1.0F);
		this.setResistance(5.0F);
		this.setFireProperties(Flammability.PLANKS);
		this.setBuoyant();
		this.setStepSound(soundWoodFootstep);
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
	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < topTextures.length; i++) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}
}

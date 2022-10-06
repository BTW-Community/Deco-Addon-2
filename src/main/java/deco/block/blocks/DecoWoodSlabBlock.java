package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.util.Flammability;
import btw.item.BTWItems;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.World;

public class DecoWoodSlabBlock extends DecoSlabBlock {
	public DecoWoodSlabBlock(int id, Block[] blocks, int[] metas) {
		super(id, BTWBlocks.plankMaterial, blocks, metas);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1.5F);
		this.setResistance(5.0F);
		this.setAxesEffectiveOn();
		this.setStepSound(Block.soundWoodFootstep);
		this.setFireProperties(Flammability.PLANKS);
		this.setBuoyant();
	}
	
	@Override
	public int getHarvestToolLevel(IBlockAccess blockAccess, int x, int y, int z) {
		return 2;
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.sawDust.itemID, 1, 0, chanceOfDrop);
		return true;
	}
}
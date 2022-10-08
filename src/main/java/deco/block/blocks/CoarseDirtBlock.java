package deco.block.blocks;

import btw.item.BTWItems;
import deco.block.DecoBlocks;
import net.minecraft.src.*;

public class CoarseDirtBlock extends Block {
	public CoarseDirtBlock(int blockID) {
		super(blockID, Material.ground);
		this.setHardness(0.5F);
		this.setShovelsEffectiveOn();
		this.setHoesEffectiveOn();
		this.setStepSound(soundGravelFootstep);
		this.setUnlocalizedName("decoBlockCoarseDirt");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int side, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.dirtPile.itemID, 3, 0, chanceOfDrop);
		this.dropItemsIndividually(world, x, y, z, BTWItems.gravelPile.itemID, 3, 0, chanceOfDrop);
		return true;
	}
	
	@Override
	public boolean canBePistonShoveled(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public boolean canReedsGrowOnBlock(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public boolean canWildVegetationGrowOnBlock(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public boolean canSaplingsGrowOnBlock(World world, int x, int y, int z) {
		return true;
	}
}

package deco.block.blocks;

import btw.block.blocks.FallingFullBlock;
import btw.item.BTWItems;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class MudBlock extends FallingFullBlock {
	public MudBlock(int blockID) {
		super(blockID, Material.ground);
		this.setHardness(0.5F);
		this.setShovelsEffectiveOn();
		this.setStepSound(soundGravelFootstep);
		this.setUnlocalizedName("decoBlockMud");
	}
	
	@Override
	public float getMovementModifier(World world, int x, int y, int z) {
		return 0.8F;
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.dirtPile.itemID, 6, 0, chanceOfDrop);
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

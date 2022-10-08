package deco.block.blocks;

import btw.block.blocks.AttachedSlabBlock;
import btw.item.BTWItems;
import deco.block.DecoBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class CoarseDirtSlabBlock extends AttachedSlabBlock implements SlabInterface {
	public CoarseDirtSlabBlock(int blockID) {
		super(blockID, Material.ground);
		this.setHardness(0.5F);
		this.setShovelsEffectiveOn();
		this.setHoesEffectiveOn();
		this.setStepSound(soundGravelFootstep);
		this.setUnlocalizedName("decoBlockCoarseDirtSlab");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int side, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.dirtPile.itemID, 2, 0, chanceOfDrop);
		this.dropItemsIndividually(world, x, y, z, BTWItems.gravelPile.itemID, 1, 0, chanceOfDrop);
		return true;
	}
	
	@Override
	public boolean canBePistonShoveled(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	protected void onAnchorBlockLost(World world, int x, int y, int z) {
		this.dropComponentItemsOnBadBreak(world, x, y, z, world.getBlockMetadata(x, y, z), 1);
		world.setBlockToAir(x, y, z);
	}
	
	@Override
	public int getCombinedBlockID(int iMetadata) {
		return DecoBlocks.coarseDirt.blockID;
	}
	
	@Override
	public int getNumTypes() {
		return 1;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		return DecoBlocks.coarseDirt.getIcon(side, metadata);
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {}
}

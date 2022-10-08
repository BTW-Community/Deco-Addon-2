package deco.block.blocks;

import btw.block.blocks.FallingSlabBlock;
import btw.item.BTWItems;
import deco.block.util.SandHelper;
import net.minecraft.src.*;

public class RedSandSlabBlock extends FallingSlabBlock implements SlabInterface {
	public RedSandSlabBlock(int blockID) {
		super(blockID, Material.sand);
		this.setHardness(0.5F);
		this.setShovelsEffectiveOn(true);
		this.setStepSound(soundSandFootstep);
		this.setUnlocalizedName("decoBlockRedSandSlab");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int side, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.sandPile.itemID, 3, SandHelper.RED_SAND_TYPE, chanceOfDrop);
		return true;
	}
	
	@Override
	public float getMovementModifier(World world, int x, int y, int z) {
		return 0.8F;
	}
	
	@Override
	public int getCombinedBlockID(int metadata) {
		return Block.sand.blockID;
	}
	
	@Override
	public int getCombinedMetadata(int metadata) {
		return SandHelper.RED_SAND_TYPE;
	}
	
	@Override
	public int getNumTypes() {
		return 1;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Override
	public Icon getIcon(int side, int metadata) {
		return Block.sand.getIcon(side, SandHelper.RED_SAND_TYPE);
	}
	
	@Override
	public void registerIcons(IconRegister register) {}
}

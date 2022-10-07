package deco.block.blocks.legacy;

import btw.block.blocks.FallingFullBlock;
import btw.item.BTWItems;
import deco.block.util.SandHelper;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;

import java.util.Random;

public class LegacyRedSandBlock extends FallingFullBlock {
	public LegacyRedSandBlock(int blockID) {
		super(blockID, Material.sand);
        this.setHardness(0.5F);
        this.setShovelsEffectiveOn();
        this.setFilterableProperties(8);
        this.setStepSound(soundSandFootstep);
        this.setUnlocalizedName("decoBlockRedSand");
	}
	
	@Override
	public int idDropped(int metadata, Random random, int par3) {
		return Block.sand.blockID;
	}
	
	@Override
	public int damageDropped(int metadata) {
		return SandHelper.RED_SAND_TYPE;
	}
	
	@Override
	public float getMovementModifier(World world, int x, int y, int z) {
		return 0.8F;
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.sandPile.itemID, 6, SandHelper.RED_SAND_TYPE, chanceOfDrop);
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
	public boolean canCactusGrowOnBlock(World world, int x, int y, int z) {
		return true;
	}
}

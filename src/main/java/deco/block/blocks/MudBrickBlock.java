package deco.block.blocks;

import btw.item.BTWItems;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class MudBrickBlock extends Block {
	public MudBrickBlock(int blockID) {
		super(blockID, Material.ground);
		this.setHardness(1.0F);
		this.setShovelsEffectiveOn();
		this.setPicksEffectiveOn();
		this.setStepSound(soundGravelFootstep);
		this.setUnlocalizedName("decoBlockMudBrick");
	}
	
	@Override
	public float getMovementModifier(World world, int x, int y, int z) {
		return 1.2F;
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.dirtPile.itemID, 12, 0, chanceOfDrop);
		return true;
	}
}

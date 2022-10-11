package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.MortarReceiverBlock;
import deco.block.DecoBlocks;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.World;

public class LooseRedNetherBrickBlock extends MortarReceiverBlock {
	public LooseRedNetherBrickBlock(int blockID) {
		super(blockID, BTWBlocks.netherRockMaterial);
		this.setHardness(1.0F);
		this.setResistance(5.0F);
		this.setPicksEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("decoBlockNetherBrickRedLoose");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public boolean onMortarApplied(World world, int x, int y, int z) {
		world.setBlockWithNotify(x, y, z, DecoBlocks.netherBrick.blockID);
		return true;
	}
}

package deco.block.blocks;

import deco.block.DecoBlocks;
import net.minecraft.src.*;

import java.util.Random;

public class SlateTilesBlock extends Block {
	public SlateTilesBlock(int blockID) {
		super(blockID, Material.rock);
		this.setHardness(2.25F);
		this.setResistance(10.0F);
		this.setPicksEffectiveOn();
		this.setChiselsEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("decoBlockStoneBricks");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void onBlockDestroyedWithImproperTool(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		dropBlockAsItem(world, x, y, z, metadata, 0);
	}
	
	@Override
	public int idDropped(int metadata, Random random, int fortuneModifier) {
		return DecoBlocks.looseSlateBrick.blockID;
	}
	
	@Override
	public boolean hasMortar(IBlockAccess blockAccess, int x, int y, int z) {
		return true;
	}
}

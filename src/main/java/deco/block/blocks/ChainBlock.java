package deco.block.blocks;

import btw.block.model.BlockModel;
import deco.block.model.ChainBlockModel;
import deco.item.DecoItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class ChainBlock extends Block {
	public final BlockModel blockModel = new ChainBlockModel();
	
	public ChainBlock(int blockID) {
		super(blockID, Material.iron);
		this.setUnlocalizedName("decoBlockChain");
		this.setStepSound(Block.soundMetalFootstep);
		this.setHardness(0.5F);
		this.setPicksEffectiveOn(true);
		this.initBlockBounds(.4375D, 0.0D, .4375D, .5625D, 1.0D, .5625D);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean hasSmallCenterHardPointToFacing(IBlockAccess blockAccess, int x, int y, int z, int side, boolean ignoreTransparency) {
		return side == 0 || side == 1;
	}
	
	@Override
	public int idDropped(int metadata, Random rand, int fortuneModifier) {
		return DecoItems.chain.itemID;
	}
	
	@Override
	protected boolean canSilkHarvest() {
		return false;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean renderBlock(RenderBlocks renderBlocks, int x, int y, int z) {
		BlockModel var6 = this.blockModel.makeTemporaryCopy();
		return var6.renderAsBlock(renderBlocks, this, x, y, z);
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public int idPicked(World world, int x, int y, int z) {
		return DecoItems.chain.itemID;
	}
}

package deco.block.blocks;

import btw.block.blocks.StairsBlock;
import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.World;

import java.util.Random;

public class MortaredStairsBlock extends StairsBlock {
	private int looseID;
	
	public MortaredStairsBlock(int blockID, Block referenceBlock, int referenceBlockMetadata, int looseID) {
		super(blockID, referenceBlock, referenceBlockMetadata);
		this.looseID = looseID;
	}
	
	@Override
	public void onBlockDestroyedWithImproperTool(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		dropBlockAsItem(world, x, y, z, metadata, 0);
	}
	
	@Override
	public int idDropped(int metadata, Random random, int fortuneModifier) {
		return this.looseID;
	}
	
	@Override
	public boolean hasMortar(IBlockAccess blockAccess, int x, int y, int z) {
		return true;
	}
}

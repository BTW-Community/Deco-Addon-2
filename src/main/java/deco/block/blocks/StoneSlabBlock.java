package deco.block.blocks;

import net.minecraft.src.*;

public class StoneSlabBlock extends DecoSlabBlock {
	public final boolean[] mortaredList;
	public final Block[] looseDropList;
	public final int[] looseDropMetaList;
	
	public StoneSlabBlock(int id, Block[] blocks, int[] metas) {
		this(id, blocks, metas, new boolean[]{false, false, false, false, false, false, false, false},
				new Block[]{null, null, null, null, null, null, null, null}, new int[]{0, 0, 0, 0, 0, 0, 0, 0});
	}
	
	public StoneSlabBlock(int blockID, Block[] blocks, int[] metas, boolean[] mortared, Block[] looseDrop, int[] looseDropMeta) {
		super(blockID, Material.rock, blocks, metas);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setPicksEffectiveOn();
		this.setStepSound(Block.soundStoneFootstep);
		
		mortaredList = mortared;
		looseDropList = looseDrop;
		looseDropMetaList = looseDropMeta;
	}
	
	@Override
	public int getHarvestToolLevel(IBlockAccess blockAccess, int x, int y, int z) {
		int meta = blockAccess.getBlockMetadata(x, y, z);
		Block owner = blockTypes[meta % 8];
		return 1;
		//return (owner instanceof DecoBlockSandStone || owner instanceof DecoBlockRedSandStone) ? 3 : 1;
	}
	
	@Override
	public boolean hasMortar(IBlockAccess blockAccess, int x, int y, int z) {
		int meta = blockAccess.getBlockMetadata(x, y, z);
		return mortaredList[meta % 8];
	}
	
	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chanceOfDrop, int fortuneModifier) {
		if (!world.isRemote) {
			Block drop = this;
			int dropMeta = metadata % 8;
			
			if (looseDropList[metadata % 8] != null) {
				drop = looseDropList[metadata % 8];
				dropMeta = looseDropMetaList[metadata % 8];
			}
			
			this.dropBlockAsItem_do(world, x, y, z, new ItemStack(drop, 1, dropMeta));
		}
	}
}

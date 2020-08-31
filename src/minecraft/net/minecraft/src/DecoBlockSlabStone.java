package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockSlabStone extends DecoBlockSlabBase
{
	/** The list of the types of step blocks. */
	public final boolean[] mortaredList;
	public final Block[] looseDropList;
	public final int[] looseDropMetaList;
	private Icon theIcon;

	public DecoBlockSlabStone(int par1, Block[] blocks, int[] metas, boolean[] mortared, Block[] looseDrop, int[] looseDropMeta)
	{
		super(par1, Material.rock, blocks, metas);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.setStepSound(Block.soundStoneFootstep);
		mortaredList = mortared;
		looseDropList = looseDrop;
		looseDropMetaList = looseDropMeta;
	}

	public DecoBlockSlabStone(int par1, Block[] blocks, int[] metas)
	{
		this(par1, blocks, metas, new boolean[] {false, false, false, false, false, false, false, false}, new Block[] {null, null, null, null, null, null, null, null}, new int[] {0, 0, 0, 0, 0, 0, 0, 0});
	}

	public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4) {
    	int meta = var1.getBlockMetadata(var2, var3, var4);
    	Block owner = blockTypes[meta % 8];
    	
    	return (owner instanceof DecoBlockSandStone || owner instanceof DecoBlockRedSandStone) ? 3 : 1;
	}

    public boolean HasMortar(IBlockAccess var1, int var2, int var3, int var4)
    {
    	int meta = var1.getBlockMetadata(var2, var3, var4);
        return mortaredList[meta % 8];
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        if (!var1.isRemote)
        {
        	Block drop = this;
        	int dropMeta = var5 % 8;
        	
        	if (looseDropList[var5 % 8] != null) {
        		drop = looseDropList[var5 % 8];
        		dropMeta = looseDropMetaList[var5 % 8];
        	}
        	
            this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(drop, 1, dropMeta));
        }
    }
}
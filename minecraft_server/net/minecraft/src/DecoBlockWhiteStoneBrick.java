package net.minecraft.src;

import java.util.Random;

public class DecoBlockWhiteStoneBrick extends Block {
	public static final int typeDefault = 0;
	public static final int typeMossy = 1;
	public static final int typeCracked = 2;
	public static final int typeChiseled = 3;
	
	public DecoBlockWhiteStoneBrick(int ID)
	{
		super(ID, Material.rock);
		setHardness(1.5F);
		setResistance(10.0F);
		setStepSound(Block.soundStoneFootstep);
		this.SetPicksEffectiveOn(true);
		setCreativeTab(CreativeTabs.tabBlock);
        this.setTickRandomly(true);
	}
	
	public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (var6 == 0 && !var1.getBlockMaterial(var2, var3 - 1, var4).blocksMovement())
        {
            int var7 = var1.getBlockId(var2, var3 + 1, var4);

            if (var7 != Block.waterMoving.blockID && var7 != Block.waterStill.blockID)
            {
                if ((var7 == Block.lavaMoving.blockID || var7 == Block.lavaStill.blockID) && var5.nextInt(15) == 0)
                {
                    var1.setBlockMetadataWithNotify(var2, var3, var4, 2);
                    var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
                }
            }
            else if (var5.nextInt(15) == 0)
            {
                var1.setBlockMetadataWithNotify(var2, var3, var4, 1);
                var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
            }
        }
    }
	
	public int damageDropped(int Meta)
	{
		return Meta;
	}
}

package net.minecraft.src;

import java.util.Random;

public class FCBlockStoneBrick extends BlockStoneBrick
{
    public FCBlockStoneBrick(int var1)
    {
        super(var1);
        this.setHardness(2.25F);
        this.setResistance(10.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("stonebricksmooth");
        this.setTickRandomly(true);
    }

    /**
     * Ticks the block if it's been scheduled
     */
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

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcBlockStoneBrickLoose.blockID;
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        this.dropBlockAsItem(var1, var3, var4, var5, var6, 0);
    }

    public boolean HasMortar(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }
}

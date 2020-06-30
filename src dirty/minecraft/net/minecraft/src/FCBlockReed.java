package net.minecraft.src;

import java.util.Random;

public class FCBlockReed extends BlockReed
{
    private static final double m_dWidth = 0.75D;
    private static final double m_dHalfWidth = 0.375D;

    protected FCBlockReed(int var1)
    {
        super(var1);
        this.InitBlockBounds(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var1.provider.dimensionId != 1 && var5.nextInt(2) == 0 && var1.isAirBlock(var2, var3 + 1, var4))
        {
            int var6;

            for (var6 = 1; var1.getBlockId(var2, var3 - var6, var4) == this.blockID; ++var6)
            {
                ;
            }

            if (var6 < 3)
            {
                int var7 = var1.getBlockMetadata(var2, var3, var4);

                if (var7 == 15)
                {
                    var1.setBlock(var2, var3 + 1, var4, this.blockID);
                    var1.SetBlockMetadataWithNotify(var2, var3, var4, 0, 4);
                }
                else
                {
                    var1.SetBlockMetadataWithNotify(var2, var3, var4, var7 + 1, 4);
                }
            }
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3 - 1, var4);
        Block var6 = Block.blocksList[var5];
        return var5 == this.blockID || var6 != null && var6.CanReedsGrowOnBlock(var1, var2, var3 - 1, var4) && var6.IsConsideredNeighbouringWaterForReedGrowthOn(var1, var2, var3 - 1, var4);
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        if (var5.IsAffectedByMovementModifiers() && var5.onGround)
        {
            var5.motionX *= 0.8D;
            var5.motionZ *= 0.8D;
        }
    }

    public boolean DoesBlockDropAsItemOnSaw(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetPreventsFluidFlow(World var1, int var2, int var3, int var4, Block var5)
    {
        return true;
    }

    public boolean CanBeGrazedOn(IBlockAccess var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        return var5.CanGrazeOnRoughVegetation();
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return var1.renderCrossedSquares(this, var2, var3, var4);
    }
}

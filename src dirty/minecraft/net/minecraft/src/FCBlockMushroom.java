package net.minecraft.src;

import java.util.Random;

public class FCBlockMushroom extends BlockMushroom
{
    protected FCBlockMushroom(int var1, String var2)
    {
        super(var1, var2);
        this.InitBlockBounds(0.3D, 0.0D, 0.3D, 0.7D, 0.4D, 0.7D);
        this.setCreativeTab((CreativeTabs)null);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var1.provider.dimensionId != 1)
        {
            if (var1.getBlockId(var2, var3 - 1, var4) == Block.mycelium.blockID && var5.nextInt(50) == 0)
            {
                this.fertilizeMushroom(var1, var2, var3, var4, var5);
            }
            else
            {
                super.updateTick(var1, var2, var3, var4, var5);
            }
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);
        return (var5 == 0 || blocksList[var5].blockMaterial.isReplaceable()) && this.canBlockStay(var1, var2, var3, var4);
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3 - 1, var4);
        return var5 == Block.mycelium.blockID || var1.getFullBlockLightValue(var2, var3, var4) < 13 && this.CanGrowOnBlock(var1, var2, var3 - 1, var4);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcItemMushroomRed.itemID;
    }

    protected boolean CanGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3, var4);
    }

    public boolean CanBeCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        return true;
    }
}

package net.minecraft.src;

import java.util.Random;

public class FCBlockCandle extends Block
{
    private static final double m_dStickHeight = 0.375D;
    private static final double m_dStickWidth = 0.125D;
    private static final double m_dStickHalfWidth = 0.0625D;
    private static final double m_dWickHeight = 0.0625D;
    private static final double m_dWickWidth = 0.03125D;
    private static final double m_dWickHalfWidth = 0.015625D;

    public FCBlockCandle(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialCandle);
        this.setHardness(0.0F);
        this.SetPicksEffectiveOn(true);
        this.SetAxesEffectiveOn(true);
        this.setLightValue(1.0F);
        this.InitBlockBounds(0.4375D, 0.0D, 0.4375D, 0.5625D, 0.375D, 0.5625D);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("fcBlockCandle");
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3 - 1, var4);
        int var6 = var1.getBlockMetadata(var2, var3 - 1, var4);
        return var5 == FCBetterThanWolves.fcAestheticNonOpaque.blockID && var6 == 12 ? true : FCUtilsWorld.DoesBlockHaveSmallCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return null;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcItemCandle.itemID;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return var1;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!this.canPlaceBlockAt(var1, var2, var3, var4))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    public boolean IsBlockRestingOnThatBelow(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetCanBlockLightItemOnFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public void OnNeighborDisrupted(World var1, int var2, int var3, int var4, int var5)
    {
        if (var5 == 0)
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }
}

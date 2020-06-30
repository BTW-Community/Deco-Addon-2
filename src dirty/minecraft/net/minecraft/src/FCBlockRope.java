package net.minecraft.src;

import java.util.Random;

public class FCBlockRope extends Block
{
    public static final double m_dRopeWidth = 0.125D;
    public static final double m_dRopeHalfWidth = 0.0625D;

    protected FCBlockRope(int var1)
    {
        super(var1, Material.circuits);
        this.setHardness(0.5F);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.InitBlockBounds(0.4375D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D);
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("fcBlockRope");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcItemRope.itemID;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockId(var2, var3 + 1, var4);
        boolean var7 = true;

        if (var6 == FCBetterThanWolves.fcAnchor.blockID)
        {
            int var8 = ((FCBlockAnchor)FCBetterThanWolves.fcAnchor).GetFacing(var1, var2, var3 + 1, var4);

            if (var8 == 1)
            {
                var7 = false;
            }
        }
        else if (var6 != this.blockID && var6 != FCBetterThanWolves.fcPulley.blockID)
        {
            var7 = false;
        }

        if (!var7)
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3 + 1, var4);
        return var5 == this.blockID || var5 == FCBetterThanWolves.fcAnchor.blockID;
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

    public boolean IsBlockClimbable(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean HasSmallCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var5 == 0;
    }

    public void BreakRope(World var1, int var2, int var3, int var4)
    {
        FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcItemRope.itemID, 0);
        var1.playAuxSFX(2001, var2, var3, var4, this.blockID);
        var1.setBlockWithNotify(var2, var3, var4, 0);
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World var1, int var2, int var3, int var4)
    {
        return this.idDropped(var1.getBlockMetadata(var2, var3, var4), var1.rand, 0);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        if (var5 >= 2)
        {
            return true;
        }
        else
        {
            int var6 = var1.getBlockId(var2, var3, var4);
            return var6 != this.blockID && super.shouldSideBeRendered(var1, var2, var3, var4, var5);
        }
    }
}

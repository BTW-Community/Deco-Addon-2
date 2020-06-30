package net.minecraft.src;

import java.util.Random;

public class FCBlockLegacyCorner extends Block
{
    public static final int m_iNumSubtypes = 16;
    public static final int m_iStoneTextureID = 1;
    public static final int m_iWoodTextureID = 4;
    private static final double m_dCornerWidth = 0.5D;
    private static final double m_dHalfCornerWidth = 0.25D;
    private static final double fCornerWidthOffset = 0.5D;
    public Icon m_IconWood;

    protected FCBlockLegacyCorner(int var1)
    {
        super(var1, Material.wood);
        this.setHardness(1.5F);
        this.SetAxesEffectiveOn(true);
        this.SetPicksEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.InitBlockBounds(0.25D, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockCorner");
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
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return (var1 & 8) == 0 ? FCBetterThanWolves.fcBlockWoodCornerItemStubID : FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner.blockID;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return (var1 & 8) != 0 ? 1 : 0;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        double var5 = 0.0D;
        double var7 = var5 + 0.5D;
        double var9 = 0.0D;
        double var11 = var9 + 0.5D;
        double var13 = 0.0D;
        double var15 = var13 + 0.5D;

        if (this.IsIOffset(var1, var2, var3, var4))
        {
            var5 += 0.5D;
            var7 += 0.5D;
        }

        if (this.IsJOffset(var1, var2, var3, var4))
        {
            var9 += 0.5D;
            var11 += 0.5D;
        }

        if (this.IsKOffset(var1, var2, var3, var4))
        {
            var13 += 0.5D;
            var15 += 0.5D;
        }

        return AxisAlignedBB.getAABBPool().getAABB(var5, var9, var13, var7, var11, var15);
    }

    private boolean IsPlayerClickOffsetOnAxis(float var1)
    {
        return var1 > 0.0F && var1 >= 0.5F;
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        boolean var10 = false;
        boolean var11 = false;
        boolean var12 = false;

        if (var5 == 0)
        {
            var11 = true;
            var10 = this.IsPlayerClickOffsetOnAxis(var6);
            var12 = this.IsPlayerClickOffsetOnAxis(var8);
        }
        else if (var5 == 1)
        {
            var10 = this.IsPlayerClickOffsetOnAxis(var6);
            var12 = this.IsPlayerClickOffsetOnAxis(var8);
        }
        else if (var5 == 2)
        {
            var12 = true;
            var10 = this.IsPlayerClickOffsetOnAxis(var6);
            var11 = this.IsPlayerClickOffsetOnAxis(var7);
        }
        else if (var5 == 3)
        {
            var10 = this.IsPlayerClickOffsetOnAxis(var6);
            var11 = this.IsPlayerClickOffsetOnAxis(var7);
        }
        else if (var5 == 4)
        {
            var10 = true;
            var11 = this.IsPlayerClickOffsetOnAxis(var7);
            var12 = this.IsPlayerClickOffsetOnAxis(var8);
        }
        else if (var5 == 5)
        {
            var11 = this.IsPlayerClickOffsetOnAxis(var7);
            var12 = this.IsPlayerClickOffsetOnAxis(var8);
        }

        return this.SetCornerAlignmentInMetadata(var9, var10, var11, var12);
    }

    public boolean DoesBlockBreakSaw(World var1, int var2, int var3, int var4)
    {
        return this.GetIsStone(var1, var2, var3, var4);
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return !this.GetIsStone(var1, var2, var3, var4) ? FCBetterThanWolves.fcItemGear.itemID : super.GetItemIDDroppedOnSaw(var1, var2, var3, var4);
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return !this.GetIsStone(var1, var2, var3, var4) ? 2 : super.GetItemIDDroppedOnSaw(var1, var2, var3, var4);
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return !this.IsJOffset(var1, var2, var3, var4);
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        boolean var3 = this.IsIOffset(var1);
        boolean var4 = this.IsJOffset(var1);
        boolean var5 = this.IsKOffset(var1);

        if (var2)
        {
            if (var3)
            {
                if (var5)
                {
                    var3 = false;
                }
                else
                {
                    var5 = true;
                }
            }
            else if (var5)
            {
                var5 = false;
            }
            else
            {
                var3 = true;
            }
        }
        else if (var3)
        {
            if (var5)
            {
                var5 = false;
            }
            else
            {
                var3 = false;
            }
        }
        else if (var5)
        {
            var3 = true;
        }
        else
        {
            var5 = true;
        }

        return this.SetCornerAlignmentInMetadata(var1, var3, var4, var5);
    }

    public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.GetCornerAlignment(var1, var2, var3, var4);

        if (!var5)
        {
            ++var6;

            if (var6 > 7)
            {
                var6 = 0;
            }
        }
        else
        {
            --var6;

            if (var6 < 0)
            {
                var6 = 7;
            }
        }

        this.SetCornerAlignment(var1, var2, var3, var4, var6);
        return true;
    }

    public int GetFurnaceBurnTime(int var1)
    {
        return var1 == 0 ? FCBlockPlanks.GetFurnaceBurnTimeByWoodType(0) / 8 : 0;
    }

    public int GetCornerAlignment(IBlockAccess var1, int var2, int var3, int var4)
    {
        return var1.getBlockMetadata(var2, var3, var4) & 7;
    }

    public void SetCornerAlignment(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & 8;
        var6 |= var5;
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public void SetCornerAlignment(World var1, int var2, int var3, int var4, boolean var5, boolean var6, boolean var7)
    {
        int var8 = 0;

        if (var5)
        {
            var8 |= 4;
        }

        if (var6)
        {
            var8 |= 2;
        }

        if (var7)
        {
            var8 |= 1;
        }

        this.SetCornerAlignment(var1, var2, var3, var4, var8);
    }

    public int SetCornerAlignmentInMetadata(int var1, int var2)
    {
        var1 &= 8;
        var1 |= var2;
        return var1;
    }

    public int SetCornerAlignmentInMetadata(int var1, boolean var2, boolean var3, boolean var4)
    {
        int var5 = 0;

        if (var2)
        {
            var5 |= 4;
        }

        if (var3)
        {
            var5 |= 2;
        }

        if (var4)
        {
            var5 |= 1;
        }

        return this.SetCornerAlignmentInMetadata(var1, var5);
    }

    public boolean IsIOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.IsIOffset(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean IsIOffset(int var1)
    {
        return (var1 & 4) > 0;
    }

    public boolean IsJOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.IsJOffset(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean IsJOffset(int var1)
    {
        return (var1 & 2) > 0;
    }

    public boolean IsKOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.IsKOffset(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean IsKOffset(int var1)
    {
        return (var1 & 1) > 0;
    }

    public boolean GetIsStone(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 8) > 0;
    }

    public void SetIsStone(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & 7;

        if (var5)
        {
            var6 |= 8;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("stone");
        this.m_IconWood = var1.registerIcon("wood");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return (var2 & 8) > 0 ? this.blockIcon : this.m_IconWood;
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
        return this.m_currentBlockRenderer.ShouldSideBeRenderedBasedOnCurrentBounds(var2, var3, var4, var5);
    }
}

package net.minecraft.src;

import java.util.List;

public class FCBlockSidingAndCorner extends Block
{
    protected static final double m_dSidingHeight = 0.5D;
    protected static final double m_dCornerWidth = 0.5D;
    protected static final double m_dCornerWidthOffset = 0.5D;
    String m_sTextureName;

    protected FCBlockSidingAndCorner(int var1, Material var2, String var3, float var4, float var5, StepSound var6, String var7)
    {
        super(var1, var2);
        this.setHardness(var4);
        this.setResistance(var5);
        this.setStepSound(var6);
        this.setUnlocalizedName(var7);
        this.m_sTextureName = var3;
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
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return var1 & 1;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        AxisAlignedBB var6;

        if (!this.GetIsCorner(var1, var2, var3, var4))
        {
            var6 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
            var6.TiltToFacingAlongJ(var5);
            return var6;
        }
        else
        {
            var6 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 0.5D, 0.5D, 0.5D);

            if (this.IsCornerFacingIOffset(var5))
            {
                var6.minX += 0.5D;
                var6.maxX += 0.5D;
            }

            if (this.IsCornerFacingJOffset(var5))
            {
                var6.minY += 0.5D;
                var6.maxY += 0.5D;
            }

            if (this.IsCornerFacingKOffset(var5))
            {
                var6.minZ += 0.5D;
                var6.maxZ += 0.5D;
            }

            return var6;
        }
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        if (!this.GetIsCorner(var9))
        {
            return this.SetFacing(var9, var5);
        }
        else
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

            return this.SetCornerFacingInMetadata(var9, var10, var11, var12);
        }
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        if (!this.GetIsCorner(var1, var2, var3, var4))
        {
            int var7 = this.GetFacing(var1, var2, var3, var4);
            return var5 == Block.GetOppositeFacing(var7);
        }
        else
        {
            return false;
        }
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        if (super.CanGroundCoverRestOnBlock(var1, var2, var3, var4))
        {
            return true;
        }
        else
        {
            if (!this.GetIsCorner(var1, var2, var3, var4))
            {
                int var5 = this.GetFacing(var1, var2, var3, var4);

                if (var5 == 1)
                {
                    return true;
                }
            }

            return false;
        }
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        if (!this.GetIsCorner(var1, var2, var3, var4))
        {
            int var5 = this.GetFacing(var1, var2, var3, var4);

            if (var5 == 1)
            {
                return -0.5F;
            }
        }

        return 0.0F;
    }

    public int GetFacing(int var1)
    {
        return var1 >> 1;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 &= 1;
        var1 |= var2 << 1;
        return var1;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        return !this.GetIsCorner(var1, var2, var3, var4) ? var5 != 0 : !this.IsCornerFacingJOffset(var5);
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        if (!this.GetIsCorner(var1, var2, var3, var4))
        {
            int var5 = this.GetFacing(var1, var2, var3, var4);

            if (var5 > 1)
            {
                return true;
            }
        }

        return false;
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        int var3 = this.GetFacing(var1);

        if ((var1 & 1) == 0)
        {
            return super.RotateMetadataAroundJAxis(var1, var2);
        }
        else
        {
            boolean var4 = this.IsCornerFacingIOffset(var3);
            boolean var5 = this.IsCornerFacingJOffset(var3);
            boolean var6 = this.IsCornerFacingKOffset(var3);

            if (var2)
            {
                if (var4)
                {
                    if (var6)
                    {
                        var4 = false;
                    }
                    else
                    {
                        var6 = true;
                    }
                }
                else if (var6)
                {
                    var6 = false;
                }
                else
                {
                    var4 = true;
                }
            }
            else if (var4)
            {
                if (var6)
                {
                    var6 = false;
                }
                else
                {
                    var4 = false;
                }
            }
            else if (var6)
            {
                var4 = true;
            }
            else
            {
                var6 = true;
            }

            return this.SetCornerFacingInMetadata(var1, var4, var5, var6);
        }
    }

    public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);

        if (!this.GetIsCorner(var1, var2, var3, var4))
        {
            var6 = Block.CycleFacing(var6, var5);
        }
        else if (!var5)
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

        this.SetFacing(var1, var2, var3, var4, var6);
        return true;
    }

    public float MobSpawnOnVerticalOffset(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);

        if (this.GetIsCorner(var1, var2, var3, var4))
        {
            if (!this.IsCornerFacingJOffset(var5))
            {
                return -0.5F;
            }
        }
        else if (var5 == 1)
        {
            return -0.5F;
        }

        return 0.0F;
    }

    public boolean GetIsCorner(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsCorner(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean GetIsCorner(int var1)
    {
        return (var1 & 1) > 0;
    }

    public boolean IsCornerFacingIOffset(int var1)
    {
        return (var1 & 4) > 0;
    }

    public boolean IsCornerFacingJOffset(int var1)
    {
        return (var1 & 2) > 0;
    }

    public boolean IsCornerFacingKOffset(int var1)
    {
        return (var1 & 1) > 0;
    }

    private boolean IsPlayerClickOffsetOnAxis(float var1)
    {
        return var1 > 0.0F && var1 >= 0.5F;
    }

    public void SetCornerFacing(World var1, int var2, int var3, int var4, boolean var5, boolean var6, boolean var7)
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

        this.SetFacing(var1, var2, var3, var4, var8);
    }

    public int SetCornerFacingInMetadata(int var1, boolean var2, boolean var3, boolean var4)
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

        return this.SetFacing(var1, var5);
    }

    public static int GetCornerAlignmentOffsetAlongAxis(int var0, int var1)
    {
        return (var0 & 4 >> var1) > 0 ? 1 : -1;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon(this.m_sTextureName);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        var3.add(new ItemStack(var1, 1, 0));
        var3.add(new ItemStack(var1, 1, 1));
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

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        if (this.blockID != FCBetterThanWolves.fcBlockWoodSidingItemStubID && ((var2 & 1) != 0 || this.blockID == FCBetterThanWolves.fcBlockWoodCornerItemStubID))
        {
            var1.setRenderBounds(0.25D, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D);
        }
        else
        {
            var1.setRenderBounds(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D);
        }

        if (this.blockID != FCBetterThanWolves.fcBlockWoodSidingItemStubID && this.blockID != FCBetterThanWolves.fcBlockWoodCornerItemStubID)
        {
            FCClientUtilsRender.RenderInvBlockWithMetadata(var1, this, -0.5F, -0.5F, -0.5F, 0);
        }
        else
        {
            Icon var4;

            switch (var2)
            {
                case 1:
                    var4 = FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner.blockIcon;
                    break;

                case 2:
                    var4 = FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner.blockIcon;
                    break;

                case 3:
                    var4 = FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner.blockIcon;
                    break;

                case 4:
                    var4 = FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner.blockIcon;
                    break;

                default:
                    var4 = FCBetterThanWolves.fcBlockWoodOakSidingAndCorner.blockIcon;
            }

            FCClientUtilsRender.RenderInvBlockWithTexture(var1, this, -0.5F, -0.5F, -0.5F, var4);
        }
    }
}

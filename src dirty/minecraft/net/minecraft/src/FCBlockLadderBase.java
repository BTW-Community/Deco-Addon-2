package net.minecraft.src;

import java.util.Random;

public class FCBlockLadderBase extends Block
{
    protected static final float m_fLadderThickness = 0.125F;
    protected static final AxisAlignedBB m_boxCollision = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
    protected static final double m_dLadderHorizontalOffset = 0.05000000074505806D;
    private Icon m_filterIcon;

    protected FCBlockLadderBase(int var1)
    {
        super(var1, Material.circuits);
        this.setHardness(0.4F);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyant();
        this.setStepSound(soundLadderFootstep);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcBlockLadder.blockID;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        AxisAlignedBB var5 = m_boxCollision.MakeTemporaryCopy();
        var5.RotateAroundJToFacing(this.GetFacing(var1, var2, var3, var4));
        return var5;
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
        for (int var5 = 2; var5 <= 5; ++var5)
        {
            if (this.CanAttachToFacing(var1, var2, var3, var4, Block.GetOppositeFacing(var5)))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        if (this.CanAttachToFacing(var1, var2, var3, var4, Block.GetOppositeFacing(var5)))
        {
            var9 = this.SetFacing(var9, var5);
        }
        else
        {
            for (int var10 = 2; var10 <= 5; ++var10)
            {
                if (this.CanAttachToFacing(var1, var2, var3, var4, var10))
                {
                    var9 = this.SetFacing(var9, Block.GetOppositeFacing(var10));
                    break;
                }
            }
        }

        return var9;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (!this.CanAttachToFacing(var1, var2, var3, var4, Block.GetOppositeFacing(this.GetFacing(var6))))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var6, 0);
            var1.setBlockToAir(var2, var3, var4);
        }

        super.onNeighborBlockChange(var1, var2, var3, var4, var5);
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 8;
    }

    public boolean IsBlockClimbable(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public int GetFacing(int var1)
    {
        return (var1 & 3) + 2;
    }

    public int SetFacing(int var1, int var2)
    {
        int var3 = MathHelper.clamp_int(var2, 2, 5) - 2;
        var1 &= -4;
        return var1 | var3;
    }

    public boolean CanRotateAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return var5 == Block.GetOppositeFacing(this.GetFacing(var1, var2, var3, var4));
    }

    public int GetNewMetadataRotatedAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        int var7 = var1.getBlockMetadata(var2, var3, var4);
        return this.SetFacing(var7, Block.GetOppositeFacing(var6));
    }

    public boolean CanItemPassIfFilter(ItemStack var1)
    {
        int var2 = var1.getItem().GetFilterableProperties(var1);
        return (var2 & 1) == 0;
    }

    public boolean CanMobsSpawnOn(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    protected boolean CanAttachToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        if (var5 >= 2)
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);
            return FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var6.i, var6.j, var6.k, Block.GetOppositeFacing(var5));
        }
        else
        {
            return false;
        }
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("ladder");
        this.m_filterIcon = var1.registerIcon("fcBlockHopper_ladder");
    }

    public Icon GetHopperFilterIcon()
    {
        return this.m_filterIcon;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return this.RenderLadder(var1, var2, var3, var4);
    }

    public boolean RenderLadder(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        int var6 = this.GetFacing(var5, var2, var3, var4);
        Tessellator var7 = Tessellator.instance;
        var7.setBrightness(this.getMixedBrightnessForBlock(var5, var2, var3, var4));
        float var8 = 1.0F;
        var7.setColorOpaque_F(var8, var8, var8);
        Icon var9 = this.blockIcon;

        if (var1.hasOverrideBlockTexture())
        {
            var9 = var1.GetOverrideTexture();
        }

        double var10 = (double)var9.getMinU();
        double var12 = (double)var9.getMinV();
        double var14 = (double)var9.getMaxU();
        double var16 = (double)var9.getMaxV();

        if (var6 == 5)
        {
            var7.addVertexWithUV((double)var2 + 0.05000000074505806D, (double)(var3 + 1), (double)(var4 + 1), var10, var12);
            var7.addVertexWithUV((double)var2 + 0.05000000074505806D, (double)(var3 + 0), (double)(var4 + 1), var10, var16);
            var7.addVertexWithUV((double)var2 + 0.05000000074505806D, (double)(var3 + 0), (double)(var4 + 0), var14, var16);
            var7.addVertexWithUV((double)var2 + 0.05000000074505806D, (double)(var3 + 1), (double)(var4 + 0), var14, var12);
        }
        else if (var6 == 4)
        {
            var7.addVertexWithUV((double)(var2 + 1) - 0.05000000074505806D, (double)(var3 + 0), (double)(var4 + 1), var14, var16);
            var7.addVertexWithUV((double)(var2 + 1) - 0.05000000074505806D, (double)(var3 + 1), (double)(var4 + 1), var14, var12);
            var7.addVertexWithUV((double)(var2 + 1) - 0.05000000074505806D, (double)(var3 + 1), (double)(var4 + 0), var10, var12);
            var7.addVertexWithUV((double)(var2 + 1) - 0.05000000074505806D, (double)(var3 + 0), (double)(var4 + 0), var10, var16);
        }
        else if (var6 == 3)
        {
            var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)var4 + 0.05000000074505806D, var14, var16);
            var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 1), (double)var4 + 0.05000000074505806D, var14, var12);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 1), (double)var4 + 0.05000000074505806D, var10, var12);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)var4 + 0.05000000074505806D, var10, var16);
        }
        else if (var6 == 2)
        {
            var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 1), (double)(var4 + 1) - 0.05000000074505806D, var10, var12);
            var7.addVertexWithUV((double)(var2 + 1), (double)(var3 + 0), (double)(var4 + 1) - 0.05000000074505806D, var10, var16);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 0), (double)(var4 + 1) - 0.05000000074505806D, var14, var16);
            var7.addVertexWithUV((double)(var2 + 0), (double)(var3 + 1), (double)(var4 + 1) - 0.05000000074505806D, var14, var12);
        }

        return true;
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

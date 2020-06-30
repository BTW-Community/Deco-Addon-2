package net.minecraft.src;

public class FCBlockSpike extends Block
{
    protected static FCModelBlockSpike m_model = new FCModelBlockSpike();

    public FCBlockSpike(int var1)
    {
        super(var1, Material.iron);
        this.setHardness(2.0F);
        this.SetPicksEffectiveOn();
        this.InitBlockBounds(m_model.m_boxCollisionCenter);
        this.setStepSound(soundMetalFootstep);
        this.setUnlocalizedName("fcBlockSpikeIron");
        this.setCreativeTab(CreativeTabs.tabDecorations);
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
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);

        if (var5 >= 2)
        {
            AxisAlignedBB var6 = m_model.m_boxCollisionStrut.MakeTemporaryCopy();
            var6.RotateAroundJToFacing(var5);
            var6.offset((double)var2, (double)var3, (double)var4);
            return var6;
        }
        else
        {
            return m_model.m_boxCollisionCenter.MakeTemporaryCopy().offset((double)var2, (double)var3, (double)var4);
        }
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCModelBlock var7 = new FCModelBlock();
        var7.AddPrimitive(m_model.m_boxCollisionCenter.MakeTemporaryCopy());
        FCModelBlock var8 = this.GetSideSupportsTemporaryModel(var1, var2, var3, var4);

        if (var8 != null)
        {
            var8.MakeTemporaryCopyOfPrimitiveList(var7);
        }

        return var7.CollisionRayTrace(var1, var2, var3, var4, var5, var6);
    }

    /**
     * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
     */
    public boolean canPlaceBlockOnSide(World var1, int var2, int var3, int var4, int var5)
    {
        return this.CanConnectToBlockToFacing(var1, var2, var3, var4, Block.GetOppositeFacing(var5));
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        return this.SetFacing(var9, Block.GetOppositeFacing(var5));
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        super.onNeighborBlockChange(var1, var2, var3, var4, var5);

        if (!this.CanConnectToBlockToFacing(var1, var2, var3, var4, this.GetFacing(var1, var2, var3, var4)))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockToAir(var2, var3, var4);
        }
    }

    public int GetFacing(int var1)
    {
        return var1 & 7;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 &= -8;
        return var1 | var2;
    }

    public boolean HasSmallCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var5 < 2;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1.0F;
    }

    protected boolean CanConnectToBlockToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);
        return FCUtilsWorld.DoesBlockHaveSmallCenterHardpointToFacing(var1, var6.i, var6.j, var6.k, Block.GetOppositeFacing(var5)) || var1.getBlockId(var6.i, var6.j, var6.k) == this.blockID;
    }

    protected boolean IsConnectedSpikeToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);
        return var1.getBlockId(var6.i, var6.j, var6.k) == this.blockID && this.GetFacing(var1, var6.i, var6.j, var6.k) == Block.GetOppositeFacing(var5);
    }

    FCModelBlock GetSideSupportsTemporaryModel(IBlockAccess var1, int var2, int var3, int var4)
    {
        FCModelBlock var5 = null;
        int var6 = this.GetFacing(var1, var2, var3, var4);

        for (int var7 = 2; var7 <= 5; ++var7)
        {
            if (var7 == var6 || this.IsConnectedSpikeToFacing(var1, var2, var3, var4, var7))
            {
                FCModelBlock var8 = m_model.m_modelSideSupport.MakeTemporaryCopy();
                var8.RotateAroundJToFacing(var7);

                if (var5 == null)
                {
                    var5 = var8;
                }
                else
                {
                    var8.MakeTemporaryCopyOfPrimitiveList(var5);
                }
            }
        }

        return var5;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return true;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        int var6 = this.GetFacing(var5, var2, var3, var4);
        FCModelBlock var7 = this.GetSideSupportsTemporaryModel(var5, var2, var3, var4);
        FCModelBlock var8 = m_model.MakeTemporaryCopy();

        if ((var6 == 0 && var5.getBlockId(var2, var3 - 1, var4) != this.blockID || var6 == 1 && var5.getBlockId(var2, var3 + 1, var4) != this.blockID) && !FCUtilsWorld.IsGroundCoverOnBlock(var1.blockAccess, var2, var3, var4))
        {
            m_model.m_modelBase.MakeTemporaryCopyOfPrimitiveList(var8);
        }

        int var9;

        if (var6 == 1)
        {
            var9 = var5.getBlockId(var2, var3 - 1, var4);

            if (var9 != this.blockID && var7 == null)
            {
                m_model.m_modelBall.MakeTemporaryCopyOfPrimitiveList(var8);
            }
        }
        else
        {
            var9 = var5.getBlockId(var2, var3 + 1, var4);

            if (var9 != this.blockID)
            {
                if (FCUtilsWorld.IsBlockRestingOnThatBelow(var5, var2, var3 + 1, var4))
                {
                    m_model.m_modelHolder.MakeTemporaryCopyOfPrimitiveList(var8);
                }
                else if (var7 == null)
                {
                    m_model.m_modelBall.MakeTemporaryCopyOfPrimitiveList(var8);
                }
            }
        }

        if (var6 == 1)
        {
            var8.TiltToFacingAlongJ(0);
        }

        if (var7 != null)
        {
            var7.RenderAsBlock(var1, this, var2, var3, var4);
            m_model.m_modelCenterBrace.MakeTemporaryCopyOfPrimitiveList(var8);
        }

        return var8.RenderAsBlock(var1, this, var2, var3, var4);
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        m_model.RenderAsItemBlock(var1, this, var2);
    }
}

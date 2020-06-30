package net.minecraft.src;

public class FCBlockBasketWicker extends FCBlockBasket
{
    private static final float m_fBasketOpenHeight = 0.75F;
    private static final float m_fBasketHeight = 0.5F;
    private static final float m_fBasketRimWidth = 0.0625F;
    private static final float m_fBasketWidthLip = 0.0F;
    private static final float m_fBasketDepthLip = 0.0625F;
    private static final float m_fBasketLidHeight = 0.125F;
    private static final float m_fBasketLidLayerHeight = 0.0625F;
    private static final float m_fBasketLidLayerWidthGap = 0.0625F;
    private static final float m_fBasketHandleHeight = 0.0625F;
    private static final float m_fBasketHandleWidth = 0.125F;
    private static final float m_fBasketHandleHalfWidth = 0.0625F;
    private static final float m_fBasketHandleLength = 0.25F;
    private static final float m_fBasketHandleHalfLength = 0.125F;
    private static final float m_fBasketInteriorWallThickness = 0.0625F;
    private static final float m_fMindTheGap = 0.001F;
    private static final double m_dLidOpenLipHeight = 0.0625D;
    private static final double m_dLidOpenLipYPos = 0.9375D;
    private static final double m_dLidOpenLipWidth = 0.125D;
    private static final double m_dLidOpenLipLength = 1.0D;
    private static final double m_dLidOpenLipHalfLength = 0.5D;
    private static final double m_dLidOpenLipHorizontalOffset = 0.3125D;
    public FCModelBlock m_blockModelBase;
    public FCModelBlock m_blockModelBaseOpenCollision;
    public FCModelBlock m_blockModelLid;
    public FCModelBlock m_blockModelLidFull;
    public FCModelBlock m_blockModelInterior;
    private static AxisAlignedBB m_boxCollisionLidOpenLip = new AxisAlignedBB(0.0D, 0.9375D, 0.3125D, 1.0D, 1.0D, 0.4375D);
    private static final Vec3 m_lidRotationPoint = Vec3.createVectorHelper(0.5D, 0.375D, 0.875D);
    private Icon m_iconBaseOpenTop;
    private Icon m_iconFront;
    private Icon m_iconTop;
    private Icon m_iconBottom;
    private boolean m_bRenderingBase = false;
    private boolean m_bRenderingInterior = false;

    public FCBlockBasketWicker(int var1)
    {
        super(var1);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
        this.InitModelBase();
        this.InitModelBaseOpenCollison();
        this.InitModelLid();
        this.InitModelLidFull();
        this.InitModelInterior();
        this.setUnlocalizedName("fcBlockBasketWicker");
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityBasketWicker();
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        int var10 = var1.getBlockMetadata(var2, var3, var4);

        if (!this.GetIsOpen(var10))
        {
            if (!var1.isRemote)
            {
                this.SetIsOpen(var1, var2, var3, var4, true);
            }
            else
            {
                var5.playSound("step.gravel", 0.25F + var1.rand.nextFloat() * 0.1F, 0.5F + var1.rand.nextFloat() * 0.1F);
            }

            return true;
        }
        else
        {
            if (this.IsClickingOnLid(var1, var2, var3, var4, var6, var7, var8, var9))
            {
                FCTileEntityBasketWicker var11 = (FCTileEntityBasketWicker)var1.getBlockTileEntity(var2, var3, var4);

                if (!var11.m_bClosing)
                {
                    if (!var1.isRemote)
                    {
                        var11.StartClosingServerSide();
                    }

                    return true;
                }
            }
            else
            {
                if (this.GetHasContents(var10))
                {
                    if (var1.isRemote)
                    {
                        var5.playSound("step.gravel", 0.5F + var1.rand.nextFloat() * 0.25F, 1.0F + var1.rand.nextFloat() * 0.25F);
                    }
                    else
                    {
                        this.EjectStorageStack(var1, var2, var3, var4);
                    }

                    this.SetHasContents(var1, var2, var3, var4, false);
                    return true;
                }

                ItemStack var13 = var5.getCurrentEquippedItem();

                if (var13 != null)
                {
                    if (var1.isRemote)
                    {
                        var5.playSound("step.gravel", 0.5F + var1.rand.nextFloat() * 0.25F, 0.5F + var1.rand.nextFloat() * 0.25F);
                    }
                    else
                    {
                        FCTileEntityBasketWicker var12 = (FCTileEntityBasketWicker)var1.getBlockTileEntity(var2, var3, var4);
                        var12.SetStorageStack(var13);
                    }

                    var13.stackSize = 0;
                    this.SetHasContents(var1, var2, var3, var4, true);
                    return true;
                }
            }

            return false;
        }
    }

    private void EjectStorageStack(World var1, int var2, int var3, int var4)
    {
        FCTileEntityBasketWicker var5 = (FCTileEntityBasketWicker)var1.getBlockTileEntity(var2, var3, var4);
        ItemStack var6 = var5.GetStorageStack();

        if (var6 != null)
        {
            float var7 = 0.5F;
            float var8 = 0.4F;
            float var9 = 0.5F;
            double var10 = (double)((float)var2 + var7);
            double var12 = (double)((float)var3 + var8);
            double var14 = (double)((float)var4 + var9);
            EntityItem var16 = new EntityItem(var1, var10, var12, var14, var6);
            var16.motionY = 0.2D;
            double var17 = 0.15D;
            double var19 = 0.05D;
            int var21 = this.GetFacing(var1, var2, var3, var4);

            if (var21 <= 3)
            {
                var16.motionX = (var1.rand.nextDouble() * 2.0D - 1.0D) * var19;

                if (var21 == 2)
                {
                    var16.motionZ = -var17;
                }
                else
                {
                    var16.motionZ = var17;
                }
            }
            else
            {
                var16.motionZ = (var1.rand.nextDouble() * 2.0D - 1.0D) * var19;

                if (var21 == 4)
                {
                    var16.motionX = -var17;
                }
                else
                {
                    var16.motionX = var17;
                }
            }

            var16.delayBeforeCanPickup = 10;
            var1.spawnEntityInWorld(var16);
            var5.SetStorageStack((ItemStack)null);
        }
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return this.GetFixedBlockBoundsFromPool().offset((double)var2, (double)var3, (double)var4);
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        return !this.GetIsOpen(var1, var2, var3, var4) ? AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCUtilsRayTraceVsComplexBlock var7 = new FCUtilsRayTraceVsComplexBlock(var1, var2, var3, var4, var5, var6);
        int var8 = var1.getBlockMetadata(var2, var3, var4);
        int var9 = this.GetFacing(var8);
        FCModelBlock var10;

        if (!this.GetIsOpen(var8))
        {
            var10 = this.m_blockModelBase.MakeTemporaryCopy();
            FCModelBlock var11;

            if (this.GetHasContents(var8))
            {
                var11 = this.m_blockModelLidFull.MakeTemporaryCopy();
            }
            else
            {
                var11 = this.m_blockModelLid.MakeTemporaryCopy();
            }

            var11.RotateAroundJToFacing(var9);
            var11.AddToRayTrace(var7);
        }
        else
        {
            var10 = this.m_blockModelBaseOpenCollision.MakeTemporaryCopy();
            FCTileEntityBasketWicker var13 = (FCTileEntityBasketWicker)var1.getBlockTileEntity(var2, var3, var4);

            if (var13.m_fLidOpenRatio > 0.95F)
            {
                AxisAlignedBB var12 = m_boxCollisionLidOpenLip.MakeTemporaryCopy();
                var12.RotateAroundJToFacing(var9);
                var7.AddBoxWithLocalCoordsToIntersectionList(var12);
            }
        }

        var10.RotateAroundJToFacing(var9);
        var10.AddToRayTrace(var7);
        return var7.GetFirstIntersection();
    }

    public void OnCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        if (!var1.isRemote)
        {
            this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemWickerPiece.itemID, 1, 0, 0.75F);
        }
    }

    public FCModelBlock GetLidModel(int var1)
    {
        return this.GetHasContents(var1) ? this.m_blockModelLidFull : this.m_blockModelLid;
    }

    public Vec3 GetLidRotationPoint()
    {
        return m_lidRotationPoint;
    }

    public float MobSpawnOnVerticalOffset(World var1, int var2, int var3, int var4)
    {
        return -0.5F;
    }

    private void InitModelBase()
    {
        this.m_blockModelBase = new FCModelBlock();
        this.m_blockModelBase.AddBox(0.0625D, 0.0D, 0.125D, 0.9375D, 0.375D, 0.875D);
    }

    private void InitModelBaseOpenCollison()
    {
        this.m_blockModelBaseOpenCollision = new FCModelBlock();
        this.m_blockModelBaseOpenCollision.AddBox(0.0625D, 0.0D, 0.125D, 0.9375D, 0.75D, 0.875D);
    }

    private void InitModelLid()
    {
        this.m_blockModelLid = new FCModelBlock();
        this.m_blockModelLid.AddBox(0.0D, 0.375D, 0.0625D, 1.0D, 0.4375D, 0.9375D);
        this.m_blockModelLid.AddBox(0.0625D, 0.4375D, 0.125D, 0.9375D, 0.5D, 0.875D);
        this.m_blockModelLid.AddBox(0.375D, 0.5D, 0.4375D, 0.625D, 0.5625D, 0.5625D);
    }

    private void InitModelLidFull()
    {
        this.m_blockModelLidFull = new FCModelBlock();
        this.m_blockModelLidFull.AddBox(0.0D, 0.375D, 0.0625D, 1.0D, 0.4375D, 0.9375D);
        this.m_blockModelLidFull.AddBox(0.0625D, 0.4375D, 0.125D, 0.9375D, 0.5D, 0.875D);
        this.m_blockModelLidFull.AddBox(0.125D, 0.5D, 0.1875D, 0.875D, 0.5625D, 0.8125D);
    }

    private void InitModelInterior()
    {
        this.m_blockModelInterior = new FCModelBlock();
        this.m_blockModelInterior.AddBox(0.8760000000474975D, 0.375D, 0.8135000000474975D, 0.12399999797344208D, 0.0625D, 0.18649999797344208D);
    }

    private boolean IsClickingOnLid(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8)
    {
        return var7 > 0.75F;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_iconBaseOpenTop = var1.registerIcon("fcBlockBasketWicker_open_top");
        this.m_iconFront = var1.registerIcon("fcBlockBasketWicker_front");
        this.m_iconTop = var1.registerIcon("fcBlockBasketWicker_top");
        this.m_iconBottom = var1.registerIcon("fcBlockBasketWicker_bottom");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        if (var1 == 1 && this.m_bRenderingBase)
        {
            return this.m_iconBaseOpenTop;
        }
        else
        {
            if (!this.m_bRenderingInterior)
            {
                if (var1 == 1)
                {
                    return this.m_iconTop;
                }

                if (var1 == 0)
                {
                    return this.m_iconBottom;
                }

                int var3 = this.GetFacing(var2);

                if (var1 == var3)
                {
                    return this.m_iconFront;
                }
            }

            return super.getIcon(var1, var2);
        }
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return var5 == 0 ? (this.m_bRenderingInterior ? false : !this.m_bRenderingBase || super.shouldSideBeRendered(var1, var2, var3, var4, var5)) : true;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        int var6 = var1.blockAccess.getBlockMetadata(var2, var3, var4);
        int var7 = this.GetFacing(var6);
        this.m_bRenderingBase = true;
        FCModelBlock var5 = this.m_blockModelBase.MakeTemporaryCopy();
        var5.RotateAroundJToFacing(this.GetFacing(var1.blockAccess, var2, var3, var4));
        var1.SetUvRotateTop(this.ConvertFacingToTopTextureRotation(var7));
        var1.SetUvRotateBottom(this.ConvertFacingToBottomTextureRotation(var7));
        boolean var8 = var5.RenderAsBlock(var1, this, var2, var3, var4);
        this.m_bRenderingBase = false;

        if (!this.GetIsOpen(var6))
        {
            if (this.GetHasContents(var6))
            {
                var5 = this.m_blockModelLidFull.MakeTemporaryCopy();
            }
            else
            {
                var5 = this.m_blockModelLid.MakeTemporaryCopy();
            }

            var5.RotateAroundJToFacing(this.GetFacing(var1.blockAccess, var2, var3, var4));
            var5.RenderAsBlockWithColorMultiplier(var1, this, var2, var3, var4);
        }
        else
        {
            var5 = this.m_blockModelInterior.MakeTemporaryCopy();
            var5.RotateAroundJToFacing(var7);
            this.m_bRenderingInterior = true;
            var5.RenderAsBlockWithColorMultiplier(var1, this, var2, var3, var4);
            this.m_bRenderingInterior = false;
        }

        var1.ClearUvRotation();
        return var8;
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        this.m_blockModelLid.RenderAsItemBlock(var1, this, var2);
        this.m_blockModelBase.RenderAsItemBlock(var1, this, var2);
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, MovingObjectPosition var2)
    {
        int var3 = var2.blockX;
        int var4 = var2.blockY;
        int var5 = var2.blockZ;
        int var6 = var1.getBlockMetadata(var3, var4, var5);
        int var7 = this.GetFacing(var6);
        double var8 = (double)var4;
        double var10 = (double)((float)var4 + 0.5F);

        if (this.GetIsOpen(var6))
        {
            if (var2.hitVec.yCoord - var8 >= 0.9364999999525025D)
            {
                AxisAlignedBB var20 = m_boxCollisionLidOpenLip.MakeTemporaryCopy();
                var20.RotateAroundJToFacing(var7);
                return var20.offset((double)var3, (double)var4, (double)var5);
            }

            var10 -= 0.125D;
        }

        double var12;
        double var14;
        double var16;
        double var18;

        if (var7 != 2 && var7 != 3)
        {
            var12 = (double)var3 + 0.0625D + 0.0625D;
            var14 = (double)var3 + 1.0D - 0.0625D - 0.0625D;
            var16 = (double)var5 + 0.0625D + 0.0D;
            var18 = (double)var5 + 1.0D - 0.0625D - 0.0D;
        }
        else
        {
            var12 = (double)var3 + 0.0625D + 0.0D;
            var14 = (double)var3 + 1.0D - 0.0625D - 0.0D;
            var16 = (double)var5 + 0.0625D + 0.0625D;
            var18 = (double)var5 + 1.0D - 0.0625D - 0.0625D;
        }

        return AxisAlignedBB.getAABBPool().getAABB(var12, var8, var16, var14, var10, var18);
    }
}

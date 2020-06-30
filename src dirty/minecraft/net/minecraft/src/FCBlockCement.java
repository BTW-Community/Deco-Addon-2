package net.minecraft.src;

import java.util.Random;

public class FCBlockCement extends BlockContainer
{
    private final int iCementTexture;
    private final int iCementPartiallyDryTexture;
    public static final int iMaxCementSpreadDist = 16;
    public static final int iCementTicksToDry = 12;
    public static final int iCementTicksToPartiallyDry = 8;
    boolean[] tempSpreadToSideFlags;
    int[] tempClosestDownslopeToSideDist;
    private Icon m_IconDrying;

    protected FCBlockCement(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialCement);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        this.setHardness(100.0F);
        this.setLightOpacity(255);
        this.setUnlocalizedName("fcBlockCement");
        this.setStepSound(Block.soundSandFootstep);
        this.iCementTexture = 15;
        this.iCementPartiallyDryTexture = 16;
        this.tempSpreadToSideFlags = new boolean[4];
        this.tempClosestDownslopeToSideDist = new int[4];
        Block.useNeighborBrightness[var1] = true;
        this.setTickRandomly(true);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityCement();
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
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
     * Returns whether this block is collideable based on the arguments passed in Args: blockMetaData, unknownFlag
     */
    public boolean canCollideCheck(int var1, boolean var2)
    {
        return var2 && var1 == 0;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return var1.getBlockId(var2, var3 + 1, var4) != this.blockID ? AxisAlignedBB.getAABBPool().getAABB((double)var2, (double)var3, (double)var4, (double)((float)(var2 + 1)), (double)((float)var3 + 0.5F), (double)((float)(var4 + 1))) : AxisAlignedBB.getAABBPool().getAABB((double)var2, (double)var3, (double)var4, (double)((float)(var2 + 1)), (double)((float)(var3 + 1)), (double)((float)(var4 + 1)));
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return 0;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 0;
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 20;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);

        if (var1.getBlockId(var2, var3, var4) == this.blockID)
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = this.GetCementSpreadDist(var1, var2, var3, var4);
        int var8;
        int var11;

        if (var6 > 0)
        {
            byte var7 = -100;
            var11 = this.CheckForLesserSpreadDist(var1, var2 - 1, var3, var4, var7);
            var11 = this.CheckForLesserSpreadDist(var1, var2 + 1, var3, var4, var11);
            var11 = this.CheckForLesserSpreadDist(var1, var2, var3, var4 - 1, var11);
            var11 = this.CheckForLesserSpreadDist(var1, var2, var3, var4 + 1, var11);
            var11 = this.CheckForLesserSpreadDist(var1, var2, var3 + 1, var4, var11);

            if (var11 < 0)
            {
                var11 = -1;
            }
            else
            {
                ++var11;
            }

            var8 = this.GetCementSpreadDist(var1, var2, var3 + 1, var4);

            if (var8 >= 0 && var8 < var11)
            {
                var11 = var8 + 1;
            }

            if (var11 > 0 && var11 < var6)
            {
                var6 = var11;
                this.SetCementSpreadDist(var1, var2, var3, var4, var11);
                this.SetCementDryTime(var1, var2, var3, var4, 0);
            }
        }

        var11 = this.GetCementDryTime(var1, var2, var3, var4);
        ++var11;
        var8 = this.CheckNeighboursCloserToSourceForMinDryTime(var1, var2, var3, var4);

        if (var8 <= var11)
        {
            if (var8 <= 0)
            {
                var11 = 0;
            }
            else
            {
                var11 = var8 - 1;
            }
        }

        if (var11 > 12)
        {
            var1.setBlockWithNotify(var2, var3, var4, Block.stone.blockID);
        }
        else
        {
            this.SetCementDryTime(var1, var2, var3, var4, var11);
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));

            if (this.IsBlockOpenToSpread(var1, var2, var3 - 1, var4))
            {
                int var9 = var6 + 1;

                if (var9 <= 16)
                {
                    var1.setBlockWithNotify(var2, var3 - 1, var4, this.blockID);
                    this.SetCementSpreadDist(var1, var2, var3 - 1, var4, var9);
                }
            }
            else if (var6 >= 0 && (var6 == 0 || this.blockBlocksFlow(var1, var2, var3 - 1, var4)))
            {
                boolean[] var12 = this.CheckSideBlocksForPotentialSpread(var1, var2, var3, var4);
                int var10 = var6 + 1;

                if (var10 <= 16)
                {
                    if (var12[0])
                    {
                        this.AttemptToSpreadToBlock(var1, var2 - 1, var3, var4, var10);
                    }

                    if (var12[1])
                    {
                        this.AttemptToSpreadToBlock(var1, var2 + 1, var3, var4, var10);
                    }

                    if (var12[2])
                    {
                        this.AttemptToSpreadToBlock(var1, var2, var3, var4 - 1, var10);
                    }

                    if (var12[3])
                    {
                        this.AttemptToSpreadToBlock(var1, var2, var3, var4 + 1, var10);
                    }
                }
            }
        }
    }

    public boolean GetCanBlockBeIncinerated(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        return null;
    }

    private boolean IsPowered(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return (var5 & 1) > 0;
    }

    public float GetRenderHeight(IBlockAccess var1, int var2, int var3, int var4)
    {
        float var5 = 1.0F;

        if (var1.getBlockMaterial(var2, var3, var4) == this.blockMaterial)
        {
            int var6 = this.GetCementSpreadDist(var1, var2, var3, var4);
            var5 = (float)(var6 + 1) / 18.0F;

            if (this.IsCementPartiallyDry(var1, var2, var3, var4))
            {
                var5 *= 0.1F;
            }
            else
            {
                var5 *= 0.5F;
            }
        }

        return var5;
    }

    public int GetCementSpreadDist(IBlockAccess var1, int var2, int var3, int var4)
    {
        if (var1.getBlockMaterial(var2, var3, var4) != this.blockMaterial)
        {
            return -1;
        }
        else
        {
            FCTileEntityCement var5 = (FCTileEntityCement)var1.getBlockTileEntity(var2, var3, var4);
            return var5.GetSpreadDist();
        }
    }

    public void SetCementSpreadDist(World var1, int var2, int var3, int var4, int var5)
    {
        FCTileEntityCement var6 = (FCTileEntityCement)var1.getBlockTileEntity(var2, var3, var4);
        var6.SetSpreadDist(var5);
        var1.notifyBlocksOfNeighborChange(var2, var3, var4, this.blockID);
        var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
    }

    public boolean IsCementSourceBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetCementSpreadDist(var1, var2, var3, var4) == 0;
    }

    public int GetCementDryTime(IBlockAccess var1, int var2, int var3, int var4)
    {
        if (var1.getBlockMaterial(var2, var3, var4) == this.blockMaterial)
        {
            TileEntity var5 = var1.getBlockTileEntity(var2, var3, var4);

            if (var5 instanceof FCTileEntityCement)
            {
                FCTileEntityCement var6 = (FCTileEntityCement)var1.getBlockTileEntity(var2, var3, var4);
                return var6.GetDryTime();
            }
        }

        return 0;
    }

    public void SetCementDryTime(World var1, int var2, int var3, int var4, int var5)
    {
        FCTileEntityCement var6 = (FCTileEntityCement)var1.getBlockTileEntity(var2, var3, var4);
        var6.SetDryTime(var5);
        var1.notifyBlocksOfNeighborChange(var2, var3, var4, this.blockID);
        var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
    }

    public boolean IsCementPartiallyDry(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetCementDryTime(var1, var2, var3, var4) >= 8;
    }

    private int CheckNeighboursCloserToSourceForMinDryTime(World var1, int var2, int var3, int var4)
    {
        short var5 = 1000;
        int var6 = this.GetCementSpreadDist(var1, var2, var3, var4);
        int var7 = this.GetLesserDryTimeIfCloserToSource(var1, var2, var3 + 1, var4, var6, var5);
        var7 = this.GetLesserDryTimeIfCloserToSource(var1, var2 + 1, var3, var4, var6, var7);
        var7 = this.GetLesserDryTimeIfCloserToSource(var1, var2 - 1, var3, var4, var6, var7);
        var7 = this.GetLesserDryTimeIfCloserToSource(var1, var2, var3, var4 + 1, var6, var7);
        var7 = this.GetLesserDryTimeIfCloserToSource(var1, var2, var3, var4 - 1, var6, var7);
        return var7;
    }

    private int GetLesserDryTimeIfCloserToSource(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        Material var7 = var1.getBlockMaterial(var2, var3, var4);

        if (var7 == this.blockMaterial)
        {
            int var8 = this.GetCementSpreadDist(var1, var2, var3, var4);

            if (var8 < var5)
            {
                int var9 = this.GetCementDryTime(var1, var2, var3, var4);

                if (var9 < var6)
                {
                    return var9;
                }
            }
        }

        return var6;
    }

    private void AttemptToSpreadToBlock(World var1, int var2, int var3, int var4, int var5)
    {
        if (this.IsBlockOpenToSpread(var1, var2, var3, var4))
        {
            int var6 = var1.getBlockId(var2, var3, var4);

            if (var6 > 0)
            {
                Block.blocksList[var6].dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            }

            var1.setBlockWithNotify(var2, var3, var4, this.blockID);
            this.SetCementSpreadDist(var1, var2, var3, var4, var5);
        }
    }

    private boolean[] CheckSideBlocksForPotentialSpread(World var1, int var2, int var3, int var4)
    {
        for (int var5 = 0; var5 < 4; ++var5)
        {
            int var6 = var2;
            int var8 = var4;

            switch (var5)
            {
                case 0:
                    var6 = var2 - 1;
                    break;

                case 1:
                    var6 = var2 + 1;
                    break;

                case 2:
                    var8 = var4 - 1;
                    break;

                default:
                    var8 = var4 + 1;
            }

            if (!this.blockBlocksFlow(var1, var6, var3, var8) && (var1.getBlockMaterial(var6, var3, var8) != this.blockMaterial || !this.IsCementSourceBlock(var1, var6, var3, var8)))
            {
                this.tempSpreadToSideFlags[var5] = true;
            }
            else
            {
                this.tempSpreadToSideFlags[var5] = false;
            }
        }

        return this.tempSpreadToSideFlags;
    }

    private boolean[] CheckSideBlocksForDownslope(World var1, int var2, int var3, int var4)
    {
        int var5;
        int var6;

        for (var5 = 0; var5 < 4; ++var5)
        {
            this.tempClosestDownslopeToSideDist[var5] = 1000;
            var6 = var2;
            int var8 = var4;

            if (var5 == 0)
            {
                var6 = var2 - 1;
            }
            else if (var5 == 1)
            {
                var6 = var2 + 1;
            }
            else if (var5 == 2)
            {
                var8 = var4 - 1;
            }
            else if (var5 == 3)
            {
                var8 = var4 + 1;
            }

            if (!this.blockBlocksFlow(var1, var6, var3, var8) && (var1.getBlockMaterial(var6, var3, var8) != this.blockMaterial || !this.IsCementSourceBlock(var1, var6, var3, var8)))
            {
                if (!this.blockBlocksFlow(var1, var6, var3 - 1, var8))
                {
                    this.tempClosestDownslopeToSideDist[var5] = 0;
                }
                else
                {
                    this.tempClosestDownslopeToSideDist[var5] = this.RecursivelyCheckSideBlocksForDownSlope(var1, var6, var3, var8, 1, var5);
                }
            }
        }

        var5 = this.tempClosestDownslopeToSideDist[0];

        for (var6 = 1; var6 < 4; ++var6)
        {
            if (this.tempClosestDownslopeToSideDist[var6] < var5)
            {
                var5 = this.tempClosestDownslopeToSideDist[var6];
            }
        }

        for (var6 = 0; var6 < 4; ++var6)
        {
            this.tempSpreadToSideFlags[var6] = this.tempClosestDownslopeToSideDist[var6] == var5;
        }

        return this.tempSpreadToSideFlags;
    }

    private int RecursivelyCheckSideBlocksForDownSlope(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        int var7 = 1000;

        for (int var8 = 0; var8 < 4; ++var8)
        {
            if ((var8 != 0 || var6 != 1) && (var8 != 1 || var6 != 0) && (var8 != 2 || var6 != 3) && (var8 != 3 || var6 != 2))
            {
                int var9 = var2;
                int var11 = var4;

                if (var8 == 0)
                {
                    var9 = var2 - 1;
                }
                else if (var8 == 1)
                {
                    var9 = var2 + 1;
                }
                else if (var8 == 2)
                {
                    var11 = var4 - 1;
                }
                else if (var8 == 3)
                {
                    var11 = var4 + 1;
                }

                if (!this.blockBlocksFlow(var1, var9, var3, var11) && this.GetCementSpreadDist(var1, var9, var3, var11) != 0)
                {
                    if (!this.blockBlocksFlow(var1, var9, var3 - 1, var11))
                    {
                        return var5;
                    }

                    if (var5 < 4)
                    {
                        int var12 = this.RecursivelyCheckSideBlocksForDownSlope(var1, var9, var3, var11, var5 + 1, var8);

                        if (var12 < var7)
                        {
                            var7 = var12;
                        }
                    }
                }
            }
        }

        return var7;
    }

    private boolean blockBlocksFlow(World var1, int var2, int var3, int var4)
    {
        Block var5 = blocksList[var1.getBlockId(var2, var3, var4)];
        return var5 != null && var5.blockMaterial != this.blockMaterial && var5.GetPreventsFluidFlow(var1, var2, var3, var4, this);
    }

    protected int CheckForLesserSpreadDist(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetCementSpreadDist(var1, var2, var3, var4);
        return var6 < 0 ? var5 : (var5 >= 0 && var6 >= var5 ? var5 : var6);
    }

    private boolean IsBlockOpenToSpread(World var1, int var2, int var3, int var4)
    {
        if (var3 < 0)
        {
            return false;
        }
        else
        {
            Material var5 = var1.getBlockMaterial(var2, var3, var4);
            return var5 == this.blockMaterial ? false : !this.blockBlocksFlow(var1, var2, var3, var4);
        }
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.IsCementPartiallyDry(var1, var2, var3, var4) ? this.m_IconDrying : this.blockIcon;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("fcBlockCement");
        this.m_IconDrying = var1.registerIcon("fcBlockCement_drying");
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        return this.RenderCement(var1, var1.blockAccess, var2, var3, var4, this);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        Material var6 = var1.getBlockMaterial(var2, var3, var4);
        return var6 == this.blockMaterial ? false : (var6 == Material.ice ? false : (var5 == 1 ? true : super.shouldSideBeRendered(var1, var2, var3, var4, var5)));
    }

    /**
     * How bright to render this block based on the light its receiving. Args: iBlockAccess, x, y, z
     */
    public float getBlockBrightness(IBlockAccess var1, int var2, int var3, int var4)
    {
        float var5 = var1.getLightBrightness(var2, var3, var4);
        float var6 = var1.getLightBrightness(var2, var3 + 1, var4);
        return var5 <= var6 ? var6 : var5;
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.IsCementPartiallyDry(var1, var2, var3, var4) && var5.nextInt(250) == 0)
        {
            var1.playSound((double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), "mob.ghast.moan", 0.5F, 2.6F + (var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.8F);
        }
    }

    public boolean RenderCement(RenderBlocks var1, IBlockAccess var2, int var3, int var4, int var5, Block var6)
    {
        boolean var7 = false;
        Tessellator var8 = Tessellator.instance;
        var8.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        boolean var9 = var6.shouldSideBeRendered(var2, var3, var4 + 1, var5, 1);
        boolean var10 = var6.shouldSideBeRendered(var2, var3, var4 - 1, var5, 0);
        boolean[] var11 = new boolean[] {var6.shouldSideBeRendered(var2, var3, var4, var5 - 1, 2), var6.shouldSideBeRendered(var2, var3, var4, var5 + 1, 3), var6.shouldSideBeRendered(var2, var3 - 1, var4, var5, 4), var6.shouldSideBeRendered(var2, var3 + 1, var4, var5, 5)};

        if (!var9 && !var10 && !var11[0] && !var11[1] && !var11[2] && !var11[3])
        {
            return false;
        }
        else
        {
            boolean var12 = false;
            float var13 = 0.5F;
            float var14 = 1.0F;
            float var15 = 0.8F;
            float var16 = 0.6F;
            float var17 = this.RenderCementGetCornerHeightFromNeighbours(var2, var3, var4, var5);
            float var18 = this.RenderCementGetCornerHeightFromNeighbours(var2, var3, var4, var5 + 1);
            float var19 = this.RenderCementGetCornerHeightFromNeighbours(var2, var3 + 1, var4, var5 + 1);
            float var20 = this.RenderCementGetCornerHeightFromNeighbours(var2, var3 + 1, var4, var5);

            if (var7 || var9)
            {
                var12 = true;
                Icon var21 = var6.getBlockTexture(var2, var3, var4, var5, 1);
                double var22 = (double)var21.getMinU();
                double var24 = (double)var21.getMaxU();
                double var26 = (double)var21.getMinV();
                double var28 = (double)var21.getMaxV();
                var8.setBrightness(var6.getMixedBrightnessForBlock(var2, var3, var4 + 1, var5));
                var8.addVertexWithUV((double)(var3 + 0), (double)((float)var4 + var17), (double)(var5 + 0), var22, var26);
                var8.addVertexWithUV((double)(var3 + 0), (double)((float)var4 + var18), (double)(var5 + 1), var22, var28);
                var8.addVertexWithUV((double)(var3 + 1), (double)((float)var4 + var19), (double)(var5 + 1), var24, var28);
                var8.addVertexWithUV((double)(var3 + 1), (double)((float)var4 + var20), (double)(var5 + 0), var24, var26);
            }

            if (var7 || var10)
            {
                var8.setBrightness(var6.getMixedBrightnessForBlock(var2, var3, var4 - 1, var5));
                var1.renderFaceYNeg(var6, (double)var3, (double)var4, (double)var5, var6.getBlockTexture(var2, var3, var4, var5, 0));
                var12 = true;
            }

            for (int var42 = 0; var42 < 4; ++var42)
            {
                int var43 = var3;
                int var44 = var5;

                if (var42 == 0)
                {
                    var44 = var5 - 1;
                }
                else if (var42 == 1)
                {
                    var44 = var5 + 1;
                }
                else if (var42 == 2)
                {
                    var43 = var3 - 1;
                }
                else if (var42 == 3)
                {
                    var43 = var3 + 1;
                }

                if (var7 || var11[var42])
                {
                    float var25;
                    float var27;
                    float var29;
                    float var30;
                    float var45;
                    float var46;

                    if (var42 == 0)
                    {
                        var25 = var17;
                        var45 = var20;
                        var27 = (float)var3;
                        var29 = (float)(var3 + 1);
                        var46 = (float)var5;
                        var30 = (float)var5;
                    }
                    else if (var42 == 1)
                    {
                        var25 = var19;
                        var45 = var18;
                        var27 = (float)(var3 + 1);
                        var29 = (float)var3;
                        var46 = (float)(var5 + 1);
                        var30 = (float)(var5 + 1);
                    }
                    else if (var42 == 2)
                    {
                        var25 = var18;
                        var45 = var17;
                        var27 = (float)var3;
                        var29 = (float)var3;
                        var46 = (float)(var5 + 1);
                        var30 = (float)var5;
                    }
                    else
                    {
                        var25 = var20;
                        var45 = var19;
                        var27 = (float)(var3 + 1);
                        var29 = (float)(var3 + 1);
                        var46 = (float)var5;
                        var30 = (float)(var5 + 1);
                    }

                    var12 = true;
                    Icon var31 = var6.getBlockTexture(var2, var3, var4, var5, var42 + 2);
                    double var32 = (double)var31.getMinU();
                    double var34 = (double)var31.getMaxU();
                    double var36 = (double)var31.getInterpolatedV((1.0D - (double)var25) * 16.0D);
                    double var38 = (double)var31.getInterpolatedV((double)(1.0F - var45) * 16.0D);
                    double var40 = (double)var31.getMaxV();
                    var8.setBrightness(var6.getMixedBrightnessForBlock(var2, var43, var4, var44));
                    var8.addVertexWithUV((double)var27, (double)((float)var4 + var25), (double)var46, var32, var36);
                    var8.addVertexWithUV((double)var29, (double)((float)var4 + var45), (double)var30, var34, var38);
                    var8.addVertexWithUV((double)var29, (double)(var4 + 0), (double)var30, var34, var40);
                    var8.addVertexWithUV((double)var27, (double)(var4 + 0), (double)var46, var32, var40);
                }
            }

            return var12;
        }
    }

    public float RenderCementGetCornerHeightFromNeighbours(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = 0;
        float var6 = 0.0F;

        for (int var7 = 0; var7 < 4; ++var7)
        {
            int var8 = var2 - (var7 & 1);
            int var10 = var4 - (var7 >> 1 & 1);

            if (var1.getBlockMaterial(var8, var3 + 1, var10) == FCBetterThanWolves.fcMaterialCement)
            {
                return 1.0F;
            }

            Material var11 = var1.getBlockMaterial(var8, var3, var10);

            if (var11 == FCBetterThanWolves.fcMaterialCement)
            {
                if (var1.isBlockOpaqueCube(var8, var3 + 1, var10))
                {
                    return 1.0F;
                }

                if (this.IsCementSourceBlock(var1, var8, var3, var10))
                {
                    var6 += this.GetRenderHeight(var1, var8, var3, var10) * 10.0F;
                    var5 += 10;
                }

                var6 += this.GetRenderHeight(var1, var8, var3, var10);
                ++var5;
            }
            else if (!var11.isSolid())
            {
                var6 += 0.6F;
                ++var5;
            }
        }

        if (var5 > 0)
        {
            return 1.0F - var6 / (float)var5;
        }
        else
        {
            return 1.0F;
        }
    }
}

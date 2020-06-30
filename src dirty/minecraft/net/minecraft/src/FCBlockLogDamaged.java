package net.minecraft.src;

import java.util.List;

public class FCBlockLogDamaged extends Block
{
    public static final float m_fHardness = 2.0F;
    private FCModelBlock[] m_blockModels;
    private FCModelBlock[] m_blockModelsNarrowOneSide;
    private FCModelBlock[] m_blockModelsNarrowTwoSides;
    private AxisAlignedBB[] m_boxSelectionArray;
    private static final float m_fRimWidth = 0.0625F;
    private static final float m_fLayerHeight = 0.125F;
    private static final float m_fFirstLayerHeight = 0.1875F;
    private static final float m_fLayerWidthGap = 0.0625F;
    private boolean m_bTempPosNarrow = false;
    private boolean m_bTempNegNarrow = false;
    private FCModelBlock m_tempCurrentModel;
    private Icon m_IconSide;
    private Icon m_IconStumpTop;

    public FCBlockLogDamaged(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialLog);
        this.setHardness(2.0F);
        this.SetAxesEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.SetBuoyant();
        this.SetFireProperties(5, 5);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        this.InitModels();
        Block.useNeighborBrightness[var1] = true;
        this.setLightOpacity(8);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockLogDamaged");
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
     * Returns the block hardness at a location. Args: world, x, y, z
     */
    public float getBlockHardness(World var1, int var2, int var3, int var4)
    {
        float var5 = super.getBlockHardness(var1, var2, var3, var4);
        var1.getBlockMetadata(var2, var3, var4);

        if (GetIsStump(var1, var2, var3, var4))
        {
            var5 *= 3.0F;
        }

        return var5;
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        if (var5 <= 1)
        {
            var9 = this.SetOrientation(var9, 0);
        }
        else if (var5 <= 3)
        {
            var9 = this.SetOrientation(var9, 2);
        }
        else
        {
            var9 = this.SetOrientation(var9, 1);
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
        this.CheckForReplaceWithSpike(var1, var2, var3, var4, var6);
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        int var7 = this.SetCurrentModelForBlock(var1, var2, var3, var4);
        FCModelBlock var8 = this.m_tempCurrentModel.MakeTemporaryCopy();
        var8.TiltToFacingAlongJ(var7);
        return var8.CollisionRayTrace(var1, var2, var3, var4, var5, var6);
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        int var7 = this.GetOrientation(var1, var2, var3, var4);
        return var7 == 0 ? var5 <= 1 : (var7 == 1 ? var5 >= 4 : var5 == 2 || var5 == 3);
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        boolean var7 = GetIsStump(var6);

        if (var7)
        {
            this.dropBlockAsItem_do(var1, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemSawDust));
        }

        this.dropBlockAsItem_do(var1, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemSawDust));
    }

    public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        return true;
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        int var7 = var2.getBlockMetadata(var3, var4, var5);
        int var8 = this.GetDamageLevel(var7);

        if (var8 >= 3)
        {
            if (!var2.isRemote)
            {
                if (GetIsStump(var7))
                {
                    FCUtilsItem.DropStackAsIfBlockHarvested(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemSawDust, 1));
                }

                FCUtilsItem.DropStackAsIfBlockHarvested(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemSawDust, 1));
            }

            return false;
        }
        else
        {
            ++var8;
            this.SetDamageLevel(var2, var3, var4, var5, var8);

            if (!var2.isRemote)
            {
                if (GetIsStump(var7))
                {
                    FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemSawDust, 1), var6);
                    FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemSawDust, 1), var6);
                }
                else if (var8 != 1 && var8 != 3)
                {
                    FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemSawDust, 1), var6);
                }
                else
                {
                    var2.playAuxSFX(2268, var3, var4, var5, 0);
                    FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(Item.stick, 1), var6);
                }
            }

            return true;
        }
    }

    public boolean GetIsProblemToRemove(IBlockAccess var1, int var2, int var3, int var4)
    {
        return GetIsStump(var1, var2, var3, var4);
    }

    public boolean GetDoesStumpRemoverWorkOnBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return GetIsStump(var1, var2, var3, var4);
    }

    /**
     * Return whether this block can drop from an explosion.
     */
    public boolean canDropFromExplosion(Explosion var1)
    {
        return false;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5)
    {
        float var6 = 1.0F;

        if (var5 != null)
        {
            var6 = 1.0F / var5.explosionSize;
        }

        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 1, 0, var6);
    }

    public boolean GetCanBlockBeIncinerated(World var1, int var2, int var3, int var4)
    {
        return !GetIsStump(var1, var2, var3, var4);
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 1000;
    }

    public void OnDestroyedByFire(World var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        if (GetIsStump(var1, var2, var3, var4))
        {
            int var7 = FCBetterThanWolves.fcBlockStumpCharred.SetDamageLevel(0, this.GetDamageLevel(var1, var2, var3, var4));
            var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockStumpCharred.blockID, var7);
        }
        else
        {
            super.OnDestroyedByFire(var1, var2, var3, var4, var5, var6);
        }
    }

    protected void InitModels()
    {
        this.m_blockModels = new FCModelBlock[4];
        this.m_blockModelsNarrowOneSide = new FCModelBlock[4];
        this.m_blockModelsNarrowTwoSides = new FCModelBlock[4];
        this.m_boxSelectionArray = new AxisAlignedBB[4];
        int var1;

        for (var1 = 0; var1 < 4; ++var1)
        {
            FCModelBlock var2 = this.m_blockModels[var1] = new FCModelBlock();
            FCModelBlock var3 = this.m_blockModelsNarrowOneSide[var1] = new FCModelBlock();
            FCModelBlock var4 = this.m_blockModelsNarrowTwoSides[var1] = new FCModelBlock();
            float var5 = 0.0625F + 0.0625F * (float)var1;
            float var6 = 0.0F;

            if (var1 > 0)
            {
                var6 = 0.1875F + 0.125F * (float)(var1 - 1);
            }

            var2.AddBox((double)var5, (double)var6, (double)var5, (double)(1.0F - var5), (double)(1.0F - var6), (double)(1.0F - var5));
            var3.AddBox((double)var5, (double)var6, (double)var5, (double)(1.0F - var5), 1.0D, (double)(1.0F - var5));
            var4.AddBox((double)var5, 0.0D, (double)var5, (double)(1.0F - var5), 1.0D, (double)(1.0F - var5));
            AxisAlignedBB var7 = this.m_boxSelectionArray[var1] = new AxisAlignedBB((double)var5, 0.0D, (double)var5, (double)(1.0F - var5), 1.0D, (double)(1.0F - var5));
        }

        for (var1 = 1; var1 < 4; ++var1)
        {
            this.m_blockModels[var1].AddBox(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.1875D, 0.9375D);
            this.m_blockModelsNarrowOneSide[var1].AddBox(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.1875D, 0.9375D);
            this.m_blockModels[var1].AddBox(0.0625D, 0.8125D, 0.0625D, 0.9375D, 1.0D, 0.9375D);
        }

        float var8 = 0.125F;
        float var9 = 0.1875F;

        for (int var10 = 2; var10 < 4; ++var10)
        {
            this.m_blockModels[var10].AddBox((double)var8, (double)var9, (double)var8, (double)(1.0F - var8), (double)(var9 + 0.125F), (double)(1.0F - var8));
            this.m_blockModelsNarrowOneSide[var10].AddBox((double)var8, (double)var9, (double)var8, (double)(1.0F - var8), (double)(var9 + 0.125F), (double)(1.0F - var8));
            this.m_blockModels[var10].AddBox((double)var8, (double)(1.0F - var9 - 0.125F), (double)var8, (double)(1.0F - var8), (double)(1.0F - var9), (double)(1.0F - var8));
        }

        var8 = 0.1875F;
        var9 = 0.3125F;
        this.m_blockModels[3].AddBox((double)var8, (double)var9, (double)var8, (double)(1.0F - var8), (double)(var9 + 0.125F), (double)(1.0F - var8));
        this.m_blockModelsNarrowOneSide[3].AddBox((double)var8, (double)var9, (double)var8, (double)(1.0F - var8), (double)(var9 + 0.125F), (double)(1.0F - var8));
        this.m_blockModels[3].AddBox((double)var8, (double)(1.0F - var9 - 0.125F), (double)var8, (double)(1.0F - var8), 0.875D, (double)(1.0F - var8));
    }

    public void SetDamageLevel(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -4;
        var6 |= var5;

        if (!this.CheckForReplaceWithSpike(var1, var2, var3, var4, var6))
        {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
        }
    }

    private boolean CheckForReplaceWithSpike(World var1, int var2, int var3, int var4, int var5)
    {
        if (this.GetDamageLevel(var5) == 3 && !GetIsStump(var5))
        {
            int var6 = this.SetConnectionFlagsForBlock(var1, var2, var3, var4, var5);

            if (this.m_bTempPosNarrow != this.m_bTempNegNarrow)
            {
                FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);
                var7.AddFacingAsOffset(var6);
                int var8 = var1.getBlockId(var7.i, var7.j, var7.k);
                Block var10000 = Block.blocksList[var8];

                if (var8 != this.blockID || this.GetOrientation(var5) != this.GetOrientation(var1, var7.i, var7.j, var7.k))
                {
                    var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockLogSpike.blockID, var6);
                    return true;
                }
            }
        }

        return false;
    }

    public int GetDamageLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetDamageLevel(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetDamageLevel(int var1)
    {
        return var1 & 3;
    }

    public int GetOrientation(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetOrientation(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetOrientation(int var1)
    {
        int var2 = var1 >> 2 & 3;

        if (var2 == 3)
        {
            var2 = 0;
        }

        return var2;
    }

    public int SetOrientation(int var1, int var2)
    {
        if (!GetIsStump(var1))
        {
            var1 |= var2 << 2;
        }

        return var1;
    }

    public static boolean GetIsStump(IBlockAccess var0, int var1, int var2, int var3)
    {
        return GetIsStump(var0.getBlockMetadata(var1, var2, var3));
    }

    public static boolean GetIsStump(int var0)
    {
        return (var0 & 12) == 12;
    }

    public int SetIsStump(int var1)
    {
        var1 |= 12;
        return var1;
    }

    public int SetCurrentModelForBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.SetConnectionFlagsForBlock(var1, var2, var3, var4);
        int var6 = this.GetDamageLevel(var1, var2, var3, var4);

        if (this.m_bTempPosNarrow)
        {
            if (this.m_bTempNegNarrow)
            {
                this.m_tempCurrentModel = this.m_blockModelsNarrowTwoSides[var6];
            }
            else
            {
                this.m_tempCurrentModel = this.m_blockModelsNarrowOneSide[var6];
            }
        }
        else
        {
            this.m_tempCurrentModel = this.m_blockModels[var6];
        }

        return var5;
    }

    public int SetConnectionFlagsForBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.SetConnectionFlagsForBlock(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4));
    }

    public int SetConnectionFlagsForBlock(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetOrientation(var5);
        int var7 = 1;

        if (var6 == 1)
        {
            var7 = 5;
        }
        else if (var6 == 2)
        {
            var7 = 3;
        }

        this.m_bTempPosNarrow = true;
        this.m_bTempNegNarrow = true;
        FCUtilsBlockPos var8 = new FCUtilsBlockPos(var2, var3, var4);
        var8.AddFacingAsOffset(var7);
        int var9 = var1.getBlockId(var8.i, var8.j, var8.k);
        Block var10000 = Block.blocksList[var9];

        if (this.DoesTargetBlockConnectToFacing(var6, var1, var8.i, var8.j, var8.k, Block.GetOppositeFacing(var7)))
        {
            this.m_bTempPosNarrow = false;
        }

        var8.Set(var2, var3, var4);
        var8.AddFacingAsOffset(Block.GetOppositeFacing(var7));
        var9 = var1.getBlockId(var8.i, var8.j, var8.k);
        var10000 = Block.blocksList[var9];

        if (GetIsStump(var5) || this.DoesTargetBlockConnectToFacing(var6, var1, var8.i, var8.j, var8.k, var7))
        {
            this.m_bTempNegNarrow = false;
        }

        if (!this.m_bTempPosNarrow && this.m_bTempNegNarrow)
        {
            var7 = Block.GetOppositeFacing(var7);
            this.m_bTempPosNarrow = true;
            this.m_bTempNegNarrow = false;
        }

        return var7;
    }

    public boolean DoesTargetBlockConnectToFacing(int var1, IBlockAccess var2, int var3, int var4, int var5, int var6)
    {
        int var7 = var2.getBlockId(var3, var4, var5);
        return var7 != this.blockID ? (var7 == FCBetterThanWolves.fcBlockLogSpike.blockID ? FCBetterThanWolves.fcBlockLogSpike.GetFacing(var2, var3, var4, var5) == Block.GetOppositeFacing(var6) : var7 == Block.wood.blockID) : this.GetDamageLevel(var2, var3, var4, var5) == 0 && var1 == this.GetOrientation(var2, var3, var4, var5);
    }

    public boolean IsItemEffectiveConversionTool(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        if (var1 != null)
        {
            Item var6 = var1.getItem();

            if (var6 instanceof FCItemChisel || var6 instanceof FCItemAxe || var6 == FCBetterThanWolves.fcItemBattleAxe)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("fcBlockLogChewedOak_top");
        this.m_IconSide = var1.registerIcon("fcBlockLogChewedOak_side");
        this.m_IconStumpTop = var1.registerIcon("fcBlockTrunkTop");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        int var3 = this.GetOrientation(var2);
        Icon var4 = this.blockIcon;

        if (var3 == 0)
        {
            if (var1 >= 2)
            {
                var4 = this.m_IconSide;
            }
            else if (var1 == 1 && GetIsStump(var2))
            {
                var4 = this.m_IconStumpTop;
            }
        }
        else if (var3 == 1)
        {
            if (var1 != 4 && var1 != 5)
            {
                var4 = this.m_IconSide;
            }
        }
        else if (var1 != 2 && var1 != 3)
        {
            var4 = this.m_IconSide;
        }

        return var4;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.m_currentBlockRenderer.ShouldSideBeRenderedBasedOnCurrentBounds(var2, var3, var4, var5);
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        int var5 = this.SetCurrentModelForBlock(var1.blockAccess, var2, var3, var4);
        FCModelBlock var6 = this.m_tempCurrentModel.MakeTemporaryCopy();
        var6.TiltToFacingAlongJ(var5);
        return var6.RenderAsBlock(var1, this, var2, var3, var4);
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        this.m_blockModelsNarrowOneSide[var2].RenderAsItemBlock(var1, this, var2);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < 4; ++var4)
        {
            var3.add(new ItemStack(var1, 1, var4));
        }
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        int var5 = this.SetConnectionFlagsForBlock(var1, var2, var3, var4);
        int var6 = this.GetDamageLevel(var1, var2, var3, var4);
        AxisAlignedBB var7 = this.m_boxSelectionArray[var6].MakeTemporaryCopy();
        var7.TiltToFacingAlongJ(var5);
        return var7.offset((double)var2, (double)var3, (double)var4);
    }
}

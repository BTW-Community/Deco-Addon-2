package net.minecraft.src;

import java.util.List;

public class AddonBlockLogDamaged extends FCBlockLogDamaged {
    private boolean m_bTempPosNarrow = false;
    private boolean m_bTempNegNarrow = false;
    private FCModelBlock[] m_blockModels;
    private FCModelBlock[] m_blockModelsNarrowOneSide;
    private FCModelBlock[] m_blockModelsNarrowTwoSides;
    private AxisAlignedBB[] m_boxSelectionArray;
    private FCModelBlock m_tempCurrentModel;
    
    private String topTexture;
    private String sideTexture;
    private String stumpTexture;
    
	public AddonBlockLogDamaged(int id, String side, String top, String stump) {
		super(id);
		topTexture = top;
		sideTexture = side;
		stumpTexture = stump;
	}

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
    	if (this.blockID == AddonDefs.logDamagedBlood.blockID)
    		var1.playAuxSFX(2225, var2, var3, var4, 0);
        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
    	if (this.blockID == AddonDefs.logDamagedBlood.blockID)
    		var2.playAuxSFX(2225, var3, var4, var5, 0);
    	
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
                
                if (this.blockID == AddonDefs.logDamagedBlood.blockID)
                	FCUtilsItem.DropStackAsIfBlockHarvested(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemSoulDust, 1));
                else
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
                    
                    if (this.blockID == AddonDefs.logDamagedBlood.blockID)
                        FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemSoulDust, 1), var6);
                    else
                        FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemSawDust, 1), var6);
                }
                else if (var8 != 1 && var8 != 3)
                {
                    if (this.blockID == AddonDefs.logDamagedBlood.blockID)
                        FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemSoulDust, 1), var6);
                    else
                        FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemSawDust, 1), var6);
                }
                else
                {
                    var2.playAuxSFX(AddonManager.addonShaftRippedOffLogAuxFXID, var3, var4, var5, 0);
                    FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(Item.stick, 1), var6);
                }
            }

            return true;
        }
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

        if (this.blockID == AddonDefs.logDamagedBlood.blockID)
        	this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSoulDust.itemID, 1, 0, var6);
        else
            this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 1, 0, var6);
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
                    var1.setBlockAndMetadataWithNotify(var2, var3, var4, AddonUtilsBlock.getLogSpikeFromBlockID(this.blockID), var6);
                    return true;
                }
            }
        }

        return false;
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
        return var7 != this.blockID ? ((var7 == FCBetterThanWolves.fcBlockLogSpike.blockID || var7 == AddonDefs.logSpikeSpruce.blockID || var7 == AddonDefs.logSpikeBirch.blockID || var7 == AddonDefs.logSpikeJungle.blockID || var7 == AddonDefs.logSpikeBlood.blockID || var7 == AddonDefs.logSpikeCherry.blockID) ? FCBetterThanWolves.fcBlockLogSpike.GetFacing(var2, var3, var4, var5) == Block.GetOppositeFacing(var6) : 
        	(var7 == Block.wood.blockID || var7 == AddonDefs.barkLog.blockID || var7 == AddonDefs.barkLogStripped.blockID || var7 == AddonDefs.strippedLog.blockID || var7 == AddonDefs.cherryLog.blockID || var7 == FCBetterThanWolves.fcBloodWood.blockID || var7 == AddonDefs.bloodLog.blockID || var7 == AddonDefs.cherryStump.blockID || 
        	var7 == FCBetterThanWolves.fcBlockLogDamaged.blockID || var7 == AddonDefs.logDamagedSpruce.blockID || var7 == AddonDefs.logDamagedBirch.blockID || var7 == AddonDefs.logDamagedJungle.blockID || var7 == AddonDefs.logDamagedBlood.blockID || var7 == AddonDefs.logDamagedCherry.blockID)) : 
        		this.GetDamageLevel(var2, var3, var4, var5) == 0 && var1 == this.GetOrientation(var2, var3, var4, var5);
    }

    //CLIENT ONLY
    private Icon m_IconSide;
    private Icon m_IconStumpTop;
    
    public boolean RenderBlock(RenderBlocks render, int x, int y, int z)
    {
        int var5 = this.SetCurrentModelForBlock(render.blockAccess, x, y, z);
        FCModelBlock var6 = this.m_tempCurrentModel.MakeTemporaryCopy();
        var6.TiltToFacingAlongJ(var5);

        int var7 = render.blockAccess.getBlockMetadata(x, y, z);

        if (var7 == 4 || var7 == 5 || var7 == 6 || var7 == 7)
        {
			render.SetUvRotateTop(1);
			render.SetUvRotateBottom(1);
			render.SetUvRotateWest(1);
			render.SetUvRotateEast(1);
        }
        else if (var7 == 8 || var7 == 9 || var7 == 10 || var7 == 11)
        {
			render.SetUvRotateNorth(1);
			render.SetUvRotateSouth(1);
        }
        
        var6.RenderAsBlock(render, this, x, y, z);
		render.ClearUvRotation();
		return true;
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        this.m_blockModelsNarrowOneSide[var2].RenderAsItemBlock(var1, this, var2);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon(topTexture);
        this.m_IconSide = var1.registerIcon(sideTexture);
        this.m_IconStumpTop = var1.registerIcon(stumpTexture);
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
}
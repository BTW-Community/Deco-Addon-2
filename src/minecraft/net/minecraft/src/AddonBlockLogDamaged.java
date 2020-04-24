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
        return var7 != this.blockID ? (var7 == FCBetterThanWolves.fcBlockLogSpike.blockID ? FCBetterThanWolves.fcBlockLogSpike.GetFacing(var2, var3, var4, var5) == Block.GetOppositeFacing(var6) : 
        	(var7 == Block.wood.blockID || var7 == AddonDefs.barkLog.blockID || var7 == AddonDefs.barkLogStripped.blockID || var7 == AddonDefs.strippedLog.blockID || var7 == AddonDefs.cherryLog.blockID || var7 == AddonDefs.cherryStump.blockID)) : 
        		this.GetDamageLevel(var2, var3, var4, var5) == 0 && var1 == this.GetOrientation(var2, var3, var4, var5);
    }

    //CLIENT ONLY
    private Icon m_IconSide;
    private Icon m_IconStumpTop;
    
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
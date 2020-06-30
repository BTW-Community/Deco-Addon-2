package net.minecraft.src;

import java.util.Random;

public class FCBlockHandCrank extends Block implements FCIBlockMechanical
{
    private static int m_iHandCrankTickRate = 3;
    private static int m_iHandCrankDelayBeforeReset = 15;
    private static double m_dBaseHeight = 0.25D;
    private static double m_dShaftSelectionWidth = 0.25D;
    private static double m_dShaftSelectionHalfWidth = m_dShaftSelectionWidth / 2.0D;
    private Icon[] m_IconBySideArray = new Icon[6];
    private Icon m_IconShaft;

    protected FCBlockHandCrank(int var1)
    {
        super(var1, Material.circuits);
        this.setHardness(0.5F);
        this.SetPicksEffectiveOn();
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockHandCrank");
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return m_iHandCrankTickRate;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return null;
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
        return this.CanRestAtPosition(var1, var2, var3, var4) && super.canPlaceBlockAt(var1, var2, var3, var4);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        int var10 = var1.getBlockMetadata(var2, var3, var4);

        if (var10 == 0)
        {
            if (var5.getFoodStats().getFoodLevel() > 18)
            {
                var5.addExhaustion(2.0F);

                if (!var1.isRemote)
                {
                    if (!this.CheckForOverpower(var1, var2, var3, var4))
                    {
                        var1.setBlockMetadataWithNotify(var2, var3, var4, 1);
                        var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
                        var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.click", 1.0F, 2.0F);
                        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
                        var1.markBlockForUpdate(var2, var3, var4);
                    }
                    else
                    {
                        this.BreakCrankWithDrop(var1, var2, var3, var4);
                    }
                }
            }
            else if (var1.isRemote)
            {
                var5.addChatMessage("You\'re too exhausted for manual labor.");
                return false;
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (var6 > 0)
        {
            if (var6 < 7)
            {
                if (var6 <= 6)
                {
                    var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.click", 1.0F, 2.0F);
                }

                if (var6 <= 5)
                {
                    var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1) + var6);
                }
                else
                {
                    var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, m_iHandCrankDelayBeforeReset);
                }

                var1.setBlockMetadata(var2, var3, var4, var6 + 1);
            }
            else
            {
                var1.setBlockMetadataWithNotify(var2, var3, var4, 0);
                var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
                var1.markBlockForUpdate(var2, var3, var4);
                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.click", 0.3F, 0.7F);
            }
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (var6 > 0 && !var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!this.CanRestAtPosition(var1, var2, var3, var4))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCUtilsRayTraceVsComplexBlock var7 = new FCUtilsRayTraceVsComplexBlock(var1, var2, var3, var4, var5, var6);
        var7.AddBoxWithLocalCoordsToIntersectionList(this.GetBaseBoundsFromPool());
        var7.AddBoxWithLocalCoordsToIntersectionList(this.GetShaftSelectionBoundsFromPool());
        return var7.GetFirstIntersection();
    }

    public boolean CanOutputMechanicalPower()
    {
        return true;
    }

    public boolean CanInputMechanicalPower()
    {
        return false;
    }

    public boolean CanInputAxlePowerToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return false;
    }

    public boolean IsInputtingMechanicalPower(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean IsOutputtingMechanicalPower(World var1, int var2, int var3, int var4)
    {
        return var1.getBlockMetadata(var2, var3, var4) > 0;
    }

    public void Overpower(World var1, int var2, int var3, int var4) {}

    public boolean CanRestAtPosition(World var1, int var2, int var3, int var4)
    {
        return FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true);
    }

    public boolean CheckForOverpower(World var1, int var2, int var3, int var4)
    {
        int var5 = 0;

        for (int var6 = 0; var6 <= 5; ++var6)
        {
            if (var6 != 1)
            {
                FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);
                var7.AddFacingAsOffset(var6);
                int var8 = var1.getBlockId(var7.i, var7.j, var7.k);
                Block var9 = Block.blocksList[var8];

                if (var9 != null && var9 instanceof FCIBlockMechanical)
                {
                    FCIBlockMechanical var10 = (FCIBlockMechanical)var9;

                    if (var10.CanInputMechanicalPower())
                    {
                        ++var5;
                    }
                }
            }
        }

        return var5 > 1;
    }

    public void BreakCrankWithDrop(World var1, int var2, int var3, int var4)
    {
        FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, Item.stick.itemID, 0);
        FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcItemGear.itemID, 0);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemStone.itemID, 12, 0, 0.75F);
        var1.playAuxSFX(2235, var2, var3, var4, 0);
        var1.setBlockWithNotify(var2, var3, var4, 0);
    }

    protected AxisAlignedBB GetBaseBoundsFromPool()
    {
        return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, m_dBaseHeight, 1.0D);
    }

    protected AxisAlignedBB GetShaftSelectionBoundsFromPool()
    {
        return AxisAlignedBB.getAABBPool().getAABB(0.0D, m_dBaseHeight, 0.5D - m_dShaftSelectionHalfWidth, 1.0D, 1.0D, 0.5D + m_dShaftSelectionHalfWidth);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("stone");
        this.m_IconShaft = var1.registerIcon("fcBlockHandCrank_shaft");
        this.m_IconBySideArray[0] = var1.registerIcon("fcBlockHandCrank_bottom");
        this.m_IconBySideArray[1] = var1.registerIcon("fcBlockHandCrank_top");
        Icon var2 = var1.registerIcon("fcBlockHandCrank_side");
        this.m_IconBySideArray[2] = var2;
        this.m_IconBySideArray[3] = var2;
        this.m_IconBySideArray[4] = var2;
        this.m_IconBySideArray[5] = var2;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return this.m_IconBySideArray[var1];
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return var5 != 1 ? super.shouldSideBeRendered(var1, var2, var3, var4, var5) : true;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        Tessellator var6 = Tessellator.instance;
        var1.setRenderBounds(this.GetBaseBoundsFromPool());
        var1.renderStandardBlock(this, var2, var3, var4);
        float var7 = this.getBlockBrightness(var5, var2, var3, var4);

        if (Block.lightValue[this.blockID] > 0)
        {
            var7 = 1.0F;
        }

        var6.setColorOpaque_F(var7, var7, var7);
        Icon var8 = this.m_IconShaft;

        if (var1.hasOverrideBlockTexture())
        {
            var8 = var1.GetOverrideTexture();
        }

        double var9 = (double)var8.getMinU();
        double var11 = (double)var8.getMaxU();
        double var13 = (double)var8.getMinV();
        double var15 = (double)var8.getMaxV();
        Vec3[] var17 = new Vec3[8];
        float var18 = 0.0625F;
        float var19 = 0.0625F;
        float var20 = 0.9F;
        var17[0] = Vec3.createVectorHelper((double)(-var18), 0.0D, (double)(-var19));
        var17[1] = Vec3.createVectorHelper((double)var18, 0.0D, (double)(-var19));
        var17[2] = Vec3.createVectorHelper((double)var18, 0.0D, (double)var19);
        var17[3] = Vec3.createVectorHelper((double)(-var18), 0.0D, (double)var19);
        var17[4] = Vec3.createVectorHelper((double)(-var18), (double)var20, (double)(-var19));
        var17[5] = Vec3.createVectorHelper((double)var18, (double)var20, (double)(-var19));
        var17[6] = Vec3.createVectorHelper((double)var18, (double)var20, (double)var19);
        var17[7] = Vec3.createVectorHelper((double)(-var18), (double)var20, (double)var19);
        boolean var21 = var5.getBlockMetadata(var2, var3, var4) > 0;

        for (int var22 = 0; var22 < 8; ++var22)
        {
            if (var21)
            {
                var17[var22].zCoord -= 0.0625D;
                var17[var22].rotateAroundX(0.35F);
            }
            else
            {
                var17[var22].zCoord += 0.0625D;
                var17[var22].rotateAroundX(-0.35F);
            }

            var17[var22].rotateAroundY(((float)Math.PI / 2F));
            var17[var22].xCoord += (double)var2 + 0.5D;
            var17[var22].yCoord += (double)((float)var3 + 0.125F);
            var17[var22].zCoord += (double)var4 + 0.5D;
        }

        Vec3 var27 = null;
        Vec3 var23 = null;
        Vec3 var24 = null;
        Vec3 var25 = null;

        for (int var26 = 0; var26 < 6; ++var26)
        {
            if (var26 == 0)
            {
                var9 = (double)var8.getInterpolatedU(7.0D);
                var11 = (double)var8.getInterpolatedU(9.0D);
                var13 = (double)var8.getMinV();
                var15 = (double)var8.getInterpolatedV(2.0D);
            }
            else if (var26 == 2)
            {
                var9 = (double)var8.getInterpolatedU(7.0D);
                var11 = (double)var8.getInterpolatedU(9.0D);
                var13 = (double)var8.getMinV();
                var15 = (double)var8.getMaxV();
            }

            if (var26 == 0)
            {
                var27 = var17[0];
                var23 = var17[1];
                var24 = var17[2];
                var25 = var17[3];
            }
            else if (var26 == 1)
            {
                var27 = var17[7];
                var23 = var17[6];
                var24 = var17[5];
                var25 = var17[4];
            }
            else if (var26 == 2)
            {
                var27 = var17[1];
                var23 = var17[0];
                var24 = var17[4];
                var25 = var17[5];
            }
            else if (var26 == 3)
            {
                var27 = var17[2];
                var23 = var17[1];
                var24 = var17[5];
                var25 = var17[6];
            }
            else if (var26 == 4)
            {
                var27 = var17[3];
                var23 = var17[2];
                var24 = var17[6];
                var25 = var17[7];
            }
            else if (var26 == 5)
            {
                var27 = var17[0];
                var23 = var17[3];
                var24 = var17[7];
                var25 = var17[4];
            }

            var6.addVertexWithUV(var27.xCoord, var27.yCoord, var27.zCoord, var9, var15);
            var6.addVertexWithUV(var23.xCoord, var23.yCoord, var23.zCoord, var11, var15);
            var6.addVertexWithUV(var24.xCoord, var24.yCoord, var24.zCoord, var11, var13);
            var6.addVertexWithUV(var25.xCoord, var25.yCoord, var25.zCoord, var9, var13);
        }

        return true;
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        var1.setRenderBounds(this.GetBaseBoundsFromPool());
        FCClientUtilsRender.RenderInvBlockWithMetadata(var1, this, -0.5F, -0.5F, -0.5F, 0);
        float var4 = 0.0625F;
        var1.setRenderBounds((double)(0.5F - var4), m_dBaseHeight, (double)(0.5F - var4), (double)(0.5F + var4), 1.0D, (double)(0.5F + var4));
        FCClientUtilsRender.RenderInvBlockWithTexture(var1, this, -0.5F, -0.5F, -0.5F, this.m_IconShaft);
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return this.GetShaftSelectionBoundsFromPool().offset((double)var2, (double)var3, (double)var4);
    }
}

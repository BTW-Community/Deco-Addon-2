package net.minecraft.src;

import java.util.Random;

public class FCBlockMillStone extends BlockContainer implements FCIBlockMechanical
{
    private static int m_iTickRate = 10;
    public static final int m_iContentsNothing = 0;
    public static final int m_iContentsNormalGrinding = 1;
    public static final int m_iContentsNetherrack = 2;
    public static final int m_iContentsCompanionCube = 3;
    public static final int m_iContentsJammed = 4;
    public static final FCModelBlockMillStone m_model = new FCModelBlockMillStone();
    private Icon[] m_iconsBySide = new Icon[6];
    private Icon[] m_iconsBySideFull = new Icon[6];
    private Icon[] m_iconsBySideOn = new Icon[6];
    private Icon[] m_iconsBySideOnFull = new Icon[6];
    private boolean m_bRenderingBase = false;

    protected FCBlockMillStone(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(3.5F);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("fcBlockMillStone");
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabRedstone);
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
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return m_iTickRate;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        int var10 = this.GetCurrentGrindingType(var1, var2, var3, var4);
        FCTileEntityMillStone var11 = (FCTileEntityMillStone)var1.getBlockTileEntity(var2, var3, var4);

        if (var10 == 0)
        {
            ItemStack var12 = var5.getCurrentEquippedItem();

            if (var12 != null)
            {
                if (!var1.isRemote)
                {
                    var1.playAuxSFX(2231, var2, var3, var4, 0);
                    var11.AttemptToAddSingleItemFromStack(var12);
                }
                else
                {
                    --var12.stackSize;
                }
            }
        }
        else if (!var1.isRemote)
        {
            var1.playAuxSFX(2231, var2, var3, var4, 0);
            var11.EjectContents(var6);
        }

        return true;
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        FCUtilsInventory.EjectInventoryContents(var1, var2, var3, var4, (IInventory)var1.getBlockTileEntity(var2, var3, var4));
        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityMillStone();
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        boolean var6 = this.IsInputtingMechanicalPower(var1, var2, var3, var4);
        boolean var7 = this.GetIsMechanicalOn(var1, var2, var3, var4);

        if (var7 != var6)
        {
            this.SetIsMechanicalOn(var1, var2, var3, var4, var6);
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.IsCurrentStateValid(var1, var2, var3, var4) && !var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID))
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
        if (!this.IsCurrentStateValid(var1, var2, var3, var4) && !var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCUtilsRayTraceVsComplexBlock var7 = new FCUtilsRayTraceVsComplexBlock(var1, var2, var3, var4, var5, var6);
        m_model.AddToRayTrace(var7);
        var7.AddBoxWithLocalCoordsToIntersectionList(m_model.m_boxBase);
        return var7.GetFirstIntersection();
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return true;
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var5 == 0 || var6;
    }

    public boolean CanOutputMechanicalPower()
    {
        return false;
    }

    public boolean CanInputMechanicalPower()
    {
        return true;
    }

    public boolean IsInputtingMechanicalPower(World var1, int var2, int var3, int var4)
    {
        return FCUtilsMechPower.IsBlockPoweredByAxle(var1, var2, var3, var4, this) || FCUtilsMechPower.IsBlockPoweredByHandCrank(var1, var2, var3, var4);
    }

    public boolean CanInputAxlePowerToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return var5 < 2;
    }

    public boolean IsOutputtingMechanicalPower(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public void Overpower(World var1, int var2, int var3, int var4)
    {
        this.BreakMillStone(var1, var2, var3, var4);
    }

    public boolean GetIsMechanicalOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsMechanicalOn(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean GetIsMechanicalOn(int var1)
    {
        return (var1 & 1) > 0;
    }

    public void SetIsMechanicalOn(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.SetIsMechanicalOn(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetIsMechanicalOn(int var1, boolean var2)
    {
        return var2 ? var1 | 1 : var1 & -2;
    }

    public int GetCurrentGrindingType(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetCurrentGrindingType(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetCurrentGrindingType(int var1)
    {
        return (var1 & 14) >> 1;
    }

    public void SetCurrentGrindingType(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.SetCurrentGrindingType(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithClient(var2, var3, var4, var6);
    }

    public int SetCurrentGrindingType(int var1, int var2)
    {
        var1 &= -15;
        return var1 | var2 << 1;
    }

    private void BreakMillStone(World var1, int var2, int var3, int var4)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemStone.itemID, 16, 0, 0.75F);
        var1.playAuxSFX(2235, var2, var3, var4, 0);
        var1.setBlockWithNotify(var2, var3, var4, 0);
    }

    public boolean IsCurrentStateValid(World var1, int var2, int var3, int var4)
    {
        boolean var5 = this.IsInputtingMechanicalPower(var1, var2, var3, var4);
        boolean var6 = this.GetIsMechanicalOn(var1, var2, var3, var4);
        return var6 == var5;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("stone");
        this.m_iconsBySideFull[0] = this.m_iconsBySide[0] = var1.registerIcon("fcBlockMillStone_bottom");
        this.m_iconsBySideFull[1] = this.m_iconsBySide[1] = var1.registerIcon("fcBlockMillStone_top");
        this.m_iconsBySideOn[0] = this.m_iconsBySideOnFull[0] = var1.registerIcon("fcBlockMillStone_bottom_on");
        this.m_iconsBySideOn[1] = this.m_iconsBySideOnFull[1] = var1.registerIcon("fcBlockMillStone_top_on");
        Icon var2 = var1.registerIcon("fcBlockMillStone_side");
        Icon var3 = var1.registerIcon("fcBlockMillStone_side_full");
        Icon var4 = var1.registerIcon("fcBlockMillStone_side_on");
        Icon var5 = var1.registerIcon("fcBlockMillStone_side_on_full");

        for (int var6 = 2; var6 <= 5; ++var6)
        {
            this.m_iconsBySide[var6] = var2;
            this.m_iconsBySideFull[var6] = var3;
            this.m_iconsBySideOn[var6] = var4;
            this.m_iconsBySideOnFull[var6] = var5;
        }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return this.m_bRenderingBase ? this.m_iconsBySide[var1] : (this.GetIsMechanicalOn(var2) ? (this.GetCurrentGrindingType(var2) == 0 ? this.m_iconsBySideOn[var1] : this.m_iconsBySideOnFull[var1]) : (this.GetCurrentGrindingType(var2) == 0 ? this.m_iconsBySide[var1] : this.m_iconsBySideFull[var1]));
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
        this.m_bRenderingBase = true;
        m_model.m_boxBase.RenderAsBlock(var1, this, var2, var3, var4);
        this.m_bRenderingBase = false;
        return m_model.RenderAsBlock(var1, this, var2, var3, var4);
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        this.m_bRenderingBase = true;
        m_model.m_boxBase.RenderAsItemBlock(var1, this, var2);
        this.m_bRenderingBase = false;
        m_model.RenderAsItemBlock(var1, this, var2);
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.GetIsMechanicalOn(var1, var2, var3, var4))
        {
            int var6 = this.GetCurrentGrindingType(var1, var2, var3, var4);
            this.EmitMillingParticles(var1, var2, var3, var4, var6, var5);

            if (var6 == 4)
            {
                var1.playSound((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "minecart.base", 1.0F + var1.rand.nextFloat() * 0.1F, 1.25F + var1.rand.nextFloat() * 0.1F);
            }
            else if (var6 != 0)
            {
                var1.playSound((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "minecart.base", 1.0F + var1.rand.nextFloat() * 0.1F, 0.75F + var1.rand.nextFloat() * 0.1F);
            }
            else if (var5.nextInt(2) == 0)
            {
                var1.playSound((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "minecart.base", 1.5F + var5.nextFloat() * 0.1F, 0.5F + var5.nextFloat() * 0.1F);
            }

            if (var6 == 2)
            {
                if (var5.nextInt(3) <= 1)
                {
                    var1.playSound((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "mob.ghast.scream", 0.75F, var5.nextFloat() * 0.4F + 0.8F);
                }
            }
            else if (var6 == 3 && var5.nextInt(3) <= 1)
            {
                var1.playSound((double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), "mob.wolf.hurt", 2.0F, (var5.nextFloat() - var5.nextFloat()) * 0.2F + 1.0F);
            }
        }
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        AxisAlignedBB var5 = m_model.m_boxSelection.MakeTemporaryCopy();
        var5.offset((double)var2, (double)var3, (double)var4);
        return var5;
    }

    public void ClientNotificationOfMetadataChange(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        if (!this.GetIsMechanicalOn(var5) && this.GetIsMechanicalOn(var6))
        {
            int var7 = this.GetCurrentGrindingType(var6);

            if (var7 == 3)
            {
                var1.playSound((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "mob.wolf.hurt", 5.0F, (var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.2F + 1.0F);
            }

            var1.playSound((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "minecart.base", 1.5F + var1.rand.nextFloat() * 0.1F, 0.5F + var1.rand.nextFloat() * 0.1F);
            this.EmitMillingParticles(var1, var2, var3, var4, var7, var1.rand);
        }
    }

    private void EmitMillingParticles(World var1, int var2, int var3, int var4, int var5, Random var6)
    {
        String var7;

        if (var5 == 0)
        {
            var7 = "smoke";
        }
        else if (var5 == 4)
        {
            var7 = "largesmoke";
        }
        else
        {
            var7 = "fcwhitesmoke";
        }

        for (int var8 = 0; var8 < 5; ++var8)
        {
            float var9 = (float)var2 + var6.nextFloat();
            float var10 = (float)var3 + var6.nextFloat() * 0.5F + 1.0F;
            float var11 = (float)var4 + var6.nextFloat();
            var1.spawnParticle(var7, (double)var9, (double)var10, (double)var11, 0.0D, 0.0D, 0.0D);
        }
    }
}

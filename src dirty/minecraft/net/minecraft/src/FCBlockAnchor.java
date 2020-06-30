package net.minecraft.src;

public class FCBlockAnchor extends Block
{
    public static double m_dAnchorBaseHeight = 0.375D;
    private Icon m_IconFront;
    public Icon m_IconNub;
    private Icon m_IconRope;

    protected FCBlockAnchor(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(2.0F);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, m_dAnchorBaseHeight, 1.0D);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("fcBlockAnchor");
        this.setCreativeTab(CreativeTabs.tabTransport);
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);

        switch (var5)
        {
            case 0:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 1.0D - m_dAnchorBaseHeight, 0.0D, 1.0D, 1.0D, 1.0D);

            case 1:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, m_dAnchorBaseHeight, 1.0D);

            case 2:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 1.0D - m_dAnchorBaseHeight, 1.0D, 1.0D, 1.0D);

            case 3:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, m_dAnchorBaseHeight);

            case 4:
                return AxisAlignedBB.getAABBPool().getAABB(1.0D - m_dAnchorBaseHeight, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

            default:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, m_dAnchorBaseHeight, 1.0D, 1.0D);
        }
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
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        return this.SetFacing(var9, var5);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        ItemStack var10 = var5.getCurrentEquippedItem();

        if (var10 != null)
        {
            return false;
        }
        else
        {
            this.RetractRope(var1, var2, var3, var4, var5);
            return true;
        }
    }

    public int GetFacing(int var1)
    {
        return var1;
    }

    public int SetFacing(int var1, int var2)
    {
        return var2;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        return var5 != 0;
    }

    public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        var6 = Block.CycleFacing(var6, var5);
        this.SetFacing(var1, var2, var3, var4, var6);
        var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
        return true;
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return Block.GetOppositeFacing(var5) == this.GetFacing(var1, var2, var3, var4);
    }

    void RetractRope(World var1, int var2, int var3, int var4, EntityPlayer var5)
    {
        for (int var6 = var3 - 1; var6 >= 0; --var6)
        {
            int var7 = var1.getBlockId(var2, var6, var4);

            if (var7 != FCBetterThanWolves.fcRopeBlock.blockID)
            {
                break;
            }

            if (var1.getBlockId(var2, var6 - 1, var4) != FCBetterThanWolves.fcRopeBlock.blockID)
            {
                this.AddRopeToPlayerInventory(var1, var2, var3, var4, var5);
                Block var8 = FCBetterThanWolves.fcRopeBlock;

                if (!var1.isRemote)
                {
                    var1.playAuxSFX(2001, var2, var3, var4, var7);
                    var1.setBlockWithNotify(var2, var6, var4, 0);
                }

                break;
            }
        }
    }

    private void AddRopeToPlayerInventory(World var1, int var2, int var3, int var4, EntityPlayer var5)
    {
        ItemStack var6 = new ItemStack(FCBetterThanWolves.fcItemRope);

        if (var5.inventory.addItemStackToInventory(var6))
        {
            var1.playSoundAtEntity(var5, "random.pop", 0.2F, ((var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        }
        else
        {
            FCUtilsItem.EjectStackWithRandomOffset(var1, var2, var3, var4, var6);
        }
    }

    public boolean NotifyAnchorBlockOfAttachedPulleyStateChange(FCTileEntityPulley var1, World var2, int var3, int var4, int var5)
    {
        byte var6 = 0;

        if (var1.IsRaising())
        {
            if (var2.getBlockId(var3, var4 + 1, var5) == FCBetterThanWolves.fcRopeBlock.blockID)
            {
                var6 = 1;
            }
        }
        else if (var1.IsLowering() && (var2.isAirBlock(var3, var4 - 1, var5) || var2.getBlockId(var3, var4 - 1, var5) == FCBetterThanWolves.fcPlatform.blockID))
        {
            var6 = -1;
        }

        if (var6 != 0)
        {
            this.ConvertAnchorToEntity(var2, var3, var4, var5, var1, var6);
            return true;
        }
        else
        {
            return false;
        }
    }

    private void ConvertAnchorToEntity(World var1, int var2, int var3, int var4, FCTileEntityPulley var5, int var6)
    {
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var5.xCoord, var5.yCoord, var5.zCoord);
        FCEntityMovingAnchor var8 = new FCEntityMovingAnchor(var1, (double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), var7, var6);
        var1.spawnEntityInWorld(var8);
        this.ConvertConnectedPlatformsToEntities(var1, var2, var3, var4, var8);
        var1.setBlockWithNotify(var2, var3, var4, 0);
    }

    private void ConvertConnectedPlatformsToEntities(World var1, int var2, int var3, int var4, FCEntityMovingAnchor var5)
    {
        int var6 = var3 - 1;
        int var7 = var1.getBlockId(var2, var6, var4);

        if (var7 == FCBetterThanWolves.fcPlatform.blockID)
        {
            ((FCBlockPlatform)FCBetterThanWolves.fcPlatform).CovertToEntitiesFromThisPlatform(var1, var2, var6, var4, var5);
        }
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_IconFront = var1.registerIcon("fcBlockAnchor_front");
        this.m_IconNub = var1.registerIcon("fcBlockAnchor_nub");
        this.m_IconRope = var1.registerIcon("fcBlockRope");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var1 < 2 ? this.m_IconFront : this.blockIcon;
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        return var5 != var6 && var5 != Block.GetOppositeFacing(var6) ? this.blockIcon : this.m_IconFront;
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
        IBlockAccess var5 = var1.blockAccess;
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        var1.renderStandardBlock(this, var2, var3, var4);
        int var6 = this.GetFacing(var5, var2, var3, var4);
        double var7 = 0.125D;
        double var9 = 0.125D;
        double var11 = 0.25D;
        var1.setRenderBounds(0.5D - var9, m_dAnchorBaseHeight, 0.5D - var7, 0.5D + var9, m_dAnchorBaseHeight + var11, 0.5D + var7);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var1, this, var2, var3, var4, this.m_IconNub);
        boolean var13 = false;
        var7 = 0.0625D;
        var9 = 0.0625D;
        var11 = m_dAnchorBaseHeight;

        if (var6 == 1)
        {
            int var14 = var5.getBlockId(var2, var3 + 1, var4);

            if (var14 == FCBetterThanWolves.fcRopeBlock.blockID || var14 == FCBetterThanWolves.fcPulley.blockID)
            {
                var1.setRenderBounds(0.5D - var9, var11, 0.5D - var7, 0.5D + var9, 1.0D, 0.5D + var7);
                var13 = true;
            }
        }
        else if (var5.getBlockId(var2, var3 - 1, var4) == FCBetterThanWolves.fcRopeBlock.blockID)
        {
            var1.setRenderBounds(0.5D - var9, 0.0D, 0.5D - var7, 0.5D + var9, var11, 0.5D + var7);
            var13 = true;
        }

        if (var13)
        {
            FCClientUtilsRender.RenderStandardBlockWithTexture(var1, this, var2, var3, var4, this.m_IconRope);
        }

        return true;
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        var1.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, m_dAnchorBaseHeight, 1.0D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var1, this, -0.5F, -0.25F, -0.5F, 1);
        float var4 = 0.125F;
        float var5 = 0.125F;
        float var6 = 0.25F;
        var1.setRenderBounds((double)(0.5F - var5), m_dAnchorBaseHeight, (double)(0.5F - var4), (double)(0.5F + var5), m_dAnchorBaseHeight + (double)var6, (double)(0.5F + var4));
        FCClientUtilsRender.RenderInvBlockWithTexture(var1, this, -0.5F, -0.25F, -0.5F, this.m_IconNub);
    }
}

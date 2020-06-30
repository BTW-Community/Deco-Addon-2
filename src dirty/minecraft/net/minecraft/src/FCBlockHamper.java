package net.minecraft.src;

public class FCBlockHamper extends FCBlockBasket
{
    public static final FCModelBlockHamper m_model = new FCModelBlockHamper();
    private boolean m_bRenderingBase = false;
    private Icon m_iconBaseOpenTop;
    private Icon m_iconFront;
    private Icon m_iconTop;
    private Icon m_iconBottom;

    protected FCBlockHamper(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockHamper");
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityHamper();
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (!var1.isRemote && !FCUtilsWorld.DoesBlockHaveCenterHardpointToFacing(var1, var2, var3 + 1, var4, 0, true) && !FCUtilsWorld.IsBlockRestingOnThatBelow(var1, var2, var3 + 1, var4))
        {
            FCTileEntityHamper var10 = (FCTileEntityHamper)var1.getBlockTileEntity(var2, var3, var4);

            if (var5 instanceof EntityPlayerMP)
            {
                FCContainerHamper var11 = new FCContainerHamper(var5.inventory, var10);
                FCBetterThanWolves.ServerOpenCustomInterface((EntityPlayerMP)var5, var11, FCBetterThanWolves.fcHamperContainerID);
            }
        }

        return true;
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var5 == 0 || var5 == 1 && !this.GetIsOpen(var1, var2, var3, var4);
    }

    public void OnCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        if (!var1.isRemote)
        {
            this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemWickerPiece.itemID, 2, 0, 0.75F);
        }
    }

    public FCModelBlock GetLidModel(int var1)
    {
        return m_model.m_lid;
    }

    public Vec3 GetLidRotationPoint()
    {
        return m_model.GetLidRotationPoint();
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_iconBaseOpenTop = var1.registerIcon("fcBlockHamper_open_top");
        this.m_iconFront = var1.registerIcon("fcBlockHamper_front");
        this.m_iconTop = var1.registerIcon("fcBlockHamper_top");
        this.m_iconBottom = var1.registerIcon("fcBlockHamper_bottom");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        if (var1 == 1)
        {
            return this.m_bRenderingBase ? this.m_iconBaseOpenTop : this.m_iconTop;
        }
        else if (var1 == 0)
        {
            return this.m_iconBottom;
        }
        else
        {
            int var3 = this.GetFacing(var2);
            return var1 == var3 ? this.m_iconFront : super.getIcon(var1, var2);
        }
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
        int var5 = var1.blockAccess.getBlockMetadata(var2, var3, var4);
        int var6 = this.GetFacing(var5);
        FCModelBlock var7 = m_model.MakeTemporaryCopy();
        var7.RotateAroundJToFacing(var6);
        var1.SetUvRotateTop(this.ConvertFacingToTopTextureRotation(var6));
        var1.SetUvRotateBottom(this.ConvertFacingToBottomTextureRotation(var6));
        this.m_bRenderingBase = true;
        var7.RenderAsBlock(var1, this, var2, var3, var4);
        this.m_bRenderingBase = false;

        if (!this.GetIsOpen(var5))
        {
            var7 = m_model.m_lid.MakeTemporaryCopy();
            var7.RotateAroundJToFacing(var6);
            var7.RenderAsBlock(var1, this, var2, var3, var4);
        }

        var1.ClearUvRotation();
        return false;
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        m_model.RenderAsItemBlock(var1, this, var2);
        m_model.m_lid.RenderAsItemBlock(var1, this, var2);
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        AxisAlignedBB var6;

        if (this.GetIsOpen(var5))
        {
            var6 = m_model.m_selectionBoxOpen.MakeTemporaryCopy();
        }
        else
        {
            var6 = m_model.m_selectionBox.MakeTemporaryCopy();
        }

        var6.RotateAroundJToFacing(this.GetFacing(var5));
        var6.offset((double)var2, (double)var3, (double)var4);
        return var6;
    }
}

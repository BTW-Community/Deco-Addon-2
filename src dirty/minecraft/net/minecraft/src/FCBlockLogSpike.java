package net.minecraft.src;

public class FCBlockLogSpike extends Block
{
    public static final float m_fHardness = 2.0F;
    private FCModelBlock m_modelBlock = new FCModelBlockLogSpike();
    private Icon m_IconSide;

    public FCBlockLogSpike(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialLog);
        this.setHardness(2.0F);
        this.SetAxesEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.SetBuoyant();
        this.SetFireProperties(5, 5);
        Block.useNeighborBrightness[var1] = true;
        this.setLightOpacity(4);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockLogSpike");
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
        var9 = this.SetFacing(var9, var5);
        return var9;
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        int var7 = this.GetFacing(var1, var2, var3, var4);
        FCModelBlock var8 = this.m_modelBlock.MakeTemporaryCopy();
        var8.TiltToFacingAlongJ(var7);
        return var8.CollisionRayTrace(var1, var2, var3, var4, var5, var6);
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var5 == Block.GetOppositeFacing(var5);
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        this.dropBlockAsItem_do(var1, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemSawDust));
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

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 1000;
    }

    public int GetFacing(int var1)
    {
        int var2 = var1 & 7;
        return var2;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 &= -8;
        return var1 | var2;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("fcBlockLogChewedOak_top");
        this.m_IconSide = var1.registerIcon("fcBlockLogChewedOak_side");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        int var3 = this.GetFacing(var2);
        return var1 != var3 && var1 != Block.GetOppositeFacing(var3) ? this.m_IconSide : this.blockIcon;
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
        int var5 = this.GetFacing(var1.blockAccess, var2, var3, var4);
        FCModelBlock var6 = this.m_modelBlock.MakeTemporaryCopy();
        var6.TiltToFacingAlongJ(var5);
        return var6.RenderAsBlock(var1, this, var2, var3, var4);
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        this.m_modelBlock.RenderAsItemBlock(var1, this, var2);
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        AxisAlignedBB var6 = FCModelBlockLogSpike.m_boxSelection.MakeTemporaryCopy();
        var6.TiltToFacingAlongJ(var5);
        return var6.offset((double)var2, (double)var3, (double)var4);
    }
}

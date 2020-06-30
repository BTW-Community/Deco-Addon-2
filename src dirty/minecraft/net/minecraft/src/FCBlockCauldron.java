package net.minecraft.src;

public class FCBlockCauldron extends FCBlockCookingVessel
{
    private Icon m_IconContents;

    public FCBlockCauldron(int var1)
    {
        super(var1, Material.iron);
        this.setHardness(3.5F);
        this.setResistance(10.0F);
        this.setStepSound(soundMetalFootstep);
        this.setUnlocalizedName("fcBlockCauldron");
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityCauldron();
    }

    protected void ValidateFireUnderState(World var1, int var2, int var3, int var4)
    {
        if (!var1.isRemote)
        {
            TileEntity var5 = var1.getBlockTileEntity(var2, var3, var4);

            if (var5 instanceof FCTileEntityCauldron)
            {
                FCTileEntityCauldron var6 = (FCTileEntityCauldron)var5;
                var6.ValidateFireUnderType();
            }
        }
    }

    protected int GetContainerID()
    {
        return FCBetterThanWolves.fcCauldronContainerID;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        Icon var2 = var1.registerIcon("fcBlockCauldron_side");
        this.blockIcon = var2;
        this.m_IconInteriorBySideArray[0] = this.m_IconWideBandBySideArray[0] = this.m_IconCenterColumnBySideArray[0] = var1.registerIcon("fcBlockCauldron_bottom");
        this.m_IconInteriorBySideArray[1] = this.m_IconCenterColumnBySideArray[1] = var1.registerIcon("fcBlockCauldron_top");
        this.m_IconWideBandBySideArray[1] = var1.registerIcon("fcBlockCauldronWideBand_top");
        this.m_IconInteriorBySideArray[2] = this.m_IconWideBandBySideArray[2] = this.m_IconCenterColumnBySideArray[2] = var2;
        this.m_IconInteriorBySideArray[3] = this.m_IconWideBandBySideArray[3] = this.m_IconCenterColumnBySideArray[3] = var2;
        this.m_IconInteriorBySideArray[4] = this.m_IconWideBandBySideArray[4] = this.m_IconCenterColumnBySideArray[4] = var2;
        this.m_IconInteriorBySideArray[5] = this.m_IconWideBandBySideArray[5] = this.m_IconCenterColumnBySideArray[5] = var2;
        this.m_IconContents = var1.registerIcon("fcBlockCauldron_contents");
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        super.RenderBlock(var1, var2, var3, var4);
        IBlockAccess var5 = var1.blockAccess;

        if (this.GetFacing(var5, var2, var3, var4) == 1)
        {
            TileEntity var6 = var5.getBlockTileEntity(var2, var3, var4);

            if (var6 instanceof FCTileEntityCookingVessel)
            {
                FCTileEntityCookingVessel var7 = (FCTileEntityCookingVessel)var5.getBlockTileEntity(var2, var3, var4);
                short var8 = var7.m_sStorageSlotsOccupied;
                float var9 = (float)var8 / 27.0F;
                float var10 = 0.5625F;
                float var11 = var10 + 0.0625F + (0.875F - (var10 + 0.0625F)) * var9;
                var1.setRenderBounds(0.125D, (double)var10, 0.125D, 0.875D, (double)var11, 0.875D);
                FCClientUtilsRender.RenderStandardBlockWithTexture(var1, this, var2, var3, var4, this.m_IconContents);
            }
        }

        return true;
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        super.RenderBlockAsItem(var1, var2, var3);
        float var4 = 0.5625F;
        float var5 = 0.625F;
        var1.setRenderBounds(0.125D, 0.5625D, 0.125D, 0.875D, 0.625D, 0.875D);
        FCClientUtilsRender.RenderInvBlockWithTexture(var1, this, -0.5F, -0.5F, -0.5F, this.m_IconContents);
    }
}

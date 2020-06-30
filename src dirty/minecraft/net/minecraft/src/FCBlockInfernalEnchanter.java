package net.minecraft.src;

import java.util.Random;

public class FCBlockInfernalEnchanter extends BlockContainer
{
    public static final float m_fBlockHeight = 0.5F;
    public static final float m_fCandleHeight = 0.25F;
    private static final float m_fBlockHardness = 100.0F;
    private static final float m_fBlockExplosionResistance = 2000.0F;
    private static final int m_iHorizontalBookShelfCheckDistance = 8;
    private static final int m_iVerticalPositiveBookShelfCheckDistance = 8;
    private static final int m_iVerticalNegativeBookShelfCheckDistance = 8;
    private Icon[] m_IconBySideArray = new Icon[6];
    private Icon m_IconCandle;

    protected FCBlockInfernalEnchanter(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialSoulforgedSteel);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
        this.setLightOpacity(0);
        this.setHardness(100.0F);
        this.setResistance(2000.0F);
        this.setStepSound(soundMetalFootstep);
        this.setUnlocalizedName("fcBlockInfernalEnchanter");
        this.setCreativeTab(CreativeTabs.tabDecorations);
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
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityInfernalEnchanter();
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (!var1.isRemote && var5 instanceof EntityPlayerMP)
        {
            FCContainerInfernalEnchanter var10 = new FCContainerInfernalEnchanter(var5.inventory, var1, var2, var3, var4);
            FCBetterThanWolves.ServerOpenCustomInterface((EntityPlayerMP)var5, var10, FCBetterThanWolves.fcInfernalEnchanterContainerID);
        }

        return true;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    private boolean IsValidBookshelf(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);
        return var5 == Block.bookShelf.blockID && (var1.isAirBlock(var2 + 1, var3, var4) || var1.isAirBlock(var2 - 1, var3, var4) || var1.isAirBlock(var2, var3, var4 + 1) || var1.isAirBlock(var2, var3, var4 - 1));
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        Icon var2 = var1.registerIcon("fcBlockInfernalEnchanter_bottom");
        this.blockIcon = var2;
        this.m_IconBySideArray[0] = var2;
        this.m_IconBySideArray[1] = var1.registerIcon("fcBlockInfernalEnchanter_top");
        Icon var3 = var1.registerIcon("fcBlockInfernalEnchanter_side");
        this.m_IconBySideArray[2] = var3;
        this.m_IconBySideArray[3] = var3;
        this.m_IconBySideArray[4] = var3;
        this.m_IconBySideArray[5] = var3;
        this.m_IconCandle = var1.registerIcon("fcBlockCandle_c00");
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
        return true;
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        super.randomDisplayTick(var1, var2, var3, var4, var5);
        this.DisplayMagicLetters(var1, var2, var3, var4, var5);
    }

    private void DisplayMagicLetters(World var1, int var2, int var3, int var4, Random var5)
    {
        TileEntity var6 = var1.getBlockTileEntity(var2, var3, var4);

        if (var6 != null && var6 instanceof FCTileEntityInfernalEnchanter)
        {
            FCTileEntityInfernalEnchanter var7 = (FCTileEntityInfernalEnchanter)var6;

            if (var7.m_bPlayerNear)
            {
                for (int var8 = 0; var8 < 64; ++var8)
                {
                    int var9 = var5.nextInt(17) - 8 + var2;
                    int var10 = var5.nextInt(17) - 8 + var3;
                    int var11 = var5.nextInt(17) - 8 + var4;

                    if (this.IsValidBookshelf(var1, var9, var10, var11))
                    {
                        Vec3 var12 = Vec3.createVectorHelper((double)(var9 - var2), (double)(var10 - var3), (double)(var11 - var4));
                        var1.spawnParticle("enchantmenttable", (double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, var12.xCoord, var12.yCoord, var12.zCoord);
                    }
                }
            }
        }
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        var1.renderStandardBlock(this, var2, var3, var4);
        var1.setRenderBounds(0.0625D, 0.5D, 0.0625D, 0.1875D, 0.75D, 0.1875D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var1, this, var2, var3, var4, this.m_IconCandle);
        var1.setRenderBounds(0.8125D, 0.5D, 0.0625D, 0.9375D, 0.75D, 0.1875D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var1, this, var2, var3, var4, this.m_IconCandle);
        var1.setRenderBounds(0.0625D, 0.5D, 0.8125D, 0.1875D, 0.75D, 0.9375D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var1, this, var2, var3, var4, this.m_IconCandle);
        var1.setRenderBounds(0.8125D, 0.5D, 0.8125D, 0.9375D, 0.75D, 0.9375D);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var1, this, var2, var3, var4, this.m_IconCandle);
        return true;
    }
}

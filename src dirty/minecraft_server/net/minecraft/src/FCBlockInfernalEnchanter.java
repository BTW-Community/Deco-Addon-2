package net.minecraft.src;

public class FCBlockInfernalEnchanter extends BlockContainer
{
    public static final float m_fBlockHeight = 0.5F;
    public static final float m_fCandleHeight = 0.25F;
    private static final float m_fBlockHardness = 100.0F;
    private static final float m_fBlockExplosionResistance = 2000.0F;
    private static final int m_iHorizontalBookShelfCheckDistance = 8;
    private static final int m_iVerticalPositiveBookShelfCheckDistance = 8;
    private static final int m_iVerticalNegativeBookShelfCheckDistance = 8;

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
}

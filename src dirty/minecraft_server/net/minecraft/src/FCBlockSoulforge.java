package net.minecraft.src;

public class FCBlockSoulforge extends BlockContainer
{
    private final FCModelBlock m_blockModel = new FCModelBlockSoulforge();

    public FCBlockSoulforge(int var1)
    {
        super(var1, Material.iron);
        this.setHardness(3.5F);
        this.setStepSound(soundMetalFootstep);
        this.setUnlocalizedName("fcBlockAnvil");
        this.setCreativeTab(CreativeTabs.tabDecorations);
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
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityAnvil();
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        if (var5 < 2)
        {
            var5 = 2;
        }
        else
        {
            var5 = Block.GetOppositeFacing(var5);
        }

        return this.SetFacing(var9, var5);
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
    {
        int var7 = FCUtilsMisc.ConvertOrientationToFlatBlockFacingReversed(var5);
        this.SetFacing(var1, var2, var3, var4, var7);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (!var1.isRemote && var5 instanceof EntityPlayerMP)
        {
            FCContainerSoulforge var10 = new FCContainerSoulforge(var5.inventory, var1, var2, var3, var4);
            FCBetterThanWolves.ServerOpenCustomInterface((EntityPlayerMP)var5, var10, FCBetterThanWolves.fcAnvilContainerID);
        }

        return true;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        return var5 != 2 && var5 != 3 ? AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.25D, 1.0D, 1.0D, 0.75D) : AxisAlignedBB.getAABBPool().getAABB(0.25D, 0.0D, 0.0D, 0.75D, 1.0D, 1.0D);
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        FCTileEntityAnvil var7 = (FCTileEntityAnvil)var1.getBlockTileEntity(var2, var3, var4);

        if (var7 != null)
        {
            var7.EjectMoulds();
        }

        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        int var7 = this.GetFacing(var1, var2, var3, var4);
        FCModelBlock var8 = this.m_blockModel.MakeTemporaryCopy();
        var8.RotateAroundJToFacing(var7);
        return var8.CollisionRayTrace(var1, var2, var3, var4, var5, var6);
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
        return true;
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
    {
        this.RotateAroundJAxis(var1, var2, var3, var4, var5);
        return true;
    }
}

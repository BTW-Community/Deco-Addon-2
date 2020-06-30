package net.minecraft.src;

public abstract class FCBlockBasket extends BlockContainer
{
    protected FCBlockBasket(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialBasket);
        this.setHardness(0.05F);
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.WICKER);
        this.setStepSound(soundGrassFootstep);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        FCTileEntityBasket var7 = (FCTileEntityBasket)var1.getBlockTileEntity(var2, var3, var4);
        var7.EjectContents();
        super.breakBlock(var1, var2, var3, var4, var5, var6);
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

    public int PreBlockPlacedBy(World var1, int var2, int var3, int var4, int var5, EntityLiving var6)
    {
        int var7 = FCUtilsMisc.ConvertOrientationToFlatBlockFacingReversed(var6);
        return this.SetFacing(var5, var7);
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        return !FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true) ? false : super.canPlaceBlockAt(var1, var2, var3, var4);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockToAir(var2, var3, var4);
        }
    }

    public boolean CanBeCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        return true;
    }

    public int GetFacing(int var1)
    {
        return (var1 & 3) + 2;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 &= -4;
        var1 |= MathHelper.clamp_int(var2, 2, 5) - 2;
        return var1;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetPreventsFluidFlow(World var1, int var2, int var3, int var4, Block var5)
    {
        return false;
    }

    public void SetHasContents(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.SetHasContents(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetHasContents(int var1, boolean var2)
    {
        if (var2)
        {
            var1 |= 4;
        }
        else
        {
            var1 &= -5;
        }

        return var1;
    }

    public boolean GetHasContents(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetHasContents(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean GetHasContents(int var1)
    {
        return (var1 & 4) != 0;
    }

    public void SetIsOpen(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.SetIsOpen(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetIsOpen(int var1, boolean var2)
    {
        if (var2)
        {
            var1 |= 8;
        }
        else
        {
            var1 &= -9;
        }

        return var1;
    }

    public boolean GetIsOpen(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsOpen(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean GetIsOpen(int var1)
    {
        return (var1 & 8) != 0;
    }

    public abstract FCModelBlock GetLidModel(int var1);

    public abstract Vec3 GetLidRotationPoint();
}

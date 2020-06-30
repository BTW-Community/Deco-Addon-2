package net.minecraft.src;

import java.util.Random;

public class FCBlockLegacyOmniSlab extends Block
{
    public static final int m_iNumSubtypes = 2;
    protected static final double m_dSlabHeight = 0.5D;
    private Icon m_IconWood;

    protected FCBlockLegacyOmniSlab(int var1)
    {
        super(var1, Material.wood);
        this.setHardness(2.0F);
        this.SetAxesEffectiveOn();
        this.SetPicksEffectiveOn();
        this.SetBuoyancy(1.0F);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockOmniSlab");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return (var1 & 1) > 0 ? FCBetterThanWolves.fcBlockWoodSidingItemStubID : FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner.blockID;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return 0;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);

        switch (var5)
        {
            case 0:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);

            case 1:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

            case 2:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.5D, 1.0D, 1.0D, 1.0D);

            case 3:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);

            case 4:
                return AxisAlignedBB.getAABBPool().getAABB(0.5D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

            default:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D);
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

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        int var7 = this.GetFacing(var1, var2, var3, var4);
        return var5 == Block.GetOppositeFacing(var7);
    }

    public int GetFacing(int var1)
    {
        return var1 >> 1;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 &= 1;
        var1 |= var2 << 1;
        return var1;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        return var5 != 0;
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        return var5 > 1;
    }

    public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        var6 = Block.CycleFacing(var6, var5);
        this.SetFacing(var1, var2, var3, var4, var6);
        var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
        return true;
    }

    public boolean DoesBlockBreakSaw(World var1, int var2, int var3, int var4)
    {
        return !this.IsSlabWood(var1, var2, var3, var4);
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return this.IsSlabWood(var1, var2, var3, var4) ? FCBetterThanWolves.fcBlockWoodMouldingItemStubID : super.GetItemIDDroppedOnSaw(var1, var2, var3, var4);
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return this.IsSlabWood(var1, var2, var3, var4) ? 2 : super.GetItemIDDroppedOnSaw(var1, var2, var3, var4);
    }

    public int GetFurnaceBurnTime(int var1)
    {
        return (var1 & 1) > 0 ? FCBlockPlanks.GetFurnaceBurnTimeByWoodType(0) / 2 : 0;
    }

    public boolean IsSlabWood(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 1) > 0;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("stone");
        this.m_IconWood = var1.registerIcon("wood");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return (var2 & 1) > 0 ? this.m_IconWood : this.blockIcon;
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World var1, int var2, int var3, int var4)
    {
        return this.idDropped(var1.getBlockMetadata(var2, var3, var4), var1.rand, 0);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.m_currentBlockRenderer.ShouldSideBeRenderedBasedOnCurrentBounds(var2, var3, var4, var5);
    }
}

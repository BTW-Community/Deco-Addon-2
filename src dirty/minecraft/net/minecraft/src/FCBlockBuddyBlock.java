package net.minecraft.src;

import java.util.Random;

public class FCBlockBuddyBlock extends Block
{
    private static final int m_iTickRate = 5;
    private Icon m_IconOn;
    private Icon m_IconFront;
    private Icon m_IconFrontOn;

    public FCBlockBuddyBlock(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(3.5F);
        this.setTickRandomly(true);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("fcBlockBuddyBlock");
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 5;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, 1);
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        return this.SetFacing(var9, Block.GetOppositeFacing(var5));
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
    {
        int var7 = FCUtilsMisc.ConvertPlacingEntityOrientationToBlockFacingReversed(var5);
        this.SetFacing(var1, var2, var3, var4, var7);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!this.IsRedstoneOn(var1, var2, var3, var4))
        {
            Block var6 = blocksList[var5];

            if (var6 != null && var6.TriggersBuddy() && !var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
            {
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, 1);
            }
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.IsRedstoneOn(var1, var2, var3, var4))
        {
            this.SetBlockRedstoneOn(var1, var2, var3, var4, false);
        }
        else
        {
            this.SetBlockRedstoneOn(var1, var2, var3, var4, true);
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.IsRedstoneOn(var1, var2, var3, var4) && !var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    /**
     * Returns true if the block is emitting indirect/weak redstone power on the specified side. If isBlockNormalCube
     * returns true, standard redstone propagation rules will apply instead and this will not be called. Args: World, X,
     * Y, Z, side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.GetPowerProvided(var1, var2, var3, var4, var5);
    }

    /**
     * Returns true if the block is emitting direct/strong redstone power on the specified side. Args: World, X, Y, Z,
     * side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingStrongPower(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.GetPowerProvided(var1, var2, var3, var4, var5);
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower()
    {
        return true;
    }

    public int GetFacing(int var1)
    {
        return (var1 & -2) >> 1;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 = var1 & 1 | var2 << 1;
        return var1;
    }

    public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        var6 = Block.CycleFacing(var6, var5);
        this.SetFacing(var1, var2, var3, var4, var6);
        var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
        return true;
    }

    public boolean TriggersBuddy()
    {
        return false;
    }

    public int GetPowerProvided(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        return Block.GetOppositeFacing(var5) == var6 && this.IsRedstoneOn(var1, var2, var3, var4) ? 15 : 0;
    }

    public boolean IsRedstoneOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 1) > 0;
    }

    public void SetBlockRedstoneOn(World var1, int var2, int var3, int var4, boolean var5)
    {
        if (var5 != this.IsRedstoneOn(var1, var2, var3, var4))
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);

            if (var5)
            {
                var6 |= 1;
                var1.playAuxSFX(2234, var2, var3, var4, 0);
            }
            else
            {
                var6 &= -2;
            }

            var1.setBlockMetadataWithClient(var2, var3, var4, var6);
            int var7 = this.GetFacing(var1, var2, var3, var4);
            FCUtilsBlockPos var8 = new FCUtilsBlockPos(var2, var3, var4);
            var8.AddFacingAsOffset(var7);
            Block var9 = Block.blocksList[var1.getBlockId(var8.i, var8.j, var8.k)];

            if (var9 != null)
            {
                var9.onNeighborBlockChange(var1, var8.i, var8.j, var8.k, this.blockID);
            }

            var1.notifyBlocksOfNeighborChange(var8.i, var8.j, var8.k, this.blockID);
            var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
        }
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_IconOn = var1.registerIcon("fcBlockBuddyBlock_on");
        this.m_IconFront = var1.registerIcon("fcBlockBuddyBlock_front");
        this.m_IconFrontOn = var1.registerIcon("fcBlockBuddyBlock_front_on");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var1 == 3 ? this.m_IconFront : this.blockIcon;
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        return var6 == var5 ? (this.IsRedstoneOn(var1, var2, var3, var4) ? this.m_IconFrontOn : this.m_IconFront) : (this.IsRedstoneOn(var1, var2, var3, var4) ? this.m_IconOn : this.blockIcon);
    }
}

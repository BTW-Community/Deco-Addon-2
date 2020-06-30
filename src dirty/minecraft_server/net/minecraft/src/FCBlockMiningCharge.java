package net.minecraft.src;

import java.util.Random;

public class FCBlockMiningCharge extends Block
{
    public static final double m_dBoundingBoxHeight = 0.5D;
    private static final int m_iTickRate = 1;

    public FCBlockMiningCharge(int var1)
    {
        super(var1, Material.tnt);
        this.setHardness(0.0F);
        this.SetFireProperties(FCEnumFlammability.EXPLOSIVES);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("fcBlockMiningCharge");
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 1;
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
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 0;
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        for (int var5 = 0; var5 <= 5; ++var5)
        {
            if (this.IsValidAnchorToFacing(var1, var2, var3, var4, var5))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        var5 = Block.GetOppositeFacing(var5);

        if (!this.IsValidAnchorToFacing(var1, var2, var3, var4, var5))
        {
            var5 = 0;

            for (int var10 = 0; var10 <= 5; ++var10)
            {
                if (this.IsValidAnchorToFacing(var1, var2, var3, var4, var10))
                {
                    var5 = var10;
                    break;
                }
            }
        }

        return this.SetFacing(var9, var5);
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);

        switch (var5)
        {
            case 0:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);

            case 1:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);

            case 2:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5D);

            case 3:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.5D, 1.0D, 1.0D, 1.0D);

            case 4:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D);

            default:
                return AxisAlignedBB.getAABBPool().getAABB(0.5D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5)
    {
        if (!var1.isRemote)
        {
            int var6 = this.GetFacing(var1, var2, var3, var4);
            FCEntityMiningCharge var7 = CreatePrimedEntity(var1, var2, var3, var4, var6);
            var7.m_iFuse = 1;
        }
    }

    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
    public void onBlockDestroyedByPlayer(World var1, int var2, int var3, int var4, int var5)
    {
        if (!var1.isRemote)
        {
            this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(FCBetterThanWolves.fcBlockMiningCharge.blockID, 1, 0));
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        ItemStack var10 = var5.getCurrentEquippedItem();

        if (var10 != null && var10.itemID == Item.flintAndSteel.itemID)
        {
            if (!var1.isRemote)
            {
                int var11 = var1.getBlockMetadata(var2, var3, var4);
                var1.setBlockWithNotify(var2, var3, var4, 0);
                CreatePrimedEntity(var1, var2, var3, var4, var11);
            }

            var10.damageItem(1, var5);
            return true;
        }
        else
        {
            return super.onBlockActivated(var1, var2, var3, var4, var5, var6, var7, var8, var9);
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.IsGettingRedstonePower(var1, var2, var3, var4))
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            var1.setBlockWithNotify(var2, var3, var4, 0);
            CreatePrimedEntity(var1, var2, var3, var4, var6);
        }
        else if (!this.IsValidAnchorToFacing(var1, var2, var3, var4, this.GetFacing(var1, var2, var3, var4)))
        {
            var1.setBlockWithNotify(var2, var3, var4, 0);
            this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(FCBetterThanWolves.fcBlockMiningCharge.blockID, 1, 0));
        }
    }

    public void OnDestroyedByFire(World var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        CreatePrimedEntity(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4));
        super.OnDestroyedByFire(var1, var2, var3, var4, var5, var6);
    }

    public int GetFacing(int var1)
    {
        return var1 & 7;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 &= -8;
        var1 |= var2;
        return var1;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        return var5 != 1;
    }

    public boolean RotateAroundJAxis(World var1, int var2, int var3, int var4, boolean var5)
    {
        if (super.RotateAroundJAxis(var1, var2, var3, var4, var5))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        var6 = Block.CycleFacing(var6, var5);
        this.SetFacing(var1, var2, var3, var4, var6);
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        return true;
    }

    public boolean IsGettingRedstonePower(World var1, int var2, int var3, int var4)
    {
        return var1.isBlockIndirectlyGettingPowered(var2, var3, var4);
    }

    public boolean IsValidAnchorToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);
        return FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var6.i, var6.j, var6.k, GetOppositeFacing(var5), true);
    }

    public static FCEntityMiningCharge CreatePrimedEntity(World var0, int var1, int var2, int var3, int var4)
    {
        FCEntityMiningCharge var5 = new FCEntityMiningCharge(var0, var1, var2, var3, FCBetterThanWolves.fcBlockMiningCharge.GetFacing(var4));
        var0.spawnEntityInWorld(var5);
        var0.playSoundAtEntity(var5, "random.fuse", 1.0F, 1.0F);
        return var5;
    }
}

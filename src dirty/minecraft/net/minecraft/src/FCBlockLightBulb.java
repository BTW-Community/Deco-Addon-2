package net.minecraft.src;

import java.util.Random;

public class FCBlockLightBulb extends Block
{
    private static final int iLightBulbTickRate = 2;
    private boolean m_bGlowing;

    public FCBlockLightBulb(int var1, boolean var2)
    {
        super(var1, Material.glass);
        this.setHardness(0.4F);
        this.SetPicksEffectiveOn();
        this.setStepSound(Block.soundGlassFootstep);
        this.setUnlocalizedName("fcBlockLightBlock");
        this.m_bGlowing = var2;

        if (var2)
        {
            this.setLightValue(1.0F);
        }
        else
        {
            this.setCreativeTab(CreativeTabs.tabRedstone);
        }

        this.setTickRandomly(true);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 2;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcLightBulbOff.blockID;
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
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        boolean var6 = var1.isBlockIndirectlyGettingPowered(var2, var3, var4);

        if (var6)
        {
            if (!this.IsLightOn(var1, var2, var3, var4))
            {
                this.LightBulbTurnOn(var1, var2, var3, var4);
                return;
            }
        }
        else if (this.IsLightOn(var1, var2, var3, var4))
        {
            this.LightBulbTurnOff(var1, var2, var3, var4);
            return;
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.IsCurrentStateValid(var1, var2, var3, var4) && !var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    public boolean IsCurrentStateValid(World var1, int var2, int var3, int var4)
    {
        boolean var5 = var1.isBlockIndirectlyGettingPowered(var2, var3, var4);
        return var5 == this.IsLightOn(var1, var2, var3, var4);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!this.IsCurrentStateValid(var1, var2, var3, var4) && !var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
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

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var6;
    }

    private void LightBulbTurnOn(World var1, int var2, int var3, int var4)
    {
        var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcLightBulbOn.blockID);
    }

    private void LightBulbTurnOff(World var1, int var2, int var3, int var4)
    {
        var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcLightBulbOff.blockID);
    }

    public boolean IsLightOn(World var1, int var2, int var3, int var4)
    {
        return var1.getBlockId(var2, var3, var4) == FCBetterThanWolves.fcLightBulbOn.blockID;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        if (this.m_bGlowing)
        {
            this.blockIcon = var1.registerIcon("fcBlockLightBlock_lit");
        }
        else
        {
            this.blockIcon = var1.registerIcon("fcBlockLightBlock");
        }
    }

    /**
     * How bright to render this block based on the light its receiving. Args: iBlockAccess, x, y, z
     */
    public float getBlockBrightness(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.blockID == FCBetterThanWolves.fcLightBulbOn.blockID ? 100.0F : var1.getLightBrightness(var2, var3, var4);
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World var1, int var2, int var3, int var4)
    {
        return this.idDropped(var1.getBlockMetadata(var2, var3, var4), var1.rand, 0);
    }
}

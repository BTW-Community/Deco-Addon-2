package net.minecraft.src;

import java.util.Random;

public class FCBlockKiln extends Block
{
    private static int m_iMinFireFactorBaseTickRate = 40;
    private static int m_iMaxFireFactorBaseTickRate = 160;

    public FCBlockKiln(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(2.0F);
        this.setResistance(10.0F);
        this.setStepSound(soundStoneFootstep);
        this.setTickRandomly(true);
        this.setUnlocalizedName("fcBlockKiln");
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);

        if (this.CanBlockBeCooked(var1, var2, var3 + 1, var4))
        {
            this.ScheduleUpdateBasedOnCookState(var1, var2, var3, var4);
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = this.GetCookCounter(var1, var2, var3, var4);
        int var7 = 0;

        if (this.CanBlockBeCooked(var1, var2, var3 + 1, var4))
        {
            if (this.CheckKilnIntegrity(var1, var2, var3, var4))
            {
                if (var6 >= 15)
                {
                    this.CookBlock(var1, var2, var3 + 1, var4);
                }
                else
                {
                    var7 = var6 + 1;
                    this.ScheduleUpdateBasedOnCookState(var1, var2, var3, var4);
                }
            }
            else
            {
                this.ScheduleUpdateBasedOnCookState(var1, var2, var3, var4);
            }
        }

        if (var6 != var7)
        {
            this.SetCookCounter(var1, var2, var3, var4, var7);
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Block.brick.blockID;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (var1.getBlockId(var2, var3 - 1, var4) != FCBetterThanWolves.fcBlockFireStoked.blockID)
        {
            var1.setBlockWithNotify(var2, var3, var4, Block.brick.blockID);
        }
        else if (this.CanBlockBeCooked(var1, var2, var3 + 1, var4))
        {
            if (!var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID) && !var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
            {
                this.ScheduleUpdateBasedOnCookState(var1, var2, var3, var4);
            }
        }
        else if (this.GetCookCounter(var1, var2, var3, var4) > 0)
        {
            this.SetCookCounterNoNotify(var1, var2, var3, var4, 0);
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID) && this.CanBlockBeCooked(var1, var2, var3 + 1, var4))
        {
            this.ScheduleUpdateBasedOnCookState(var1, var2, var3, var4);
        }
    }

    public int GetCookCounter(int var1)
    {
        return var1;
    }

    public int GetCookCounter(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetCookCounter(var1.getBlockMetadata(var2, var3, var4));
    }

    public int SetCookCounter(int var1, int var2)
    {
        return var2;
    }

    public void SetCookCounter(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.SetCookCounter(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public void SetCookCounterNoNotify(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.SetCookCounter(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithClient(var2, var3, var4, var6);
    }

    protected void ScheduleUpdateBasedOnCookState(World var1, int var2, int var3, int var4)
    {
        int var5 = this.ComputeTickRateBasedOnFireFactor(var1, var2, var3, var4);
        var5 *= this.GetBlockCookTimeMultiplier(var1, var2, var3 + 1, var4);
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, var5);
    }

    private boolean CanBlockBeCooked(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);
        Block var6 = Block.blocksList[var5];
        return var6 != null ? var6.GetCanBeCookedByKiln(var1, var2, var3, var4) : false;
    }

    private void CookBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);
        Block var6 = Block.blocksList[var5];

        if (var6 != null && var6.GetCanBeCookedByKiln(var1, var2, var3, var4))
        {
            var6.OnCookedByKiln(var1, var2, var3, var4);
        }
    }

    private boolean CheckKilnIntegrity(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = 0;

        for (int var6 = 1; var6 <= 5; ++var6)
        {
            FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3 + 1, var4);
            var7.AddFacingAsOffset(var6);
            int var8 = var1.getBlockId(var7.i, var7.j, var7.k);

            if (var8 == Block.brick.blockID || var8 == FCBetterThanWolves.fcKiln.blockID)
            {
                ++var5;

                if (var5 >= 3)
                {
                    return true;
                }
            }
        }

        return false;
    }

    private int ComputeTickRateBasedOnFireFactor(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = 0;
        int var6;

        for (var6 = -1; var6 <= 1; ++var6)
        {
            for (int var7 = -1; var7 <= 1; ++var7)
            {
                if ((var6 != 0 || var7 != 0) && var1.getBlockId(var2 + var6, var3 - 1, var4 + var7) == FCBetterThanWolves.fcBlockFireStoked.blockID)
                {
                    ++var5;
                }
            }
        }

        var6 = (m_iMaxFireFactorBaseTickRate - m_iMinFireFactorBaseTickRate) * (8 - var5) / 8 + m_iMinFireFactorBaseTickRate;
        return var6;
    }

    private int GetBlockCookTimeMultiplier(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);
        Block var6 = Block.blocksList[var5];
        return var6 != null ? var6.GetCookTimeMultiplierInKiln(var1, var2, var3, var4) : 1;
    }
}

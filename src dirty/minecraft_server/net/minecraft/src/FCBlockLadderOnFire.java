package net.minecraft.src;

import java.util.Random;

public class FCBlockLadderOnFire extends FCBlockLadderBase
{
    private static final int m_iChanceOfLightingLadderAbove = 4;
    private static final int m_iTickRate = 60;
    private static final int m_iTickRateVariance = 30;

    protected FCBlockLadderOnFire(int var1)
    {
        super(var1);
        this.setLightValue(1.0F);
        this.setTickRandomly(true);
        this.setUnlocalizedName("fcBlockLadderOnFire");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return 0;
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 60;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1) + var1.rand.nextInt(30));
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.IsRainingOnLadder(var1, var2, var3, var4))
        {
            this.Extinguish(var1, var2, var3, var4);
        }
        else
        {
            FCBlockFire.CheckForFireSpreadFromLocation(var1, var2, var3, var4, var5, 0);
            int var6 = this.GetBurnCounter(var1, var2, var3, var4);

            if (var6 < 3)
            {
                if (var5.nextInt(4) == 0)
                {
                    this.LightLadderAtLocationIfPresent(var1, var2, var3 + 1, var4);
                }

                this.SetBurnCounter(var1, var2, var3, var4, var6 + 1);
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1) + var5.nextInt(30));
            }
            else
            {
                this.LightLadderAtLocationIfPresent(var1, var2, var3 + 1, var4);
                var1.setBlockToAir(var2, var3, var4);
            }
        }
    }

    public boolean GetDoesFireDamageToEntities(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetCanBlockLightItemOnFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanBeCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        return true;
    }

    protected void LightLadderAtLocationIfPresent(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);

        if (var5 == FCBetterThanWolves.fcBlockLadder.blockID)
        {
            FCBetterThanWolves.fcBlockLadder.SetOnFireDirectly(var1, var2, var3, var4);
        }
    }

    protected void Extinguish(World var1, int var2, int var3, int var4)
    {
        int var5 = FCBetterThanWolves.fcBlockLadder.SetFacing(0, this.GetFacing(var1, var2, var3, var4));
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockLadder.blockID, var5);
    }

    protected boolean IsRainingOnLadder(World var1, int var2, int var3, int var4)
    {
        return var1.IsRainingAtPos(var2, var3, var4);
    }

    protected int GetBurnCounter(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetBurnCounter(var1.getBlockMetadata(var2, var3, var4));
    }

    protected int GetBurnCounter(int var1)
    {
        return var1 >> 2 & 3;
    }

    protected void SetBurnCounter(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.SetBurnCounter(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadata(var2, var3, var4, var6);
    }

    protected int SetBurnCounter(int var1, int var2)
    {
        var1 &= -13;
        return var1 | var2 << 2;
    }
}

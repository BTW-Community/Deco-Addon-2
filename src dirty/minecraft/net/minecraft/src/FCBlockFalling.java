package net.minecraft.src;

import java.util.Random;

public class FCBlockFalling extends Block
{
    public static final int m_iFallingBlockTickRate = 2;
    public static final int m_iTackyFallingBlockTickRate = 40;

    public FCBlockFalling(int var1, Material var2)
    {
        super(var1, var2);
    }

    public boolean IsFallingBlock()
    {
        return true;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        this.ScheduleCheckForFall(var1, var2, var3, var4);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        this.ScheduleCheckForFall(var1, var2, var3, var4);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        this.CheckForFall(var1, var2, var3, var4);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 2;
    }
}

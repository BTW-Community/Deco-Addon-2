package net.minecraft.src;

import java.util.Random;

public class FCBlockAxlePowerSource extends FCBlockAxle
{
    protected FCBlockAxlePowerSource(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockAxlePowerSource");
        this.setCreativeTab((CreativeTabs)null);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5) {}

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5) {}

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcBlockAxle.blockID;
    }

    public int GetMechanicalPowerLevelProvidedToAxleAtFacing(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetAxisAlignment(var1, var2, var3, var4);
        return var5 >> 1 == var6 ? 4 : 0;
    }

    protected void ValidatePowerLevel(World var1, int var2, int var3, int var4) {}

    public int GetPowerLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 4;
    }

    public int GetPowerLevelFromMetadata(int var1)
    {
        return 4;
    }

    public void SetPowerLevel(World var1, int var2, int var3, int var4, int var5) {}

    public int SetPowerLevelInMetadata(int var1, int var2)
    {
        return var1;
    }

    public void SetPowerLevelWithoutNotify(World var1, int var2, int var3, int var4, int var5) {}
}

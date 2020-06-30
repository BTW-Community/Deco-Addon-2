package net.minecraft.src;

public class FCBlockWaterStationary extends BlockStationary
{
    protected FCBlockWaterStationary(int var1, Material var2)
    {
        super(var1, var2);
    }

    public boolean CanPathThroughBlock(IBlockAccess var1, int var2, int var3, int var4, Entity var5, PathFinder var6)
    {
        return var6.CanPathThroughWater();
    }

    public int GetWeightOnPathBlocked(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1;
    }

    public int AdjustPathWeightOnNotBlocked(int var1)
    {
        return 2;
    }
}

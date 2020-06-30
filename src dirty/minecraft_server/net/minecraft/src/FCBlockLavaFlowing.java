package net.minecraft.src;

public class FCBlockLavaFlowing extends BlockFlowing
{
    protected FCBlockLavaFlowing(int var1, Material var2)
    {
        super(var1, var2);
    }

    public boolean CanPathThroughBlock(IBlockAccess var1, int var2, int var3, int var4, Entity var5, PathFinder var6)
    {
        return var5.handleLavaMovement();
    }

    public int GetWeightOnPathBlocked(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -2;
    }

    public boolean GetDoesFireDamageToEntities(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetCanBlockLightItemOnFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }
}

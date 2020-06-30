package net.minecraft.src;

public class FCBlockOre extends BlockOre
{
    public FCBlockOre(int var1)
    {
        super(var1);
        this.SetPicksEffectiveOn();
    }

    public boolean HasStrata()
    {
        return true;
    }

    public int GetMetadataConversionForStrataLevel(int var1, int var2)
    {
        return var1;
    }

    /**
     * Returns the block hardness at a location. Args: world, x, y, z
     */
    public float getBlockHardness(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetStrata(var1, var2, var3, var4);
        return var5 != 0 ? (var5 == 1 ? 4.0F : 6.0F) : super.getBlockHardness(var1, var2, var3, var4);
    }

    public float getExplosionResistance(Entity var1, World var2, int var3, int var4, int var5)
    {
        int var6 = this.GetStrata(var2, var3, var4, var5);
        return var6 != 0 ? (var6 == 1 ? 4.2000003F : 6.0F) : super.getExplosionResistance(var1, var2, var3, var4, var5);
    }

    public boolean IsNaturalStone(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public int GetStrata(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetStrata(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetStrata(int var1)
    {
        return var1 & 3;
    }

    public int GetRequiredToolLevelForStrata(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetStrata(var1, var2, var3, var4);
        return var5 > 1 ? var5 + 1 : 2;
    }
}

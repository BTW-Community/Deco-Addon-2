package net.minecraft.src;

public class FCBlockFarmlandLegacyUnfertilized extends FCBlockFarmlandLegacyBase
{
    protected FCBlockFarmlandLegacyUnfertilized(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockFarmlandFertilizedNew");
    }

    public boolean GetCanGrassSpreadToBlock(World var1, int var2, int var3, int var4)
    {
        return var1.isAirBlock(var2, var3 + 1, var4);
    }

    public boolean AttempToSpreadGrassToBlock(World var1, int var2, int var3, int var4)
    {
        return var1.rand.nextInt(3) == 0 ? super.AttempToSpreadGrassToBlock(var1, var2, var3, var4) : false;
    }

    public boolean SpreadGrassToBlock(World var1, int var2, int var3, int var4)
    {
        var1.setBlockAndMetadataWithNotify(var2, var3 + 1, var4, Block.tallGrass.blockID, 1);
        return true;
    }

    protected boolean IsFertilized(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    protected void SetFertilized(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockFarmlandLegacyFertilized.blockID, var5);
    }

    protected void ConvertToNewSoil(World var1, int var2, int var3, int var4)
    {
        int var5 = 0;

        if (this.IsHydrated(var1, var2, var3, var4))
        {
            var5 = FCBetterThanWolves.fcBlockFarmland.SetFullyHydrated(var5);
        }

        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockFarmland.blockID, var5);
    }
}

package net.minecraft.src;

public class FCBlockFarmlandFertilized extends FCBlockFarmland
{
    protected FCBlockFarmlandFertilized(int var1)
    {
        super(var1);
    }

    public float GetPlantGrowthOnMultiplier(World var1, int var2, int var3, int var4, Block var5)
    {
        return 2.0F;
    }

    public boolean GetIsFertilizedForPlantGrowth(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public void NotifyOfFullStagePlantGrowthOn(World var1, int var2, int var3, int var4, Block var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockFarmland.blockID, var6);
    }

    protected boolean IsFertilized(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }
}

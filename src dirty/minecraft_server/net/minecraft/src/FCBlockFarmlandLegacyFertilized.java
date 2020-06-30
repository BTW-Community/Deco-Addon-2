package net.minecraft.src;

public class FCBlockFarmlandLegacyFertilized extends FCBlockFarmlandLegacyBase
{
    private static final int m_iDefaultTexture = 2;
    private static final int m_iTopWetTexture = 136;
    public static final int m_iTopDryTexture = 137;

    protected FCBlockFarmlandLegacyFertilized(int var1)
    {
        super(var1);
        this.setUnlocalizedName("FCBlockFarmlandFertilized");
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
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, Block.tilledField.blockID, var6);
    }

    protected boolean IsFertilized(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    protected void ConvertToNewSoil(World var1, int var2, int var3, int var4)
    {
        int var5 = 0;

        if (this.IsHydrated(var1, var2, var3, var4))
        {
            var5 = FCBetterThanWolves.fcBlockFarmlandFertilized.SetFullyHydrated(var5);
        }

        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockFarmlandFertilized.blockID, var5);
    }
}

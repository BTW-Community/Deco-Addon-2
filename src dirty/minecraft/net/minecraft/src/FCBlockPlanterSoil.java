package net.minecraft.src;

import java.util.Random;

public class FCBlockPlanterSoil extends FCBlockPlanterBase
{
    private Icon m_iconTopSoilDry;
    private Icon m_iconTopSoilDryFertilized;

    protected FCBlockPlanterSoil(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockPlanterSoil");
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        boolean var6 = this.HasIrrigatingBlocks(var1, var2, var3, var4) || var1.IsRainingAtPos(var2, var3 + 1, var4);

        if (var6 != this.GetIsHydrated(var1, var2, var3, var4))
        {
            this.SetIsHydrated(var1, var2, var3, var4, var6);
        }
    }

    public boolean AttemptToApplyFertilizerTo(World var1, int var2, int var3, int var4)
    {
        if (!this.GetIsFertilized(var1, var2, var3, var4))
        {
            this.SetIsFertilized(var1, var2, var3, var4, true);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean CanDomesticatedCropsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanReedsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanSaplingsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanWildVegetationGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanCactusGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean IsBlockHydratedForPlantGrowthOn(World var1, int var2, int var3, int var4)
    {
        return this.GetIsHydrated(var1, var2, var3, var4);
    }

    public boolean IsConsideredNeighbouringWaterForReedGrowthOn(World var1, int var2, int var3, int var4)
    {
        return this.GetIsHydrated(var1, var2, var3, var4);
    }

    public boolean GetIsFertilizedForPlantGrowth(World var1, int var2, int var3, int var4)
    {
        return this.GetIsFertilized(var1, var2, var3, var4);
    }

    public void NotifyOfFullStagePlantGrowthOn(World var1, int var2, int var3, int var4, Block var5)
    {
        if (this.GetIsFertilized(var1, var2, var3, var4))
        {
            this.SetIsFertilized(var1, var2, var3, var4, false);
        }
    }

    protected boolean HasIrrigatingBlocks(World var1, int var2, int var3, int var4)
    {
        return var1.getBlockMaterial(var2, var3 - 1, var4) == Material.water || var1.getBlockMaterial(var2, var3 + 1, var4) == Material.water || var1.getBlockMaterial(var2, var3, var4 - 1) == Material.water || var1.getBlockMaterial(var2, var3, var4 + 1) == Material.water || var1.getBlockMaterial(var2 - 1, var3, var4) == Material.water || var1.getBlockMaterial(var2 + 1, var3, var4) == Material.water;
    }

    protected boolean GetIsHydrated(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsHydrated(var1.getBlockMetadata(var2, var3, var4));
    }

    protected boolean GetIsHydrated(int var1)
    {
        return (var1 & 1) != 0;
    }

    protected void SetIsHydrated(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.SetIsHydrated(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    protected int SetIsHydrated(int var1, boolean var2)
    {
        if (var2)
        {
            var1 |= 1;
        }
        else
        {
            var1 &= -2;
        }

        return var1;
    }

    protected boolean GetIsFertilized(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsFertilized(var1.getBlockMetadata(var2, var3, var4));
    }

    protected boolean GetIsFertilized(int var1)
    {
        return (var1 & 2) != 0;
    }

    protected void SetIsFertilized(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.SetIsFertilized(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    protected int SetIsFertilized(int var1, boolean var2)
    {
        if (var2)
        {
            var1 |= 2;
        }
        else
        {
            var1 &= -3;
        }

        return var1;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_iconTopSoilDry = var1.registerIcon("fcBlockPlanter_top_dry");
        this.m_iconTopSoilDryFertilized = var1.registerIcon("fcBlockPlanter_top_dry_fertilized");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var1 == 1 ? (this.GetIsFertilized(var2) ? (this.GetIsHydrated(var2) ? this.m_iconTopSoilWetFertilized : this.m_iconTopSoilDryFertilized) : (this.GetIsHydrated(var2) ? this.m_iconTopSoilWet : this.m_iconTopSoilDry)) : this.blockIcon;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        return this.RenderFilledPlanterBlock(var1, var2, var3, var4);
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        this.RenderFilledPlanterInvBlock(var1, this, var2);
    }
}

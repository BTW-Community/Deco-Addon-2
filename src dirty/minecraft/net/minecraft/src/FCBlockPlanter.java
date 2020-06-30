package net.minecraft.src;

import java.util.Random;

public class FCBlockPlanter extends FCBlockPlanterBase
{
    public static final int m_iTypeEmpty = 0;
    public static final int m_iTypeSoil = 1;
    public static final int m_iTypeSoilFertilized = 2;
    public static final int m_iTypeSoulSand = 8;
    public static final int m_iTypeGrass0 = 9;
    public static final int m_iTypeGrass1 = 11;
    public static final int m_iTypeGrass2 = 13;
    public static final int m_iTypeGrass3 = 15;
    private Icon m_iconTopGrass;
    private Icon m_iconTopSoulSand;

    public FCBlockPlanter(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockPlanter");
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        if (var1 == 11 || var1 == 13 || var1 == 15)
        {
            var1 = 9;
        }

        return var1;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = this.GetPlanterType(var1, var2, var3, var4);

        if (var6 == 9 || var6 == 11 || var6 == 13 || var6 == 15)
        {
            int var7 = this.GetGrassGrowthState(var1, var2, var3, var4);
            int var8 = 0;
            int var9;

            if (var1.isAirBlock(var2, var3 + 1, var4) && var1.getBlockLightValue(var2, var3 + 1, var4) >= 8)
            {
                var8 = var7 + 1;

                if (var8 > 3)
                {
                    var8 = 0;
                    var9 = var5.nextInt(4);

                    if (var9 == 0)
                    {
                        var1.setBlockWithNotify(var2, var3 + 1, var4, Block.plantRed.blockID);
                    }
                    else if (var9 == 1)
                    {
                        var1.setBlockWithNotify(var2, var3 + 1, var4, Block.plantYellow.blockID);
                    }
                    else
                    {
                        var1.setBlockAndMetadataWithNotify(var2, var3 + 1, var4, Block.tallGrass.blockID, 1);
                    }
                }
            }

            if (var1.getBlockLightValue(var2, var3 + 1, var4) >= 9)
            {
                for (var9 = 0; var9 < 4; ++var9)
                {
                    int var10 = var2 + var5.nextInt(3) - 1;
                    int var11 = var3 + var5.nextInt(5) - 3;
                    int var12 = var4 + var5.nextInt(3) - 1;
                    int var13 = var1.getBlockId(var10, var11 + 1, var12);

                    if (var1.getBlockId(var10, var11, var12) == Block.dirt.blockID && var1.getBlockLightValue(var10, var11 + 1, var12) >= 4 && Block.lightOpacity[var13] <= 2)
                    {
                        var1.setBlockWithNotify(var10, var11, var12, Block.grass.blockID);
                    }
                }
            }

            if (var8 != var7)
            {
                this.SetGrassGrowthState(var1, var2, var3, var4, var8);
            }
        }
    }

    public boolean AttemptToApplyFertilizerTo(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetPlanterType(var1, var2, var3, var4);

        if (var5 == 1)
        {
            this.SetPlanterType(var1, var2, var3, var4, 2);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return this.GetPlanterType(var1, var2, var3, var4) == 0 ? false : super.HasLargeCenterHardPointToFacing(var1, var2, var3, var4, var5, var6);
    }

    public boolean CanDomesticatedCropsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetPlanterType(var1, var2, var3, var4);
        return var5 == 1 || var5 == 2;
    }

    public boolean CanReedsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetPlanterType(var1, var2, var3, var4);
        return var5 != 0 && var5 != 8;
    }

    public boolean CanSaplingsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetPlanterType(var1, var2, var3, var4);
        return var5 != 0 && var5 != 8;
    }

    public boolean CanWildVegetationGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetPlanterType(var1, var2, var3, var4);
        return var5 != 0 && var5 != 8;
    }

    public boolean CanNetherWartGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return this.GetPlanterType(var1, var2, var3, var4) == 8;
    }

    public boolean CanCactusGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetPlanterType(var1, var2, var3, var4);
        return var5 != 0 && var5 != 8;
    }

    public boolean IsBlockHydratedForPlantGrowthOn(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetPlanterType(var1, var2, var3, var4);
        return var5 == 1 || var5 == 2;
    }

    public boolean IsConsideredNeighbouringWaterForReedGrowthOn(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetPlanterType(var1, var2, var3, var4);
        return var5 == 1 || var5 == 2 || super.IsConsideredNeighbouringWaterForReedGrowthOn(var1, var2, var3, var4);
    }

    public boolean GetIsFertilizedForPlantGrowth(World var1, int var2, int var3, int var4)
    {
        return this.GetPlanterType(var1, var2, var3, var4) == 2;
    }

    public void NotifyOfFullStagePlantGrowthOn(World var1, int var2, int var3, int var4, Block var5)
    {
        int var6 = this.GetPlanterType(var1, var2, var3, var4);

        if (var6 == 2)
        {
            this.SetPlanterType(var1, var2, var3, var4, 1);
        }
    }

    public int GetPlanterType(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetPlanterTypeFromMetadata(var1.getBlockMetadata(var2, var3, var4));
    }

    public void SetPlanterType(World var1, int var2, int var3, int var4, int var5)
    {
        var1.setBlockMetadataWithNotify(var2, var3, var4, var5);
    }

    public int GetPlanterTypeFromMetadata(int var1)
    {
        return var1;
    }

    public int GetGrassGrowthState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetPlanterType(var1, var2, var3, var4);

        switch (var5)
        {
            case 9:
                return 0;

            case 10:
            case 12:
            case 14:
            default:
                return 0;

            case 11:
                return 1;

            case 13:
                return 2;

            case 15:
                return 3;
        }
    }

    public void SetGrassGrowthState(World var1, int var2, int var3, int var4, int var5)
    {
        byte var6 = 9;

        if (var5 == 1)
        {
            var6 = 11;
        }
        else if (var5 == 2)
        {
            var6 = 13;
        }
        else if (var5 == 3)
        {
            var6 = 15;
        }

        this.SetPlanterType(var1, var2, var3, var4, var6);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_iconTopGrass = var1.registerIcon("fcBlockPlanter_top_grass");
        this.m_iconTopSoulSand = var1.registerIcon("fcBlockPlanter_top_soulsand");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        int var3 = this.GetPlanterTypeFromMetadata(var2);
        return var1 == 1 && var3 != 0 ? (var3 == 1 ? this.m_iconTopSoilWet : (var3 == 8 ? this.m_iconTopSoulSand : (var3 == 2 ? this.m_iconTopSoilWetFertilized : this.m_iconTopGrass))) : this.blockIcon;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        int var6 = this.GetPlanterType(var5, var2, var3, var4);
        return var6 == 0 ? RenderEmptyPlanterBlock(var1, var5, var2, var3, var4, this) : this.RenderFilledPlanterBlock(var1, var2, var3, var4);
    }

    public static boolean RenderEmptyPlanterBlock(RenderBlocks var0, IBlockAccess var1, int var2, int var3, int var4, Block var5)
    {
        var0.setRenderBounds(0.125D, 0.0D, 0.125D, 0.25D, 0.6875D, 0.75D);
        var0.renderStandardBlock(var5, var2, var3, var4);
        var0.setRenderBounds(0.125D, 0.0D, 0.75D, 0.75D, 0.6875D, 0.875D);
        var0.renderStandardBlock(var5, var2, var3, var4);
        var0.setRenderBounds(0.75D, 0.0D, 0.25D, 0.875D, 0.6875D, 0.875D);
        var0.renderStandardBlock(var5, var2, var3, var4);
        var0.setRenderBounds(0.25D, 0.0D, 0.125D, 0.875D, 0.6875D, 0.25D);
        var0.renderStandardBlock(var5, var2, var3, var4);
        var0.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 0.125D, 0.75D);
        var0.renderStandardBlock(var5, var2, var3, var4);
        var0.setRenderBounds(0.0D, 0.6875D, 0.0D, 0.125D, 1.0D, 0.875D);
        var0.renderStandardBlock(var5, var2, var3, var4);
        var0.setRenderBounds(0.0D, 0.6875D, 0.875D, 0.875D, 1.0D, 1.0D);
        var0.renderStandardBlock(var5, var2, var3, var4);
        var0.setRenderBounds(0.875D, 0.6875D, 0.125D, 1.0D, 1.0D, 1.0D);
        var0.renderStandardBlock(var5, var2, var3, var4);
        var0.setRenderBounds(0.125D, 0.6875D, 0.0D, 1.0D, 1.0D, 0.125D);
        var0.renderStandardBlock(var5, var2, var3, var4);
        return true;
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        if (var2 == 0)
        {
            RenderEmptyPlanterInvBlock(var1, this, var2);
        }
        else
        {
            this.RenderFilledPlanterInvBlock(var1, this, var2);
        }
    }

    public static void RenderEmptyPlanterInvBlock(RenderBlocks var0, Block var1, int var2)
    {
        var0.setRenderBounds(0.125D, 0.0D, 0.125D, 0.25D, 0.6875D, 0.75D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
        var0.setRenderBounds(0.125D, 0.0D, 0.75D, 0.75D, 0.6875D, 0.875D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
        var0.setRenderBounds(0.75D, 0.0D, 0.25D, 0.875D, 0.6875D, 0.875D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
        var0.setRenderBounds(0.25D, 0.0D, 0.125D, 0.875D, 0.6875D, 0.25D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
        var0.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 0.125D, 0.75D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
        var0.setRenderBounds(0.0D, 0.6875D, 0.0D, 0.125D, 1.0D, 0.875D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
        var0.setRenderBounds(0.0D, 0.6875D, 0.875D, 0.875D, 1.0D, 1.0D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
        var0.setRenderBounds(0.875D, 0.6875D, 0.125D, 1.0D, 1.0D, 1.0D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
        var0.setRenderBounds(0.125D, 0.6875D, 0.0D, 1.0D, 1.0D, 0.125D);
        FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, var2);
    }
}

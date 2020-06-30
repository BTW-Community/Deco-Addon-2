package net.minecraft.src;

import java.util.Random;

public class FCBlockFarmland extends FCBlockFarmlandBase
{
    public static final int m_iLightLevelForWeedGrowth = 11;

    protected FCBlockFarmland(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockFarmlandNew");
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        super.onNeighborBlockChange(var1, var2, var3, var4, var5);

        if (!var1.getBlockMaterial(var2, var3 + 1, var4).isSolid() && !this.CanFallIntoBlockAtPos(var1, var2, var3 - 1, var4))
        {
            if (this.GetWeedsGrowthLevel(var1, var2, var3, var4) > 0 && !this.CanWeedsShareSpaceWithBlockAt(var1, var2, var3 + 1, var4))
            {
                this.SetWeedsGrowthLevel(var1, var2, var3, var4, 0);
            }
        }
        else
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        super.updateTick(var1, var2, var3, var4, var5);

        if (var1.getBlockId(var2, var3, var4) == this.blockID && !this.CheckForSnowReversion(var1, var2, var3, var4, var5))
        {
            this.UpdateWeedGrowth(var1, var2, var3, var4, var5);
        }
    }

    public void NotifyOfPlantAboveRemoved(World var1, int var2, int var3, int var4, Block var5)
    {
        if (var1.getBlockId(var2, var3 + 1, var4) != Block.tallGrass.blockID)
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);
        }
    }

    protected boolean IsHydrated(int var1)
    {
        return (var1 & 1) > 0;
    }

    protected int SetFullyHydrated(int var1)
    {
        return var1 | 1;
    }

    protected void DryIncrementally(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        int var6 = var5 & 1;

        if (var6 > 0)
        {
            var5 &= -2;
            var1.setBlockMetadataWithNotify(var2, var3, var4, var5);
        }
    }

    protected boolean IsFertilized(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    protected void SetFertilized(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockFarmlandFertilized.blockID, var5);
    }

    public int GetWeedsGrowthLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetWeedsGrowthLevel(var1.getBlockMetadata(var2, var3, var4));
    }

    public void RemoveWeeds(World var1, int var2, int var3, int var4)
    {
        this.SetWeedsGrowthLevel(var1, var2, var3, var4, 0);
    }

    protected void CheckForSoilReversion(World var1, int var2, int var3, int var4)
    {
        if (var1.rand.nextInt(8) == 0)
        {
            super.CheckForSoilReversion(var1, var2, var3, var4);
        }
    }

    protected int GetHorizontalHydrationRange(World var1, int var2, int var3, int var4)
    {
        BiomeGenBase var5 = var1.getBiomeGenForCoords(var2, var4);
        return !var5.getEnableSnow() && !var5.CanRainInBiome() ? 2 : 4;
    }

    public boolean CanWeedsShareSpaceWithBlockAt(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3, var4)];
        return var5 != null ? var5.CanWeedsGrowInBlock(var1, var2, var3, var4) : false;
    }

    protected int GetWeedsGrowthLevel(int var1)
    {
        return (var1 & 14) >> 1;
    }

    protected void SetWeedsGrowthLevel(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.SetWeedsGrowthLevel(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    protected int SetWeedsGrowthLevel(int var1, int var2)
    {
        var1 &= -15;
        return var1 | var2 << 1;
    }

    public boolean CheckForSnowReversion(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var1.IsSnowingAtPos(var2, var3 + 1, var4) && var5.nextInt(2) == 0)
        {
            if (var1.getSavedLightValue(EnumSkyBlock.Block, var2, var3 + 1, var4) < 10)
            {
                var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);

                if (Block.snow.canPlaceBlockAt(var1, var2, var3 + 1, var4))
                {
                    var1.setBlockWithNotify(var2, var3 + 1, var4, Block.snow.blockID);
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public void UpdateWeedGrowth(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var1.getBlockId(var2, var3, var4) == this.blockID)
        {
            int var6 = this.GetWeedsGrowthLevel(var1, var2, var3, var4);
            int var7 = (int)(var1.worldInfo.getWorldTime() % 24000L);

            if (var1.isAirBlock(var2, var3 + 1, var4))
            {
                if (var7 > 14000 && var7 < 22000 && var5.nextInt(20) == 0 && var1.GetBlockNaturalLightValueMaximum(var2, var3 + 1, var4) >= 11)
                {
                    var1.setBlockWithNotify(var2, var3 + 1, var4, FCBetterThanWolves.fcBlockWeeds.blockID);
                    this.SetWeedsGrowthLevel(var1, var2, var3, var4, 1);
                }
            }
            else if (this.CanWeedsShareSpaceWithBlockAt(var1, var2, var3 + 1, var4))
            {
                if (var7 > 14000 && var7 < 22000)
                {
                    if (var6 == 0)
                    {
                        if (var5.nextInt(20) == 0)
                        {
                            this.SetWeedsGrowthLevel(var1, var2, var3, var4, 1);
                        }
                    }
                    else if (var6 % 2 == 0)
                    {
                        this.SetWeedsGrowthLevel(var1, var2, var3, var4, var6 + 1);
                    }
                }
                else if (var1.GetBlockNaturalLightValue(var2, var3 + 1, var4) >= 11)
                {
                    if (var6 == 7)
                    {
                        this.SetWeedsGrowthLevel(var1, var2, var3, var4, 0);
                        var1.setBlockAndMetadataWithNotify(var2, var3 + 1, var4, Block.tallGrass.blockID, 1);
                    }
                    else if (var6 % 2 == 1)
                    {
                        this.SetWeedsGrowthLevel(var1, var2, var3, var4, var6 + 1);
                    }
                }
            }
            else if (var6 > 0)
            {
                this.SetWeedsGrowthLevel(var1, var2, var3, var4, 0);
            }
        }
    }
}

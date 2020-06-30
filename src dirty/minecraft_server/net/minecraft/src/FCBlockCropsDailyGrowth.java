package net.minecraft.src;

import java.util.Random;

public abstract class FCBlockCropsDailyGrowth extends FCBlockCrops
{
    protected FCBlockCropsDailyGrowth(int var1)
    {
        super(var1);
    }

    public float GetBaseGrowthChance(World var1, int var2, int var3, int var4)
    {
        return 0.4F;
    }

    protected void AttemptToGrow(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = (int)(var1.worldInfo.getWorldTime() % 24000L);

        if (var6 > 14000 && var6 < 22000)
        {
            if (this.GetHasGrownToday(var1, var2, var3, var4))
            {
                this.SetHasGrownToday(var1, var2, var3, var4, false);
            }
        }
        else if (!this.GetHasGrownToday(var1, var2, var3, var4) && this.GetWeedsGrowthLevel(var1, var2, var3, var4) == 0 && var1.GetBlockNaturalLightValue(var2, var3 + 1, var4) >= this.GetLightLevelForGrowth())
        {
            Block var7 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

            if (var7 != null && var7.IsBlockHydratedForPlantGrowthOn(var1, var2, var3 - 1, var4))
            {
                float var8 = this.GetBaseGrowthChance(var1, var2, var3, var4);

                if (var7.GetIsFertilizedForPlantGrowth(var1, var2, var3 - 1, var4))
                {
                    var8 *= 2.0F;
                }

                if (var5.nextFloat() <= var8)
                {
                    this.IncrementGrowthLevel(var1, var2, var3, var4);
                    this.UpdateFlagForGrownToday(var1, var2, var3, var4);
                }
            }
        }
    }

    protected void UpdateFlagForGrownToday(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

        if (var5 != null && (!var5.GetIsFertilizedForPlantGrowth(var1, var2, var3 - 1, var4) || this.GetGrowthLevel(var1, var2, var3, var4) % 2 == 0))
        {
            this.SetHasGrownToday(var1, var2, var3, var4, true);
        }
    }

    protected boolean GetHasGrownToday(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetHasGrownToday(var1.getBlockMetadata(var2, var3, var4));
    }

    protected boolean GetHasGrownToday(int var1)
    {
        return (var1 & 8) != 0;
    }

    protected void SetHasGrownToday(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.SetHasGrownToday(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadata(var2, var3, var4, var6);
    }

    protected int SetHasGrownToday(int var1, boolean var2)
    {
        if (var2)
        {
            var1 |= 8;
        }
        else
        {
            var1 &= -9;
        }

        return var1;
    }
}

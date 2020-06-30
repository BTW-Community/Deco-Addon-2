package net.minecraft.src;

import java.util.Random;

public class FCBlockWoodCinders extends FCBlockFalling
{
    private static final int m_iChanceOfDisolvingInRain = 5;

    public FCBlockWoodCinders(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialLog);
        this.setHardness(0.25F);
        this.SetAxesEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.SetBuoyant();
        this.setTickRandomly(true);
        this.setStepSound(soundGravelFootstep);
        this.setUnlocalizedName("fcBlockWoodCinders");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return 0;
    }

    public boolean OnFinishedFalling(EntityFallingSand var1, float var2)
    {
        World var3 = var1.worldObj;

        if (!var3.isRemote)
        {
            int var4 = MathHelper.floor_double(var1.posX);
            int var5 = MathHelper.floor_double(var1.posY);
            int var6 = MathHelper.floor_double(var1.posZ);

            if (!FCBlockAshGroundCover.AttemptToPlaceAshAt(var3, var4, var5, var6) && !FCBlockAshGroundCover.AttemptToPlaceAshAt(var3, var4, var5 + 1, var6))
            {
                for (int var7 = 0; var7 < 16; ++var7)
                {
                    int var8 = var4 + var3.rand.nextInt(7) - 3;
                    int var9 = var5 + var3.rand.nextInt(5) - 2;
                    int var10 = var6 + var3.rand.nextInt(7) - 3;

                    if (FCBlockAshGroundCover.AttemptToPlaceAshAt(var3, var8, var9, var10))
                    {
                        break;
                    }
                }
            }
        }

        return false;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.GetIsStump(var1, var2, var3, var4))
        {
            super.updateTick(var1, var2, var3, var4, var5);
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var5.nextInt(5) == 0 && !this.GetIsStump(var1, var2, var3, var4) && var1.IsRainingAtPos(var2, var3 + 1, var4))
        {
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        return this.GetIsStump(var2, var3, var4, var5);
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        if (this.GetIsStump(var2.getBlockMetadata(var3, var4, var5)))
        {
            var2.setBlockWithNotify(var3, var4, var5, FCBetterThanWolves.fcBlockStumpCharred.blockID);
            return true;
        }
        else
        {
            return false;
        }
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsStump(var1.getBlockMetadata(var2, var3, var4)) ? 1000 : super.GetHarvestToolLevel(var1, var2, var3, var4);
    }

    public boolean GetDoesStumpRemoverWorkOnBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsStump(var1, var2, var3, var4);
    }

    public boolean GetIsStump(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return this.GetIsStump(var5);
    }

    public boolean GetIsStump(int var1)
    {
        return (var1 & 8) != 0;
    }

    public int SetIsStump(int var1, boolean var2)
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

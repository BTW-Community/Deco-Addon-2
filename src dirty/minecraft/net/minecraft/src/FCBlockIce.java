package net.minecraft.src;

import java.util.Random;

public class FCBlockIce extends BlockIce
{
    public FCBlockIce(int var1)
    {
        super(var1);
        this.SetPicksEffectiveOn();
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        if (!var1.provider.isHellWorld && this.IsNonSourceIceFromMetadata(var6) && (!this.canSilkHarvest() || !EnchantmentHelper.getSilkTouchModifier(var2)))
        {
            var2.addExhaustion(0.025F);
            var2.addStat(StatList.mineBlockStatArray[this.blockID], 1);
            FCUtilsMisc.PlaceNonPersistantWaterMinorSpread(var1, var3, var4, var5);
        }
        else
        {
            super.harvestBlock(var1, var2, var3, var4, var5, var6);
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var1.getSavedLightValue(EnumSkyBlock.Block, var2, var3, var4) > 11 - Block.lightOpacity[this.blockID])
        {
            this.Melt(var1, var2, var3, var4);
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        int var5;

        if (var1.provider.isHellWorld)
        {
            var1.setBlockWithNotify(var2, var3, var4, 0);
            var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.fizz", 0.5F, 2.6F + (var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.8F);

            for (var5 = 0; var5 < 8; ++var5)
            {
                var1.spawnParticle("largesmoke", (double)var2 + Math.random(), (double)var3 + Math.random(), (double)var4 + Math.random(), 0.0D, 0.0D, 0.0D);
            }
        }
        else
        {
            var5 = var1.getBlockId(var2, var3 + 1, var4);

            if (!FCUtilsMisc.IsIKInColdBiome(var1, var2, var4) || var5 != this.blockID && !var1.canBlockSeeTheSky(var2, var3 + 1, var4))
            {
                var1.setBlockWithNotify(var2, var3, var4, 0);
                FCUtilsMisc.PlaceNonPersistantWaterMinorSpread(var1, var2, var3, var4);
            }
        }
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        return 1.0F;
    }

    public int AdjustMetadataForPistonMove(int var1)
    {
        return var1 |= 8;
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var6;
    }

    public boolean GetCanBeSetOnFireDirectly(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean GetCanBeSetOnFireDirectlyByItem(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean SetOnFireDirectly(World var1, int var2, int var3, int var4)
    {
        this.Melt(var1, var2, var3, var4);
        return true;
    }

    public int GetChanceOfFireSpreadingDirectlyTo(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 60;
    }

    public boolean IsNonSourceIce(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.IsNonSourceIceFromMetadata(var1.getBlockMetadata(var2, var3, var4));
    }

    public void SetIsNonSourceIce(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -9;

        if (var5)
        {
            var6 |= 8;
        }

        var1.setBlockMetadata(var2, var3, var4, var6);
    }

    public boolean IsNonSourceIceFromMetadata(int var1)
    {
        return (var1 & 8) > 0;
    }

    private void Melt(World var1, int var2, int var3, int var4)
    {
        if (this.IsNonSourceIce(var1, var2, var3, var4))
        {
            FCUtilsMisc.PlaceNonPersistantWaterMinorSpread(var1, var2, var3, var4);
        }
        else
        {
            var1.setBlockWithNotify(var2, var3, var4, Block.waterMoving.blockID);
        }
    }
}

package net.minecraft.src;

import java.util.Random;

public class FCBlockSnowLooseSlab extends FCBlockSlabFalling
{
    public FCBlockSnowLooseSlab(int var1)
    {
        super(var1, Material.craftedSnow);
        this.setHardness(0.2F);
        this.SetShovelsEffectiveOn();
        this.SetBuoyant();
        this.setStepSound(soundSnowFootstep);
        this.setUnlocalizedName("fcBlockSnowLooseSlab");
        this.setTickRandomly(true);
        this.setLightOpacity(2);
        Block.useNeighborBrightness[var1] = true;
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Item.snowball.itemID;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 4;
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        this.dropBlockAsItem(var1, var3, var4, var5, var6, 0);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        if (var1.provider.isHellWorld)
        {
            var1.setBlockToAir(var2, var3, var4);
            var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.fizz", 0.5F, 2.6F + (var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.8F);

            for (int var5 = 0; var5 < 8; ++var5)
            {
                var1.spawnParticle("largesmoke", (double)var2 + Math.random(), (double)var3 + Math.random(), (double)var4 + Math.random(), 0.0D, 0.0D, 0.0D);
            }
        }
        else
        {
            this.ScheduleCheckForFall(var1, var2, var3, var4);
        }
    }

    public int GetCombinedBlockID(int var1)
    {
        return FCBetterThanWolves.fcBlockSnowLoose.blockID;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if ((!this.HasStickySnowNeighborInContact(var1, var2, var3, var4) || this.GetIsUpsideDown(var1, var2, var3, var4) && this.HasFallingBlockRestingOn(var1, var2, var3, var4)) && !this.CheckForFall(var1, var2, var3, var4) && this.GetIsUpsideDown(var1, var2, var3, var4))
        {
            this.SetIsUpsideDown(var1, var2, var3, var4, false);
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6;

        if (FCUtilsMisc.IsIKInColdBiome(var1, var2, var4))
        {
            if (var5.nextInt(1) == 0)
            {
                var6 = this.GetHardeningLevel(var1, var2, var3, var4);

                if (var6 < 7)
                {
                    this.SetHardeningLevel(var1, var2, var3, var4, var6 + 1);
                }
                else
                {
                    this.ConvertToSolidSnow(var1, var2, var3, var4);
                }
            }
        }
        else if (var5.nextInt(1) == 0)
        {
            var6 = this.GetHardeningLevel(var1, var2, var3, var4);

            if (var6 > 0)
            {
                this.SetHardeningLevel(var1, var2, var3, var4, var6 - 1);
            }
            else
            {
                this.Melt(var1, var2, var3, var4);
            }
        }
    }

    public boolean CanBePlacedUpsideDownAtLocation(World var1, int var2, int var3, int var4)
    {
        return this.HasStickySnowNeighborInContact(var1, var2, var3, var4, true);
    }

    public void OnPlayerWalksOnBlock(World var1, int var2, int var3, int var4, EntityPlayer var5)
    {
        if (!this.CheckForFall(var1, var2, var3, var4) && this.GetIsUpsideDown(var1, var2, var3, var4))
        {
            this.SetIsUpsideDown(var1, var2, var3, var4, false);
        }
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        return 0.8F;
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

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return true;
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int var1)
    {
        return new ItemStack(this.blockID, 1, 0);
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public int GetHardeningLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetHardeningLevel(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetHardeningLevel(int var1)
    {
        return (var1 & 14) >> 1;
    }

    public void SetHardeningLevel(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.SetHardeningLevel(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetHardeningLevel(int var1, int var2)
    {
        var1 &= -15;
        return var1 | var2 << 1;
    }

    private void ConvertToSolidSnow(World var1, int var2, int var3, int var4)
    {
        int var5 = FCBetterThanWolves.fcBlockSnowSolidSlab.SetIsUpsideDown(0, this.GetIsUpsideDown(var1, var2, var3, var4));
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockSnowSolidSlab.blockID, var5);
    }

    private void Melt(World var1, int var2, int var3, int var4)
    {
        FCUtilsMisc.PlaceNonPersistantWaterMinorSpread(var1, var2, var3, var4);
    }
}

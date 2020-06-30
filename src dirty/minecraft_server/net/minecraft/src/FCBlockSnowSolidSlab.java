package net.minecraft.src;

import java.util.Random;

public class FCBlockSnowSolidSlab extends FCBlockSlab
{
    public FCBlockSnowSolidSlab(int var1)
    {
        super(var1, Material.craftedSnow);
        this.setHardness(0.5F);
        this.SetShovelsEffectiveOn();
        this.SetBuoyant();
        this.setStepSound(soundSnowFootstep);
        this.setUnlocalizedName("fcBlockSnowSolidSlab");
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
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!FCUtilsMisc.IsIKInColdBiome(var1, var2, var4) && var5.nextInt(1) == 0)
        {
            this.ConvertToLooseSnow(var1, var2, var3, var4);
        }
    }

    public int GetCombinedBlockID(int var1)
    {
        return FCBetterThanWolves.fcBlockSnowSolid.blockID;
    }

    public boolean IsStickyToSnow(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        return 1.0F;
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

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    private void ConvertToLooseSnow(World var1, int var2, int var3, int var4)
    {
        int var5 = FCBetterThanWolves.fcBlockSnowLooseSlab.SetIsUpsideDown(0, this.GetIsUpsideDown(var1, var2, var3, var4));
        var5 = FCBetterThanWolves.fcBlockSnowLooseSlab.SetHardeningLevel(var5, 7);
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockSnowLooseSlab.blockID, var5);
        FCBetterThanWolves.fcBlockSnowLooseSlab.ScheduleCheckForFall(var1, var2, var3, var4);
    }

    private void Melt(World var1, int var2, int var3, int var4)
    {
        FCUtilsMisc.PlaceNonPersistantWaterMinorSpread(var1, var2, var3, var4);
    }
}

package net.minecraft.src;

import java.util.Random;

public class FCBlockMycelium extends BlockMycelium
{
    public static final int m_iMyceliumSurviveMinimumLightLevel = 4;
    public static final int m_iMyceliumSpreadToMinimumLightLevel = 4;

    protected FCBlockMycelium(int var1)
    {
        super(var1);
        this.setHardness(0.6F);
        this.SetShovelsEffectiveOn();
        this.SetHoesEffectiveOn();
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("mycel");
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockId(var2, var3 + 1, var4);
        Block var7 = Block.blocksList[var6];

        if (var1.getBlockLightValue(var2, var3 + 1, var4) >= 4 && Block.lightOpacity[var6] <= 2 && (var7 == null || !var7.HasLargeCenterHardPointToFacing(var1, var2, var3 + 1, var4, 0)))
        {
            CheckForMyceliumSpreadFromLocation(var1, var2, var3, var4);
        }
        else
        {
            var1.setBlockWithNotify(var2, var3, var4, Block.dirt.blockID);
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcBlockDirtLoose.blockID;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileDirt.itemID, 6, 0, var6);
        return true;
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        super.OnBlockDestroyedWithImproperTool(var1, var2, var3, var4, var5, var6);
        this.OnDirtDugWithImproperTool(var1, var3, var4, var5);
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5)
    {
        super.onBlockDestroyedByExplosion(var1, var2, var3, var4, var5);
        this.OnDirtDugWithImproperTool(var1, var2, var3, var4);
    }

    protected void OnNeighborDirtDugWithImproperTool(World var1, int var2, int var3, int var4, int var5)
    {
        if (var5 == 0)
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);
        }
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanMobsSpawnOn(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean CanBeGrazedOn(IBlockAccess var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        return var5.CanGrazeMycelium();
    }

    public void OnGrazed(World var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        if (!var5.GetDisruptsEarthOnGraze())
        {
            var1.setBlockWithNotify(var2, var3, var4, Block.dirt.blockID);
        }
        else
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);
            this.NotifyNeighborsBlockDisrupted(var1, var2, var3, var4);
        }
    }

    public void OnVegetationAboveGrazed(World var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        if (var5.GetDisruptsEarthOnGraze())
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);
            this.NotifyNeighborsBlockDisrupted(var1, var2, var3, var4);
        }
    }

    public boolean GetCanBlightSpreadToBlock(World var1, int var2, int var3, int var4, int var5)
    {
        return var5 >= 2;
    }

    public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        return var1 != null && var1.getItem() instanceof FCItemHoe;
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        var2.setBlockWithNotify(var3, var4, var5, FCBetterThanWolves.fcBlockDirtLoose.blockID);

        if (!var2.isRemote)
        {
            var2.playAuxSFX(2001, var3, var4, var5, this.blockID);
        }

        return true;
    }

    public static void CheckForMyceliumSpreadFromLocation(World var0, int var1, int var2, int var3)
    {
        if (var0.provider.dimensionId != 1 && var0.getBlockLightValue(var1, var2 + 1, var3) >= 9 && !FCBlockGroundCover.IsGroundCoverRestingOnBlock(var0, var1, var2, var3))
        {
            for (int var4 = 0; var4 < 2; ++var4)
            {
                CheckForMyceliumSpreadToRandomBlockAround(var0, var1, var2, var3);
            }
        }
    }

    public static void CheckForMyceliumSpreadToRandomBlockAround(World var0, int var1, int var2, int var3)
    {
        int var4 = var1 + var0.rand.nextInt(3) - 1;
        int var5 = var2 + var0.rand.nextInt(5) - 3;
        int var6 = var3 + var0.rand.nextInt(3) - 1;
        Block var7 = Block.blocksList[var0.getBlockId(var4, var5, var6)];

        if (var7 != null)
        {
            var7.AttempToSpreadMyceliumToBlock(var0, var4, var5, var6);
        }
    }
}

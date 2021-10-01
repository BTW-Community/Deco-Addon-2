package net.minecraft.src;

import java.util.Random;

public class FCBlockGrass extends BlockGrass
{
    public static final int m_iGrassSpreadFromLightLevel = 11;
    public static final int m_iGrassSpreadToLightLevel = 11;
    public static final int m_iGrassSurviveMinimumLightLevel = 9;
    private boolean m_bTempHasSnowOnTop;
    private Icon iconGrassTop;
    private Icon iconGrassTopRough;
    private Icon iconSnowSide;
    private Icon iconGrassSideOverlay;

    protected FCBlockGrass(int var1)
    {
        super(var1);
        this.setHardness(0.6F);
        this.SetShovelsEffectiveOn();
        this.SetHoesEffectiveOn();
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("grass");
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote)
        {
        	Block blockAbove = Block.blocksList[world.getBlockId(x, y + 1, z)];
        	
            if ((world.getBlockLightValue(x, y + 1, z) < 4 && Block.lightOpacity[world.getBlockId(x, y + 1, z)] > 2) || (blockAbove != null && !blockAbove.GetCanGrassGrowUnderBlock(world, x, y + 1, z, false)))
            {
                world.setBlock(x, y, z, Block.dirt.blockID);
            }
            else if ((world.getBlockLightValue(x, y + 1, z) >= 9 || world.GetBlockNaturalLightValue(x, y + 1, z) >= 9) && (blockAbove == null || blockAbove.GetCanGrassGrowUnderBlock(world, x, y + 1, z, false)))
            {
                CheckForGrassSpreadFromLocation(world, x, y, z);
            }
        }
    }

    public boolean CanBeGrazedOn(IBlockAccess blockAccess, int x, int y, int z, EntityAnimal animal)
    {
    	World world = (World) blockAccess;
    	int skylight = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
        return skylight >= 9;
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

    public boolean GetCanBlightSpreadToBlock(World var1, int var2, int var3, int var4, int var5)
    {
        return true;
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
            if (var2.rand.nextInt(25) == 0)
            {
                FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemHempSeeds), var6);
            }
        }

        return true;
    }

    public static void CheckForGrassSpreadFromLocation(World var0, int var1, int var2, int var3)
    {
        if (var0.provider.dimensionId != 1 && !FCBlockGroundCover.IsGroundCoverRestingOnBlock(var0, var1, var2, var3))
        {
            int var4 = var1 + var0.rand.nextInt(3) - 1;
            int var5 = var2 + var0.rand.nextInt(5) - 3;
            int var6 = var3 + var0.rand.nextInt(3) - 1;
            Block var7 = Block.blocksList[var0.getBlockId(var4, var5, var6)];

            if (var7 != null)
            {
                var7.AttempToSpreadGrassToBlock(var0, var4, var5, var6);
            }
        }
    }
}

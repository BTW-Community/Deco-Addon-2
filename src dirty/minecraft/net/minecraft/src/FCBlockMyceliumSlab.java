package net.minecraft.src;

import java.util.Random;

public class FCBlockMyceliumSlab extends FCBlockSlabAttached
{
    private Icon m_iconBottom;
    private Icon m_iconSide;
    private Icon m_iconSideHalf;
    private Icon m_iconSideSnow;
    private Icon m_iconSideHalfSnow;

    protected FCBlockMyceliumSlab(int var1)
    {
        super(var1, Material.grass);
        this.setHardness(0.6F);
        this.SetShovelsEffectiveOn();
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("fcBlockMyceliumSlab");
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockId(var2, var3 + 1, var4);
        Block var7 = Block.blocksList[var6];
        boolean var8 = this.GetIsUpsideDown(var1, var2, var3, var4);

        if ((!var8 || var7 == null || !var7.HasLargeCenterHardPointToFacing(var1, var2, var3 + 1, var4, 0)) && var1.getBlockLightValue(var2, var3 + 1, var4) >= 4 && Block.lightOpacity[var6] <= 2)
        {
            FCBlockMycelium.CheckForMyceliumSpreadFromLocation(var1, var2, var3, var4);
        }
        else
        {
            int var9 = this.SetIsUpsideDown(0, var8);
            var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtSlab.blockID, var9);
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcBlockDirtLooseSlab.blockID;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileDirt.itemID, 3, 0, var6);
        return true;
    }

    public boolean AttemptToCombineWithFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        if (var5.blockID == FCBetterThanWolves.fcBlockDirtLooseSlab.blockID && !this.GetIsUpsideDown(var1, var2, var3, var4))
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);
            return true;
        }
        else
        {
            return super.AttemptToCombineWithFallingEntity(var1, var2, var3, var4, var5);
        }
    }

    protected void OnAnchorBlockLost(World var1, int var2, int var3, int var4)
    {
        this.DropComponentItemsOnBadBreak(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 1.0F);
    }

    public int GetCombinedBlockID(int var1)
    {
        return Block.mycelium.blockID;
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
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
            int var6 = this.SetIsUpsideDown(0, this.GetIsUpsideDown(var1, var2, var3, var4));
            var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtSlab.blockID, var6);
        }
        else
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLooseSlab.blockID);
            this.NotifyNeighborsBlockDisrupted(var1, var2, var3, var4);
        }
    }

    public void OnVegetationAboveGrazed(World var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        if (var5.GetDisruptsEarthOnGraze())
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLooseSlab.blockID);
            this.NotifyNeighborsBlockDisrupted(var1, var2, var3, var4);
        }
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        super.OnBlockDestroyedWithImproperTool(var1, var2, var3, var4, var5, var6);
        this.OnDirtSlabDugWithImproperTool(var1, var3, var4, var5, this.GetIsUpsideDown(var6));
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5)
    {
        super.onBlockDestroyedByExplosion(var1, var2, var3, var4, var5);
        this.OnDirtSlabDugWithImproperTool(var1, var2, var3, var4, this.GetIsUpsideDown(var1, var2, var3, var4));
    }

    protected void OnNeighborDirtDugWithImproperTool(World var1, int var2, int var3, int var4, int var5)
    {
        if (var5 == 0)
        {
            boolean var6 = this.GetIsUpsideDown(var1, var2, var3, var4);

            if ((!var6 || var5 != 0) && (var6 || var5 != 1))
            {
                var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLooseSlab.blockID);
            }
        }
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("fcBlockMyceliumSlab_top");
        this.m_iconBottom = var1.registerIcon("fcBlockMyceliumSlab_bottom");
        this.m_iconSide = var1.registerIcon("fcBlockMyceliumSlab_side");
        this.m_iconSideHalf = var1.registerIcon("fcBlockMyceliumSlab_side_half");
        this.m_iconSideSnow = var1.registerIcon("snow_side");
        this.m_iconSideHalfSnow = var1.registerIcon("FCBlockSlabDirt_grass_snow_side");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var1 < 2 ? (var1 == 0 ? this.m_iconBottom : this.blockIcon) : (this.GetIsUpsideDown(var2) ? this.m_iconSide : this.m_iconSideHalf);
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        return var5 > 1 && this.IsSnowCoveringTopSurface(var1, var2, var3, var4) ? (this.GetIsUpsideDown(var6) ? this.m_iconSideSnow : this.m_iconSideHalfSnow) : this.getIcon(var5, var6);
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        super.randomDisplayTick(var1, var2, var3, var4, var5);

        if (var5.nextInt(10) == 0)
        {
            double var6 = (double)var3 + 0.6D;

            if (this.GetIsUpsideDown(var1, var2, var3, var4))
            {
                var6 += 0.5D;
            }

            var1.spawnParticle("townaura", (double)var2 + var5.nextDouble(), var6, (double)var4 + var5.nextDouble(), 0.0D, 0.0D, 0.0D);
        }
    }
}

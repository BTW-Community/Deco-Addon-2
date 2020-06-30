package net.minecraft.src;

import java.util.Random;

public class FCBlockMyceliumSlab extends FCBlockSlabAttached
{
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
}

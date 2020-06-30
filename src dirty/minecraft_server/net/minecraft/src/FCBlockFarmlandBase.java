package net.minecraft.src;

import java.util.Random;

public abstract class FCBlockFarmlandBase extends Block
{
    protected FCBlockFarmlandBase(int var1)
    {
        super(var1, Material.ground);
        this.setHardness(0.6F);
        this.SetShovelsEffectiveOn(true);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
        this.setLightOpacity(255);
        useNeighborBrightness[var1] = true;
        this.setTickRandomly(true);
        this.setStepSound(soundGravelFootstep);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.HasIrrigatingBlocks(var1, var2, var3, var4) && !var1.IsRainingAtPos(var2, var3 + 1, var4))
        {
            if (this.IsHydrated(var1, var2, var3, var4))
            {
                this.DryIncrementally(var1, var2, var3, var4);
            }
            else
            {
                this.CheckForSoilReversion(var1, var2, var3, var4);
            }
        }
        else
        {
            this.SetFullyHydrated(var1, var2, var3, var4);
        }
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D).offset((double)var2, (double)var3, (double)var4);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Block's chance to react to an entity falling on it.
     */
    public void onFallenUpon(World var1, int var2, int var3, int var4, Entity var5, float var6)
    {
        if (!var1.isRemote && var1.rand.nextFloat() < var6 - 0.75F)
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);
        }
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        return 0.8F;
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

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public void OnVegetationAboveGrazed(World var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        if (var5.GetDisruptsEarthOnGraze())
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);
            this.NotifyNeighborsBlockDisrupted(var1, var2, var3, var4);
        }
    }

    public boolean CanDomesticatedCropsGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanWildVegetationGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean IsBlockHydratedForPlantGrowthOn(World var1, int var2, int var3, int var4)
    {
        return this.IsHydrated(var1, var2, var3, var4);
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        if (!var1.isRemote && !this.IsFertilized(var1, var2, var3, var4) && var5.isEntityAlive() && var5 instanceof EntityItem)
        {
            EntityItem var6 = (EntityItem)var5;
            ItemStack var7 = var6.getEntityItem();

            if (var7.itemID == Item.dyePowder.itemID && var7.getItemDamage() == 15)
            {
                --var7.stackSize;

                if (var7.stackSize <= 0)
                {
                    var6.setDead();
                }

                this.SetFertilized(var1, var2, var3, var4);
                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.pop", 0.25F, ((var1.rand.nextFloat() - var1.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            }
        }
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -0.0625F;
    }

    public boolean AttemptToApplyFertilizerTo(World var1, int var2, int var3, int var4)
    {
        if (!this.IsFertilized(var1, var2, var3, var4))
        {
            this.SetFertilized(var1, var2, var3, var4);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean GetCanBlightSpreadToBlock(World var1, int var2, int var3, int var4, int var5)
    {
        return var5 >= 1;
    }

    protected boolean IsHydrated(World var1, int var2, int var3, int var4)
    {
        return this.IsHydrated(var1.getBlockMetadata(var2, var3, var4));
    }

    protected abstract boolean IsHydrated(int var1);

    protected void SetFullyHydrated(World var1, int var2, int var3, int var4)
    {
        int var5 = this.SetFullyHydrated(var1.getBlockMetadata(var2, var3, var4));
        var1.setBlockMetadataWithNotify(var2, var3, var4, var5);
    }

    protected abstract int SetFullyHydrated(int var1);

    protected abstract void DryIncrementally(World var1, int var2, int var3, int var4);

    protected abstract boolean IsFertilized(IBlockAccess var1, int var2, int var3, int var4);

    protected void SetFertilized(World var1, int var2, int var3, int var4) {}

    protected int GetHorizontalHydrationRange(World var1, int var2, int var3, int var4)
    {
        return 4;
    }

    protected boolean HasIrrigatingBlocks(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetHorizontalHydrationRange(var1, var2, var3, var4);

        for (int var6 = var2 - var5; var6 <= var2 + var5; ++var6)
        {
            for (int var7 = var3; var7 <= var3 + 1; ++var7)
            {
                for (int var8 = var4 - var5; var8 <= var4 + var5; ++var8)
                {
                    if (var1.getBlockMaterial(var6, var7, var8) == Material.water)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    protected boolean DoesBlockAbovePreventSoilReversion(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3 + 1, var4);
        return var5 == Block.crops.blockID || var5 == Block.melonStem.blockID || var5 == Block.pumpkinStem.blockID || var5 == Block.potato.blockID || var5 == Block.carrot.blockID;
    }

    protected void CheckForSoilReversion(World var1, int var2, int var3, int var4)
    {
        if (!this.DoesBlockAbovePreventSoilReversion(var1, var2, var3, var4))
        {
            var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);
        }
    }
}

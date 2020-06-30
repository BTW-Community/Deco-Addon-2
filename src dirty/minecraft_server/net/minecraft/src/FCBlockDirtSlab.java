package net.minecraft.src;

import java.util.Random;

public class FCBlockDirtSlab extends FCBlockSlabAttached
{
    public static final int m_iSubtypeDirt = 0;
    public static final int m_iSubtypeGrass = 1;
    public static final int m_iSubtypeMycelium = 2;
    public static final int m_iSubtypePackedEarth = 3;
    public static final int m_iNumSubtypes = 4;

    public FCBlockDirtSlab(int var1)
    {
        super(var1, Material.ground);
        this.setHardness(0.5F);
        this.SetShovelsEffectiveOn(true);
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("fcBlockSlabDirt");
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = this.GetSubtype(var1, var2, var3, var4);

        if (var6 == 1)
        {
            int var7 = var1.GetBlockNaturalLightValueMaximum(var2, var3 + 1, var4);
            int var8 = var7 - var1.skylightSubtracted;
            boolean var9 = this.GetIsUpsideDown(var1, var2, var3, var4);
            int var10 = var1.getBlockId(var2, var3 + 1, var4);
            Block var11 = Block.blocksList[var10];

            if ((var11 == null || var11.GetCanGrassGrowUnderBlock(var1, var2, var3 + 1, var4, !var9)) && var7 >= 9 && Block.lightOpacity[var1.getBlockId(var2, var3 + 1, var4)] <= 2)
            {
                if (var8 >= 11)
                {
                    FCBlockGrass.CheckForGrassSpreadFromLocation(var1, var2, var3, var4);
                }
            }
            else
            {
                this.SetSubtype(var1, var2, var3, var4, 0);
            }
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        int var2 = this.GetSubtype(var1);
        return var2 == 3 ? var2 : 0;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        int var4 = this.GetSubtype(var1);
        return var4 == 3 ? super.idDropped(var1, var2, var3) : FCBetterThanWolves.fcBlockDirtLooseSlab.blockID;
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        float var5 = 1.0F;
        int var6 = this.GetSubtype(var1, var2, var3, var4);

        if (var6 == 3)
        {
            var5 = 1.2F;
        }

        return var5;
    }

    public StepSound GetStepSound(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetSubtype(var1, var2, var3, var4);
        return var5 != 0 && var5 != 3 ? this.stepSound : soundGravelFootstep;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        byte var7 = 3;

        if (this.GetSubtype(var5) == 3)
        {
            var7 = 6;
        }

        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileDirt.itemID, var7, 0, var6);
        return true;
    }

    public boolean GetCanGrassSpreadToBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetSubtype(var1, var2, var3, var4);

        if (var5 == 0)
        {
            Block var6 = Block.blocksList[var1.getBlockId(var2, var3 + 1, var4)];
            boolean var7 = this.GetIsUpsideDown(var1, var2, var3, var4);

            if (var6 == null || var6.GetCanGrassGrowUnderBlock(var1, var2, var3 + 1, var4, !var7))
            {
                return true;
            }
        }

        return false;
    }

    public boolean SpreadGrassToBlock(World var1, int var2, int var3, int var4)
    {
        this.SetSubtype(var1, var2, var3, var4, 1);
        return true;
    }

    public boolean GetCanMyceliumSpreadToBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetSubtype(var1, var2, var3, var4);
        return var5 != 0 ? false : !this.GetIsUpsideDown(var1, var2, var3, var4) || !FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 + 1, var4, 0);
    }

    public boolean SpreadMyceliumToBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = ((FCBlockMyceliumSlab)FCBetterThanWolves.fcBlockMyceliumSlab).SetIsUpsideDown(0, this.GetIsUpsideDown(var1, var2, var3, var4));
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockMyceliumSlab.blockID, var5);
        return true;
    }

    public boolean AttemptToCombineWithFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        if (var5.blockID == FCBetterThanWolves.fcBlockDirtLooseSlab.blockID)
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);

            if (this.GetSubtype(var6) != 3 && !this.GetIsUpsideDown(var6))
            {
                var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLoose.blockID);
                return true;
            }
        }

        return super.AttemptToCombineWithFallingEntity(var1, var2, var3, var4, var5);
    }

    protected void OnAnchorBlockLost(World var1, int var2, int var3, int var4)
    {
        this.DropComponentItemsOnBadBreak(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 1.0F);
    }

    public int GetCombinedBlockID(int var1)
    {
        int var2 = this.GetSubtype(var1);
        return var2 == 3 ? FCBetterThanWolves.fcBlockAestheticOpaqueEarth.blockID : Block.dirt.blockID;
    }

    public int GetCombinedMetadata(int var1)
    {
        int var2 = this.GetSubtype(var1);
        return var2 == 3 ? 6 : 0;
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

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int var1)
    {
        return new ItemStack(this.blockID, 1, this.GetSubtype(var1));
    }

    public boolean CanBeGrazedOn(IBlockAccess var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        return this.GetSubtype(var1, var2, var3, var4) == 1;
    }

    public void OnGrazed(World var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        if (!var5.GetDisruptsEarthOnGraze())
        {
            this.SetSubtype(var1, var2, var3, var4, 0);
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

        if (this.GetSubtype(var6) != 3)
        {
            this.OnDirtSlabDugWithImproperTool(var1, var3, var4, var5, this.GetIsUpsideDown(var6));
        }
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5)
    {
        super.onBlockDestroyedByExplosion(var1, var2, var3, var4, var5);

        if (this.GetSubtype(var1, var2, var3, var4) != 3)
        {
            this.OnDirtSlabDugWithImproperTool(var1, var2, var3, var4, this.GetIsUpsideDown(var1, var2, var3, var4));
        }
    }

    protected void OnNeighborDirtDugWithImproperTool(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetSubtype(var1, var2, var3, var4);

        if (var6 != 3 && (var6 != 1 || var5 == 0))
        {
            boolean var7 = this.GetIsUpsideDown(var1, var2, var3, var4);

            if ((!var7 || var5 != 0) && (var7 || var5 != 1))
            {
                var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtLooseSlab.blockID);
            }
        }
    }

    public int GetSubtype(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetSubtype(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetSubtype(int var1)
    {
        return (var1 & -2) >> 1;
    }

    public void SetSubtype(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & 1;
        var6 |= var5 << 1;
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }
}

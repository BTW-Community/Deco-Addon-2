package net.minecraft.src;

import java.util.Random;

public class FCBlockHempCrop extends BlockCrops
{
    private static final double m_dCollisionBoxWidth = 0.4000000059604645D;
    private static final double m_dCollisionBoxHalfWidth = 0.20000000298023224D;
    private static final float m_fBaseGrowthChance = 0.1F;

    protected FCBlockHempCrop(int var1)
    {
        super(var1);
        this.setHardness(0.2F);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.CROPS);
        this.InitBlockBounds(0.29999999701976776D, 0.0D, 0.29999999701976776D, 0.7000000029802322D, 1.0D, 0.7000000029802322D);
        this.setUnlocalizedName("fcBlockHemp");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return var1 >= 7 ? this.getCropItem() : 0;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        var5 = MathHelper.clamp_int(var5, 0, 7);
        double var6 = (double)(1 + var5) / 8.0D;
        double var8 = 0.20000000298023224D;
        int var10 = this.GetWeedsGrowthLevel(var1, var2, var3, var4);

        if (var10 > 0)
        {
            var6 = Math.max(var6, FCBlockWeeds.GetWeedsBoundsHeight(var10));
            var8 = 0.375D;
        }

        return AxisAlignedBB.getAABBPool().getAABB(0.5D - var8, 0.0D, 0.5D - var8, 0.5D + var8, var6, 0.5D + var8);
    }

    public boolean DoesBlockDropAsItemOnSaw(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World var1, int var2, int var3, int var4)
    {
        return super.canBlockStay(var1, var2, var3, var4) || var1.getBlockId(var2, var3 - 1, var4) == this.blockID && !this.GetIsTopBlock(var1, var2, var3 - 1, var4);
    }

    protected void AttemptToGrow(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.GetIsTopBlock(var1, var2, var3, var4) && this.GetWeedsGrowthLevel(var1, var2, var3, var4) == 0 && (var1.getBlockLightValue(var2, var3, var4) >= 15 || var1.getBlockId(var2, var3 + 1, var4) == FCBetterThanWolves.fcLightBulbOn.blockID || var1.getBlockId(var2, var3 + 2, var4) == FCBetterThanWolves.fcLightBulbOn.blockID))
        {
            Block var6 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

            if (var6 != null && var6.IsBlockHydratedForPlantGrowthOn(var1, var2, var3 - 1, var4))
            {
                var1.getBlockMetadata(var2, var3, var4);
                float var8;

                if (this.GetGrowthLevel(var1, var2, var3, var4) < 7)
                {
                    var8 = this.GetBaseGrowthChance(var1, var2, var3, var4) * var6.GetPlantGrowthOnMultiplier(var1, var2, var3 - 1, var4, this);

                    if (var5.nextFloat() <= var8)
                    {
                        this.IncrementGrowthLevel(var1, var2, var3, var4);
                    }
                }
                else if (var1.isAirBlock(var2, var3 + 1, var4))
                {
                    var8 = this.GetBaseGrowthChance(var1, var2, var3, var4) / 4.0F * var6.GetPlantGrowthOnMultiplier(var1, var2, var3 - 1, var4, this);

                    if (var5.nextFloat() <= var8)
                    {
                        int var9 = this.SetIsTopBlock(0, true);
                        var1.setBlockAndMetadataWithNotify(var2, var3 + 1, var4, this.blockID, var9);
                        var6.NotifyOfFullStagePlantGrowthOn(var1, var2, var3 - 1, var4, this);
                    }
                }
            }
        }
    }

    public void DropSeeds(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        if (this.GetIsTopBlock(var5) && var1.rand.nextInt(100) < 50)
        {
            FCUtilsItem.DropStackAsIfBlockHarvested(var1, var2, var3, var4, new ItemStack(this.getSeedItem(), 1, 0));
        }
    }

    /**
     * Generate a seed ItemStack for this crop.
     */
    protected int getSeedItem()
    {
        return FCBetterThanWolves.fcItemHempSeeds.itemID;
    }

    /**
     * Generate a crop produce ItemStack for this crop.
     */
    protected int getCropItem()
    {
        return FCBetterThanWolves.fcItemHemp.itemID;
    }

    public float GetBaseGrowthChance(World var1, int var2, int var3, int var4)
    {
        return 0.1F;
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        super.harvestBlock(var1, var2, var3, var4, var5, var6);

        if (!var1.isRemote && (var2.getCurrentEquippedItem() == null || var2.getCurrentEquippedItem().itemID != Item.shears.itemID) && var1.getBlockId(var3, var4 - 1, var5) == this.blockID)
        {
            this.dropBlockAsItem(var1, var3, var4 - 1, var5, var1.getBlockMetadata(var3, var4 - 1, var5), 0);
            var1.setBlockToAir(var3, var4 - 1, var5);
        }
    }

    protected boolean GetIsTopBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsTopBlock(var1.getBlockMetadata(var2, var3, var4));
    }

    protected boolean GetIsTopBlock(int var1)
    {
        return (var1 & 8) != 0;
    }

    protected void SetIsTopBlock(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.SetIsTopBlock(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    protected int SetIsTopBlock(int var1, boolean var2)
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

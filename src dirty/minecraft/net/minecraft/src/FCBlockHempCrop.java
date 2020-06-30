package net.minecraft.src;

import java.util.Random;

public class FCBlockHempCrop extends BlockCrops
{
    private static final double m_dCollisionBoxWidth = 0.4000000059604645D;
    private static final double m_dCollisionBoxHalfWidth = 0.20000000298023224D;
    private static final float m_fBaseGrowthChance = 0.1F;
    private Icon[] m_IconBottomByGrowthArray = new Icon[8];
    private Icon m_IconTop;

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

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.m_IconTop = var1.registerIcon("fcBlockHemp_top");
        this.blockIcon = this.m_IconTop;
        this.m_IconBottomByGrowthArray[0] = var1.registerIcon("fcBlockHemp_bottom_00");
        this.m_IconBottomByGrowthArray[1] = var1.registerIcon("fcBlockHemp_bottom_01");
        this.m_IconBottomByGrowthArray[2] = var1.registerIcon("fcBlockHemp_bottom_02");
        this.m_IconBottomByGrowthArray[3] = var1.registerIcon("fcBlockHemp_bottom_03");
        this.m_IconBottomByGrowthArray[4] = var1.registerIcon("fcBlockHemp_bottom_04");
        this.m_IconBottomByGrowthArray[5] = var1.registerIcon("fcBlockHemp_bottom_05");
        this.m_IconBottomByGrowthArray[6] = var1.registerIcon("fcBlockHemp_bottom_06");
        this.m_IconBottomByGrowthArray[7] = var1.registerIcon("fcBlockHemp_bottom_07");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return this.GetIsTopBlock(var2) ? this.m_IconTop : this.m_IconBottomByGrowthArray[var2];
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.renderCrossedSquares(this, var2, var3, var4);

        if (!this.GetIsTopBlock(var1.blockAccess, var2, var3, var4))
        {
            FCBetterThanWolves.fcBlockWeeds.RenderWeeds(this, var1, var2, var3, var4);
        }

        return true;
    }
}

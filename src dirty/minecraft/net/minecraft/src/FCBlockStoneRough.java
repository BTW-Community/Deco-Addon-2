package net.minecraft.src;

public class FCBlockStoneRough extends FCBlockFullBlock
{
    public static FCBlockStoneRough[] m_startaLevelBlockArray = new FCBlockStoneRough[3];
    public int m_iStrataLevel;
    private Icon m_iconBroken;
    private Icon[] m_crackIcons;

    public FCBlockStoneRough(int var1, int var2)
    {
        super(var1, Material.rock);
        this.m_iStrataLevel = var2;
        m_startaLevelBlockArray[var2] = this;

        if (var2 == 0)
        {
            this.setHardness(2.25F);
            this.setResistance(10.0F);
        }
        else if (var2 == 1)
        {
            this.setHardness(3.0F);
            this.setResistance(13.0F);
        }
        else
        {
            this.setHardness(4.5F);
            this.setResistance(20.0F);
        }

        this.SetPicksEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("fcBlockStoneRough");
    }

    public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        if (this.m_iStrataLevel == 0 && var1 != null && var1.getItem() instanceof FCItemPickaxe)
        {
            int var6 = ((FCItemTool)var1.getItem()).toolMaterial.getHarvestLevel();

            if (var6 <= 1)
            {
                return false;
            }
        }

        return true;
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        int var7 = var2.getBlockMetadata(var3, var4, var5);

        if (var7 < 15)
        {
            ++var7;

            if (!var2.isRemote && this.IsEffectiveItemConversionTool(var1, var2, var3, var4, var5))
            {
                if (var7 <= 8)
                {
                    if ((var7 & 1) == 0)
                    {
                        var2.playAuxSFX(2269, var3, var4, var5, 0);
                        FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemStone, 1), var6);
                    }
                    else if (var7 <= 5 && this.IsUberItemConversionTool(var1, var2, var3, var4, var5))
                    {
                        var7 += 3;
                        var2.playAuxSFX(2269, var3, var4, var5, 0);
                        FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemStoneBrick), var6);
                    }
                }
                else if (var7 == 12)
                {
                    var2.playAuxSFX(2270, var3, var4, var5, 0);
                    FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemPileGravel, 1), var6);
                }
            }

            var2.setBlockMetadataWithNotify(var3, var4, var5, var7);
            return true;
        }
        else
        {
            if (!var2.isRemote && this.IsEffectiveItemConversionTool(var1, var2, var3, var4, var5))
            {
                var2.playAuxSFX(2270, var3, var4, var5, 0);
                FCUtilsItem.DropStackAsIfBlockHarvested(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemPileGravel, 1));
            }

            return false;
        }
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        if (!var1.isRemote)
        {
            int var8 = FCBetterThanWolves.fcItemStone.itemID;
            int var9 = 1;

            if (var5 < 8)
            {
                var9 = 8 - var5 / 2;
            }
            else
            {
                var8 = FCBetterThanWolves.fcItemPileGravel.itemID;

                if (var5 < 12)
                {
                    var9 = 2;
                }
            }

            for (int var10 = 0; var10 < var9; ++var10)
            {
                this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(var8, 1, this.damageDropped(var5)));
            }
        }
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        var1.playAuxSFX(2270, var3, var4, var5, 0);
        this.DropComponentItemsWithChance(var1, var3, var4, var5, var6, 1.0F);
    }

    /**
     * Return whether this block can drop from an explosion.
     */
    public boolean canDropFromExplosion(Explosion var1)
    {
        return false;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World var1, int var2, int var3, int var4, Explosion var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        float var7 = 1.0F;

        if (var5 != null)
        {
            var7 = 1.0F / var5.explosionSize;
        }

        this.DropComponentItemsWithChance(var1, var2, var3, var4, var6, var7);
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.m_iStrataLevel > 1 ? this.m_iStrataLevel + 1 : 2;
    }

    public int GetEfficientToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.m_iStrataLevel > 0 ? this.m_iStrataLevel + 1 : 0;
    }

    public boolean AreChiselsEffectiveOn(World var1, int var2, int var3, int var4)
    {
        return var1.getBlockMetadata(var2, var3, var4) >= 8 ? false : super.AreChiselsEffectiveOn(var1, var2, var3, var4);
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return false;
    }

    public boolean IsNaturalStone(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean IsEffectiveItemConversionTool(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        if (var1 != null && var1.getItem() instanceof FCItemChisel)
        {
            int var6 = ((FCItemChisel)var1.getItem()).toolMaterial.getHarvestLevel();
            return var6 >= this.GetEfficientToolLevel(var2, var3, var4, var5);
        }
        else
        {
            return false;
        }
    }

    public boolean IsUberItemConversionTool(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        if (var1 != null && var1.getItem() instanceof FCItemChisel)
        {
            int var6 = ((FCItemChisel)var1.getItem()).toolMaterial.getHarvestLevel();
            return var6 >= this.GetUberToolLevel(var2, var3, var4, var5);
        }
        else
        {
            return false;
        }
    }

    public int GetUberToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    private void DropComponentItemsWithChance(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        if (var5 < 8)
        {
            int var7 = 4 - var5 / 2;
            this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemStone.itemID, var7, 0, var6);
        }

        byte var8 = 1;

        if (var5 < 12)
        {
            var8 = 2;
        }

        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileGravel.itemID, var8, 0, var6);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        String var2 = this.getUnlocalizedName2();

        if (this.m_iStrataLevel > 0)
        {
            var2 = var2 + "_" + this.m_iStrataLevel;
        }

        this.blockIcon = var1.registerIcon(var2);
        this.m_iconBroken = var1.registerIcon(var2 + "_broken");
        this.m_crackIcons = new Icon[7];

        for (int var3 = 0; var3 < 7; ++var3)
        {
            this.m_crackIcons[var3] = var1.registerIcon("fcOverlayStoneRough_" + (var3 + 1));
        }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var2 >= 8 ? this.m_iconBroken : this.blockIcon;
    }

    public void RenderBlockSecondPass(RenderBlocks var1, int var2, int var3, int var4, boolean var5)
    {
        if (var5)
        {
            IBlockAccess var6 = var1.blockAccess;
            int var7 = var6.getBlockMetadata(var2, var3, var4);

            if (var7 > 0 && var7 != 8)
            {
                boolean var8 = false;
                int var10;

                if (var7 < 8)
                {
                    var10 = MathHelper.clamp_int(var7 - 1, 0, 6);
                }
                else
                {
                    var10 = MathHelper.clamp_int(var7 - 9, 0, 6);
                }

                Icon var9 = this.m_crackIcons[var10];

                if (var9 != null)
                {
                    this.RenderBlockWithTexture(var1, var2, var3, var4, var9);
                }
            }
        }
    }
}

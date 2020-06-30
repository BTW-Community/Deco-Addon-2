package net.minecraft.src;

import java.util.Random;

public abstract class FCBlockOreStaged extends FCBlockOre
{
    public FCBlockOreStaged(int var1)
    {
        super(var1);
        this.SetChiselsEffectiveOn();
    }

    public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        return true;
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        int var7 = var2.getBlockMetadata(var3, var4, var5);
        int var8 = this.GetStrata(var7);
        var2.setBlockAndMetadataWithNotify(var3, var4, var5, FCBlockStoneRough.m_startaLevelBlockArray[var8].blockID, 4);

        if (!var2.isRemote)
        {
            int var9 = this.GetConversionLevelForTool(var1, var2, var3, var4, var5);

            if (var9 > 0)
            {
                var2.playAuxSFX(2269, var3, var4, var5, 0);

                if (var9 >= 3)
                {
                    this.EjectItemsOnGoodPickConversion(var1, var2, var3, var4, var5, var7, var6);
                }
                else if (var9 == 2)
                {
                    this.EjectItemsOnStonePickConversion(var1, var2, var3, var4, var5, var7, var6);
                }
                else
                {
                    this.EjectItemsOnChiselConversion(var1, var2, var3, var4, var5, var7, var6);
                }
            }
        }

        return true;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);

        if (!var1.isRemote)
        {
            this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemStone.itemID, 6, 0, 1.0F);
        }
    }

    public int GetEfficientToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetRequiredToolLevelForOre(var1, var2, var3, var4);
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetRequiredToolLevelForOre(var1, var2, var3, var4);
        int var6 = this.GetRequiredToolLevelForStrata(var1, var2, var3, var4);
        return var6 > var5 ? var6 : var5;
    }

    public abstract int IdDroppedOnConversion(int var1);

    public int DamageDroppedOnConversion(int var1)
    {
        return 0;
    }

    public int QuantityDroppedOnConversion(Random var1)
    {
        return 1;
    }

    public int IdDroppedOnStonePickConversion(int var1, Random var2, int var3)
    {
        return this.idDropped(var1, var2, var3);
    }

    public int DamageDroppedOnStonePickConversion(int var1)
    {
        return this.damageDropped(var1);
    }

    public int QuantityDroppedOnStonePickConversion(Random var1)
    {
        return this.quantityDropped(var1);
    }

    protected void EjectItemsOnGoodPickConversion(ItemStack var1, World var2, int var3, int var4, int var5, int var6, int var7)
    {
        FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(this.idDropped(var6, var2.rand, 0), this.quantityDropped(var2.rand), this.damageDropped(var6)), var7);
    }

    protected void EjectItemsOnStonePickConversion(ItemStack var1, World var2, int var3, int var4, int var5, int var6, int var7)
    {
        FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(this.IdDroppedOnStonePickConversion(var6, var2.rand, 0), this.QuantityDroppedOnStonePickConversion(var2.rand), this.DamageDroppedOnStonePickConversion(var6)), var7);
    }

    protected void EjectItemsOnChiselConversion(ItemStack var1, World var2, int var3, int var4, int var5, int var6, int var7)
    {
        FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(this.IdDroppedOnConversion(var6), this.QuantityDroppedOnConversion(var2.rand), this.DamageDroppedOnConversion(var6)), var7);
    }

    public int GetRequiredToolLevelForOre(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 0;
    }

    private int GetConversionLevelForTool(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        if (var1 != null)
        {
            int var6;

            if (var1.getItem() instanceof FCItemPickaxe)
            {
                var6 = ((FCItemTool)var1.getItem()).toolMaterial.getHarvestLevel();

                if (var6 >= this.GetRequiredToolLevelForOre(var2, var3, var4, var5))
                {
                    if (var6 > 1)
                    {
                        return 3;
                    }

                    return 2;
                }
            }
            else if (var1.getItem() instanceof FCItemChisel)
            {
                var6 = ((FCItemTool)var1.getItem()).toolMaterial.getHarvestLevel();

                if (var6 >= this.GetRequiredToolLevelForOre(var2, var3, var4, var5))
                {
                    return 1;
                }
            }
        }

        return 0;
    }
}

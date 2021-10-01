package net.minecraft.src;

public class DecoBlockSlateRough extends FCBlockStoneRough {

	public DecoBlockSlateRough(int var1) {
		super(var1, 3);
		this.setUnlocalizedName("decoBlockSlateRough");
	}

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 3;
    }

    public int GetEfficientToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 3;
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
                        FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(DecoDefs.slateStone, 1), var6);
                    }
                    else if (var7 <= 5 && this.IsUberItemConversionTool(var1, var2, var3, var4, var5))
                    {
                        var7 += 3;
                        var2.playAuxSFX(2269, var3, var4, var5, 0);
                        FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(DecoDefs.slateBrickItem), var6);
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
            int var8 = DecoDefs.slateStone.itemID;
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
}

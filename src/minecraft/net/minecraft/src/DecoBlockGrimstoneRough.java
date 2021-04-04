package net.minecraft.src;

public class DecoBlockGrimstoneRough extends FCBlockStoneRough {

	public DecoBlockGrimstoneRough(int var1) {
		super(var1, 2);
		this.setUnlocalizedName("decoBlockGrimstoneRough");
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
                        FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(DecoDefs.grimstoneTile, 1), var6);
                    }
                    else if (var7 <= 5 && this.IsUberItemConversionTool(var1, var2, var3, var4, var5))
                    {
                        var7 += 3;
                        var2.playAuxSFX(2269, var3, var4, var5, 0);
                        FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(DecoDefs.grimstoneBrickItem), var6);
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
            int var8 = DecoDefs.grimstoneTile.itemID;
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

    //CLIENT ONLY
    private Icon m_iconBroken;
    private Icon[] m_crackIcons;
    
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("decoBlockGrimstoneRough");
        this.m_iconBroken = var1.registerIcon("decoBlockGrimstoneRough_broken");
        this.m_crackIcons = new Icon[7];

        for (int var3 = 0; var3 < 7; ++var3)
        {
            this.m_crackIcons[var3] = var1.registerIcon("decoOverlayGrimstoneRough_" + (var3 + 1));
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

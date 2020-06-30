package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockStone extends FCBlockFullBlock
{
    private Icon[] m_IconByMetadataArray = new Icon[16];

    public FCBlockStone(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(2.25F);
        this.setResistance(10.0F);
        this.SetPicksEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("stone");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Returns the block hardness at a location. Args: world, x, y, z
     */
    public float getBlockHardness(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetStrata(var1, var2, var3, var4);
        return var5 != 0 ? (var5 == 1 ? 3.0F : 4.5F) : super.getBlockHardness(var1, var2, var3, var4);
    }

    public float getExplosionResistance(Entity var1, World var2, int var3, int var4, int var5)
    {
        int var6 = this.GetStrata(var2, var3, var4, var5);
        return var6 != 0 ? (var6 == 1 ? 7.8F : 12.0F) : super.getExplosionResistance(var1, var2, var3, var4, var5);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcBlockCobblestoneLoose.blockID;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);

        if (!var1.isRemote)
        {
            this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(FCBetterThanWolves.fcItemStone));

            if (!this.GetIsCracked(var5))
            {
                this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(FCBetterThanWolves.fcItemPileGravel));
            }
        }
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemStone.itemID, 5, 0, var6);
        int var7 = this.GetIsCracked(var5) ? 2 : 3;
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileGravel.itemID, var7, 0, var6);
        return true;
    }

    public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        return true;
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        int var7 = var2.getBlockMetadata(var3, var4, var5);
        int var8 = this.GetStrata(var7);
        int var9 = this.GetConversionLevelForTool(var1, var2, var3, var4, var5);

        if (this.GetIsCracked(var7))
        {
            var2.setBlockAndMetadataWithNotify(var3, var4, var5, FCBlockStoneRough.m_startaLevelBlockArray[var8].blockID, 0);

            if (!var2.isRemote && var9 > 0)
            {
                var2.playAuxSFX(2269, var3, var4, var5, 0);
                FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemStone, 1), var6);
            }
        }
        else if (var9 == 2)
        {
            var2.setBlockAndMetadataWithNotify(var3, var4, var5, FCBlockStoneRough.m_startaLevelBlockArray[var8].blockID, 4);

            if (!var2.isRemote)
            {
                var2.playAuxSFX(2269, var3, var4, var5, 0);
                FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemStone, 3), var6);
                FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemPileGravel, 1), var6);
            }
        }
        else if (var9 == 3)
        {
            var2.setBlockAndMetadataWithNotify(var3, var4, var5, FCBlockStoneRough.m_startaLevelBlockArray[var8].blockID, 2);

            if (!var2.isRemote)
            {
                var2.playAuxSFX(2269, var3, var4, var5, 0);
                FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemStoneBrick), var6);
                FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemPileGravel, 1), var6);
            }
        }
        else
        {
            if (!var2.isRemote)
            {
                var2.playAuxSFX(2270, var3, var4, var5, 0);
                FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemPileGravel, 1), var6);
            }

            this.SetIsCracked(var2, var3, var4, var5, true);
        }

        return true;
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetStrata(var1, var2, var3, var4);
        return var5 > 1 ? var5 + 1 : 2;
    }

    public int GetEfficientToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetStrata(var1, var2, var3, var4);
        return var5 > 0 ? var5 + 1 : 0;
    }

    public boolean IsNaturalStone(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    private int GetConversionLevelForTool(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        if (var1 != null)
        {
            int var6;

            if (var1.getItem() instanceof FCItemPickaxe)
            {
                var6 = ((FCItemTool)var1.getItem()).toolMaterial.getHarvestLevel();

                if (var6 >= this.GetEfficientToolLevel(var2, var3, var4, var5))
                {
                    return 2;
                }
            }
            else if (var1.getItem() instanceof FCItemChisel)
            {
                var6 = ((FCItemTool)var1.getItem()).toolMaterial.getHarvestLevel();

                if (var6 >= this.GetEfficientToolLevel(var2, var3, var4, var5))
                {
                    if (var6 >= this.GetUberToolLevel(var2, var3, var4, var5))
                    {
                        return 3;
                    }

                    return 1;
                }
            }
        }

        return 0;
    }

    public int GetStrata(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetStrata(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetStrata(int var1)
    {
        return var1 & 3;
    }

    public void SetIsCracked(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.SetIsCracked(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetIsCracked(int var1, boolean var2)
    {
        if (var2)
        {
            var1 |= 4;
        }
        else
        {
            var1 &= -5;
        }

        return var1;
    }

    public boolean GetIsCracked(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsCracked(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean GetIsCracked(int var1)
    {
        return (var1 & 4) != 0;
    }

    public int GetUberToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        var3.add(new ItemStack(var1, 1, 0));
        var3.add(new ItemStack(var1, 1, 1));
        var3.add(new ItemStack(var1, 1, 2));
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_IconByMetadataArray[0] = this.blockIcon;
        this.m_IconByMetadataArray[1] = var1.registerIcon("fcBlockStone_1");
        this.m_IconByMetadataArray[2] = var1.registerIcon("fcBlockStone_2");
        this.m_IconByMetadataArray[3] = this.blockIcon;
        this.m_IconByMetadataArray[4] = var1.registerIcon("fcBlockStone_cracked");
        this.m_IconByMetadataArray[5] = var1.registerIcon("fcBlockStone_1_cracked");
        this.m_IconByMetadataArray[6] = var1.registerIcon("fcBlockStone_2_cracked");
        this.m_IconByMetadataArray[7] = this.blockIcon;

        for (int var2 = 8; var2 < 16; ++var2)
        {
            this.m_IconByMetadataArray[var2] = this.blockIcon;
        }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return this.m_IconByMetadataArray[var2];
    }
}

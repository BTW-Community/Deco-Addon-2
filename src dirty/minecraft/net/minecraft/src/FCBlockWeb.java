package net.minecraft.src;

import java.util.Random;

public class FCBlockWeb extends BlockWeb
{
    private Icon[] m_IconByDamageArray = new Icon[4];

    public FCBlockWeb(int var1)
    {
        super(var1);
        this.SetAxesEffectiveOn(true);
        this.SetChiselsEffectiveOn(true);
        this.setHardness(4.0F);
        this.SetBuoyant();
        this.setStepSound(FCBetterThanWolves.fcStepSoundSquish);
        this.setUnlocalizedName("web");
        this.setCreativeTab((CreativeTabs)null);
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        if (var2.getCurrentEquippedItem() != null && var2.getCurrentEquippedItem().itemID == Item.shears.itemID && this.GetDamageLevel(var6) == 0)
        {
            var2.addStat(StatList.mineBlockStatArray[this.blockID], 1);
            this.dropBlockAsItem_do(var1, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcBlockWeb, 1, 0));
        }
        else
        {
            super.harvestBlock(var1, var2, var3, var4, var5, var6);
        }
    }

    public int GetEfficientToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return 0;
    }

    public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
    {
        return true;
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        int var7 = var2.getBlockMetadata(var3, var4, var5);
        int var8 = this.GetDamageLevel(var7);

        if (var8 < 3)
        {
            this.SetDamageLevel(var2, var3, var4, var5, var8 + 1);
            return true;
        }
        else
        {
            if (!var2.isRemote && this.IsEffectiveItemConversionTool(var1, var2, var3, var4, var5))
            {
                var2.playSoundEffect((double)var3 + 0.5D, (double)var4 + 0.5D, (double)var5 + 0.5D, "random.bow", 0.75F + var2.rand.nextFloat() * 0.25F, var2.rand.nextFloat() * 0.25F + 1.25F);
                FCUtilsItem.DropStackAsIfBlockHarvested(var2, var3, var4, var5, new ItemStack(Item.silk, 1));
            }

            return false;
        }
    }

    public void SetDamageLevel(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.SetDamageLevel(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetDamageLevel(int var1, int var2)
    {
        var1 &= -4;
        return var1 | var2;
    }

    public int GetDamageLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetDamageLevel(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetDamageLevel(int var1)
    {
        return var1 & 3;
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

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("web");
        this.m_IconByDamageArray[0] = this.blockIcon;
        this.m_IconByDamageArray[1] = var1.registerIcon("fcBlockWeb_1");
        this.m_IconByDamageArray[2] = var1.registerIcon("fcBlockWeb_2");
        this.m_IconByDamageArray[3] = var1.registerIcon("fcBlockWeb_3");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return this.m_IconByDamageArray[this.GetDamageLevel(var2)];
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return var1.renderCrossedSquares(this, var2, var3, var4);
    }
}

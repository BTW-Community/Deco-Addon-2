package net.minecraft.src;

import java.util.Random;

public class FCBlockWeb extends BlockWeb
{
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
}

package net.minecraft.src;

public class DecoBlockHempCrop extends FCBlockHempCrop {
	public DecoBlockHempCrop(int var1) {
		super(var1);
	}

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        var2.addStat(StatList.mineBlockStatArray[this.blockID], 1);
        var2.AddHarvestBlockExhaustion(this.blockID, var3, var4, var5, var6);

        if (this.canSilkHarvest(var6) && EnchantmentHelper.getSilkTouchModifier(var2))
        {
            ItemStack var8 = this.createStackedBlock(var6);

            if (var8 != null)
            {
                this.dropBlockAsItem_do(var1, var3, var4, var5, var8);
            }
        }
        else
        {
            int var7 = EnchantmentHelper.getFortuneModifier(var2);
            this.dropBlockAsItem(var1, var3, var4, var5, var6, var7);
        }
        
        if (!var1.isRemote && !(var2.getCurrentEquippedItem() != null && (var2.getCurrentEquippedItem().itemID == Item.shears.itemID || var2.getCurrentEquippedItem().itemID == DecoDefs.shearsDiamond.itemID)) && var1.getBlockId(var3, var4 - 1, var5) == this.blockID)
        {
            this.dropBlockAsItem(var1, var3, var4 - 1, var5, var1.getBlockMetadata(var3, var4 - 1, var5), 0);
            var1.setBlockToAir(var3, var4 - 1, var5);
        }
    }
}
package net.minecraft.src;

public class DecoBlockWeb extends FCBlockWeb {
	public DecoBlockWeb(int var1) {
		super(var1);
	}

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        if (var2.getCurrentEquippedItem() != null && (var2.getCurrentEquippedItem().itemID == Item.shears.itemID || var2.getCurrentEquippedItem().itemID == DecoDefs.shearsDiamond.itemID))
        {
            var2.addStat(StatList.mineBlockStatArray[this.blockID], 1);
            this.dropBlockAsItem_do(var1, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcBlockWeb, 1, 0));
        }
        else
        {
            super.harvestBlock(var1, var2, var3, var4, var5, var6);
        }
    }
}
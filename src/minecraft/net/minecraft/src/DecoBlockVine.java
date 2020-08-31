package net.minecraft.src;

public class DecoBlockVine extends FCBlockVine {
	public DecoBlockVine(int var1) {
		super(var1);
	}

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
        if (!par1World.isRemote && par2EntityPlayer.getCurrentEquippedItem() != null && (par2EntityPlayer.getCurrentEquippedItem().itemID == Item.shears.itemID || par2EntityPlayer.getCurrentEquippedItem().itemID == DecoDefs.shearsDiamond.itemID))
        {
            par2EntityPlayer.addStat(StatList.mineBlockStatArray[this.blockID], 1);
            this.dropBlockAsItem_do(par1World, par3, par4, par5, new ItemStack(Block.vine, 1, 0));
        }
        else
        {
            super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
        }
    }
}
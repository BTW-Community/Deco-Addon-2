package net.minecraft.src;

public class DecoItemBlockBookshelf extends DecoItemBlockMulti {
	public DecoItemBlockBookshelf(Block owner, String[] names) {
		super(owner, names);
	}

    public void OnUsedInCrafting(int itemDamage, EntityPlayer player, ItemStack stackResult) {
    	if (stackResult.itemID == Item.book.itemID || stackResult.itemID == DecoDefs.bookPlain.itemID) {
    		FCUtilsItem.EjectStackWithRandomVelocity(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(DecoDefs.bookshelfEmpty, 1, itemDamage));
    	}
    	if (stackResult.itemID == Item.glassBottle.itemID) {
    		FCUtilsItem.EjectStackWithRandomVelocity(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(DecoDefs.bottleRackEmpty, 1, itemDamage));
    	}
    }
}
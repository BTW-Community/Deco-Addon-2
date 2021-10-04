package net.minecraft.src;

public class DecoItemBlockBookshelf extends DecoItemBlockMulti {
	public DecoItemBlockBookshelf(Block owner, String[] names) {
		super(owner, names);
	}

	//CLIENT ONLY
	@Override
    public boolean hasEffect(ItemStack var1) {
        return var1.itemID == Block.bookShelf.blockID;
    }
}
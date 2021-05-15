package net.minecraft.src;

public class DecoItemBookEnchanted extends FCItemBook {
	public DecoItemBookEnchanted(int id) {
		super(id);
	}

	@Override
    public boolean hasEffect(ItemStack var1) {
        return true;
    }
	
	//CLIENT ONLY
	@Override
	public void registerIcons(IconRegister register) {
		this.itemIcon = register.registerIcon("book");
	}
}
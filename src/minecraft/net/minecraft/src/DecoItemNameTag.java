package net.minecraft.src;

public class DecoItemNameTag extends Item {
	protected DecoItemNameTag(int id) {
		super(id);
		this.setUnlocalizedName("ginger_nameTag");
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setMaxStackSize(1);
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityLiving entity) {
		String name = stack.getDisplayName();
		
		//Does not allow blank names (or unchanged from base name)
		if (name.equals("") || !stack.hasDisplayName())
			return false;
		
		//Applies the name
		entity.func_94058_c(name);
		//Makes name visible
		//entity.func_94061_f(true);
		entity.SetPersistent(true);
		
		stack.stackSize--;
		
		return true;
	}
}
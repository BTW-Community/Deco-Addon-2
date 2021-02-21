package net.minecraft.src;

public class DecoItemColored extends Item {
	public DecoItemColored(int par1) {
		super(par1);
	}

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack stack)
    {
        return this.getUnlocalizedName() + "." + DecoUtilsMisc.colorOrder[stack.getItemDamage()];
    }
}
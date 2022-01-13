package net.minecraft.src;

import java.util.List;

public class DecoItemBlockWhiteBrick extends ItemBlockWithMetadata {
	public DecoItemBlockWhiteBrick(int par1, Block par2Block) {
		super(par1, par2Block);
	}
	
	public String getUnlocalizedName(ItemStack reference)
	{
		int itemDamage = reference.getItemDamage();
		
		if (itemDamage == 1)
			return super.getUnlocalizedName() + ".mossy";
		if (itemDamage == 2)
			return super.getUnlocalizedName() + ".cracked";
		if (itemDamage == 3)
			return super.getUnlocalizedName() + ".chiseled";
		if (itemDamage == 4)
			return super.getUnlocalizedName() + ".smooth";
		
		return super.getUnlocalizedName();
	}
	
	public void getSubItems(int var1, CreativeTabs var2, List var3) {
		for (int var4 = 0; var4 < 5; ++var4)
			var3.add(new ItemStack(this, 1, var4));
	}
}
package net.minecraft.src;

import java.util.List;

public class DecoItemBlockBrick extends ItemBlockWithMetadata {
	public DecoItemBlockBrick(int par1, Block par2Block) {
		super(par1, par2Block);
	}
	public void getSubItems(int var1, CreativeTabs var2, List var3)
	{
		for (int var4 = 0; var4 < 4; ++var4)
			var3.add(new ItemStack(this, 1, var4));
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
		return super.getUnlocalizedName();
	}
}
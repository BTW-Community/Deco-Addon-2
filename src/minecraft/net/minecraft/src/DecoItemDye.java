package net.minecraft.src;

import java.util.List;

public class DecoItemDye extends FCItemDye
{
	public static final String[] ColorPlus_dyeColorNames = new String[] {"black","red","green","brown","blue","purple","cyan","silver","gray","pink","lime","yellow","lightBlue","magenta","orange", "white",
			"black2","red2","green2","brown2","blue2","purple2","cyan2","silver2","gray2","pink2","lime2","yellow2","lightBlue2","magenta2","orange2","white2"};

	public DecoItemDye(int ID)
	{
		super(ID);
	}

	@Override public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName() + "." + ColorPlus_dyeColorNames[stack.getItemDamage() & 31];
	}

	//CLIENT ONLY
	public Icon[] extraIcons = new Icon[16];
	
	@Override public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int var4 = 0; var4 < 16; ++var4)
		{
			par3List.add(new ItemStack(par1, 1, var4));
		}
		par3List.add(new ItemStack(par1, 1, 16));
		par3List.add(new ItemStack(par1, 1, 20));
		par3List.add(new ItemStack(par1, 1, 31));
	}

	@Override public Icon getIconFromDamage(int Meta)
	{
		return Meta>15 ? extraIcons[Meta-16] : super.getIconFromDamage(Meta);
	}

	@Override public void registerIcons(IconRegister var1)
	{
		super.registerIcons(var1);
		for (int i = 0; i < 16; i++)
		{
			extraIcons[i] = var1.registerIcon("decoItemDye_" + DecoUtilsMisc.colorOrder[i]);
			i++;
		}
	}
}
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
}
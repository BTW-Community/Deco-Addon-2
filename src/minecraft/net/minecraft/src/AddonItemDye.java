package net.minecraft.src;

import java.util.List;

public class AddonItemDye extends FCItemDye
{
	//CLIENT ONLY METHODS
			public Icon[] ExtraIcons = new Icon[16];
	//
			public static final String[] ColorPlus_dyeColorNames = new String[] {"black","red","green","brown","blue","purple","cyan","silver","gray","pink","lime","yellow","lightBlue","magenta","orange", "white","black2","red2","green2","brown2","blue2","purple2","cyan2","silver2","gray2","pink2","lime2","yellow2","lightBlue2","magenta2","orange2","white2"};
			
			public AddonItemDye(int ID)
			{
				super(ID);
				setUnlocalizedName("dyePowder");
				SetBellowsBlowDistance(2);
				AddonManager.Name(new ItemStack(this, 1, 16), "Black Dye");
				AddonManager.Name(new ItemStack(this, 1, 20), "Blue Dye");
				AddonManager.Name(new ItemStack(this, 1, 31), "White Dye");
			}
			@Override public String getUnlocalizedName(ItemStack I)
			{
				return super.getUnlocalizedName() + "." + ColorPlus_dyeColorNames[I.getItemDamage()&31];
			}
			
	//CLIENT ONLY METHODS
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
				return Meta>15 ? ExtraIcons[Meta-16] : super.getIconFromDamage(Meta);
			}
			@Override public void registerIcons(IconRegister var1)
			{
				super.registerIcons(var1);
				int I=0;
				while(I<16)
				{
					ExtraIcons[I] = var1.registerIcon("ginger_dyePowder_"+I);
					I++;
				}
			}
	//
		}
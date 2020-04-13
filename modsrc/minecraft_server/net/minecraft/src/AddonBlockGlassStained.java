package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class AddonBlockGlassStained extends FCBlockGlass
{
	public AddonBlockGlassStained(int ID)
	{
		super(ID,Material.glass,false);
		setCreativeTab((CreativeTabs)null);
		setHardness(0.3F);
		setStepSound(soundGlassFootstep);
		setUnlocalizedName("ginger_glass_");
		AddonManager.Register(this, new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan", "lightGrey", "grey", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white" },
				new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "Light Grey", "Grey", "Pink", "Lime", "Yellow", "Light Blue", "Magenta", "Orange", "White" }, " Stained Glass Block");
	}

	public int damageDropped(int Meta)
	{
		return Meta;
	}
	public int getRenderBlockPass()
	{
		return 1;
	}
}
package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class AddonBlockStone extends Block {
	public AddonBlockStone(int ID) {
		super(ID, Material.rock);
		this.setHardness(2.25F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.SetChiselsEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("addonStone");
		this.setCreativeTab(CreativeTabs.tabBlock);

		AddonManager.Register(this, new String[] {"granite", "andesite", "diorite"}, new String[] {"Granite", "Andesite", "Diorite"});
	}
	
	public int idDropped(int var1, Random rand, int var3) {
		return AddonDefs.stoneTypesCobble.blockID;
	}

	public int damageDropped(int Meta)
	{
		return Meta;
	}
	
	//CLIENT ONLY METHODS
	public static Icon[] Icons = new Icon[3];
	public Icon getIcon(int Side, int Meta)
	{
		return Icons[Meta];
	}
}

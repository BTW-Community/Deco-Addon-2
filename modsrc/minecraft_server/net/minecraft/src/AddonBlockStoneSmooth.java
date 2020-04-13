package net.minecraft.src;

public class AddonBlockStoneSmooth extends Block{
	public AddonBlockStoneSmooth(int ID) {
		super(ID, Material.rock);
		this.setHardness(2.25F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.SetChiselsEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("addonStone");
		this.setCreativeTab(CreativeTabs.tabBlock);

		AddonManager.Register(this, new String[] {"graniteSmooth", "andesiteSmooth", "dioriteSmooth"}, new String[] {"Polished Granite", "Polished Andesite", "Polished Diorite"});
	}

	public int damageDropped(int Meta)
	{
		return Meta;
	}
}

package net.minecraft.src;

public class AddonBlockPrismarine extends Block {
	public AddonBlockPrismarine(int ID) {
		super(ID, Material.rock);
        this.setHardness(2.25F);
        this.setResistance(10.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("addonStone");
		this.setCreativeTab(CreativeTabs.tabBlock);

		AddonManager.Register(this, new String[] {"prismarine", "prismarineBrick", "prismarineDark"}, new String[] {"Prismarine", "Prismarine Bricks", "Dark Prismarine"});
	}

	public int damageDropped(int Meta)
	{
		return Meta;
	}
}

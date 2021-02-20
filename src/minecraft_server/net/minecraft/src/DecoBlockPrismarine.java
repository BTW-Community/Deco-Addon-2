package net.minecraft.src;

public class DecoBlockPrismarine extends Block {
	public DecoBlockPrismarine(int ID) {
		super(ID, Material.rock);
        this.setHardness(2.25F);
        this.setResistance(10.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("decoBlockPrismarine");
		this.setCreativeTab(CreativeTabs.tabBlock);

		DecoManager.Register(this, new String[] {"prismarine", "prismarineBrick", "prismarineDark"});
	}

	public int damageDropped(int Meta)
	{
		return Meta;
	}
}

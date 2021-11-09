package net.minecraft.src;

public class DecoItemSpiderEye extends ItemFood {
	public DecoItemSpiderEye(int id) {
		super(id, 2, 0.8F, false);
		this.setPotionEffect(Potion.poison.id, 5, 0, 1.0F);
		this.SetNeutralBuoyant();
		this.SetFilterableProperties(2);
		this.setPotionEffect(PotionHelper.goldenCarrotEffect);
		this.setUnlocalizedName("spiderEye");
	}
}
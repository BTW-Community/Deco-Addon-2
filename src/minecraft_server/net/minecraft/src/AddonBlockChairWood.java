package net.minecraft.src;

public class AddonBlockChairWood extends AddonBlockChair {
	public AddonBlockChairWood(int id, String woodType, String woodName) {
		super(id, Material.wood, woodType + "wood", woodName + " Wood");
		this.SetAxesEffectiveOn( true );
		this.setStepSound(soundWoodFootstep);
	}

}

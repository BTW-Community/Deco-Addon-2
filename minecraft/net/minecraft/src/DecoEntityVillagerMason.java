package net.minecraft.src;

public class DecoEntityVillagerMason extends FCEntityVillager {
	public DecoEntityVillagerMason(World world) {
		super(world, DecoDefsIDs.id_masonProfession);
	}
	
	//CLIENT ONLY
	public String getTexture() {
        return "/deco/masonVillager.png";
	}
}
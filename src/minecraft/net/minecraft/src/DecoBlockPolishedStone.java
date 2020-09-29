package net.minecraft.src;

public class DecoBlockPolishedStone extends Block {
	public DecoBlockPolishedStone(int par1, Material par2Material) {
		super(par1, par2Material);
	}
	
	//Client only
	public void registerIcons(IconRegister register) {
		this.blockIcon = register.registerIcon("stoneslab_top");
	}
}
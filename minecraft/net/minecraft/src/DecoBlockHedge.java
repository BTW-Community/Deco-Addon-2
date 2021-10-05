package net.minecraft.src;

public class DecoBlockHedge extends FCBlockLeaves {
	protected DecoBlockHedge(int id) {
		super(id);
		this.blockMaterial = DecoDefs.materialHedge;
	}
	@Override public boolean isOpaqueCube()
	{
		return false;
	}
}
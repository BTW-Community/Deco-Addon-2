package net.minecraft.src;

public class DecoBlockHedge extends DecoBlockLeaves {
	protected DecoBlockHedge(int id) {
		super(id);
		this.blockMaterial = DecoDefs.materialHedge;
	}
	@Override public boolean isOpaqueCube()
	{
		return false;
	}
}
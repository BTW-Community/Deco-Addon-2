package net.minecraft.src;

public class AddonBlockFence extends FCBlockFence {

	public AddonBlockFence(int id, String texture, Material mat) {
		super(id, texture, mat);
	}

	@Override
    protected boolean CanConnectToBlockToFacing(IBlockAccess blockAccess, int x, int y, int z, int facing)
    {
        FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z, facing);
        return AddonUtilsBlock.canFenceConnect(blockAccess, blockPos.i, blockPos.j, blockPos.k, facing, this);
    }
}

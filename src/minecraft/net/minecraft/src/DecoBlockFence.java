package net.minecraft.src;

public class DecoBlockFence extends FCBlockFence {
	public DecoBlockFence(int id, String texture, Material mat) {
		super(id, texture, mat);
	}

	@Override
    protected boolean CanConnectToBlockToFacing(IBlockAccess blockAccess, int x, int y, int z, int facing)
    {
        FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z, facing);
        return DecoUtilsBlock.canFenceConnect(blockAccess, blockPos.i, blockPos.j, blockPos.k, facing, this);
    }
}

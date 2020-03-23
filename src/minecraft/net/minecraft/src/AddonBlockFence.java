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
	
	@Override
    protected boolean CanConnectToBlockAt(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);

        if (!isFenceConnectionException(var5))
        {
            Block var6 = Block.blocksList[var5];
            return var6 != null && var6.blockMaterial.isOpaque() && var6.renderAsNormalBlock() ? var6.blockMaterial != Material.pumpkin : false;
        }
        else
        {
            return true;
        }
    }
	
	protected boolean isFenceConnectionException(int blockID) {
		return blockID == this.blockID || blockID == Block.fenceGate.blockID || blockID == AddonDefs.gateBirch.blockID || blockID == AddonDefs.gateBlood.blockID || blockID == AddonDefs.gateJungle.blockID || blockID == AddonDefs.gateSpruce.blockID;
	}
}

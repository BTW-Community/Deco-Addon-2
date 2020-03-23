package net.minecraft.src;

public class AddonUtilsBlock {
	public static boolean canFenceConnect(IBlockAccess blockAccess, int x, int y, int z, int facing, Block thisBlock) {
		int blockID = blockAccess.getBlockId(x, y, z);
		Block block = Block.blocksList[blockID];
		
		boolean isFenceConnector = blockID == thisBlock.blockID || isFence(blockID) || isFenceGate(blockID);
		boolean isSolid = block != null && block.blockMaterial.isOpaque() && block.renderAsNormalBlock() ? block.blockMaterial != Material.pumpkin : false;
		boolean isGlass = block != null && blockID == Block.glass.blockID || blockID == AddonDefs.glassStained.blockID;
		boolean hasCenterPoint = FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(blockAccess, x, y, z, getOppositeFacing(facing));
		
		return isFenceConnector || isSolid || isGlass || hasCenterPoint;
	}
	
	public static boolean isFence(int blockID) {
		return blockID == Block.fence.blockID;
	}
	
	public static boolean isFenceGate(int blockID) {
		return blockID == Block.fenceGate.blockID || blockID == AddonDefs.gateBirch.blockID || blockID == AddonDefs.gateBlood.blockID || blockID == AddonDefs.gateJungle.blockID || blockID == AddonDefs.gateSpruce.blockID;
	}
	
	public static int getOppositeFacing(int facing) {
		return facing == 0 ? 1 : facing == 1 ? 0 : facing == 2 ? 3 : facing == 3 ? 2 : facing == 4 ? 5 : 4;
	}
}

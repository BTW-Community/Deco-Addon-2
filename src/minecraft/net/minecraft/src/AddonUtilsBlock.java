package net.minecraft.src;

public class AddonUtilsBlock {
	public static boolean canFenceConnect(IBlockAccess blockAccess, int x, int y, int z, int facing, Block thisBlock) {
		int blockID = blockAccess.getBlockId(x, y, z);
		int metadata = blockAccess.getBlockMetadata(x, y, z);
		Block block = Block.blocksList[blockID];
		
		boolean isFenceConnector = blockID == thisBlock.blockID || isFence(blockID, metadata) || isFenceGate(blockID);
		boolean isSolid = block != null && block.blockMaterial.isOpaque() && block.renderAsNormalBlock() ? block.blockMaterial != Material.pumpkin : false;
		boolean isGlass = block != null && blockID == Block.glass.blockID || blockID == AddonDefs.glassStained.blockID;
		boolean hasCenterPoint = FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(blockAccess, x, y, z, Facing.oppositeSide[facing]);
		
		return isFenceConnector || isSolid || isGlass || hasCenterPoint;
	}
	
	public static boolean canFenceConnect(World world, int x, int y, int z, int facing, Block thisBlock) {
		int blockID = world.getBlockId(x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);
		Block block = Block.blocksList[blockID];
		
		boolean isFenceConnector = blockID == thisBlock.blockID || isFence(blockID, metadata) || isFenceGate(blockID);
		boolean isSolid = block != null && block.blockMaterial.isOpaque() && block.renderAsNormalBlock();
		boolean isGlass = block != null && blockID == Block.glass.blockID || blockID == AddonDefs.glassStained.blockID;
		boolean hasCenterPoint = FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x, y, z, Facing.oppositeSide[facing]);
		
		return isFenceConnector || isSolid || isGlass || hasCenterPoint;
	}
	public static boolean canWallConnect(IBlockAccess blockAccess, int x, int y, int z, int facing, Block thisBlock) {
		int blockID = blockAccess.getBlockId(x, y, z);
		int metadata = blockAccess.getBlockMetadata(x, y, z);
		Block block = Block.blocksList[blockID];
		
		boolean isWallConnector = blockID == thisBlock.blockID || isWall(blockID, metadata) || isFenceGate(blockID);
		boolean isSolid = block != null && block.blockMaterial.isOpaque() && block.renderAsNormalBlock() ? block.blockMaterial != Material.pumpkin : false;
		boolean isGlass = block != null && blockID == Block.glass.blockID || blockID == AddonDefs.glassStained.blockID;
		boolean hasCenterPoint = FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(blockAccess, x, y, z, Facing.oppositeSide[facing]);
		
		return isWallConnector || isSolid || isGlass || hasCenterPoint;
	}
	
	public static boolean canWallConnect(World world, int x, int y, int z, int facing, Block thisBlock) {
		int blockID = world.getBlockId(x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);
		Block block = Block.blocksList[blockID];
		
		boolean isWallConnector = blockID == thisBlock.blockID || isWall(blockID, metadata) || isFenceGate(blockID);
		boolean isSolid = block != null && block.blockMaterial.isOpaque() && block.renderAsNormalBlock();
		boolean isGlass = block != null && blockID == Block.glass.blockID || blockID == AddonDefs.glassStained.blockID;
		boolean hasCenterPoint = FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x, y, z, Facing.oppositeSide[facing]);
		
		return isWallConnector || isSolid || isGlass || hasCenterPoint;
	}
	
	public static boolean isFence(int blockID, int metadata) {
		return blockID == Block.fence.blockID || ((Block.blocksList[blockID] instanceof AddonBlockSidingAndCornerAndDecorative && metadata == 14) && !(Block.blocksList[blockID] instanceof AddonBlockSidingAndCornerDecorativeWall) && ((AddonBlockSidingAndCornerAndDecorative) Block.blocksList[blockID]).isFenceFromMetadata(metadata));
	}
	
	public static boolean isFenceGate(int blockID) {
		return blockID == Block.fenceGate.blockID || blockID == AddonDefs.gateBirch.blockID || blockID == AddonDefs.gateBlood.blockID || blockID == AddonDefs.gateJungle.blockID || blockID == AddonDefs.gateSpruce.blockID;
	}
	
	public static boolean isWall(int blockID, int metadata) {
		return blockID == Block.cobblestoneWall.blockID || (Block.blocksList[blockID] instanceof AddonBlockSidingAndCornerDecorativeWall && metadata == 14);
	}
}

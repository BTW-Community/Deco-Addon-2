package net.minecraft.src;

import java.util.List;

public class AddonUtilsBlock {
	public static boolean canFenceConnect(IBlockAccess blockAccess, int x, int y, int z, int facing, Block thisBlock) {
		int blockID = blockAccess.getBlockId(x, y, z);
		int metadata = blockAccess.getBlockMetadata(x, y, z);
		Block block = Block.blocksList[blockID];
		
		boolean isFenceConnector = isFence(blockID, metadata) || (isFenceGate(blockID) && shouldGateConnectToFacing(blockID, metadata, facing));
		boolean isSolid = block != null && block.blockMaterial.isOpaque() && block.renderAsNormalBlock();
		boolean isGlass = block != null && blockID == Block.glass.blockID || blockID == AddonDefs.glassStained.blockID;
		boolean hasCenterPoint = FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(blockAccess, x, y, z, Facing.oppositeSide[facing]);
		
		return isFenceConnector || isSolid || isGlass  || hasCenterPoint;
	}
	
	public static boolean canWallConnect(IBlockAccess blockAccess, int x, int y, int z, int facing, Block thisBlock) {
		int blockID = blockAccess.getBlockId(x, y, z);
		int metadata = blockAccess.getBlockMetadata(x, y, z);
		Block block = Block.blocksList[blockID];
		
		boolean isWallConnector = block == Block.cobblestoneWall || isWall(blockID, metadata) || (isFenceGate(blockID) && shouldGateConnectToFacing(blockID, metadata, facing)) || isPane(blockID);
		boolean isSolid = block != null && block.blockMaterial.isOpaque() && block.renderAsNormalBlock();
		boolean isGlass = block != null && blockID == Block.glass.blockID || blockID == AddonDefs.glassStained.blockID;
		boolean hasCenterPoint = FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(blockAccess, x, y, z, Facing.oppositeSide[facing]);
		
		return isWallConnector || isSolid || isGlass  || hasCenterPoint;
	}
	
	public static boolean canPaneConnect(IBlockAccess blockAccess, int x, int y, int z, int facing, Block thisBlock) {
		int blockID = blockAccess.getBlockId(x, y, z);
		int metadata = blockAccess.getBlockMetadata(x, y, z);
		Block block = Block.blocksList[blockID];

		boolean isPaneConnector = isPane(blockID) || isWall(blockID, metadata);
		boolean isGlass = block != null && blockID == Block.glass.blockID || blockID == AddonDefs.glassStained.blockID;
		boolean isSolid = block != null && block.blockMaterial.isOpaque() && block.renderAsNormalBlock();
		boolean hasCenterPoint = FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(blockAccess, x, y, z, Facing.oppositeSide[facing]);
		
		return isPaneConnector || isSolid || isGlass || hasCenterPoint;
	}
	
	//This is really bad programming practice but it is the only way without modifying Block and each individual class with an exception
	public static boolean blockIsWallConnectionAboveException(IBlockAccess blockAccess, int x, int y, int z, Block blockAbove) {
		if (blockAbove instanceof BlockTrapDoor || 
				blockAbove instanceof FCBlockSlab ||
				blockAbove instanceof BlockHalfSlab ||
				blockAbove instanceof BlockFenceGate ||
				blockAbove instanceof AddonBlockLantern ||
				blockAbove instanceof FCBlockTorchBase ||
				blockAbove instanceof FCBlockLadderBase ||
				blockAbove instanceof BlockVine ||
				blockAbove instanceof AddonBlockSignWall ||
				blockAbove instanceof FCBlockStake ||
				blockAbove instanceof FCBlockAxle ||
				blockAbove instanceof FCBlockAestheticVegetation ||
				blockAbove instanceof AddonBlockChandelier ||
				blockAbove instanceof AddonBlockChair ||
				blockAbove instanceof FCBlockSpike ||
				blockAbove instanceof BlockSkull ||
				blockAbove instanceof FCBlockSidingAndCorner ||
				blockAbove instanceof FCBlockMoulding ||
				blockAbove instanceof FCBlockLogSpike ||
				blockAbove instanceof FCBlockLogDamaged)
			return true;
		else
			return false;
	}
	
	public static boolean getWallConnectionAboveException(IBlockAccess blockAccess, int x, int y, int z, Block blockAbove) {
		int meta = blockAccess.getBlockMetadata(x, y, z);
		
		if (blockAbove instanceof BlockTrapDoor ||
				blockAbove instanceof FCBlockLogDamaged) {
			return meta < 4;
		}
		else if (blockAbove instanceof FCBlockSlab) {
			return !((FCBlockSlab) blockAbove).GetIsUpsideDown(blockAccess, x, y, z);
		}
		else if (blockAbove instanceof BlockHalfSlab) {
			return !((BlockHalfSlab) blockAbove).GetIsUpsideDown(blockAccess, x, y, z);
		}
		else if (blockAbove instanceof FCBlockAxle ||
				blockAbove instanceof FCBlockAestheticVegetation ||
				blockAbove instanceof FCBlockSpike) {
			return meta == 0;
		}
		else if (blockAbove instanceof AddonBlockLantern ||
				blockAbove instanceof FCBlockStake ||
				blockAbove instanceof BlockSkull ||
				blockAbove instanceof FCBlockLogSpike) {
			return meta == 1;
		}
		else if (blockAbove instanceof FCBlockTorchBase) {
			return meta == 5;
		}
		else if (blockAbove instanceof FCBlockSidingAndCorner) {
			if (meta == 0 || meta == 5 || meta == 7 || meta == 13 || meta == 15)
				return false;
			else
				return true;
		}
		else if (blockAbove instanceof FCBlockMoulding) {
			if (meta == 8 || meta == 9 || meta == 10 || meta == 11)
				return false;
			else
				return true;
		}
		else if (blockAbove instanceof BlockFenceGate ||
				blockAbove instanceof FCBlockLadderBase ||
				blockAbove instanceof BlockVine ||
				blockAbove instanceof AddonBlockSignWall ||
				blockAbove instanceof AddonBlockChandelier ||
				blockAbove instanceof AddonBlockChair) {
			return false;
		}
		
		return false;
	}
	
	public static boolean isFence(int blockID, int metadata) {
		return blockID == Block.fence.blockID || blockID == Block.netherFence.blockID || ((Block.blocksList[blockID] instanceof AddonBlockSidingAndCornerAndDecorative && metadata == 14) && !(Block.blocksList[blockID] instanceof AddonBlockSidingAndCornerDecorativeWall) && ((AddonBlockSidingAndCornerAndDecorative) Block.blocksList[blockID]).isFenceFromMetadata(metadata));
	}
	
	public static boolean isFenceGate(int blockID) {
		return blockID == Block.fenceGate.blockID || blockID == AddonDefs.gateBirch.blockID || blockID == AddonDefs.gateBlood.blockID || blockID == AddonDefs.gateJungle.blockID || blockID == AddonDefs.gateSpruce.blockID || blockID == AddonDefs.gateCherry.blockID;
	}
	
	public static boolean isWall(int blockID, int metadata) {
		return blockID == Block.cobblestoneWall.blockID || (Block.blocksList[blockID] instanceof AddonBlockSidingAndCornerDecorativeWall && metadata == 14);
	}
	
	public static boolean isPane(int blockID) {
		return Block.blocksList[blockID] instanceof BlockPane;
	}
	
	public static boolean shouldGateConnectToFacing(int blockID, int metadata, int facing) {
		int dir = BlockDirectional.getDirection(metadata);
		
		if (facing == 2 || facing == 3) {
			return dir == 1 || dir == 3;
		}
		else if (facing == 4 || facing == 5) {
			return dir == 0 || dir == 2;
		}
		
		return false;
	}
	
	public static AxisAlignedBB getPaneBlockBounds(IBlockAccess blockAccess, int x, int y, int z, BlockPane pane) {
		FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z, 2);
        boolean north = canPaneConnect(blockAccess, blockPos.i, blockPos.j, blockPos.k, 2, pane);
		blockPos = new FCUtilsBlockPos(x, y, z, 3);
        boolean south = canPaneConnect(blockAccess, blockPos.i, blockPos.j, blockPos.k, 3, pane);
		blockPos = new FCUtilsBlockPos(x, y, z, 4);
        boolean east = canPaneConnect(blockAccess, blockPos.i, blockPos.j, blockPos.k, 4, pane);
		blockPos = new FCUtilsBlockPos(x, y, z, 5);
        boolean west = canPaneConnect(blockAccess, blockPos.i, blockPos.j, blockPos.k, 5, pane);
		
		double minX = .4375;
		double minZ = .4375;
		double maxX = .5625;
		double maxZ = .5625;

		if (!(east || west || north || south)) {
			minX = 0;
			minZ = 0;
			maxX = 1;
			maxZ = 1;
		}
		
		if (east)
			minX = 0;
		if (west)
			maxX = 1;
		if (north)
			minZ = 0;
		if (south)
			maxZ = 1;
		
		return AxisAlignedBB.getAABBPool().getAABB(minX, 0, minZ, maxX, 1, maxZ);
	}
	
	public static void addPaneCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List collisionList, Entity entity, BlockPane pane) {
		FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z, 2);
        boolean north = canPaneConnect(world, blockPos.i, blockPos.j, blockPos.k, 2, pane);
		blockPos = new FCUtilsBlockPos(x, y, z, 3);
        boolean south = canPaneConnect(world, blockPos.i, blockPos.j, blockPos.k, 3, pane);
		blockPos = new FCUtilsBlockPos(x, y, z, 4);
        boolean east = canPaneConnect(world, blockPos.i, blockPos.j, blockPos.k, 4, pane);
		blockPos = new FCUtilsBlockPos(x, y, z, 5);
        boolean west = canPaneConnect(world, blockPos.i, blockPos.j, blockPos.k, 5, pane);
		
		double height = 1;
		
		if (east)
		{
			AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.4325D, 0.5D, height, 0.5625D).offset((double) x, (double) y, (double) z).AddToListIfIntersects(aabb, collisionList);
		}

		if (west)
		{
			AxisAlignedBB.getAABBPool().getAABB(0.5D, 0.0D, 0.4325D, 1.0D, height, 0.5625D).offset((double) x, (double) y, (double) z).AddToListIfIntersects(aabb, collisionList);
		}

		if (north)
		{
			AxisAlignedBB.getAABBPool().getAABB(0.4325D, 0.0D, 0.0D, 0.5625D, height, 0.5D).offset((double) x, (double) y, (double) z).AddToListIfIntersects(aabb, collisionList);
		}

		if (south)
		{
			AxisAlignedBB.getAABBPool().getAABB(0.4325D, 0.0D, 0.5D, 0.5625D, height, 1.0D).offset((double) x, (double) y, (double) z).AddToListIfIntersects(aabb, collisionList);
		}
		
		if (!south && !north && !east && !west) {
			AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.3125D, 0.5D, height, 0.6875D).offset((double) x, (double) y, (double) z).AddToListIfIntersects(aabb, collisionList);
			AxisAlignedBB.getAABBPool().getAABB(0.5D, 0.0D, 0.3125D, 1.0D, height, 0.6875D).offset((double) x, (double) y, (double) z).AddToListIfIntersects(aabb, collisionList);
			AxisAlignedBB.getAABBPool().getAABB(0.3125D, 0.0D, 0.0D, 0.6875D, height, 0.5D).offset((double) x, (double) y, (double) z).AddToListIfIntersects(aabb, collisionList);
			AxisAlignedBB.getAABBPool().getAABB(0.3125D, 0.0D, 0.5D, 0.6875D, height, 1.0D).offset((double) x, (double) y, (double) z).AddToListIfIntersects(aabb, collisionList);
		}
	}
	
	public static int getDamagedLogFromMetadata(int meta) {
		switch (meta) {
		case 0:
		default:
			return FCBetterThanWolves.fcBlockLogDamaged.blockID;
		case 1:
			return AddonDefs.logDamagedSpruce.blockID;
		case 2:
			return AddonDefs.logDamagedBirch.blockID;
		case 3:
			return AddonDefs.logDamagedJungle.blockID;
		case 4:
			return AddonDefs.logDamagedCherry.blockID;
		}
	}
	
	public static int getLogSpikeFromBlockID(int id) {
		if (id == FCBetterThanWolves.fcBlockLogDamaged.blockID)
			return FCBetterThanWolves.fcBlockLogSpike.blockID;
		else if (id == AddonDefs.logDamagedSpruce.blockID)
			return AddonDefs.logSpikeSpruce.blockID;
		else if (id == AddonDefs.logDamagedBirch.blockID)
			return AddonDefs.logSpikeBirch.blockID;
		else if (id == AddonDefs.logDamagedJungle.blockID)
			return AddonDefs.logSpikeJungle.blockID;
		else if (id == AddonDefs.logDamagedBlood.blockID)
			return AddonDefs.logSpikeBlood.blockID;
		else
			return AddonDefs.logSpikeCherry.blockID;
	}
}

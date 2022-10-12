package deco.block.blocks;

import btw.block.blocks.ButtonBlock;
import btw.world.util.BlockPos;
import btw.world.util.WorldUtils;
import net.minecraft.src.*;

import java.util.Random;

public class DecoButtonBlock extends ButtonBlock {
	private static int[] metaForFacing = {0, 5, 4, 3, 2, 1};
	private Block owner;
	private int ownerMeta;
	
	public DecoButtonBlock(int blockID, boolean isWood, Block owner, int ownerMetadata) {
		super(blockID, isWood);
		
		if (isWood) {
			this.setAxesEffectiveOn();
			this.setBuoyant();
		}
		else {
			this.setPicksEffectiveOn();
		}
		
		this.owner = owner;
		this.ownerMeta = ownerMetadata;
	}
	
	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side) {
		BlockPos blockPos = new BlockPos(x, y, z, Facing.oppositeSide[side]);
		return WorldUtils.doesBlockHaveLargeCenterHardpointToFacing(world, blockPos.x, blockPos.y, blockPos.z, side);
	}
	
	@Override
	public int getFacing(int var1) {
		return metaForFacing[var1 & 7];
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		boolean canPlace = false;
		
		for (int facing = 0; facing < 6; facing++) {
			BlockPos blockPos = new BlockPos(x, y, z, facing);
			
			if (WorldUtils.doesBlockHaveLargeCenterHardpointToFacing(world, blockPos.x, blockPos.y, blockPos.z, Facing.oppositeSide[facing])) {
				canPlace = true;
			}
		}
		
		return canPlace;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		if (this.canPlaceBlockAt(world, x, y, z)) {
			boolean onValidBlock = false;
			
			int metadata = world.getBlockMetadata(x, y, z);
			
			for (int facing = 0; facing < 6; facing++) {
				BlockPos blockPos = new BlockPos(x, y, z, facing);
				if (WorldUtils.doesBlockHaveLargeCenterHardpointToFacing(world, blockPos.x, blockPos.y, blockPos.z, Facing.oppositeSide[facing]) &&
						(metadata & 7) == metaForFacing[Facing.oppositeSide[facing]])
				{
					onValidBlock = true;
				}
			}
			
			if (!onValidBlock) {
				this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
				world.setBlockToAir(x, y, z);
			}
		}
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		int newMetadata = world.getBlockMetadata(x, y, z);
		int powerState = newMetadata & 8;
		newMetadata &= 7;
		
		if (side == 0 && WorldUtils.doesBlockHaveLargeCenterHardpointToFacing(world, x, y + 1, z, 0)) {
			newMetadata = 0;
		}
		else if (side == 1 && WorldUtils.doesBlockHaveLargeCenterHardpointToFacing(world, x, y - 1, z, 1)) {
			newMetadata = 5;
		}
		else if (side == 2 && WorldUtils.doesBlockHaveLargeCenterHardpointToFacing(world, x, y, z + 1, 2)) {
			newMetadata = 4;
		}
		else if (side == 3 && WorldUtils.doesBlockHaveLargeCenterHardpointToFacing(world, x, y, z - 1, 3)) {
			newMetadata = 3;
		}
		else if (side == 4 && WorldUtils.doesBlockHaveLargeCenterHardpointToFacing(world, x + 1, y, z, 4)) {
			newMetadata = 2;
		}
		else if (side == 5 && WorldUtils.doesBlockHaveLargeCenterHardpointToFacing(world, x - 1, y, z, 5)) {
			newMetadata = 1;
		}
		
		return newMetadata + powerState;
	}
	
	@Override
	public AxisAlignedBB getBlockBoundsFromPoolBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
		int meta = blockAccess.getBlockMetadata(x, y, z);
		
		double minW = .3125;
		double maxW = .6875;
		double minH = .375;
		double maxH = .625;
		double maxL = .125;
		
		//If the button is on make it smaller
		if ((meta & 8) > 1) {
			maxL = .0625;
		}
		
		switch (meta & 7) {
			default:
			case 0:
				return new AxisAlignedBB(minW, 1 - maxL, minW, maxW, 1, maxW);
			case 1:
				return new AxisAlignedBB(0, minH, minW, maxL, maxH, maxW);
			case 2:
				return new AxisAlignedBB(1 - maxL, minH, minW, 1, maxH, maxW);
			case 3:
				return new AxisAlignedBB(minW, minH, 0, maxW, maxH, maxL);
			case 4:
				return new AxisAlignedBB(minW, minH, 1 - maxL, maxW, maxH, 1);
			case 5:
				return new AxisAlignedBB(minW, 0, minW, maxW, maxL, maxW);
		}
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		int metadata = world.getBlockMetadata(x, y, z);
		int facing = metadata & 7;
		int powerState = 8 - (metadata & 8);
		
		if (powerState == 0) {
			return true;
		}
		else {
			world.setBlockMetadataWithNotify(x, y, z, facing + powerState, 3);
			world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			world.playSoundEffect((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.click", 0.3F, 0.6F);
			this.sendRedstoneUpdates(world, x, y, z, facing);
			world.scheduleBlockUpdate(x, y, z, this.blockID, this.tickRate(world));
			return true;
		}
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote) {
			int metadata = world.getBlockMetadata(x, y, z);
			
			if ((metadata & 8) != 0) {
				world.setBlockMetadataWithNotify(x, y, z, metadata & 7, 3);
				int facing = metadata & 7;
				this.sendRedstoneUpdates(world, x, y, z, facing);
				world.playSoundEffect((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "random.click", 0.3F, 0.5F);
				world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			}
		}
		
		super.updateTick(world, x, y, z, rand);
	}
	
	@Override
	public int isProvidingStrongPower(IBlockAccess blockAccess, int x, int y, int z, int sideReversed) {
		int metadata = blockAccess.getBlockMetadata(x, y, z);
		
		if ((metadata & 8) == 0) {
			return 0;
		}
		else {
			BlockPos blockPos = new BlockPos(x, y, z, Facing.oppositeSide[sideReversed]);
			return blockAccess.isBlockNormalCube(blockPos.x, blockPos.y, blockPos.z) && (metadata & 7) == metaForFacing[sideReversed] ? 15 : 0;
		}
	}
	
	//------------- Class Specific Methods ------------//
	
	//Provides update when button is activated
	private void sendRedstoneUpdates(World world, int x, int y, int z, int meta) {
		world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
		
		if (meta == 1) {
			world.notifyBlocksOfNeighborChange(x - 1, y, z, this.blockID);
		}
		else if (meta == 2) {
			world.notifyBlocksOfNeighborChange(x + 1, y, z, this.blockID);
		}
		else if (meta == 3) {
			world.notifyBlocksOfNeighborChange(x, y, z - 1, this.blockID);
		}
		else if (meta == 4) {
			world.notifyBlocksOfNeighborChange(x, y, z + 1, this.blockID);
		}
		else if (meta == 5) {
			world.notifyBlocksOfNeighborChange(x, y - 1, z, this.blockID);
		}
		else {
			world.notifyBlocksOfNeighborChange(x, y + 1, z, this.blockID);
		}
	}
	
	//----------- Client Side Functionality -----------//
	
	@Override
	public Icon getIcon(int side, int meta) {
		return this.owner.getIcon(side, ownerMeta);
	}
}

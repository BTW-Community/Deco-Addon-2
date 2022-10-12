package deco.block.blocks;

import btw.world.util.BlockPos;
import btw.world.util.WorldUtils;
import deco.client.util.DecoRenderUtils;
import deco.item.DecoItems;
import net.minecraft.src.*;

import java.util.Random;

public class AmethystShardBlock extends Block {
	public AmethystShardBlock(int id) {
		super(id, Material.glass);
		this.setHardness(0.3F);
		this.setStepSound(Block.soundGlassFootstep);
		this.setUnlocalizedName("decoBlockAmethystShard");
		this.setPicksEffectiveOn();
		
		this.initBlockBounds(new AxisAlignedBB(0.25, 0, 0.25, 0.75, 0.875, 0.75));
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		super.onNeighborBlockChange(world, x, y, z, neighborID);
		
		if (!this.canBlockStay(world, x, y, z)) {
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return this.canAmethystStay(world, x, y, z, this.getFacing(world, x, y, z));
	}
	
	@Override
	public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int facing) {
		return this.canAmethystStay(world, x, y, z, facing);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int idDropped(int metadata, Random rand, int fortuneModifier) {
		return DecoItems.amethystShard.itemID;
	}
	
	@Override
	public boolean getPreventsFluidFlow(World world, int x, int y, int z, Block fluidBlock) {
		return false;
	}
	
	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
		this.setFacing(world, x, y, z, side);
		return side;
	}
	
	@Override
	public int getFacing(int metadata) {
		return metadata;
	}
	
	@Override
	public int setFacing(int metadata, int facing) {
		return facing;
	}
	
	// ------ Class specific methods ------ //
	
	public boolean canAmethystStay(World world, int x, int y, int z, int facing) {
		BlockPos pos = new BlockPos(x, y, z, Facing.oppositeSide[facing]);
		return WorldUtils.doesBlockHaveLargeCenterHardpointToFacing(world, pos.x, pos.y, pos.z, facing, true);
	}
	
	// ------ Client only methods ------ //
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
		AxisAlignedBB boxPool = this.getBlockBoundsFromPoolBasedOnState(world, x, y, z).offset((double) x, (double) y, (double) z);
		AxisAlignedBB box = boxPool.makeTemporaryCopy();
		
		box.minX -= x;
		box.maxX -= x;
		box.minY -= y;
		box.maxY -= y;
		box.minZ -= z;
		box.maxZ -= z;
		
		double tempX;
		double tempZ;
		
		double offset = 1 - box.maxY;
		
		switch (this.getFacing(world.getBlockMetadata(x, y, z))) {
			default:
			case 0:
				box.minY += offset;
				box.maxY += offset;
			case 1:
				break;
			case 2:
				box.minY += offset;
				box.maxY += offset;
			case 3:
				tempZ = box.minZ;
				box.minZ = box.minY;
				box.minY = tempZ;
				
				tempZ = box.maxZ;
				box.maxZ = box.maxY;
				box.maxY = tempZ;
				break;
			case 4:
				box.minY += offset;
				box.maxY += offset;
			case 5:
				tempX = box.minX;
				box.minX = box.minY;
				box.minY = tempX;
				
				tempX = box.maxX;
				box.maxX = box.maxY;
				box.maxY = tempX;
				break;
		}
		
		box.minX += x;
		box.maxX += x;
		box.minY += y;
		box.maxY += y;
		box.minZ += z;
		box.maxZ += z;
		
		return box;
	}
	
	@Override
	public boolean renderBlock(RenderBlocks render, int x, int y, int z) {
		DecoRenderUtils.renderCrossedSquares(render, this, x, y, z, this.getFacing(render.blockAccess.getBlockMetadata(x, y, z)));
		return true;
	}
	
	@Override
	public int idPicked(World world, int x, int y, int z) {
		return DecoItems.amethystShard.itemID;
	}
}

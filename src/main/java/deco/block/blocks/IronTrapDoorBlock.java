package deco.block.blocks;

import btw.block.blocks.TrapDoorBlock;
import net.minecraft.src.*;

public class IronTrapDoorBlock extends TrapDoorBlock {
	public IronTrapDoorBlock(int blockID) {
		super(blockID);
		this.blockMaterial = Material.iron;
		this.setStepSound(soundMetalFootstep);
		this.setUnlocalizedName("decoBlockTrapdoorIron");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		return false;
	}
	
	@Override
	public void onPoweredBlockChange(World world, int x, int y, int z, boolean isPowered) {
		int metadata = world.getBlockMetadata(x, y, z);
		boolean poweredState = (metadata & 4) > 0;
		
		if (poweredState != isPowered) {
			world.setBlockMetadataWithNotify(x, y, z, metadata ^ 4, 2);
			if (!world.isRemote) {
				world.playAuxSFX(1003, x, y, z, 0);
			}
		}
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		if (!world.isRemote) {
			boolean isPowered = world.isBlockIndirectlyGettingPowered(x, y, z);
			
			if (isPowered || neighborID > 0 && Block.blocksList[neighborID].canProvidePower()) {
				this.onPoweredBlockChange(world, x, y, z, isPowered);
			}
		}
	}
	
	//----------- Client Side Functionality -----------//
	
	@Override
	public Icon getIcon(int side, int metadata) {
		return blockIcon;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		blockIcon = register.registerIcon(this.getUnlocalizedName2());
	}
}

package deco.entity;

import btw.entity.FallingBlockEntity;
import net.minecraft.src.*;

public class FallingConcreteEntity extends FallingBlockEntity {
	public FallingConcreteEntity(World world, double x, double y, double z, int blockID, int metadata) {
		super(world, x, y, z, blockID, metadata);
	}
	
	@Override
	public void onUpdate() {
		this.handleWaterMovement();
		
		if (!this.worldObj.isRemote && this.inWater) {
			int x = MathHelper.floor_double(this.posX);
			int y = MathHelper.floor_double(this.posY);
			int z = MathHelper.floor_double(this.posZ);
			
			if (this.attemptToReplaceBlockAtPosition(x, y, z)) {
				Block.blocksList[this.blockID].onFinishFalling(this.worldObj, x, y, z, this.metadata);
				this.setDead();
				return;
			}
		}
		
		super.onUpdate();
	}
	
	private boolean attemptToReplaceBlockAtPosition(int x, int y, int z) {
		if (!this.hasBlockBrokenOnLand && this.canReplaceBlockAtPosition(x, y, z) &&
				(!Block.blocksList[this.blockID].canFallIntoBlockAtPos(this.worldObj, x, y - 1, z) ||
						(Block.blocksList[this.worldObj.getBlockId(x, y, z)] != null &&
								Block.blocksList[this.worldObj.getBlockId(x, y, z)].blockMaterial == Material.water)))
		{
			Block block = Block.blocksList[this.worldObj.getBlockId(x, y, z)];
			
			if (block != null) {
				block.onCrushedByFallingEntity(this.worldObj, x, y, z, this);
			}
			
			this.worldObj.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, "random.fizz",
					0.3F, 2.6F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.8F);
			
			return this.worldObj.setBlock(x, y, z, this.blockID, this.metadata, 3);
		}
		else {
			return false;
		}
	}
	
	private boolean canReplaceBlockAtPosition(int x, int y, int z) {
		if (this.worldObj.canPlaceEntityOnSide(this.blockID, x, y, z, true, 1, null, null) ||
				Block.blocksList[this.worldObj.getBlockId(x, y, z)].blockMaterial == Material.water)
		{
			return true;
		}
		else {
			Block block = Block.blocksList[this.worldObj.getBlockId(x, y, z)];
			return block != null && block.canBeCrushedByFallingEntity(this.worldObj, x, y, z, this);
		}
	}
}

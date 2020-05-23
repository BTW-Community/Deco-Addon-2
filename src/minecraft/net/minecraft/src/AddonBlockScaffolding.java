package net.minecraft.src;

public class AddonBlockScaffolding extends Block {
	public AddonBlockScaffolding(int id) {
		super(id, FCBetterThanWolves.fcMaterialMiscellaneous);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
	@Override
    public boolean IsNormalCube(IBlockAccess blockAccess, int x, int y, int z)
    {
        return false;
    }

    /**
     * Returns whether this block is collideable based on the arguments passed in Args: blockMetaData, unknownFlag
     */
    public boolean canCollideCheck(int meta, boolean var2)
    {
        return true;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    	if (!(entity instanceof EntityLiving) || (!((EntityLiving) entity).isJumping)) {
        	double bottomBound = entity.boundingBox.minY;
        	bottomBound -= (int)bottomBound;
        	
        	if (bottomBound > .8) {
        		entity.moveEntity(0, 1 - bottomBound, 0);
        	}
        	
    		entity.motionY = 0.075;
    		entity.onGround = true;
    	}
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }
}
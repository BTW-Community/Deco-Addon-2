package net.minecraft.src;

import java.util.List;

public class AddonBlockPane extends FCBlockPane {

	protected AddonBlockPane(int id, String sideTexture, String topTexture, Material material, boolean canDropItself) {
		super(id, sideTexture, topTexture, material, canDropItself);
	}
    
    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
		return AddonUtilsBlock.getPaneBlockBounds(blockAccess, x, y, z, this);
	}
    
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List collisionList, Entity entity)
    {
        AddonUtilsBlock.addPaneCollisionBoxesToList(world, x, y, z, aabb, collisionList, entity, this);
    }
}
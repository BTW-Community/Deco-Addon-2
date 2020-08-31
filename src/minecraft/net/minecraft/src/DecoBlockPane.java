package net.minecraft.src;

import java.util.List;

public class DecoBlockPane extends FCBlockPane {

	protected DecoBlockPane(int id, String sideTexture, String topTexture, Material material, boolean canDropItself) {
		super(id, sideTexture, topTexture, material, canDropItself);
	}
    
    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
		return DecoUtilsBlock.getPaneBlockBounds(blockAccess, x, y, z, this);
	}
    
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List collisionList, Entity entity)
    {
        DecoUtilsBlock.addPaneCollisionBoxesToList(world, x, y, z, aabb, collisionList, entity, this);
    }

	//CLIENT ONLY
    public boolean RenderBlock(RenderBlocks render, int x, int y, int z) {
    	return DecoClientUtilsRender.renderPane(render, x, y, z, this);
    }
}
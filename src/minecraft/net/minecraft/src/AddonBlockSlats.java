package net.minecraft.src;

import java.util.List;

public class AddonBlockSlats extends FCBlockSlats {
    protected AddonBlockSlats(int var1)
    {
        super(var1);
    }
    
    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
		return AddonUtilsBlock.getPaneBlockBounds(blockAccess, x, y, z, this);
	}
    
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List collisionList, Entity entity)
    {
        AddonUtilsBlock.addPaneCollisionBoxesToList(world, x, y, z, aabb, collisionList, entity, this);
    }
    
    //CLIENT ONLY
    public boolean RenderBlock(RenderBlocks render, int x, int y, int z) {
    	return AddonClientUtilsRender.renderPane(render, x, y, z, this);
    }
}

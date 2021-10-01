package net.minecraft.src;

import java.util.List;

public class DecoBlockGrate extends FCBlockGrate {
    protected DecoBlockGrate(int var1)
    {
        super(var1);
    }
    
    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
		return DecoUtilsBlock.getPaneBlockBounds(blockAccess, x, y, z, this);
	}
    
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List collisionList, Entity entity)
    {
        DecoUtilsBlock.addPaneCollisionBoxesToList(world, x, y, z, aabb, collisionList, entity, this);
    }
}

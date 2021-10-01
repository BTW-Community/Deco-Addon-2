package net.minecraft.src;

import java.util.List;

public class DecoBlockWickerPane extends FCBlockWickerPane {
    protected DecoBlockWickerPane(int var1)
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

    public boolean CanTransformItemIfFilter(ItemStack var1)
    {
        try {
			return var1.itemID == Block.gravel.blockID || (var1.itemID == FCBetterThanWolves.fcItemWheat.itemID && Class.forName("AutoPlusMod") != null);
		} catch (ClassNotFoundException e) {
			return false;
		}
    }
    
    //CLIENT ONLY
    public boolean RenderBlock(RenderBlocks render, int x, int y, int z) {
    	return DecoClientUtilsRender.renderPane(render, x, y, z, this);
    }
}

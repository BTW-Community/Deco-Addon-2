package net.minecraft.src;

public class AddonItemCocoaBeans extends FCItemCocoaBeans {
	public AddonItemCocoaBeans(int id) {
		super(id);
	}

    protected boolean AttemptPlaceOn(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        int var9 = world.getBlockId(x, y, z);
        int var10 = world.getBlockMetadata(x, y, z);

        if (side >= 2 && (var9 == Block.wood.blockID || var9 == AddonDefs.barkLog.blockID || var9 == AddonDefs.strippedLog.blockID || var9 == AddonDefs.barkLogStripped.blockID) && BlockLog.limitToValidMetadata(var10) == 3)
        {
            FCUtilsBlockPos var11 = new FCUtilsBlockPos(x, y, z, side);

            if (world.isAirBlock(var11.i, var11.j, var11.k))
            {
                int var12 = Block.cocoaPlant.blockID;
                int var13 = Block.blocksList[var12].onBlockPlaced(world, var11.i, var11.j, var11.k, side, hitX, hitY, hitZ, 0);
                world.setBlockAndMetadataWithNotify(var11.i, var11.j, var11.k, var12, var13);
				world.playSound(x, y, z, "dig.wood", 1.0F, .75F);
                return true;
            }
        }

        return false;
    }
}
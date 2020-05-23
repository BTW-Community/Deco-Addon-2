package net.minecraft.src;

public class AddonItemCocoaBeans extends FCItemCocoaBeans {
	public AddonItemCocoaBeans(int id) {
		super(id);
	}

    protected boolean AttemptPlaceOn(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8)
    {
        int var9 = var1.getBlockId(var2, var3, var4);
        int var10 = var1.getBlockMetadata(var2, var3, var4);

        if (var5 >= 2 && (var9 == Block.wood.blockID || var9 == AddonDefs.barkLog.blockID || var9 == AddonDefs.strippedLog.blockID || var9 == AddonDefs.barkLogStripped.blockID) && BlockLog.limitToValidMetadata(var10) == 3)
        {
            FCUtilsBlockPos var11 = new FCUtilsBlockPos(var2, var3, var4, var5);

            if (var1.isAirBlock(var11.i, var11.j, var11.k))
            {
                int var12 = Block.cocoaPlant.blockID;
                int var13 = Block.blocksList[var12].onBlockPlaced(var1, var11.i, var11.j, var11.k, var5, var6, var7, var8, 0);
                var1.setBlockAndMetadataWithNotify(var11.i, var11.j, var11.k, var12, var13);
                return true;
            }
        }

        return false;
    }
}
package net.minecraft.src;

public class DecoBlockDoorIron extends FCBlockDoor {
	public DecoBlockDoorIron(int id) {
		super(id, Material.iron);
	}

    /**
     * A function to open a door.
     */
    public void onPoweredBlockChange(World world, int x, int y, int z, boolean var5)
    {
        int var6 = this.getFullMetadata(world, x, y, z);
        boolean var7 = (var6 & 4) != 0;

        if (var7 != var5)
        {
            int var8 = var6 & 7;
            var8 ^= 4;
            
            if (! world.isRemote) {
            	if (this.isDoorOpen(world, x, y, z))
            		world.playAuxSFX(DecoManager.decoDoorIronCloseAuxFXID, x, y, z, 0);
            	else
            		world.playAuxSFX(DecoManager.decoDoorIronOpenAuxFXID, x, y, z, 0);
            }
            
            if ((var6 & 8) == 0)
            {
                world.SetBlockMetadataWithNotify(x, y, z, var8, 3);
                world.notifyBlockChange(x, y + 1, z, this.blockID);
                world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
            }
            else
            {
                world.SetBlockMetadataWithNotify(x, y - 1, z, var8, 3);
                world.notifyBlockChange(x, y, z, this.blockID);
                world.markBlockRangeForRenderUpdate(x, y - 1, z, x, y, z);
            }
        }
    }
}
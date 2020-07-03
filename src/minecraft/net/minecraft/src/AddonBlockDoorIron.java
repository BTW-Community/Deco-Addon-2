package net.minecraft.src;

public class AddonBlockDoorIron extends FCBlockDoor {
	public AddonBlockDoorIron(int id) {
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
            
            if (world.rand.nextInt(2) == 0)
                AddonUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.doorIronClose", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_close", 1, world.rand.nextFloat() * 0.1F + 0.9F);
            else
                AddonUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.doorIronOpen", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_open", 1, world.rand.nextFloat() * 0.1F + 0.9F);
        }
    }
}
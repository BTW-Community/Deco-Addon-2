package net.minecraft.src;

public class AddonBlockTerracottaGlazed extends Block {

	private String nameTag;
	private int index;

	protected AddonBlockTerracottaGlazed(int id, int index, String tag, String name, Material material) {
		super(id, material);
		this.nameTag = tag;
		this.setUnlocalizedName(tag);
	}

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack itemStack)
    {
        int var7 = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var7 == 0)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 2);
        }

        if (var7 == 1)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 1);
        }

        if (var7 == 2)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 3);
        }

        if (var7 == 3)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 0);
        }
    }
}

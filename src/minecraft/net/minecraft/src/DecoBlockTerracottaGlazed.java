package net.minecraft.src;

public class DecoBlockTerracottaGlazed extends Block {
	private int index;
	
	protected DecoBlockTerracottaGlazed(int id, int index) {
		super(id, Material.rock);
		this.index = index;
	}

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack itemStack)
    {
        int var7 = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (var7 == 0)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (var7 == 1)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 1, 2);
        }

        if (var7 == 2)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (var7 == 3)
        {
        	world.setBlockMetadataWithNotify(x, y, z, 0, 2);
        }
    }
	
	//CLIENT ONLY
	private Icon icons[] = new Icon[4];
	
	@Override
	public Icon getIcon(int side, int metadata){
		switch (side) {
		//Bottom
		case 0:
			switch (metadata) {
			case 0:
				return icons[0];
			case 1:
				return icons[1];
			case 2:
				return icons[2];
			case 3:
				return icons[3];
			}
		//Top
		case 1:
			switch (metadata) {
			case 0:
				return icons[1];
			case 1:
				return icons[0];
			case 2:
				return icons[3];
			case 3:
				return icons[2];
			}
		//East
		case 2:
			switch (metadata) {
			case 0:
				return icons[3];
			case 1:
				return icons[2];
			case 2:
				return icons[1];
			case 3:
				return icons[0];
			}
		//West
		case 3:
			switch (metadata) {
			case 0:
				return icons[2];
			case 1:
				return icons[3];
			case 2:
				return icons[0];
			case 3:
				return icons[1];
			}
		//North
		case 4:
			switch (metadata) {
			case 0:
				return icons[1];
			case 1:
				return icons[0];
			case 2:
				return icons[2];
			case 3:
				return icons[3];
			}
		//South
		case 5:
			switch (metadata) {
			case 0:
				return icons[0];
			case 1:
				return icons[1];
			case 2:
				return icons[3];
			case 3:
				return icons[2];
			}
		default:
			return icons[0];
		}
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		icons[0] = iconRegister.registerIcon("decoBlockGlazedTerracotta_" + DecoUtilsMisc.colorOrder[this.index] + "_0");
		icons[1] = iconRegister.registerIcon("decoBlockGlazedTerracotta_" + DecoUtilsMisc.colorOrder[this.index] + "_1");
		icons[2] = iconRegister.registerIcon("decoBlockGlazedTerracotta_" + DecoUtilsMisc.colorOrder[this.index] + "_2");
		icons[3] = iconRegister.registerIcon("decoBlockGlazedTerracotta_" + DecoUtilsMisc.colorOrder[this.index] + "_3");
	}
}

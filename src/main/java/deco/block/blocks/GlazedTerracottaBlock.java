package deco.block.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class GlazedTerracottaBlock extends TerracottaBlock {
	private String baseTexture;
	
	public GlazedTerracottaBlock(int blockID, String name, String baseTexture) {
		super(blockID, name);
		this.baseTexture = baseTexture;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack itemStack) {
		int yaw = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		
		switch (yaw) {
			case 0:
				world.setBlockMetadataWithNotify(x, y, z, 2, 2);
				break;
			case 1:
				world.setBlockMetadataWithNotify(x, y, z, 1, 2);
				break;
			case 2:
				world.setBlockMetadataWithNotify(x, y, z, 3, 2);
				break;
			case 3:
				world.setBlockMetadataWithNotify(x, y, z, 0, 2);
				break;
		}
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon icons[] = new Icon[4];
	
	@Environment(EnvType.CLIENT)
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
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		icons[0] = iconRegister.registerIcon(this.baseTexture + "_0");
		icons[1] = iconRegister.registerIcon(this.baseTexture + "_1");
		icons[2] = iconRegister.registerIcon(this.baseTexture + "_2");
		icons[3] = iconRegister.registerIcon(this.baseTexture + "_3");
	}
}

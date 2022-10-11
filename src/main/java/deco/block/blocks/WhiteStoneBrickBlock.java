package deco.block.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class WhiteStoneBrickBlock extends Block {
	public static final int TYPE_DEFAULT = 0;
	public static final int TYPE_MOSSY = 1;
	public static final int TYPE_CRACKED = 2;
	public static final int TYPE_CHISELED = 3;
	
	public WhiteStoneBrickBlock(int blockID) {
		super(blockID, Material.rock);
		this.setPicksEffectiveOn();
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1.5F);
		this.setResistance(10F);
		this.setUnlocalizedName("decoBlockWhiteBricks");
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (metadata == TYPE_DEFAULT && !world.getBlockMaterial(x, y - 1, z).blocksMovement()) {
			int blockAbove = world.getBlockId(x, y + 1, z);
			
			if (blockAbove != Block.waterMoving.blockID && blockAbove != Block.waterStill.blockID) {
				if ((blockAbove == Block.lavaMoving.blockID || blockAbove == Block.lavaStill.blockID) && rand.nextInt(15) == 0) {
					world.setBlockMetadataWithNotify(x, y, z, TYPE_CRACKED);
					world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
				}
			}
			else if (rand.nextInt(15) == 0) {
				world.setBlockMetadataWithNotify(x, y, z, TYPE_MOSSY);
				world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			}
		}
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] icons;
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		return icons[meta];
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		icons = new Icon[4];
		
		icons[0] = register.registerIcon("decoBlockWhiteBricks");
		icons[1] = register.registerIcon("decoBlockWhiteBricksMossy");
		icons[2] = register.registerIcon("decoBlockWhiteBricksCracked");
		icons[3] = register.registerIcon("decoBlockWhiteBricksChiseled");
	}
}

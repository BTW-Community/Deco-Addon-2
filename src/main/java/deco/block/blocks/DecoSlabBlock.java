package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.AestheticOpaqueBlock;
import btw.block.blocks.SlabBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;

public class DecoSlabBlock extends SlabBlock implements SlabInterface {
	public final Block[] blockTypes;
	public final int[] typeMetas;
	
	public DecoSlabBlock(int id, Material material, Block[] blocks, int[] metas) {
		super(id, material);
		this.setCreativeTab(CreativeTabs.tabBlock);
		blockTypes = blocks;
		typeMetas = metas;
	}
	
	@Override
	protected ItemStack createStackedBlock(int par1) {
		return new ItemStack(this.blockID, 2, par1 & 7);
	}
	
	@Override
	public boolean getIsUpsideDown(IBlockAccess blockAccess, int x, int y, int z) {
		return this.getIsUpsideDown(blockAccess.getBlockMetadata(x, y, z));
	}
	
	@Override
	public boolean getIsUpsideDown(int metadata) {
		return (metadata & 8) > 0;
	}
	
	@Override
	public void setIsUpsideDown(World world, int x, int y, int z, boolean isUpsideDown) {
		int metadata = world.getBlockMetadata(x, y, z);
		world.setBlockMetadataWithNotify(x, y, z, this.setIsUpsideDown(metadata, isUpsideDown));
	}
	
	@Override
	public int setIsUpsideDown(int metadata, boolean isUpsideDown) {
		if (isUpsideDown) {
			metadata |= 8;
		}
		else {
			metadata &= 7;
		}
		
		return metadata;
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata & 7;
	}
	
	@Override
	public int getCombinedBlockID(int metadata) {
		return blockTypes[metadata & 7].blockID;
	}
	
	@Override
	public int getCombinedMetadata(int metadata) {
		return typeMetas[metadata & 7];
	}
	
	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z) & 7;
	}
	
	@Override
	public int getNumTypes() {
		return this.blockTypes.length;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon whiteStoneIcon;
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		int baseMeta = meta & 7;
		
		//I don't know why the texture wasn't working on white stone slabs but this fixes it
		if (blockTypes[baseMeta].blockID == BTWBlocks.aestheticOpaque.blockID && typeMetas[baseMeta] == AestheticOpaqueBlock.SUBTYPE_WHITE_STONE) {
			return this.whiteStoneIcon;
		}
		
		return blockTypes[baseMeta].getIcon(side, typeMetas[baseMeta]);
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		this.blockIcon = register.registerIcon("stoneslab_top");
		this.whiteStoneIcon = register.registerIcon("fcBlockWhiteStone");
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int var4 = 0; var4 <= blockTypes.length; ++var4) {
			if (var4 != 2) {
				list.add(new ItemStack(blockID, 1, var4));
			}
		}
	}
}
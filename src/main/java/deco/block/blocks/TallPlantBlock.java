package deco.block.blocks;

import btw.block.blocks.FlowerBlock;
import btw.item.util.ItemUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TallPlantBlock extends FlowerBlock {
	private String baseTexture;
	private String[] names;
	
	public TallPlantBlock(int blockID, String baseTexture, String[] names) {
		super(blockID);
		this.setUnlocalizedName(baseTexture);
		this.baseTexture = baseTexture;
		this.names = names;
	}
	
	@Override
	public int idDropped(int metadata, Random rand, int fortuneModifier) {
		if (isTopBlock(metadata)) {
			return this.blockID;
		}
		else {
			return 0;
		}
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata & 7;
	}
	
	@Override
	public AxisAlignedBB getBlockBoundsFromPoolBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
		int metadata = blockAccess.getBlockMetadata(x, y, z);
		float height;
		
		if (this.isTopBlock(metadata)) {
			height = this.getHeight();
		}
		else {
			height = 1;
		}
		
		return AxisAlignedBB.getBoundingBox(0.5F - this.getHalfWidth(), 0, 0.5F - this.getHalfWidth(),
				0.5F + this.getHalfWidth(), height, 0.5F + this.getHalfWidth());
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving player, ItemStack itemStack) {
		if (world.getBlockId(x, y + 1, z) == 0) {
			world.setBlockAndMetadataWithNotify(x, y + 1, z, this.blockID, this.setTopBlock(world.getBlockMetadata(x, y, z), true));
		}
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourID) {
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (isTopBlock(metadata)) {
			if (world.getBlockId(x, y - 1, z) != this.blockID) {
				world.setBlockToAir(x, y, z);
				int idDropped = this.idDropped(metadata, world.rand, 1);
				
				if (idDropped != 0) {
					ItemUtils.dropSingleItemAsIfBlockHarvested(world, x, y, z, idDropped, this.damageDropped(metadata));
				}
			}
		}
		else {
			if (world.getBlockId(x, y + 1, z) != this.blockID) {
				world.setBlockToAir(x, y, z);
			}
		}
		
		super.onNeighborBlockChange(world, x, y, z, neighbourID);
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return super.canPlaceBlockAt(world, x, y, z) && world.getBlockId(x, y + 1, z) == 0;
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (isTopBlock(metadata)) {
			return world.getBlockId(x, y - 1, z) == this.blockID;
		}
		else {
			return super.canBlockStay(world, x, y, z) && world.getBlockId(x, y + 1, z) == this.blockID;
		}
	}
	
	@Override
	protected boolean canGrowOnBlock(World world, int x, int y, int z) {
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (isTopBlock(metadata)) {
			return world.getBlockId(x, y - 1, z) == this.blockID;
		}
		else {
			return super.canGrowOnBlock(world, x, y, z);
		}
	}
	
	//------------- Class Specific Methods ------------//
	
	public String[] getNames() {
		return names;
	}
	
	public ArrayList<Integer> getSpawnableList() {
		ArrayList<Integer> spawnableList = new ArrayList<>();
		
		for (int i = 0; i < this.names.length; i++) {
			spawnableList.add(i);
		}
		
		return spawnableList;
	}
	
	public float getHalfWidth() {
		return 0.2F;
	}
	
	public float getHeight() {
		return 0.6F;
	}
	
	public boolean isTopBlock(IBlockAccess blockAccess, int x, int y, int z) {
		return isTopBlock(blockAccess.getBlockMetadata(x, y, z));
	}
	
	public boolean isTopBlock(int metadata) {
		return (metadata & 8) != 0;
	}
	
	public void setTopBlock(World world, int x, int y, int z, boolean isTop) {
		int metadata = world.getBlockMetadata(x, y, z);
		world.setBlockMetadata(x, y, z, this.setTopBlock(metadata, isTop));
	}
	
	public int setTopBlock(int metadata, boolean isTop) {
		if (isTop) {
			return metadata | 8;
		}
		else {
			return metadata & 7;
		}
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] upperIcons;
	@Environment(EnvType.CLIENT)
	private Icon[] lowerIcons;
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		return upperIcons[metadata & 7];
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side) {
		int metadata = blockAccess.getBlockMetadata(x, y, z);
		
		if (isTopBlock(metadata)) {
			return upperIcons[metadata & 7];
		}
		else {
			return lowerIcons[metadata & 7];
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		upperIcons = new Icon[names.length];
		lowerIcons = new Icon[names.length];
		
		for (int i = 0; i < names.length; i++) {
			upperIcons[i] = register.registerIcon(baseTexture + "_" + names[i] + "_upper");
			lowerIcons[i] = register.registerIcon(baseTexture + "_" + names[i] + "_lower");
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < names.length; i++) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}
}

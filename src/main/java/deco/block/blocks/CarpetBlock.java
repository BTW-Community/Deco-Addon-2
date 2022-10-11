package deco.block.blocks;

import btw.block.util.Flammability;
import btw.item.BTWItems;
import btw.util.ColorUtils;
import deco.block.DecoBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;

public class CarpetBlock extends Block {
	public CarpetBlock(int id) {
		super(id, DecoBlocks.carpetMaterial);
		this.initBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
		this.setHardness(0.8F);
		this.setBuoyant();
		this.setFireProperties(Flammability.CLOTH);
		this.setStepSound(soundClothFootstep);
		this.setUnlocalizedName("decoBlockCarpet");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public float mobSpawnOnVerticalOffset(World world, int x, int y, int z) {
		return -0.9375F;
	}
	
	@Override
	public boolean canGroundCoverRestOnBlock(World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public float groundCoverRestingOnVisualOffset(IBlockAccess blockAccess, int x, int y, int z) {
		return -0.9375F;
	}
	
	@Override
	public boolean getBlocksMovement(IBlockAccess blockAccess, int x, int y, int z) {
		return false;
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int getHarvestToolLevel(IBlockAccess blockAccess, int x, int y, int z) {
		return 2;
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.wool.itemID, 1, metadata, chanceOfDrop);
		return true;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	Icon[] icons;
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		return icons[meta];
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
		return true;
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		icons = new Icon[16];
		
		for (int i = 0; i < 16; i++) {
			icons[i] = register.registerIcon("decoBlockCarpet_" + ColorUtils.colorOrder[i]);
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < 16; ++i) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}
}

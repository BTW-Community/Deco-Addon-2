package deco.block.blocks;

import btw.util.ColorUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;

public class StoneVariantsBlock extends Block {
	public static final int NUM_TYPES = 3;
	public static final int GRANITE_TYPE = 0;
	public static final int ANDESITE_TYPE = 1;
	public static final int DIORITE_TYPE = 2;
	
	protected StoneVariantsBlock(int blockID) {
		super(blockID, Material.rock);
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] iconArray;
	
	@Override
	@Environment(EnvType.CLIENT)
	public Icon getIcon(int side, int metadata) {
		return iconArray[metadata];
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < 16; i++) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public void registerIcons(IconRegister register) {
		iconArray = new Icon[16];
		
		for (int i = 0; i < iconArray.length; ++i) {
			iconArray[i] = register.registerIcon(name + "_" + ColorUtils.colorOrder[i]);
		}
	}
}

package deco.block.blocks;

import btw.util.ColorUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;

public class ColoredBlock extends Block {
	protected String name;
	
	public ColoredBlock(int blockID, Material material, String name) {
		super(blockID, material);
		
		this.setUnlocalizedName(name);
		this.name = name;
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata;
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

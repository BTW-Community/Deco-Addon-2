package deco.block.blocks;

import btw.block.blocks.FlowerBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;

public class DecoFlowerBlock extends FlowerBlock {
	private String baseTexture;
	private String[] names;
	
	public DecoFlowerBlock(int blockID, String baseTexture, String[] names) {
		super(blockID);
		this.setUnlocalizedName(baseTexture);
		this.baseTexture = baseTexture;
		this.names = names;
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}
	
	//------------- Class Specific Methods ------------//
	
	public String[] getNames() {
		return names;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] icons;
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		return icons[metadata];
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		icons = new Icon[names.length];
		
		for (int i = 0; i < names.length; i++) {
			icons[i] = register.registerIcon(baseTexture + "_" + names[i]);
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

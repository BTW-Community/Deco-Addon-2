package deco.block.blocks;

import btw.block.blocks.FenceGateBlock;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;

public class DecoGateBlock extends FenceGateBlock {
	private String texture;
	
	public DecoGateBlock(int blockID, String name) {
		super(blockID);
		
		this.setUnlocalizedName(name);
		this.texture = name;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Override
	public Icon getIcon(int side, int metadata) {
		return blockIcon;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		blockIcon = register.registerIcon(texture);
	}
}

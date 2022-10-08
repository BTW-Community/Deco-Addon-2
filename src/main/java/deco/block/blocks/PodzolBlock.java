package deco.block.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.Block;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;

public class PodzolBlock extends CoarseDirtBlock {
	public PodzolBlock(int blockID) {
		super(blockID);
		this.setUnlocalizedName("decoBlockPodzol");
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon topIcon;
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		if (side == 0) {
			return Block.dirt.blockIcon;
		}
		else if (side == 1) {
			return this.topIcon;
		}
		else {
			return this.blockIcon;
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		this.blockIcon = register.registerIcon("decoBlockPodzol_side");
		this.topIcon = register.registerIcon("decoBlockPodzol_top");
	}
}

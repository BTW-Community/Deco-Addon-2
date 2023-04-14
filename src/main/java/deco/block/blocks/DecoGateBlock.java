package deco.block.blocks;

import btw.block.blocks.FenceGateBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.Block;
import net.minecraft.src.Icon;

public class DecoGateBlock extends FenceGateBlock {
	private final int PLANK_META;
	
	public DecoGateBlock(int id, String name, int plankMeta) {
		super(id);
		this.PLANK_META = plankMeta;
		
		this.setUnlocalizedName(name);
	}
	
	//----------- Client Side Functionality -----------//
	
	@Override
	@Environment(EnvType.CLIENT)
	public Icon getIcon(int side, int meta) {
		return Block.planks.getIcon(side, this.PLANK_META);
	}
}

package deco.block.blocks;

import btw.block.blocks.TrapDoorBlock;
import btw.item.BTWItems;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.World;

public class DecoTrapDoorBlock extends TrapDoorBlock {
	private String texture;
	
	public DecoTrapDoorBlock(int blockID, String name) {
		super(blockID);
		
		this.setUnlocalizedName(name);
		this.texture = name;
	}
	
	@Override
	public int getHarvestToolLevel(IBlockAccess blockAccess, int x, int y, int z) {
		return 2;
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.sawDust.itemID, 1, 0, chanceOfDrop);
		return true;
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

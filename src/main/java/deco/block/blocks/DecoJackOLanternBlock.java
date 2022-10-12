package deco.block.blocks;

import btw.block.blocks.JackOLanternBlock;
import deco.block.DecoBlocks;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.World;

import java.util.Random;

public class DecoJackOLanternBlock extends JackOLanternBlock {
	protected DecoJackOLanternBlock(int blockID) {
		super(blockID);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		checkForExtinguish(world, x, y, z);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int iNeighborBlockID) {
		checkForExtinguish(world, x, y, z);
	}
	
	private void checkForExtinguish(World world, int x, int y, int z) {
		if (this.hasWaterToSidesOrTop(world, x, y, z)) {
			this.extinguishLantern(world, x, y, z);
		}
	}
	
	private void extinguishLantern(World world, int x, int y, int z) {
		int metadata = world.getBlockMetadata(x, y, z);
		world.setBlockAndMetadataWithNotify(x, y, z, DecoBlocks.carvedPumpkin.blockID, metadata);
		world.playAuxSFX(2227, x, y, z, 0);
	}
	
	//CLIENT ONLY
	public Icon sideIcon;
	public Icon topIcon;
	public Icon[] faces;
	
	public Icon getIcon(int side, int meta) {
		int damage = meta % 4;
		int facing = meta / 4;
		
		switch (side) {
			case 0:
			case 1:
				return topIcon;
			case 2:
				return facing == 2 ? faces[damage] : sideIcon;
			case 3:
				return facing == 0 ? faces[damage] : sideIcon;
			case 4:
				return facing == 1 ? faces[damage] : sideIcon;
			case 5:
				return facing == 3 ? faces[damage] : sideIcon;
			default:
				return sideIcon;
		}
	}
	
	public void registerIcons(IconRegister register) {
		faces = new Icon[3];
		faces[0] = register.registerIcon("decoBlockPumpkin_0_lit");
		faces[1] = register.registerIcon("decoBlockPumpkin_1_lit");
		faces[2] = register.registerIcon("decoBlockPumpkin_2_lit");
		this.topIcon = register.registerIcon("pumpkin_top");
		this.sideIcon = register.registerIcon("pumpkin_side");
	}
}

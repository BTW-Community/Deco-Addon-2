package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.LadderBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.World;

import java.util.Random;

public class DecoLadderBlock extends LadderBlock {
	private String name;
	
	private final int FLAMING_LADDER_ID;
	
	public DecoLadderBlock(int blockID, int flamingLadderID, String name) {
		super(blockID);
		this.setUnlocalizedName(name);
		this.name = name;
		this.FLAMING_LADDER_ID = flamingLadderID;
	}
	
	@Override
	public boolean setOnFireDirectly(World world, int x, int y, int z) {
		int newMetadata = BTWBlocks.flamingLadder.setFacing(0, getFacing(world, x, y, z));
		world.setBlockAndMetadataWithNotify(x, y, z, this.FLAMING_LADDER_ID, newMetadata);
		return true;
	}
	
	@Override
	public int idDropped(int metadata, Random rand, int fortuneModifier) {
		return this.blockID;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		this.blockIcon = register.registerIcon(this.name);
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getHopperFilterIcon() {
		return this.blockIcon;
	}
}

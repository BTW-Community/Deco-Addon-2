package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.LadderBlockFlaming;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.IconRegister;
import net.minecraft.src.World;

import java.util.Random;

public class DecoLadderBlockFlaming extends LadderBlockFlaming {
	private String name;
	
	private final int LADDER_ID;
	
	public DecoLadderBlockFlaming(int blockID, int ladderID, String name) {
		super(blockID);
		this.name = name;
		this.LADDER_ID = ladderID;
	}
	
	@Override
	protected void extinguish(World world, int x, int y, int z) {
		int newMetadata = BTWBlocks.ladder.setFacing(0, getFacing(world, x, y, z));
		world.setBlockAndMetadataWithNotify(x, y, z, this.LADDER_ID, newMetadata);
	}
	
	@Override
	public int idDropped(int metadata, Random rand, int fortuneModifier) {
		return this.LADDER_ID;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		this.blockIcon = register.registerIcon(this.name);
	}
}

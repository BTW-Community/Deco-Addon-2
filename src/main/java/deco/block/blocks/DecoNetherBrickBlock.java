package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.NetherBrickBlock;
import deco.block.DecoBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;

import java.util.Random;

public class DecoNetherBrickBlock extends NetherBrickBlock {
	public static final int RED_TYPE = 0;
	public static final int RED_CHISELED_TYPE = 1;
	public static final int CHISELED_TYPE = 2;
	
	public DecoNetherBrickBlock(int blockID) {
		super(blockID);
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setPicksEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("decoBlockNetherBrick");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public int idDropped(int metadata, Random rand, int fortuneModifier) {
		if (metadata == RED_TYPE || metadata == RED_CHISELED_TYPE) {
			return DecoBlocks.looseRedNetherBrick.blockID;
		}
		
		return BTWBlocks.looseNetherBrick.blockID;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] icons;
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int meta) {
		return icons[meta];
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		icons = new Icon[3];
		
		icons[0] = register.registerIcon("decoBlockNetherBrickRed");
		icons[1] = register.registerIcon("decoBlockNetherBrickRedChiseled");
		icons[2] = register.registerIcon("decoBlockNetherBrickChiseled");
	}
}

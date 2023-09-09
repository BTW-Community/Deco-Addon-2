package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.LadderBlockBase;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Material;

import java.util.Random;

public class IronLadderBlock extends LadderBlockBase {
	public IronLadderBlock(int blockID) {
		super(blockID);
		this.setAxesEffectiveOn(false);
		this.setHardness(1.5F);
		this.setNonBuoyant();
		this.setBlockMaterial(Material.iron);
		this.setStepSound(soundMetalFootstep);
		this.setUnlocalizedName("decoBlockLadderIron");
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	public int idDropped(int metadata, Random rand, int fortuneModifier) {
		return this.blockID;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		this.blockIcon = register.registerIcon(this.getUnlocalizedName2());
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getHopperFilterIcon() {
		return this.blockIcon;
	}
}

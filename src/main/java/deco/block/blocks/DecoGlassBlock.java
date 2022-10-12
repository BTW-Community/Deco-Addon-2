package deco.block.blocks;

import btw.block.blocks.GlassBlock;
import deco.block.util.WoodTypeHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Material;

public class DecoGlassBlock extends GlassBlock {
	protected String name;
	
	public DecoGlassBlock(int blockID, String name) {
		super(blockID, Material.glass, false);
		this.setHardness(0.3F);
		this.setStepSound(Block.soundGlassFootstep);
		this.setUnlocalizedName(name);
		this.setPicksEffectiveOn();
		this.setCreativeTab(CreativeTabs.tabDecorations);
		
		this.name = name;
	}
	
	// ------------ Client Side Functionality ----------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		this.blockIcon = register.registerIcon(this.name);
	}
}

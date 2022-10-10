package deco.block.blocks;

import btw.block.blocks.SidingAndCornerAndDecorativeWallBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.Icon;
import net.minecraft.src.IconRegister;
import net.minecraft.src.Material;
import net.minecraft.src.StepSound;

public class SidingCornerWallTopAndBottomBlock extends SidingAndCornerAndDecorativeWallBlock {
	private String topTexture;
	private String bottomTexture;
	
	public SidingCornerWallTopAndBottomBlock(int blockID, Material material, String topTexture, String sideTexture, float hardness,
			float resistance, StepSound stepSound, String name) {
		this(blockID, material, topTexture, sideTexture, topTexture, hardness, resistance, stepSound, name);
	}
	
	public SidingCornerWallTopAndBottomBlock(int blockID, Material material, String topTexture, String sideTexture, String bottomTexture, float hardness,
			float resistance, StepSound stepSound, String name) {
		super(blockID, material, sideTexture, hardness, resistance, stepSound, name);
		this.topTexture = topTexture;
		this.bottomTexture = bottomTexture;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon topIcon;
	@Environment(EnvType.CLIENT)
	private Icon bottomIcon;
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		this.topIcon = register.registerIcon(this.topTexture);
		this.bottomIcon = register.registerIcon(this.bottomTexture);
		
		super.registerIcons(register);
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		if (side == 0) {
			return bottomIcon;
		}
		else if (side == 1) {
			return topIcon;
		}
		else {
			return super.getIcon(side, metadata);
		}
	}
}

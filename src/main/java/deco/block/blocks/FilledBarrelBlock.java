package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.util.Flammability;
import btw.client.render.util.RenderUtils;
import btw.item.BTWItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;

public class FilledBarrelBlock extends Block {
	public static final Item[] types = {BTWItems.wheat, BTWItems.hemp, Item.potato, Item.carrot, Item.fishRaw};
	public static final String[] typeTags = {"wheat", "hemp", "potato", "carrot", "fish"};
	
	private String name;
	
	public FilledBarrelBlock(int blockID, String name) {
		super(blockID, BTWBlocks.plankMaterial);
		this.setAxesEffectiveOn();
		this.setHardness(1.0F);
		this.setResistance(5.0F);
		this.setFireProperties(Flammability.PLANKS);
		this.setBuoyant();
		this.setStepSound(soundWoodFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		
		this.name = name;
		this.setUnlocalizedName(name);
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}
	
	@Override
	public int getHarvestToolLevel(IBlockAccess blockAccess, int x, int y, int z) {
		return 2;
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.sawDust.itemID, 2, 0, chanceOfDrop);
		this.dropItemsIndividually(world, x, y, z, types[metadata].itemID, 8, 0, chanceOfDrop);
		return true;
	}
	
	//----------- Client Side Functionality -----------//
	
	private boolean secondPass = false;
	
	@Environment(EnvType.CLIENT)
	private Icon[] topIconArray;
	@Environment(EnvType.CLIENT)
	private Icon bottomIcon;
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		topIconArray = new Icon[types.length];
		
		this.blockIcon = register.registerIcon(this.name + "_side");
		this.bottomIcon = register.registerIcon(this.name + "_top");
		
		for (int i = 0; i < types.length; i++) {
			topIconArray[i] = register.registerIcon("decoBlockBarrelTop" + "_" + typeTags[i]);
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		if (secondPass && side == 1) {
			return this.topIconArray[metadata];
		}
		else if (side <= 1) {
			return this.bottomIcon;
		}
		else {
			return this.blockIcon;
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < types.length; i++) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void renderBlockSecondPass(RenderBlocks renderBlocks, int x, int y, int z, boolean firstPassResult) {
		this.secondPass = true;
		renderBlocks.setRenderBounds(0, 0, 0, 1, 1, 1);
		renderBlocks.renderStandardBlock(this, x, y, z);
		this.secondPass = false;
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void renderBlockAsItem(RenderBlocks renderBlocks, int itemDamage, float brightness) {
		renderBlocks.renderBlockAsItemVanilla(this, itemDamage, brightness);
		
		this.secondPass = true;
		// Weird offset is done to prevent z-fighting
		RenderUtils.renderInvBlockWithMetadata(renderBlocks, this, -0.5F, -0.499F, -0.5F, itemDamage);
		this.secondPass = false;
	}
}
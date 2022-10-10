package deco.block.blocks;

import btw.block.BTWBlocks;
import deco.block.DecoBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;
import java.util.Random;

public class StoneBrickVariantsBlock extends Block {
	public StoneBrickVariantsBlock(int blockID) {
		super(blockID, Material.rock);
		this.setHardness(2.25F);
		this.setResistance(10.0F);
		this.setPicksEffectiveOn();
		this.setChiselsEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("decoBlockStoneBricks");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public void onBlockDestroyedWithImproperTool(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		dropBlockAsItem(world, x, y, z, metadata, 0);
	}
	
	@Override
	public int idDropped(int metadata, Random random, int fortuneModifier) {
		switch (metadata) {
			case StoneVariantsBlock.GRANITE_TYPE:
				return DecoBlocks.looseGraniteBrick.blockID;
			case StoneVariantsBlock.ANDESITE_TYPE:
				return DecoBlocks.looseAndesiteBrick.blockID;
			case StoneVariantsBlock.DIORITE_TYPE:
				return DecoBlocks.looseDioriteBrick.blockID;
			case StoneVariantsBlock.SLATE_TYPE:
				return DecoBlocks.looseSlateBrick.blockID;
		}
		
		return BTWBlocks.looseCobblestone.blockID;
	}
	
	@Override
	public boolean hasMortar(IBlockAccess blockAccess, int x, int y, int z) {
		return true;
	}
	
	//------------- Class Specific Methods ------------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] iconArray;
	
	@Override
	@Environment(EnvType.CLIENT)
	public Icon getIcon(int side, int metadata) {
		return this.iconArray[metadata];
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < StoneVariantsBlock.NUM_TYPES; i++) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public void registerIcons(IconRegister register) {
		iconArray = new Icon[StoneVariantsBlock.NUM_TYPES];
		
		for (int i = 0; i < iconArray.length; i++) {
			iconArray[i] = register.registerIcon("decoBlock" + StoneVariantsBlock.namesCapital[i] + "Brick");
		}
	}
}
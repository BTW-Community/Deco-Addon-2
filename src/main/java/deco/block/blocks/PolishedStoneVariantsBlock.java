package deco.block.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;

public class PolishedStoneVariantsBlock extends Block {
	public PolishedStoneVariantsBlock(int blockID) {
		super(blockID, Material.rock);
		this.setHardness(2.25F);
		this.setResistance(10.0F);
		this.setPicksEffectiveOn();
		this.setChiselsEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("decoBlockStoneSmooth");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata;
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
			iconArray[i] = register.registerIcon("decoBlock" + StoneVariantsBlock.namesCapital[i] + "Smooth");
		}
	}
}

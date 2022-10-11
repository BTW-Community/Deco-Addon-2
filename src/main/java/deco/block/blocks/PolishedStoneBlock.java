package deco.block.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class PolishedStoneBlock extends Block {
	public static final int UPPER_STRATA_TYPE = 0;
	public static final int MID_STRATA_TYPE = 1;
	public static final int DEEP_STRATA_TYPE = 2;
	
	public PolishedStoneBlock(int blockID) {
		super(blockID, Material.rock);
		this.setHardness(2.25F);
		this.setResistance(10.0F);
		this.setPicksEffectiveOn();
		this.setUnlocalizedName("decoBlockPolishedStone");
		this.setStepSound(Block.soundStoneFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}
	
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	public static Icon[] icons = new Icon[3];
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		return icons[metadata];
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		icons[UPPER_STRATA_TYPE] = register.registerIcon("stoneslab_top");
		icons[MID_STRATA_TYPE] = register.registerIcon("stoneslab_top_1");
		icons[DEEP_STRATA_TYPE] = register.registerIcon("stoneslab_top_2");
	}
}

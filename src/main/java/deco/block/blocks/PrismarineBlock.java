package deco.block.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class PrismarineBlock extends Block {
	public static final int DEFAULT_TYPE = 0;
	public static final int BRICK_TYPE = 1;
	public static final int DARK_TYPE = 2;
	
	public PrismarineBlock(int blockID) {
		super(blockID, Material.rock);
		this.setHardness(2.25F);
		this.setResistance(10.0F);
		this.setPicksEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("decoBlockPrismarine");
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
		icons[DEFAULT_TYPE] = register.registerIcon("decoBlockPrismarine");
		icons[BRICK_TYPE] = register.registerIcon("decoBlockPrismarineBrick");
		icons[DARK_TYPE] = register.registerIcon("decoBlockPrismarineDark");
	}
}

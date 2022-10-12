package deco.block.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class InfusedStoneBlock extends Block {
	public static final int TYPE_DEFAULT = 0;
	public static final int TYPE_POLISHED = 1;
	public static final int TYPE_BRICK = 2;
	public static final int TYPE_CHISELED = 3;
	
	public InfusedStoneBlock(int blockID) {
		super(blockID, Material.rock);
		this.setPicksEffectiveOn();
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1.5F);
		this.setResistance(10F);
		this.setUnlocalizedName("decoBlockInfusedStone");
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
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
		icons = new Icon[4];
		
		icons[0] = register.registerIcon("decoBlockInfusedStone");
		icons[1] = register.registerIcon("decoBlockInfusedStoneSmooth");
		icons[2] = register.registerIcon("decoBlockInfusedStoneBrick");
		icons[3] = register.registerIcon("decoBlockInfusedStoneChiseled");
	}
}

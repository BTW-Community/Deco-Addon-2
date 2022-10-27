package deco.block.mixins;

import btw.block.blocks.ObsidianBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(ObsidianBlock.class)
public abstract class ObsidianBlockMixin extends BlockObsidian {
	public ObsidianBlockMixin(int blockID) {
		super(blockID);
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] icons = new Icon[2];
	
	@Environment(EnvType.CLIENT)
	@Override
	public void getSubBlocks(int metadata, CreativeTabs creativeTabs, List list) {
		list.add(new ItemStack(metadata, 1, 0));
		list.add(new ItemStack(metadata, 1, 1));
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		return icons[metadata];
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		icons[0] = register.registerIcon("obsidian");
		icons[1] = register.registerIcon("decoBlockInfusedObsidian");
	}
}

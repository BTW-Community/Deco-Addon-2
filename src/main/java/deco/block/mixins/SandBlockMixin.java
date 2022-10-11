package deco.block.mixins;

import btw.block.blocks.FallingFullBlock;
import btw.block.blocks.SandBlock;
import btw.item.BTWItems;
import deco.block.util.SandHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(SandBlock.class)
public class SandBlockMixin extends FallingFullBlock {
	public SandBlockMixin(int blockID, Material material) {
		super(blockID, material);
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}
	
	@Inject(method = "dropComponentItemsOnBadBreak", at = @At("HEAD"), cancellable = true)
	public void dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop, CallbackInfoReturnable<Boolean> cir) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.sandPile.itemID, 6, metadata, chanceOfDrop);
		cir.setReturnValue(true);
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] extraIcons;
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		if (metadata == 0) {
			return this.blockIcon;
		}
		else {
			return extraIcons[metadata - 1];
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		extraIcons = new Icon[SandHelper.NUM_EXTRA_SAND_TYPES];
		
		for (int i = 0; i < SandHelper.NUM_EXTRA_SAND_TYPES; i++) {
			extraIcons[i] = register.registerIcon("decoBlock" + SandHelper.sandNamesCapital[i + 1] + "Sand");
		}
		
		super.registerIcons(register);
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < SandHelper.NUM_SAND_TYPES; i++) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}
}
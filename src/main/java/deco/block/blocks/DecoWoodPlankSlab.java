package deco.block.blocks;

import btw.block.blocks.WoodSlabBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

public class DecoWoodPlankSlab extends WoodSlabBlock {
	public DecoWoodPlankSlab(int blockID, boolean isDoubleSlab, int[] woodTypes, String[] names) {
		super(blockID, isDoubleSlab);
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Inject(method = "getSubBlocks(ILnet/minecraft/src/CreativeTabs;Ljava/util/List;)V", at = @At("TAIL"))
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List<ItemStack> list, CallbackInfo info) {
		if (!isDoubleSlab) {
			for (int i = 5; i < 8; i++) {
				list.add(new ItemStack(blockID, 1, i));
			}
		}
	}
}

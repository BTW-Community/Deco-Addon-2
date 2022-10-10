package deco.block.mixins;

import btw.block.blocks.StoneBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(StoneBlock.class)
public interface StoneBlockInvoker {
	@Invoker("getConversionLevelForTool")
	int getConversionLevelForToolAccessor(ItemStack stack, World world, int x, int y, int z);
}

package deco.block.mixins;

import btw.block.blocks.FallingFullBlock;
import btw.block.blocks.LooseDirtBlock;
import deco.block.DecoBlocks;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(LooseDirtBlock.class)
public class LooseDirtBlockMixin extends FallingFullBlock {
	public LooseDirtBlockMixin(int blockID, Material material) {
		super(blockID, material);
	}
	
	@Inject(method = "<init>", at = @At("TAIL"))
	public void setupExtraParameters(int blockID, CallbackInfo ci) {
		this.setTickRandomly(true);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int idAbove = world.getBlockId(x, y + 1, z);
		
		if (idAbove == Block.waterMoving.blockID || idAbove == Block.waterStill.blockID) {
			if (rand.nextInt(15) == 0) {
				world.setBlockWithNotify(x, y, z, DecoBlocks.mud.blockID);
				world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			}
		}
		
		checkForFall(world, x, y, z);
	}
}

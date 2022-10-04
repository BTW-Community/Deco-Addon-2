package deco.block.mixins;

import btw.block.BTWBlocks;
import btw.block.blocks.LadderBlock;
import btw.block.blocks.LadderBlockBase;
import btw.block.blocks.LadderBlockFlaming;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LadderBlockFlaming.class)
public class LadderBlockFlamingMixin extends LadderBlockBase {
	protected LadderBlockFlamingMixin(int blockID) {
		super(blockID);
	}
	
	@Inject(method = "lightLadderAtLocationIfPresent", at = @At("HEAD"))
	public void lightLadderAtLocationIfPresent(World world, int x, int y, int z, CallbackInfo info) {
		int blockID = world.getBlockId(x, y, z);
		Block block = Block.blocksList[blockID];
		
		if (block instanceof LadderBlock) {
			block.setOnFireDirectly(world, x, y, z);
		}
	}
}

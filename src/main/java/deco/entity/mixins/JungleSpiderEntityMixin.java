package deco.entity.mixins;

import btw.entity.mob.JungleSpiderEntity;
import btw.entity.mob.SpiderEntity;
import deco.block.blocks.DecoLeavesBlock;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(JungleSpiderEntity.class)
public abstract class JungleSpiderEntityMixin extends SpiderEntity {
	public JungleSpiderEntityMixin(World world) {
		super(world);
	}
	
	@Inject(method = "canSpawnOnBlock", at = @At("HEAD"), remap = false, cancellable = true)
	public void canSpawnOnBlock(int blockID, CallbackInfoReturnable<Boolean> cir) {
		Block block = Block.blocksList[blockID];
		
		if (block instanceof DecoLeavesBlock) {
			cir.setReturnValue(((DecoLeavesBlock) block).spawnsJungleSpiders);
		}
	}
}

package deco.entity.mixins;

import btw.entity.mob.SquidEntity;
import deco.item.DecoItems;
import net.minecraft.src.EntityWaterMob;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SquidEntity.class)
public abstract class SquidEntityMixin extends EntityWaterMob {
	public SquidEntityMixin(World world) {
		super(world);
	}
	
	@Inject(method = "dropFewItems", at = @At("TAIL"))
	public void dropFewItems(boolean killedByPlayer, int lootingModifier, CallbackInfo ci) {
		int count = this.rand.nextInt(3 + lootingModifier) + 1;
		
		for (int i = 0; i < count; i++) {
			entityDropItem(new ItemStack(DecoItems.prismarineShard, 1, 0), 0F);
		}
	}
}
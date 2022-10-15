package deco.entity.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.MovementInput;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(EntityPlayerSP.class)
public abstract class EntityPlayerSPMixin extends EntityPlayer {
	public EntityPlayerSPMixin(World world) {
		super(world);
	}
	
	@Shadow
	public MovementInput movementInput;
	
	@Inject(method = "onLivingUpdate", at = @At("HEAD"))
	public void climbFreestandingLadder(CallbackInfo ci) {
		if (this.isClimbingFreestandingLadder()) {
			this.motionY = 0.11D * this.getLadderVerticalMovementModifier();
		}
	}
	
	private boolean isClimbingFreestandingLadder() {
		return this.isOnLadder() && this.movementInput.jump;
	}
}

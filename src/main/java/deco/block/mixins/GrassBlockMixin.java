package deco.block.mixins;

import btw.block.blocks.GrassBlock;
import deco.block.util.GrassHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GrassBlock.class)
public abstract class GrassBlockMixin extends BlockGrass {
	protected GrassBlockMixin(int blockID) {
		super(blockID);
	}
	
	@Inject(method = "canBeGrazedOn", at = @At("HEAD"), remap = false, cancellable = true)
	public void canBeGrazedOn(IBlockAccess blockAccess, int x, int y, int z, EntityAnimal animal, CallbackInfoReturnable<Boolean> cir) {
		World world = (World) blockAccess;
		int skylight = world.getBlockNaturalLightValueMaximum(x, y + 1, z);
		
		if (skylight < GrassHelper.EDIBLE_LIGHT_LEVEL) {
			cir.setReturnValue(false);
		}
	}
	
	@ModifyVariable(method = "canGrassSurviveAtLocation", at = @At(value = "STORE"), ordinal = 4, remap = false)
	private static int removeNaturalLightSurvivalReq(int naturalLightValue) {
		return 15;
	}
	
	@Shadow
	public boolean isSparse(IBlockAccess blockAccess, int x, int y, int z) {
		return false;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon roughGrassIcon;
	
	@Environment(EnvType.CLIENT)
	@Inject(method = "registerIcons", at = @At("HEAD"))
	public void registerIcons(IconRegister register, CallbackInfo ci) {
		this.roughGrassIcon = register.registerIcon("decoBlockGrassRough");
	}
	
	@Environment(EnvType.CLIENT)
	@Inject(method = "getBlockTextureSecondPass", at = @At(value = "RETURN", ordinal = 1), remap = false, cancellable = true)
	public void replaceTopTexture(IBlockAccess blockAccess, int x, int y, int z, int side, CallbackInfoReturnable<Icon> cir) {
		World world = null;
		
		if (blockAccess instanceof ChunkCache) {
			ChunkCache chunkCache = (ChunkCache) blockAccess;
			world = chunkCache.worldObj;
		}
		else if (blockAccess instanceof World) {
			world = (World) blockAccess;
		}
		
		int skylight;
		
		if (world != null) {
			skylight = world.getBlockNaturalLightValueMaximum(x, y + 1, z);
		}
		else {
			skylight = 9;
		}
		
		if (skylight < GrassHelper.EDIBLE_LIGHT_LEVEL && !this.isSparse(blockAccess, x, y, z)) {
			cir.setReturnValue(this.roughGrassIcon);
		}
	}
}

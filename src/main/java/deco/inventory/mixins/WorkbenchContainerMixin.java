package deco.inventory.mixins;

import btw.inventory.container.WorkbenchContainer;
import deco.block.DecoBlocks;
import net.minecraft.src.ContainerWorkbench;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorkbenchContainer.class)
public abstract class WorkbenchContainerMixin extends ContainerWorkbench {
	@Shadow(remap = false)
	public World world;
	@Shadow(remap = false)
	public int blockX, blockY, blockZ;
	
	public WorkbenchContainerMixin(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5) {
		super(par1InventoryPlayer, par2World, par3, par4, par5);
	}
	
	@Inject(method = "canInteractWith(Lnet/minecraft/src/EntityPlayer;)Z", at = @At("HEAD"), cancellable = true)
	public void canInteractWith(EntityPlayer player, CallbackInfoReturnable<Boolean> info) {
		int blockID = world.getBlockId(blockX, blockY, blockZ);
		
		if (blockID == DecoBlocks.workStump.blockID) {
			info.setReturnValue(true);
		}
	}
}

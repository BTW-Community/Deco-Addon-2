package deco.inventory.mixins;

import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = {"net/minecraft/src/SlotRepair"})
public class SlotRepairMixin extends Slot {
	public SlotRepairMixin(IInventory par1IInventory, int par2, int par3, int par4) {
		super(par1IInventory, par2, par3, par4);
	}
	
	@Shadow
	ContainerRepair anvil;
	
	@Inject(method = "canTakeStack", at = @At("HEAD"), cancellable = true)
	public void canTakeStack(EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(true);
	}
	
	/**
	 * @author dawnraider00 - Deco Addon
	 * @reason Lazy, unlikely to conflict, difficult mixin otherwise
	 */
	@Overwrite
	public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
	{
		ContainerRepairInvoker.getRepairInputInventory(this.anvil).setInventorySlotContents(0, (ItemStack)null);
		
		if (ContainerRepairInvoker.getStackSizeUsedInRepair(this.anvil) > 0)
		{
			ItemStack var3 = ContainerRepairInvoker.getRepairInputInventory(this.anvil).getStackInSlot(1);
			
			if (var3 != null && var3.stackSize > ContainerRepairInvoker.getStackSizeUsedInRepair(this.anvil))
			{
				var3.stackSize -= ContainerRepairInvoker.getStackSizeUsedInRepair(this.anvil);
				ContainerRepairInvoker.getRepairInputInventory(this.anvil).setInventorySlotContents(1, var3);
			}
			else
			{
				ContainerRepairInvoker.getRepairInputInventory(this.anvil).setInventorySlotContents(1, (ItemStack)null);
			}
		}
		else
		{
			ContainerRepairInvoker.getRepairInputInventory(this.anvil).setInventorySlotContents(1, (ItemStack)null);
		}
	}
}

package deco.inventory.mixins;

import net.minecraft.src.ContainerRepair;
import net.minecraft.src.IInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ContainerRepair.class)
public interface ContainerRepairInvoker {
	@Invoker("getRepairInputInventory")
	static IInventory getRepairInputInventory(ContainerRepair container) {
		return null;
	}
	
	@Invoker("getStackSizeUsedInRepair")
	static int getStackSizeUsedInRepair(ContainerRepair container) {
		return 0;
	}
}

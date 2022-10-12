package deco.inventory.mixins;

import net.minecraft.src.Container;
import net.minecraft.src.ContainerRepair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ContainerRepair.class)
public abstract class ContainerRepairMixin extends Container {
}

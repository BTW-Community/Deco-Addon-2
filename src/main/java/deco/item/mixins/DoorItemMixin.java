package deco.item.mixins;

import btw.item.items.DoorItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DoorItem.class)
public class DoorItemMixin extends Item {
	public DoorItemMixin(int itemID) {
		super(itemID);
	}
	
	@Inject(method = "<init>", at = @At("TAIL"))
	public void doorConstructor(int itemID, CallbackInfo info) {
		this.setMaxStackSize(16);
	}
	
	@Inject(method = "onItemUse(Lnet/minecraft/src/ItemStack;Lnet/minecraft/src/EntityPlayer;Lnet/minecraft/src/World;IIIIFFF)Z",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/src/ItemDoor;" +
							"placeDoorBlock(Lnet/minecraft/src/World;IIIILnet/minecraft/src/Block;)V"
			))
	public void playSoundOnUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int facing, float hitX, float hitY, float hitZ, CallbackInfoReturnable<Boolean> info) {
		world.playSound(x, y, z, "dig.wood", 1.0F, .75F);
	}
}

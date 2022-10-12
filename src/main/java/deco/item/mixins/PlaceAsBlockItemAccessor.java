package deco.item.mixins;

import btw.item.items.PlaceAsBlockItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PlaceAsBlockItem.class)
public interface PlaceAsBlockItemAccessor {
	@Accessor(value = "blockID", remap = false)
	void setBlockID(int blockID);
}

package deco.item.mixins;

import btw.item.blockitems.legacy.LegacySubstitutionBlockItem;
import deco.item.util.LegacySubstitutionBlockItemInterface;
import net.minecraft.src.ItemBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LegacySubstitutionBlockItem.class)
public class LegacySubstitutionBlockItemMixin extends ItemBlock implements LegacySubstitutionBlockItemInterface {
	public LegacySubstitutionBlockItemMixin(int itemID) {
		super(itemID);
	}
	
	@Override
	public void setMetadataToPlace(int metadata) {
		this.blockMetadata = metadata;
	}
}

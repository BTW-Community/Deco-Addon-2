package deco.block.mixins;

import btw.block.BTWBlocks;
import btw.block.blocks.BookshelfBlock;
import btw.item.BTWItems;
import deco.block.util.BookshelfInterface;
import deco.block.util.BookshelfType;
import deco.block.util.WoodTypeHelper;
import deco.item.DecoItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(BookshelfBlock.class)
public abstract class BookshelfBlockMixin extends Block implements BookshelfInterface {
	private BookshelfType bookshelfType;
	private String textureBase;
	
	public BookshelfBlockMixin(int blockID) {
		super(blockID, BTWBlocks.plankMaterial);
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@Inject(method = "dropComponentItemsOnBadBreak", at = @At("HEAD"), cancellable = true)
	public void dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop, CallbackInfoReturnable<Boolean> info) {
		switch (bookshelfType) {
			case RACK:
				this.dropItemsIndividually(world, x, y, z, BTWItems.sawDust.itemID, 2, 0, chanceOfDrop);
				this.dropItemsIndividually(world, x, y, z, Item.glassBottle.itemID, 3, 0, chanceOfDrop);
				info.setReturnValue(true);
				break;
			case FULL:
				this.dropItemsIndividually(world, x, y, z, BTWItems.sawDust.itemID, 2, 0, chanceOfDrop);
				this.dropItemsIndividually(world, x, y, z, DecoItems.plainBook.itemID, 3, 0, chanceOfDrop);
				info.setReturnValue(true);
				break;
			default: // Empty types
				this.dropItemsIndividually(world, x, y, z, BTWItems.sawDust.itemID, 2, 0, chanceOfDrop);
				info.setReturnValue(true);
				break;
			case ENCHANTED: // Don't override behavior of default break
				break;
		}
	}
	
	//------------- Class Specific Methods ------------//
	
	@Override
	public BookshelfInterface setTexture(String texture) {
		this.textureBase = texture;
		return this;
	}
	
	@Override
	public BookshelfInterface setType(BookshelfType type) {
		this.bookshelfType = type;
		return this;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] sideTextures;
	
	@Environment(EnvType.CLIENT)
	@Inject(method = "getIcon", at = @At("HEAD"), cancellable = true)
	public void getIcon(int side, int metadata, CallbackInfoReturnable<Icon> info) {
		if (side == 0 || side == 1) {
			info.setReturnValue(Block.planks.getIcon(side, metadata));
		}
		else {
			info.setReturnValue(sideTextures[metadata]);
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		sideTextures = new Icon[8];
		
		sideTextures[0] = register.registerIcon(textureBase + "Oak");
		sideTextures[1] = register.registerIcon(textureBase + "Spruce");
		sideTextures[2] = register.registerIcon(textureBase + "Birch");
		sideTextures[3] = register.registerIcon(textureBase + "Jungle");
		sideTextures[4] = register.registerIcon(textureBase + "Blood");
		sideTextures[5] = register.registerIcon(textureBase + "Cherry");
		sideTextures[6] = register.registerIcon(textureBase + "Acacia");
		sideTextures[7] = register.registerIcon(textureBase + "Mahogany");
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < WoodTypeHelper.NUM_TOTAL_WOOD; i++) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}
}

package deco.block.mixins;

import btw.block.blocks.WoodSlabBlock;
import deco.block.util.WoodTypeHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(WoodSlabBlock.class)
public abstract class WoodSlabMixin extends BlockHalfSlab {
    public WoodSlabMixin(int blockID, boolean isDoubleSlab, Material material) {
        super(blockID, isDoubleSlab, material);
    }

    @Inject(method = "getFullSlabName(I)Ljava/lang/String;", at = @At("HEAD"), cancellable = true)
    public void getFullSlabName(int metadata, CallbackInfoReturnable<String> info) {
        switch (metadata) {
            case WoodTypeHelper.CHERRY_WOOD_TYPE:
                info.setReturnValue(this.getUnlocalizedName() + ".cherry");
                break;
            case WoodTypeHelper.ACACIA_WOOD_TYPE:
                info.setReturnValue(this.getUnlocalizedName() + ".acacia");
                break;
            case WoodTypeHelper.MAHOGANY_WOOD_TYPE:
                info.setReturnValue(this.getUnlocalizedName() + ".mahogany");
                break;
        }
    }

    //----------- Client Side Functionality -----------//

    @Environment(EnvType.CLIENT)
    @Inject(method = "getSubBlocks(ILnet/minecraft/src/CreativeTabs;Ljava/util/List;)V", at = @At("TAIL"))
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List<ItemStack> list, CallbackInfo info) {
        if (!isDoubleSlab) {
            for (int i = 5; i < 8; i++) {
                list.add(new ItemStack(blockID, 1, i));
            }
        }
    }
}

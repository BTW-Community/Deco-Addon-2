package deco.block.mixins;

import btw.block.blocks.PlanksBlock;
import btw.crafting.util.FurnaceBurnTime;
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

@Mixin(PlanksBlock.class)
public class PlanksBlockMixin extends Block {
    protected PlanksBlockMixin(int blockID, Material material) {
        super(blockID, material);
    }

    @Inject(method = "getFurnaceBurnTimeByWoodType(I)I", at = @At("RETURN"), remap = false, cancellable = true)
    private static void getFurnaceBurnTimeByWoodType(int woodType, CallbackInfoReturnable<Integer> info) {
        switch (woodType) {
            case WoodTypeHelper.CHERRY_WOOD_TYPE:
                info.setReturnValue(FurnaceBurnTime.PLANKS_SPRUCE.burnTime);
                break;
            case WoodTypeHelper.ACACIA_WOOD_TYPE:
                info.setReturnValue(FurnaceBurnTime.PLANKS_BIRCH.burnTime);
                break;
            case WoodTypeHelper.MAHOGANY_WOOD_TYPE:
            case WoodTypeHelper.MANGROVE_WOOD_TYPE:
                info.setReturnValue(FurnaceBurnTime.PLANKS_JUNGLE.burnTime);
                break;
        }
    }

    //----------- Client Side Functionality -----------//

    @Environment(EnvType.CLIENT)
    public String[] extraTextures;
    @Environment(EnvType.CLIENT)
    public Icon[] extraIcons;

    @Environment(EnvType.CLIENT)
    @Inject(method = "registerIcons(Lnet/minecraft/src/IconRegister;)V", at = @At("TAIL"))
    public void registerIcons(IconRegister register, CallbackInfo info) {
        extraTextures = new String[] {"decoBlockPlanksCherry", "decoBlockPlanksAcacia", "decoBlockPlanksMahogany", "decoBlockPlanksMangrove"};
        extraIcons = new Icon[extraTextures.length];

        for (int i = 0; i < extraTextures.length; i++) {
            extraIcons[i] = register.registerIcon(extraTextures[i]);
        }
    }

    @Environment(EnvType.CLIENT)
    @Inject(method = "getIcon(II)Lnet/minecraft/src/Icon;", at = @At("HEAD"), cancellable = true)
    public void getIcon(int side, int metadata, CallbackInfoReturnable<Icon> info) {
        if (metadata >= 5) {
            info.setReturnValue(extraIcons[metadata - 5]);
        }
    }

    @Environment(EnvType.CLIENT)
    @Inject(method = "getSubBlocks(ILnet/minecraft/src/CreativeTabs;Ljava/util/List;)V", at = @At("TAIL"))
    public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List<ItemStack> list, CallbackInfo info) {
        for (int i = 0; i < extraTextures.length; i++) {
            list.add(new ItemStack(blockID, 1, i + 5));
        }
    }
}

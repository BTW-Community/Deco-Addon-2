package deco.block.mixins;

import btw.block.BTWBlocks;
import btw.block.blocks.BloodWoodLogBlock;
import btw.block.blocks.ChewedLogBlock;
import btw.item.BTWItems;
import btw.item.util.ItemUtils;
import deco.block.DecoBlocks;
import deco.block.util.WoodTypeHelper;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BloodWoodLogBlock.class)
public abstract class BloodWoodLogMixin extends Block {
	protected BloodWoodLogMixin(int blockID, Material material) {
		super(blockID, material);
	}
	
	@Inject(method = "<init>", at = @At("TAIL"))
	public void setupProperties(int blockID, CallbackInfo info) {
		this.setChiselsEffectiveOn();
	}
	
	@Override
	public boolean canConvertBlock(ItemStack itemStack, World world, int x, int y, int z) {
		return true;
	}
	
	@Override
	public boolean convertBlock(ItemStack stack, World world, int x, int y, int z, int side) {
		int oldMetadata = world.getBlockMetadata(x, y, z);
		
		int orientation = (oldMetadata >> 2) & 3;
		int newMetadata = BTWBlocks.oakChewedLog.setOrientation(oldMetadata, orientation);
		
		world.setBlockAndMetadataWithNotify(x, y, z, DecoBlocks.chewedBloodLog.blockID, newMetadata);
		
		if (!world.isRemote) {
			ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(BTWItems.bark, 1, WoodTypeHelper.BLOOD_WOOD_TYPE), side);
		}
		
		return true;
	}
}

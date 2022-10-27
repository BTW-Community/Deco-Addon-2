package deco.block.mixins;

import btw.block.blocks.SandstoneBlock;
import deco.block.util.SandHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;

@Mixin(SandstoneBlock.class)
public abstract class SandstoneBlockMixin extends BlockSandStone {
	public SandstoneBlockMixin(int blockID) {
		super(blockID);
	}
	
	@Inject(method = "<init>", at = @At("TAIL"))
	public void setupExtraParameters(int blockID, CallbackInfo ci) {
		this.setTickRandomly(true);
	}
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		int meta = world.getBlockMetadata(x, y, z);
		
		if (meta == SandHelper.SANDSTONE_TYPE_DEFAULT && !world.getBlockMaterial(x, y - 1, z).blocksMovement()) {
			int idAbove = world.getBlockId(x, y + 1, z);
			
			if (idAbove != Block.waterMoving.blockID && idAbove != Block.waterStill.blockID) {
				if ((idAbove == Block.lavaMoving.blockID || idAbove == Block.lavaStill.blockID) && rand.nextInt(15) == 0) {
					world.setBlockMetadataWithNotify(x, y, z, SandHelper.SANDSTONE_TYPE_CRACKED);
					world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
				}
			}
			else if (rand.nextInt(15) == 0) {
				world.setBlockMetadataWithNotify(x, y, z, SandHelper.SANDSTONE_TYPE_MOSSY);
				world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			}
		}
		else if (meta == SandHelper.SANDSTONE_TYPE_LARGE_BRICK && !world.getBlockMaterial(x, y - 1, z).blocksMovement()) {
			int idAbove = world.getBlockId(x, y + 1, z);
			
			if (idAbove != Block.waterMoving.blockID && idAbove != Block.waterStill.blockID) {
				if ((idAbove == Block.lavaMoving.blockID || idAbove == Block.lavaStill.blockID) && rand.nextInt(15) == 0) {
					world.setBlockMetadataWithNotify(x, y, z, SandHelper.SANDSTONE_TYPE_LARGE_BRICK_CRACKED);
					world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
				}
			}
			else if (rand.nextInt(15) == 0) {
				world.setBlockMetadataWithNotify(x, y, z, SandHelper.SANDSTONE_TYPE_LARGE_BRICK_MOSSY);
				world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			}
		}
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] extraSideIcons;
	@Environment(EnvType.CLIENT)
	private Icon mossyTopIcon;
	@Environment(EnvType.CLIENT)
	private Icon mossyBottomIcon;
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		if (metadata < SandHelper.NUM_VANILLA_SANDSTONE_TYPES) {
			return super.getIcon(side, metadata);
		}
		else {
			if (metadata == SandHelper.SANDSTONE_TYPE_POLISHED) {
				return super.getIcon(1, 0); // Top icon
			}
			else if (metadata == SandHelper.SANDSTONE_TYPE_MOSSY) {
				if (side == 0) {
					return mossyBottomIcon;
				}
				if (side == 1) {
					return mossyTopIcon;
				}
			}
			else if (metadata == SandHelper.SANDSTONE_TYPE_CRACKED) {
				return super.getIcon(0, 0); // Bottom icon
			}
			
			return extraSideIcons[metadata - SandHelper.NUM_VANILLA_SANDSTONE_TYPES];
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		extraSideIcons = new Icon[SandHelper.NUM_EXTRA_SANDSTONE_TYPES];
		
		for (int i = 0; i < SandHelper.NUM_EXTRA_SANDSTONE_TYPES; i++) {
			if (i == SandHelper.SANDSTONE_TYPE_POLISHED - SandHelper.NUM_VANILLA_SANDSTONE_TYPES ||
					i == SandHelper.SANDSTONE_TYPE_CRACKED - SandHelper.NUM_VANILLA_SANDSTONE_TYPES) {
				// These variants use vanilla textures
				continue;
			}
			
			extraSideIcons[i] = register.registerIcon("decoBlock" + SandHelper.sandstoneSideTextures[i + SandHelper.NUM_VANILLA_SANDSTONE_TYPES]);
		}
		
		this.mossyTopIcon = register.registerIcon("decoBlockSandstoneMossy_top");
		this.mossyBottomIcon = register.registerIcon("decoBlockSandstoneMossy_bottom");
		
		super.registerIcons(register);
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < SandHelper.NUM_SANDSTONE_TYPES; i++) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}
}

package deco.block.blocks;

import btw.item.BTWItems;
import deco.block.util.SandHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;
import java.util.Random;

public class RedSandstoneBlock extends Block {
	public RedSandstoneBlock(int blockID) {
		super(blockID, Material.rock);
		this.setPicksEffectiveOn();
		this.setHardness(1.5F);
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("decoBlockRedSandStone");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setTickRandomly(true);
	}
	
	@Override
	public int getHarvestToolLevel(IBlockAccess blockAccess, int x, int y, int z) {
		return 3;
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.sandPile.itemID, 16, SandHelper.RED_SAND_TYPE, chanceOfDrop);
		return true;
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata;
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
	private Icon[] sideIcons;
	
	@Environment(EnvType.CLIENT)
	private Icon topIcon;
	@Environment(EnvType.CLIENT)
	private Icon bottomIcon;
	
	@Environment(EnvType.CLIENT)
	private Icon mossyTopIcon;
	@Environment(EnvType.CLIENT)
	private Icon mossyBottomIcon;
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		if (metadata == SandHelper.SANDSTONE_TYPE_DEFAULT ||
				metadata == SandHelper.SANDSTONE_TYPE_SMOOTH ||
				metadata == SandHelper.SANDSTONE_TYPE_CHISELED)
		{
			if (side == 0 && metadata == SandHelper.SANDSTONE_TYPE_DEFAULT) {
				return bottomIcon;
			}
			else if (side <= 1) {
				return topIcon;
			}
		}
		else if (metadata == SandHelper.SANDSTONE_TYPE_POLISHED) {
			return topIcon;
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
			return bottomIcon;
		}
		
		return sideIcons[metadata];
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		sideIcons = new Icon[SandHelper.NUM_SANDSTONE_TYPES];
		
		for (int i = 0; i < SandHelper.NUM_SANDSTONE_TYPES; i++) {
			sideIcons[i] = register.registerIcon("decoBlockRed" + SandHelper.sandstoneSideTextures[i]);
		}
		
		this.topIcon = register.registerIcon("decoBlockRedSandstone_top");
		this.bottomIcon = register.registerIcon("decoBlockRedSandstone_bottom");
		
		this.mossyTopIcon = register.registerIcon("decoBlockRedSandstoneMossy_top");
		this.mossyBottomIcon = register.registerIcon("decoBlockRedSandstoneMossy_bottom");
		
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

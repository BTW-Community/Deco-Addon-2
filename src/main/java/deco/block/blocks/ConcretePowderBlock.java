package deco.block.blocks;

import btw.block.blocks.FallingFullBlock;
import btw.client.fx.BTWEffectManager;
import btw.item.BTWItems;
import btw.util.ColorUtils;
import deco.block.DecoBlocks;
import deco.entity.FallingConcreteEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.List;

public class ConcretePowderBlock extends FallingFullBlock {
	public ConcretePowderBlock(int blockID) {
		super(blockID, Material.sand);
		this.setHardness(0.5F);
		this.setShovelsEffectiveOn();
		this.setFilterableProperties(Item.FILTERABLE_FINE);
		this.setStepSound(soundSandFootstep);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setUnlocalizedName("decoBlockConcretePowder");
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.sandPile.itemID, 3, 0, chanceOfDrop);
		this.dropItemsIndividually(world, x, y, z, BTWItems.gravelPile.itemID, 3, 0, chanceOfDrop);
		return true;
	}
	
	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
		this.setConcreteIfNearWater(world, x, y, z);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID) {
		super.onNeighborBlockChange(world, x, y, z, neighborID);
		this.setConcreteIfNearWater(world, x, y, z);
	}
	
	@Override
	public boolean checkForFall(World var1, int var2, int var3, int var4) {
		if (this.canFallIntoBlockAtPos(var1, var2, var3 - 1, var4) && var3 >= 0) {
			if (!BlockSand.fallInstantly && var1.checkChunksExist(var2 - 32, var3 - 32, var4 - 32, var2 + 32, var3 + 32, var4 + 32)) {
				if (!var1.isRemote) {
					FallingConcreteEntity var5 = (FallingConcreteEntity) EntityList
							.createEntityOfType(FallingConcreteEntity.class, var1, (double) var2 + 0.5D, (double) var3 + 0.5D, (double) var4 + 0.5D,
									this.blockID, var1.getBlockMetadata(var2, var3, var4));
					this.onStartFalling(var5);
					var1.spawnEntityInWorld(var5);
				}
				
				return true;
			}
			else {
				var1.setBlockToAir(var2, var3, var4);
				
				while (this.canFallIntoBlockAtPos(var1, var2, var3 - 1, var4) && var3 > 0) {
					--var3;
				}
				
				if (var3 > 0) {
					var1.setBlock(var2, var3, var4, this.blockID);
				}
				
				return true;
			}
		}
		else {
			return false;
		}
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}
	
	//------------- Class Specific Methods ------------//
	
	public boolean setConcreteIfNearWater(World world, int x, int y, int z) {
		if (this.hasWaterToSidesOrTop(world, x, y, z)) {
			int meta = world.getBlockMetadata(x, y, z);
			world.setBlockWithNotify(x, y, z, DecoBlocks.concrete.blockID);
			world.setBlockMetadataWithClient(x, y, z, meta);
			world.playAuxSFX(BTWEffectManager.FIRE_FIZZ_EFFECT_ID, x, y, z, 0);
			
			return true;
		}
		
		return false;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] iconArray;
	
	@Override
	@Environment(EnvType.CLIENT)
	public Icon getIcon(int side, int metadata) {
		return iconArray[metadata];
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < 16; i++) {
			list.add(new ItemStack(blockID, 1, i));
		}
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public void registerIcons(IconRegister register) {
		iconArray = new Icon[16];
		
		for (int i = 0; i < iconArray.length; ++i) {
			iconArray[i] = register.registerIcon("decoBlockConcretePowder_" + ColorUtils.colorOrder[i]);
		}
	}
}

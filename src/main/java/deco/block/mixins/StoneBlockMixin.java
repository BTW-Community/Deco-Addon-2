package deco.block.mixins;

import btw.block.blocks.StoneBlock;
import btw.item.items.PickaxeItem;
import btw.world.util.BlockPos;
import deco.DecoAddon;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(StoneBlock.class)
public class StoneBlockMixin extends Block {
	public StoneBlockMixin(int blockID) {
		super(blockID, Material.rock);
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		player.addStat(StatList.mineBlockStatArray[this.blockID], 1);
		player.addHarvestBlockExhaustion(blockID, x, y, z, metadata);
		
		if (this.canSilkHarvest(metadata) && EnchantmentHelper.getSilkTouchModifier(player)) {
			ItemStack stack = this.createStackedBlock(metadata);
			
			if (stack != null) {
				this.dropBlockAsItem_do(world, x, y, z, stack);
			}
		}
		else {
			if (canHarvestIntact(player.getCurrentEquippedItem(), world, x, y, z)) {
				ItemStack stack = this.createStackedBlock(metadata);
				
				if (stack != null) {
					this.dropBlockAsItem_do(world, x, y, z, stack);
				}
			}
			else {
				int fortuneModifier = EnchantmentHelper.getFortuneModifier(player);
				this.dropBlockAsItem(world, x, y, z, metadata, fortuneModifier);
			}
		}
	}
	
	//------------- Class Specific Methods ------------//
	
	public boolean canHarvestIntact(ItemStack stack, World world, int x, int y, int z) {
		return canToolHarvestIntact(stack) && (this.isBlockIsolated(world, x, y, z) || DecoAddon.diamondPicksAlwaysCollectStone);
	}
	
	public boolean canToolHarvestIntact(ItemStack stack) {
		return (stack.getItem() instanceof PickaxeItem &&
				((PickaxeItem) stack.getItem()).toolMaterial.getHarvestLevel() >= EnumToolMaterial.EMERALD.getHarvestLevel());
	}
	
	public boolean isBlockIsolated(World world, int x, int y, int z) {
		boolean isolated = true;
		
		for (int dir = 0; dir < 6; dir++) {
			BlockPos pos = new BlockPos(x, y, z, dir);
			
			Block neighbor = Block.blocksList[world.getBlockId(pos.x, pos.y, pos.z)];
			
			if (neighbor != null && neighbor.isNaturalStone(world, pos.x, pos.y, pos.z)) {
				isolated = false;
			}
		}
		
		return isolated;
	}
}

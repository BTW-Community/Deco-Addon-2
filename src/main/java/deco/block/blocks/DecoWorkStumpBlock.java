package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.WorkStumpBlock;
import btw.client.fx.BTWEffectManager;
import btw.item.BTWItems;
import btw.item.util.ItemUtils;
import deco.block.util.WoodTypeHelper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class DecoWorkStumpBlock extends WorkStumpBlock {
	private int[] woodTypes;
	private int[] logs;
	private int[] chewedLogs;
	
	public DecoWorkStumpBlock(int blockID, int[] woodTypes, int[] logs, int[] chewedLogs) {
		super(blockID);
		
		this.woodTypes = woodTypes;
		this.logs = logs;
		this.chewedLogs = chewedLogs;
	}
	
	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int metadata, float chanceOfDrop, int fortuneModifier) {
		if (!world.isRemote) {
			dropBlockAsItem_do(world, x, y, z, new ItemStack(BTWItems.sawDust, 3, 0));
			dropBlockAsItem_do(world, x, y, z, new ItemStack(Block.planks.blockID, 1, woodTypes[metadata & 7]));
		}
	}
	
	@Override
	public boolean convertBlock(ItemStack stack, World world, int x, int y, int z, int side) {
		int metadata = world.getBlockMetadata(x, y, z);
		
		if (!isFinishedWorkStump(metadata) && isWorkStumpItemConversionTool(stack, world, x, y, z)) {
			world.playAuxSFX(BTWEffectManager.SHAFT_RIPPED_OFF_EFFECT_ID, x, y, z, 0);
			world.setBlockMetadataWithNotify(x, y, z, metadata & 7);
			return true;
		}
		
		int newMetadata = BTWBlocks.oakChewedLog.setIsStump(0);
		
		world.setBlockAndMetadataWithNotify(x, y, z, chewedLogs[metadata], newMetadata);
		
		if (!world.isRemote) {
			ItemUtils.ejectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(BTWItems.bark, 1, woodTypes[metadata & 7]), side);
		}
		
		return true;
	}
	
	@Override
	public ItemStack getStackRetrievedByBlockDispenser(World world, int x, int y, int z) {
		return Block.wood.getStackRetrievedByBlockDispenser(world, x, y, z);
	}
	
	//------------- Class Specific Methods ------------//
	
	private boolean isFinishedWorkStump(int metadata) {
		return (metadata & 8) == 0;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	public Icon[] sideIcons;
	@Environment(EnvType.CLIENT)
	public Icon[] topIcons;
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		sideIcons = new Icon[this.woodTypes.length];
		topIcons = new Icon[this.woodTypes.length];
		
		for (int i = 0; i < this.woodTypes.length; i++) {
			this.sideIcons[i] = register.registerIcon("decoBlockWorkStump" + WoodTypeHelper.woodNamesCapital[this.woodTypes[i]]);
			this.topIcons[i] = register.registerIcon("decoBlockTrunk" + WoodTypeHelper.woodNamesCapital[this.woodTypes[i]] + "_top");
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		int workStumpType = metadata & 7;
		
		if (side > 1) {
			return this.sideIcons[workStumpType];
		}
		else if (side == 0 || !this.isFinishedWorkStump(metadata)) {
			return this.topIcons[workStumpType];
		}
		else {
			return super.getIcon(side, metadata);
		}
	}
}

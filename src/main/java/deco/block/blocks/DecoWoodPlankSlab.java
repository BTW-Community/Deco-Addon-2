package deco.block.blocks;

import btw.block.blocks.PlanksBlock;
import btw.block.blocks.WoodSlabBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Icon;
import net.minecraft.src.ItemStack;

import java.util.List;
import java.util.Random;

public class DecoWoodPlankSlab extends WoodSlabBlock {
	private int[] woodTypes;
	private String[] names;
	
	private int singleSlabID;
	
	public DecoWoodPlankSlab(int blockID, int[] woodTypes, String[] names) {
		this(blockID, false, blockID, woodTypes, names);
	}
	
	public DecoWoodPlankSlab(int blockID, boolean isDoubleSlab, int singleSlabID, int[] woodTypes, String[] names) {
		super(blockID, isDoubleSlab);
		
		this.singleSlabID = singleSlabID;
		
		this.woodTypes = woodTypes;
		this.names = names;
	}
	
	@Override
	public int idDropped(int metadata, Random rand, int fortuneModifier) {
		return this.singleSlabID;
	}
	
	@Override
	public String getFullSlabName(int metadata) {
		return super.getUnlocalizedName() + "." + this.names[metadata];
	}
	
	@Override
	protected ItemStack createStackedBlock(int metadata) {
		return new ItemStack(this.singleSlabID, 2, metadata & 7);
	}
	
	@Override
	public int getFurnaceBurnTime(int itemDamage) {
		int burnTime = PlanksBlock.getFurnaceBurnTimeByWoodType(this.woodTypes[itemDamage]);
		
		if (!isDoubleSlab) {
			burnTime >>= 1;
		}
		
		return burnTime;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		return Block.planks.getIcon(side, this.woodTypes[metadata & 7]);
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs, List list) {
		if (!isDoubleSlab) {
			for (int i = 0; i < this.names.length; i++) {
				list.add(new ItemStack(blockID, 1, i));
			}
		}
	}
}

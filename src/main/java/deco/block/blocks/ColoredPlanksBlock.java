package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.util.Flammability;
import btw.crafting.util.FurnaceBurnTime;
import btw.item.BTWItems;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.World;

public class ColoredPlanksBlock extends ColoredBlock {
	public ColoredPlanksBlock(int blockID, String name) {
		super(blockID, BTWBlocks.plankMaterial, name);
		setAxesEffectiveOn();
		setHardness(1F);
		setResistance(5F);
		setFireProperties(Flammability.PLANKS);
		setBuoyant();
		setStepSound(soundWoodFootstep);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public int getHarvestToolLevel(IBlockAccess blockAccess, int x, int y, int z) {
		return 2;
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.sawDust.itemID, 2, 0, chanceOfDrop);
		return true;
	}
	
	@Override
	public int getFurnaceBurnTime(int itemDamage) {
		return FurnaceBurnTime.PLANKS_OAK.burnTime;
	}
}

package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.util.Flammability;
import btw.crafting.util.FurnaceBurnTime;
import btw.item.BTWItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class WorkbenchBlock extends Block {
	public WorkbenchBlock(int ID) {
		super(ID, BTWBlocks.plankMaterial);
		this.setHardness(1.5F);
		this.setBuoyant();
		this.setFurnaceBurnTime(FurnaceBurnTime.WOOD_BASED_BLOCK);
		this.setFireProperties(Flammability.PLANKS);
		this.setAxesEffectiveOn();
		this.setStepSound(soundWoodFootstep);
		this.setUnlocalizedName("workbench");
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		/*
		if (!world.isRemote) {
			player.displayGUIAnvil(x, y, z);
		}
		
		return true;
		*/
		return false;
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
	
	// ------------ Client Side Functionality ----------//
	
	@Environment(EnvType.CLIENT)
	private Icon workbenchIconTop;
	@Environment(EnvType.CLIENT)
	private Icon workbenchIconFront;
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getIcon(int side, int metadata) {
		return side == 1 ?
				this.workbenchIconTop :
				(side == 0 ?
						Block.planks.getBlockTextureFromSide(side) :
						(side != 2 && side != 4 ?
								this.blockIcon :
								this.workbenchIconFront));
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		this.blockIcon = register.registerIcon("workbench_side");
		this.workbenchIconTop = register.registerIcon("workbench_top");
		this.workbenchIconFront = register.registerIcon("workbench_front");
	}
}

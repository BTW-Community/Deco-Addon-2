package deco.block.blocks;

import btw.item.BTWItems;
import deco.block.DecoBlocks;
import net.minecraft.src.*;

import java.util.Random;

public class AshBlock extends Block {
	public AshBlock(int id) {
		super(id, DecoBlocks.ashMaterial);
		this.setShovelsEffectiveOn();
		this.setHardness(0.5F);
		this.setFilterableProperties(Item.FILTERABLE_FINE);
		this.setStepSound(Block.soundSandFootstep);
		this.setUnlocalizedName("decoBlockAsh");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.potash.itemID, 6, 0, chanceOfDrop);
		return true;
	}
	
	@Override
	public boolean canBePistonShoveled(World world, int x, int y, int z) {
		return true;
	}
	
	//CLIENT ONLY
	@Override
	public void registerIcons(IconRegister register) {
		this.blockIcon = register.registerIcon("fcBlockAshGroundCover");
	}
	
	@Override
	public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5) {
		super.randomDisplayTick(var1, var2, var3, var4, var5);
		
		if (var5.nextInt(10) == 0) {
			double var6 = (double) var3 + 1.125D;
			var1.spawnParticle("townaura", (double) var2 + var5.nextDouble(), var6, (double) var4 + var5.nextDouble(), 0.0D, 0.0D, 0.0D);
		}
	}
}

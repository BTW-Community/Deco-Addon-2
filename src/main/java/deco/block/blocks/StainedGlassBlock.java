package deco.block.blocks;

import deco.item.DecoItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class StainedGlassBlock extends ColoredBlock {
	private int item;
	
	public StainedGlassBlock(int blockID, String name, int item) {
		super(blockID, Material.glass, name);
		this.setHardness(0.3F);
		this.setStepSound(soundGlassFootstep);
		this.setUnlocalizedName("decoBlockStainedGlass");
		this.setPicksEffectiveOn();
		
		this.item = item;
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		player.addStat(StatList.mineBlockStatArray[this.blockID], 1);
		player.addHarvestBlockExhaustion(this.blockID, x, y, z, metadata);
		
		if (EnchantmentHelper.getSilkTouchModifier(player)) {
			ItemStack itemStack = new ItemStack(DecoItems.stainedGlass, 1, metadata);
			this.dropBlockAsItem(world, x, y, z, metadata, 0);
		}
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public int idDropped(int metadata, Random rand, int fortuneModifier) {
		return this.item;
	}
	
	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}
	
	@Override
	public boolean hasLargeCenterHardPointToFacing(IBlockAccess blockAccess, int x, int y, int z, int facing, boolean ignoreTransparency) {
		return ignoreTransparency;
	}
	
	@Override
	public boolean canRotateOnTurntable(IBlockAccess blockAccess, int x, int y, int z) {
		return true;
	}
	
	@Override
	public boolean canTransmitRotationHorizontallyOnTurntable(IBlockAccess blockAccess, int x, int y, int z) {
		return true;
	}
	
	@Override
	public boolean canTransmitRotationVerticallyOnTurntable(IBlockAccess blockAccess, int x, int y, int z) {
		return true;
	}
	
	//------------- Class Specific Methods ------------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public int getRenderBlockPass() {
		return 1;
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
}

package deco.block.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class ChandelierBlock extends Block {
	public ChandelierBlock(int ID) {
		super(ID, Material.iron);
		this.setUnlocalizedName("decoBlockChandelier");
		this.setStepSound(soundMetalFootstep);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setHardness(0.3F);
		this.setLightValue(1F);
		this.setPicksEffectiveOn();
		this.initBlockBounds(.25D, 0.125D, .25D, .75D, 1.0D, .75D);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean getCanBlockLightItemOnFire(IBlockAccess blockAccess, int x, int y, int z) {
		return true;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public int getRenderType() {
		return 1;
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		float height = .55F;
		float width = .15F;
		
		world.spawnParticle("smoke", x + width, y + height, z + width, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("flame", x + width, y + height, z + width, 0.0D, 0.0D, 0.0D);
		
		world.spawnParticle("smoke", x + 1F - width, y + height, z + width, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("flame", x + 1F - width, y + height, z + width, 0.0D, 0.0D, 0.0D);
		
		world.spawnParticle("smoke", x + width, y + height, z + 1F - width, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("flame", x + width, y + height, z + 1F - width, 0.0D, 0.0D, 0.0D);
		
		world.spawnParticle("smoke", x + 1F - width, y + height, z + 1F - width, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("flame", x + 1F - width, y + height, z + 1F - width, 0.0D, 0.0D, 0.0D);
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean renderBlock(RenderBlocks Render, int x, int y, int z) {
		Render.setRenderBounds(.375D, .875D, .375D, .625D, 1D, .625D);
		Render.renderStandardBlock(Block.stone, x, y, z);
		Render.renderCrossedSquares(this, x, y, z);
		return true;
	}
}

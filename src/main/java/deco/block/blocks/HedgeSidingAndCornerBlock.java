package deco.block.blocks;

import btw.block.BTWBlocks;
import btw.block.blocks.AshGroundCoverBlock;
import btw.block.blocks.SidingAndCornerAndDecorativeWallBlock;
import btw.block.util.Flammability;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class HedgeSidingAndCornerBlock extends SidingAndCornerAndDecorativeWallBlock {
	private final boolean shouldColor;
	private final Block referenceBlock;
	private final int referenceMetadata;
	
	public HedgeSidingAndCornerBlock(int blockID, String textureName, Block referenceBlock, int referenceMetadata, String name, boolean shouldColor) {
		super(blockID, referenceBlock.blockMaterial, textureName, referenceBlock.blockHardness, referenceBlock.blockResistance, referenceBlock.stepSound, name);
		this.referenceBlock = referenceBlock;
		this.referenceMetadata = referenceMetadata;
		this.shouldColor = shouldColor;
		this.setFireProperties(Flammability.LEAVES);
	}
	
	@Override
	public float getMovementModifier(World world, int x, int y, int z) {
		return 0.5F;
	}
	
	@Override
	public boolean getCanGrassGrowUnderBlock(World world, int x, int y, int z, boolean var5) {
		return true;
	}
	
	@Override
	public void onDestroyedByFire(World world, int x, int y, int z, int metadata, boolean var6) {
		super.onDestroyedByFire(world, x, y, z, metadata, var6);
		this.generateAshOnBurn(world, x, y, z);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	//------------- Class Specific Methods ------------//
	
	protected void generateAshOnBurn(World world, int x, int y, int z) {
		if (this.referenceBlock != BTWBlocks.bloodWoodLeaves) {
			for (int var5 = y; var5 > 0; --var5) {
				if (AshGroundCoverBlock.canAshReplaceBlock(world, x, var5, z)) {
					int var6 = world.getBlockId(x, var5 - 1, z);
					Block var7 = Block.blocksList[var6];
					
					if (var7 != null && var7.canGroundCoverRestOnBlock(world, x, var5 - 1, z)) {
						world.setBlockWithNotify(x, var5, z, BTWBlocks.ashCoverBlock.blockID);
						break;
					}
				}
				else if (world.getBlockId(x, var5, z) != Block.fire.blockID) {
					break;
				}
			}
		}
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean shouldRenderNeighborHalfSlabSide(IBlockAccess blockAccess, int x, int y, int z, int side, boolean isUpsideDown) {
		return true;
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean shouldRenderNeighborFullFaceSide(IBlockAccess blockAccess, int x, int y, int z, int side) {
		return true;
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		if (world.isRainingAtPos(x, y + 1, z) && !world.doesBlockHaveSolidTopSurface(x, y - 1, z) && rand.nextInt(15) == 1) {
			world.spawnParticle("dripWater", (double) x + rand.nextDouble(), (double) y - 0.05D, (double) z + rand.nextDouble(), 0.0D, 0.0D, 0.0D);
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public int getBlockColor() {
		if (!shouldColor) {
			return super.getBlockColor();
		}
		
		if (this.referenceBlock == BTWBlocks.bloodWoodLeaves) {
			return 14163743;
		}
		
		double var1 = 0.5D;
		double var3 = 1.0D;
		return ColorizerFoliage.getFoliageColor(var1, var3);
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public int getRenderColor(int metadata) {
		if (!shouldColor) {
			return super.getRenderColor(metadata);
		}
		
		if (this.referenceBlock == BTWBlocks.bloodWoodLeaves) {
			return 14163743;
		}
		
		return (metadata & 3) == 1 ? ColorizerFoliage.getFoliageColorPine() :
				((metadata & 3) == 2 ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
		if (!shouldColor) {
			return super.colorMultiplier(blockAccess, x, y, z);
		}
		
		if (this.referenceBlock == BTWBlocks.bloodWoodLeaves) {
			return 14163743;
		}
		
		if ((this.referenceMetadata & 3) == 1) {
			return ColorizerFoliage.getFoliageColorPine();
		}
		else if ((this.referenceMetadata & 3) == 2) {
			return ColorizerFoliage.getFoliageColorBirch();
		}
		else {
			int var6 = 0;
			int var7 = 0;
			int var8 = 0;
			
			for (int var9 = -1; var9 <= 1; ++var9) {
				for (int var10 = -1; var10 <= 1; ++var10) {
					int var11 = blockAccess.getBiomeGenForCoords(x + var10, z + var9).getBiomeFoliageColor();
					var6 += (var11 & 16711680) >> 16;
					var7 += (var11 & 65280) >> 8;
					var8 += var11 & 255;
				}
			}
			
			return (var6 / 9 & 255) << 16 | (var7 / 9 & 255) << 8 | var8 / 9 & 255;
		}
	}
}

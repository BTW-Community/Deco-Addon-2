package net.minecraft.src;

import java.util.Random;

import com.prupe.mcpatcher.mal.block.RenderBlocksUtils;

public class DecoBlockGrass extends FCBlockGrass {
    public static final int EDIBLE_LIGHT_LEVEL = 9;
    
	protected DecoBlockGrass(int id) {
		super(id);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!DecoBlockGrass.canGrassSurviveAtLocation(world, x, y, z)) {
			// convert back to dirt in low light
			world.setBlockWithNotify(x, y, z, Block.dirt.blockID);
		}
		else if (DecoBlockGrass.canGrassSpreadFromLocation(world, x, y, z)) {
			DecoBlockGrass.checkForGrassSpreadFromLocation(world, x, y, z);

			if (isSparse(world, x, y, z) && rand.nextInt(4) == 0) {
				this.setFullyGrown(world, x, y, z);
			}
		}
	}
	
	@Override
	public boolean CanBeGrazedOn(IBlockAccess blockAccess, int x, int y, int z, EntityAnimal animal) {
    	World world = (World) blockAccess;
    	int skylight = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
    	
        if (skylight >= EDIBLE_LIGHT_LEVEL) {
        	return super.CanBeGrazedOn(blockAccess, x, y, z, animal);
        }
        else {
        	return false;
        }
    }

	//------ Class specific methods ------//

	public static boolean canGrassSurviveAtLocation(World world, int x, int y, int z) {
		int blockAboveID = world.getBlockId(x, y + 1, z);
		Block blockAbove = Block.blocksList[blockAboveID];
		
		if (Block.lightOpacity[blockAboveID] > 2 || (blockAbove != null && !blockAbove.GetCanGrassGrowUnderBlock(world, x, y + 1, z, false)))
		{
			return false;
		}

		return true;
	}

	public static void checkForGrassSpreadFromLocation(World world, int x, int y, int z) {
		if (world.provider.dimensionId != 1 && !FCBlockGroundCover.IsGroundCoverRestingOnBlock(world, x, y, z)) {
			// check for grass spread

			int i = x + world.rand.nextInt(3) - 1;
			int j = y + world.rand.nextInt(4) - 2;
			int k = z + world.rand.nextInt(3) - 1;

			Block targetBlock = Block.blocksList[world.getBlockId(i, j, k)];

			if (targetBlock != null) {
				attempToSpreadGrassToLocation(world, i, j, k);
			}
		}
	}

	public static boolean attempToSpreadGrassToLocation(World world, int x, int y, int z) {
		int targetBlockID = world.getBlockId(x, y, z);
		Block targetBlock = Block.blocksList[targetBlockID];

		if (canGrassSurviveAtLocation(world, x, y, z)) {
			if (targetBlock.GetCanGrassSpreadToBlock(world, x, y, z) &&
					Block.lightOpacity[world.getBlockId(x, y + 1, z)] <= 2 &&
					!FCBlockGroundCover.IsGroundCoverRestingOnBlock(world, x, y, z))    		
			{
				return targetBlock.SpreadGrassToBlock(world, x, y, z);
			}
		}

		return false;
	}
    
    //----------- Client Side Functionality -----------//
    
    private Icon iconGrassTop;
	private Icon iconGrassTopSparse;
    private Icon iconGrassTopRough;
    
    public void registerIcons(IconRegister register) {
    	super.registerIcons(register);
    	
        this.iconGrassTop = register.registerIcon("grass_top");
		this.iconGrassTopSparse = register.registerIcon("fcBlockGrassSparse");
        this.iconGrassTopRough = register.registerIcon("decoBlockGrassRough");
    }

	public Icon getBlockTextureSecondPass(IBlockAccess blockAccess, int x, int y, int z, int side) {
		World world = null;
    	
    	if (blockAccess instanceof ChunkCache) {
    		ChunkCache chunkCache = (ChunkCache) blockAccess;
    		world = chunkCache.worldObj;
    	}
    	else if (blockAccess instanceof World) {
    		world = (World) blockAccess;
    	}
    	
    	int skylight;
    	
    	if (world != null)
    		skylight = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
    	else
    		skylight = 9;
    	
		Icon topIcon;

		if (isSparse(blockAccess, x, y, z)) {
			topIcon = iconGrassTopSparse;
		}
		else if (skylight >= this.EDIBLE_LIGHT_LEVEL) {
			topIcon = iconGrassTop;
		}
		else {
    		topIcon = iconGrassTopRough;
    	}

		Icon betterGrassIcon = RenderBlocksUtils.getGrassTexture(this, blockAccess, x, y, z, side, topIcon);

		if (betterGrassIcon != null) {
			return betterGrassIcon;
		}
		else if (side == 1) {
			return topIcon;
		}
		else {
			return super.getBlockTextureSecondPass(blockAccess, x, y, z, side);
		}
	}
}
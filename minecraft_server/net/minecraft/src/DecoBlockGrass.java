package net.minecraft.src;

import java.util.Random;

public class DecoBlockGrass extends FCBlockGrass {
    public static final int spreadFromLightLevel = 9;
    public static final int spreadToLightLevel = 4;
    public static final int surviveMinimumLightLevel = 4;
    public static final int edibleMinimumLightLevel = 9;
    
	protected DecoBlockGrass(int id) {
		super(id);
	}
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
    	int blockAboveID = world.getBlockId(x, y + 1, z);
    	Block blockAbove = Block.blocksList[blockAboveID];

    	int blockAboveLight = world.getBlockLightValue(x, y + 1, z);
    	
    	int blockAboveMaxNaturalLight = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
    	int blockAboveCurrentNaturalLight = blockAboveMaxNaturalLight - world.skylightSubtracted;
    	
        if (Block.lightOpacity[blockAboveID] > 2 || 
        		(blockAbove != null && !blockAbove.GetCanGrassGrowUnderBlock(world, x, y + 1, z, false)))
        {
            world.setBlockWithNotify(x, y, z, Block.dirt.blockID);
        }
        else if (blockAboveCurrentNaturalLight >= spreadFromLightLevel || blockAboveLight >= spreadFromLightLevel) {
        	checkForGrassSpreadFromLocation(world, x, y, z);
        }
    }
	
	@Override
	public boolean CanBeGrazedOn(IBlockAccess blockAccess, int x, int y, int z, EntityAnimal animal) {
    	World world = (World) blockAccess;
    	int skylight = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
        return skylight >= edibleMinimumLightLevel;
    }
	
    @Override
    public boolean ConvertBlock(ItemStack stack, World world, int x, int y, int z, int fromSide) {
    	world.setBlockWithNotify(x, y, z, FCBetterThanWolves.fcBlockDirtLoose.blockID);

    	if (!world.isRemote) {
            if (world.rand.nextInt(25) == 0) {
	            FCUtilsItem.EjectStackFromBlockTowardsFacing(world, x, y, z, new ItemStack(FCBetterThanWolves.fcItemHempSeeds), fromSide);
            }
		}
    	
    	return true;
    }
    
    //------------- Class Specific Methods ------------//    
    
    public static void checkForGrassSpreadFromLocation(World world, int x, int y, int z) {
    	if (world.provider.dimensionId != 1 && !FCBlockGroundCover.IsGroundCoverRestingOnBlock(world, x, y, z)) {
            int i = x + world.rand.nextInt(3) - 1;
            int j = y + world.rand.nextInt(5) - 3;
            int k = z + world.rand.nextInt(3) - 1;
                            
            Block targetBlock = Block.blocksList[world.getBlockId(i, j, k)];
            
            if (targetBlock != null) {
            	attempToSpreadGrassToBlock(targetBlock, world, i, j, k);
            }
    	}
    }
    
    public static boolean attempToSpreadGrassToBlock(Block block, World world, int x, int y, int z) {
    	if (block.GetCanGrassSpreadToBlock(world, x, y, z) &&
        	(world.GetBlockNaturalLightValueMaximum(x, y + 1, z) >= m_iGrassSpreadToLightLevel || world.getBlockLightValue(x, y + 1, z) >= m_iGrassSpreadToLightLevel)&& 
        	Block.lightOpacity[world.getBlockId(x, y + 1, z)] <= 2 &&
    		!FCBlockGroundCover.IsGroundCoverRestingOnBlock(world, x, y, z))
    	{
    		return block.SpreadGrassToBlock(world, x, y, z);
    	}
    	
    	return false;
    }
}
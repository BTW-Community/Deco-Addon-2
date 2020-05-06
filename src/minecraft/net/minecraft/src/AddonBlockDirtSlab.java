package net.minecraft.src;

import java.util.Random;

public class AddonBlockDirtSlab extends FCBlockDirtSlab {
	public AddonBlockDirtSlab(int id) {
		super(id);
	}

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        int idAbove = world.getBlockId(x, y + 1, z);
        Block blockAbove = Block.blocksList[idAbove];
        int lightValue = world.getBlockLightValue(x, y, z);
        int skylightBaseValue = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
        int skylightValue = skylightBaseValue - world.skylightSubtracted;

        if (Math.max(lightValue, skylightValue) >= 9 && Block.lightOpacity[idAbove] <= 2 && (blockAbove == null || blockAbove.GetCanGrassGrowUnderBlock(world, x, y + 1, z, false)))
        {
            world.setBlockMetadataWithNotify(x, y, z, 0);
            
            if (skylightValue >= 11)
            {
                CheckForGrassSpreadFromLocation(world, x, y, z);
            }
        }
        else
        {
        	if (blockAbove == null || blockAbove.GetCanGrassGrowUnderBlock(world, x, y + 1, z, false))
        		world.setBlockMetadataWithNotify(x, y, z, 1);
        	else
        		world.setBlockWithNotify(x, y, z, Block.dirt.blockID);
        }
    }

    public static void CheckForGrassSpreadFromLocation(World world, int x, int y, int z)
    {
        if (world.provider.dimensionId != 1 && !FCBlockGroundCover.IsGroundCoverRestingOnBlock(world, x, y, z))
        {
            int var4 = x + world.rand.nextInt(3) - 1;
            int var5 = y + world.rand.nextInt(5) - 3;
            int var6 = z + world.rand.nextInt(3) - 1;
            Block var7 = Block.blocksList[world.getBlockId(var4, var5, var6)];

            if (var7 != null)
            {
            	if (var7 instanceof FCBlockDirt)
            		((AddonBlockDirt) var7).AttempToSpreadGrassToBlock(world, var4, var5, var6);
            	else if (var7 instanceof FCBlockDirtSlab)
            		((AddonBlockDirtSlab) var7).AttempToSpreadGrassToBlock(world, var4, var5, var6);
            	if (var7 instanceof FCBlockDirtLoose)
            		((AddonBlockDirtLoose) var7).AttempToSpreadGrassToBlock(world, var4, var5, var6);
            	else if (var7 instanceof FCBlockDirtLooseSlab)
            		((AddonBlockDirtLooseSlab) var7).AttempToSpreadGrassToBlock(world, var4, var5, var6);
            	else
            		var7.AttempToSpreadGrassToBlock(world, var4, var5, var6);
            }
        }
    }
}
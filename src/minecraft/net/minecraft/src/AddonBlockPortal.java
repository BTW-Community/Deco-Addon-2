package net.minecraft.src;

public class AddonBlockPortal extends FCBlockPortal {
	private static final int maxPortalWidth = 23;
	private static final int maxPortalHeight = 23;
	
	public AddonBlockPortal(int id) {
		super(id);
	}

	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5) {
		
	}
	
	@Override
	public boolean tryToCreatePortal(World world, int x, int y, int z) {
		//Used to find bounds of the portal
		int xDistPos = 0;
		int xDistNeg = 0;
		int zDistPos = 0;
		int zDistNeg = 0;
		
		//Checks if there is obsidian on the same level on the x or z axis from it within the max portal width of the chosen location
		for (int i = 0; i < maxPortalWidth - 1; i++) {
			if (world.getBlockId(x + i, y, z) == Block.obsidian.blockID && xDistPos == 0) {
				xDistPos = i;
			}
			if (world.getBlockId(x - i, y, z) == Block.obsidian.blockID && xDistNeg == 0) {
				xDistNeg = -i;
			}
			if (world.getBlockId(x, y, z + i) == Block.obsidian.blockID && zDistPos == 0) {
				zDistPos = i;
			}
			if (world.getBlockId(x, y, z - i) == Block.obsidian.blockID && zDistNeg == 0) {
				zDistNeg = -i;
			}
		}
		
		int xDiff = xDistPos - xDistNeg + 1;
		int zDiff = zDistPos - zDistNeg + 1;
		
		//If obsidian is too far or too close (or doesn't exist) in both x and z, stop attempt
		if ((xDiff < 4 && zDiff < 4) || (xDiff > maxPortalWidth && zDiff > maxPortalWidth) || (xDiff > maxPortalWidth && zDiff < 4) || (zDiff > maxPortalWidth && xDiff < 4))
			return false;
		
		int isX = 0;
		int isZ = 0;
		
		//Finds which direction has the smallest portal horizontally, favoring x if they are the same
		if (xDiff >= zDiff) {
			zDistPos = 0;
			zDistNeg = 0;
			isX = 1;
		}
		else if (zDiff > xDiff) {
			xDistPos = 0;
			xDistNeg = 0;
			isZ = 1;
		}
		
		int yDist = 0;
		
		//Starts searching above starting position for a block of obsidian within min and max portal size
		for (int i = 3; i < maxPortalHeight - 1; i++) {
			if (world.getBlockId(x,  y + i,  z) == Block.obsidian.blockID ) {
				yDist = i + 1;
				break;
			}
		}
		
		//If no obsidian is found above the starting location
		if (yDist == 0)
			return false;

		int lowerBound = xDistNeg + zDistNeg;
		int upperBound = xDistPos + zDistPos;
		
		for (int i = lowerBound; i <= upperBound; i++) {
			for (int j = -1; j < yDist; j++) {
				int id = world.getBlockId(x + isX * i, y + j, z + isZ * i);
				System.out.println(id == 0 ? "air" : Block.blocksList[id].getUnlocalizedName());
				//Ignores corners
				if ((i != lowerBound && i != upperBound) || (j != -1 && j != yDist - 1)) {
					
					//If it's on the border
					if (i == lowerBound || i == upperBound || j == -1 || j == yDist - 1) {
						
						if (id != Block.obsidian.blockID)
                        {
                            return false;
                        }
					}
					//Checks to make sure no blocks are in frame except air and firestarting blocks, otherwise fails
                    else if (!world.isAirBlock(x + isX * i, y + j, z + isZ * i) && id != Block.fire.blockID && id != FCBetterThanWolves.fcBlockCampfireLarge.blockID && id != FCBetterThanWolves.fcBlockCampfireMedium.blockID && id != FCBetterThanWolves.fcBlockCampfireSmall.blockID && id != FCBetterThanWolves.fcBlockCampfireUnlit.blockID)
                    {
                        return false;
                    }
				}
			}
		}

		//Fills frame with portal blocks 
        for (int i = lowerBound + 1; i < upperBound; i++)
        {
            for (int j = 0; j < yDist - 1; j++)
            {
                world.setBlock(x + isX * i, y + j, z + isZ * i, Block.portal.blockID, 0, 2);
            }
        }

        FCUtilsWorld.GameProgressSetNetherBeenAccessedServerOnly();
        return true;
	}
}
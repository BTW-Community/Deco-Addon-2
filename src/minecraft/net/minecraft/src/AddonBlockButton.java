package net.minecraft.src;

import java.util.Random;

public class AddonBlockButton extends FCBlockButton {
	private static int[] metaForFacing = {0, 5, 4, 3, 2, 1};
	
	protected AddonBlockButton(int id, boolean isWood) {
		super(id, isWood);
	}

    /**
     * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
     */
	@Override
    public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5)
    {
    	return true;
    }

    public int GetFacing(int var1)
    {
        return metaForFacing[var1 & 7];
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
	@Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
		boolean canPlace = false;
		
		for (int i = 0; i < 6; i++) {
			FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z);
			blockPos.AddFacingAsOffset(i);
			if (FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, blockPos.i, blockPos.j, blockPos.k, Facing.oppositeSide[i]))
				canPlace = true;
		}
		
		return canPlace;
    }

    /**
     * This method is redundant, check it out...
     */
    private boolean redundantCanPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        if (!this.canPlaceBlockAt(par1World, par2, par3, par4))
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockToAir(par2, par3, par4);
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborID)
    {
        if (this.redundantCanPlaceBlockAt(world, x, y, z))
        {
    		boolean onValidBlock = false;
    		
    		int meta = world.getBlockMetadata(x, y, z);
    		
    		for (int i = 0; i < 6; i++) {
    			FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z);
    			blockPos.AddFacingAsOffset(i);
    			if (FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, blockPos.i, blockPos.j, blockPos.k, Facing.oppositeSide[i]) && (meta & 7) == metaForFacing[Facing.oppositeSide[i]])
    				onValidBlock = true;
    		}

            if (!onValidBlock)
            {
                this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
                world.setBlockToAir(x, y, z);
            }
        }
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
	@Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
        int var10 = world.getBlockMetadata(x, y, z);
        int var11 = var10 & 8;
        var10 &= 7;

        if (side == 0 && FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x, y + 1, z, 0))
        {
            var10 = 0;
        }
        else if (side == 1 && FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x, y - 1, z, 1))
        {
            var10 = 5;
        }
        else if (side == 2 && FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x, y, z + 1, 2))
        {
            var10 = 4;
        }
        else if (side == 3 && FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x, y, z - 1, 3))
        {
            var10 = 3;
        }
        else if (side == 4 && FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x + 1, y, z, 4))
        {
            var10 = 2;
        }
        else if (side == 5 && FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(world, x - 1, y, z, 5))
        {
            var10 = 1;
        }
        
        System.out.println(var10 + var11);
        return var10 + var11;
    }
    
    @Override
    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
    	int meta = blockAccess.getBlockMetadata(x, y, z);
    	
    	double minW = .3125;
    	double maxW = .6875;
    	double minH = .375;
    	double maxH = .625;
    	double maxL = .125;
    	
    	//If the button is on make it smaller
    	if ((meta & 8) > 1)
    		maxL = .0625;
    	
    	switch (meta & 7) {
    	default:
    	case 0:
    		return new AxisAlignedBB(minW, 1-maxL, minH, maxW, 1, maxH);
    	case 1:
    		return new AxisAlignedBB(0, minH, minW, maxL, maxH, maxW);
    	case 2:
    		return new AxisAlignedBB(1-maxL, minH, minW, 1, maxH, maxW);
    	case 3:
    		return new AxisAlignedBB(minW, minH, 0, maxW, maxH, maxL);
    	case 4:
    		return new AxisAlignedBB(minW, minH, 1-maxL, maxW, maxH, 1);
    	case 5:
    		return new AxisAlignedBB(minW, 0, minH, maxW, maxL, maxH);
    	}
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        int var10 = par1World.getBlockMetadata(par2, par3, par4);
        int var11 = var10 & 7;
        int var12 = 8 - (var10 & 8);

        if (var12 == 0)
        {
            return true;
        }
        else
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, var11 + var12, 3);
            par1World.markBlockRangeForRenderUpdate(par2, par3, par4, par2, par3, par4);
            par1World.playSoundEffect((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "random.click", 0.3F, 0.6F);
            this.func_82536_d(par1World, par2, par3, par4, var11);
            par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World));
            return true;
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote)
        {
            int var6 = world.getBlockMetadata(x, y, z);

            if ((var6 & 8) != 0)
            {
                world.setBlockMetadataWithNotify(x, y, z, var6 & 7, 3);
                int var7 = var6 & 7;
                this.func_82536_d(world, x, y, z, var7);
                world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "random.click", 0.3F, 0.5F);
                world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
            }
        }
        
    	super.updateTick(world, x, y, z, rand);
    }

    //Provides update when button is activated
    private void func_82536_d(World world, int x, int y, int z, int meta)
    {
        world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);

        if (meta == 1)
        {
            world.notifyBlocksOfNeighborChange(x - 1, y, z, this.blockID);
        }
        else if (meta == 2)
        {
            world.notifyBlocksOfNeighborChange(x + 1, y, z, this.blockID);
        }
        else if (meta == 3)
        {
            world.notifyBlocksOfNeighborChange(x, y, z - 1, this.blockID);
        }
        else if (meta == 4)
        {
            world.notifyBlocksOfNeighborChange(x, y, z + 1, this.blockID);
        }
        else if (meta == 5)
        {
            world.notifyBlocksOfNeighborChange(x, y - 1, z, this.blockID);
        }
        else
        {
            world.notifyBlocksOfNeighborChange(x, y + 1, z, this.blockID);
        }
    }

    /**
     * Returns true if the block is emitting direct/strong redstone power on the specified side. Args: World, X, Y, Z,
     * side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingStrongPower(IBlockAccess blockAccess, int x, int y, int z, int sideReversed)
    {
        int meta = blockAccess.getBlockMetadata(x, y, z);

        if ((meta & 8) == 0)
        {
            return 0;
        }
        else
        {
        	FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z);
        	blockPos.AddFacingAsOffset(Facing.oppositeSide[sideReversed]);
            return blockAccess.isBlockNormalCube(blockPos.i, blockPos.j, blockPos.k) && (meta & 7) == metaForFacing[sideReversed] ? 15 : 0;
        }
    }
}

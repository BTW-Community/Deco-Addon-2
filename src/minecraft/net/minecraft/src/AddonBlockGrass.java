package net.minecraft.src;

import java.util.Random;

public class AddonBlockGrass extends FCBlockGrass {
    private boolean m_bTempHasSnowOnTop;
    private Icon[] iconGrassTop = new Icon[2];
    private Icon iconSnowSide;
    private Icon iconGrassSideOverlay;
    
	public AddonBlockGrass(int id) {
		super(id);
	}

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        int var6 = world.getBlockId(x, y + 1, z);
        Block var7 = Block.blocksList[var6];
        int var8 = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
        int var9 = var8 - world.skylightSubtracted;

        if (var8 >= 9 && Block.lightOpacity[var6] <= 2 && (var7 == null || var7.GetCanGrassGrowUnderBlock(world, x, y + 1, z, false)))
        {
            world.setBlockMetadataWithNotify(x, y, z, 0);
            
            if (var9 >= 11)
            {
                CheckForGrassSpreadFromLocation(world, x, y, z);
            }
        }
        else
        {
        	if (var7 == null || var7.GetCanGrassGrowUnderBlock(world, x, y + 1, z, false))
        		world.setBlockMetadataWithNotify(x, y, z, 1);
        	else
        		world.setBlockWithNotify(x, y, z, Block.dirt.blockID);
        }
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World world, int x, int y, int z) {
    	int var6 = world.getBlockId(x, y + 1, z);
        int var8 = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
        int var9 = var8 - world.skylightSubtracted;

        if (!(var8 >= 9 && Block.lightOpacity[var6] <= 2))
        {
            world.setBlockMetadataWithNotify(x, y, z, 1);
        }
    }

    public boolean CanBeGrazedOn(IBlockAccess blockAccess, int x, int y, int z, EntityAnimal animal)
    {
    	int meta = blockAccess.getBlockMetadata(x, y, z);
        return meta == 0;
    }
    
    //CLIENT ONLY
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister register)
    {
        super.registerIcons(register);
        this.iconGrassTop[0] = register.registerIcon("grass_top");
        this.iconGrassTop[1] = register.registerIcon("ginger_grassRough");
        this.iconSnowSide = register.registerIcon("snow_side");
        this.iconGrassSideOverlay = register.registerIcon("grass_side_overlay");
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
    	int meta = blockAccess.getBlockMetadata(x, y, z);
        return side == 1 ? this.iconGrassTop[meta] : (side == 0 ? Block.dirt.getBlockTextureFromSide(side) : (this.m_bTempHasSnowOnTop ? this.iconSnowSide : this.blockIcon));
    }
}

package net.minecraft.src;

import java.util.Random;

public class DecoBlockGrass extends FCBlockGrass {
	public DecoBlockGrass(int var1) {
		super(var1);
	}

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if (!world.isRemote)
        {
        	Block blockAbove = Block.blocksList[world.getBlockId(x, y + 1, z)];
        	
            if ((world.getBlockLightValue(x, y + 1, z) < 4 && Block.lightOpacity[world.getBlockId(x, y + 1, z)] > 2) || (blockAbove != null && !blockAbove.GetCanGrassGrowUnderBlock(world, x, y + 1, z, false)))
            {
                world.setBlock(x, y, z, Block.dirt.blockID);
            }
            else if ((world.getBlockLightValue(x, y + 1, z) >= 9 || world.GetBlockNaturalLightValue(x, y + 1, z) >= 9) && (blockAbove == null || blockAbove.GetCanGrassGrowUnderBlock(world, x, y + 1, z, false)))
            {
                CheckForGrassSpreadFromLocation(world, x, y, z);
            }
        }
    }

    public boolean CanBeGrazedOn(IBlockAccess blockAccess, int x, int y, int z, EntityAnimal animal)
    {
    	World world = (World) blockAccess;
    	int skylight = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
        return skylight >= 9;
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        var2.setBlockWithNotify(var3, var4, var5, FCBetterThanWolves.fcBlockDirtLoose.blockID);
        
        if (!var2.isRemote)
        {
            if (var2.rand.nextInt(25) == 0)
            {
                FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemHempSeeds), var6);
            }
        }
        
        return true;
    }
    
    //CLIENT ONLY
    private boolean m_bTempHasSnowOnTop;
    private Icon iconGrassTop;
    private Icon iconGrassTopRough;
    private Icon iconSnowSide;
    private Icon iconGrassSideOverlay;
    
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.iconGrassTop = var1.registerIcon("grass_top");
        this.iconGrassTopRough = var1.registerIcon("decoBlockGrassRough");
        this.iconSnowSide = var1.registerIcon("snow_side");
        this.iconGrassSideOverlay = var1.registerIcon("grass_side_overlay");
    }
    
    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
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
    	
        return side == 1 ? (skylight >= 9 ? this.iconGrassTop : this.iconGrassTopRough) : this.getIcon(side, world.getBlockMetadata(x,  y,  z));
    }
}
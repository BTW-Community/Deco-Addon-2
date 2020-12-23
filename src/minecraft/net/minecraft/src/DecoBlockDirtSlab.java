package net.minecraft.src;

import java.util.Random;

import net.minecraft.client.Minecraft;

public class DecoBlockDirtSlab extends FCBlockDirtSlab {
	public DecoBlockDirtSlab(int id) {
		super(id);
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
            	this.SetSubtype(world, x, y, z, 0);
            }
            else if ((world.getBlockLightValue(x, y + 1, z) >= 9 || world.GetBlockNaturalLightValue(x, y + 1, z) >= 9) && (blockAbove == null || blockAbove.GetCanGrassGrowUnderBlock(world, x, y + 1, z, false)))
            {
                DecoBlockGrass.CheckForGrassSpreadFromLocation(world, x, y, z);
            }
        }
    }

    public boolean AttempToSpreadGrassToBlock(World var1, int var2, int var3, int var4)
    {
        if (this.GetCanGrassSpreadToBlock(var1, var2, var3, var4) && var1.getBlockLightValue(var2, var3 + 1, var4) >= 11 && lightOpacity[var1.getBlockId(var2, var3 + 1, var4)] <= 2 && !FCBlockGroundCover.IsGroundCoverRestingOnBlock(var1, var2, var3, var4))
        	return this.SpreadGrassToBlock(var1, var2, var3, var4);
        return false;
    }
    
    //CLIENT ONLY
    private Icon iconGrassTopRough;
    
    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
    	World world = null;
    	
    	if (blockAccess instanceof ChunkCache) {
    		ChunkCache chunkCache = (ChunkCache) blockAccess;
    		world = DecoManager.getWorldFromChunkCache(chunkCache);
    	}
    	else if (blockAccess instanceof World) {
    		world = (World) blockAccess;
    	}
    	
    	int skylight;
    	
    	if (world != null)
    		skylight = world.GetBlockNaturalLightValueMaximum(x, y + 1, z);
    	else
    		skylight = 9;
    	
        return side == 1 ? (skylight < 9 && this.GetSubtype(blockAccess.getBlockMetadata(x, y, z)) == this.m_iSubtypeGrass ? this.iconGrassTopRough : super.getBlockTexture(blockAccess, x, y, z, side)) : super.getBlockTexture(blockAccess, x, y, z, side);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
    	super.registerIcons(var1);
        this.iconGrassTopRough = var1.registerIcon("decoBlockGrassRough");
    }
}
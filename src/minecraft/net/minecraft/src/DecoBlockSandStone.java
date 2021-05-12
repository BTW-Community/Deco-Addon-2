package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockSandStone extends FCBlockSandStone {
    public static final int typeDefault = 0;
    public static final int typeChiseled = 1;
    public static final int typeSmooth = 2;
    public static final int typePolished = 3;
    public static final int typeBrick = 4;
    public static final int typeMossy = 5;
    public static final int typeLargeBrick = 6;
    public static final int typeLargeBrickMossy = 7;
    public static final int typeCracked = 8;
    public static final int typeLargeBrickCracked = 9;
    
	public DecoBlockSandStone(int id) {
		super(id);
        this.setTickRandomly(true);
	}

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 6));
        par3List.add(new ItemStack(par1, 1, 7));
        par3List.add(new ItemStack(par1, 1, 8));
        par3List.add(new ItemStack(par1, 1, 9));
    }
	
	public void updateTick(World world, int x, int y, int z, Random rand)
    {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta == typeDefault && !world.getBlockMaterial(x, y - 1, z).blocksMovement())
        {
            int idAbove = world.getBlockId(x, y + 1, z);

            if (idAbove != Block.waterMoving.blockID && idAbove != Block.waterStill.blockID)
            {
                if ((idAbove == Block.lavaMoving.blockID || idAbove == Block.lavaStill.blockID) && rand.nextInt(15) == 0)
                {
                	world.setBlockMetadataWithNotify(x, y, z, typeCracked);
                    world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
                }
            }
            else if (rand.nextInt(15) == 0)
            {
            	world.setBlockMetadataWithNotify(x, y, z, typeMossy);
                world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
            }
        }

        if (meta == typeLargeBrick && !world.getBlockMaterial(x, y - 1, z).blocksMovement())
        {
            int idAbove = world.getBlockId(x, y + 1, z);

            if (idAbove != Block.waterMoving.blockID && idAbove != Block.waterStill.blockID)
            {
                if ((idAbove == Block.lavaMoving.blockID || idAbove == Block.lavaStill.blockID) && rand.nextInt(15) == 0)
                {
                	world.setBlockMetadataWithNotify(x, y, z, typeLargeBrickCracked);
                    world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
                }
            }
            else if (rand.nextInt(15) == 0)
            {
            	world.setBlockMetadataWithNotify(x, y, z, typeLargeBrickMossy);
                world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
            }
        }
    }

	@Override
    public int getItemIDDroppedOnStonecutter(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        
        switch (meta) {
        default:
        case typeDefault:
        	return FCBetterThanWolves.fcBlockSandstoneSidingAndCorner.blockID;
        case typeChiseled:
        case typeSmooth:
        	return DecoDefs.sandStoneSmoothSidingAndCorner.blockID;
        case typePolished:
        	return DecoDefs.sandStonePolishedSidingAndCorner.blockID;
        case typeBrick:
        	return DecoDefs.sandStoneBrickSidingAndCorner.blockID;
        case typeMossy:
        	return DecoDefs.sandStoneMossySidingAndCorner.blockID;
        case typeLargeBrick:
        	return DecoDefs.sandStoneBrickLargeSidingAndCorner.blockID;
        case typeLargeBrickMossy:
        	return DecoDefs.sandStoneBrickLargeMossySidingAndCorner.blockID;
        case typeCracked:
        	return DecoDefs.sandStoneCrackedSidingAndCorner.blockID;
        case typeLargeBrickCracked:
        	return DecoDefs.sandStoneBrickLargeCrackedSidingAndCorner.blockID;
        }
    }

	@Override
    public int getItemCountDroppedOnStonecutter(World world, int x, int y, int z) {
        return 2;
    }

    //CLIENT ONLY
    public static final String[] SAND_STONE_TYPES = new String[] {"default", "chiseled", "smooth", "polished", "brick", "mossy", "largeBrick", "largeBrickMossy", "cracked", "largeBrickCracked"};
    private static final String[] textures = new String[] {"sandstone_side", "sandstone_carved", "sandstone_smooth", "sandstone_top", "decoBlockSandstoneBrick", "decoBlockSandstoneMossy_side", "decoBlockSandstoneBrickLarge", "decoBlockSandstoneBrickLargeMossy", "sandstone_bottom", "decoBlockSandstoneBrickLargeCracked"};
    private Icon[] sideIcons;
    private Icon iconTop;
    private Icon iconBottom;
    private Icon iconTopMossy;
    private Icon iconBottomMossy;
    
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int side, int meta)
    {
    	if (meta == 3) {
    		return iconTop;
    	}
    	
    	if (meta == 4 || meta == 6 || meta == 7 || meta == 8 || meta == 9) {
            return this.sideIcons[meta];
    	}
    	
        if (side != 1 && (side != 0 || meta != 1 && meta != 2))
        {
            if (side == 0)
            {
            	if (meta == 5)
            		return this.iconBottomMossy;
                return this.iconBottom;
            }
            else
            {
                if (meta < 0 || meta >= this.sideIcons.length)
                {
                    meta = 0;
                }

                return this.sideIcons[meta];
            }
        }
        else
        {
        	if (meta == 5)
        		return this.iconTopMossy;
            return this.iconTop;
        }
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.sideIcons = new Icon[textures.length];

        for (int var2 = 0; var2 < this.sideIcons.length; ++var2)
        {
            this.sideIcons[var2] = par1IconRegister.registerIcon(textures[var2]);
        }

        this.iconTop = par1IconRegister.registerIcon("sandstone_top");
        this.iconBottom = par1IconRegister.registerIcon("sandstone_bottom");
        this.iconTopMossy = par1IconRegister.registerIcon("decoBlockSandstoneMossy_top");
        this.iconBottomMossy = par1IconRegister.registerIcon("decoBlockSandstoneMossy_bottom");
    }
}

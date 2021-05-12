package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockRedSandStone extends Block
{
    public static final String[] SAND_STONE_TYPES = new String[] {"default", "chiseled", "smooth", "polished", "brick", "mossy", "largeBrick", "largeBrickMossy", "cracked", "largeBrickCracked"};
    private static final String[] textures = new String[] {"decoBlockRedSandstone_side", "decoBlockRedSandstoneChiseled", "decoBlockRedSandstoneSmooth", "decoBlockRedSandstone_top", "decoBlockRedSandstoneBrick", "decoBlockRedSandstoneMossy_side", 
    														"decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstone_bottom", "decoBlockRedSandstoneBrickLargeCracked"};
    
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
    
    private Icon[] sideIcons;
    private Icon iconTop;
    private Icon iconBottom;
    private Icon iconTopMossy;
    private Icon iconBottomMossy;

    public DecoBlockRedSandStone(int par1)
    {
        super(par1, Material.rock);
        this.SetPicksEffectiveOn();
        this.setHardness(1.5F);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("decoBlockRedSandStone");
        this.setCreativeTab(CreativeTabs.tabBlock);
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

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 3;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileSand.itemID, 16, 0, var6);
        return true;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }

	@Override
    public int getItemIDDroppedOnStonecutter(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        
        switch (meta) {
        default:
        case typeDefault:
        	return DecoDefs.redSandStoneSidingAndCorner.blockID;
        case typeChiseled:
        case typeSmooth:
        	return DecoDefs.redSandStoneSmoothSidingAndCorner.blockID;
        case typePolished:
        	return DecoDefs.redSandStonePolishedSidingAndCorner.blockID;
        case typeBrick:
        	return DecoDefs.redSandStoneBrickSidingAndCorner.blockID;
        case typeMossy:
        	return DecoDefs.redSandStoneMossySidingAndCorner.blockID;
        case typeLargeBrick:
        	return DecoDefs.redSandStoneBrickLargeSidingAndCorner.blockID;
        case typeLargeBrickMossy:
        	return DecoDefs.redSandStoneBrickLargeMossySidingAndCorner.blockID;
        case typeCracked:
        	return DecoDefs.redSandStoneCrackedSidingAndCorner.blockID;
        case typeLargeBrickCracked:
        	return DecoDefs.redSandStoneBrickLargeCrackedSidingAndCorner.blockID;
        }
    }

	@Override
    public int getItemCountDroppedOnStonecutter(World world, int x, int y, int z) {
        return 2;
    }
}
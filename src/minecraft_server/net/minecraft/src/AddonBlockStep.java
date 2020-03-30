package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class AddonBlockStep extends BlockHalfSlab
{
    /** The list of the types of step blocks. */
    public static final String[] blockStepTypes = new String[] {"redSandstone"};
    private Icon theIcon;

    public AddonBlockStep(int par1, boolean par2)
    {
        super(par1, par2, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }
    
    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4) {
    	return 3;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return AddonDefs.stoneSingleSlab.blockID;
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(AddonDefs.stoneSingleSlab.blockID, 2, par1 & 7);
    }

    /**
     * Takes a block ID, returns true if it's the same as the ID for a stone or wooden single slab.
     */
    private static boolean isBlockSingleSlab(int par0)
    {
        return par0 == AddonDefs.stoneSingleSlab.blockID;
    }

    /**
     * Returns the slab block name with step type.
     */
    public String getFullSlabName(int par1)
    {
        if (par1 < 0 || par1 >= blockStepTypes.length)
        {
            par1 = 0;
        }

        return super.getUnlocalizedName() + "." + blockStepTypes[par1];
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        if (par1 != AddonDefs.stoneDoubleSlab.blockID)
        {
            for (int var4 = 0; var4 <= 7; ++var4)
            {
                if (var4 != 2)
                {
                    par3List.add(new ItemStack(par1, 1, var4));
                }
            }
        }
    }
}

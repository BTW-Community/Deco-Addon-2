package net.minecraft.src;

import java.util.List;

public class DecoBlockWoodSlabReplace extends FCBlockWoodSlab {
    public static final String[] subtypes = new String[] {"oak", "spruce", "birch", "jungle", "blood", "cherry", "acacia"};
    
	public DecoBlockWoodSlabReplace(int id, boolean isDouble) {
		super(id, isDouble);
	}

    /**
     * Returns the slab block name with step type.
     */
    public String getFullSlabName(int var1)
    {
        return this.getUnlocalizedName() + "." + subtypes[var1];
    }

	//CLIENT ONLY
    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        if (var1 != Block.woodDoubleSlab.blockID)
        {
            for (int var4 = 0; var4 < subtypes.length; ++var4)
            {
                var3.add(new ItemStack(var1, 1, var4));
            }
        }
    }
}
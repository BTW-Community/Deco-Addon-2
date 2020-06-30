package net.minecraft.src;

import java.util.List;

public class AddonBlockObsidian extends FCBlockObsidian {
	public AddonBlockObsidian(int id) {
		super(id);
	}

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
    }
    
    @Override
    public int damageDropped(int meta) {
    	return meta;
    }
}
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
	
	//CLIENT ONLY
    private Icon[] icons = new Icon[2];
    
    @Override
    public Icon getIcon(int side, int meta) {
    	return icons[meta];
    }
    
    @Override
    public void registerIcons(IconRegister register) {
    	icons[0] = register.registerIcon("obsidian");
    	icons[1] = register.registerIcon("ginger_infusedObsidian");
    }
}
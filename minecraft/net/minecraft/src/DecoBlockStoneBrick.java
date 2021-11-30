package net.minecraft.src;

import java.util.List;

public class DecoBlockStoneBrick extends FCBlockStoneBrick {
	public DecoBlockStoneBrick(int id) {
		super(id);
	}

	//CLIENT ONLY
    public static final String[] textureStrings = new String[] {"stonebricksmooth", "stonebricksmooth_mossy", "stonebricksmooth_cracked", "stonebricksmooth_carved"};
    private Icon[] icons;
    
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        if (par2 < 0 || par2 >= textureStrings.length)
        {
            par2 = 0;
        }

        return this.icons[par2];
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 0; var4 < 4; ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.icons = new Icon[textureStrings.length];

        for (int var2 = 0; var2 < this.icons.length; ++var2)
        {
            this.icons[var2] = par1IconRegister.registerIcon(textureStrings[var2]);
        }
    }
}
package net.minecraft.src;

import java.util.Random;

public class AddonBlockLogBark extends FCBlockLog {

	public AddonBlockLogBark(int ID) {
		super(ID);
		AddonManager.Register(this, new String[] {"barkOak", "barkSpruce", "barkBirch", "barkJungle"}, new String[] {"Oak Wood", "Spruce Wood", "Birch Wood", "Jungle Wood"});
	}
	
	@Override
	public boolean GetIsStump(IBlockAccess access, int x, int y, int z) {
		return false;
	}

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return AddonDefs.barkLog.blockID;
    }

	//CLIENT ONLY
    public static final String[] textureTypes = new String[] {"tree_side", "tree_spruce", "tree_birch", "tree_jungle"};
    private Icon[] icons;
	
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        int var3 = par2 & 12;
        int var4 = par2 & 3;
        return icons[var4];
    }
    
    @Override 
    public void registerIcons(IconRegister Register)
    {
    	icons = new Icon[textureTypes.length];
    	for (int i = 0; i < icons.length; i++) {
    		icons[i] = Register.registerIcon(textureTypes[i]);
    	}
    }
}

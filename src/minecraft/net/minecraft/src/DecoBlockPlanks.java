package net.minecraft.src;

import java.util.List;

public class DecoBlockPlanks extends FCBlockPlanks {
    public static final String[] m_sWoodTypes = new String[] {"oak", "spruce", "birch", "jungle", "blood", "cherry", "acacia"};
    
	public DecoBlockPlanks(int id) {
		super(id);
	}

    public static int GetFurnaceBurnTimeByWoodType(int var0)
    {
        return (var0 == 0 || var0 == 5 || var0 == 6) ? FCEnumFurnaceBurnTime.PLANKS_OAK.m_iBurnTime : (var0 == 1 ? FCEnumFurnaceBurnTime.PLANKS_SPRUCE.m_iBurnTime : (var0 == 2 ? FCEnumFurnaceBurnTime.PLANKS_BIRCH.m_iBurnTime : (var0 == 3 ? FCEnumFurnaceBurnTime.PLANKS_JUNGLE.m_iBurnTime : FCEnumFurnaceBurnTime.PLANKS_BLOOD.m_iBurnTime)));
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < m_sWoodTypes.length; ++var4)
        {
            var3.add(new ItemStack(var1, 1, var4));
        }
    }

	//CLIENT ONLY
    public static final String[] m_sWoodTextureTypes = new String[] {"wood", "wood_spruce", "wood_birch", "wood_jungle", "fcBlockPlanks_blood", "decoBlockPlanksCherry", "decoBlockPlanksAcacia"};
    private Icon[] m_iconArray;
	
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return this.m_iconArray[var2];
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.m_iconArray = new Icon[m_sWoodTextureTypes.length];

        for (int var2 = 0; var2 < this.m_iconArray.length; ++var2)
        {
            this.m_iconArray[var2] = var1.registerIcon(m_sWoodTextureTypes[var2]);
        }
    }
}

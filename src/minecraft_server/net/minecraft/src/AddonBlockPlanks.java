package net.minecraft.src;

import java.util.List;

public class AddonBlockPlanks extends FCBlockPlanks {
    public static final String[] m_sWoodTypes = new String[] {"oak", "spruce", "birch", "jungle", "blood", "cherry"};
    
	public AddonBlockPlanks(int id) {
		super(id);
	}

    public static int GetFurnaceBurnTimeByWoodType(int var0)
    {
        return (var0 == 0 || var0 == 5) ? FCEnumFurnaceBurnTime.PLANKS_OAK.m_iBurnTime : (var0 == 1 ? FCEnumFurnaceBurnTime.PLANKS_SPRUCE.m_iBurnTime : (var0 == 2 ? FCEnumFurnaceBurnTime.PLANKS_BIRCH.m_iBurnTime : (var0 == 3 ? FCEnumFurnaceBurnTime.PLANKS_JUNGLE.m_iBurnTime : FCEnumFurnaceBurnTime.PLANKS_BLOOD.m_iBurnTime)));
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
}

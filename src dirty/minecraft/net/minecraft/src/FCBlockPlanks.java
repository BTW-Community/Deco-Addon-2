package net.minecraft.src;

import java.util.List;

public class FCBlockPlanks extends Block
{
    public static final String[] m_sWoodTypes = new String[] {"oak", "spruce", "birch", "jungle", "blood"};
    public static final String[] m_sWoodTextureTypes = new String[] {"wood", "wood_spruce", "wood_birch", "wood_jungle", "fcBlockPlanks_blood"};
    private Icon[] m_iconArray;

    public FCBlockPlanks(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialPlanks);
        this.SetAxesEffectiveOn();
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.SetBuoyant();
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("wood");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return var1;
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return FCBetterThanWolves.fcBlockWoodSidingItemStubID;
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public int GetItemDamageDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        var1.getBlockMetadata(var2, var3, var4);
        return var1.getBlockMetadata(var2, var3, var4);
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 2, 0, var6);
        return true;
    }

    public int GetFurnaceBurnTime(int var1)
    {
        return GetFurnaceBurnTimeByWoodType(var1);
    }

    public static int GetFurnaceBurnTimeByWoodType(int var0)
    {
        return var0 == 0 ? FCEnumFurnaceBurnTime.PLANKS_OAK.m_iBurnTime : (var0 == 1 ? FCEnumFurnaceBurnTime.PLANKS_SPRUCE.m_iBurnTime : (var0 == 2 ? FCEnumFurnaceBurnTime.PLANKS_BIRCH.m_iBurnTime : (var0 == 3 ? FCEnumFurnaceBurnTime.PLANKS_JUNGLE.m_iBurnTime : FCEnumFurnaceBurnTime.PLANKS_BLOOD.m_iBurnTime)));
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return this.m_iconArray[var2];
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

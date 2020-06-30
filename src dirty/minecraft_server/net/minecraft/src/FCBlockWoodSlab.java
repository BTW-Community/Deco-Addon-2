package net.minecraft.src;

import java.util.Random;

public class FCBlockWoodSlab extends BlockHalfSlab
{
    public static final String[] m_sWoodType = new String[] {"oak", "spruce", "birch", "jungle", "blood"};

    public FCBlockWoodSlab(int var1, boolean var2)
    {
        super(var1, var2, FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetAxesEffectiveOn();
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("woodSlab");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Block.woodSingleSlab.blockID;
    }

    /**
     * Returns the slab block name with step type.
     */
    public String getFullSlabName(int var1)
    {
        return super.getUnlocalizedName() + "." + m_sWoodType[var1];
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int var1)
    {
        return new ItemStack(Block.woodSingleSlab.blockID, 2, var1 & 7);
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        int var7 = this.isDoubleSlab ? 2 : 1;

        for (int var8 = 0; var8 < var7; ++var8)
        {
            this.dropBlockAsItem_do(var1, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemSawDust));
        }
    }

    public int GetFurnaceBurnTime(int var1)
    {
        int var2 = FCBlockPlanks.GetFurnaceBurnTimeByWoodType(var1);

        if (!this.isDoubleSlab)
        {
            var2 >>= 1;
        }

        return var2;
    }
}

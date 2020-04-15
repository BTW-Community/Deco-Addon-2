package net.minecraft.src;

import java.util.List;

public class AddonBlockRedSandStone extends Block
{
    public static final String[] SAND_STONE_TYPES = new String[] {"default", "chiseled", "smooth", "polished", "brick"};
    private static final String[] field_94405_b = new String[] {"ginger_redSandstone_side", "ginger_redSandstone_carved", "ginger_redSandstone_smooth", "ginger_redSandstone_top", "ginger_redSandstone_brick"};
    private Icon[] field_94406_c;
    private Icon field_94403_cO;
    private Icon field_94404_cP;

    public AddonBlockRedSandStone(int par1)
    {
        super(par1, Material.rock);
        this.SetPicksEffectiveOn();
        this.setHardness(1.5F);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("redSandStone");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 3;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileSand.itemID, 16, 0, var6);
        return true;
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
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
    }
}
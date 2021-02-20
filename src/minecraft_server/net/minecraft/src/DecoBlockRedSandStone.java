package net.minecraft.src;

import java.util.List;

public class DecoBlockRedSandStone extends Block
{
    public static final String[] SAND_STONE_TYPES = new String[] {"default", "chiseled", "smooth", "polished", "brick", "mossy", "largeBrick", "largeBrickMossy", "cracked", "largeBrickCracked"};
    private static final String[] textures = new String[] {"decoBlockRedSandstone_side", "decoBlockRedSandstoneChiseled", "decoBlockRedSandstoneSmooth", "decoBlockRedSandstone_top", "decoBlockRedSandstoneBrick", "decoBlockRedSandstoneMossy_side", 
    														"decoBlockRedSandstoneBrickLarge", "decoBlockRedSandstoneBrickLargeMossy", "decoBlockRedSandstone_bottom", "decoBlockRedSandstoneBrickLargeCracked"};
    private Icon[] sideIcons;
    private Icon iconTop;
    private Icon iconBottom;
    private Icon iconTopMossy;
    private Icon iconBottomMossy;

    public DecoBlockRedSandStone(int par1)
    {
        super(par1, Material.rock);
        this.SetPicksEffectiveOn();
        this.setHardness(1.5F);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("decoBlockRedSandStone");
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setTickRandomly(true);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
        par3List.add(new ItemStack(par1, 1, 4));
        par3List.add(new ItemStack(par1, 1, 5));
        par3List.add(new ItemStack(par1, 1, 6));
        par3List.add(new ItemStack(par1, 1, 7));
        par3List.add(new ItemStack(par1, 1, 8));
        par3List.add(new ItemStack(par1, 1, 9));
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
}
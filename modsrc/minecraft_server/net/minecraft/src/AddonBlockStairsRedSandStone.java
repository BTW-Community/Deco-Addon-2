package net.minecraft.src;

public class AddonBlockStairsRedSandStone extends FCBlockStairs
{
    protected AddonBlockStairsRedSandStone(int var1)
    {
        super(var1, AddonDefs.redSandStone, 0);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("redSandStoneStairs");
        this.SetPicksEffectiveOn();
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 3;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, AddonDefs.pileRedSand.itemID, 12, 0, var6);
        return true;
    }
}
package net.minecraft.src;

import java.util.Random;

public class FCBlockStairsCobblestone extends FCBlockStairs
{
    protected FCBlockStairsCobblestone(int var1)
    {
        super(var1, Block.cobblestone, 0);
        this.SetPicksEffectiveOn();
        this.setUnlocalizedName("stairsStone");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcBlockCobblestoneLooseStairs.blockID;
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        this.dropBlockAsItem(var1, var3, var4, var5, var6, 0);
    }

    public boolean HasMortar(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }
}

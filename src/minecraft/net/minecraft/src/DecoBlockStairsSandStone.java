package net.minecraft.src;

public class DecoBlockStairsSandStone extends FCBlockStairs
{
    protected DecoBlockStairsSandStone(int var1, Block owner, int ownerMeta)
    {
        super(var1, owner, ownerMeta);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.SetPicksEffectiveOn();
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 3;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileSand.itemID, 12, 0, var6);
        return true;
    }
}
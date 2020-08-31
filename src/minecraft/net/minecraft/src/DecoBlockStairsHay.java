package net.minecraft.src;

public class DecoBlockStairsHay extends FCBlockStairs {
    protected DecoBlockStairsHay(int var1)
    {
        super(var1, DecoDefs.hayBale, 0);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("hayBaleStairs");
		this.SetAxesEffectiveOn(true);
		DecoManager.Register(this);
		DecoManager.Name(this, "Hay Stairs");
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemWheat.itemID, 6, 0, var6);
        return true;
    }
}
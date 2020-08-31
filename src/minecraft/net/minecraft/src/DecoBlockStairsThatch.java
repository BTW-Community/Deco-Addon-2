package net.minecraft.src;

public class DecoBlockStairsThatch extends FCBlockStairs {
    protected DecoBlockStairsThatch(int var1)
    {
        super(var1, DecoDefs.thatch, 0);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("thatchStairs");
		this.SetAxesEffectiveOn(true);
		DecoManager.Register(this);
		DecoManager.Name(this, "Thatch Stairs");
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemStraw.itemID, 6, 0, var6);
        return true;
    }
}
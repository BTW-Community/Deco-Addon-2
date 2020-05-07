package net.minecraft.src;

public class AddonBlockStairsThatch extends FCBlockStairs {
    protected AddonBlockStairsThatch(int var1)
    {
        super(var1, AddonDefs.thatch, 0);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("thatchStairs");
		this.SetAxesEffectiveOn(true);
		AddonManager.Register(this);
		AddonManager.Name(this, "Thatch Stairs");
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemStraw.itemID, 6, 0, var6);
        return true;
    }
}
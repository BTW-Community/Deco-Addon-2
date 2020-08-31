package net.minecraft.src;

public class DecoBlockDirt extends FCBlockDirt {

	public DecoBlockDirt(int var1) {
		super(var1);
	}

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        var2.setBlockWithNotify(var3, var4, var5, FCBetterThanWolves.fcBlockDirtLoose.blockID);

        return true;
    }

    public boolean AttempToSpreadGrassToBlock(World var1, int var2, int var3, int var4)
    {
        if (this.GetCanGrassSpreadToBlock(var1, var2, var3, var4) && var1.getBlockLightValue(var2, var3 + 1, var4) >= 11 && lightOpacity[var1.getBlockId(var2, var3 + 1, var4)] <= 2 && !FCBlockGroundCover.IsGroundCoverRestingOnBlock(var1, var2, var3, var4))
        	return this.SpreadGrassToBlock(var1, var2, var3, var4);
        return false;
    }
}
package net.minecraft.src;

public class AddonBlockDirtSlab extends FCBlockDirtSlab {
	public AddonBlockDirtSlab(int id) {
		super(id);
	}

    public boolean AttempToSpreadGrassToBlock(World var1, int var2, int var3, int var4)
    {
        if (this.GetCanGrassSpreadToBlock(var1, var2, var3, var4) && var1.getBlockLightValue(var2, var3 + 1, var4) >= 11 && lightOpacity[var1.getBlockId(var2, var3 + 1, var4)] <= 2 && !FCBlockGroundCover.IsGroundCoverRestingOnBlock(var1, var2, var3, var4))
        	return this.SpreadGrassToBlock(var1, var2, var3, var4);
        return false;
    }
}
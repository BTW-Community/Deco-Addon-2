package net.minecraft.src;

public class AddonBlockGrass extends FCBlockGrass {
	protected AddonBlockGrass(int var1) {
		super(var1);
	}

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        var2.setBlockWithNotify(var3, var4, var5, FCBetterThanWolves.fcBlockDirtLoose.blockID);
        
        return true;
    }
}
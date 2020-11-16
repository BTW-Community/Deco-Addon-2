package net.minecraft.src;

public class DecoBlockDirtLooseSlab extends FCBlockDirtLooseSlab {
	public DecoBlockDirtLooseSlab(int id) {
		super(id);
	}

    public boolean AttempToSpreadGrassToBlock(World var1, int var2, int var3, int var4)
    {
    	System.out.println("Grass spread attempt");
        if (this.GetCanGrassSpreadToBlock(var1, var2, var3, var4) && var1.getBlockLightValue(var2, var3 + 1, var4) >= 11 && lightOpacity[var1.getBlockId(var2, var3 + 1, var4)] <= 2 && !FCBlockGroundCover.IsGroundCoverRestingOnBlock(var1, var2, var3, var4)) {
        	System.out.println("Grass spread success");
            var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDirtSlab.blockID, 2);
            return true;
        }
        return false;
    }
}
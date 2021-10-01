package net.minecraft.src;

public class DecoBlockLogBloodReplace extends FCBlockBloodWood {
	public DecoBlockLogBloodReplace(int id) {
		super(id);
        this.SetChiselsEffectiveOn(true);
	}

	public boolean CanConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5)
	{
		return true;
	}

	public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
	{
		int var7 = var2.getBlockMetadata(var3, var4, var5);
		byte var8 = 0;
		int var10;

		int var9 = var7 >> 2 & 3;
		var10 = FCBetterThanWolves.fcBlockLogDamaged.SetOrientation(var8, var9);

		var2.setBlockAndMetadataWithNotify(var3, var4, var5, DecoDefs.logDamagedBlood.blockID, var10);

		if (!var2.isRemote)
		{
			FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemBark, 1, 4), var6);
		}

		return true;
	}
}
package net.minecraft.src;

public class DecoBlockWorkStump extends FCBlockWorkStump {
	protected DecoBlockWorkStump(int id) {
		super(id);
	}

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
    	int meta = var1.getBlockMetadata(var2, var3, var4);
    	if (meta == 4)
    		return DecoDefs.cherryLog.GetStackRetrievedByBlockDispenser(var1, var2, var3, var4);
    	else if (meta == 5)
    		return DecoDefs.acaciaLog.GetStackRetrievedByBlockDispenser(var1, var2, var3, var4);
    	else
    		return Block.wood.GetStackRetrievedByBlockDispenser(var1, var2, var3, var4);
    }

    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        int meta = var2.getBlockMetadata(var3, var4, var5);
        int var8 = FCBetterThanWolves.fcBlockLogDamaged.SetIsStump(0);
        var2.setBlockAndMetadataWithNotify(var3, var4, var5, DecoUtilsBlock.getDamagedLogFromMetadata(meta & 3), var8);

        if (!var2.isRemote)
        {
        	if (meta == 4) meta++;
            FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemBark, 1, meta), var6);
        }

        return true;
    }
}
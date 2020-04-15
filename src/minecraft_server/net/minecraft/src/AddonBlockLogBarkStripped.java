package net.minecraft.src;

import java.util.Random;

public class AddonBlockLogBarkStripped extends FCBlockLog {

	public AddonBlockLogBarkStripped(int ID) {
		super(ID);
		AddonManager.Register(this, new String[] {"barkOakStripped", "barkSpruceStripped", "barkBirchStripped", "barkJungleStripped"}, new String[] {"Stripped Oak Wood", "Stripped Spruce Wood", "Stripped Birch Wood", "Stripped Jungle Wood"});
	}
	
	@Override
	public boolean GetIsStump(IBlockAccess access, int x, int y, int z) {
		return false;
	}

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return AddonDefs.strippedLog.blockID;
    }

	//Removes bark drop from chisel use
	@Override
    public boolean ConvertBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        int var7 = var2.getBlockMetadata(var3, var4, var5);
        byte var8 = 0;
        int var10;

        if (this.GetIsStump(var7))
        {
            if (this.IsWorkStumpItemConversionTool(var1, var2, var3, var4, var5))
            {
                var2.playAuxSFX(2268, var3, var4, var5, 0);
                var2.setBlockAndMetadataWithNotify(var3, var4, var5, FCBetterThanWolves.fcBlockWorkStump.blockID, var7 & 3);
                return true;
            }

            var10 = FCBetterThanWolves.fcBlockLogDamaged.SetIsStump(var8);
        }
        else
        {
            int var9 = var7 >> 2 & 3;
            var10 = FCBetterThanWolves.fcBlockLogDamaged.SetOrientation(var8, var9);
        }

        var2.setBlockAndMetadataWithNotify(var3, var4, var5, FCBetterThanWolves.fcBlockLogDamaged.blockID, var10);

        return true;
    }
}

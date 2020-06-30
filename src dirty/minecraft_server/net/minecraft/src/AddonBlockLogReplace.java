package net.minecraft.src;

import java.util.List;

public class AddonBlockLogReplace extends FCBlockLog {
	private String[] topTextureTypes = {"tree_top", "ginger_logSpruceTop", "ginger_logBirchTop", "ginger_logJungleTop"};
	private String[] trunkTopTextureTypes = {"fcBlockTrunkTop", "ginger_trunkSpruceTop", "ginger_trunkBirchTop", "ginger_trunkJungleTop"};
	
	public AddonBlockLogReplace(int ID) {
		super(ID);
	}

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

        var2.setBlockAndMetadataWithNotify(var3, var4, var5, AddonUtilsBlock.getDamagedLogFromMetadata(var7 & 3), var10);

        if (!var2.isRemote)
        {
            FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemBark, 1, var7 & 3), var6);
        }

        return true;
    }
}

package net.minecraft.src;

import java.util.List;

public class DecoBlockLogReplace extends FCBlockLog {
	private String[] topTextureTypes = {"tree_top", "ginger_logSpruceTop", "ginger_logBirchTop", "ginger_logJungleTop"};
	private String[] trunkTopTextureTypes = {"fcBlockTrunkTop", "ginger_trunkSpruceTop", "ginger_trunkBirchTop", "ginger_trunkJungleTop"};
	
	public DecoBlockLogReplace(int ID) {
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

        var2.setBlockAndMetadataWithNotify(var3, var4, var5, DecoUtilsBlock.getDamagedLogFromMetadata(var7 & 3), var10);

        if (!var2.isRemote)
        {
            FCUtilsItem.EjectStackFromBlockTowardsFacing(var2, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcItemBark, 1, var7 & 3), var6);
        }

        return true;
    }
	
	//CLIENT ONLY
	private Icon[] topIcons;
	private Icon[] trunkTopIcons;
    
    @Override public Icon getIcon(int var1, int var2)
    {
        int var3 = var2 & 12;
        int var4 = var2 & 3;
        
        return (var2 & 12) == 12 ? (var1 > 1 ? super.getIcon(var1, var2) : this.trunkTopIcons[var4]) : var3 == 0 && (var1 == 1 || var1 == 0) ? this.topIcons[var4] : (var3 == 4 && (var1 == 5 || var1 == 4) ? this.topIcons[var4] : (var3 == 8 && (var1 == 2 || var1 == 3) ? this.topIcons[var4] : super.getIcon(var1, var2)));
    }
    
    @Override 
    public void registerIcons(IconRegister Register)
    {
    	super.registerIcons(Register);
    	
    	topIcons = new Icon[topTextureTypes.length];
    	trunkTopIcons = new Icon[topTextureTypes.length];

    	for (int i = 0; i < topIcons.length; i++) {
    		topIcons[i] = Register.registerIcon(topTextureTypes[i]);
    		trunkTopIcons[i] = Register.registerIcon(trunkTopTextureTypes[i]);
    	}
    }
}

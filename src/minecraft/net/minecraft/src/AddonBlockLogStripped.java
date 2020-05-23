package net.minecraft.src;

import java.util.Random;

public class AddonBlockLogStripped extends FCBlockLog {

	public AddonBlockLogStripped(int ID) {
		super(ID);
		AddonManager.Register(this, new String[] {"strippedOak", "strippedSpruce", "strippedBirch", "strippedJungle"}, new String[] {"Stripped Oak Log", "Stripped Spruce Log", "Stripped Birch Log", "Stripped Jungle Log"});
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

	@Override
    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return Block.planks.blockID;
    }

	@Override
    public int GetItemDamageDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
		int metadata = var1.getBlockMetadata(var2, var3, var4);
        return this.limitToValidMetadata(metadata);
    }

	@Override
    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 4;
    }

	public boolean OnBlockSawed(World world, int x, int y, int z)
	{
		for (int i = 0; i < 2; ++i) {
			FCUtilsItem.EjectSingleItemWithRandomOffset(world, x, y, z, FCBetterThanWolves.fcItemSawDust.itemID, 0);
		}
		
		return super.OnBlockSawed(world, x, y, z);
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

        var2.setBlockAndMetadataWithNotify(var3, var4, var5, AddonUtilsBlock.getDamagedLogFromMetadata(var7 & 3), var10);

        return true;
    }

	//CLIENT ONLY
	public static final String[] topTextureTypes = {"ginger_strippedOakTop", "ginger_strippedSpruceTop", "ginger_strippedBirchTop", "ginger_strippedJungleTop"};
    public static final String[] sideTextureTypes = new String[] {"ginger_strippedOakSide", "ginger_strippedSpruceSide", "ginger_strippedBirchSide", "ginger_strippedJungleSide"};
    private Icon[] iconsSide;
	private Icon[] iconsTop;
    
	/*
    @Override public Icon getIcon(int side, int metadata)
    {
        int rotation = metadata & 12 >> 2;
        int type = metadata & 3;
        
        switch (rotation) {
        case 0:
        	if (side > 1)
        		return iconsSide[type];
        	else
        		return iconsTop[type];
        case 1:
        	if (side >= 2 && side < 4)
        		return iconsSide[type];
        	else
        		return iconsTop[type];
        case 2:
        	if (side < 4)
        		return iconsSide[type];
        	else
        		return iconsTop[type];
        default:
        	System.out.println(rotation);
        	return iconsSide[0];
        }
    }*/
	
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        int var3 = par2 & 12;
        int var4 = par2 & 3;
        return var3 == 0 && (par1 == 1 || par1 == 0) ? this.iconsTop[var4] : (var3 == 4 && (par1 == 5 || par1 == 4) ? this.iconsTop[var4] : (var3 == 8 && (par1 == 2 || par1 == 3) ? this.iconsTop[var4] : this.iconsSide[var4]));
    }
    
    @Override 
    public void registerIcons(IconRegister Register)
    {
    	iconsSide = new Icon[sideTextureTypes.length];
    	for (int i = 0; i < iconsSide.length; i++) {
    		iconsSide[i] = Register.registerIcon(sideTextureTypes[i]);
    	}
    	
    	iconsTop = new Icon[topTextureTypes.length];
    	for (int i = 0; i < iconsTop.length; i++) {
    		iconsTop[i] = Register.registerIcon(topTextureTypes[i]);
    	}
    }
}

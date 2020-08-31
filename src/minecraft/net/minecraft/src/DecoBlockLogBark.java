package net.minecraft.src;

import java.util.Random;

public class DecoBlockLogBark extends FCBlockLog {

	public DecoBlockLogBark(int ID) {
		super(ID);
		DecoManager.Register(this, new String[] {"barkOak", "barkSpruce", "barkBirch", "barkJungle"}, new String[] {"Oak Wood", "Spruce Wood", "Birch Wood", "Jungle Wood"});
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
        return DecoDefs.barkLog.blockID;
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
		int metadata = world.getBlockMetadata(x, y, z);
		
		for (int i = 0; i < 2; ++i) {
			FCUtilsItem.EjectSingleItemWithRandomOffset(world, x, y, z, FCBetterThanWolves.fcItemSawDust.itemID, 0);
		}
		
		FCUtilsItem.EjectSingleItemWithRandomOffset(world, x, y, z, FCBetterThanWolves.fcItemBark.itemID, this.limitToValidMetadata(metadata));
		
		return super.OnBlockSawed(world, x, y, z);
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
    public static final String[] textureTypes = new String[] {"tree_side", "tree_spruce", "tree_birch", "tree_jungle"};
    private Icon[] icons;
	
    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        int var3 = par2 & 12;
        int var4 = par2 & 3;
        return icons[var4];
    }
    
    @Override 
    public void registerIcons(IconRegister Register)
    {
    	icons = new Icon[textureTypes.length];
    	for (int i = 0; i < icons.length; i++) {
    		icons[i] = Register.registerIcon(textureTypes[i]);
    	}
    }
}

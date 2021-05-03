package net.minecraft.src;

import java.util.Random;

public class DecoBlockNetherBrick extends Block {
	public static final int typeRed = 0;
	public static final int typeRedChiseled = 1;
	public static final int typeChiseled = 2;
	
    public DecoBlockNetherBrick(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialNetherRock);
        this.setHardness(2.0F);
        this.setResistance(10.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("decoBlockNetherBrick");
        this.setCreativeTab(CreativeTabs.tabBlock);
		DecoManager.Register(this, new String[] {"red",  "redChiseled", "chiseled"});
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        //super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);

        if (!var1.isRemote)
        {
        	Block drop = DecoDefs.netherBrickRedLoose;
        	
        	switch (var5) {
        	case 0:
        	case 1:
        		drop = DecoDefs.netherBrickRedLoose;
        		break;
        	case 2:
        		drop = FCBetterThanWolves.fcBlockNetherBrickLoose;
        		break;
        	}
        	
            this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(drop));
        }
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        this.dropBlockAsItem(var1, var3, var4, var5, var6, 0);
    }

    public boolean HasMortar(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

	@Override
    public int getItemIDDroppedOnStonecutter(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        
        switch (meta) {
        default:
        	return DecoDefs.netherBrickRedSidingAndCorner.blockID;
        case typeChiseled:
        	return FCBetterThanWolves.fcBlockNetherBrickSidingAndCorner.blockID;
        }
    }

	@Override
    public int getItemCountDroppedOnStonecutter(World world, int x, int y, int z) {
        return 2;
    }
    
    //CLIENT ONLY
    private Icon[] icons;
    
    public Icon getIcon(int side, int meta) {
    	return icons[meta];
    }
    
    public void registerIcons(IconRegister register) {
    	icons = new Icon[3];
    	
    	icons[0] = register.registerIcon("decoBlockNetherBrickRed");
    	icons[1] = register.registerIcon("decoBlockNetherBrickRedChiseled");
    	icons[2] = register.registerIcon("decoBlockNetherBrickChiseled");
    }
}
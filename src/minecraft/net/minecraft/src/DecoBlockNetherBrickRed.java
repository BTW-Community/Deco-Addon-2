package net.minecraft.src;

import java.util.Random;

public class DecoBlockNetherBrickRed extends Block {
    public DecoBlockNetherBrickRed(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialNetherRock);
        this.setHardness(2.0F);
        this.setResistance(10.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("netherBrickVariant");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        //super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);

        if (!var1.isRemote)
        {
        	Block drop = DecoDefs.netherBrickLoose;
        	
        	switch (var5) {
        	case 0:
        	case 1:
        		drop = DecoDefs.netherBrickLoose;
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
    
    //CLIENT ONLY
    private Icon[] icons;
    
    public Icon getIcon(int side, int meta) {
    	return icons[meta];
    }
    
    public void registerIcons(IconRegister register) {
    	icons = new Icon[3];
    	
    	icons[0] = register.registerIcon("ginger_netherBrickRed");
    	icons[1] = register.registerIcon("ginger_netherBrickRedChiseled");
    	icons[2] = register.registerIcon("ginger_netherBrickChiseled");
    }
}
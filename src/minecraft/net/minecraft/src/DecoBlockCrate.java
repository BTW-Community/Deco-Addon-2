package net.minecraft.src;

import java.util.List;

public class DecoBlockCrate extends Block {
	public DecoBlockCrate(int id) {
		super(id, FCBetterThanWolves.fcMaterialPlanks);
        this.SetAxesEffectiveOn();
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.SetBuoyant();
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("decoBlockCrate");
        this.setCreativeTab(CreativeTabs.tabBlock);
	}

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 2, 0, var6);
        return true;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < 6; ++var4)
        {
            var3.add(new ItemStack(var1, 1, var4));
        }
    }
    
    public int damageDropped(int meta) {
    	return meta;
    }
    
    //CLIENT ONLY
    private Icon[] icons;
    
    @Override
    public Icon getIcon(int side, int meta) {
    	return icons[meta];
    }
    
    @Override
    public void registerIcons(IconRegister register) {
    	icons = new Icon[6];
    	
    	icons[0] = register.registerIcon("decoBlockCrateOak");
    	icons[1] = register.registerIcon("decoBlockCrateSpruce");
    	icons[2] = register.registerIcon("decoBlockCrateBirch");
    	icons[3] = register.registerIcon("decoBlockCrateJungle");
    	icons[4] = register.registerIcon("decoBlockCrateBlood");
    	icons[5] = register.registerIcon("decoBlockCrateCherry");
    }
}
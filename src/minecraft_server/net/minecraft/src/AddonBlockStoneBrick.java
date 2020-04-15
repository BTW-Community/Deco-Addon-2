package net.minecraft.src;

import java.util.List;

public class AddonBlockStoneBrick extends Block {
	public AddonBlockStoneBrick(int ID) {
		super(ID, Material.rock);
        this.setHardness(2.25F);
        this.setResistance(10.0F);
        this.SetPicksEffectiveOn();
        this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("addonStone");
		this.setCreativeTab(CreativeTabs.tabBlock);

		AddonManager.Register(this, new String[] {"graniteBrick", "andesiteBrick", "dioriteBrick"}, new String[] {"Granite Bricks", "Andesite Bricks", "Diorite Bricks"});
	}

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        //super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);

        if (!var1.isRemote)
        {
        	Block drop = AddonDefs.graniteStoneBrickLoose;
        	
        	switch (var5) {
        	case 0:
        		drop = AddonDefs.graniteStoneBrickLoose;
        		break;
        	case 1:
        		drop = AddonDefs.andesiteStoneBrickLoose;
        		break;
        	case 2:
        		drop = AddonDefs.dioriteStoneBrickLoose;
        		break;
        	}
        	
            this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(drop));
        }
    }

    public boolean HasMortar(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }
}

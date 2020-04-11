package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class AddonBlockCobble extends Block {
	public AddonBlockCobble(int ID) {
		super(ID, Material.rock);
		this.setHardness(2.25F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.SetChiselsEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("addonStone");
		this.setCreativeTab(CreativeTabs.tabBlock);

		AddonManager.Register(this, new String[] {"graniteCobble", "andesiteCobble", "dioriteCobble"}, new String[] {"Granite Cobblestone", "Andesite Cobblestone", "Diorite Cobblestone"});
	}

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        //super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);

        if (!var1.isRemote)
        {
        	Block drop = AddonDefs.graniteCobbleLoose;
        	
        	switch (var5) {
        	case 0:
        		drop = AddonDefs.graniteCobbleLoose;
        		break;
        	case 1:
        		drop = AddonDefs.andesiteCobbleLoose;
        		break;
        	case 2:
        		drop = AddonDefs.dioriteCobbleLoose;
        		break;
        	}
        	
            this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(drop));
        }
    }

    public boolean HasMortar(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }
	
	//CLIENT ONLY METHODS
	public static Icon[] Icons = new Icon[3];
	public Icon getIcon(int Side, int Meta)
	{
		return Icons[Meta];
	}
	public void registerIcons(IconRegister Register)
	{
		Icons[0] = Register.registerIcon("ginger_graniteCobble");
		Icons[1] = Register.registerIcon("ginger_andesiteCobble");
		Icons[2] = Register.registerIcon("ginger_dioriteCobble");
	}
	//
}

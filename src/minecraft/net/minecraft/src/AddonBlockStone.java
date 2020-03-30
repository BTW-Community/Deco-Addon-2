package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class AddonBlockStone extends Block {
	public AddonBlockStone(int ID) {
		super(ID, Material.rock);
		this.setHardness(2.25F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.SetChiselsEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("addonStone");
		this.setCreativeTab(CreativeTabs.tabBlock);

		AddonManager.Register(this, new String[] {"granite", "andesite", "diorite"}, new String[] {"Granite", "Andesite", "Diorite"});
	}
	
	public int idDropped(int id) {
		return AddonDefs.stoneTypesCobble.blockID;
	}
	
	public int damageDropped(int meta) {
		return meta;
	}

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        var3.add(new ItemStack(var1, 1, 0));
        var3.add(new ItemStack(var1, 1, 1));
        var3.add(new ItemStack(var1, 1, 2));
    }
	
	//CLIENT ONLY METHODS
	public static Icon[] Icons = new Icon[3];
	public Icon getIcon(int Side, int Meta)
	{
		return Icons[Meta];
	}
	public void registerIcons(IconRegister Register)
	{
		Icons[0] = Register.registerIcon("ginger_granite");
		Icons[1] = Register.registerIcon("ginger_andesite");
		Icons[2] = Register.registerIcon("ginger_diorite");
	}
	//
}

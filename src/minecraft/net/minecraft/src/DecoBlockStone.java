package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockStone extends Block {
	public DecoBlockStone(int ID) {
		super(ID, Material.rock);
		this.setHardness(2.25F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.SetChiselsEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("addonStone");
		this.setCreativeTab(CreativeTabs.tabBlock);

		DecoManager.Register(this, new String[] {"granite", "andesite", "diorite"}, new String[] {"Granite", "Andesite", "Diorite"});
	}

	public int damageDropped(int meta) {
		return meta;
	}

	public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
	{
		return 3;
	}

	public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
	{
		if (var5 == 0) {
			this.DropItemsIndividualy(var1, var2, var3, var4, DecoDefs.graniteCobbleLoose.blockID, 1, 0, var6);
		}
		else if (var5 == 1) {
			this.DropItemsIndividualy(var1, var2, var3, var4, DecoDefs.andesiteCobbleLoose.blockID, 1, 0, var6);
		}
		else if (var5 == 2) {
			this.DropItemsIndividualy(var1, var2, var3, var4, DecoDefs.dioriteCobbleLoose.blockID, 1, 0, var6);
		}
		return true;
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

	/**
	 * Get the block's damage value (for use with pick block).
	 */
	public int getDamageValue(World world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
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

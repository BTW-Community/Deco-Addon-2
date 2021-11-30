package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockStoneVariantCobble extends Block {
	public DecoBlockStoneVariantCobble(int ID) {
		super(ID, Material.rock);
		this.setHardness(2.25F);
		this.setResistance(10.0F);
		this.SetPicksEffectiveOn();
		this.SetChiselsEffectiveOn();
		this.setStepSound(soundStoneFootstep);
		this.setUnlocalizedName("decoBlockCobblestone");
		this.setCreativeTab(CreativeTabs.tabBlock);

		DecoManager.register(this, new String[] {"granite", "andesite", "diorite", "slate"});
	}

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        //super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);

        if (!var1.isRemote)
        {
        	Block drop = DecoDefs.graniteCobbleLoose;
        	
        	switch (var5) {
        	case 0:
        		drop = DecoDefs.graniteCobbleLoose;
        		break;
        	case 1:
        		drop = DecoDefs.andesiteCobbleLoose;
        		break;
        	case 2:
        		drop = DecoDefs.dioriteCobbleLoose;
        		break;
        	case 3:
        		drop = DecoDefs.slateCobbleLoose;
        		break;
        	}
        	
            this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(drop));
        }
    }

    public boolean HasMortar(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
	
	//CLIENT ONLY METHODS
	public static Icon[] Icons = new Icon[4];
	public Icon getIcon(int Side, int Meta)
	{
		return Icons[Meta];
	}
	public void registerIcons(IconRegister Register)
	{
		Icons[0] = Register.registerIcon("decoBlockGraniteCobble");
		Icons[1] = Register.registerIcon("decoBlockAndesiteCobble");
		Icons[2] = Register.registerIcon("decoBlockDioriteCobble");
		Icons[3] = Register.registerIcon("decoBlockSlateCobble");
	}
	//
}

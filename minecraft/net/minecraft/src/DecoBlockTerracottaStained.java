package net.minecraft.src;

import java.util.List;

public class DecoBlockTerracottaStained extends Block
{
	private String nameTag;
	private Icon[] icons = new Icon[16];
	
	public DecoBlockTerracottaStained(int id) {
		super(id, Material.rock);
	}
	
	@Override
	public int damageDropped(int metadata){
		return metadata;
	}

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 3;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, DecoDefs.pileRedSand.itemID, 8, 0, var6);
        return true;
    }

	@Override
    public int getItemIDDroppedOnStonecutter(World world, int x, int y, int z) {
		if (this.blockID == DecoDefs.stainedTerracotta.blockID) {
			return DecoDefs.stainedTerracottaSidingAndCorner[world.getBlockMetadata(x, y, z)].blockID;
		}
		else {
			return DecoDefs.shingleColoredSidingAndCorner[world.getBlockMetadata(x, y, z)].blockID;
		}
    }

	@Override
    public int getItemCountDroppedOnStonecutter(World world, int x, int y, int z) {
        return 2;
    }
	
	//CLIENT ONLY
	@Override
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i < 16; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
    }
    
	@Override
	public Icon getIcon(int side, int metadata){
		return this.icons[metadata];
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		for (int i = 0; i < 16; i++)
			this.icons[i] = iconRegister.registerIcon(this.getUnlocalizedName2() + "_" + DecoUtilsMisc.colorOrder[i]);
	}
}

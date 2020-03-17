package net.minecraft.src;

import java.util.Random;

public class AddonBlockPodzol extends FCBlockDirt {
	protected AddonBlockPodzol(int ID) {
		super(ID);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileDirt.itemID, 3, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemPileSand.itemID, 3, 0, var6);
        return true;
    }
	
	@Override
    public boolean GetCanMyceliumSpreadToBlock(World var1, int var2, int var3, int var4)
    {
        return false;
    }
	
	@Override
    public boolean GetCanGrassSpreadToBlock(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    protected void OnNeighborDirtDugWithImproperTool(World var1, int var2, int var3, int var4, int var5) {}
    
    //CLIENT ONLY
    private Icon iconPodzolTop;
    private Icon iconSnowSide;

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.iconPodzolTop : (par1 == 0 ? Block.dirt.getBlockTextureFromSide(par1) : this.blockIcon);
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (par5 == 1)
        {
            return this.iconPodzolTop;
        }
        else if (par5 == 0)
        {
            return Block.dirt.getBlockTextureFromSide(par5);
        }
        else
        {
            Material var6 = par1IBlockAccess.getBlockMaterial(par2, par3 + 1, par4);
            return var6 != Material.snow && var6 != Material.craftedSnow ? this.blockIcon : this.iconSnowSide;
        }
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("ginger_podzol_side");
        this.iconPodzolTop = par1IconRegister.registerIcon("ginger_podzol_top");
        this.iconSnowSide = par1IconRegister.registerIcon("ginger_snow_side");
    }
}

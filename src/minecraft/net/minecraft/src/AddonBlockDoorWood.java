package net.minecraft.src;

import java.util.Random;

public class AddonBlockDoorWood extends FCBlockDoorWood {
	private String[] doorIconNames;
	
	protected AddonBlockDoorWood(int ID, String[] textures) {
		super(ID);
		doorIconNames = textures;
	}
	
	@Override
    public int idDropped(int par1, Random par2Random, int par3) {
		switch (this.blockID) {
		case AddonDefs.id_doorSpruce:
			return AddonDefs.itemDoorSpruce.itemID;
		case AddonDefs.id_doorBirch:
			return AddonDefs.itemDoorBirch.itemID;
		case AddonDefs.id_doorJungle:
			return AddonDefs.itemDoorJungle.itemID;
		case AddonDefs.id_doorBlood:
			return AddonDefs.itemDoorBlood.itemID;
		default:
			return AddonDefs.itemDoorSpruce.itemID;
		}
	}
	
    public int idPicked(World par1World, int par2, int par3, int par4) {
		switch (this.blockID) {
		case AddonDefs.id_doorSpruce:
			return AddonDefs.itemDoorSpruce.itemID;
		case AddonDefs.id_doorBirch:
			return AddonDefs.itemDoorBirch.itemID;
		case AddonDefs.id_doorJungle:
			return AddonDefs.itemDoorJungle.itemID;
		case AddonDefs.id_doorBlood:
			return AddonDefs.itemDoorBlood.itemID;
		default:
			return AddonDefs.itemDoorSpruce.itemID;
		}
    }
	
	//CLIENT ONLY
	/** Used for pointing at icon names. */
	private Icon[] iconArray;

	public Icon getIcon(int par1, int par2)
	{
		return this.iconArray[0];
	}

	/**
	 * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
	 */
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		if (par5 != 1 && par5 != 0)
		{
			int var6 = this.getFullMetadata(par1IBlockAccess, par2, par3, par4);
			int var7 = var6 & 3;
			boolean var8 = (var6 & 4) != 0;
			boolean var9 = false;
			boolean var10 = (var6 & 8) != 0;

			if (var8)
			{
				if (var7 == 0 && par5 == 2)
				{
					var9 = !var9;
				}
				else if (var7 == 1 && par5 == 5)
				{
					var9 = !var9;
				}
				else if (var7 == 2 && par5 == 3)
				{
					var9 = !var9;
				}
				else if (var7 == 3 && par5 == 4)
				{
					var9 = !var9;
				}
			}
			else
			{
				if (var7 == 0 && par5 == 5)
				{
					var9 = !var9;
				}
				else if (var7 == 1 && par5 == 3)
				{
					var9 = !var9;
				}
				else if (var7 == 2 && par5 == 4)
				{
					var9 = !var9;
				}
				else if (var7 == 3 && par5 == 2)
				{
					var9 = !var9;
				}

				if ((var6 & 16) != 0)
				{
					var9 = !var9;
				}
			}

			return this.iconArray[(var9 ? doorIconNames.length : 0) + (var10 ? 1 : 0)];
		}
		else
		{
			return this.iconArray[0];
		}
	}

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.iconArray = new Icon[doorIconNames.length * 2];

		for (int var2 = 0; var2 < doorIconNames.length; ++var2)
		{
			this.iconArray[var2] = par1IconRegister.registerIcon(doorIconNames[var2]);
			this.iconArray[var2 + doorIconNames.length] = new IconFlipped(this.iconArray[var2], true, false);
		}
	}
}

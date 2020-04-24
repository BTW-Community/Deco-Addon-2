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
		int returnID = 0;
		
		int doorSpruce = AddonDefs.doorSpruce.blockID; 
		int doorBirch = AddonDefs.doorBirch.blockID;
		int doorJungle = AddonDefs.doorJungle.blockID;
		int doorBlood = AddonDefs.doorBlood.blockID;
		int doorCherry = AddonDefs.doorCherry.blockID;
		
		returnID = this.blockID == doorSpruce ? AddonDefs.itemDoorSpruce.itemID : (this.blockID == doorBirch ? AddonDefs.itemDoorBirch.itemID : (this.blockID == doorJungle ? AddonDefs.itemDoorJungle.itemID : (this.blockID == doorBlood ? AddonDefs.itemDoorBlood.itemID : AddonDefs.itemDoorCherry.itemID)));
		
		return (par1 & 8) != 0 ? 0 : returnID;
	}
	
    public int idPicked(World par1World, int par2, int par3, int par4) {
		int doorSpruce = AddonDefs.doorSpruce.blockID; 
		int doorBirch = AddonDefs.doorBirch.blockID;
		int doorJungle = AddonDefs.doorJungle.blockID;
		int doorBlood = AddonDefs.doorBlood.blockID;
		int doorCherry = AddonDefs.doorCherry.blockID;

		return this.blockID == doorSpruce ? AddonDefs.itemDoorSpruce.itemID : (this.blockID == doorBirch ? AddonDefs.itemDoorBirch.itemID : (this.blockID == doorJungle ? AddonDefs.itemDoorJungle.itemID : (this.blockID == doorBlood ? AddonDefs.itemDoorBlood.itemID : AddonDefs.itemDoorCherry.itemID)));
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

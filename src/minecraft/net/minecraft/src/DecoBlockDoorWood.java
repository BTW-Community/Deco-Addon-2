package net.minecraft.src;

import java.util.Random;

public class DecoBlockDoorWood extends FCBlockDoorWood {
	private String[] doorIconNames;
	private int doorItemID;
	
	protected DecoBlockDoorWood(int ID, String[] textures, int doorItemID) {
		super(ID);
		this.doorIconNames = textures;
		this.doorItemID = doorItemID;
	}

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (this.blockMaterial == Material.iron)
        {
            return true;
        }
        else
        {
            int var10 = this.getFullMetadata(world, x, y, z);
            int var11 = var10 & 7;
            var11 ^= 4;
            
            if (!world.isRemote) {
            	if (this.isDoorOpen(world, x, y, z))
            		world.playAuxSFX(DecoManager.decoDoorWoodCloseAuxFXID, x, y, z, 0);
            	else
            		world.playAuxSFX(DecoManager.decoDoorWoodOpenAuxFXID, x, y, z, 0);
            }

            if ((var10 & 8) == 0)
            {
                world.SetBlockMetadataWithNotify(x, y, z, var11, 3);
                world.notifyBlockChange(x, y + 1, z, this.blockID);
                world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
            }
            else
            {
                world.SetBlockMetadataWithNotify(x, y - 1, z, var11, 3);
                world.notifyBlockChange(x, y, z, this.blockID);
                world.markBlockRangeForRenderUpdate(x, y - 1, z, x, y, z);
            }
            
            return true;
        }
    }
	
	@Override
    public int idDropped(int par1, Random par2Random, int par3) {
		if ((par1 & 8) == 0)
			return doorItemID;
		else
			return 0;
	}
	
    public int idPicked(World par1World, int par2, int par3, int par4) {
    	return doorItemID;
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

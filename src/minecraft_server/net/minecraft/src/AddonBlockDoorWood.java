package net.minecraft.src;

import java.util.Random;

public class AddonBlockDoorWood extends FCBlockDoorWood {
	private String[] doorIconNames;
	private int doorItemID;
	
	protected AddonBlockDoorWood(int ID, String[] textures, int doorItemID) {
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
            		world.playAuxSFX(AddonManager.addonDoorWoodCloseAuxFXID, x, y, z, 0);
            	else
            		world.playAuxSFX(AddonManager.addonDoorWoodOpenAuxFXID, x, y, z, 0);
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
		return doorItemID;
	}
	
    public int idPicked(World par1World, int par2, int par3, int par4) {
    	return doorItemID;
    }
}

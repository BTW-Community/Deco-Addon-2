package net.minecraft.src;

import java.util.Random;

public class AddonBlockDoorWood extends FCBlockDoorWood {
	private String[] doorIconNames;
	
	protected AddonBlockDoorWood(int ID, String[] textures) {
		super(ID);
		doorIconNames = textures;
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
            
            if (this.isDoorOpen(world, x, y, z))
                AddonUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.doorClose", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_close", 1, world.rand.nextFloat() * 0.1F + 0.9F);
            else
                AddonUtilsSound.playSoundWithVanillaFallback(world, x, y, z, "deco.random.doorOpen", 1, world.rand.nextFloat() * 0.1F + 0.9F, "random.door_open", 1, world.rand.nextFloat() * 0.1F + 0.9F);

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
}

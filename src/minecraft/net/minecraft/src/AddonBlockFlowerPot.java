package net.minecraft.src;

import java.util.Random;

public class AddonBlockFlowerPot extends BlockContainer {
	public AddonBlockFlowerPot(int id) {
		super(id, Material.circuits);
        this.InitBlockBounds(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.375D, 0.6875D);
		this.setHardness(0.0F);
		this.setStepSound(soundPowderFootstep);
		this.setUnlocalizedName("flowerPot");
	}

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        return super.canPlaceBlockAt(world, x, y, z) && FCUtilsWorld.DoesBlockHaveCenterHardpointToFacing(world, x, y - 1, z, 1);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World world, int x, int y, int z, int par5)
    {
        if (!FCUtilsWorld.DoesBlockHaveCenterHardpointToFacing(world, x, y - 1, z, 1))
        {
            this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World world, int x, int y, int z)
    {
    	AddonTileEntityFlowerPot potTile = (AddonTileEntityFlowerPot) world.getBlockTileEntity(x, y, z);
    	int blockInPotID = potTile.getStoredBlockID();
    	
    	if (blockInPotID == Block.mushroomBrown.blockID)
    		return FCBetterThanWolves.fcItemMushroomBrown.itemID;
    	else if (blockInPotID == Block.mushroomRed.blockID)
    		return FCBetterThanWolves.fcItemMushroomRed.itemID;
    	else
    		return blockInPotID == 0 ? Item.flowerPot.itemID : blockInPotID;
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World world, int x, int y, int z)
    {
    	AddonTileEntityFlowerPot potTile = (AddonTileEntityFlowerPot) world.getBlockTileEntity(x, y, z);
    	int blockInPotMeta = potTile.getStoredBlockMetadata();
        return blockInPotMeta;
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World world, int x, int y, int z, int var5, int var6)
    {
    	AddonTileEntityFlowerPot potTile = (AddonTileEntityFlowerPot) world.getBlockTileEntity(x, y, z);
    	int blockInPotID = potTile != null ? potTile.getStoredBlockID() : 0;

        if (blockInPotID != 0)
        {
        	int blockInPotMeta = potTile.getStoredBlockMetadata();
            FCUtilsItem.EjectSingleItemWithRandomOffset(world, x, y, z, blockInPotID, blockInPotMeta);
        }
        
        super.breakBlock(world, x, y, z, var5, var6);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Item.flowerPot.itemID;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World)
    {
        return new AddonTileEntityFlowerPot();
    }
    
    //When right clicked by a player
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    	ItemStack handItemStack = player.getCurrentEquippedItem();
    	AddonTileEntityFlowerPot potTile = (AddonTileEntityFlowerPot) world.getBlockTileEntity(x, y, z);
    	
    	//Empty hand retrieves item
    	if (handItemStack == null) {
    		potTile.retrieveItemFromPot(player);
    		return true;
    	}
    	
    	int heldID = handItemStack.getItem().itemID;
    	int heldDamage = handItemStack.getItemDamage();
    	//If pot already has an item do nothing
    	if (potTile.hasItem()) {
    		return false;
    	}
    	//If item is placeable within the pot, place it
    	else if (potTile.isValidItemForPot(heldID, heldDamage) || heldID == FCBetterThanWolves.fcItemMushroomBrown.itemID || heldID == FCBetterThanWolves.fcItemMushroomRed.itemID) {
    		potTile.placeItemInPot(heldID, heldDamage);
    		
    		//Decrements stack, unless in creative
    		if (!player.capabilities.isCreativeMode && --handItemStack.stackSize <= 0)
            {
    			player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
            }
    		
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1.0F;
    }
    
    //CLIENT ONLY
    @Override
    public boolean RenderBlock(RenderBlocks render, int x, int y, int z) {
    	//Only renders the pot (copied from vanilla render code). The plant inside the pot is handled by the tile entity renderer.
        render.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(render.blockAccess, x, y, z));
    	
        Tessellator tess = Tessellator.instance;
        tess.setBrightness(this.getMixedBrightnessForBlock(render.blockAccess, x, y, z));
        float var6 = 1.0F;
        int var7 = this.colorMultiplier(render.blockAccess, x, y, z);
        Icon var8 = render.getBlockIconFromSide(this, 0);
        float var9 = (float)(var7 >> 16 & 255) / 255.0F;
        float var10 = (float)(var7 >> 8 & 255) / 255.0F;
        float var11 = (float)(var7 & 255) / 255.0F;
        tess.setColorOpaque_F(var6 * var9, var6 * var10, var6 * var11);
        float var12 = 0.1865F;
        render.renderFaceXPos(this, (double)((float)x - 0.5F + var12), (double)y, (double)z, var8);
        render.renderFaceXNeg(this, (double)((float)x + 0.5F - var12), (double)y, (double)z, var8);
        render.renderFaceZPos(this, (double)x, (double)y, (double)((float)z - 0.5F + var12), var8);
        render.renderFaceZNeg(this, (double)x, (double)y, (double)((float)z + 0.5F - var12), var8);
        render.renderFaceYPos(this, (double)x, (double)((float)y - 0.5F + var12 + 0.1875F), (double)z, render.getBlockIcon(Block.dirt));
        
        super.RenderBlock(render, x, y, z);
        
        //Renders the pot's contents
		AddonTileEntityFlowerPot potTile = (AddonTileEntityFlowerPot) render.blockAccess.getBlockTileEntity(x, y, z);
		int storedBlockID = potTile.getStoredBlockID();
		int storedBlockMetadata = potTile.getStoredBlockMetadata();
        tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		
		//Cactus renders differently than everything else
		if (storedBlockID == Block.cactus.blockID) {
			render.SetRenderAllFaces(true);
			
			render.setRenderBounds(.375, .375, .375, .625, 1.0, .625);
			render.renderStandardBlock(Block.cactus, x, y, z);
			
			render.SetRenderAllFaces(false);
			render.setRenderBounds(0, 0, 0, 1, 1, 1);
		}
		//Renders other blocks as crossed squares. Double checks validity
		else if (potTile.isValidItemForPot(storedBlockID, storedBlockMetadata)) {
			Block storedBlock = Block.blocksList[storedBlockID];
			if (potTile.isSaplingForAltRender(storedBlock.blockID))
				//Renders saplings as full growth stage
				render.drawCrossedSquares(storedBlock, storedBlockMetadata + 12, potTile.xCoord, potTile.yCoord + .25, potTile.zCoord, 1.0F);
			else
				render.drawCrossedSquares(storedBlock, storedBlockMetadata, potTile.xCoord, potTile.yCoord + .25, potTile.zCoord, 1.0F);
		}
		else {
			return false;
		}
        
        return true;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return var5 == 0 ? !var1.isBlockOpaqueCube(var2, var3, var4) : true;
    }
}
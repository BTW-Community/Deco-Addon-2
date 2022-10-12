package deco.block.blocks;

import java.util.Random;

import btw.item.BTWItems;
import btw.item.util.ItemUtils;
import btw.world.util.WorldUtils;
import deco.block.tileentity.FlowerPotTileEntity;
import deco.client.util.DecoRenderUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class FlowerPotBlock extends BlockContainer {
	public FlowerPotBlock(int id) {
		super(id, Material.circuits);
		this.initBlockBounds(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.375D, 0.6875D);
		this.setHardness(0.0F);
		this.setStepSound(soundPowderFootstep);
		this.setUnlocalizedName("flowerPot");
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return super.canPlaceBlockAt(world, x, y, z) && WorldUtils.doesBlockHaveCenterHardpointToFacing(world, x, y - 1, z, 1);
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int par5) {
		if (!WorldUtils.doesBlockHaveCenterHardpointToFacing(world, x, y - 1, z, 1)) {
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, int var5, int var6) {
		FlowerPotTileEntity potTile = (FlowerPotTileEntity) world.getBlockTileEntity(x, y, z);
		int blockInPotID = potTile != null ? potTile.getStoredBlockID() : 0;
		
		if (blockInPotID != 0) {
			int blockInPotMeta = potTile.getStoredBlockMetadata();
			ItemUtils.ejectSingleItemWithRandomOffset(world, x, y, z, blockInPotID, blockInPotMeta);
		}
		
		super.breakBlock(world, x, y, z, var5, var6);
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Item.flowerPot.itemID;
	}
	
	@Override
	public TileEntity createNewTileEntity(World par1World) {
		return new FlowerPotTileEntity();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		ItemStack handItemStack = player.getCurrentEquippedItem();
		FlowerPotTileEntity potTile = (FlowerPotTileEntity) world.getBlockTileEntity(x, y, z);
		
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
		else if (potTile.isValidItemForPot(heldID, heldDamage) || heldID == BTWItems.brownMushroom.itemID ||
				heldID == BTWItems.redMushroom.itemID) {
			potTile.placeItemInPot(heldID, heldDamage);
			
			//Decrements stack, unless in creative
			if (!player.capabilities.isCreativeMode && --handItemStack.stackSize <= 0) {
				player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
			}
			
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean canGroundCoverRestOnBlock(World var1, int var2, int var3, int var4) {
		return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
	}
	
	@Override
	public float groundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4) {
		return -1.0F;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean renderBlock(RenderBlocks render, int x, int y, int z) {
		render.setRenderBounds(this.getBlockBoundsFromPoolBasedOnState(render.blockAccess, x, y, z));
		
		Tessellator tess = Tessellator.instance;
		tess.setBrightness(this.getMixedBrightnessForBlock(render.blockAccess, x, y, z));
		float var6 = 1.0F;
		int var7 = this.colorMultiplier(render.blockAccess, x, y, z);
		Icon var8 = render.getBlockIconFromSide(this, 0);
		float var9 = (float) (var7 >> 16 & 255) / 255.0F;
		float var10 = (float) (var7 >> 8 & 255) / 255.0F;
		float var11 = (float) (var7 & 255) / 255.0F;
		tess.setColorOpaque_F(var6 * var9, var6 * var10, var6 * var11);
		float var12 = 0.1865F;
		render.renderFaceXPos(this, (double) ((float) x - 0.5F + var12), (double) y, (double) z, var8);
		render.renderFaceXNeg(this, (double) ((float) x + 0.5F - var12), (double) y, (double) z, var8);
		render.renderFaceZPos(this, (double) x, (double) y, (double) ((float) z - 0.5F + var12), var8);
		render.renderFaceZNeg(this, (double) x, (double) y, (double) ((float) z + 0.5F - var12), var8);
		render.renderFaceYPos(this, (double) x, (double) ((float) y - 0.5F + var12 + 0.1875F), (double) z, render.getBlockIcon(Block.dirt));
		
		super.renderBlock(render, x, y, z);
		
		//Renders the pot's contents
		FlowerPotTileEntity potTile = (FlowerPotTileEntity) render.blockAccess.getBlockTileEntity(x, y, z);
		int storedBlockID = potTile.getStoredBlockID();
		int storedBlockMetadata = potTile.getStoredBlockMetadata();
		tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		
		//Cactus renders differently than everything else
		if (storedBlockID == Block.cactus.blockID) {
			render.setRenderAllFaces(true);
			
			render.setRenderBounds(.375, .375, .375, .625, 1.0, .625);
			render.renderStandardBlock(Block.cactus, x, y, z);
			
			render.setRenderAllFaces(false);
			render.setRenderBounds(0, 0, 0, 1, 1, 1);
		}
		//Renders other blocks as crossed squares. Double checks validity
		else if (potTile.isValidItemForPot(storedBlockID, storedBlockMetadata)) {
			Block storedBlock = Block.blocksList[storedBlockID];
			
			DecoRenderUtils.drawCrossedSquares(render, storedBlock, storedBlockMetadata, potTile.xCoord, potTile.yCoord + .25, potTile.zCoord, 1.0F, 1);
		}
		else {
			return false;
		}
		
		return true;
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5) {
		return var5 == 0 ? !var1.isBlockOpaqueCube(var2, var3, var4) : true;
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public int idPicked(World world, int x, int y, int z) {
		FlowerPotTileEntity potTile = (FlowerPotTileEntity) world.getBlockTileEntity(x, y, z);
		int blockInPotID = potTile.getStoredBlockID();
		
		if (blockInPotID == Block.mushroomBrown.blockID) {
			return BTWItems.brownMushroom.itemID;
		}
		else if (blockInPotID == Block.mushroomRed.blockID) {
			return BTWItems.redMushroom.itemID;
		}
		else {
			return blockInPotID == 0 ? Item.flowerPot.itemID : blockInPotID;
		}
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public int getDamageValue(World world, int x, int y, int z) {
		FlowerPotTileEntity potTile = (FlowerPotTileEntity) world.getBlockTileEntity(x, y, z);
		int blockInPotMeta = potTile.getStoredBlockMetadata();
		return blockInPotMeta;
	}
}
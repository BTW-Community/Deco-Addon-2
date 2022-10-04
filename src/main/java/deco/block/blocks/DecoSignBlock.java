package deco.block.blocks;

import btw.block.blocks.SignBlock;
import deco.util.MiscUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

public class DecoSignBlock extends SignBlock {
	public final String SIGN_TEXTURE;
	private String baseTexture;
	
	private final int SIGN_META_DROPPED;
	
	public DecoSignBlock(int blockID, int signMetaDropped, boolean isFreeStanding, String signTexture, String baseTexture) {
		super(blockID, isFreeStanding);
		
		this.SIGN_TEXTURE = signTexture;
		this.baseTexture = baseTexture;
		
		this.SIGN_META_DROPPED = signMetaDropped;
	}
	
	@Override
	public int damageDropped(int par1)
	{
		return this.SIGN_META_DROPPED;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int var6, float var7, float var8, float var9) {
		if (player.getCurrentEquippedItem() == null) {
			TileEntitySign tileEntity = (TileEntitySign) world.getBlockTileEntity(x, y, z);
			
			if (tileEntity != null) {
				String[] text = tileEntity.signText;
				tileEntity.setEditable(true);
				
				for (int i = 0; i < text.length; i++) {
					if (text[i].length() > 0 && text[i].charAt(0) == '§') {
						text[i] = text[i].substring(2);
					}
				}
				
				player.displayGUIEditSign(tileEntity);
			}
			
			return true;
		}
		else if (player.getCurrentEquippedItem().itemID == Item.dyePowder.itemID) {
			TileEntitySign tileEntity = (TileEntitySign) world.getBlockTileEntity(x, y, z);
			
			if (tileEntity != null) {
				String[] text = tileEntity.signText;
				String formatCode = MiscUtil.getFormatCodeFromDyeMetadata(player.getCurrentEquippedItem().getItemDamage());
				
				for (int i = 0; i < text.length; i++) {
					if (text[i].length() > 0 && text[i].charAt(0) == '§') {
						text[i] = text[i].substring(2);
					}
					text[i] = formatCode + text[i];
				}
				
				player.getCurrentEquippedItem().stackSize--;
			}
			
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean canRotateAroundBlockOnTurntableToFacing(World world, int x, int y, int z, int facing) {
		return this.freeStanding && facing == Block.getOppositeFacing(world.getBlockMetadata(x, y, z));
	}
	
	@Override
	public boolean onRotatedAroundBlockOnTurntableToFacing(World world, int x, int y, int z, int facing) {
		dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
		world.setBlockToAir(x, y, z);
		
		return false;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister register) {
		this.blockIcon = register.registerIcon(this.baseTexture);
	}
	
	public String getSignTexture() {
		return this.SIGN_TEXTURE;
	}
}

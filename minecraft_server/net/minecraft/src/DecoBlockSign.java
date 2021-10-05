package net.minecraft.src;

public class DecoBlockSign extends FCBlockSign {
	private int signItemMetaDrop;
	private String baseTexture;
	
	public DecoBlockSign(int id, boolean floor, int metaDrop, String baseTexture) {
		super(id, floor);
		this.signItemMetaDrop = metaDrop;
		this.baseTexture = baseTexture;
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int var6, float var7, float var8, float var9)
	{
		if (player.getCurrentEquippedItem() == null)
		{
			TileEntitySign tileEntity = (TileEntitySign)world.getBlockTileEntity(x, y, z);

			if (tileEntity != null)
			{
				String[] text = tileEntity.signText;
				tileEntity.setEditable(true);
				
				for (int i = 0; i < text.length; i++) {
					if (text[i].length() > 0 && text[i].charAt(0) == '§')
						text[i] = text[i].substring(2);
				}
				
				player.displayGUIEditSign(tileEntity);
			}

			return true;
		}
		else if (player.getCurrentEquippedItem().itemID == Item.dyePowder.itemID) {
			TileEntitySign tileEntity = (TileEntitySign)world.getBlockTileEntity(x, y, z);
			
			if (tileEntity != null)
			{
				String[] text = tileEntity.signText;
				String formatCode = DecoUtilsMisc.getFormatCodeFromDyeMetadata(player.getCurrentEquippedItem().getItemDamage());
				
				for (int i = 0; i < text.length; i++) {
					if (text[i].length() > 0 && text[i].charAt(0) == '§')
						text[i] = text[i].substring(2);
					text[i] = formatCode + text[i];
				}
				
				player.getCurrentEquippedItem().stackSize--;
			}
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public int damageDropped(int meta) {
		return signItemMetaDrop;
	}
}
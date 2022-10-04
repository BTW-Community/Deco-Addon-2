package deco.block.blocks;

import btw.block.blocks.DoorBlockWood;
import btw.item.BTWItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

public class DecoDoorBlockWood extends DoorBlockWood {
	private int itemID;
	
	private String[] doorIconNames;
	
	public DecoDoorBlockWood(int blockID, int itemID, String topTexture, String bottomTexture) {
		super(blockID);
		
		this.itemID = itemID;
		
		this.doorIconNames = new String[]{bottomTexture, topTexture};
	}
	
	@Override
	public int idDropped(int metadata, Random rand, int par3) {
		if ((metadata & 8) == 0) {
			return this.itemID;
		}
		else {
			return 0;
		}
	}
	
	@Override
	public int getHarvestToolLevel(IBlockAccess blockAccess, int x, int y, int z) {
		return 2;
	}
	
	@Override
	public boolean dropComponentItemsOnBadBreak(World world, int x, int y, int z, int metadata, float chanceOfDrop) {
		this.dropItemsIndividually(world, x, y, z, BTWItems.sawDust.itemID, 6, 0, chanceOfDrop);
		return true;
	}
	
	//----------- Client Side Functionality -----------//
	
	@Environment(EnvType.CLIENT)
	private Icon[] iconArray;
	
	@Environment(EnvType.CLIENT)
	@Override
	public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		if (par5 != 1 && par5 != 0) {
			int var6 = this.getFullMetadata(par1IBlockAccess, par2, par3, par4);
			int var7 = var6 & 3;
			boolean var8 = (var6 & 4) != 0;
			boolean var9 = false;
			boolean var10 = (var6 & 8) != 0;
			
			if (var8) {
				if (var7 == 0 && par5 == 2) {
					var9 = !var9;
				}
				else if (var7 == 1 && par5 == 5) {
					var9 = !var9;
				}
				else if (var7 == 2 && par5 == 3) {
					var9 = !var9;
				}
				else if (var7 == 3 && par5 == 4) {
					var9 = !var9;
				}
			}
			else {
				if (var7 == 0 && par5 == 5) {
					var9 = !var9;
				}
				else if (var7 == 1 && par5 == 3) {
					var9 = !var9;
				}
				else if (var7 == 2 && par5 == 4) {
					var9 = !var9;
				}
				else if (var7 == 3 && par5 == 2) {
					var9 = !var9;
				}
				
				if ((var6 & 16) != 0) {
					var9 = !var9;
				}
			}
			
			return this.iconArray[(var9 ? doorIconNames.length : 0) + (var10 ? 1 : 0)];
		}
		else {
			return this.iconArray[0];
		}
	}
	
	@Environment(EnvType.CLIENT)
	public Icon getIcon(int side, int metadata) {
		return this.iconArray[0];
	}
	
	@Environment(EnvType.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.iconArray = new Icon[doorIconNames.length * 2];
		
		for (int var2 = 0; var2 < doorIconNames.length; ++var2) {
			this.iconArray[var2] = par1IconRegister.registerIcon(doorIconNames[var2]);
			this.iconArray[var2 + doorIconNames.length] = new IconFlipped(this.iconArray[var2], true, false);
		}
	}
}

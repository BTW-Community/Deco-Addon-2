package net.minecraft.src;

import java.util.List;

public class DecoItemGlassStained extends DecoItemColored {
	private Icon[] icons;

	public DecoItemGlassStained(int ID) {
		super(ID);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public Icon getIconFromDamage(int damage) {
		int var2 = MathHelper.clamp_int(damage, 0, 15);
		return this.icons[var2];
	}

	public void getSubItems(int ID, CreativeTabs par2CreativeTabs, List list) {
		for (int i = 0; i <= 15; ++i) {
			list.add(new ItemStack(ID, 1, i));
		}
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		int var11 = par3World.getBlockId(par4, par5, par6);

		if (var11 == Block.snow.blockID && (par3World.getBlockMetadata(par4, par5, par6) & 7) < 1) {
			par7 = 1;
		} else if (var11 != Block.vine.blockID && var11 != Block.tallGrass.blockID && var11 != Block.deadBush.blockID) {
			if (par7 == 0) {
				--par5;
			}

			if (par7 == 1) {
				++par5;
			}

			if (par7 == 2) {
				--par6;
			}

			if (par7 == 3) {
				++par6;
			}

			if (par7 == 4) {
				--par4;
			}

			if (par7 == 5) {
				++par4;
			}
		}

		if (par1ItemStack.stackSize == 0) {
			return false;
		} else if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
			return false;
		} else if (par5 == 255 && Block.blocksList[DecoDefs.glassStained.blockID].blockMaterial.isSolid()) {
			return false;
		} else if (par3World.canPlaceEntityOnSide(DecoDefs.glassStained.blockID, par4, par5, par6, false, par7, par2EntityPlayer,
				par1ItemStack)) {
			Block var12 = Block.blocksList[DecoDefs.glassStained.blockID];
			int var13 = par1ItemStack.getItemDamage();
			int var14 = Block.blocksList[DecoDefs.glassStained.blockID].onBlockPlaced(par3World, par4, par5, par6, par7, par8, par9, par10,
					var13);

			if (par3World.setBlock(par4, par5, par6, DecoDefs.glassStained.blockID, var14, 3)) {
				if (par3World.getBlockId(par4, par5, par6) == DecoDefs.glassStained.blockID) {
					Block.blocksList[DecoDefs.glassStained.blockID].onBlockPlacedBy(par3World, par4, par5, par6, par2EntityPlayer, par1ItemStack);
					Block.blocksList[DecoDefs.glassStained.blockID].onPostBlockPlaced(par3World, par4, par5, par6, var14);
					par3World.NotifyNearbyAnimalsOfPlayerBlockAddOrRemove(par2EntityPlayer, Block.blocksList[DecoDefs.glassStained.blockID], par4,
							par5, par6);
				}

				par3World.playSoundEffect((double) ((float) par4 + 0.5F), (double) ((float) par5 + 0.5F),
						(double) ((float) par6 + 0.5F), var12.stepSound.getPlaceSound(),
						(var12.stepSound.getVolume() + 1.0F) / 2.0F, var12.stepSound.getPitch() * 0.8F);
				--par1ItemStack.stackSize;
			}

			return true;
		} else {
			return false;
		}
	}

	@Override
	public void registerIcons(IconRegister ir) {
		this.icons = new Icon[16];

		for (int i = 0; i < 16; ++i) {
			this.icons[i] = ir.registerIcon("decoItemStainedGlass_" + DecoUtilsMisc.colorOrder[i]);
		}
	}
}
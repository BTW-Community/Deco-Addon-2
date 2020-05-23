package net.minecraft.src;

public class AddonBlockBarrelFilling extends BlockContainer {
	public AddonBlockBarrelFilling(int id) {
		super(id, FCBetterThanWolves.fcMaterialPlanks);
        this.SetAxesEffectiveOn();
        this.setHardness(1.0F);
        this.setResistance(5.0F);
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.SetBuoyant();
        this.setStepSound(soundWoodFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("barrelFilling");
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new AddonTileEntityBarrelFilling();
	}

    public boolean DoesBlockHopperInsert(World var1, int var2, int var3, int var4)
    {
        return false;
    }
	
	public static int getMetadataFromBarrel(int id, int meta) {
		if (id == AddonDefs.barrelEmpty.blockID) {
			return meta;
		}
		else if (id == AddonDefs.barrelEmpty2.blockID) {
			return meta + 4;
		}
		else return -1;
	}
	
	public static boolean isValidItemForFill(ItemStack stack) {
		for (Item i : AddonBlockBarrelFilled.types) {
			if (stack.itemID == i.itemID) {
				return true;
			}
		}
		
		return false;
	}

	//CLIENT ONLY
	@Override public void registerIcons(IconRegister register) {}
}
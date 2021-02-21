package net.minecraft.src;

public class DecoBlockBarrelFilling extends BlockContainer {
	public DecoBlockBarrelFilling(int id) {
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
		return new DecoTileEntityBarrelFilling();
	}

    public boolean DoesBlockHopperInsert(World var1, int var2, int var3, int var4)
    {
        return false;
    }
	
	public static int getMetadataFromBarrel(int id, int meta) {
		if (id == DecoDefs.barrelEmpty.blockID) {
			return meta;
		}
		else if (id == DecoDefs.barrelEmpty2.blockID) {
			return meta + 4;
		}
		else return -1;
	}
	
	public static boolean isValidItemForFill(ItemStack stack) {
		for (Item i : DecoBlockBarrelFilled.types) {
			if (stack.itemID == i.itemID) {
				return true;
			}
		}
		
		return false;
	}
}
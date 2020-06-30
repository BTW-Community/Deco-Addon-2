package net.minecraft.src;

public class AddonItemCoal extends ItemCoal {
	public AddonItemCoal(int id) {
		super(id);
	}

    public boolean IsPistonPackable(ItemStack var1)
    {
    	//Only coal can be packed, not charcoal
        return var1.getItemDamage() == 0;
    }

    public int GetRequiredItemCountToPistonPack(ItemStack var1)
    {
        return 9;
    }

    public int GetResultingBlockIDOnPistonPack(ItemStack var1)
    {
        return AddonDefs.coalBlock.blockID;
    }
}
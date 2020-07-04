package net.minecraft.src;

public class AddonItemShearsDiamond extends AddonItemShears {
	public AddonItemShearsDiamond(int id) {
		super(id);
		this.setMaxDamage(500);
	}

    public float getStrVsBlock(ItemStack var1, World var2, Block var3, int var4, int var5, int var6)
    {
    	return super.getStrVsBlock(var1, var2, var3, var4, var5, var6) * 1.33F;
    }

    public boolean IsDamagedInCrafting()
    {
        return false;
    }

	public boolean IsConsumedInCrafting()
	{
		return false;
	}
}
package net.minecraft.src;

public class DecoItemShearsDiamond extends DecoItemShears {
	public DecoItemShearsDiamond(int id) {
		super(id);
		this.setMaxDamage(500);
        this.SetInfernalMaxEnchantmentCost(30);
        this.SetInfernalMaxNumEnchants(4);
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

	public void OnUsedInCrafting(EntityPlayer player, ItemStack stack)
	{
		if (player.m_iTimesCraftedThisTick == 0)
		{
			player.playSound("mob.sheep.shear", .8F, 1);
		}
	}

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return EnumToolMaterial.EMERALD.getEnchantability();
    }

    public boolean IsEnchantmentApplicable(Enchantment var1)
    {
        return var1.type == EnumEnchantmentType.digger ? true : super.IsEnchantmentApplicable(var1);
    }
}
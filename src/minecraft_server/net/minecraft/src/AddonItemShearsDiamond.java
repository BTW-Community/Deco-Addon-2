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

	public void OnUsedInCrafting(EntityPlayer player, ItemStack stack)
	{
		if (player.m_iTimesCraftedThisTick == 0)
		{
			player.playSound("mob.sheep.shear", .8F, 1);
		}
	}

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var3.m_iTimesCraftedThisTick == 0 && var2.isRemote)
        {
            var3.playSound("random.anvil_use", 0.5F, var2.rand.nextFloat() * 0.25F + 1.25F);
        }

        super.onCreated(var1, var2, var3);
    }
}
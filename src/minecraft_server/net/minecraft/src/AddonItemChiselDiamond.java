package net.minecraft.src;

public class AddonItemChiselDiamond extends FCItemChisel {
	protected AddonItemChiselDiamond(int var1)
	{
		super(var1, EnumToolMaterial.EMERALD, 500);
		this.efficiencyOnProperMaterial /= 4.0F;
		this.SetFilterableProperties(4);
		this.setUnlocalizedName("ginger_chiselDiamond");
	}

	protected boolean GetCanBePlacedAsBlock()
	{
		return true;
	}

	public boolean onBlockDestroyed(ItemStack var1, World var2, int var3, int var4, int var5, int var6, EntityLiving var7)
	{
		if (var3 == Block.wood.blockID && var2.getBlockId(var4, var5, var6) == FCBetterThanWolves.fcBlockWorkStump.blockID)
		{
			var1.damageItem(10, var7);
			return true;
		}
		else
		{
			return super.onBlockDestroyed(var1, var2, var3, var4, var5, var6, var7);
		}
	}

	public boolean IsDamagedInCrafting()
	{
		return true;
	}

	public void OnUsedInCrafting(EntityPlayer var1, ItemStack var2)
	{
		PlayStoneSplitSoundOnPlayer(var1);

		if (!var1.worldObj.isRemote && var2.itemID == FCBetterThanWolves.fcItemStoneBrick.itemID)
		{
			FCUtilsItem.EjectStackWithRandomVelocity(var1.worldObj, var1.posX, var1.posY, var1.posZ, new ItemStack(FCBetterThanWolves.fcItemPileGravel, 2));
		}
	}

	public void OnBrokenInCrafting(EntityPlayer var1)
	{
		PlayBreakSoundOnPlayer(var1);
	}

	protected boolean CanToolStickInBlock(ItemStack var1, Block var2, World var3, int var4, int var5, int var6)
	{
		return var2.blockMaterial == Material.rock && var2.blockID != Block.bedrock.blockID ? true : super.CanToolStickInBlock(var1, var2, var3, var4, var5, var6);
	}

	public static void PlayStoneSplitSoundOnPlayer(EntityPlayer var0)
	{
		if (var0.m_iTimesCraftedThisTick == 0)
		{
			var0.playSound("random.anvil_land", 0.5F, var0.worldObj.rand.nextFloat() * 0.25F + 1.75F);
		}
	}

	public static void PlayBreakSoundOnPlayer(EntityPlayer var0)
	{
		var0.playSound("random.break", 0.8F, 0.8F + var0.worldObj.rand.nextFloat() * 0.4F);
	}
}
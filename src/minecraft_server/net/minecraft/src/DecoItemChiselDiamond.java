package net.minecraft.src;

public class DecoItemChiselDiamond extends FCItemChisel {
	protected DecoItemChiselDiamond(int var1)
	{
		super(var1, EnumToolMaterial.EMERALD, 500);
		this.SetFilterableProperties(4);
		this.setUnlocalizedName("decoItemChiselDiamond");
        this.SetInfernalMaxEnchantmentCost(30);
        this.SetInfernalMaxNumEnchants(4);
	}

	protected boolean GetCanBePlacedAsBlock()
	{
		return true;
	}

    public boolean onBlockDestroyed(ItemStack var1, World var2, int var3, int var4, int var5, int var6, EntityLiving var7)
    {
        if ((var3 == Block.wood.blockID || var3 == DecoDefs.cherryStump.blockID || var3 == DecoDefs.acaciaStump.blockID) && var2.getBlockId(var4, var5, var6) == FCBetterThanWolves.fcBlockWorkStump.blockID)
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
			if (stack.itemID == DecoDefs.barkLogStripped.blockID || stack.itemID == DecoDefs.strippedLog.blockID || stack.itemID == DecoDefs.cherryLog.blockID) {
				DecoUtilsSound.playSoundAtEntityWithVanillaFallback(player.worldObj, player, "deco.random.strip", 2, 1, "mob.zombie.wood", 0.5F, 2.5F);
			}
			else if (stack.itemID == DecoDefs.bloodLog.blockID) {
				player.worldObj.playAuxSFX(2225, (int)player.posX, (int)player.posY, (int)player.posZ, 0);
				DecoUtilsSound.playSoundAtEntityWithNullFallback(player.worldObj, player, "deco.random.strip", 2, 1);
			}
			else if (stack.itemID == DecoDefs.pumpkin.blockID) {
            	DecoUtilsSound.playSoundAtEntityWithVanillaFallback(player.worldObj, player, "deco.random.pumpkinCarve", 2, 1, "mob.slime.attack", 0.5F, (player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.1F + 0.7F);
			}
			else if (stack.itemID != Block.pumpkin.blockID) {
				PlayStoneSplitSoundOnPlayer(player);
			}
		}

		if (!player.worldObj.isRemote && stack.itemID == FCBetterThanWolves.fcBlockStoneBrickLoose.blockID)
		{
			FCUtilsItem.EjectStackWithRandomVelocity(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(FCBetterThanWolves.fcItemPileGravel, 2));
		}
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

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return this.toolMaterial.getEnchantability();
    }

    public boolean IsEnchantmentApplicable(Enchantment var1)
    {
        return var1.type == EnumEnchantmentType.digger ? true : super.IsEnchantmentApplicable(var1);
    }
}
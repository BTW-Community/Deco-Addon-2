package net.minecraft.src;

public class AddonItemChiselIron extends FCItemChiselIron {
	protected AddonItemChiselIron(int id) {
		super(id);
	}

	public void OnUsedInCrafting(EntityPlayer player, ItemStack stack)
	{
		if (player.m_iTimesCraftedThisTick == 0)
		{
			if (stack.itemID == AddonDefs.barkLogStripped.blockID || stack.itemID == AddonDefs.strippedLog.blockID || stack.itemID == AddonDefs.cherryLog.blockID) {
				AddonUtilsSound.playSoundAtEntityWithVanillaFallback(player.worldObj, player, "deco.random.strip", 2, 1, "mob.zombie.wood", 0.5F, 2.5F);
			}
			else if (stack.itemID == AddonDefs.bloodLog.blockID) {
				player.worldObj.playAuxSFX(2225, (int)player.posX, (int)player.posY, (int)player.posZ, 0);
				AddonUtilsSound.playSoundAtEntityWithNullFallback(player.worldObj, player, "deco.random.strip", 2, 1);
			}
			else if (stack.itemID == AddonDefs.pumpkin.blockID) {
            	AddonUtilsSound.playSoundAtEntityWithVanillaFallback(player.worldObj, player, "deco.random.pumpkinCarve", 2, 1, "mob.slime.attack", 0.5F, (player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.1F + 0.7F);
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
}
package net.minecraft.src;

public class DecoItemChiselDiamond extends FCItemChiselDiamond {
	protected DecoItemChiselDiamond(int id)
	{
		super(id);
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
	}
}
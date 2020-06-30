package net.minecraft.src;

public class AddonItemBlockPumpkinFresh extends FCItemBlockPumpkinFresh {
	public AddonItemBlockPumpkinFresh(int id) {
		super(id);
	}

    public void OnUsedInCrafting(EntityPlayer player, ItemStack stack)
    {
        if (stack.itemID == Block.pumpkin.blockID)
        {
            if (!player.worldObj.isRemote)
            {
                FCUtilsItem.EjectStackWithRandomVelocity(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(Item.pumpkinSeeds, 4, 0));
            }

            if (player.m_iTimesCraftedThisTick == 0)
            {
            	AddonUtilsSound.playSoundAtEntityWithVanillaFallback(player.worldObj, player, "deco.random.pumpkinCarve", 2, 1, "mob.slime.attack", 0.5F, (player.worldObj.rand.nextFloat() - player.worldObj.rand.nextFloat()) * 0.1F + 0.7F);
            }
        }
    }
}
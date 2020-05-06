package net.minecraft.src;

public class AddonItemBlockBloodLogReplace extends FCItemBlockBloodWood {
    public AddonItemBlockBloodLogReplace(int var1) {
		super(var1);
	}

	public void OnUsedInCrafting(EntityPlayer var1, ItemStack var2)
    {
        if (!var1.worldObj.isRemote)
        {
            if (var2.itemID == Block.planks.blockID)
            {
                FCUtilsItem.EjectStackWithRandomVelocity(var1.worldObj, var1.posX, var1.posY, var1.posZ, new ItemStack(FCBetterThanWolves.fcItemSawDust, 1, 0));
                FCUtilsItem.EjectStackWithRandomVelocity(var1.worldObj, var1.posX, var1.posY, var1.posZ, new ItemStack(FCBetterThanWolves.fcItemSoulDust, 1, 0));
                FCUtilsItem.EjectStackWithRandomVelocity(var1.worldObj, var1.posX, var1.posY, var1.posZ, new ItemStack(FCBetterThanWolves.fcItemBark, 1, 4));
            }
            else if (var2.itemID == Item.stick.itemID)
            {
                FCUtilsItem.EjectStackWithRandomVelocity(var1.worldObj, var1.posX, var1.posY, var1.posZ, new ItemStack(FCBetterThanWolves.fcItemSawDust, 3, 0));
                FCUtilsItem.EjectStackWithRandomVelocity(var1.worldObj, var1.posX, var1.posY, var1.posZ, new ItemStack(FCBetterThanWolves.fcItemSoulDust, 1, 0));
                FCUtilsItem.EjectStackWithRandomVelocity(var1.worldObj, var1.posX, var1.posY, var1.posZ, new ItemStack(FCBetterThanWolves.fcItemBark, 1, 4));
            }
            else if (var2.itemID == AddonDefs.bloodLog.blockID && var2.getItemDamage() == 0) {
                FCUtilsItem.EjectStackWithRandomVelocity(var1.worldObj, var1.posX, var1.posY, var1.posZ, new ItemStack(FCBetterThanWolves.fcItemBark, 1, 4));
            }
        }
    }
}

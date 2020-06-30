package net.minecraft.src;

public class FCItemBlockBloodWood extends ItemBlock
{
    public FCItemBlockBloodWood(int var1)
    {
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
        }
    }

    public int GetCampfireBurnTime(int var1)
    {
        return 0;
    }
}

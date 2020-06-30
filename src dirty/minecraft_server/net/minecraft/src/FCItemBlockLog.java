package net.minecraft.src;

public class FCItemBlockLog extends ItemMultiTextureTile
{
    public FCItemBlockLog(int var1, Block var2, String[] var3)
    {
        super(var1, var2, var3);
    }

    public void OnUsedInCrafting(int var1, EntityPlayer var2, ItemStack var3)
    {
        if (!var2.worldObj.isRemote)
        {
            if (var3.itemID == Block.planks.blockID)
            {
                FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(FCBetterThanWolves.fcItemSawDust, 2, 0));
                FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(FCBetterThanWolves.fcItemBark, 1, var3.getItemDamage()));
            }
            else if (var3.itemID == Item.stick.itemID)
            {
                FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(FCBetterThanWolves.fcItemSawDust, 4, 0));
                FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(FCBetterThanWolves.fcItemBark, 1, var1));
            }
        }
    }

    public int GetCampfireBurnTime(int var1)
    {
        return 0;
    }
}

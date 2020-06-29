package net.minecraft.src;

public class AddonItemBlockLogCherry extends FCItemBlockLog
{
    public AddonItemBlockLogCherry(int var1, Block var2, String[] var3)
    {
        super(var1, var2, var3);
    }

    public void OnUsedInCrafting(int var1, EntityPlayer var2, ItemStack var3)
    {
        if (!var2.worldObj.isRemote)
        {
            if (var3.itemID == this.getBlockID() && (var3.getItemDamage() == 1 || var3.getItemDamage() == 3) && var1 == 1)
            {
                FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(FCBetterThanWolves.fcItemBark, 1, 5));
            }
            else if (var3.itemID == Block.planks.blockID)
            {
                FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(FCBetterThanWolves.fcItemSawDust, 2, 0));
                
                if (var3.getItemDamage() == 1)
                	FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(FCBetterThanWolves.fcItemBark, 1, 5));
            }
            else if (var3.itemID == Item.stick.itemID)
            {
                FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(FCBetterThanWolves.fcItemSawDust, 2, 0));

                if (var3.getItemDamage() == 1)
                	FCUtilsItem.EjectStackWithRandomVelocity(var2.worldObj, var2.posX, var2.posY, var2.posZ, new ItemStack(FCBetterThanWolves.fcItemBark, 1, 5));
            }
        }
    }
}
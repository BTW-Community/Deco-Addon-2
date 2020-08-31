package net.minecraft.src;

public class DecoItemBlockLogCherry extends FCItemBlockLog
{
    public DecoItemBlockLogCherry(int var1, Block var2, String[] var3)
    {
        super(var1, var2, var3);
    }

    public void OnUsedInCrafting(int itemDamage, EntityPlayer player, ItemStack stackResult)
    {
        if (!player.worldObj.isRemote)
        {
            if (stackResult.itemID == this.getBlockID() && (itemDamage == 0 || itemDamage == 2) && (stackResult.getItemDamage() != 2))
            {
                FCUtilsItem.EjectStackWithRandomVelocity(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(FCBetterThanWolves.fcItemBark, 1, 5));
            }
            else if (stackResult.itemID == Block.planks.blockID)
            {
                FCUtilsItem.EjectStackWithRandomVelocity(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(FCBetterThanWolves.fcItemSawDust, 2, 0));
                
                if (itemDamage == 0 || itemDamage == 2)
                	FCUtilsItem.EjectStackWithRandomVelocity(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(FCBetterThanWolves.fcItemBark, 1, 5));
            }
            else if (stackResult.itemID == Item.stick.itemID)
            {
                FCUtilsItem.EjectStackWithRandomVelocity(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(FCBetterThanWolves.fcItemSawDust, 2, 0));

                if (itemDamage == 0 || itemDamage == 2)
                	FCUtilsItem.EjectStackWithRandomVelocity(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(FCBetterThanWolves.fcItemBark, 1, 5));
            }
        }
    }
}
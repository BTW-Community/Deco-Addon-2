package net.minecraft.src;

public class AddonItemBlockStem  extends FCItemBlockLog
{
	private int barkMeta;
	
    public AddonItemBlockStem(int var1, Block var2, String[] var3, int barkMeta)
    {
        super(var1, var2, var3);
        this.barkMeta = barkMeta;
    }

    public void OnUsedInCrafting(int itemDamage, EntityPlayer player, ItemStack stackResult)
    {
        if (!player.worldObj.isRemote)
        {
            if (stackResult.itemID == this.getBlockID() && (itemDamage == 0 || itemDamage == 2) && (stackResult.getItemDamage() != 2))
            {
                FCUtilsItem.EjectStackWithRandomVelocity(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(FCBetterThanWolves.fcItemBark, 1, barkMeta));
            }
            else if (stackResult.itemID == Block.planks.blockID)
            {
                FCUtilsItem.EjectStackWithRandomVelocity(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(FCBetterThanWolves.fcItemSawDust, 2, 0));
                
                if (itemDamage == 0 || itemDamage == 2)
                	FCUtilsItem.EjectStackWithRandomVelocity(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(FCBetterThanWolves.fcItemBark, 1, barkMeta));
            }
            else if (stackResult.itemID == Item.stick.itemID)
            {
                FCUtilsItem.EjectStackWithRandomVelocity(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(FCBetterThanWolves.fcItemSawDust, 2, 0));

                if (itemDamage == 0 || itemDamage == 2)
                	FCUtilsItem.EjectStackWithRandomVelocity(player.worldObj, player.posX, player.posY, player.posZ, new ItemStack(FCBetterThanWolves.fcItemBark, 1, barkMeta));
            }
        }
    }
}

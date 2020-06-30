package net.minecraft.src;

public class FCItemGlassBottle extends ItemGlassBottle
{
    public FCItemGlassBottle(int var1)
    {
        super(var1);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        MovingObjectPosition var4 = FCUtilsMisc.GetMovingObjectPositionFromPlayerHitWaterAndLava(var2, var3, true);

        if (var4 == null)
        {
            return var1;
        }
        else
        {
            if (var4.typeOfHit == EnumMovingObjectType.TILE)
            {
                int var5 = var4.blockX;
                int var6 = var4.blockY;
                int var7 = var4.blockZ;

                if (!var2.canMineBlock(var3, var5, var6, var7))
                {
                    return var1;
                }

                if (!var3.canPlayerEdit(var5, var6, var7, var4.sideHit, var1))
                {
                    return var1;
                }

                if (var2.getBlockMaterial(var5, var6, var7) == Material.water)
                {
                    --var1.stackSize;

                    if (var1.stackSize <= 0)
                    {
                        return new ItemStack(Item.potion);
                    }

                    if (!var3.inventory.addItemStackToInventory(new ItemStack(Item.potion)))
                    {
                        var3.dropPlayerItem(new ItemStack(Item.potion.itemID, 1, 0));
                    }
                }
            }

            return var1;
        }
    }
}

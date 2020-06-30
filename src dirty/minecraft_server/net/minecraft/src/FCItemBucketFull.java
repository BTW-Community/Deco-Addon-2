package net.minecraft.src;

public abstract class FCItemBucketFull extends FCItemBucket
{
    public FCItemBucketFull(int var1)
    {
        super(var1);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        MovingObjectPosition var4 = this.getMovingObjectPositionFromPlayer(var2, var3, false);

        if (var4 != null && var4.typeOfHit == EnumMovingObjectType.TILE && var2.canMineBlock(var3, var4.blockX, var4.blockY, var4.blockZ))
        {
            FCUtilsBlockPos var5 = new FCUtilsBlockPos(var4.blockX, var4.blockY, var4.blockZ, var4.sideHit);

            if (var3.canPlayerEdit(var5.i, var5.j, var5.k, var4.sideHit, var1) && this.AttemptPlaceContentsAtLocation(var2, var5.i, var5.j, var5.k) && !var3.capabilities.isCreativeMode)
            {
                return new ItemStack(Item.bucketEmpty);
            }
        }

        return var1;
    }

    protected abstract boolean AttemptPlaceContentsAtLocation(World var1, int var2, int var3, int var4);
}

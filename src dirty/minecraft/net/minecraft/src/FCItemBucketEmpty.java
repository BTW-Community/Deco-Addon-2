package net.minecraft.src;

public class FCItemBucketEmpty extends FCItemBucket
{
    public FCItemBucketEmpty(int var1)
    {
        super(var1);
        this.setMaxStackSize(16);
        this.setUnlocalizedName("bucket");
    }

    /**
     * Returns the blockID for this Item
     */
    public int getBlockID()
    {
        return FCBetterThanWolves.fcBlockBucketEmpty.blockID;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        MovingObjectPosition var4 = FCUtilsMisc.GetMovingObjectPositionFromPlayerHitWaterAndLava(var2, var3, true);

        if (var4 != null && var4.typeOfHit == EnumMovingObjectType.TILE)
        {
            int var5 = var4.blockX;
            int var6 = var4.blockY;
            int var7 = var4.blockZ;
            var2.getBlockId(var5, var6, var7);

            if (var2.getBlockMaterial(var5, var6, var7) == Material.water)
            {
                if (FCUtilsMisc.DoesWaterHaveValidSource(var2, var5, var6, var7, 128))
                {
                    if (--var1.stackSize <= 0)
                    {
                        return new ItemStack(Item.bucketWater);
                    }

                    if (!var3.inventory.addItemStackToInventory(new ItemStack(Item.bucketWater)))
                    {
                        var3.dropPlayerItem(new ItemStack(Item.bucketWater.itemID, 1, 0));
                    }
                }

                return var1;
            }

            if (var2.getBlockMaterial(var5, var6, var7) == Material.lava)
            {
                var3.dealFireDamage(1);
                var2.playSoundEffect((double)var5 + 0.5D, (double)var6 + 0.5D, (double)var7 + 0.5D, "random.fizz", 0.5F, 2.6F + (var2.rand.nextFloat() - var2.rand.nextFloat()) * 0.8F);

                if (var2.isRemote)
                {
                    for (int var9 = 0; var9 < 8; ++var9)
                    {
                        var2.spawnParticle("largesmoke", (double)var5 + Math.random(), (double)var6 + Math.random(), (double)var7 + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                }

                return var1;
            }
        }

        return var1;
    }

    private boolean IsPlayerClickingOnWaterOrLava(ItemStack var1, World var2, EntityPlayer var3)
    {
        MovingObjectPosition var4 = FCUtilsMisc.GetMovingObjectPositionFromPlayerHitWaterAndLava(var2, var3, true);

        if (var4 != null && var4.typeOfHit == EnumMovingObjectType.TILE)
        {
            Material var5 = var2.getBlockMaterial(var4.blockX, var4.blockY, var4.blockZ);

            if (var5 == Material.water || var5 == Material.lava)
            {
                return true;
            }
        }

        return false;
    }
}

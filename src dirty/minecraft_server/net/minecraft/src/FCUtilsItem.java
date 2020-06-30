package net.minecraft.src;

public class FCUtilsItem
{
    public static void EjectStackWithRandomOffset(World var0, int var1, int var2, int var3, ItemStack var4)
    {
        float var5 = var0.rand.nextFloat() * 0.7F + 0.15F;
        float var6 = var0.rand.nextFloat() * 0.2F + 0.1F;
        float var7 = var0.rand.nextFloat() * 0.7F + 0.15F;
        EjectStackWithRandomVelocity(var0, (double)((float)var1 + var5), (double)((float)var2 + var6), (double)((float)var3 + var7), var4);
    }

    public static void EjectSingleItemWithRandomOffset(World var0, int var1, int var2, int var3, int var4, int var5)
    {
        ItemStack var6 = new ItemStack(var4, 1, var5);
        EjectStackWithRandomOffset(var0, var1, var2, var3, var6);
    }

    public static void EjectStackWithRandomVelocity(World var0, double var1, double var3, double var5, ItemStack var7)
    {
        EntityItem var8 = new EntityItem(var0, var1, var3, var5, var7);
        float var9 = 0.05F;
        var8.motionX = (double)((float)var0.rand.nextGaussian() * var9);
        var8.motionY = (double)((float)var0.rand.nextGaussian() * var9 + 0.2F);
        var8.motionZ = (double)((float)var0.rand.nextGaussian() * var9);
        var8.delayBeforeCanPickup = 10;
        var0.spawnEntityInWorld(var8);
    }

    public static void EjectSingleItemWithRandomVelocity(World var0, float var1, float var2, float var3, int var4, int var5)
    {
        ItemStack var6 = new ItemStack(var4, 1, var5);
        EjectStackWithRandomVelocity(var0, (double)var1, (double)var2, (double)var3, var6);
    }

    public static void DropStackAsIfBlockHarvested(World var0, int var1, int var2, int var3, ItemStack var4)
    {
        float var5 = 0.7F;
        double var6 = (double)(var0.rand.nextFloat() * var5) + (double)(1.0F - var5) * 0.5D;
        double var8 = (double)(var0.rand.nextFloat() * var5) + (double)(1.0F - var5) * 0.5D;
        double var10 = (double)(var0.rand.nextFloat() * var5) + (double)(1.0F - var5) * 0.5D;
        EntityItem var12 = new EntityItem(var0, (double)var1 + var6, (double)var2 + var8, (double)var3 + var10, var4);
        var12.delayBeforeCanPickup = 10;
        var0.spawnEntityInWorld(var12);
    }

    public static void DropSingleItemAsIfBlockHarvested(World var0, int var1, int var2, int var3, int var4, int var5)
    {
        ItemStack var6 = new ItemStack(var4, 1, var5);
        DropStackAsIfBlockHarvested(var0, var1, var2, var3, var6);
    }

    public static void EjectStackAroundBlock(World var0, int var1, int var2, int var3, ItemStack var4)
    {
        int var5 = var0.rand.nextInt(6);
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var1, var2, var3);
        FCUtilsBlockPos var7 = new FCUtilsBlockPos();

        for (int var8 = 0; var8 < 6; ++var8)
        {
            var7.Set(var1, var2, var3);
            var7.AddFacingAsOffset(var5);

            if (FCUtilsWorld.IsReplaceableBlock(var0, var7.i, var7.j, var7.k))
            {
                var6.Set(var7);
                break;
            }

            ++var5;

            if (var5 >= 6)
            {
                var5 = 0;
            }
        }

        DropStackAsIfBlockHarvested(var0, var6.i, var6.j, var6.k, var4);
    }

    public static void EjectStackFromBlockTowardsFacing(World var0, int var1, int var2, int var3, ItemStack var4, int var5)
    {
        Vec3 var6 = Vec3.createVectorHelper(var0.rand.nextDouble() * 0.7D + 0.15D, 1.2D + var0.rand.nextDouble() * 0.1D, var0.rand.nextDouble() * 0.7D + 0.15D);
        var6.TiltAsBlockPosToFacingAlongJ(var5);
        EntityItem var7 = new EntityItem(var0, (double)var1 + var6.xCoord, (double)var2 + var6.yCoord, (double)var3 + var6.zCoord, var4);

        if (var5 < 2)
        {
            var7.motionX = var0.rand.nextDouble() * 0.1D - 0.05D;
            var7.motionZ = var0.rand.nextDouble() * 0.1D - 0.05D;

            if (var5 == 0)
            {
                var7.motionY = 0.0D;
            }
            else
            {
                var7.motionY = 0.2D;
            }
        }
        else
        {
            Vec3 var8 = Vec3.createVectorHelper(var0.rand.nextDouble() * 0.1D - 0.05D, 0.2D, var0.rand.nextDouble() * -0.05D - 0.05D);
            var8.RotateAsVectorAroundJToFacing(var5);
            var7.motionX = var8.xCoord;
            var7.motionY = var8.yCoord;
            var7.motionZ = var8.zCoord;
        }

        var7.delayBeforeCanPickup = 10;
        var0.spawnEntityInWorld(var7);
    }

    public static void GivePlayerStackOrEjectFromTowardsFacing(EntityPlayer var0, ItemStack var1, int var2, int var3, int var4, int var5)
    {
        if (var0.inventory.addItemStackToInventory(var1))
        {
            var0.worldObj.playSoundAtEntity(var0, "random.pop", 0.2F, ((var0.rand.nextFloat() - var0.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        }
        else if (!var0.worldObj.isRemote)
        {
            EjectStackFromBlockTowardsFacing(var0.worldObj, var2, var3, var4, var1, var5);
        }
    }

    public static void GivePlayerStackOrEjectFavorEmptyHand(EntityPlayer var0, ItemStack var1, int var2, int var3, int var4)
    {
        if (var0.AddStackToCurrentHeldStackIfEmpty(var1))
        {
            var0.worldObj.playSoundAtEntity(var0, "random.pop", 0.2F, ((var0.rand.nextFloat() - var0.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        }
        else
        {
            GivePlayerStackOrEject(var0, var1, var2, var3, var4);
        }
    }

    public static void GivePlayerStackOrEject(EntityPlayer var0, ItemStack var1, int var2, int var3, int var4)
    {
        if (var0.inventory.addItemStackToInventory(var1))
        {
            var0.worldObj.playSoundAtEntity(var0, "random.pop", 0.2F, ((var0.rand.nextFloat() - var0.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        }
        else if (!var0.worldObj.isRemote)
        {
            EjectStackWithRandomOffset(var0.worldObj, var2, var3, var4, var1);
        }
    }

    public static void GivePlayerStackOrEject(EntityPlayer var0, ItemStack var1)
    {
        if (var0.inventory.addItemStackToInventory(var1))
        {
            var0.worldObj.playSoundAtEntity(var0, "random.pop", 0.2F, ((var0.rand.nextFloat() - var0.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
        }
        else if (!var0.worldObj.isRemote)
        {
            EjectStackWithRandomVelocity(var0.worldObj, var0.posX, var0.posY, var0.posZ, var1);
        }
    }
}

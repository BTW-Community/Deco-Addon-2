package net.minecraft.src;

public abstract class FCItemThrowable extends Item
{
    public FCItemThrowable(int var1)
    {
        super(var1);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (!var3.capabilities.isCreativeMode)
        {
            --var1.stackSize;
        }

        var2.playSoundAtEntity(var3, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!var2.isRemote)
        {
            this.SpawnThrownEntity(var1, var2, var3);
        }

        return var1;
    }

    public boolean OnItemUsedByBlockDispenser(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(0, 0, 0, var6);
        double var8 = (double)var3 + (double)var7.i * 0.6D + 0.5D;
        double var10 = (double)var4 + (double)var7.j * 0.6D + 0.5D;
        double var12 = (double)var5 + (double)var7.k * 0.6D + 0.5D;
        double var14;

        if (var6 > 2)
        {
            var14 = 0.1D;
        }
        else
        {
            var14 = (double)var7.j;
        }

        EntityThrowable var16 = this.GetEntityFiredByByBlockDispenser(var2, var8, var10, var12);
        var16.setThrowableHeading((double)var7.i, var14, (double)var7.k, 1.1F, 6.0F);
        var2.spawnEntityInWorld(var16);
        var2.playAuxSFX(1002, var3, var4, var5, 0);
        return true;
    }

    protected abstract void SpawnThrownEntity(ItemStack var1, World var2, EntityPlayer var3);

    protected abstract EntityThrowable GetEntityFiredByByBlockDispenser(World var1, double var2, double var4, double var6);
}

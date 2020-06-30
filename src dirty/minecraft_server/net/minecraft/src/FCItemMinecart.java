package net.minecraft.src;

public class FCItemMinecart extends ItemMinecart
{
    public FCItemMinecart(int var1, int var2)
    {
        super(var1, var2);
    }

    public boolean OnItemUsedByBlockDispenser(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(0, 0, 0, var6);
        double var8 = (double)var3 + (double)var7.i * 1.35D + 0.5D;
        double var10 = (double)(var4 + var7.j);
        double var12 = (double)var5 + (double)var7.k * 1.35D + 0.5D;
        EntityMinecart var14 = EntityMinecart.createMinecart(var2, var8, var10, var12, ((ItemMinecart)var1.getItem()).minecartType);
        var2.spawnEntityInWorld(var14);
        var2.playAuxSFX(1000, var3, var4, var5, 0);
        return true;
    }
}

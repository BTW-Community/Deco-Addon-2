package net.minecraft.src;

public class FCItemBoat extends ItemBoat
{
    public FCItemBoat(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetIncineratedInCrucible();
        this.SetFurnaceBurnTime(5 * FCEnumFurnaceBurnTime.PLANKS_JUNGLE.m_iBurnTime / 2);
        this.setUnlocalizedName("boat");
    }

    public boolean OnItemUsedByBlockDispenser(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(0, 0, 0, var6);
        double var8 = (double)var3 + (double)var7.i * 1.6D + 0.5D;
        double var10 = (double)(var4 + var7.j);
        double var12 = (double)var5 + (double)var7.k * 1.6D + 0.5D;
        double var14 = (double)(var4 + var7.j);
        EntityBoat var16 = new EntityBoat(var2, var8, var10, var12);
        var2.spawnEntityInWorld(var16);
        var2.playAuxSFX(1000, var3, var4, var5, 0);
        return true;
    }
}

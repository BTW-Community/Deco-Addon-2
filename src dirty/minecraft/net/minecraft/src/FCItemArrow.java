package net.minecraft.src;

public class FCItemArrow extends Item
{
    public FCItemArrow(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetBellowsBlowDistance(1);
        this.SetIncineratedInCrucible();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.SHAFT);
        this.SetFilterableProperties(4);
        this.setUnlocalizedName("arrow");
        this.setCreativeTab(CreativeTabs.tabCombat);
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
            var14 = 0.10000000149011612D;
        }
        else
        {
            var14 = (double)var7.j;
        }

        EntityArrow var16 = this.GetFiredArrowEntity(var2, var8, var10, var12);
        var16.setThrowableHeading((double)var7.i, var14, (double)var7.k, 1.1F, 6.0F);
        var2.spawnEntityInWorld(var16);
        var2.playAuxSFX(1002, var3, var4, var5, 0);
        return true;
    }

    EntityArrow GetFiredArrowEntity(World var1, double var2, double var4, double var6)
    {
        EntityArrow var8 = new EntityArrow(var1, var2, var4, var6);
        var8.canBePickedUp = 1;
        return var8;
    }
}

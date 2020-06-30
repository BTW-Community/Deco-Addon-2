package net.minecraft.src;

public class FCItemArrowBroadhead extends FCItemArrow
{
    public FCItemArrowBroadhead(int var1)
    {
        super(var1);
        this.SetNeutralBuoyant();
        this.SetBellowsBlowDistance(0);
        this.SetNotIncineratedInCrucible();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.NONE);
        this.setUnlocalizedName("fcItemArrowBroadhead");
    }

    EntityArrow GetFiredArrowEntity(World var1, double var2, double var4, double var6)
    {
        FCEntityBroadheadArrow var8 = new FCEntityBroadheadArrow(var1, var2, var4, var6);
        var8.canBePickedUp = 1;
        return var8;
    }
}

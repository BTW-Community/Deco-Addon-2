package net.minecraft.src;

public class FCItemArrowRotten extends FCItemArrow
{
    public FCItemArrowRotten(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcItemArrowRotten");
    }

    EntityArrow GetFiredArrowEntity(World var1, double var2, double var4, double var6)
    {
        FCEntityRottenArrow var8 = new FCEntityRottenArrow(var1, var2, var4, var6);
        var8.canBePickedUp = 2;
        return var8;
    }
}

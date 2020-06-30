package net.minecraft.src;

public class FCItemFoodHighRes extends FCItemFood
{
    public FCItemFoodHighRes(int var1, int var2, float var3, boolean var4, String var5)
    {
        super(var1, var2, var3, var4, var5);
    }

    public FCItemFoodHighRes(int var1, int var2, float var3, boolean var4, String var5, boolean var6)
    {
        super(var1, var2, var3, var4, var5, var6);
    }

    public int GetHungerRestored()
    {
        return this.getHealAmount();
    }
}

package net.minecraft.src;

import java.util.Random;

public class FCUtilsRandomItemStack extends WeightedRandomItem
{
    private ItemStack m_stack = null;
    private int m_iMinStackSize;
    private int m_iMaxStackSize;

    public FCUtilsRandomItemStack(int var1, int var2, int var3, int var4, int var5)
    {
        super(var5);
        this.m_stack = new ItemStack(var1, 1, var2);
        this.m_iMinStackSize = var3;
        this.m_iMaxStackSize = var4;
    }

    public static ItemStack GetRandomStack(Random var0, FCUtilsRandomItemStack[] var1)
    {
        FCUtilsRandomItemStack var2 = (FCUtilsRandomItemStack)WeightedRandom.getRandomItem(var0, var1);
        int var3 = var2.m_iMinStackSize + var0.nextInt(var2.m_iMinStackSize - var2.m_iMinStackSize + 1);
        ItemStack var4 = var2.m_stack.copy();
        var4.stackSize = var3;
        return var4;
    }
}

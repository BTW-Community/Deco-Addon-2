package net.minecraft.src;

import java.util.Random;

public class FCItemFlintAndSteel extends FCItemFireStarter
{
    private static final float m_fFlintAndSteelExhaustionPerUse = 0.01F;

    public FCItemFlintAndSteel(int var1)
    {
        super(var1, 64, 0.01F);
    }

    protected boolean CheckChanceOfStart(ItemStack var1, Random var2)
    {
        return var2.nextInt(4) == 0;
    }

    protected void PerformUseEffects(EntityPlayer var1)
    {
        var1.playSound("fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
    }
}

package net.minecraft.src;

import java.util.concurrent.Callable;

class CallableEffectDuration implements Callable
{
    final PotionEffect field_102037_a;

    final EntityLiving field_102036_b;

    CallableEffectDuration(EntityLiving par1EntityLiving, PotionEffect par2PotionEffect)
    {
        this.field_102036_b = par1EntityLiving;
        this.field_102037_a = par2PotionEffect;
    }

    public String func_102035_a()
    {
        return this.field_102037_a.getDuration() + "";
    }

    public Object call()
    {
        return this.func_102035_a();
    }
}

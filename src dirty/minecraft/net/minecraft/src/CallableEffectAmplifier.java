package net.minecraft.src;

import java.util.concurrent.Callable;

class CallableEffectAmplifier implements Callable
{
    final PotionEffect field_102040_a;

    final EntityLiving field_102039_b;

    CallableEffectAmplifier(EntityLiving par1EntityLiving, PotionEffect par2PotionEffect)
    {
        this.field_102039_b = par1EntityLiving;
        this.field_102040_a = par2PotionEffect;
    }

    public String func_102038_a()
    {
        return this.field_102040_a.getAmplifier() + "";
    }

    public Object call()
    {
        return this.func_102038_a();
    }
}

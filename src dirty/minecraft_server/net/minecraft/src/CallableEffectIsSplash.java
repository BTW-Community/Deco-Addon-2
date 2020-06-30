package net.minecraft.src;

import java.util.concurrent.Callable;

class CallableEffectIsSplash implements Callable
{
    final PotionEffect field_102043_a;

    final EntityLiving field_102042_b;

    CallableEffectIsSplash(EntityLiving par1EntityLiving, PotionEffect par2PotionEffect)
    {
        this.field_102042_b = par1EntityLiving;
        this.field_102043_a = par2PotionEffect;
    }

    public String func_102041_a()
    {
        return this.field_102043_a.isSplashPotionEffect() + "";
    }

    public Object call()
    {
        return this.func_102041_a();
    }
}

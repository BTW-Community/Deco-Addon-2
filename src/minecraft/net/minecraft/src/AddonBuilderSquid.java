package net.minecraft.src;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.DynamicType.Loaded;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;

public class AddonBuilderSquid {
	public AddonBuilderSquid() {
		Class<?> dynamicSquid = new ByteBuddy()
				.redefine(FCEntitySquid.class)
				.method(ElementMatchers.named("dropFewItems").and(ElementMatchers.takesArgument(0, Boolean.class)).and(ElementMatchers.takesArgument(1,  Integer.class)))
					.intercept(MethodDelegation.to(this.getClass()))
				.make()
				.load(getClass().getClassLoader(), ClassReloadingStrategy.fromInstalledAgent())
				.getLoaded();
	}

    
    protected void dropFewItems(boolean var1, int var2)
    {
    	System.out.println("Addon squid killed");
    	/*
        int var3 = this.rand.nextInt(3 + var2) + 1;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            this.entityDropItem(new ItemStack(Item.dyePowder, 1, 0), 0.0F);
        }

        if (this.rand.nextInt(8) - var2 <= 0)
        {
            this.dropItem(FCBetterThanWolves.fcItemMysteriousGland.itemID, 1);
        }*/
    }
}
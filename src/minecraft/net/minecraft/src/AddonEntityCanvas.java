package net.minecraft.src;

public class AddonEntityCanvas extends FCEntityCanvas {

	public AddonEntityCanvas(World var1) {
		super(var1);
	}

	public AddonEntityCanvas(World var1, int var2, int var3, int var4, int var5) {
		super(var1, var2, var3, var4, var5);
	}

	public AddonEntityCanvas(World var1, int var2, int var3, int var4, int var5, int var6) {
		super(var1, var2, var3, var4, var5, var6);
	}

	public AddonEntityCanvas(World var1, int var2, int var3, int var4, int var5, String var6) {
		super(var1, var2, var3, var4, var5, var6);
	}

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource damageSource, int var2)
    {
        if (!this.isDead && !this.worldObj.isRemote)
        {
        	AddonUtilsSound.playSoundAtEntityWithNullFallback(this.worldObj, this, "deco.misc.painting.break", 1, 1);
            this.setDead();
            this.setBeenAttacked();
            EntityPlayer player = null;

            if (damageSource.getEntity() instanceof EntityPlayer)
            {
                player = (EntityPlayer)damageSource.getEntity();
            }

            if (player != null && player.capabilities.isCreativeMode)
            {
                return true;
            }
            
            this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(FCBetterThanWolves.fcItemCanvas)));
        }

        return true;
    }
}
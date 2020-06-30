package net.minecraft.src;

public class AddonEntityPainting extends EntityPainting {
	public AddonEntityPainting(World par1World, int par2, int par3, int par4, int par5, String par6Str) {
		super(par1World, par2, par3, par4, par5, par6Str);
	}

	public AddonEntityPainting(World par1World, int par2, int par3, int par4, int par5) {
		super(par1World, par2, par3, par4, par5);
	}

	public AddonEntityPainting(World par1World) {
		super(par1World);
	}

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            if (!this.isDead && !this.worldObj.isRemote)
            {
            	AddonUtilsSound.playSoundAtEntityWithNullFallback(this.worldObj, this, "deco.misc.painting.break", 1, 1);
                this.setDead();
                this.setBeenAttacked();
                EntityPlayer var3 = null;

                if (par1DamageSource.getEntity() instanceof EntityPlayer)
                {
                    var3 = (EntityPlayer)par1DamageSource.getEntity();
                }

                if (var3 != null && var3.capabilities.isCreativeMode)
                {
                    return true;
                }

                this.dropItemStack();
            }

            return true;
        }
    }
}
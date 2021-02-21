package net.minecraft.src;

public class DecoEntityCanvas extends FCEntityCanvas {

	public DecoEntityCanvas(World var1) {
		super(var1);
	}

	public DecoEntityCanvas(World var1, int var2, int var3, int var4, int var5) {
		super(var1, var2, var3, var4, var5);
	}

	public DecoEntityCanvas(World var1, int var2, int var3, int var4, int var5, int var6) {
		super(var1, var2, var3, var4, var5, var6);
	}

	public DecoEntityCanvas(World var1, int var2, int var3, int var4, int var5, String var6) {
		super(var1, var2, var3, var4, var5, var6);
	}

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource damageSource, int var2)
    {
        if (!this.isDead && !this.worldObj.isRemote)
        {
        	this.worldObj.playAuxSFX(DecoManager.decoPaintingBreakAuxFXID, this.xPosition, this.yPosition, this.zPosition, 0);
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
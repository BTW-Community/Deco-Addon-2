package net.minecraft.src;

public class AddonEntityItemFrame extends EntityItemFrame {
	public AddonEntityItemFrame(World world) {
		super(world);
	}

	public AddonEntityItemFrame(World world, int x, int y, int z, int par5) {
		super(world, x, y, z, par5);
	}

    protected void entityInit()
    {
    	super.entityInit();
    }
    
    public boolean attackEntityFrom(DamageSource damageSource, int par2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            if (!this.isDead && !this.worldObj.isRemote)
            {
            	if (getDisplayedItem() != null) {
                    EntityPlayer player = null;

                    if (damageSource.getEntity() instanceof EntityPlayer)
                    {
                        player = (EntityPlayer)damageSource.getEntity();
                    }
            		
                    ItemStack stack = this.getDisplayedItem();
                    stack.setItemFrame(null);

                    if (player != null && !player.capabilities.isCreativeMode)
                    {
                        FCUtilsItem.EjectStackFromBlockTowardsFacing(this.worldObj, this.xPosition, this.yPosition, this.zPosition, stack, getItemEjectFacingFromHangingDirection(this.hangingDirection));
                    }

                	this.worldObj.playAuxSFX(AddonManager.addonItemFrameRemoveItemAuxFXID, this.xPosition, this.yPosition, this.zPosition, 0);
                    this.setDisplayedItem(null);
            	}
            	else {
                	this.worldObj.playAuxSFX(AddonManager.addonItemFrameBreakAuxFXID, this.xPosition, this.yPosition, this.zPosition, 0);
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

                    this.dropItemStack();
            	}
            }

            return true;
        }
    }
    
    public int getItemEjectFacingFromHangingDirection(int hangDir) {
    	switch (hangDir) {
    	case 0:
    		return 3;
    	case 1:
    		return 4;
    	case 2:
    		return 2;
    	case 3:
    		return 5;
    	default:
    		return 2;
    	}
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        if (this.getDisplayedItem() == null)
        {
            ItemStack var2 = par1EntityPlayer.getHeldItem();

            if (var2 != null && !this.worldObj.isRemote)
            {
                this.setDisplayedItem(var2);
            	this.worldObj.playAuxSFX(AddonManager.addonItemFrameAddItemAuxFXID, this.xPosition, this.yPosition, this.zPosition, 0);

                if (!par1EntityPlayer.capabilities.isCreativeMode && --var2.stackSize <= 0)
                {
                    par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
                }
            }
        }
        else if (!this.worldObj.isRemote)
        {
            this.setItemRotation(this.getRotation() + 1);
        	this.worldObj.playAuxSFX(AddonManager.addonItemFrameRotateItemAuxFXID, this.xPosition, this.yPosition, this.zPosition, 0);
        }

        return true;
    }

    public void setDisplayedItem(ItemStack stack)
    {
    	if (stack != null) {
    		stack = stack.copy();
        	stack.stackSize = 1;
        	stack.setItemFrame(this);
    	}
        this.getDataWatcher().updateObject(2, stack);
        this.getDataWatcher().setObjectWatched(2);
    }
}
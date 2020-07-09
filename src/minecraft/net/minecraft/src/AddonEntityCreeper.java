package net.minecraft.src;

public class AddonEntityCreeper extends FCEntityCreeper {
	public AddonEntityCreeper(World var1) {
		super(var1);
	}

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer var1)
    {
        ItemStack var2 = var1.inventory.getCurrentItem();

        if (var2 != null && (var2.itemID == Item.shears.itemID || var2.itemID == AddonDefs.shearsDiamond.itemID) && this.GetNeuteredState() == 0)
        {
            if (!this.worldObj.isRemote)
            {
                this.SetNeuteredState(1);
                EntityItem var3 = this.entityDropItem(new ItemStack(FCBetterThanWolves.fcItemCreeperOysters, 1), 0.25F);
                var3.motionY += (double)(this.rand.nextFloat() * 0.025F);
                var3.motionX += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
                var3.motionZ += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
                int var4 = MathHelper.floor_double(this.posX);
                int var5 = MathHelper.floor_double(this.posY);
                int var6 = MathHelper.floor_double(this.posZ);
                this.worldObj.playAuxSFX(2258, var4, var5, var6, 0);
            }

            var2.damageItem(10, var1);

            if (var2.stackSize <= 0)
            {
                var1.inventory.mainInventory[var1.inventory.currentItem] = null;
            }

            return true;
        }
        else
        {
            return super.interact(var1);
        }
    }
}
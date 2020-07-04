package net.minecraft.src;

public class AddonEntitySheep extends FCEntitySheep {
	public AddonEntitySheep(World var1) {
		super(var1);
	}

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer var1)
    {
        ItemStack var2 = var1.inventory.getCurrentItem();

        if (var2 != null && (var2.itemID == Item.shears.itemID || var2.itemID == AddonDefs.shearsDiamond.itemID) && !this.getSheared() && !this.isChild())
        {
            if (!this.worldObj.isRemote)
            {
                this.setSheared(true);
                int var3 = 1 + this.rand.nextInt(3);

                for (int var4 = 0; var4 < var3; ++var4)
                {
                    EntityItem var5 = this.entityDropItem(new ItemStack(FCBetterThanWolves.fcItemWool.itemID, 1, BlockCloth.getDyeFromBlock(this.getFleeceColor())), 1.0F);
                    var5.motionY += (double)(this.rand.nextFloat() * 0.05F);
                    var5.motionX += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
                    var5.motionZ += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
                }
            }

            var2.damageItem(1, var1);
            this.playSound("mob.sheep.shear", 1.0F, 1.0F);
            this.attackEntityFrom(DamageSource.generic, 0);

            if (var2.stackSize <= 0)
            {
                var1.inventory.mainInventory[var1.inventory.currentItem] = null;
            }
        }

        return this.EntityAnimalInteract(var1);
    }
}
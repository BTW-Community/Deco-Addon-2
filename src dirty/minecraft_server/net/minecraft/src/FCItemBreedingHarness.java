package net.minecraft.src;

public class FCItemBreedingHarness extends Item
{
    public FCItemBreedingHarness(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.setUnlocalizedName("fcItemHarnessBreeding");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    /**
     * Called when a player right clicks an entity with an item.
     */
    public boolean useItemOnEntity(ItemStack var1, EntityLiving var2)
    {
        if (var2 instanceof EntityAnimal)
        {
            Object var3 = (EntityAnimal)var2;

            if (!((EntityAnimal)var3).isChild() && !((EntityAnimal)var3).getWearingBreedingHarness())
            {
                if (var2 instanceof FCEntityCow)
                {
                    ((EntityAnimal)var3).setDead();

                    if (!((EntityAnimal)var3).worldObj.isRemote)
                    {
                        FCEntityCow var4 = new FCEntityCow(((EntityAnimal)var3).worldObj);
                        var4.setLocationAndAngles(((EntityAnimal)var3).posX, ((EntityAnimal)var3).posY, ((EntityAnimal)var3).posZ, ((EntityAnimal)var3).rotationYaw, ((EntityAnimal)var3).rotationPitch);
                        var4.setEntityHealth(((EntityAnimal)var3).getHealth());
                        var4.renderYawOffset = ((EntityAnimal)var3).renderYawOffset;
                        ((EntityAnimal)var3).worldObj.spawnEntityInWorld(var4);
                        var3 = var4;
                    }
                    else
                    {
                        ((EntityAnimal)var3).worldObj.spawnParticle("largeexplode", ((EntityAnimal)var3).posX, ((EntityAnimal)var3).posY + (double)(((EntityAnimal)var3).height / 2.0F), ((EntityAnimal)var3).posZ, 0.0D, 0.0D, 0.0D);
                    }
                }
                else if (var2 instanceof FCEntitySheep)
                {
                    if (!((EntityAnimal)var3).worldObj.isRemote)
                    {
                        FCEntitySheep var5 = (FCEntitySheep)var3;
                        var5.setSheared(true);
                    }
                }
                else if (!(var2 instanceof FCEntityPig))
                {
                    return false;
                }

                --var1.stackSize;

                if (!((EntityAnimal)var3).worldObj.isRemote)
                {
                    ((EntityAnimal)var3).setWearingBreedingHarness(true);
                }

                return true;
            }
        }

        return false;
    }
}

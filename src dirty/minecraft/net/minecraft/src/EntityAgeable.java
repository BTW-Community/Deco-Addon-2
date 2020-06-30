package net.minecraft.src;

public abstract class EntityAgeable extends EntityCreature
{
    private float m_fAdultWidth = -1.0F;
    private float m_fAdultHeight;

    public EntityAgeable(World par1World)
    {
        super(par1World);
    }

    public abstract EntityAgeable createChild(EntityAgeable var1);

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        return this.EntityAgeableInteract(par1EntityPlayer);
    }

    public boolean EntityAgeableInteract(EntityPlayer var1)
    {
        ItemStack var2 = var1.inventory.getCurrentItem();

        if (var2 != null && var2.itemID == Item.monsterPlacer.itemID && !this.worldObj.isRemote)
        {
            Class var3 = EntityList.getClassFromID(var2.getItemDamage());

            if (var3 != null && var3.isAssignableFrom(this.getClass()))
            {
                EntityAgeable var4 = this.createChild(this);

                if (var4 != null)
                {
                    var4.setGrowingAge(-this.GetTicksForChildToGrow());
                    var4.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);
                    this.worldObj.spawnEntityInWorld(var4);

                    if (var2.hasDisplayName())
                    {
                        var4.func_94058_c(var2.getDisplayName());
                    }

                    if (!var1.capabilities.isCreativeMode)
                    {
                        --var2.stackSize;

                        if (var2.stackSize <= 0)
                        {
                            var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
                        }
                    }
                }
            }
        }

        return super.interact(var1);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(12, new Integer(0));
    }

    /**
     * The age value may be negative or positive or zero. If it's negative, it get's incremented on each tick, if it's
     * positive, it get's decremented each tick. Don't confuse this with EntityLiving.getAge. With a negative value the
     * Entity is considered a child.
     */
    public int getGrowingAge()
    {
        return this.dataWatcher.getWatchableObjectInt(12);
    }

    /**
     * The age value may be negative or positive or zero. If it's negative, it get's incremented on each tick, if it's
     * positive, it get's decremented each tick. With a negative value the Entity is considered a child.
     */
    public void setGrowingAge(int par1)
    {
        this.dataWatcher.updateObject(12, Integer.valueOf(par1));
        this.AdjustSizeForAge(this.isChild());
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Age", this.getGrowingAge());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setGrowingAge(par1NBTTagCompound.getInteger("Age"));
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (this.worldObj.isRemote)
        {
            this.AdjustSizeForAge(this.isChild());
        }
        else
        {
            int var1 = this.getGrowingAge();

            if (var1 < 0)
            {
                if (!this.CanChildGrow())
                {
                    return;
                }

                ++var1;

                if (var1 == 0)
                {
                    AxisAlignedBB var2 = AxisAlignedBB.getAABBPool().getAABB(this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.minX + (double)this.m_fAdultWidth, this.boundingBox.minY + (double)this.m_fAdultHeight, this.boundingBox.minZ + (double)this.m_fAdultWidth);

                    if (!this.worldObj.getCollidingBoundingBoxes(this, var2).isEmpty())
                    {
                        var1 = -20;
                    }
                }

                this.setGrowingAge(var1);
            }
            else if (var1 > 0)
            {
                if (!this.CanLoveJuiceRegenerate())
                {
                    return;
                }

                --var1;
                this.setGrowingAge(var1);
            }
        }
    }

    /**
     * If Animal, checks if the age timer is negative
     */
    public boolean isChild()
    {
        return this.getGrowingAge() < 0;
    }

    /**
     * Sets the width and height of the entity. Args: width, height
     */
    protected final void setSize(float par1, float par2)
    {
        boolean var3 = this.m_fAdultWidth > 0.0F;
        this.m_fAdultWidth = par1;
        this.m_fAdultHeight = par2;

        if (!var3)
        {
            this.AdjustedSizeToScale(1.0F);
        }
    }

    private void AdjustedSizeToScale(float var1)
    {
        super.setSize(this.m_fAdultWidth * var1, this.m_fAdultHeight * var1);
    }

    public void AdjustSizeForAge(boolean var1)
    {
        this.AdjustedSizeToScale(var1 ? 0.5F : 1.0F);
    }

    public boolean CanChildGrow()
    {
        return this.worldObj.provider.dimensionId != 1;
    }

    public boolean CanLoveJuiceRegenerate()
    {
        return true;
    }

    protected int GetTicksForChildToGrow()
    {
        return 24000;
    }
}

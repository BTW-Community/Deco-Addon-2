package net.minecraft.src;

import java.util.Iterator;
import net.minecraft.server.MinecraftServer;

public class EntityItem extends Entity
{
    /**
     * The age of this EntityItem (used to animate it up and down as well as expire it)
     */
    public int age;
    public int delayBeforeCanPickup;

    /** The health of this EntityItem. (For example, damage for tools) */
    private int health;

    /** The EntityItem's random initial float height. */
    public float hoverStart;
    private long m_lAbsoluteItemDespawnTime;

    public EntityItem(World par1World, double par2, double par4, double par6)
    {
        super(par1World);
        this.m_lAbsoluteItemDespawnTime = 0L;
        this.age = 0;
        this.health = 5;
        this.hoverStart = (float)(Math.random() * Math.PI * 2.0D);
        this.setSize(0.25F, 0.25F);
        this.yOffset = this.height / 2.0F;
        this.setPosition(par2, par4, par6);
        this.rotationYaw = (float)(Math.random() * 360.0D);
        this.motionX = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
        this.motionY = 0.20000000298023224D;
        this.motionZ = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
    }

    public EntityItem(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack)
    {
        this(par1World, par2, par4, par6);
        this.setEntityItemStack(par8ItemStack);
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    public EntityItem(World par1World)
    {
        super(par1World);
        this.m_lAbsoluteItemDespawnTime = 0L;
        this.age = 0;
        this.health = 5;
        this.hoverStart = (float)(Math.random() * Math.PI * 2.0D);
        this.setSize(0.25F, 0.25F);
        this.yOffset = this.height / 2.0F;
    }

    protected void entityInit()
    {
        this.getDataWatcher().addObjectByDataType(10, 5);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (this.delayBeforeCanPickup > 0)
        {
            --this.delayBeforeCanPickup;
        }

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.motionY -= 0.03999999910593033D;
        this.UpdateHardcoreBuoy();

        if (!this.worldObj.isRemote)
        {
            this.pushOutOfBlocks(this.posX, (this.boundingBox.minY + this.boundingBox.maxY) / 2.0D, this.posZ);
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        boolean var1 = (int)this.prevPosX != (int)this.posX || (int)this.prevPosY != (int)this.posY || (int)this.prevPosZ != (int)this.posZ;

        if (var1 || this.ticksExisted % 25 == 0)
        {
            if (this.worldObj.getBlockMaterial(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) == Material.lava)
            {
                this.motionY = 0.20000000298023224D;
                this.motionX = (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
                this.motionZ = (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
                this.playSound("random.fizz", 0.4F, 2.0F + this.rand.nextFloat() * 0.4F);
            }

            if (!this.worldObj.isRemote)
            {
                this.searchForOtherItemsNearby();
            }
        }

        float var2 = 0.98F;

        if (this.onGround)
        {
            var2 = 0.58800006F;
            int var3 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));

            if (var3 > 0)
            {
                var2 = Block.blocksList[var3].slipperiness * 0.98F;
            }
        }

        this.motionX *= (double)var2;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= (double)var2;

        if (this.onGround)
        {
            this.motionY *= -0.5D;
        }

        ++this.age;
        this.CheckForItemDespawn();
    }

    /**
     * Looks for other itemstacks nearby and tries to stack them together
     */
    private void searchForOtherItemsNearby()
    {
        Iterator var1 = this.worldObj.getEntitiesWithinAABB(EntityItem.class, this.boundingBox.expand(0.5D, 0.0D, 0.5D)).iterator();

        while (var1.hasNext())
        {
            EntityItem var2 = (EntityItem)var1.next();
            this.combineItems(var2);
        }
    }

    /**
     * Tries to merge this item with the item passed as the parameter. Returns true if successful. Either this item or
     * the other item will  be removed from the world.
     */
    public boolean combineItems(EntityItem par1EntityItem)
    {
        if (par1EntityItem == this)
        {
            return false;
        }
        else if (par1EntityItem.isEntityAlive() && this.isEntityAlive())
        {
            ItemStack var2 = this.getEntityItem();
            ItemStack var3 = par1EntityItem.getEntityItem();

            if (var3.getItem() != var2.getItem())
            {
                return false;
            }
            else if (var3.hasTagCompound() ^ var2.hasTagCompound())
            {
                return false;
            }
            else if (var3.hasTagCompound() && !var3.getTagCompound().equals(var2.getTagCompound()))
            {
                return false;
            }
            else if (var3.getItem().getHasSubtypes() && var3.getItemDamage() != var2.getItemDamage())
            {
                return false;
            }
            else if (var3.stackSize < var2.stackSize)
            {
                return par1EntityItem.combineItems(this);
            }
            else if (var3.stackSize + var2.stackSize > var3.getMaxStackSize())
            {
                return false;
            }
            else
            {
                var3.stackSize += var2.stackSize;
                par1EntityItem.delayBeforeCanPickup = Math.max(par1EntityItem.delayBeforeCanPickup, this.delayBeforeCanPickup);
                par1EntityItem.age = Math.min(par1EntityItem.age, this.age);
                par1EntityItem.setEntityItemStack(var3);
                this.setDead();
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * sets the age of the item so that it'll despawn one minute after it has been dropped (instead of five). Used when
     * items are dropped from players in creative mode
     */
    public void setAgeToCreativeDespawnTime()
    {
        this.age = 4800;
    }

    /**
     * Returns if this entity is in water and will end up adding the waters velocity to the entity
     */
    public boolean handleWaterMovement()
    {
        return this.worldObj.handleMaterialAcceleration(this.boundingBox, Material.water, this);
    }

    /**
     * Will deal the specified amount of damage to the entity if the entity isn't immune to fire damage. Args:
     * amountDamage
     */
    protected void dealFireDamage(int par1)
    {
        this.attackEntityFrom(DamageSource.inFire, par1);
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
        else if (this.getEntityItem() != null && this.getEntityItem().itemID == Item.netherStar.itemID && par1DamageSource.isExplosion())
        {
            return false;
        }
        else
        {
            this.setBeenAttacked();

            if (!this.worldObj.isRemote && !this.isDead && this.getEntityItem().getItem().itemID == FCBetterThanWolves.fcItemBlastingOil.itemID)
            {
                this.DetonateBlastingOil();
                return false;
            }
            else
            {
                this.health -= par2;

                if (this.health <= 0)
                {
                    this.setDead();
                }

                return false;
            }
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setShort("Health", (short)((byte)this.health));
        par1NBTTagCompound.setShort("Age", (short)this.age);

        if (this.getEntityItem() != null)
        {
            par1NBTTagCompound.setCompoundTag("Item", this.getEntityItem().writeToNBT(new NBTTagCompound()));
        }

        par1NBTTagCompound.setLong("fcDespawnTime", this.m_lAbsoluteItemDespawnTime);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        this.health = par1NBTTagCompound.getShort("Health") & 255;
        this.age = par1NBTTagCompound.getShort("Age");
        NBTTagCompound var2 = par1NBTTagCompound.getCompoundTag("Item");
        this.setEntityItemStack(ItemStack.loadItemStackFromNBT(var2));

        if (par1NBTTagCompound.hasKey("fcDespawnTime"))
        {
            this.m_lAbsoluteItemDespawnTime = par1NBTTagCompound.getLong("fcDespawnTime");
        }

        if (this.getEntityItem() == null)
        {
            this.setDead();
        }
    }

    /**
     * Called by a player entity when they collide with an entity
     */
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
    {
        if (!this.worldObj.isRemote)
        {
            ItemStack var2 = this.getEntityItem();
            int var3 = var2.stackSize;

            if (this.delayBeforeCanPickup == 0 && par1EntityPlayer.inventory.addItemStackToInventory(var2))
            {
                if (var2.itemID == Block.wood.blockID)
                {
                    par1EntityPlayer.triggerAchievement(AchievementList.mineWood);
                }

                if (var2.itemID == Item.leather.itemID)
                {
                    par1EntityPlayer.triggerAchievement(AchievementList.killCow);
                }

                if (var2.itemID == Item.diamond.itemID)
                {
                    par1EntityPlayer.triggerAchievement(AchievementList.diamonds);
                }

                if (var2.itemID == Item.blazeRod.itemID)
                {
                    par1EntityPlayer.triggerAchievement(AchievementList.blazeRod);
                }

                this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                par1EntityPlayer.onItemPickup(this, var3);

                if (var2.stackSize <= 0)
                {
                    this.setDead();
                }
            }
        }
    }

    /**
     * Gets the username of the entity.
     */
    public String getEntityName()
    {
        return StatCollector.translateToLocal("item." + this.getEntityItem().getItemName());
    }

    /**
     * If returns false, the item will not inflict any damage against entities.
     */
    public boolean canAttackWithItem()
    {
        return false;
    }

    /**
     * Teleports the entity to another dimension. Params: Dimension number to teleport to
     */
    public void travelToDimension(int par1)
    {
        super.travelToDimension(par1);

        if (!this.worldObj.isRemote)
        {
            this.searchForOtherItemsNearby();
        }
    }

    /**
     * Returns the ItemStack corresponding to the Entity (Note: if no item exists, will log an error but still return an
     * ItemStack containing Block.stone)
     */
    public ItemStack getEntityItem()
    {
        ItemStack var1 = this.getDataWatcher().getWatchableObjectItemStack(10);

        if (var1 == null)
        {
            if (this.worldObj != null)
            {
                this.worldObj.getWorldLogAgent().logSevere("Item entity " + this.entityId + " has no item?!");
            }

            return new ItemStack(Block.stone);
        }
        else
        {
            return var1;
        }
    }

    /**
     * Sets the ItemStack for this entity
     */
    public void setEntityItemStack(ItemStack par1ItemStack)
    {
        this.getDataWatcher().updateObject(10, par1ItemStack);
        this.getDataWatcher().setObjectWatched(10);
    }

    private void UpdateHardcoreBuoy()
    {
        if (FCBetterThanWolves.IsHardcoreBuoyEnabled(this.worldObj))
        {
            byte var1 = 10;
            double var2 = 0.0D;
            double var4 = 0.1D;

            for (int var6 = 0; var6 < var1; ++var6)
            {
                double var7 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(var6 + 0) * 0.375D + var4;
                double var9 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(var6 + 1) * 0.375D + var4;
                AxisAlignedBB var11 = AxisAlignedBB.getAABBPool().getAABB(this.boundingBox.minX, var7, this.boundingBox.minZ, this.boundingBox.maxX, var9, this.boundingBox.maxZ);

                if (!this.worldObj.isAABBInMaterial(var11, Material.water))
                {
                    break;
                }

                var2 += 1.0D / (double)var1;
            }

            if (var2 > 0.001D)
            {
                if (!this.IsInUndertow())
                {
                    float var12 = this.getEntityItem().getItem().GetBuoyancy(this.getEntityItem().getItemDamage()) + 1.0F;
                    this.motionY += 0.04D * (double)var12 * var2;
                }

                this.motionX *= 0.8999999761581421D;
                this.motionY *= 0.8999999761581421D;
                this.motionZ *= 0.8999999761581421D;
            }
        }
    }

    /**
     * Checks for block collisions, and calls the associated onBlockCollided method for the collided block.
     */
    protected void doBlockCollisions()
    {
        int var1 = MathHelper.floor_double(this.boundingBox.minX + 0.001D);
        int var2 = MathHelper.floor_double(this.boundingBox.minY - 0.01D);
        int var3 = MathHelper.floor_double(this.boundingBox.minZ + 0.001D);
        int var4 = MathHelper.floor_double(this.boundingBox.maxX - 0.001D);
        int var5 = MathHelper.floor_double(this.boundingBox.maxY - 0.001D);
        int var6 = MathHelper.floor_double(this.boundingBox.maxZ - 0.001D);

        if (this.worldObj.checkChunksExist(var1, var2, var3, var4, var5, var6))
        {
            for (int var7 = var1; var7 <= var4; ++var7)
            {
                for (int var8 = var2; var8 <= var5; ++var8)
                {
                    for (int var9 = var3; var9 <= var6; ++var9)
                    {
                        int var10 = this.worldObj.getBlockId(var7, var8, var9);

                        if (var10 > 0)
                        {
                            Block.blocksList[var10].onEntityCollidedWithBlock(this.worldObj, var7, var8, var9, this);
                        }
                    }
                }
            }
        }
    }

    private boolean IsInUndertow()
    {
        int var1 = MathHelper.floor_double(this.boundingBox.minX);
        int var2 = MathHelper.floor_double(this.boundingBox.maxX + 1.0D);
        int var3 = MathHelper.floor_double(this.boundingBox.minY);
        int var4 = MathHelper.floor_double(this.boundingBox.maxY + 1.0D);
        int var5 = MathHelper.floor_double(this.boundingBox.minZ);
        int var6 = MathHelper.floor_double(this.boundingBox.maxZ + 1.0D);

        for (int var7 = var1; var7 < var2; ++var7)
        {
            for (int var8 = var3; var8 < var4; ++var8)
            {
                for (int var9 = var5; var9 < var6; ++var9)
                {
                    if (this.DoesBlockHaveUndertow(var7, var8, var9))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean DoesBlockHaveUndertow(int var1, int var2, int var3)
    {
        int var4 = this.worldObj.getBlockId(var1, var2, var3);

        if (var4 == Block.waterMoving.blockID || var4 == Block.waterStill.blockID)
        {
            int var5 = this.worldObj.getBlockMetadata(var1, var2, var3);

            if (var5 >= 8)
            {
                return true;
            }

            var4 = this.worldObj.getBlockId(var1, var2 - 1, var3);

            if (var4 == Block.waterMoving.blockID || var4 == Block.waterStill.blockID)
            {
                var5 = this.worldObj.getBlockMetadata(var1, var2 - 1, var3);

                if (var5 >= 8)
                {
                    return true;
                }
            }

            var4 = this.worldObj.getBlockId(var1, var2 + 1, var3);

            if (var4 == Block.waterMoving.blockID || var4 == Block.waterStill.blockID)
            {
                var5 = this.worldObj.getBlockMetadata(var1, var2 + 1, var3);

                if (var5 >= 8)
                {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float var1)
    {
        super.fall(var1);

        if (!this.worldObj.isRemote && this.getEntityItem().getItem().itemID == FCBetterThanWolves.fcItemBlastingOil.itemID && var1 > 3.0F)
        {
            this.DetonateBlastingOil();
        }
    }

    private void DetonateBlastingOil()
    {
        int var1 = this.getEntityItem().stackSize;
        this.health = 0;
        this.setDead();

        if (var1 > 0)
        {
            float var2 = 1.5F + (float)(var1 - 1) * 2.5F / 63.0F;
            this.worldObj.createExplosion((Entity)null, this.posX, this.posY, this.posZ, var2, true);
        }
    }

    /**
     * Adds velocity to push the entity out of blocks at the specified x, y, z position Args: x, y, z
     */
    protected boolean pushOutOfBlocks(double var1, double var3, double var5)
    {
        int var7 = MathHelper.floor_double(var1);
        int var8 = MathHelper.floor_double(var3);
        int var9 = MathHelper.floor_double(var5);
        double var10 = var1 - (double)var7;
        double var12 = var3 - (double)var8;
        double var14 = var5 - (double)var9;

        if (this.worldObj.isBlockNormalCube(var7, var8, var9))
        {
            boolean var16 = !this.worldObj.isBlockNormalCube(var7 - 1, var8, var9);
            boolean var17 = !this.worldObj.isBlockNormalCube(var7 + 1, var8, var9);
            boolean var18 = !this.worldObj.isBlockNormalCube(var7, var8 - 1, var9);
            boolean var19 = !this.worldObj.isBlockNormalCube(var7, var8 + 1, var9);
            boolean var20 = !this.worldObj.isBlockNormalCube(var7, var8, var9 - 1);
            boolean var21 = !this.worldObj.isBlockNormalCube(var7, var8, var9 + 1);
            byte var22 = -1;
            double var23 = 9999.0D;

            if (var16 && var10 < var23)
            {
                var23 = var10;
                var22 = 0;
            }

            if (var17 && 1.0D - var10 < var23)
            {
                var23 = 1.0D - var10;
                var22 = 1;
            }

            if (var18 && var12 < var23)
            {
                var23 = var12;
                var22 = 2;
            }

            if (var19 && 1.0D - var12 < var23)
            {
                var23 = 1.0D - var12;
                var22 = 3;
            }

            if (var20 && var14 < var23)
            {
                var23 = var14;
                var22 = 4;
            }

            if (var21 && 1.0D - var14 < var23)
            {
                var23 = 1.0D - var14;
                var22 = 5;
            }

            float var25 = this.rand.nextFloat() * 0.2F + 0.1F;

            if (var22 == 0)
            {
                this.motionX = (double)(-var25);
            }

            if (var22 == 1)
            {
                this.motionX = (double)var25;
            }

            if (var22 == 2)
            {
                this.motionY = (double)(-var25);
            }

            if (var22 == 3)
            {
                this.motionY = (double)var25;
            }

            if (var22 == 4)
            {
                this.motionZ = (double)(-var25);
            }

            if (var22 == 5)
            {
                this.motionZ = (double)var25;
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean IsItemEntity()
    {
        return true;
    }

    public boolean CanEntityTriggerTripwire()
    {
        return false;
    }

    private void CheckForItemDespawn()
    {
        if (!this.worldObj.isRemote)
        {
            if (this.m_lAbsoluteItemDespawnTime > 0L)
            {
                long var1 = MinecraftServer.getServer().worldServers[0].getTotalWorldTime();

                if (var1 >= this.m_lAbsoluteItemDespawnTime)
                {
                    this.setDead();
                }
            }
            else if (this.age >= 6000)
            {
                this.setDead();
            }
        }
    }

    public void SetEntityItemAsDroppedOnPlayerDeath(EntityPlayer var1)
    {
        this.m_lAbsoluteItemDespawnTime = MinecraftServer.getServer().worldServers[0].getTotalWorldTime() + 24000L;
    }

    public static boolean InstallationIntegrityTestEntityItem()
    {
        return true;
    }
}

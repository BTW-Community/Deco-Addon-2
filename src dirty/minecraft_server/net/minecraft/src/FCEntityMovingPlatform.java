package net.minecraft.src;

import java.util.List;

public class FCEntityMovingPlatform extends Entity implements FCIEntityPacketHandler, FCIEntityIgnoreServerValidation
{
    private static final int m_iYMotionDataWatcherID = 22;
    private static final int m_iVehicleSpawnPacketType = 103;
    private double m_AssociatedAnchorLastKnownXPos;
    private double m_AssociatedAnchorLastKnownYPos;
    private double m_AssociatedAnchorLastKnownZPos;

    public FCEntityMovingPlatform(World var1)
    {
        super(var1);
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.m_AssociatedAnchorLastKnownXPos = 0.0D;
        this.m_AssociatedAnchorLastKnownYPos = 0.0D;
        this.m_AssociatedAnchorLastKnownZPos = 0.0D;
    }

    public FCEntityMovingPlatform(World var1, double var2, double var4, double var6, FCEntityMovingAnchor var8)
    {
        this(var1);

        if (var8 != null)
        {
            this.m_AssociatedAnchorLastKnownXPos = var8.posX;
            this.m_AssociatedAnchorLastKnownYPos = var8.posY;
            this.m_AssociatedAnchorLastKnownZPos = var8.posZ;
            this.motionY = var8.motionY;
        }

        this.setPosition(var2, var4, var6);
        this.lastTickPosX = this.prevPosX = var2;
        this.lastTickPosY = this.prevPosY = var4;
        this.lastTickPosZ = this.prevPosZ = var6;
    }

    protected void entityInit()
    {
        this.dataWatcher.addObject(22, new Integer(0));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound var1)
    {
        var1.setDouble("m_AssociatedAnchorLastKnownXPos", this.m_AssociatedAnchorLastKnownXPos);
        var1.setDouble("m_AssociatedAnchorLastKnownYPos", this.m_AssociatedAnchorLastKnownYPos);
        var1.setDouble("m_AssociatedAnchorLastKnownZPos", this.m_AssociatedAnchorLastKnownZPos);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound var1)
    {
        this.m_AssociatedAnchorLastKnownXPos = var1.getDouble("m_AssociatedAnchorLastKnownXPos");
        this.m_AssociatedAnchorLastKnownYPos = var1.getDouble("m_AssociatedAnchorLastKnownYPos");
        this.m_AssociatedAnchorLastKnownZPos = var1.getDouble("m_AssociatedAnchorLastKnownZPos");
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    /**
     * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
     * pushable on contact, like boats or minecarts.
     */
    public AxisAlignedBB getCollisionBox(Entity var1)
    {
        return var1.boundingBox;
    }

    /**
     * returns the bounding box for this entity
     */
    public AxisAlignedBB getBoundingBox()
    {
        return this.boundingBox;
    }

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return false;
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (!this.isDead)
        {
            if (this.worldObj.isRemote)
            {
                this.motionY = this.GetCorseYMotion();
            }

            FCEntityMovingAnchor var1 = null;
            boolean var2 = false;
            int var3 = MathHelper.floor_double(this.posX);
            int var4 = MathHelper.floor_double(this.posY);
            int var5 = MathHelper.floor_double(this.posZ);

            if (!this.worldObj.isRemote)
            {
                List var6 = this.worldObj.getEntitiesWithinAABB(FCEntityMovingAnchor.class, AxisAlignedBB.getAABBPool().getAABB(this.m_AssociatedAnchorLastKnownXPos - 0.25D, this.m_AssociatedAnchorLastKnownYPos - 0.25D, this.m_AssociatedAnchorLastKnownZPos - 0.25D, this.m_AssociatedAnchorLastKnownXPos + 0.25D, this.m_AssociatedAnchorLastKnownYPos + 0.25D, this.m_AssociatedAnchorLastKnownZPos + 0.25D));

                if (var6 != null && var6.size() > 0)
                {
                    var1 = (FCEntityMovingAnchor)var6.get(0);

                    if (!var1.isDead)
                    {
                        this.motionY = var1.posY - this.m_AssociatedAnchorLastKnownYPos;

                        if (this.motionY < 0.01D && this.motionY > -0.01D)
                        {
                            this.motionY = 0.0D;
                            var2 = true;
                        }

                        this.m_AssociatedAnchorLastKnownXPos = var1.posX;
                        this.m_AssociatedAnchorLastKnownYPos = var1.posY;
                        this.m_AssociatedAnchorLastKnownZPos = var1.posZ;
                    }
                    else
                    {
                        var1 = null;
                    }
                }

                this.SetCorseYMotion(this.motionY);
            }

            double var14 = this.posY;
            this.MoveEntityInternal(this.motionX, this.motionY, this.motionZ);
            double var8 = this.posY;
            List var10 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.0D, 0.15D, 0.0D));
            int var11;

            if (var10 != null && var10.size() > 0)
            {
                for (var11 = 0; var11 < var10.size(); ++var11)
                {
                    Entity var12 = (Entity)var10.get(var11);

                    if (!var12.canBePushed() && !(var12 instanceof EntityItem) && !(var12 instanceof EntityXPOrb))
                    {
                        if (!var12.isDead && var12 instanceof FCEntityMechPower)
                        {
                            FCEntityMechPower var13 = (FCEntityMechPower)var12;
                            var13.DestroyWithDrop();
                        }
                    }
                    else
                    {
                        this.PushEntity(var12);
                    }
                }
            }

            if (!this.worldObj.isRemote)
            {
                if (var1 == null)
                {
                    this.ConvertToBlock(var3, var4, var5, (FCEntityMovingAnchor)null, this.motionY > 0.0D);
                    return;
                }

                if (!var2)
                {
                    int var15;
                    int var16;

                    if (this.motionY > 0.0D)
                    {
                        var11 = MathHelper.floor_double(var8 + 0.49000000953674316D);
                        var15 = this.worldObj.getBlockId(var3, var11, var5);

                        if (!FCUtilsWorld.IsReplaceableBlock(this.worldObj, var3, var11, var5))
                        {
                            if (Block.blocksList[var15].blockMaterial.isSolid() && var15 != Block.web.blockID && var15 != FCBetterThanWolves.fcBlockWeb.blockID)
                            {
                                this.ConvertToBlock(var3, var4, var5, var1, true);
                                var1.ForceStopByPlatform();
                                return;
                            }

                            var16 = this.worldObj.getBlockMetadata(var3, var11, var5);
                            Block.blocksList[var15].dropBlockAsItem(this.worldObj, var3, var11, var5, var16, 0);
                            this.worldObj.setBlockWithNotify(var3, var11, var5, 0);
                            this.worldObj.playAuxSFX(2252, var3, var11, var5, var15 + (var16 << 12));
                        }
                    }
                    else
                    {
                        var11 = MathHelper.floor_double(var8 - 0.49000000953674316D);
                        var15 = this.worldObj.getBlockId(var3, var11, var5);

                        if (!FCUtilsWorld.IsReplaceableBlock(this.worldObj, var3, var11, var5))
                        {
                            if (Block.blocksList[var15].blockMaterial.isSolid() && var15 != Block.web.blockID && var15 != FCBetterThanWolves.fcBlockWeb.blockID)
                            {
                                this.ConvertToBlock(var3, var4, var5, var1, false);
                                var1.ForceStopByPlatform();
                                return;
                            }

                            var16 = this.worldObj.getBlockMetadata(var3, var11, var5);
                            Block.blocksList[var15].dropBlockAsItem(this.worldObj, var3, var11, var5, var16, 0);
                            this.worldObj.setBlockWithNotify(var3, var11, var5, 0);
                            this.worldObj.playAuxSFX(2252, var3, var11, var5, var15 + (var16 << 12));
                        }
                    }
                }
            }
        }
    }

    /**
     * Tries to moves the entity by the passed in displacement. Args: x, y, z
     */
    public void moveEntity(double var1, double var3, double var5)
    {
        this.DestroyPlatformWithDrop();
    }

    protected boolean ShouldSetPositionOnLoad()
    {
        return false;
    }

    public Packet GetSpawnPacketForThisEntity()
    {
        return new Packet23VehicleSpawn(this, GetVehicleSpawnPacketType(), 0);
    }

    public int GetTrackerViewDistance()
    {
        return 160;
    }

    public int GetTrackerUpdateFrequency()
    {
        return 3;
    }

    public boolean GetTrackMotion()
    {
        return false;
    }

    public boolean ShouldServerTreatAsOversized()
    {
        return false;
    }

    private double GetCorseYMotion()
    {
        return (double)this.dataWatcher.getWatchableObjectInt(22) / 100.0D;
    }

    private void SetCorseYMotion(double var1)
    {
        this.dataWatcher.updateObject(22, Integer.valueOf((int)(var1 * 100.0D)));
    }

    public static int GetVehicleSpawnPacketType()
    {
        return 103;
    }

    public void DestroyPlatformWithDrop()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);
        ItemStack var4 = new ItemStack(FCBetterThanWolves.fcPlatform);
        FCUtilsItem.EjectStackWithRandomOffset(this.worldObj, var1, var2, var3, var4);
        this.setDead();
    }

    private void MoveEntityInternal(double var1, double var3, double var5)
    {
        double var7 = this.posX + var1;
        double var9 = this.posY + var3;
        double var11 = this.posZ + var5;
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.setPosition(var7, var9, var11);
        this.TestForBlockCollisions();
    }

    private void TestForBlockCollisions()
    {
        int var1 = MathHelper.floor_double(this.boundingBox.minX + 0.001D);
        int var2 = MathHelper.floor_double(this.boundingBox.minY + 0.001D);
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

    private void PushEntity(Entity var1)
    {
        double var2 = this.boundingBox.maxY + 0.075D;
        double var4 = var1.boundingBox.minY;

        if (var4 < var2)
        {
            double var6;

            if (var4 > this.boundingBox.maxY - 0.5D)
            {
                if (!(var1 instanceof EntityPlayer))
                {
                    var6 = this.boundingBox.maxY + 0.01D - var4;
                    var1.setPosition(var1.posX, var1.posY + var6, var1.posZ);

                    if (var1.riddenByEntity != null)
                    {
                        var1.riddenByEntity.setPosition(var1.riddenByEntity.posX, var1.riddenByEntity.posY + var6, var1.riddenByEntity.posZ);
                    }
                }
            }
            else if (var1 instanceof EntityLiving && this.motionY < 0.0D)
            {
                var6 = var1.boundingBox.maxY;

                if (this.boundingBox.minY < var6 - 0.25D && var2 > var6)
                {
                    var1.attackEntityFrom(DamageSource.inWall, 1);
                }
            }
        }
    }

    private void ConvertToBlock(int var1, int var2, int var3, FCEntityMovingAnchor var4, boolean var5)
    {
        boolean var6 = true;
        int var7 = this.worldObj.getBlockId(var1, var2, var3);

        if (FCUtilsWorld.IsReplaceableBlock(this.worldObj, var1, var2, var3))
        {
            this.worldObj.setBlockWithNotify(var1, var2, var3, FCBetterThanWolves.fcPlatform.blockID);
        }
        else if (Block.blocksList[var7].blockMaterial.isSolid() && var7 != Block.web.blockID && var7 != FCBetterThanWolves.fcBlockWeb.blockID)
        {
            FCUtilsItem.EjectSingleItemWithRandomOffset(this.worldObj, var1, var2, var3, FCBetterThanWolves.fcPlatform.blockID, 0);
            var6 = false;
        }
        else
        {
            int var8 = this.worldObj.getBlockMetadata(var1, var2, var3);
            Block.blocksList[var7].dropBlockAsItem(this.worldObj, var1, var2, var3, var8, 0);
            this.worldObj.playAuxSFX(2252, var1, var2, var3, var7 + (var8 << 12));
            this.worldObj.setBlockWithNotify(var1, var2, var3, FCBetterThanWolves.fcPlatform.blockID);
        }

        FCUtilsMisc.PositionAllNonPlayerMoveableEntitiesOutsideOfLocation(this.worldObj, var1, var2, var3);

        if (!var5)
        {
            FCUtilsMisc.ServerPositionAllPlayerEntitiesOutsideOfLocation(this.worldObj, var1, var2 + 1, var3);
            FCUtilsMisc.ServerPositionAllPlayerEntitiesOutsideOfLocation(this.worldObj, var1, var2, var3);
        }
        else
        {
            FCUtilsMisc.ServerPositionAllPlayerEntitiesOutsideOfLocation(this.worldObj, var1, var2 - 1, var3);
            FCUtilsMisc.ServerPositionAllPlayerEntitiesOutsideOfLocation(this.worldObj, var1, var2, var3);
        }

        this.setDead();
    }
}

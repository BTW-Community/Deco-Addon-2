package net.minecraft.src;

public abstract class FCEntityMechPower extends Entity implements FCIEntityPacketHandler
{
    private static final int m_iRotationSpeedDataWatcherID = 22;
    public float m_fRotation;
    public int m_iCurrentDamage;
    public int m_iTimeSinceHit;
    public int m_iRockDirection;
    protected boolean m_bProvidingPower;
    protected int m_iFullUpdateTickCount;

    public FCEntityMechPower(World var1)
    {
        super(var1);
        this.m_bProvidingPower = false;
        this.m_iCurrentDamage = 0;
        this.m_iTimeSinceHit = 0;
        this.m_iRockDirection = 1;
        this.m_fRotation = 0.0F;
        this.m_iFullUpdateTickCount = 0;
        this.preventEntitySpawning = true;
        this.setSize(this.GetWidth(), this.GetHeight());
        this.yOffset = this.height / 2.0F;
    }

    public FCEntityMechPower(World var1, double var2, double var4, double var6)
    {
        this(var1);
        this.setPosition(var2, var4, var6);
    }

    protected void entityInit()
    {
        this.dataWatcher.addObject(22, new Integer(0));
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
     * Tries to moves the entity by the passed in displacement. Args: x, y, z
     */
    public void moveEntity(double var1, double var3, double var5)
    {
        if (!this.isDead)
        {
            this.DestroyWithDrop();
        }
    }

    /**
     * Sets entity to burn for x amount of seconds, cannot lower amount of existing fire.
     */
    public void setFire(int var1) {}

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource var1, int var2)
    {
        if (this.isDead)
        {
            return true;
        }
        else
        {
            this.m_iCurrentDamage += var2 * 5;
            this.m_iRockDirection = -this.m_iRockDirection;
            this.m_iTimeSinceHit = 10;

            if (!this.worldObj.isRemote)
            {
                Entity var3 = var1.getEntity();

                if (var3 instanceof EntityPlayer && ((EntityPlayer)var3).capabilities.isCreativeMode)
                {
                    this.DestroyWithDrop();
                }
                else
                {
                    this.setBeenAttacked();

                    if (this.m_iCurrentDamage > this.GetMaxDamage())
                    {
                        this.DestroyWithDrop();
                    }
                }
            }

            return true;
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (!this.isDead)
        {
            if (!this.worldObj.isRemote)
            {
                --this.m_iFullUpdateTickCount;

                if (this.m_iFullUpdateTickCount <= 0)
                {
                    this.m_iFullUpdateTickCount = this.GetTicksPerFullUpdate();
                    this.OnFullUpdateServer();
                }

                this.UpdateRotationAndDamageState();
            }
            else
            {
                float var1 = this.m_fRotation;
                this.UpdateRotationAndDamageState();
                int var2 = (int)(this.m_fRotation / 45.0F);
                int var3 = (int)(var1 / 45.0F);

                if (var3 != var2)
                {
                    this.OnClientRotationOctantChange();
                }
            }
        }
    }

    protected boolean ShouldSetPositionOnLoad()
    {
        return false;
    }

    public boolean AttractsLightning()
    {
        return true;
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
        return true;
    }

    public abstract float GetWidth();

    public abstract float GetHeight();

    public abstract float GetDepth();

    protected abstract void InitBoundingBox();

    protected abstract AxisAlignedBB GetDeviceBounds();

    protected abstract int GetMaxDamage();

    protected abstract int GetTicksPerFullUpdate();

    protected abstract void DestroyWithDrop();

    public abstract boolean ValidateAreaAroundDevice();

    protected abstract boolean ValidateConnectedAxles();

    protected abstract float ComputeRotation();

    protected abstract void TransferPowerStateToConnectedAxles();

    private void UpdateRotationAndDamageState()
    {
        this.m_fRotation += this.getRotationSpeed();

        if (this.m_fRotation > 360.0F)
        {
            this.m_fRotation -= 360.0F;
        }
        else if (this.m_fRotation < -360.0F)
        {
            this.m_fRotation += 360.0F;
        }

        if (this.m_iTimeSinceHit > 0)
        {
            --this.m_iTimeSinceHit;
        }

        if (this.m_iCurrentDamage > 0)
        {
            --this.m_iCurrentDamage;
        }
    }

    protected void OnClientRotationOctantChange() {}

    public boolean IsClearOfBlockingEntities()
    {
        AxisAlignedBB var1 = this.GetDeviceBounds();
        return this.worldObj.checkNoEntityCollision(var1, this);
    }

    public float getRotationSpeed()
    {
        return (float)this.dataWatcher.getWatchableObjectInt(22) / 100.0F;
    }

    public void setRotationSpeed(float var1)
    {
        this.dataWatcher.updateObject(22, Integer.valueOf((int)(var1 * 100.0F)));
    }

    public int getRotationSpeedScaled()
    {
        return this.dataWatcher.getWatchableObjectInt(22);
    }

    public void setRotationSpeedScaled(int var1)
    {
        this.dataWatcher.updateObject(22, Integer.valueOf(var1));
    }

    protected void OnFullUpdateServer()
    {
        if (this.ValidateAreaAroundDevice() && this.ValidateConnectedAxles())
        {
            this.setRotationSpeed(this.ComputeRotation());
            float var1 = this.getRotationSpeed();
            boolean var2 = false;

            if (var1 > 0.01F || var1 < -0.01F)
            {
                var2 = true;
            }

            if (this.m_bProvidingPower != var2)
            {
                this.m_bProvidingPower = var2;
                this.TransferPowerStateToConnectedAxles();
            }
        }
        else
        {
            this.DestroyWithDrop();
        }
    }
}

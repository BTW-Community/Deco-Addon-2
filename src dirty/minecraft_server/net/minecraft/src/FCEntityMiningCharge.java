package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class FCEntityMiningCharge extends Entity implements FCIEntityPacketHandler
{
    public int m_iFuse;
    public int m_iFacing;
    public boolean m_bAttachedToBlock;

    public FCEntityMiningCharge(World var1)
    {
        super(var1);
        this.m_iFuse = 0;
        this.m_iFacing = 0;
        this.m_bAttachedToBlock = true;
        this.preventEntitySpawning = true;
        this.setSize(0.98F, 0.98F);
        this.yOffset = this.height / 2.0F;
    }

    public FCEntityMiningCharge(World var1, int var2, int var3, int var4, int var5)
    {
        this(var1);
        this.m_iFuse = 80;
        this.m_iFacing = var5;
        this.setPosition((double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F));
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
    }

    public FCEntityMiningCharge(World var1, double var2, double var4, double var6, int var8, int var9, boolean var10)
    {
        this(var1);
        this.setPosition(var2, var4, var6);
        this.m_iFacing = var8;
        this.m_iFuse = var9;
        this.m_bAttachedToBlock = var10;
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
    }

    protected void entityInit() {}

    /**
     * Returns true if this entity should push and be pushed by other entities when colliding.
     */
    public boolean canBePushed()
    {
        return false;
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
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource var1, int var2)
    {
        if (var1.isExplosion() && this.m_iFuse > 1)
        {
            this.m_iFuse = 1;
        }

        this.setBeenAttacked();
        return false;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.m_bAttachedToBlock)
        {
            boolean var1 = false;
            FCUtilsBlockPos var2 = new FCUtilsBlockPos(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
            var2.AddFacingAsOffset(this.m_iFacing);

            if (this.worldObj.isBlockNormalCube(var2.i, var2.j, var2.k) || this.m_iFacing == 0 && this.worldObj.doesBlockHaveSolidTopSurface(var2.i, var2.j, var2.k))
            {
                var1 = true;
            }

            this.m_bAttachedToBlock = var1;
        }

        if (!this.m_bAttachedToBlock)
        {
            if (this.m_iFacing == 1)
            {
                this.m_iFacing = 0;
            }

            this.motionY -= 0.03999999910593033D;
            this.moveEntity(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.9800000190734863D;
            this.motionY *= 0.9800000190734863D;
            this.motionZ *= 0.9800000190734863D;

            if (this.onGround)
            {
                this.motionX *= 0.699999988079071D;
                this.motionZ *= 0.699999988079071D;
                this.motionY *= -0.5D;
            }
        }

        if (this.m_iFuse-- <= 0)
        {
            if (!this.worldObj.isRemote)
            {
                this.setDead();
                this.explode();
            }
            else
            {
                this.m_iFuse = 0;
            }
        }
        else
        {
            this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound var1)
    {
        var1.setByte("m_iFuse", (byte)this.m_iFuse);
        var1.setByte("m_iFacing", (byte)this.m_iFacing);
        var1.setBoolean("m_bAttachedToBlock", this.m_bAttachedToBlock);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound var1)
    {
        this.m_iFuse = var1.getByte("m_iFuse");
        this.m_iFacing = var1.getByte("m_iFacing");
        this.m_bAttachedToBlock = var1.getBoolean("m_bAttachedToBlock");
    }

    protected boolean ShouldSetPositionOnLoad()
    {
        return false;
    }

    public Packet GetSpawnPacketForThisEntity()
    {
        ByteArrayOutputStream var1 = new ByteArrayOutputStream();
        DataOutputStream var2 = new DataOutputStream(var1);

        try
        {
            var2.writeInt(3);
            var2.writeInt(this.entityId);
            var2.writeInt((int)(this.posX * 32.0D));
            var2.writeInt((int)(this.posY * 32.0D));
            var2.writeInt((int)(this.posZ * 32.0D));
            var2.writeByte((byte)this.m_iFacing);
            var2.writeByte((byte)this.m_iFuse);
            var2.writeByte(this.m_bAttachedToBlock ? 1 : 0);
        }
        catch (Exception var4)
        {
            var4.printStackTrace();
        }

        return new Packet250CustomPayload("FC|SE", var1.toByteArray());
    }

    public int GetTrackerViewDistance()
    {
        return 160;
    }

    public int GetTrackerUpdateFrequency()
    {
        return 10;
    }

    public boolean GetTrackMotion()
    {
        return false;
    }

    public boolean ShouldServerTreatAsOversized()
    {
        return false;
    }

    private void explode()
    {
        FCExplosionMining var1 = new FCExplosionMining(this.worldObj, this.posX, this.posY, this.posZ, this.m_iFacing);
        var1.doExplosion();
    }
}

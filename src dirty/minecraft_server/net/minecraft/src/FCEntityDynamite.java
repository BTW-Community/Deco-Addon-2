package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class FCEntityDynamite extends Entity implements IProjectile, FCIEntityPacketHandler
{
    public static final int m_iTicksToDetonate = 100;
    public int m_iFuse;
    public int m_iItemShiftedIndex;

    public FCEntityDynamite(World var1)
    {
        super(var1);
        this.setSize(0.25F, 0.4F);
        this.m_iFuse = -1;
        this.yOffset = 0.07F;
        this.preventEntitySpawning = true;
        this.m_iItemShiftedIndex = 0;
        this.isImmuneToFire = true;
    }

    public FCEntityDynamite(World var1, int var2)
    {
        this(var1);
        this.m_iItemShiftedIndex = var2;
    }

    public FCEntityDynamite(World var1, EntityLiving var2, int var3, boolean var4)
    {
        this(var1, var3);
        this.setLocationAndAngles(var2.posX, var2.posY + (double)var2.getEyeHeight(), var2.posZ, var2.rotationYaw, var2.rotationPitch);
        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        float var5 = 0.4F;
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * var5);
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * var5);
        this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI) * var5);
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 0.75F, 1.0F);

        if (var4)
        {
            this.m_iFuse = 100;
        }
    }

    public FCEntityDynamite(World var1, double var2, double var4, double var6, int var8)
    {
        this(var1, var8);
        this.setPosition(var2, var4, var6);
    }

    protected void entityInit() {}

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        var1.setInteger("m_iItemShiftedIndex", this.m_iItemShiftedIndex);
        var1.setInteger("m_iFuse", this.m_iFuse);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        this.m_iItemShiftedIndex = var1.getInteger("m_iItemShiftedIndex");
        this.m_iFuse = var1.getInteger("m_iFuse");
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (!this.isDead)
        {
            int var1 = MathHelper.floor_double(this.posX);
            int var2 = MathHelper.floor_double(this.posY);
            int var3 = MathHelper.floor_double(this.posZ);
            int var4 = this.worldObj.getBlockId(var1, var2, var3);

            if (var4 == Block.lavaMoving.blockID || var4 == Block.lavaStill.blockID)
            {
                this.m_iFuse = 1;
            }

            if (this.m_iFuse > 0)
            {
                --this.m_iFuse;

                if (this.m_iFuse == 0)
                {
                    this.setDead();

                    if (!this.worldObj.isRemote)
                    {
                        this.DynamiteExplode();
                        return;
                    }

                    this.m_iFuse = 1;
                }

                float var5 = 0.25F;
                this.worldObj.spawnParticle("smoke", this.posX - this.motionX * (double)var5, this.posY + 0.5D - this.motionY * (double)var5, this.posZ - this.motionZ * (double)var5, this.motionX * 0.10000000149011612D, this.motionY * 0.10000000149011612D, this.motionZ * 0.10000000149011612D);
            }
            else if (var4 != Block.fire.blockID && var4 != FCBetterThanWolves.fcBlockFireStoked.blockID)
            {
                if (this.onGround && Math.abs(this.motionX) < 0.01D && Math.abs(this.motionY) < 0.01D && Math.abs(this.motionZ) < 0.01D && !this.worldObj.isRemote)
                {
                    this.ConvertToItem();
                    return;
                }
            }
            else
            {
                this.m_iFuse = 100;
                this.worldObj.playSoundAtEntity(this, "random.fuse", 1.0F, 1.0F);
            }

            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
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

            this.extinguish();
        }
    }

    protected boolean ShouldSetPositionOnLoad()
    {
        return false;
    }

    /**
     * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
     */
    public void setThrowableHeading(double var1, double var3, double var5, float var7, float var8)
    {
        float var9 = MathHelper.sqrt_double(var1 * var1 + var3 * var3 + var5 * var5);
        var1 /= (double)var9;
        var3 /= (double)var9;
        var5 /= (double)var9;
        var1 += this.rand.nextGaussian() * 0.007499999832361937D * (double)var8;
        var3 += this.rand.nextGaussian() * 0.007499999832361937D * (double)var8;
        var5 += this.rand.nextGaussian() * 0.007499999832361937D * (double)var8;
        var1 *= (double)var7;
        var3 *= (double)var7;
        var5 *= (double)var7;
        this.motionX = var1;
        this.motionY = var3;
        this.motionZ = var5;
        float var10 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(var1, var5) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(var3, (double)var10) * 180.0D / Math.PI);
    }

    public int GetTrackerViewDistance()
    {
        return 64;
    }

    public int GetTrackerUpdateFrequency()
    {
        return 10;
    }

    public boolean GetTrackMotion()
    {
        return true;
    }

    public boolean ShouldServerTreatAsOversized()
    {
        return false;
    }

    public Packet GetSpawnPacketForThisEntity()
    {
        ByteArrayOutputStream var1 = new ByteArrayOutputStream();
        DataOutputStream var2 = new DataOutputStream(var1);

        try
        {
            var2.writeInt(6);
            var2.writeInt(this.entityId);
            var2.writeInt(MathHelper.floor_double(this.posX * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.posY * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.posZ * 32.0D));
            var2.writeInt(this.m_iItemShiftedIndex);
            var2.writeInt(this.m_iFuse);
            var2.writeByte((byte)((int)(this.motionX * 128.0D)));
            var2.writeByte((byte)((int)(this.motionY * 128.0D)));
            var2.writeByte((byte)((int)(this.motionZ * 128.0D)));
        }
        catch (Exception var4)
        {
            var4.printStackTrace();
        }

        return new Packet250CustomPayload("FC|SE", var1.toByteArray());
    }

    private void DynamiteExplode()
    {
        float var1 = 1.5F;
        this.worldObj.createExplosion((Entity)null, this.posX, this.posY, this.posZ, var1, true);
        int var2 = MathHelper.floor_double(this.posX);
        int var3 = MathHelper.floor_double(this.posY);
        int var4 = MathHelper.floor_double(this.posZ);
        int var5 = this.worldObj.getBlockId(var2, var3, var4);

        if (var5 == Block.waterMoving.blockID || var5 == Block.waterStill.blockID)
        {
            this.RedneckFishing(var2, var3, var4);
        }
    }

    private void RedneckFishing(int var1, int var2, int var3)
    {
        for (int var4 = var1 - 2; var4 <= var1 + 2; ++var4)
        {
            for (int var5 = var2 - 2; var5 <= var2 + 4; ++var5)
            {
                for (int var6 = var3 - 2; var6 <= var3 + 2; ++var6)
                {
                    if (this.IsValidBlockForRedneckFishing(var4, var5, var6) && this.worldObj.rand.nextInt(25) == 0)
                    {
                        this.SpawnRedneckFish(var4, var5, var6);
                    }
                }
            }
        }
    }

    private boolean IsValidBlockForRedneckFishing(int var1, int var2, int var3)
    {
        for (int var4 = var1 - 1; var4 <= var1 + 1; ++var4)
        {
            for (int var5 = var2 - 1; var5 <= var2; ++var5)
            {
                for (int var6 = var3 - 1; var6 <= var3 + 1; ++var6)
                {
                    int var7 = this.worldObj.getBlockId(var4, var5, var6);

                    if (var7 != Block.waterMoving.blockID && var7 != Block.waterStill.blockID)
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private void SpawnRedneckFish(int var1, int var2, int var3)
    {
        ItemStack var4 = new ItemStack(Item.fishRaw.itemID, 1, 0);
        Object var5;

        if (FCBetterThanWolves.IsHardcoreBuoyEnabled(this.worldObj))
        {
            var5 = new EntityItem(this.worldObj, (double)((float)var1 + 0.5F), (double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), var4);
        }
        else
        {
            var5 = new FCEntityItemFloating(this.worldObj, (double)((float)var1 + 0.5F), (double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), var4);
        }

        this.worldObj.spawnEntityInWorld((Entity)var5);
    }

    private void ConvertToItem()
    {
        FCUtilsItem.EjectSingleItemWithRandomVelocity(this.worldObj, (float)this.posX, (float)this.posY, (float)this.posZ, FCBetterThanWolves.fcItemDynamite.itemID, 0);
        this.setDead();
    }
}

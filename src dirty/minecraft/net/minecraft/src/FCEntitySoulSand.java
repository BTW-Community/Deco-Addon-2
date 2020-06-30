package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import net.minecraft.client.Minecraft;

public class FCEntitySoulSand extends Entity implements FCIEntityPacketHandler
{
    private double targetX;
    private double targetY;
    private double targetZ;
    private int despawnTimer;

    public FCEntitySoulSand(World var1)
    {
        super(var1);
        this.setSize(0.25F, 0.25F);
    }

    public FCEntitySoulSand(World var1, double var2, double var4, double var6)
    {
        this(var1);
        this.setPosition(var2, var4, var6);
        this.yOffset = 0.0F;
        this.despawnTimer = 0;
    }

    protected void entityInit() {}

    /**
     * Sets the velocity to the args. Args: x, y, z
     */
    public void setVelocity(double var1, double var3, double var5)
    {
        this.motionX = var1;
        this.motionY = var3;
        this.motionZ = var5;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float var7 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(var1, var5) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(var3, (double)var7) * 180.0D / Math.PI);
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        super.onUpdate();
        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

        for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)var1) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
        {
            ;
        }

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
        {
            this.prevRotationPitch += 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw < -180.0F)
        {
            this.prevRotationYaw -= 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
        {
            this.prevRotationYaw += 360.0F;
        }

        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;

        if (!this.worldObj.isRemote)
        {
            double var2 = this.targetX - this.posX;
            double var4 = this.targetZ - this.posZ;
            float var6 = (float)Math.sqrt(var2 * var2 + var4 * var4);
            float var7 = (float)Math.atan2(var4, var2);
            double var8 = (double)var1 + (double)(var6 - var1) * 6.0E-4D;

            if (var6 < 1.0F)
            {
                var8 *= 0.8D;
                this.motionY *= 0.8D;
            }

            this.motionX = Math.cos((double)var7) * var8;
            this.motionZ = Math.sin((double)var7) * var8;

            if (this.posY < this.targetY)
            {
                this.motionY += (1.0D - this.motionY) * 0.014999999664723873D;
            }
            else
            {
                this.motionY += (-1.0D - this.motionY) * 0.014999999664723873D;
            }

            this.setPosition(this.posX, this.posY, this.posZ);
            ++this.despawnTimer;

            if (this.despawnTimer > 40 && !this.worldObj.isRemote)
            {
                this.setDead();

                if (var6 >= 1.0F)
                {
                    this.worldObj.playAuxSFX(2228, (int)Math.round(this.posX), (int)Math.round(this.posY), (int)Math.round(this.posZ), 0);
                }
            }
        }
        else
        {
            this.ClientUpdateParticles();
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1) {}

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1) {}

    public float getShadowSize()
    {
        return 0.0F;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float var1)
    {
        return 1.0F;
    }

    public int getBrightnessForRender(float var1)
    {
        return 15728880;
    }

    /**
     * If returns false, the item will not inflict any damage against entities.
     */
    public boolean canAttackWithItem()
    {
        return false;
    }

    public int GetTrackerViewDistance()
    {
        return 64;
    }

    public int GetTrackerUpdateFrequency()
    {
        return 4;
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
            var2.writeInt(11);
            var2.writeInt(this.entityId);
            var2.writeInt(MathHelper.floor_double(this.posX * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.posY * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.posZ * 32.0D));
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

    public void MoveTowards(double var1, double var3)
    {
        double var5 = var1 - this.posX;
        double var7 = var3 - this.posZ;
        float var9 = MathHelper.sqrt_double(var5 * var5 + var7 * var7);

        if (var9 > 3.0F)
        {
            this.targetX = this.posX + var5 / (double)var9 * 3.0D;
            this.targetZ = this.posZ + var7 / (double)var9 * 3.0D;
        }
        else
        {
            this.targetX = var1;
            this.targetZ = var3;
        }

        this.targetY = this.posY + 0.1D;
        this.despawnTimer = 0;
    }

    private void ClientUpdateParticles()
    {
        float var1 = 0.25F;

        for (int var2 = 0; var2 < 10; ++var2)
        {
            EntitySmokeFX var3 = new EntitySmokeFX(this.worldObj, this.posX - this.motionX * (double)var1 + this.rand.nextDouble() * 0.6D - 0.3D, this.posY - this.motionY * (double)var1 + this.rand.nextDouble() * 0.6D - 0.3D - 0.1D, this.posZ - this.motionZ * (double)var1 + this.rand.nextDouble() * 0.6D - 0.3D, this.motionX, this.motionY, this.motionZ, 0.33F);

            if (this.rand.nextInt(8) == 0)
            {
                var3.setRBGColorF(1.0F, 1.0F, 1.0F);
            }

            Minecraft.getMinecraft().effectRenderer.addEffect(var3);
            EntityAuraFX var5 = new EntityAuraFX(this.worldObj, this.posX - this.motionX * (double)var1 + this.rand.nextDouble() * 0.6D - 0.3D, this.posY - this.motionY * (double)var1 - 0.5D, this.posZ - this.motionZ * (double)var1 + this.rand.nextDouble() * 0.6D - 0.3D, this.motionX, this.motionY, this.motionZ);
            float var4 = 0.1F + this.rand.nextFloat() * 0.9F;
            var5.setRBGColorF(0.42352942F * var4, 0.30588236F * var4, 0.23529412F * var4);
            var5.particleScale *= 0.25F;
            Minecraft.getMinecraft().effectRenderer.addEffect(var5);
        }
    }
}

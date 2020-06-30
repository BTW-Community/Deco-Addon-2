package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class FCEntityItemFloating extends EntityItem implements FCIEntityPacketHandler
{
    public FCEntityItemFloating(World var1, double var2, double var4, double var6, ItemStack var8)
    {
        super(var1, var2, var4, var6, var8);
    }

    public FCEntityItemFloating(World var1)
    {
        super(var1);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.onEntityUpdate();

        if (this.delayBeforeCanPickup > 0)
        {
            --this.delayBeforeCanPickup;
        }

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        byte var1 = 5;
        double var2 = 0.0D;
        double var4 = 0.1D;

        for (int var6 = 0; var6 < var1; ++var6)
        {
            double var7 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(var6 + 0) + var4;
            double var9 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(var6 + 1) + var4;
            AxisAlignedBB var11 = AxisAlignedBB.getAABBPool().getAABB(this.boundingBox.minX, var7, this.boundingBox.minZ, this.boundingBox.maxX, var9, this.boundingBox.maxZ);

            if (this.worldObj.isAABBInMaterial(var11, Material.water))
            {
                var2 += 1.0D / (double)var1;
            }
        }

        if (this.worldObj.getBlockMaterial(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) == Material.lava)
        {
            this.motionY = 0.2000000029802322D;
            this.motionX = (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
            this.motionZ = (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
            this.worldObj.playSoundAtEntity(this, "random.fizz", 0.4F, 2.0F + this.rand.nextFloat() * 0.4F);
        }
        else if (var2 < 1.0D)
        {
            double var12 = var2 * 2.0D - 1.0D;
            this.motionY += 0.04D * var12;
        }
        else
        {
            if (this.motionY < 0.0D)
            {
                this.motionY /= 2.0D;
            }

            this.motionY += 0.007D;
        }

        if (!this.worldObj.isRemote)
        {
            this.pushOutOfBlocks(this.posX, (this.boundingBox.minY + this.boundingBox.maxY) / 2.0D, this.posZ);
        }

        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        float var13 = 0.98F;

        if (this.onGround)
        {
            var13 = 0.5880001F;
            int var14 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.boundingBox.minY) - 1, MathHelper.floor_double(this.posZ));

            if (var14 > 0)
            {
                var13 = Block.blocksList[var14].slipperiness * 0.98F;
            }
        }

        this.motionX *= (double)var13;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= (double)var13;

        if (this.onGround)
        {
            this.motionY *= -0.5D;
        }

        ++this.age;

        if (this.age >= 6000)
        {
            this.setDead();
        }
    }

    public int GetTrackerViewDistance()
    {
        return 64;
    }

    public int GetTrackerUpdateFrequency()
    {
        return 20;
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
            var2.writeInt(5);
            var2.writeInt(this.entityId);
            var2.writeInt(MathHelper.floor_double(this.posX * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.posY * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.posZ * 32.0D));
            var2.writeInt(this.getEntityItem().itemID);
            var2.writeInt(this.getEntityItem().stackSize);
            var2.writeInt(this.getEntityItem().getItemDamage());
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
}

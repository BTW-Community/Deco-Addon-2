package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class FCEntitySpiderWeb extends EntityThrowable implements FCIEntityPacketHandler
{
    public FCEntitySpiderWeb(World var1)
    {
        super(var1);
    }

    public FCEntitySpiderWeb(World var1, int var2)
    {
        this(var1);
    }

    public FCEntitySpiderWeb(World var1, EntityLiving var2)
    {
        super(var1, var2);
    }

    public FCEntitySpiderWeb(World var1, double var2, double var4, double var6)
    {
        super(var1, var2, var4, var6);
    }

    public FCEntitySpiderWeb(World var1, EntityLiving var2, Entity var3)
    {
        super(var1);
        this.SetThrower(var2);
        this.setSize(0.25F, 0.25F);
        this.setLocationAndAngles(var2.posX, var2.posY + (double)var2.getEyeHeight(), var2.posZ, var2.rotationYaw, var2.rotationPitch);
        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.posY -= 0.2D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        double var4 = var3.posY;

        if (this.worldObj.rayTraceBlocks_do_do(this.worldObj.getWorldVec3Pool().getVecFromPool(var2.posX, var2.posY + (double)var2.getEyeHeight(), var2.posZ), this.worldObj.getWorldVec3Pool().getVecFromPool(var3.posX, var4, var2.posZ), false, true) != null)
        {
            var4 = var3.posY + (double)(var3.getEyeHeight() / 2.0F);

            if (this.worldObj.rayTraceBlocks_do_do(this.worldObj.getWorldVec3Pool().getVecFromPool(var2.posX, var2.posY + (double)var2.getEyeHeight(), var2.posZ), this.worldObj.getWorldVec3Pool().getVecFromPool(var3.posX, var4, var2.posZ), false, true) != null)
            {
                var4 = var3.posY + (double)var3.getEyeHeight();
            }
        }

        double var6 = var3.posX - this.posX;
        double var8 = var4 - this.posY;
        double var10 = var3.posZ - this.posZ;
        this.setThrowableHeading(var6, var8, var10, 1.5F, 1.0F);
        this.motionY += 0.10000000149011612D;
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition var1)
    {
        Entity var2 = var1.entityHit;

        if (var2 != null)
        {
            var2.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0);

            if (!this.worldObj.isRemote)
            {
                int var3 = MathHelper.floor_double(var2.posX);
                int var4 = MathHelper.floor_double(var2.posY);
                int var5 = MathHelper.floor_double(var2.posZ);

                if (!this.AttemptToPlaceWebInBlock(var3, var4 - 1, var5))
                {
                    this.AttemptToPlaceWebInBlock(var3, var4, var5);
                }
            }
        }
        else if (!this.worldObj.isRemote)
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(var1.blockX, var1.blockY, var1.blockZ, var1.sideHit);
            this.AttemptToPlaceWebInBlock(var6.i, var6.j, var6.k);
        }

        this.setDead();
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
            var2.writeInt(10);
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

    private boolean AttemptToPlaceWebInBlock(int var1, int var2, int var3)
    {
        if (this.CanWebReplaceBlock(var1, var2, var3))
        {
            this.worldObj.setBlockWithNotify(var1, var2, var3, FCBetterThanWolves.fcBlockWeb.blockID);
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean CanWebReplaceBlock(int var1, int var2, int var3)
    {
        int var4 = this.worldObj.getBlockId(var1, var2, var3);
        Block var5 = Block.blocksList[var4];
        return var5 == null || var5.CanSpitWebReplaceBlock(this.worldObj, var1, var2, var3);
    }
}

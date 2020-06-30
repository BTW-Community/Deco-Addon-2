package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class FCEntityWaterWheel extends FCEntityMechPowerHorizontal
{
    public static final float m_fHeight = 4.8F;
    public static final float m_fWidth = 4.8F;
    public static final float m_fDepth = 0.8F;
    public static final int m_iMaxDamage = 160;
    public static final float m_fRotationPerTick = 0.25F;
    public static final int m_iTicksPerFullUpdate = 20;

    public FCEntityWaterWheel(World var1)
    {
        super(var1);
    }

    public FCEntityWaterWheel(World var1, double var2, double var4, double var6, boolean var8)
    {
        super(var1, var2, var4, var6, var8);
    }

    protected void entityInit()
    {
        super.entityInit();
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound var1)
    {
        var1.setBoolean("bWaterWheelIAligned", this.m_bIAligned);
        var1.setFloat("fRotation", this.m_fRotation);
        var1.setBoolean("bProvidingPower", this.m_bProvidingPower);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound var1)
    {
        this.m_bIAligned = var1.getBoolean("bWaterWheelIAligned");
        this.m_fRotation = var1.getFloat("fRotation");
        this.m_bProvidingPower = var1.getBoolean("bProvidingPower");
        this.InitBoundingBox();
    }

    public Packet GetSpawnPacketForThisEntity()
    {
        ByteArrayOutputStream var1 = new ByteArrayOutputStream();
        DataOutputStream var2 = new DataOutputStream(var1);

        try
        {
            byte var3 = 0;

            if (this.m_bIAligned)
            {
                var3 = 1;
            }

            var2.writeInt(2);
            var2.writeInt(this.entityId);
            var2.writeInt(MathHelper.floor_double(this.posX * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.posY * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.posZ * 32.0D));
            var2.writeByte(var3);
            var2.writeInt(this.getRotationSpeedScaled());
        }
        catch (Exception var4)
        {
            var4.printStackTrace();
        }

        return new Packet250CustomPayload("FC|SE", var1.toByteArray());
    }

    public float GetWidth()
    {
        return 4.8F;
    }

    public float GetHeight()
    {
        return 4.8F;
    }

    public float GetDepth()
    {
        return 0.8F;
    }

    public int GetMaxDamage()
    {
        return 160;
    }

    public int GetTicksPerFullUpdate()
    {
        return 20;
    }

    protected void DestroyWithDrop()
    {
        if (!this.isDead)
        {
            this.dropItemWithOffset(FCBetterThanWolves.fcItemWaterWheel.itemID, 1, 0.0F);
            this.setDead();
        }
    }

    public boolean ValidateAreaAroundDevice()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);
        return WaterWheelValidateAreaAroundBlock(this.worldObj, var1, var2, var3, this.m_bIAligned);
    }

    protected float ComputeRotation()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);
        float var4 = 0.0F;
        int var5 = var2 - 2;
        int var6 = this.worldObj.getBlockId(var1, var5, var3);

        if (var6 == Block.waterMoving.blockID || var6 == Block.waterStill.blockID)
        {
            Vec3 var7 = this.getFlowVector(this.worldObj, var1, var5, var3);

            if (this.m_bIAligned)
            {
                if (var7.zCoord > 0.33000001311302185D)
                {
                    var4 = -0.25F;
                }
                else if (var7.zCoord < -0.33000001311302185D)
                {
                    var4 = 0.25F;
                }
            }
            else if (var7.xCoord > 0.33000001311302185D)
            {
                var4 = 0.25F;
            }
            else if (var7.xCoord < -0.33000001311302185D)
            {
                var4 = -0.25F;
            }
        }

        byte var8;
        byte var10;

        if (this.m_bIAligned)
        {
            var10 = 0;
            var8 = 2;
        }
        else
        {
            var10 = 2;
            var8 = 0;
        }

        var6 = this.worldObj.getBlockId(var1 + var10, var2, var3 - var8);
        BlockFluid var9;

        if (var6 == Block.waterMoving.blockID || var6 == Block.waterStill.blockID)
        {
            var9 = (BlockFluid)((BlockFluid)Block.blocksList[var6]);
            var4 -= 0.25F;
        }

        var6 = this.worldObj.getBlockId(var1 - var10, var2, var3 + var8);

        if (var6 == Block.waterMoving.blockID || var6 == Block.waterStill.blockID)
        {
            var9 = (BlockFluid)((BlockFluid)Block.blocksList[var6]);
            var4 += 0.25F;
        }

        if (var4 > 0.25F)
        {
            var4 = 0.25F;
        }
        else if (var4 <= -0.25F)
        {
            var4 = -0.25F;
        }

        return var4;
    }

    public static boolean WaterWheelValidateAreaAroundBlock(World var0, int var1, int var2, int var3, boolean var4)
    {
        byte var5;
        byte var6;

        if (var4)
        {
            var5 = 0;
            var6 = 1;
        }
        else
        {
            var5 = 1;
            var6 = 0;
        }

        for (int var7 = -2; var7 <= 2; ++var7)
        {
            for (int var8 = -2; var8 <= 2; ++var8)
            {
                if (var7 != 0 || var8 != 0)
                {
                    int var9 = var1 + var5 * var8;
                    int var10 = var2 + var7;
                    int var11 = var3 + var6 * var8;

                    if (!IsValidBlockForWaterWheelToOccupy(var0, var9, var10, var11))
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static boolean IsValidBlockForWaterWheelToOccupy(World var0, int var1, int var2, int var3)
    {
        if (!var0.isAirBlock(var1, var2, var3))
        {
            int var4 = var0.getBlockId(var1, var2, var3);

            if (var4 != Block.waterMoving.blockID && var4 != Block.waterStill.blockID)
            {
                return false;
            }
        }

        return true;
    }

    private Vec3 getFlowVector(IBlockAccess var1, int var2, int var3, int var4)
    {
        Vec3 var5 = var1.getWorldVec3Pool().getVecFromPool(0.0D, 0.0D, 0.0D);
        int var6 = this.getEffectiveFlowDecay(var1, var2, var3, var4);

        for (int var7 = 0; var7 < 4; ++var7)
        {
            int var8 = var2;
            int var10 = var4;

            if (var7 == 0)
            {
                var8 = var2 - 1;
            }

            if (var7 == 1)
            {
                var10 = var4 - 1;
            }

            if (var7 == 2)
            {
                ++var8;
            }

            if (var7 == 3)
            {
                ++var10;
            }

            int var11 = this.getEffectiveFlowDecay(var1, var8, var3, var10);
            int var12;

            if (var11 < 0)
            {
                if (!var1.getBlockMaterial(var8, var3, var10).blocksMovement())
                {
                    var11 = this.getEffectiveFlowDecay(var1, var8, var3 - 1, var10);

                    if (var11 >= 0)
                    {
                        var12 = var11 - (var6 - 8);
                        var5 = var5.addVector((double)((var8 - var2) * var12), (double)((var3 - var3) * var12), (double)((var10 - var4) * var12));
                    }
                }
            }
            else if (var11 >= 0)
            {
                var12 = var11 - var6;
                var5 = var5.addVector((double)((var8 - var2) * var12), (double)((var3 - var3) * var12), (double)((var10 - var4) * var12));
            }
        }

        if (var1.getBlockMetadata(var2, var3, var4) >= 8)
        {
            boolean var13 = false;

            if (var13 || this.isBlockSolid(var1, var2, var3, var4 - 1, 2))
            {
                var13 = true;
            }

            if (var13 || this.isBlockSolid(var1, var2, var3, var4 + 1, 3))
            {
                var13 = true;
            }

            if (var13 || this.isBlockSolid(var1, var2 - 1, var3, var4, 4))
            {
                var13 = true;
            }

            if (var13 || this.isBlockSolid(var1, var2 + 1, var3, var4, 5))
            {
                var13 = true;
            }

            if (var13 || this.isBlockSolid(var1, var2, var3 + 1, var4 - 1, 2))
            {
                var13 = true;
            }

            if (var13 || this.isBlockSolid(var1, var2, var3 + 1, var4 + 1, 3))
            {
                var13 = true;
            }

            if (var13 || this.isBlockSolid(var1, var2 - 1, var3 + 1, var4, 4))
            {
                var13 = true;
            }

            if (var13 || this.isBlockSolid(var1, var2 + 1, var3 + 1, var4, 5))
            {
                var13 = true;
            }

            if (var13)
            {
                var5 = var5.normalize().addVector(0.0D, -6.0D, 0.0D);
            }
        }

        var5 = var5.normalize();
        return var5;
    }

    private boolean isBlockSolid(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        Material var6 = var1.getBlockMaterial(var2, var3, var4);
        return var6 == Block.waterMoving.blockMaterial ? false : (var5 == 1 ? true : (var6 == Material.ice ? false : var1.getBlockMaterial(var2, var3, var4).isSolid()));
    }

    private int getEffectiveFlowDecay(IBlockAccess var1, int var2, int var3, int var4)
    {
        if (var1.getBlockMaterial(var2, var3, var4) != Block.waterMoving.blockMaterial)
        {
            return -1;
        }
        else
        {
            int var5 = var1.getBlockMetadata(var2, var3, var4);

            if (var5 >= 8)
            {
                var5 = 0;
            }

            return var5;
        }
    }
}

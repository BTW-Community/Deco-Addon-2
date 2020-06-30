package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Iterator;
import java.util.List;

public class FCEntityUrn extends EntityThrowable implements FCIEntityPacketHandler
{
    public static final double m_dCubicRange = 4.0D;
    public int m_iItemShiftedIndex;

    public FCEntityUrn(World var1)
    {
        super(var1);
        this.m_iItemShiftedIndex = 0;
    }

    public FCEntityUrn(World var1, int var2)
    {
        this(var1);
        this.m_iItemShiftedIndex = var2;
    }

    public FCEntityUrn(World var1, EntityLiving var2, int var3)
    {
        super(var1, var2);
        this.m_iItemShiftedIndex = var3;
    }

    public FCEntityUrn(World var1, double var2, double var4, double var6, int var8)
    {
        super(var1, var2, var4, var6);
        this.m_iItemShiftedIndex = var8;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        var1.setInteger("m_iItemShiftedIndex", this.m_iItemShiftedIndex);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);
        this.m_iItemShiftedIndex = var1.getInteger("m_iItemShiftedIndex");
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition var1)
    {
        this.setDead();

        if (!this.worldObj.isRemote)
        {
            if (this.m_iItemShiftedIndex == FCBetterThanWolves.fcItemSoulUrn.itemID)
            {
                boolean var2 = true;

                if (var1.entityHit != null)
                {
                    var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0);

                    if (var1.entityHit instanceof FCEntityZombie)
                    {
                        FCEntityZombie var3 = (FCEntityZombie)((FCEntityZombie)var1.entityHit);

                        if (var3.AttemptToStartCure())
                        {
                            var2 = false;
                        }
                    }
                }
                else if (this.m_iItemShiftedIndex == FCBetterThanWolves.fcItemSoulUrn.itemID && AttemptToCreateGolemOrWither(this.worldObj, var1.blockX, var1.blockY, var1.blockZ))
                {
                    var2 = false;
                }

                if (var2)
                {
                    AxisAlignedBB var7 = AxisAlignedBB.getAABBPool().getAABB(var1.hitVec.xCoord - 4.0D, var1.hitVec.yCoord - 4.0D, var1.hitVec.zCoord - 4.0D, var1.hitVec.xCoord + 4.0D, var1.hitVec.yCoord + 4.0D, var1.hitVec.zCoord + 4.0D);
                    List var4 = this.worldObj.getEntitiesWithinAABB(FCEntityZombie.class, var7);
                    Iterator var5 = var4.iterator();

                    while (var5.hasNext() && var2)
                    {
                        FCEntityZombie var6 = (FCEntityZombie)var5.next();

                        if (var6.AttemptToStartCure())
                        {
                            var2 = false;
                            var6.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0);
                        }
                    }
                }

                this.worldObj.playAuxSFX(2248, (int)Math.round(this.posX), (int)Math.round(this.posY), (int)Math.round(this.posZ), 0);
            }
            else if (var1.entityHit != null)
            {
                var1.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0);
            }
        }
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
            var2.writeInt(7);
            var2.writeInt(this.entityId);
            var2.writeInt(MathHelper.floor_double(this.posX * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.posY * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.posZ * 32.0D));
            var2.writeInt(this.m_iItemShiftedIndex);
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

    public static boolean AttemptToCreateGolemOrWither(World var0, int var1, int var2, int var3)
    {
        int var4 = var2;

        while (true)
        {
            if (var4 <= var2 + 2)
            {
                if (IsGolemHeadBlock(var0, var1, var4, var3))
                {
                    return AttemptToCreateSnowOrIronGolem(var0, var1, var4, var3);
                }

                if (IsWitherHeadBlock(var0, var1, var4, var3))
                {
                    return AttemptToCreateWither(var0, var1, var4, var3);
                }

                if (IsValidBodyBlockForSnowGolem(var0, var1, var4, var3) || IsValidBodyBlockForIronGolem(var0, var1, var4, var3) || IsWitherBodyBlock(var0, var1, var4, var3))
                {
                    ++var4;
                    continue;
                }
            }

            if (IsValidBodyBlockForIronGolem(var0, var1, var2, var3))
            {
                var4 = var2 + 1;

                for (int var5 = var1 - 1; var5 <= var1 + 1; ++var5)
                {
                    for (int var6 = var3 - 1; var6 <= var3 + 1; ++var6)
                    {
                        if (IsGolemHeadBlock(var0, var5, var4, var6))
                        {
                            return AttemptToCreateSnowOrIronGolem(var0, var5, var4, var6);
                        }
                    }
                }
            }

            return false;
        }
    }

    private static boolean IsGolemHeadBlock(World var0, int var1, int var2, int var3)
    {
        int var4 = var0.getBlockId(var1, var2, var3);
        return var4 == Block.pumpkin.blockID || var4 == Block.pumpkinLantern.blockID;
    }

    private static boolean IsWitherHeadBlock(World var0, int var1, int var2, int var3)
    {
        int var4 = var0.getBlockId(var1, var2, var3);

        if (var4 == Block.skull.blockID)
        {
            TileEntity var5 = var0.getBlockTileEntity(var1, var2, var3);

            if (var5 != null && var5 instanceof TileEntitySkull)
            {
                return ((TileEntitySkull)var5).getSkullType() == 5;
            }
        }

        return false;
    }

    private static boolean IsValidBodyBlockForSnowGolem(World var0, int var1, int var2, int var3)
    {
        int var4 = var0.getBlockId(var1, var2, var3);
        return var4 == Block.blockSnow.blockID || var4 == FCBetterThanWolves.fcBlockSnowLoose.blockID || var4 == FCBetterThanWolves.fcBlockSnowSolid.blockID;
    }

    private static boolean IsValidBodyBlockForIronGolem(World var0, int var1, int var2, int var3)
    {
        int var4 = var0.getBlockId(var1, var2, var3);
        return var4 == Block.blockIron.blockID;
    }

    private static boolean IsWitherBodyBlock(World var0, int var1, int var2, int var3)
    {
        int var4 = var0.getBlockId(var1, var2, var3);

        if (var4 == FCBetterThanWolves.fcAestheticOpaque.blockID)
        {
            int var5 = var0.getBlockMetadata(var1, var2, var3);

            if (var5 == 15)
            {
                return true;
            }
        }

        return false;
    }

    private static boolean AttemptToCreateSnowOrIronGolem(World var0, int var1, int var2, int var3)
    {
        if (IsValidBodyBlockForSnowGolem(var0, var1, var2 - 1, var3) && IsValidBodyBlockForSnowGolem(var0, var1, var2 - 2, var3))
        {
            var0.setBlock(var1, var2, var3, 0);
            var0.setBlock(var1, var2 - 1, var3, 0);
            var0.setBlock(var1, var2 - 2, var3, 0);
            var0.notifyBlockChange(var1, var2, var3, 0);
            var0.notifyBlockChange(var1, var2 - 1, var3, 0);
            var0.notifyBlockChange(var1, var2 - 2, var3, 0);
            FCEntitySnowman var7 = new FCEntitySnowman(var0);
            var7.setLocationAndAngles((double)var1 + 0.5D, (double)var2 - 1.95D, (double)var3 + 0.5D, 0.0F, 0.0F);
            var0.spawnEntityInWorld(var7);
            var0.playAuxSFX(2263, var1, var2, var3, 0);
            return true;
        }
        else if (IsValidBodyBlockForIronGolem(var0, var1, var2 - 1, var3) && IsValidBodyBlockForIronGolem(var0, var1, var2 - 2, var3))
        {
            boolean var4 = IsValidBodyBlockForIronGolem(var0, var1 - 1, var2 - 1, var3) && IsValidBodyBlockForIronGolem(var0, var1 + 1, var2 - 1, var3);
            boolean var5 = IsValidBodyBlockForIronGolem(var0, var1, var2 - 1, var3 - 1) && IsValidBodyBlockForIronGolem(var0, var1, var2 - 1, var3 + 1);

            if (var4 || var5)
            {
                var0.setBlock(var1, var2, var3, 0);
                var0.setBlock(var1, var2 - 1, var3, 0);
                var0.setBlock(var1, var2 - 2, var3, 0);

                if (var4)
                {
                    var0.setBlock(var1 - 1, var2 - 1, var3, 0);
                    var0.setBlock(var1 + 1, var2 - 1, var3, 0);
                }
                else
                {
                    var0.setBlock(var1, var2 - 1, var3 - 1, 0);
                    var0.setBlock(var1, var2 - 1, var3 + 1, 0);
                }

                var0.notifyBlockChange(var1, var2, var3, 0);
                var0.notifyBlockChange(var1, var2 - 1, var3, 0);
                var0.notifyBlockChange(var1, var2 - 2, var3, 0);

                if (var4)
                {
                    var0.notifyBlockChange(var1 - 1, var2 - 1, var3, 0);
                    var0.notifyBlockChange(var1 + 1, var2 - 1, var3, 0);
                }
                else
                {
                    var0.notifyBlockChange(var1, var2 - 1, var3 - 1, 0);
                    var0.notifyBlockChange(var1, var2 - 1, var3 + 1, 0);
                }

                EntityIronGolem var6 = new EntityIronGolem(var0);
                var6.setPlayerCreated(true);
                var6.setLocationAndAngles((double)var1 + 0.5D, (double)var2 - 1.95D, (double)var3 + 0.5D, 0.0F, 0.0F);
                var0.spawnEntityInWorld(var6);
                var0.playAuxSFX(2264, var1, var2, var3, 0);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    private static boolean AttemptToCreateWither(World var0, int var1, int var2, int var3)
    {
        if (var2 >= 2 && var0.provider.dimensionId == 0)
        {
            int var4;

            for (var4 = -2; var4 <= 0; ++var4)
            {
                if (IsWitherBodyBlock(var0, var1, var2 - 1, var3 + var4) && IsWitherBodyBlock(var0, var1, var2 - 1, var3 + var4 + 1) && IsWitherBodyBlock(var0, var1, var2 - 2, var3 + var4 + 1) && IsWitherBodyBlock(var0, var1, var2 - 1, var3 + var4 + 2) && IsWitherHeadBlock(var0, var1, var2, var3 + var4) && IsWitherHeadBlock(var0, var1, var2, var3 + var4 + 1) && IsWitherHeadBlock(var0, var1, var2, var3 + var4 + 2))
                {
                    var0.SetBlockMetadataWithNotify(var1, var2, var3 + var4, 8, 2);
                    var0.SetBlockMetadataWithNotify(var1, var2, var3 + var4 + 1, 8, 2);
                    var0.SetBlockMetadataWithNotify(var1, var2, var3 + var4 + 2, 8, 2);
                    var0.setBlock(var1, var2, var3 + var4, 0, 0, 2);
                    var0.setBlock(var1, var2, var3 + var4 + 1, 0, 0, 2);
                    var0.setBlock(var1, var2, var3 + var4 + 2, 0, 0, 2);
                    var0.setBlock(var1, var2 - 1, var3 + var4, 0, 0, 2);
                    var0.setBlock(var1, var2 - 1, var3 + var4 + 1, 0, 0, 2);
                    var0.setBlock(var1, var2 - 1, var3 + var4 + 2, 0, 0, 2);
                    var0.setBlock(var1, var2 - 2, var3 + var4 + 1, 0, 0, 2);
                    FCEntityWitherPersistent.SummonWitherAtLocation(var0, var1, var2, var3 + var4 + 1);
                    var0.notifyBlockChange(var1, var2, var3 + var4, 0);
                    var0.notifyBlockChange(var1, var2, var3 + var4 + 1, 0);
                    var0.notifyBlockChange(var1, var2, var3 + var4 + 2, 0);
                    var0.notifyBlockChange(var1, var2 - 1, var3 + var4, 0);
                    var0.notifyBlockChange(var1, var2 - 1, var3 + var4 + 1, 0);
                    var0.notifyBlockChange(var1, var2 - 1, var3 + var4 + 2, 0);
                    var0.notifyBlockChange(var1, var2 - 2, var3 + var4 + 1, 0);
                    return true;
                }
            }

            for (var4 = -2; var4 <= 0; ++var4)
            {
                if (IsWitherBodyBlock(var0, var1 + var4, var2 - 1, var3) && IsWitherBodyBlock(var0, var1 + var4 + 1, var2 - 1, var3) && IsWitherBodyBlock(var0, var1 + var4 + 1, var2 - 2, var3) && IsWitherBodyBlock(var0, var1 + var4 + 2, var2 - 1, var3) && IsWitherHeadBlock(var0, var1 + var4, var2, var3) && IsWitherHeadBlock(var0, var1 + var4 + 1, var2, var3) && IsWitherHeadBlock(var0, var1 + var4 + 2, var2, var3))
                {
                    var0.SetBlockMetadataWithNotify(var1 + var4, var2, var3, 8, 2);
                    var0.SetBlockMetadataWithNotify(var1 + var4 + 1, var2, var3, 8, 2);
                    var0.SetBlockMetadataWithNotify(var1 + var4 + 2, var2, var3, 8, 2);
                    var0.setBlock(var1 + var4, var2, var3, 0, 0, 2);
                    var0.setBlock(var1 + var4 + 1, var2, var3, 0, 0, 2);
                    var0.setBlock(var1 + var4 + 2, var2, var3, 0, 0, 2);
                    var0.setBlock(var1 + var4, var2 - 1, var3, 0, 0, 2);
                    var0.setBlock(var1 + var4 + 1, var2 - 1, var3, 0, 0, 2);
                    var0.setBlock(var1 + var4 + 2, var2 - 1, var3, 0, 0, 2);
                    var0.setBlock(var1 + var4 + 1, var2 - 2, var3, 0, 0, 2);
                    FCEntityWitherPersistent.SummonWitherAtLocation(var0, var1 + var4 + 1, var2, var3);
                    var0.notifyBlockChange(var1 + var4, var2, var3, 0);
                    var0.notifyBlockChange(var1 + var4 + 1, var2, var3, 0);
                    var0.notifyBlockChange(var1 + var4 + 2, var2, var3, 0);
                    var0.notifyBlockChange(var1 + var4, var2 - 1, var3, 0);
                    var0.notifyBlockChange(var1 + var4 + 1, var2 - 1, var3, 0);
                    var0.notifyBlockChange(var1 + var4 + 2, var2 - 1, var3, 0);
                    var0.notifyBlockChange(var1 + var4 + 1, var2 - 2, var3, 0);
                    return true;
                }
            }
        }

        return false;
    }
}

package net.minecraft.src;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class FCEntityWindMillVertical extends FCEntityMechPower
{
    public static final float m_fHeight = 6.8F;
    public static final float m_fWidth = 8.8F;
    private static final int m_iMaxDamage = 160;
    private static final float m_fRotationPerTick = -0.12F;
    private static final float m_fRotationPerTickInStorm = -2.0F;
    private static final float m_fRotationPerTickInHell = -0.07F;
    private static final int m_iTicksPerFullUpdate = 20;
    private static final int m_iUpdatesToOverpower = 30;
    private static final int m_iBladeColor0DataWatcherID = 23;
    private static final int m_iBladeColor1DataWatcherID = 24;
    private static final int m_iBladeColor2DataWatcherID = 25;
    private static final int m_iBladeColor3DataWatcherID = 26;
    private static final int m_iBladeColor4DataWatcherID = 27;
    private static final int m_iBladeColor5DataWatcherID = 28;
    private static final int m_iBladeColor6DataWatcherID = 29;
    private static final int m_iBladeColor7DataWatcherID = 30;
    private int m_iCurrentBladeColoringIndex;
    protected int m_iOverpowerTimer;

    public FCEntityWindMillVertical(World var1)
    {
        super(var1);
        this.m_iCurrentBladeColoringIndex = 0;
    }

    public FCEntityWindMillVertical(World var1, double var2, double var4, double var6)
    {
        super(var1, var2, var4, var6);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(23, new Byte((byte)0));
        this.dataWatcher.addObject(24, new Byte((byte)0));
        this.dataWatcher.addObject(25, new Byte((byte)0));
        this.dataWatcher.addObject(26, new Byte((byte)0));
        this.dataWatcher.addObject(27, new Byte((byte)0));
        this.dataWatcher.addObject(28, new Byte((byte)0));
        this.dataWatcher.addObject(29, new Byte((byte)0));
        this.dataWatcher.addObject(30, new Byte((byte)0));
    }

    public int getBladeColor(int var1)
    {
        return this.dataWatcher.getWatchableObjectByte(23 + var1);
    }

    public void setBladeColor(int var1, int var2)
    {
        this.dataWatcher.updateObject(23 + var1, Byte.valueOf((byte)var2));
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound var1)
    {
        var1.setFloat("fRotation", this.m_fRotation);
        var1.setBoolean("bProvidingPower", this.m_bProvidingPower);
        var1.setInteger("iOverpowerTimer", this.m_iOverpowerTimer);
        var1.setInteger("iBladeColors0", this.getBladeColor(0));
        var1.setInteger("iBladeColors1", this.getBladeColor(1));
        var1.setInteger("iBladeColors2", this.getBladeColor(2));
        var1.setInteger("iBladeColors3", this.getBladeColor(3));
        var1.setInteger("iBladeColors4", this.getBladeColor(4));
        var1.setInteger("iBladeColors5", this.getBladeColor(5));
        var1.setInteger("iBladeColors6", this.getBladeColor(6));
        var1.setInteger("iBladeColors7", this.getBladeColor(7));
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound var1)
    {
        this.m_fRotation = var1.getFloat("fRotation");
        this.m_bProvidingPower = var1.getBoolean("bProvidingPower");
        this.m_iOverpowerTimer = var1.getInteger("iOverpowerTimer");
        this.setBladeColor(0, var1.getInteger("iBladeColors0"));
        this.setBladeColor(1, var1.getInteger("iBladeColors1"));
        this.setBladeColor(2, var1.getInteger("iBladeColors2"));
        this.setBladeColor(3, var1.getInteger("iBladeColors3"));
        this.setBladeColor(4, var1.getInteger("iBladeColors4"));
        this.setBladeColor(5, var1.getInteger("iBladeColors5"));
        this.setBladeColor(6, var1.getInteger("iBladeColors6"));
        this.setBladeColor(7, var1.getInteger("iBladeColors7"));
        this.InitBoundingBox();
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer var1)
    {
        ItemStack var2 = var1.inventory.getCurrentItem();

        if (var2 != null && (var2.itemID == Item.dyePowder.itemID || var2.itemID == FCBetterThanWolves.fcItemDung.itemID))
        {
            if (!this.worldObj.isRemote)
            {
                boolean var3 = false;
                int var4;

                if (var2.itemID == Item.dyePowder.itemID)
                {
                    var4 = BlockCloth.getBlockFromDye(var2.getItemDamage());
                }
                else
                {
                    var4 = 12;
                }

                this.setBladeColor(this.m_iCurrentBladeColoringIndex, var4);
                ++this.m_iCurrentBladeColoringIndex;

                if (this.m_iCurrentBladeColoringIndex >= 8)
                {
                    this.m_iCurrentBladeColoringIndex = 0;
                }
            }

            --var2.stackSize;

            if (var2.stackSize == 0)
            {
                var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
            }

            return true;
        }
        else
        {
            return super.interact(var1);
        }
    }

    /**
     * Will get destroyed next tick.
     */
    public void setDead()
    {
        if (this.m_bProvidingPower)
        {
            boolean[] var1 = new boolean[7];
            int var2;

            for (var2 = 0; var2 < 7; ++var2)
            {
                var1[var2] = false;
            }

            var2 = MathHelper.floor_double(this.posX);
            int var3 = MathHelper.floor_double(this.posY);
            int var4 = MathHelper.floor_double(this.posZ);
            FCBlockAxle var5 = (FCBlockAxle)FCBetterThanWolves.fcBlockAxlePowerSource;
            int var6;
            int var7;
            int var8;

            for (var6 = var3 - 2; var6 <= var3 + 2; ++var6)
            {
                var7 = this.worldObj.getBlockId(var2, var6, var4);

                if (var7 == FCBetterThanWolves.fcBlockAxlePowerSource.blockID)
                {
                    var8 = var5.GetAxisAlignment(this.worldObj, var2, var6, var4);

                    if (var8 == 0)
                    {
                        int var9 = var6 - var3 + 3;
                        var1[var9] = true;
                        this.worldObj.setBlock(var2, var6, var4, FCBetterThanWolves.fcBlockAxle.blockID, 0, 2);
                    }
                }
            }

            for (var6 = var3 - 3; var6 <= var3 + 3; var6 += 6)
            {
                var7 = this.worldObj.getBlockId(var2, var6, var4);

                if (var7 == FCBetterThanWolves.fcBlockAxlePowerSource.blockID)
                {
                    var8 = var5.GetAxisAlignment(this.worldObj, var2, var6, var4);

                    if (var8 == 0)
                    {
                        this.worldObj.setBlock(var2, var6, var4, FCBetterThanWolves.fcBlockAxle.blockID, 0, 3);
                    }
                }
            }
        }

        super.setDead();
    }

    public float GetWidth()
    {
        return 8.8F;
    }

    public float GetHeight()
    {
        return 6.8F;
    }

    public float GetDepth()
    {
        return 8.8F;
    }

    public int GetMaxDamage()
    {
        return 160;
    }

    public int GetTicksPerFullUpdate()
    {
        return 20;
    }

    protected void OnFullUpdateServer()
    {
        super.OnFullUpdateServer();

        if (this.m_iOverpowerTimer >= 0)
        {
            if (this.m_iOverpowerTimer > 0)
            {
                --this.m_iOverpowerTimer;
            }

            if (this.m_iOverpowerTimer <= 0)
            {
                int var1 = MathHelper.floor_double(this.posX);
                int var2 = MathHelper.floor_double(this.posY);
                int var3 = MathHelper.floor_double(this.posZ);
                ((FCBlockAxle)((FCBlockAxle)FCBetterThanWolves.fcBlockAxle)).Overpower(this.worldObj, var1, var2, var3);
            }
        }
    }

    public void DestroyWithDrop()
    {
        if (!this.isDead)
        {
            this.dropItemWithOffset(FCBetterThanWolves.fcItemWindMillVertical.itemID, 1, 0.0F);
            this.setDead();
        }
    }

    public boolean ValidateAreaAroundDevice()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);
        return WindMillValidateAreaAroundBlock(this.worldObj, var1, var2, var3);
    }

    protected float ComputeRotation()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);
        float var4 = 0.0F;

        if (this.worldObj.provider.dimensionId == -1)
        {
            var4 = -0.07F;
            this.m_iOverpowerTimer = -1;
        }
        else if (this.worldObj.provider.dimensionId != 1 && this.CanSeeSky())
        {
            if (this.worldObj.isThundering() && this.IsBeingPrecipitatedOn())
            {
                var4 = -2.0F;

                if (this.m_iOverpowerTimer < 0)
                {
                    this.m_iOverpowerTimer = 30;
                }
            }
            else
            {
                var4 = -0.12F;
                this.m_iOverpowerTimer = -1;
            }
        }
        else
        {
            this.m_iOverpowerTimer = -1;
        }

        return var4;
    }

    protected boolean ValidateConnectedAxles()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);
        int var4;
        int var5;

        for (var4 = var2 - 3; var4 <= var2 + 3; ++var4)
        {
            var5 = this.worldObj.getBlockId(var1, var4, var3);

            if (!FCUtilsMechPower.IsBlockIDAxle(var5))
            {
                return false;
            }

            int var6 = ((FCBlockAxle)((FCBlockAxle)Block.blocksList[var5])).GetAxisAlignment(this.worldObj, var1, var4, var3);

            if (var6 != 0)
            {
                return false;
            }
        }

        if (!this.m_bProvidingPower)
        {
            for (var4 = var2 - 3; var4 <= var2 + 3; ++var4)
            {
                var5 = this.worldObj.getBlockId(var1, var4, var3);

                if (((FCBlockAxle)Block.blocksList[var5]).GetPowerLevel(this.worldObj, var1, var4, var3) > 0)
                {
                    return false;
                }
            }
        }
        else
        {
            for (var4 = var2 - 3; var4 <= var2 + 3; ++var4)
            {
                var5 = this.worldObj.getBlockId(var1, var4, var3);

                if (var5 != FCBetterThanWolves.fcBlockAxlePowerSource.blockID)
                {
                    this.PowerAxleColumn();
                    break;
                }
            }
        }

        return true;
    }

    protected void TransferPowerStateToConnectedAxles()
    {
        if (this.m_bProvidingPower)
        {
            this.PowerAxleColumn();
        }
        else
        {
            this.DepowerAxleColumn();
        }
    }

    protected void OnClientRotationOctantChange()
    {
        float var1 = this.getRotationSpeed();

        if (var1 < -0.12F)
        {
            int var2 = MathHelper.floor_double(this.posX);
            int var3 = MathHelper.floor_double(this.posY);
            int var4 = MathHelper.floor_double(this.posZ);
            int var5 = this.worldObj.getBlockId(var2, var3, var4);

            if (var5 == FCBetterThanWolves.fcBlockAxlePowerSource.blockID)
            {
                int var6 = ((FCBlockAxle)FCBetterThanWolves.fcBlockAxlePowerSource).GetAxisAlignment(this.worldObj, var2, var3, var4);
                this.ClientNotifyGearboxOfOverpoweredOctantChangeInDirection(var2, var3, var4, var6 << 1);
                this.ClientNotifyGearboxOfOverpoweredOctantChangeInDirection(var2, var3, var4, (var6 << 1) + 1);
            }
        }
    }

    public Packet GetSpawnPacketForThisEntity()
    {
        ByteArrayOutputStream var1 = new ByteArrayOutputStream();
        DataOutputStream var2 = new DataOutputStream(var1);

        try
        {
            var2.writeInt(9);
            var2.writeInt(this.entityId);
            var2.writeInt(MathHelper.floor_double(this.posX * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.posY * 32.0D));
            var2.writeInt(MathHelper.floor_double(this.posZ * 32.0D));
            var2.writeInt(this.getRotationSpeedScaled());
            var2.writeByte((byte)this.getBladeColor(0));
            var2.writeByte((byte)this.getBladeColor(1));
            var2.writeByte((byte)this.getBladeColor(2));
            var2.writeByte((byte)this.getBladeColor(3));
            var2.writeByte((byte)this.getBladeColor(4));
            var2.writeByte((byte)this.getBladeColor(5));
            var2.writeByte((byte)this.getBladeColor(6));
            var2.writeByte((byte)this.getBladeColor(7));
        }
        catch (Exception var4)
        {
            var4.printStackTrace();
        }

        return new Packet250CustomPayload("FC|SE", var1.toByteArray());
    }

    private void PowerAxleColumn()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);

        for (int var4 = var2 - 2; var4 <= var2 + 2; ++var4)
        {
            this.worldObj.setBlock(var1, var4, var3, FCBetterThanWolves.fcBlockAxlePowerSource.blockID, 0, 2);
        }

        this.worldObj.setBlock(var1, var2 + 3, var3, FCBetterThanWolves.fcBlockAxlePowerSource.blockID, 0, 3);
        this.worldObj.setBlock(var1, var2 + -3, var3, FCBetterThanWolves.fcBlockAxlePowerSource.blockID, 0, 3);
    }

    private void DepowerAxleColumn()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);

        for (int var4 = var2 - 2; var4 <= var2 + 2; ++var4)
        {
            this.worldObj.setBlock(var1, var4, var3, FCBetterThanWolves.fcBlockAxle.blockID, 0, 2);
        }

        this.worldObj.setBlock(var1, var2 + 3, var3, FCBetterThanWolves.fcBlockAxle.blockID, 0, 3);
        this.worldObj.setBlock(var1, var2 + -3, var3, FCBetterThanWolves.fcBlockAxle.blockID, 0, 3);
    }

    protected void ClientNotifyGearboxOfOverpoweredOctantChangeInDirection(int var1, int var2, int var3, int var4)
    {
        FCUtilsBlockPos var5 = new FCUtilsBlockPos(var1, var2, var3);

        for (int var6 = 0; var6 < 10; ++var6)
        {
            var5.AddFacingAsOffset(var4);
            int var7 = this.worldObj.getBlockId(var5.i, var5.j, var5.k);

            if (var7 != FCBetterThanWolves.fcBlockAxle.blockID && var7 != FCBetterThanWolves.fcBlockAxlePowerSource.blockID)
            {
                if (FCUtilsMechPower.IsPoweredGearBox(this.worldObj, var5.i, var5.j, var5.k))
                {
                    this.worldObj.playSound((double)var5.i + 0.5D, (double)var5.j + 0.5D, (double)var5.k + 0.5D, "random.chestclosed", 1.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.5F);
                }

                break;
            }

            if (!((FCBlockAxle)Block.blocksList[var7]).IsAxleOrientedTowardsFacing(this.worldObj, var5.i, var5.j, var5.k, var4))
            {
                break;
            }
        }
    }

    public static boolean WindMillValidateAreaAroundBlock(World var0, int var1, int var2, int var3)
    {
        for (int var4 = var1 - 4; var4 <= var1 + 4; ++var4)
        {
            for (int var5 = var2 - 3; var5 <= var2 + 3; ++var5)
            {
                for (int var6 = var3 - 4; var6 <= var3 + 4; ++var6)
                {
                    if ((var4 != var1 || var6 != var3) && !IsValidBlockForWindMillToOccupy(var0, var4, var5, var6))
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static boolean IsValidBlockForWindMillToOccupy(World var0, int var1, int var2, int var3)
    {
        return var0.isAirBlock(var1, var2, var3);
    }

    protected void InitBoundingBox()
    {
        this.boundingBox.setBounds(this.posX - (double)(this.GetWidth() * 0.5F), this.posY - (double)(this.GetHeight() * 0.5F), this.posZ - (double)(this.GetWidth() * 0.5F), this.posX + (double)(this.GetWidth() * 0.5F), this.posY + (double)(this.GetHeight() * 0.5F), this.posZ + (double)(this.GetWidth() * 0.5F));
    }

    private boolean CanSeeSky()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.posY);
        int var3 = MathHelper.floor_double(this.posZ);

        for (int var4 = var1 - 4; var4 <= var1 + 4; ++var4)
        {
            for (int var5 = var3 - 4; var5 <= var3 + 4; ++var5)
            {
                if (this.worldObj.canBlockSeeTheSky(var4, var2, var5))
                {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean IsBeingPrecipitatedOn()
    {
        if (this.worldObj.isRaining())
        {
            int var1 = MathHelper.floor_double(this.posX);
            int var2 = MathHelper.floor_double(this.posY);
            int var3 = MathHelper.floor_double(this.posZ);

            for (int var4 = var1 - 4; var4 <= var1 + 4; ++var4)
            {
                for (int var5 = var3 - 4; var5 <= var3 + 4; ++var5)
                {
                    if (this.worldObj.IsPrecipitatingAtPos(var4, var5))
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    protected AxisAlignedBB GetDeviceBounds()
    {
        return AxisAlignedBB.getAABBPool().getAABB(this.posX - (double)(this.GetWidth() * 0.5F), this.posY - (double)(this.GetHeight() * 0.5F), this.posZ - (double)(this.GetWidth() * 0.5F), this.posX + (double)(this.GetWidth() * 0.5F), this.posY + (double)(this.GetHeight() * 0.5F), this.posZ + (double)(this.GetWidth() * 0.5F));
    }
}

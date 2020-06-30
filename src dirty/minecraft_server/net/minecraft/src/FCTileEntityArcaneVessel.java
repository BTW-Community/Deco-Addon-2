package net.minecraft.src;

public class FCTileEntityArcaneVessel extends TileEntity implements FCITileEntityDataPacketHandler
{
    public static final int m_iMaxContainedExperience = 1000;
    private static final int m_iMinTempleExperience = 200;
    private static final int m_iMaxTempleExperience = 256;
    public static final int m_iMaxVisualExperienceLevel = 10;
    private final int m_iXPEjectUnitSize = 20;
    private int m_iVisualExperienceLevel = 0;
    private int m_iContainedRegularExperience = 0;
    private int m_iContainedDragonExperience = 0;

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
        var1.setInteger("regXP", this.m_iContainedRegularExperience);
        var1.setInteger("dragXP", this.m_iContainedDragonExperience);
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
        this.m_iContainedRegularExperience = var1.getInteger("regXP");
        this.m_iContainedDragonExperience = var1.getInteger("dragXP");
        int var2 = this.m_iContainedRegularExperience + this.m_iContainedDragonExperience;
        this.m_iVisualExperienceLevel = (int)(10.0F * ((float)var2 / 1000.0F));

        if (var2 > 0 && this.m_iVisualExperienceLevel == 0)
        {
            this.m_iVisualExperienceLevel = 1;
        }
    }

    /**
     * Overriden in a sign to provide the text.
     */
    public Packet getDescriptionPacket()
    {
        NBTTagCompound var1 = new NBTTagCompound();
        var1.setByte("x", (byte)this.m_iVisualExperienceLevel);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, var1);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        if (!this.worldObj.isRemote)
        {
            int var1 = this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord);
            Block var2 = Block.blocksList[var1];

            if (var2 != null && var2 instanceof FCBlockArcaneVessel)
            {
                FCBlockArcaneVessel var3 = (FCBlockArcaneVessel)var2;

                if (var3.GetMechanicallyPoweredFlag(this.worldObj, this.xCoord, this.yCoord, this.zCoord))
                {
                    int var4 = var3.GetTiltFacing(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                    this.AttemptToSpillXPFromInv(var4);
                }
            }
        }
    }

    public void readNBTFromPacket(NBTTagCompound var1)
    {
        this.m_iVisualExperienceLevel = var1.getByte("x");
        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
    }

    public int GetVisualExperienceLevel()
    {
        return this.m_iVisualExperienceLevel;
    }

    public int GetContainedRegularExperience()
    {
        return this.m_iContainedRegularExperience;
    }

    public void SetContainedRegularExperience(int var1)
    {
        this.m_iContainedRegularExperience = var1;
        this.ValidateVisualExperience();
    }

    public int GetContainedDragonExperience()
    {
        return this.m_iContainedDragonExperience;
    }

    public void SetContainedDragonExperience(int var1)
    {
        this.m_iContainedDragonExperience = var1;
        this.ValidateVisualExperience();
    }

    public int GetContainedTotalExperience()
    {
        return this.m_iContainedDragonExperience + this.m_iContainedRegularExperience;
    }

    public void ValidateVisualExperience()
    {
        int var1 = this.m_iContainedRegularExperience + this.m_iContainedDragonExperience;
        int var2 = (int)(10.0F * ((float)var1 / 1000.0F));

        if (var1 > 0 && var2 == 0)
        {
            var2 = 1;
        }

        if (var2 != this.m_iVisualExperienceLevel)
        {
            this.m_iVisualExperienceLevel = var2;
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        }
    }

    public void InitTempleExperience()
    {
        this.SetContainedRegularExperience(this.worldObj.rand.nextInt(56) + 200);
    }

    public boolean AttemptToSwallowXPOrb(World var1, int var2, int var3, int var4, EntityXPOrb var5)
    {
        int var6 = this.m_iContainedRegularExperience + this.m_iContainedDragonExperience;
        int var7 = 1000 - var6;

        if (var7 > 0)
        {
            boolean var8 = false;
            boolean var9 = var5.m_bNotPlayerOwned;
            int var10;

            if (var5.xpValue <= var7)
            {
                var10 = var5.xpValue;
                var5.setDead();
            }
            else
            {
                var10 = var7;
            }

            if (var9)
            {
                this.SetContainedDragonExperience(this.m_iContainedDragonExperience + var10);
            }
            else
            {
                this.SetContainedRegularExperience(this.m_iContainedRegularExperience + var10);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    private void AttemptToSpillXPFromInv(int var1)
    {
        int var2 = 0;
        boolean var3 = false;

        if (this.m_iContainedDragonExperience > 0 || this.m_iContainedRegularExperience > 0 && !this.IsTiltedOutputBlocked(var1))
        {
            if (this.m_iContainedDragonExperience > 0)
            {
                var3 = true;

                if (this.m_iContainedDragonExperience < 20)
                {
                    var2 = this.m_iContainedDragonExperience;
                }
                else
                {
                    var2 = 20;
                }

                this.SetContainedDragonExperience(this.m_iContainedDragonExperience - var2);
            }
            else
            {
                if (this.m_iContainedRegularExperience < 20)
                {
                    var2 = this.m_iContainedRegularExperience;
                }
                else
                {
                    var2 = 20;
                }

                this.SetContainedRegularExperience(this.m_iContainedRegularExperience - var2);
            }
        }

        if (var2 > 0)
        {
            this.SpillXPOrb(var2, var3, var1);
        }
    }

    private boolean IsTiltedOutputBlocked(int var1)
    {
        FCUtilsBlockPos var2 = new FCUtilsBlockPos(this.xCoord, this.yCoord, this.zCoord);
        var2.AddFacingAsOffset(var1);

        if (!this.worldObj.isAirBlock(var2.i, var2.j, var2.k) && !FCUtilsWorld.IsReplaceableBlock(this.worldObj, var2.i, var2.j, var2.k))
        {
            int var3 = this.worldObj.getBlockId(var2.i, var2.j, var2.k);
            Block var4 = Block.blocksList[var3];

            if (var4.blockMaterial.isSolid())
            {
                return true;
            }
        }

        return false;
    }

    public void EjectContentsOnBlockBreak()
    {
        int var1;

        while (this.m_iContainedRegularExperience > 0)
        {
            var1 = 20;

            if (this.m_iContainedRegularExperience < 20)
            {
                var1 = this.m_iContainedRegularExperience;
            }

            this.EjectXPOrbOnBlockBreak(var1, false);
            this.m_iContainedRegularExperience -= var1;
        }

        while (this.m_iContainedDragonExperience > 0)
        {
            var1 = 20;

            if (this.m_iContainedDragonExperience < 20)
            {
                var1 = this.m_iContainedDragonExperience;
            }

            this.EjectXPOrbOnBlockBreak(var1, true);
            this.m_iContainedDragonExperience -= var1;
        }
    }

    private void SpillXPOrb(int var1, boolean var2, int var3)
    {
        Vec3 var4 = FCUtilsMisc.ConvertBlockFacingToVector(var3);
        var4.xCoord *= 0.5D;
        var4.yCoord *= 0.5D;
        var4.zCoord *= 0.5D;
        var4.xCoord += (double)((float)this.xCoord + 0.5F);
        var4.yCoord += (double)((float)this.yCoord + 0.25F);
        var4.zCoord += (double)((float)this.zCoord + 0.5F + this.worldObj.rand.nextFloat() * 0.3F);

        if (var4.xCoord <= 0.10000000149011612D && var4.xCoord >= -0.10000000149011612D)
        {
            var4.zCoord += (double)(this.worldObj.rand.nextFloat() * 0.5F - 0.25F);
        }
        else
        {
            var4.xCoord += (double)(this.worldObj.rand.nextFloat() * 0.5F - 0.25F);
        }

        EntityXPOrb var5 = new EntityXPOrb(this.worldObj, var4.xCoord, var4.yCoord, var4.zCoord, var1, var2);
        Vec3 var6 = FCUtilsMisc.ConvertBlockFacingToVector(var3);
        var6.xCoord *= 0.10000000149011612D;
        var6.yCoord *= 0.10000000149011612D;
        var6.zCoord *= 0.10000000149011612D;
        var5.motionX = var6.xCoord;
        var5.motionY = var6.yCoord;
        var5.motionZ = var6.zCoord;
        this.worldObj.spawnEntityInWorld(var5);
    }

    private void EjectXPOrbOnBlockBreak(int var1, boolean var2)
    {
        double var3 = this.worldObj.rand.nextDouble() * 0.7D + 0.15D;
        double var5 = this.worldObj.rand.nextDouble() * 0.7D + 0.15D;
        double var7 = this.worldObj.rand.nextDouble() * 0.7D + 0.15D;
        EntityXPOrb var9 = new EntityXPOrb(this.worldObj, (double)this.xCoord + var3, (double)this.yCoord + var5, (double)this.zCoord + var7, var1, var2);
        var9.motionX = (double)((float)this.worldObj.rand.nextGaussian() * 0.05F);
        var9.motionY = (double)((float)this.worldObj.rand.nextGaussian() * 0.05F + 0.2F);
        var9.motionZ = (double)((float)this.worldObj.rand.nextGaussian() * 0.05F);
        this.worldObj.spawnEntityInWorld(var9);
    }
}

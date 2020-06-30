package net.minecraft.src;

public class FCEntityRottenArrow extends EntityArrow implements FCIEntityPacketHandler
{
    private static final float m_fDamageMultiplier = 1.0F;
    private static final int m_iVehicleSpawnPacketType = 104;

    public FCEntityRottenArrow(World var1)
    {
        super(var1);
    }

    public FCEntityRottenArrow(World var1, double var2, double var4, double var6)
    {
        super(var1, var2, var4, var6);
    }

    public FCEntityRottenArrow(World var1, EntityLiving var2, float var3)
    {
        super(var1, var2, var3);
        this.canBePickedUp = 2;
    }

    public FCEntityRottenArrow(World var1, EntityLiving var2, EntityLiving var3, float var4, float var5)
    {
        super(var1, var2, var3, var4, var5);
    }

    protected float GetDamageMultiplier()
    {
        return 1.0F;
    }

    protected boolean AddArrowToPlayerInv(EntityPlayer var1)
    {
        return false;
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (!this.isDead && this.inGround)
        {
            for (int var1 = 0; var1 < 32; ++var1)
            {
                this.worldObj.spawnParticle("iconcrack_333", this.posX, this.posY, this.posZ, (double)((float)(Math.random() * 2.0D - 1.0D) * 0.4F), (double)((float)(Math.random() * 2.0D - 1.0D) * 0.4F), (double)((float)(Math.random() * 2.0D - 1.0D) * 0.4F));
            }

            this.setDead();
        }
    }

    public Item GetCorrespondingItem()
    {
        return FCBetterThanWolves.fcItemRottenArrow;
    }

    public boolean CanHopperCollect()
    {
        return false;
    }

    public Packet GetSpawnPacketForThisEntity()
    {
        return new Packet23VehicleSpawn(this, GetVehicleSpawnPacketType(), this.shootingEntity == null ? this.entityId : this.shootingEntity.entityId);
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
        return false;
    }

    public boolean ShouldServerTreatAsOversized()
    {
        return false;
    }

    public static int GetVehicleSpawnPacketType()
    {
        return 104;
    }
}

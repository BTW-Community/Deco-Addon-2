package net.minecraft.src;

public class FCEntityBroadheadArrow extends EntityArrow implements FCIEntityPacketHandler
{
    private static final float m_fBroadheadDamageMultiplier = 1.5F;
    private static final int m_iVehicleSpawnPacketType = 101;

    public FCEntityBroadheadArrow(World var1)
    {
        super(var1);
    }

    public FCEntityBroadheadArrow(World var1, double var2, double var4, double var6)
    {
        super(var1, var2, var4, var6);
    }

    public FCEntityBroadheadArrow(World var1, EntityLiving var2, float var3)
    {
        super(var1, var2, var3);
    }

    protected float GetDamageMultiplier()
    {
        return 1.5F;
    }

    public Item GetCorrespondingItem()
    {
        return FCBetterThanWolves.fcItemBroadheadArrow;
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
        return 101;
    }
}

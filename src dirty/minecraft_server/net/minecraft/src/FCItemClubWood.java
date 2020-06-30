package net.minecraft.src;

public class FCItemClubWood extends FCItemClub
{
    public static final int m_iWeaponDamageWood = 2;
    public static final int m_iDurabilityWood = 10;

    public FCItemClubWood(int var1)
    {
        super(var1, 10, 2, "fcItemClub");
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.SHAFT);
    }
}

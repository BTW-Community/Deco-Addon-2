package net.minecraft.src;

public enum FCEnumFlammability
{
    NONE(0, 0),
    MINIMAL(5, 5),
    MEDIUM(5, 20),
    HIGH(30, 60),
    EXTREME(60, 100),
    QUICKCATCHSLOWBURN(15, 100),
    MEDIUMCATCHQUICKBURN(30, 20);
    public static final FCEnumFlammability LOGS = MINIMAL;
    public static final FCEnumFlammability PLANKS = MEDIUM;
    public static final FCEnumFlammability LEAVES = HIGH;
    public static final FCEnumFlammability CLOTH = HIGH;
    public static final FCEnumFlammability CROPS = HIGH;
    public static final FCEnumFlammability GRASS = EXTREME;
    public static final FCEnumFlammability WICKER = EXTREME;
    public static final FCEnumFlammability EXPLOSIVES = QUICKCATCHSLOWBURN;
    public static final FCEnumFlammability VINES = QUICKCATCHSLOWBURN;
    public static final FCEnumFlammability BOOKSHELVES = MEDIUMCATCHQUICKBURN;
    public final int m_iChanceToEncourageFire;
    public final int m_iAbilityToCatchFire;

    private FCEnumFlammability(int var3, int var4)
    {
        this.m_iChanceToEncourageFire = var3;
        this.m_iAbilityToCatchFire = var4;
    }
}

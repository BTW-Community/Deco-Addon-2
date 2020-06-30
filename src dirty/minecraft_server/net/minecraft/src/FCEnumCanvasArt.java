package net.minecraft.src;

public enum FCEnumCanvasArt
{
    Icarus("Icarus", 64, 64, 0, 0),
    Dagon("Dagon", 64, 64, 64, 0),
    Pentacle("Pentacle", 64, 64, 128, 0),
    Dragon("Dragon", 64, 64, 192, 0),
    TreeOfLife("TreeOfLife", 64, 96, 0, 64),
    Magician("Magician", 48, 96, 64, 64),
    HangedMan("HangedMan", 48, 96, 112, 64),
    Death("Death", 48, 96, 160, 64),
    Fool("Fool", 48, 96, 208, 64),
    IsleOfDead("IsleOfDead", 96, 48, 0, 160);
    public static final int maxArtTitleLength = "SuperLongTestString".length();
    public final String m_sTitle;
    public final int m_iSizeX;
    public final int m_iSizeY;
    public final int m_iOffsetX;
    public final int m_iOffsetY;

    private FCEnumCanvasArt(String var3, int var4, int var5, int var6, int var7)
    {
        this.m_sTitle = var3;
        this.m_iSizeX = var4;
        this.m_iSizeY = var5;
        this.m_iOffsetX = var6;
        this.m_iOffsetY = var7;
    }
}

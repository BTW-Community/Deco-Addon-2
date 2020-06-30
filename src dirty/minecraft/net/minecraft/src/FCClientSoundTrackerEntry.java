package net.minecraft.src;

public class FCClientSoundTrackerEntry
{
    String m_sName;
    float m_fXPos;
    float m_fYPos;
    float m_fZPos;
    float m_fMaxRangeSq;

    public FCClientSoundTrackerEntry(String var1, float var2, float var3, float var4, float var5)
    {
        this.m_sName = var1;
        this.m_fXPos = var2;
        this.m_fYPos = var3;
        this.m_fZPos = var4;
        this.m_fMaxRangeSq = var5 * var5;
    }
}

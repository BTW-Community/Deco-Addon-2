package net.minecraft.src;

public class FCModelBlockFurnaceBrick extends FCModelBlock
{
    public static final float m_fBaseHeight = 0.375F;
    public static final float m_fSideWidth = 0.25F;
    public static final float m_fHalfSideWidth = 0.125F;
    public static final float m_fTopThickness = 0.25F;
    public static final float m_fHalfTopThickness = 0.125F;
    public static final float m_fMindTheGap = 0.001F;

    protected void InitModel()
    {
        this.AddBox(0.7509999871253967D, 0.7509999871253967D, 0.75D, 0.24899999797344208D, 0.37400001287460327D, 0.0D);
    }
}

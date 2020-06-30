package net.minecraft.src;

public class FCModelBlockSoulforge extends FCModelBlock
{
    public static final float m_fAnvilBaseHeight = 0.125F;
    public static final float m_fAnvilBaseWidth = 0.5F;
    public static final float m_fAnvilHalfBaseWidth = 0.25F;
    public static final float m_fAnvilShaftHeight = 0.4375F;
    public static final float m_fAnvilShaftWidth = 0.25F;
    public static final float m_fAnvilHalfShaftWidth = 0.125F;
    public static final float m_fAnvilTopHeight = 0.4375F;
    public static final float m_fAnvilTopWidth = 0.375F;
    public static final float m_fAnvilTopHalfWidth = 0.1875F;

    protected void InitModel()
    {
        this.AddBox(0.25D, 0.0D, 0.0D, 0.75D, 0.125D, 1.0D);
        this.AddBox(0.375D, 0.125D, 0.375D, 0.625D, 0.5625D, 0.625D);
        this.AddBox(0.3125D, 0.5625D, 0.3125D, 0.6875D, 1.0D, 0.6875D);
        this.AddBox(0.3125D, 0.75D, 0.25D, 0.6875D, 1.0D, 0.3125D);
        this.AddBox(0.3125D, 0.9375D, 0.0D, 0.6875D, 1.0D, 0.25D);
        this.AddBox(0.3125D, 0.75D, 0.6875D, 0.6875D, 1.0D, 0.75D);
        this.AddBox(0.375D, 0.8125D, 0.75D, 0.625D, 1.0D, 0.875D);
        this.AddBox(0.4375D, 0.875D, 0.875D, 0.5625D, 1.0D, 1.0D);
    }
}

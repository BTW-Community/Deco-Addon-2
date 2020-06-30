package net.minecraft.src;

public class FCModelBlockShovel extends FCModelBlock
{
    public static final float m_fSideThickness = 0.0625F;
    public static final float m_fSlopHeight = 0.125F;
    public static final float m_fBackSlopHeight = 0.25F;
    public static final float m_fSlopeCollisionHeight = 0.015625F;
    public static final float m_fSlopeMiddleMajorGap = 0.375F;
    public static final double m_dMindTheGap = 0.001D;
    public FCModelBlock m_rayTraceModel;
    public FCModelBlock m_collisionModel;

    protected void InitModel()
    {
        this.m_rayTraceModel = new FCModelBlock();
        this.m_collisionModel = new FCModelBlock();
        this.AddBox(0.0D, 0.0D, 0.5D, 0.0625D, 1.0D, 1.0D);
        this.AddBox(0.0D, 0.0D, 0.0D, 0.0625D, 0.5D, 0.5D);
        this.m_rayTraceModel.AddBox(0.0D, 0.0D, 0.5D, 0.0625D, 1.0D, 1.0D);
        this.m_rayTraceModel.AddBox(0.0D, 0.0D, 0.0D, 0.0625D, 0.5D, 0.5D);
        this.AddBox(0.9375D, 0.0D, 0.5D, 1.0D, 1.0D, 1.0D);
        this.AddBox(0.9375D, 0.0D, 0.0D, 1.0D, 0.5D, 0.5D);
        this.m_rayTraceModel.AddBox(0.9375D, 0.0D, 0.5D, 1.0D, 1.0D, 1.0D);
        this.m_rayTraceModel.AddBox(0.9375D, 0.0D, 0.0D, 1.0D, 0.5D, 0.5D);
        this.AddPrimitive((new FCUtilsPrimitiveQuad(Vec3.createVectorHelper(0.0625D, 0.0D, 0.0D), Vec3.createVectorHelper(0.0625D, 0.125D, 0.625D), Vec3.createVectorHelper(0.9375D, 0.125D, 0.625D), Vec3.createVectorHelper(0.9375D, 0.0D, 0.0D))).SetIconIndex(1).SetUVFractions(0.0625F, 0.0F, 0.9375F, 0.625F));
        this.AddPrimitive((new FCUtilsPrimitiveQuad(Vec3.createVectorHelper(0.9375D, 0.0D, 0.0D), Vec3.createVectorHelper(0.9375D, 0.0D, 0.75D), Vec3.createVectorHelper(0.0625D, 0.0D, 0.75D), Vec3.createVectorHelper(0.0625D, 0.0D, 0.0D))).SetIconIndex(2).SetUVFractions(0.0625F, 0.0F, 0.9375F, 0.75F));
        this.m_rayTraceModel.AddBox(0.0D, 0.0D, 0.0D, 1.0D, 0.015625D, 0.75D);
        this.AddPrimitive((new FCUtilsPrimitiveQuad(Vec3.createVectorHelper(0.9375D, 1.0D, 1.0D), Vec3.createVectorHelper(0.9375D, 0.375D, 0.875D), Vec3.createVectorHelper(0.0625D, 0.375D, 0.875D), Vec3.createVectorHelper(0.0625D, 1.0D, 1.0D))).SetIconIndex(1).SetUVFractions(0.0625F, 0.0F, 0.9375F, 0.625F));
        this.AddPrimitive((new FCUtilsPrimitiveQuad(Vec3.createVectorHelper(0.0625D, 1.0D, 1.0D), Vec3.createVectorHelper(0.0625D, 0.25D, 1.0D), Vec3.createVectorHelper(0.9375D, 0.25D, 1.0D), Vec3.createVectorHelper(0.9375D, 1.0D, 1.0D))).SetIconIndex(2).SetUVFractions(0.0625F, 0.0F, 0.9375F, 0.75F));
        this.m_rayTraceModel.AddBox(0.0D, 0.25D, 0.984375D, 1.0D, 1.0D, 1.0D);
        this.AddPrimitive((new FCUtilsPrimitiveQuad(Vec3.createVectorHelper(0.9375D, 0.375D, 0.875D), Vec3.createVectorHelper(0.9375D, 0.125D, 0.625D), Vec3.createVectorHelper(0.0625D, 0.125D, 0.625D), Vec3.createVectorHelper(0.0625D, 0.375D, 0.875D))).SetIconIndex(3).SetUVFractions(0.0625F, 0.0F, 0.9375F, 0.375F));
        this.AddPrimitive((new FCUtilsPrimitiveQuad(Vec3.createVectorHelper(0.0625D, 0.25D, 1.0D), Vec3.createVectorHelper(0.0625D, 0.0D, 0.75D), Vec3.createVectorHelper(0.9375D, 0.0D, 0.75D), Vec3.createVectorHelper(0.9375D, 0.25D, 1.0D))).SetIconIndex(3).SetUVFractions(0.0625F, 0.625F, 0.9375F, 1.0F));
        this.m_rayTraceModel.AddPrimitive((new FCUtilsPrimitiveQuad(Vec3.createVectorHelper(0.0625D, 0.25D, 1.0D), Vec3.createVectorHelper(0.0625D, 0.0D, 0.75D), Vec3.createVectorHelper(0.9375D, 0.0D, 0.75D), Vec3.createVectorHelper(0.9375D, 0.25D, 1.0D))).SetIconIndex(3).SetUVFractions(0.0625F, 0.625F, 0.9375F, 1.0F));
        this.m_collisionModel.AddBox(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
        this.m_collisionModel.AddBox(0.0D, 0.5D, 0.5D, 1.0D, 1.0D, 1.0D);
    }
}

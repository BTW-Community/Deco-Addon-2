package net.minecraft.src;

public class FCBlockDetectorLogicGlowing extends FCBlockDetectorLogic
{
    private static final float m_fLitFaceThickness = 0.01F;

    public FCBlockDetectorLogicGlowing(int var1)
    {
        super(var1);
        this.setLightValue(1.0F);
        this.setUnlocalizedName("fcBlockDetectorLogicGlowing");
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    protected void RemoveSelf(World var1, int var2, int var3, int var4)
    {
        var1.setBlock(var2, var3, var4, 0, 0, 2);
    }
}

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

    /**
     * Goes straight to getLightBrightnessForSkyBlocks for Blocks, does some fancy computing for Fluids
     */
    public int getMixedBrightnessForBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 983280;
    }

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return 1;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.m_currentBlockRenderer.ShouldSideBeRenderedBasedOnCurrentBounds(var2, var3, var4, var5);
    }

    public void SetRenderBoundsToRenderLitFace(RenderBlocks var1, int var2)
    {
        switch (var2)
        {
            case 0:
                var1.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.009999999776482582D, 1.0D);
                break;

            case 1:
                var1.setRenderBounds(0.0D, 0.9900000095367432D, 0.0D, 1.0D, 1.0D, 1.0D);
                break;

            case 2:
                var1.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.009999999776482582D);
                break;

            case 3:
                var1.setRenderBounds(0.0D, 0.0D, 0.9900000095367432D, 1.0D, 1.0D, 1.0D);
                break;

            case 4:
                var1.setRenderBounds(0.0D, 0.0D, 0.0D, 0.009999999776482582D, 1.0D, 1.0D);
                break;

            default:
                var1.setRenderBounds(0.9900000095367432D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        }
    }

    public boolean ShouldVisiblyProjectToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);
        return var5 == 0 ? FCUtilsWorld.DoesBlockHaveSolidTopSurface(var1, var6.i, var6.j, var6.k) : var1.isBlockNormalCube(var6.i, var6.j, var6.k);
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;

        for (int var6 = 0; var6 <= 5; ++var6)
        {
            if (this.ShouldVisiblyProjectToFacing(var5, var2, var3, var4, var6))
            {
                this.SetRenderBoundsToRenderLitFace(var1, var6);
                FCClientUtilsRender.RenderBlockFullBrightWithTexture(var1, var5, var2, var3, var4, this.blockIcon);
            }
        }

        return true;
    }
}

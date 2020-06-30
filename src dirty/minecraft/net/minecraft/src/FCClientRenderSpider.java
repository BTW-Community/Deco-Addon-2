package net.minecraft.src;

public class FCClientRenderSpider extends RenderSpider
{
    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving var1, int var2, float var3)
    {
        FCEntitySpider var4 = (FCEntitySpider)var1;
        return !var4.DoEyesGlow() ? -1 : this.setSpiderEyeBrightness(var4, var2, var3);
    }
}

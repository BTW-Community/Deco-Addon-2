package net.minecraft.src;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TileEntitySkullRenderer extends TileEntitySpecialRenderer
{
    public static TileEntitySkullRenderer skullRenderer;
    private ModelSkeletonHead field_82396_c = new ModelSkeletonHead(0, 0, 64, 32);
    private ModelSkeletonHead field_82395_d = new ModelSkeletonHead(0, 0, 64, 64);
    private ModelSkeletonHead m_InfusedModel = new ModelSkeletonHead(0, 0, 32, 16);

    /**
     * Render a skull tile entity.
     */
    public void renderTileEntitySkullAt(TileEntitySkull par1TileEntitySkull, double par2, double par4, double par6, float par8)
    {
        this.func_82393_a((float)par2, (float)par4, (float)par6, par1TileEntitySkull.getBlockMetadata() & 7, (float)(par1TileEntitySkull.func_82119_b() * 360) / 16.0F, par1TileEntitySkull.getSkullType(), par1TileEntitySkull.getExtraType());
    }

    /**
     * Associate a TileEntityRenderer with this TileEntitySpecialRenderer
     */
    public void setTileEntityRenderer(TileEntityRenderer par1TileEntityRenderer)
    {
        super.setTileEntityRenderer(par1TileEntityRenderer);
        skullRenderer = this;
    }

    public void func_82393_a(float par1, float par2, float par3, int par4, float par5, int par6, String par7Str)
    {
        ModelSkeletonHead var8 = this.field_82396_c;

        switch (par6)
        {
            case 0:
            default:
                this.bindTextureByName("/mob/skeleton.png");
                break;

            case 1:
                this.bindTextureByName("/mob/skeleton_wither.png");
                break;

            case 2:
                this.bindTextureByName("/mob/zombie.png");
                var8 = this.field_82395_d;
                break;

            case 3:
                if (par7Str != null && par7Str.length() > 0)
                {
                    String var9 = FCBetterThanWolves.fcPlayerSkinURL + StringUtils.stripControlCodes(par7Str) + ".png";

                    if (!skullRenderer.tileEntityRenderer.renderEngine.hasImageData(var9))
                    {
                        skullRenderer.tileEntityRenderer.renderEngine.obtainImageData(var9, new ImageBufferDownload());
                    }

                    this.bindTextureByURL(var9, "/mob/char.png");
                }
                else
                {
                    this.bindTextureByName("/mob/char.png");
                }

                break;

            case 4:
                this.bindTextureByName("/mob/creeper.png");
                break;

            case 5:
                this.bindTextureByName("/btwmodtex/fcInfusedSkull.png");
                var8 = this.m_InfusedModel;
        }

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);

        if (par4 != 1)
        {
            switch (par4)
            {
                case 2:
                    GL11.glTranslatef(par1 + 0.5F, par2 + 0.25F, par3 + 0.74F);
                    break;

                case 3:
                    GL11.glTranslatef(par1 + 0.5F, par2 + 0.25F, par3 + 0.26F);
                    par5 = 180.0F;
                    break;

                case 4:
                    GL11.glTranslatef(par1 + 0.74F, par2 + 0.25F, par3 + 0.5F);
                    par5 = 270.0F;
                    break;

                case 5:
                default:
                    GL11.glTranslatef(par1 + 0.26F, par2 + 0.25F, par3 + 0.5F);
                    par5 = 90.0F;
            }
        }
        else
        {
            GL11.glTranslatef(par1 + 0.5F, par2, par3 + 0.5F);
        }

        float var10 = 0.0625F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        var8.render((Entity)null, 0.0F, 0.0F, 0.0F, par5, 0.0F, var10);

        if (par6 == 5)
        {
            this.RenderInfusedEyes(var8, par5);
        }

        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntitySkullAt((TileEntitySkull)par1TileEntity, par2, par4, par6, par8);
    }

    private void RenderInfusedEyes(ModelSkeletonHead var1, float var2)
    {
        this.bindTextureByName("/btwmodtex/fcInfusedSkullEyes.png");
        float var3 = 1.0F;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDepthMask(true);
        char var4 = 61680;
        int var5 = var4 % 65536;
        int var6 = var4 / 65536;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var5 / 1.0F, (float)var6 / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, var3);
        var1.render((Entity)null, 0.0F, 0.0F, 0.0F, var2, 0.0F, 0.0625F);
    }
}

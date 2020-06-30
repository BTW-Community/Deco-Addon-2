package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class FCClientRenderBlockLiftedByPlatform extends Render
{
    public FCClientRenderBlockLiftedByPlatform()
    {
        this.shadowSize = 0.0F;
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        var4 += 0.07500000298023224D;
        World var10 = var1.worldObj;
        FCEntityBlockLiftedByPlatform var11 = (FCEntityBlockLiftedByPlatform)var1;
        int var12 = var11.GetBlockID();
        int var13 = var11.GetBlockMetadata();
        Block var14 = Block.blocksList[var12];

        if (var14 != null)
        {
            if (var14 instanceof BlockRailBase)
            {
                BlockRailBase var15 = (BlockRailBase)var14;
                this.RenderBlockMinecartTrack(var10, var11, var15, var13, var2, var4, var6);
            }
            else if (var14 instanceof BlockRedstoneWire)
            {
                BlockRedstoneWire var16 = (BlockRedstoneWire)var14;
                this.RenderBlockRedstoneWire(var10, var11, var16, var13, var2, var4, var6);
            }
        }
    }

    public boolean RenderBlockRedstoneWire(World var1, FCEntityBlockLiftedByPlatform var2, BlockRedstoneWire var3, int var4, double var5, double var7, double var9)
    {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        this.loadTexture("/terrain.png");
        Tessellator var11 = Tessellator.instance;
        var11.startDrawingQuads();
        int var12 = MathHelper.floor_double(var2.posX);
        int var13 = MathHelper.floor_double(var2.posY);
        int var14 = MathHelper.floor_double(var2.posZ);
        float var15 = var3.getBlockBrightness(var1, var12, var13, var14);
        float var16 = var3.getBlockBrightness(var1, var12, var13 - 1, var14);

        if (var16 > var15)
        {
            var15 = var16;
        }

        var11.setColorOpaque_F(0.4F * var15, 0.0F, 0.0F);
        Icon var17 = var3.getIcon(0, var4);
        double var18 = (double)var17.getMinU();
        double var20 = (double)var17.getMaxU();
        double var22 = (double)var17.getMinV();
        double var24 = (double)var17.getMaxV();
        float var26 = 0.015625F;
        float var27 = (float)(var5 + 0.5D);
        float var28 = (float)(var5 + 0.5D);
        float var29 = (float)(var5 - 0.5D);
        float var30 = (float)(var5 - 0.5D);
        float var31 = (float)(var9 - 0.5D);
        float var32 = (float)(var9 + 0.5D);
        float var33 = (float)(var9 + 0.5D);
        float var34 = (float)(var9 - 0.5D);
        float var35 = (float)(var7 - 0.5D) + var26;
        float var36 = (float)(var7 - 0.5D) + var26;
        float var37 = (float)(var7 - 0.5D) + var26;
        float var38 = (float)(var7 - 0.5D) + var26;
        var11.addVertexWithUV((double)var27, (double)var35, (double)var31, var20, var22);
        var11.addVertexWithUV((double)var28, (double)var36, (double)var32, var20, var24);
        var11.addVertexWithUV((double)var29, (double)var37, (double)var33, var18, var24);
        var11.addVertexWithUV((double)var30, (double)var38, (double)var34, var18, var22);
        var11.addVertexWithUV((double)var30, (double)var38, (double)var34, var18, var22);
        var11.addVertexWithUV((double)var29, (double)var37, (double)var33, var18, var24);
        var11.addVertexWithUV((double)var28, (double)var36, (double)var32, var20, var24);
        var11.addVertexWithUV((double)var27, (double)var35, (double)var31, var20, var22);
        var11.draw();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
        return true;
    }

    public boolean RenderBlockMinecartTrack(World var1, FCEntityBlockLiftedByPlatform var2, BlockRailBase var3, int var4, double var5, double var7, double var9)
    {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        this.loadTexture("/terrain.png");
        Tessellator var11 = Tessellator.instance;
        var11.startDrawingQuads();
        int var12 = MathHelper.floor_double(var2.posX);
        int var13 = MathHelper.floor_double(var2.posY);
        int var14 = MathHelper.floor_double(var2.posZ);
        float var15 = var3.getBlockBrightness(var1, var12, var13, var14);
        float var16 = var3.getBlockBrightness(var1, var12, var13 - 1, var14);

        if (var16 > var15)
        {
            var15 = var16;
        }

        var11.setColorOpaque_F(var15, var15, var15);
        Icon var17 = var3.getIcon(0, var4);

        if (var3.isPowered())
        {
            var4 &= 7;
        }

        double var18 = (double)var17.getMinU();
        double var20 = (double)var17.getMaxU();
        double var22 = (double)var17.getMinV();
        double var24 = (double)var17.getMaxV();
        float var26 = 0.0625F;
        float var27 = (float)(var5 + 0.5D);
        float var28 = (float)(var5 + 0.5D);
        float var29 = (float)(var5 - 0.5D);
        float var30 = (float)(var5 - 0.5D);
        float var31 = (float)(var9 - 0.5D);
        float var32 = (float)(var9 + 0.5D);
        float var33 = (float)(var9 + 0.5D);
        float var34 = (float)(var9 - 0.5D);
        float var35 = (float)(var7 - 0.5D) + var26;
        float var36 = (float)(var7 - 0.5D) + var26;
        float var37 = (float)(var7 - 0.5D) + var26;
        float var38 = (float)(var7 - 0.5D) + var26;

        if (var4 != 1 && var4 != 2 && var4 != 3 && var4 != 7)
        {
            if (var4 == 8)
            {
                var27 = var28 = (float)(var5 - 0.5D);
                var29 = var30 = (float)(var5 + 0.5D);
                var31 = var34 = (float)(var9 + 0.5D);
                var32 = var33 = (float)(var9 - 0.5D);
            }
            else if (var4 == 9)
            {
                var27 = var30 = (float)(var5 - 0.5D);
                var28 = var29 = (float)(var5 + 0.5D);
                var31 = var32 = (float)(var9 - 0.5D);
                var33 = var34 = (float)(var9 + 0.5D);
            }
        }
        else
        {
            var27 = var30 = (float)(var5 + 0.5D);
            var28 = var29 = (float)(var5 - 0.5D);
            var31 = var32 = (float)(var9 + 0.5D);
            var33 = var34 = (float)(var9 - 0.5D);
        }

        if (var4 != 2 && var4 != 4)
        {
            if (var4 == 3 || var4 == 5)
            {
                ++var36;
                ++var37;
            }
        }
        else
        {
            ++var35;
            ++var38;
        }

        var11.addVertexWithUV((double)var27, (double)var35, (double)var31, var20, var22);
        var11.addVertexWithUV((double)var28, (double)var36, (double)var32, var20, var24);
        var11.addVertexWithUV((double)var29, (double)var37, (double)var33, var18, var24);
        var11.addVertexWithUV((double)var30, (double)var38, (double)var34, var18, var22);
        var11.addVertexWithUV((double)var30, (double)var38, (double)var34, var18, var22);
        var11.addVertexWithUV((double)var29, (double)var37, (double)var33, var18, var24);
        var11.addVertexWithUV((double)var28, (double)var36, (double)var32, var20, var24);
        var11.addVertexWithUV((double)var27, (double)var35, (double)var31, var20, var22);
        var11.draw();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
        return true;
    }
}

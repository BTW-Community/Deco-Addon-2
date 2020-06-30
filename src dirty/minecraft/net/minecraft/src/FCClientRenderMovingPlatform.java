package net.minecraft.src;

import java.util.List;
import org.lwjgl.opengl.GL11;

public class FCClientRenderMovingPlatform extends Render
{
    private RenderBlocks localRenderBlocks = new RenderBlocks();

    public FCClientRenderMovingPlatform()
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
        World var10 = var1.worldObj;
        this.localRenderBlocks.blockAccess = var10;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)var2, (float)var4, (float)var6);
        GL11.glDisable(GL11.GL_LIGHTING);
        int var11 = MathHelper.floor_double(var1.posX);
        int var12 = MathHelper.floor_double(var1.posY);
        int var13 = MathHelper.floor_double(var1.posZ);
        this.loadTexture("/terrain.png");
        Block var14 = FCBetterThanWolves.fcPlatform;
        List var15 = var1.worldObj.getEntitiesWithinAABB(FCEntityMovingPlatform.class, AxisAlignedBB.getAABBPool().getAABB(var1.posX - 1.0D, var1.posY - 0.10000000149011612D, var1.posZ - 0.10000000149011612D, var1.posX - 0.8999999761581421D, var1.posY + 0.10000000149011612D, var1.posZ + 0.10000000149011612D));

        if (var15 == null || var15.size() <= 0)
        {
            this.localRenderBlocks.setRenderBounds(9.999999747378752E-5D, 0.0625D, 9.999999747378752E-5D, 0.0625D, 0.9375D, 0.9998999834060669D);
            FCClientUtilsRender.RenderMovingBlock(this.localRenderBlocks, var14, var10, var11, var12, var13);
        }

        var15 = var1.worldObj.getEntitiesWithinAABB(FCEntityMovingPlatform.class, AxisAlignedBB.getAABBPool().getAABB(var1.posX - 0.10000000149011612D, var1.posY - 0.10000000149011612D, var1.posZ + 0.8999999761581421D, var1.posX + 0.10000000149011612D, var1.posY + 0.10000000149011612D, var1.posZ + 1.0D));

        if (var15 == null || var15.size() <= 0)
        {
            this.localRenderBlocks.setRenderBounds(0.0D, 0.0625D, 0.9375D, 1.0D, 0.9375D, 1.0D);
            FCClientUtilsRender.RenderMovingBlock(this.localRenderBlocks, var14, var10, var11, var12, var13);
        }

        var15 = var1.worldObj.getEntitiesWithinAABB(FCEntityMovingPlatform.class, AxisAlignedBB.getAABBPool().getAABB(var1.posX + 0.8999999761581421D, var1.posY - 0.10000000149011612D, var1.posZ - 0.10000000149011612D, var1.posX + 1.0D, var1.posY + 0.10000000149011612D, var1.posZ + 0.10000000149011612D));

        if (var15 == null || var15.size() <= 0)
        {
            this.localRenderBlocks.setRenderBounds(0.9375D, 0.0625D, 9.999999747378752E-5D, 0.9998999834060669D, 0.9375D, 0.9998999834060669D);
            FCClientUtilsRender.RenderMovingBlock(this.localRenderBlocks, var14, var10, var11, var12, var13);
        }

        var15 = var1.worldObj.getEntitiesWithinAABB(FCEntityMovingPlatform.class, AxisAlignedBB.getAABBPool().getAABB(var1.posX - 0.10000000149011612D, var1.posY - 0.10000000149011612D, var1.posZ - 1.0D, var1.posX + 0.10000000149011612D, var1.posY + 0.10000000149011612D, var1.posZ - 0.8999999761581421D));

        if (var15 == null || var15.size() <= 0)
        {
            this.localRenderBlocks.setRenderBounds(0.0D, 0.0625D, 0.0D, 1.0D, 0.9375D, 0.0625D);
            FCClientUtilsRender.RenderMovingBlock(this.localRenderBlocks, var14, var10, var11, var12, var13);
        }

        this.localRenderBlocks.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
        FCClientUtilsRender.RenderMovingBlock(this.localRenderBlocks, var14, var10, var11, var12, var13);
        this.localRenderBlocks.setRenderBounds(0.0D, 0.9375D, 0.0D, 1.0D, 1.0D, 1.0D);
        FCClientUtilsRender.RenderMovingBlock(this.localRenderBlocks, var14, var10, var11, var12, var13);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}

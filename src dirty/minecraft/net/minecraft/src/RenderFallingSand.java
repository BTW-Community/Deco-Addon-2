package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class RenderFallingSand extends Render
{
    private RenderBlocks sandRenderBlocks = new RenderBlocks();

    public RenderFallingSand()
    {
        this.shadowSize = 0.5F;
    }

    /**
     * The actual render method that is used in doRender
     */
    public void doRenderFallingSand(EntityFallingSand par1EntityFallingSand, double par2, double par4, double par6, float par8, float par9)
    {
        World var10 = par1EntityFallingSand.getWorld();
        Block var11 = Block.blocksList[par1EntityFallingSand.blockID];

        if (this.ShouldRender(var10, par1EntityFallingSand))
        {
            this.shadowSize = 0.5F;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)par2, (float)par4, (float)par6);
            this.loadTexture("/terrain.png");
            GL11.glDisable(GL11.GL_LIGHTING);
            Tessellator var12;

            if (var11 instanceof BlockAnvil && var11.getRenderType() == 35)
            {
                this.sandRenderBlocks.blockAccess = var10;
                var12 = Tessellator.instance;
                var12.startDrawingQuads();
                var12.setTranslation((double)((float)(-MathHelper.floor_double(par1EntityFallingSand.posX)) - 0.5F), (double)((float)(-MathHelper.floor_double(par1EntityFallingSand.posY)) - 0.5F), (double)((float)(-MathHelper.floor_double(par1EntityFallingSand.posZ)) - 0.5F));
                this.sandRenderBlocks.renderBlockAnvilMetadata((BlockAnvil)var11, MathHelper.floor_double(par1EntityFallingSand.posX), MathHelper.floor_double(par1EntityFallingSand.posY), MathHelper.floor_double(par1EntityFallingSand.posZ), par1EntityFallingSand.metadata);
                var12.setTranslation(0.0D, 0.0D, 0.0D);
                var12.draw();
            }
            else if (var11.getRenderType() == 27)
            {
                this.sandRenderBlocks.blockAccess = var10;
                var12 = Tessellator.instance;
                var12.startDrawingQuads();
                var12.setTranslation((double)((float)(-MathHelper.floor_double(par1EntityFallingSand.posX)) - 0.5F), (double)((float)(-MathHelper.floor_double(par1EntityFallingSand.posY)) - 0.5F), (double)((float)(-MathHelper.floor_double(par1EntityFallingSand.posZ)) - 0.5F));
                this.sandRenderBlocks.renderBlockDragonEgg((BlockDragonEgg)var11, MathHelper.floor_double(par1EntityFallingSand.posX), MathHelper.floor_double(par1EntityFallingSand.posY), MathHelper.floor_double(par1EntityFallingSand.posZ));
                var12.setTranslation(0.0D, 0.0D, 0.0D);
                var12.draw();
            }
            else if (var11 != null)
            {
                this.sandRenderBlocks.blockAccess = var10;
                Tessellator.instance.startDrawingQuads();
                Tessellator.instance.setTranslation((double)(-MathHelper.floor_double(par1EntityFallingSand.posX)) - 0.5D, (double)(-MathHelper.floor_double(par1EntityFallingSand.posY)) - 0.5D, (double)(-MathHelper.floor_double(par1EntityFallingSand.posZ)) - 0.5D);
                var11.m_currentBlockRenderer = this.sandRenderBlocks;
                var11.RenderFallingBlock(this.sandRenderBlocks, MathHelper.floor_double(par1EntityFallingSand.posX), MathHelper.floor_double(par1EntityFallingSand.posY), MathHelper.floor_double(par1EntityFallingSand.posZ), par1EntityFallingSand.metadata);
                Tessellator.instance.setTranslation(0.0D, 0.0D, 0.0D);
                Tessellator.instance.draw();
            }

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
        else
        {
            this.shadowSize = 0.0F;
        }
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.doRenderFallingSand((EntityFallingSand)par1Entity, par2, par4, par6, par8, par9);
    }

    private boolean ShouldRender(World var1, EntityFallingSand var2)
    {
        Block var3 = Block.blocksList[var2.blockID];
        return var3 != null ? var3.ShouldRenderWhileFalling(var1, var2) : false;
    }
}

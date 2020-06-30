package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class FCClientUtilsRender
{
    public static void RenderStandardBlockWithTexture(RenderBlocks var0, Block var1, int var2, int var3, int var4, Icon var5)
    {
        boolean var6 = var0.hasOverrideBlockTexture();

        if (!var6)
        {
            var0.setOverrideBlockTexture(var5);
        }

        var0.renderStandardBlock(var1, var2, var3, var4);

        if (!var6)
        {
            var0.clearOverrideBlockTexture();
        }
    }

    public static void RenderBlockWithBoundsAndTextureRotation(RenderBlocks var0, Block var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, float var9, float var10, float var11)
    {
        SetRenderBoundsToBlockFacing(var0, var5, var6, var7, var8, var9, var10, var11);
        RenderBlockWithTextureRotation(var0, var1, var2, var3, var4, var5);
    }

    public static void SetRenderBoundsWithAxisAlignment(RenderBlocks var0, float var1, float var2, float var3, float var4, float var5, float var6, boolean var7)
    {
        if (!var7)
        {
            var0.setRenderBounds((double)var1, (double)var2, (double)var3, (double)var4, (double)var5, (double)var6);
        }
        else
        {
            var0.setRenderBounds((double)var3, (double)var2, (double)var1, (double)var6, (double)var5, (double)var4);
        }
    }

    public static void SetRenderBoundsToBlockFacing(RenderBlocks var0, int var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        float var8 = var2;
        float var9 = var3;
        float var10 = var4;
        float var11 = var5;
        float var12 = var6;
        float var13 = var7;

        switch (var1)
        {
            case 0:
                var9 = 1.0F - var6;
                var12 = 1.0F - var3;

            case 1:
            default:
                break;

            case 2:
                var10 = 1.0F - var6;
                var13 = 1.0F - var3;
                var9 = var4;
                var12 = var7;
                break;

            case 3:
                var10 = var3;
                var13 = var6;
                var9 = 1.0F - var7;
                var12 = 1.0F - var4;
                break;

            case 4:
                var8 = 1.0F - var6;
                var11 = 1.0F - var3;
                var9 = var2;
                var12 = var5;
                break;

            case 5:
                var8 = var3;
                var11 = var6;
                var9 = 1.0F - var5;
                var12 = 1.0F - var2;
        }

        var0.setRenderBounds((double)var8, (double)var9, (double)var10, (double)var11, (double)var12, (double)var13);
    }

    public static void RenderBlockWithTextureRotation(RenderBlocks var0, Block var1, int var2, int var3, int var4, int var5)
    {
        SetTextureRotationBasedOnBlockFacing(var0, var5);
        var0.renderStandardBlock(var1, var2, var3, var4);
        var0.ClearUvRotation();
    }

    public static void SetTextureRotationBasedOnBlockFacing(RenderBlocks var0, int var1)
    {
        switch (var1)
        {
            case 0:
                var0.SetUvRotateEast(3);
                var0.SetUvRotateWest(3);
                var0.SetUvRotateSouth(3);
                var0.SetUvRotateNorth(3);

            case 1:
            default:
                break;

            case 2:
                var0.SetUvRotateSouth(1);
                var0.SetUvRotateNorth(2);
                var0.SetUvRotateEast(3);
                var0.SetUvRotateWest(3);
                break;

            case 3:
                var0.SetUvRotateSouth(2);
                var0.SetUvRotateNorth(1);
                var0.SetUvRotateTop(3);
                var0.SetUvRotateBottom(3);
                break;

            case 4:
                var0.SetUvRotateEast(1);
                var0.SetUvRotateWest(2);
                var0.SetUvRotateTop(2);
                var0.SetUvRotateBottom(1);
                var0.SetUvRotateNorth(2);
                var0.SetUvRotateSouth(1);
                break;

            case 5:
                var0.SetUvRotateEast(2);
                var0.SetUvRotateWest(1);
                var0.SetUvRotateTop(1);
                var0.SetUvRotateBottom(2);
                var0.SetUvRotateSouth(1);
                var0.SetUvRotateNorth(2);
        }
    }

    public static void RenderBlockInteriorWithBoundsAndTextureRotation(RenderBlocks var0, Block var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, float var9, float var10, float var11)
    {
        SetRenderBoundsToBlockFacing(var0, var5, var6, var7, var8, var9, var10, var11);
        SetTextureRotationBasedOnBlockFacing(var0, var5);
        RenderBlockWithTextureRotation(var0, var1, var2, var3, var4, var5);
        var0.ClearUvRotation();
    }

    public static void RenderMovingBlockWithTexture(RenderBlocks var0, Block var1, World var2, int var3, int var4, int var5, Icon var6)
    {
        float var7 = 0.5F;
        float var8 = 1.0F;
        float var9 = 0.8F;
        float var10 = 0.6F;
        Tessellator var11 = Tessellator.instance;
        var11.startDrawingQuads();
        var11.setBrightness(var1.getMixedBrightnessForBlock(var2, var3, var4, var5));
        float var12 = 1.0F;
        float var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var7 * var13, var7 * var13, var7 * var13);
        var0.renderFaceYNeg(var1, -0.5D, -0.5D, -0.5D, var6);
        var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var8 * var13, var8 * var13, var8 * var13);
        var0.renderFaceYPos(var1, -0.5D, -0.5D, -0.5D, var6);
        var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var9 * var13, var9 * var13, var9 * var13);
        var0.renderFaceZNeg(var1, -0.5D, -0.5D, -0.5D, var6);
        var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var9 * var13, var9 * var13, var9 * var13);
        var0.renderFaceZPos(var1, -0.5D, -0.5D, -0.5D, var6);
        var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var10 * var13, var10 * var13, var10 * var13);
        var0.renderFaceXNeg(var1, -0.5D, -0.5D, -0.5D, var6);
        var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var10 * var13, var10 * var13, var10 * var13);
        var0.renderFaceXPos(var1, -0.5D, -0.5D, -0.5D, var6);
        var11.draw();
    }

    public static void RenderMovingBlock(RenderBlocks var0, Block var1, World var2, int var3, int var4, int var5)
    {
        RenderMovingBlockWithMetadata(var0, var1, var2, var3, var4, var5, 0);
    }

    public static void RenderMovingBlockWithMetadata(RenderBlocks var0, Block var1, IBlockAccess var2, int var3, int var4, int var5, int var6)
    {
        float var7 = 0.5F;
        float var8 = 1.0F;
        float var9 = 0.8F;
        float var10 = 0.6F;
        Tessellator var11 = Tessellator.instance;
        var11.startDrawingQuads();
        var11.setBrightness(var1.getMixedBrightnessForBlock(var2, var3, var4, var5));
        float var12 = 1.0F;
        float var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var7 * var13, var7 * var13, var7 * var13);
        var0.renderFaceYNeg(var1, -0.5D, -0.5D, -0.5D, var1.getIcon(0, var6));
        var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var8 * var13, var8 * var13, var8 * var13);
        var0.renderFaceYPos(var1, -0.5D, -0.5D, -0.5D, var1.getIcon(1, var6));
        var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var9 * var13, var9 * var13, var9 * var13);
        var0.renderFaceZNeg(var1, -0.5D, -0.5D, -0.5D, var1.getIcon(2, var6));
        var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var9 * var13, var9 * var13, var9 * var13);
        var0.renderFaceZPos(var1, -0.5D, -0.5D, -0.5D, var1.getIcon(3, var6));
        var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var10 * var13, var10 * var13, var10 * var13);
        var0.renderFaceXNeg(var1, -0.5D, -0.5D, -0.5D, var1.getIcon(4, var6));
        var13 = 1.0F;

        if (var13 < var12)
        {
            var13 = var12;
        }

        var11.setColorOpaque_F(var10 * var13, var10 * var13, var10 * var13);
        var0.renderFaceXPos(var1, -0.5D, -0.5D, -0.5D, var1.getIcon(5, var6));
        var11.draw();
    }

    public static void RenderInvBlockWithTexture(RenderBlocks var0, Block var1, float var2, float var3, float var4, Icon var5)
    {
        Tessellator var6 = Tessellator.instance;
        GL11.glTranslatef(var2, var3, var4);
        var6.startDrawingQuads();
        var6.setNormal(0.0F, -1.0F, 0.0F);
        var0.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, var5);
        var6.draw();
        var6.startDrawingQuads();
        var6.setNormal(0.0F, 1.0F, 0.0F);
        var0.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, var5);
        var6.draw();
        var6.startDrawingQuads();
        var6.setNormal(0.0F, 0.0F, -1.0F);
        var0.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, var5);
        var6.draw();
        var6.startDrawingQuads();
        var6.setNormal(0.0F, 0.0F, 1.0F);
        var0.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, var5);
        var6.draw();
        var6.startDrawingQuads();
        var6.setNormal(-1.0F, 0.0F, 0.0F);
        var0.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, var5);
        var6.draw();
        var6.startDrawingQuads();
        var6.setNormal(1.0F, 0.0F, 0.0F);
        var0.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, var5);
        var6.draw();
        GL11.glTranslatef(-var2, -var3, -var4);
    }

    public static void RenderInvBlockWithMetadata(RenderBlocks var0, Block var1, float var2, float var3, float var4, int var5)
    {
        Tessellator var6 = Tessellator.instance;
        GL11.glTranslatef(var2, var3, var4);
        var6.startDrawingQuads();
        var6.setNormal(0.0F, -1.0F, 0.0F);
        var0.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(0, var5));
        var6.draw();
        var6.startDrawingQuads();
        var6.setNormal(0.0F, 1.0F, 0.0F);
        var0.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(1, var5));
        var6.draw();
        var6.startDrawingQuads();
        var6.setNormal(0.0F, 0.0F, -1.0F);
        var0.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(2, var5));
        var6.draw();
        var6.startDrawingQuads();
        var6.setNormal(0.0F, 0.0F, 1.0F);
        var0.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(3, var5));
        var6.draw();
        var6.startDrawingQuads();
        var6.setNormal(-1.0F, 0.0F, 0.0F);
        var0.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(4, var5));
        var6.draw();
        var6.startDrawingQuads();
        var6.setNormal(1.0F, 0.0F, 0.0F);
        var0.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, var1.getIcon(5, var5));
        var6.draw();
        GL11.glTranslatef(-var2, -var3, -var4);
    }

    public static boolean RenderBlockFullBrightWithTexture(RenderBlocks var0, IBlockAccess var1, int var2, int var3, int var4, Icon var5)
    {
        Tessellator var6 = Tessellator.instance;
        var6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var6.setBrightness(var1.getLightBrightnessForSkyBlocks(var2, var3, var4, 15));
        var0.renderFaceYNeg((Block)null, (double)var2, (double)var3, (double)var4, var5);
        var6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var6.setBrightness(var1.getLightBrightnessForSkyBlocks(var2, var3, var4, 15));
        var0.renderFaceYPos((Block)null, (double)var2, (double)var3, (double)var4, var5);
        var6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var6.setBrightness(var1.getLightBrightnessForSkyBlocks(var2, var3, var4, 15));
        var0.renderFaceZNeg((Block)null, (double)var2, (double)var3, (double)var4, var5);
        var6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var6.setBrightness(var1.getLightBrightnessForSkyBlocks(var2, var3, var4, 15));
        var0.renderFaceZPos((Block)null, (double)var2, (double)var3, (double)var4, var5);
        var6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var6.setBrightness(var1.getLightBrightnessForSkyBlocks(var2, var3, var4, 15));
        var0.renderFaceXNeg((Block)null, (double)var2, (double)var3, (double)var4, var5);
        var6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var6.setBrightness(var1.getLightBrightnessForSkyBlocks(var2, var3, var4, 15));
        var0.renderFaceXPos((Block)null, (double)var2, (double)var3, (double)var4, var5);
        return true;
    }

    public static void RenderInvBlockFullBrightWithTexture(RenderBlocks var0, Block var1, float var2, float var3, float var4, Icon var5)
    {
        IBlockAccess var6 = var0.blockAccess;
        Tessellator var7 = Tessellator.instance;
        GL11.glTranslatef(var2, var3, var4);
        var7.startDrawingQuads();
        var7.setNormal(0.0F, -1.0F, 0.0F);
        var7.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var7.setBrightness(240);
        var0.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, var5);
        var7.draw();
        var7.startDrawingQuads();
        var7.setNormal(0.0F, 1.0F, 0.0F);
        var7.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var7.setBrightness(240);
        var0.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, var5);
        var7.draw();
        var7.startDrawingQuads();
        var7.setNormal(0.0F, 0.0F, -1.0F);
        var7.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var7.setBrightness(240);
        var0.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, var5);
        var7.draw();
        var7.startDrawingQuads();
        var7.setNormal(0.0F, 0.0F, 1.0F);
        var7.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var7.setBrightness(240);
        var0.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, var5);
        var7.draw();
        var7.startDrawingQuads();
        var7.setNormal(-1.0F, 0.0F, 0.0F);
        var7.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var7.setBrightness(240);
        var0.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, var5);
        var7.draw();
        var7.startDrawingQuads();
        var7.setNormal(1.0F, 0.0F, 0.0F);
        var7.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var7.setBrightness(240);
        var0.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, var5);
        var7.draw();
        GL11.glTranslatef(-var2, -var3, -var4);
    }

    public static boolean ShouldRenderNeighborFullFaceSide(IBlockAccess var0, int var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var0.getBlockId(var1, var2, var3)];
        return var5 != null ? var5.ShouldRenderNeighborFullFaceSide(var0, var1, var2, var3, var4) : true;
    }

    public static boolean ShouldRenderNeighborHalfSlabSide(IBlockAccess var0, int var1, int var2, int var3, int var4, boolean var5)
    {
        Block var6 = Block.blocksList[var0.getBlockId(var1, var2, var3)];
        return var6 != null ? var6.ShouldRenderNeighborHalfSlabSide(var0, var1, var2, var3, var4, var5) : true;
    }

    public static void DrawSecondaryCraftingOutputIndicator(Minecraft var0, int var1, int var2)
    {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        String var3 = "+";
        var0.fontRenderer.drawString(var3, var1 + 1, var2, 0);
        var0.fontRenderer.drawString(var3, var1 - 1, var2, 0);
        var0.fontRenderer.drawString(var3, var1, var2 + 1, 0);
        var0.fontRenderer.drawString(var3, var1, var2 - 1, 0);
        var0.fontRenderer.drawString(var3, var1, var2, 8453920);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }
}

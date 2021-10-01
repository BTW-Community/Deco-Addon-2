package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class DecoClientUtilsRender {
	public static boolean shouldBlockRenderForMultipleLayers(IBlockAccess blockAccess, int x, int y, int z ) {
		int id = blockAccess.getBlockId(x, y, z);
		int meta = blockAccess.getBlockMetadata(x, y, z);
		
		if (id == Block.waterMoving.blockID || id == Block.waterStill.blockID || id == Block.portal.blockID || id == DecoDefs.glassStained.blockID)
			return true;
		else
			return false;
	}
	
    public static void RenderInvBlockWithMetadataAndColor(RenderBlocks render, Block block, float x, float y, float z, int meta, int red, int blue, int green)
    {
        Tessellator tess = Tessellator.instance;
        GL11.glTranslatef(x, y, z);
        tess.setColorOpaque_F(red, green, blue);
        tess.startDrawingQuads();
        tess.setNormal(0.0F, -1.0F, 0.0F);
        render.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, meta));
        tess.draw();
        tess.setColorOpaque_F(red, green, blue);
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 1.0F, 0.0F);
        render.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, meta));
        tess.draw();
        tess.setColorOpaque_F(red, green, blue);
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 0.0F, -1.0F);
        render.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, meta));
        tess.draw();
        tess.setColorOpaque_F(red, green, blue);
        tess.startDrawingQuads();
        tess.setNormal(0.0F, 0.0F, 1.0F);
        render.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, meta));
        tess.draw();
        tess.setColorOpaque_F(red, green, blue);
        tess.startDrawingQuads();
        tess.setNormal(-1.0F, 0.0F, 0.0F);
        render.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, meta));
        tess.draw();
        tess.setColorOpaque_F(red, green, blue);
        tess.startDrawingQuads();
        tess.setNormal(1.0F, 0.0F, 0.0F);
        render.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, meta));
        tess.draw();
        GL11.glTranslatef(-x, -y, -z);
    }
    
	public static boolean renderPane(RenderBlocks render, int x, int y, int z, BlockPane pane) {
		render.setRenderBounds(pane.GetBlockBoundsFromPoolBasedOnState(render.blockAccess, x, y, z));int var5 = render.blockAccess.getHeight();
        Tessellator var6 = Tessellator.instance;
        var6.setBrightness(pane.getMixedBrightnessForBlock(render.blockAccess, x, y, z));
        float var7 = 1.0F;
        int var8 = pane.colorMultiplier(render.blockAccess, x, y, z);
        float var9 = (float)(var8 >> 16 & 255) / 255.0F;
        float var10 = (float)(var8 >> 8 & 255) / 255.0F;
        float var11 = (float)(var8 & 255) / 255.0F;
        var6.setColorOpaque_F(var7 * var9, var7 * var10, var7 * var11);
        Icon var12;
        Icon var13;
        int var14;

        if (render.hasOverrideBlockTexture())
        {
            var12 = render.GetOverrideTexture();
            var13 = render.GetOverrideTexture();
        }
        else
        {
            var14 = render.blockAccess.getBlockMetadata(x, y, z);
            var12 = render.getBlockIconFromSideAndMetadata(pane, 0, var14);
            var13 = pane.getSideTextureIndex();
        }

        var14 = var12.getOriginX();
        int var15 = var12.getOriginY();
        double var16 = (double)var12.getMinU();
        double var18 = (double)var12.getInterpolatedU(8.0D);
        double var20 = (double)var12.getMaxU();
        double var22 = (double)var12.getMinV();
        double var24 = (double)var12.getMaxV();
        int var26 = var13.getOriginX();
        int var27 = var13.getOriginY();
        double var28 = (double)var13.getInterpolatedU(7.0D);
        double var30 = (double)var13.getInterpolatedU(9.0D);
        double var32 = (double)var13.getMinV();
        double var34 = (double)var13.getInterpolatedV(8.0D);
        double var36 = (double)var13.getMaxV();
        double var38 = (double)x;
        double var40 = (double)x + 0.5D;
        double var42 = (double)(x + 1);
        double var44 = (double)z;
        double var46 = (double)z + 0.5D;
        double var48 = (double)(z + 1);
        double var50 = (double)x + 0.5D - 0.0625D;
        double var52 = (double)x + 0.5D + 0.0625D;
        double var54 = (double)z + 0.5D - 0.0625D;
        double var56 = (double)z + 0.5D + 0.0625D;
		FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z, 2);
        boolean north = DecoUtilsBlock.canPaneConnect(render.blockAccess, blockPos.i, blockPos.j, blockPos.k, 2, pane);
		blockPos = new FCUtilsBlockPos(x, y, z, 3);
        boolean south = DecoUtilsBlock.canPaneConnect(render.blockAccess, blockPos.i, blockPos.j, blockPos.k, 3, pane);
		blockPos = new FCUtilsBlockPos(x, y, z, 4);
        boolean east = DecoUtilsBlock.canPaneConnect(render.blockAccess, blockPos.i, blockPos.j, blockPos.k, 4, pane);
		blockPos = new FCUtilsBlockPos(x, y, z, 5);
        boolean west = DecoUtilsBlock.canPaneConnect(render.blockAccess, blockPos.i, blockPos.j, blockPos.k, 5, pane);
        boolean var62 = pane.shouldSideBeRendered(render.blockAccess, x, y + 1, z, 1);
        boolean var63 = pane.shouldSideBeRendered(render.blockAccess, x, y - 1, z, 0);

        if ((!east || !west) && (east || west || north || south))
        {
            if (east && !west)
            {
                var6.addVertexWithUV(var38, (double)(y + 1), var46, var16, var22);
                var6.addVertexWithUV(var38, (double)(y + 0), var46, var16, var24);
                var6.addVertexWithUV(var40, (double)(y + 0), var46, var18, var24);
                var6.addVertexWithUV(var40, (double)(y + 1), var46, var18, var22);
                var6.addVertexWithUV(var40, (double)(y + 1), var46, var16, var22);
                var6.addVertexWithUV(var40, (double)(y + 0), var46, var16, var24);
                var6.addVertexWithUV(var38, (double)(y + 0), var46, var18, var24);
                var6.addVertexWithUV(var38, (double)(y + 1), var46, var18, var22);

                if (!south && !north)
                {
                    var6.addVertexWithUV(var40, (double)(y + 1), var56, var28, var32);
                    var6.addVertexWithUV(var40, (double)(y + 0), var56, var28, var36);
                    var6.addVertexWithUV(var40, (double)(y + 0), var54, var30, var36);
                    var6.addVertexWithUV(var40, (double)(y + 1), var54, var30, var32);
                    var6.addVertexWithUV(var40, (double)(y + 1), var54, var28, var32);
                    var6.addVertexWithUV(var40, (double)(y + 0), var54, var28, var36);
                    var6.addVertexWithUV(var40, (double)(y + 0), var56, var30, var36);
                    var6.addVertexWithUV(var40, (double)(y + 1), var56, var30, var32);
                }

                if (var62 || y < var5 - 1 && render.blockAccess.isAirBlock(x - 1, y + 1, z))
                {
                    var6.addVertexWithUV(var38, (double)(y + 1) + 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var56, var30, var36);
                    var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var54, var28, var36);
                    var6.addVertexWithUV(var38, (double)(y + 1) + 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var38, (double)(y + 1) + 0.01D, var56, var30, var36);
                    var6.addVertexWithUV(var38, (double)(y + 1) + 0.01D, var54, var28, var36);
                    var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var54, var28, var34);
                }

                if (var63 || y > 1 && render.blockAccess.isAirBlock(x - 1, y - 1, z))
                {
                    var6.addVertexWithUV(var38, (double)y - 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var40, (double)y - 0.01D, var56, var30, var36);
                    var6.addVertexWithUV(var40, (double)y - 0.01D, var54, var28, var36);
                    var6.addVertexWithUV(var38, (double)y - 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var40, (double)y - 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var38, (double)y - 0.01D, var56, var30, var36);
                    var6.addVertexWithUV(var38, (double)y - 0.01D, var54, var28, var36);
                    var6.addVertexWithUV(var40, (double)y - 0.01D, var54, var28, var34);
                }
            }
            else if (!east && west)
            {
                var6.addVertexWithUV(var40, (double)(y + 1), var46, var18, var22);
                var6.addVertexWithUV(var40, (double)(y + 0), var46, var18, var24);
                var6.addVertexWithUV(var42, (double)(y + 0), var46, var20, var24);
                var6.addVertexWithUV(var42, (double)(y + 1), var46, var20, var22);
                var6.addVertexWithUV(var42, (double)(y + 1), var46, var18, var22);
                var6.addVertexWithUV(var42, (double)(y + 0), var46, var18, var24);
                var6.addVertexWithUV(var40, (double)(y + 0), var46, var20, var24);
                var6.addVertexWithUV(var40, (double)(y + 1), var46, var20, var22);

                if (!south && !north)
                {
                    var6.addVertexWithUV(var40, (double)(y + 1), var54, var28, var32);
                    var6.addVertexWithUV(var40, (double)(y + 0), var54, var28, var36);
                    var6.addVertexWithUV(var40, (double)(y + 0), var56, var30, var36);
                    var6.addVertexWithUV(var40, (double)(y + 1), var56, var30, var32);
                    var6.addVertexWithUV(var40, (double)(y + 1), var56, var28, var32);
                    var6.addVertexWithUV(var40, (double)(y + 0), var56, var28, var36);
                    var6.addVertexWithUV(var40, (double)(y + 0), var54, var30, var36);
                    var6.addVertexWithUV(var40, (double)(y + 1), var54, var30, var32);
                }

                if (var62 || y < var5 - 1 && render.blockAccess.isAirBlock(x + 1, y + 1, z))
                {
                    var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var56, var30, var32);
                    var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var54, var28, var32);
                    var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var56, var30, var32);
                    var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var54, var28, var32);
                }

                if (var63 || y > 1 && render.blockAccess.isAirBlock(x + 1, y - 1, z))
                {
                    var6.addVertexWithUV(var40, (double)y - 0.01D, var56, var30, var32);
                    var6.addVertexWithUV(var42, (double)y - 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var42, (double)y - 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var40, (double)y - 0.01D, var54, var28, var32);
                    var6.addVertexWithUV(var42, (double)y - 0.01D, var56, var30, var32);
                    var6.addVertexWithUV(var40, (double)y - 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var40, (double)y - 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var42, (double)y - 0.01D, var54, var28, var32);
                }
            }
        }
        else
        {
            var6.addVertexWithUV(var38, (double)(y + 1), var46, var16, var22);
            var6.addVertexWithUV(var38, (double)(y + 0), var46, var16, var24);
            var6.addVertexWithUV(var42, (double)(y + 0), var46, var20, var24);
            var6.addVertexWithUV(var42, (double)(y + 1), var46, var20, var22);
            var6.addVertexWithUV(var42, (double)(y + 1), var46, var16, var22);
            var6.addVertexWithUV(var42, (double)(y + 0), var46, var16, var24);
            var6.addVertexWithUV(var38, (double)(y + 0), var46, var20, var24);
            var6.addVertexWithUV(var38, (double)(y + 1), var46, var20, var22);

            if (var62)
            {
                var6.addVertexWithUV(var38, (double)(y + 1) + 0.01D, var56, var30, var36);
                var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var56, var30, var32);
                var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var54, var28, var32);
                var6.addVertexWithUV(var38, (double)(y + 1) + 0.01D, var54, var28, var36);
                var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var56, var30, var36);
                var6.addVertexWithUV(var38, (double)(y + 1) + 0.01D, var56, var30, var32);
                var6.addVertexWithUV(var38, (double)(y + 1) + 0.01D, var54, var28, var32);
                var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var54, var28, var36);
            }
            else
            {
                if (y < var5 - 1 && render.blockAccess.isAirBlock(x - 1, y + 1, z))
                {
                    var6.addVertexWithUV(var38, (double)(y + 1) + 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var56, var30, var36);
                    var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var54, var28, var36);
                    var6.addVertexWithUV(var38, (double)(y + 1) + 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var38, (double)(y + 1) + 0.01D, var56, var30, var36);
                    var6.addVertexWithUV(var38, (double)(y + 1) + 0.01D, var54, var28, var36);
                    var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var54, var28, var34);
                }

                if (y < var5 - 1 && render.blockAccess.isAirBlock(x + 1, y + 1, z))
                {
                    var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var56, var30, var32);
                    var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var54, var28, var32);
                    var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var56, var30, var32);
                    var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var40, (double)(y + 1) + 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var42, (double)(y + 1) + 0.01D, var54, var28, var32);
                }
            }

            if (var63)
            {
                var6.addVertexWithUV(var38, (double)y - 0.01D, var56, var30, var36);
                var6.addVertexWithUV(var42, (double)y - 0.01D, var56, var30, var32);
                var6.addVertexWithUV(var42, (double)y - 0.01D, var54, var28, var32);
                var6.addVertexWithUV(var38, (double)y - 0.01D, var54, var28, var36);
                var6.addVertexWithUV(var42, (double)y - 0.01D, var56, var30, var36);
                var6.addVertexWithUV(var38, (double)y - 0.01D, var56, var30, var32);
                var6.addVertexWithUV(var38, (double)y - 0.01D, var54, var28, var32);
                var6.addVertexWithUV(var42, (double)y - 0.01D, var54, var28, var36);
            }
            else
            {
                if (y > 1 && render.blockAccess.isAirBlock(x - 1, y - 1, z))
                {
                    var6.addVertexWithUV(var38, (double)y - 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var40, (double)y - 0.01D, var56, var30, var36);
                    var6.addVertexWithUV(var40, (double)y - 0.01D, var54, var28, var36);
                    var6.addVertexWithUV(var38, (double)y - 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var40, (double)y - 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var38, (double)y - 0.01D, var56, var30, var36);
                    var6.addVertexWithUV(var38, (double)y - 0.01D, var54, var28, var36);
                    var6.addVertexWithUV(var40, (double)y - 0.01D, var54, var28, var34);
                }

                if (y > 1 && render.blockAccess.isAirBlock(x + 1, y - 1, z))
                {
                    var6.addVertexWithUV(var40, (double)y - 0.01D, var56, var30, var32);
                    var6.addVertexWithUV(var42, (double)y - 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var42, (double)y - 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var40, (double)y - 0.01D, var54, var28, var32);
                    var6.addVertexWithUV(var42, (double)y - 0.01D, var56, var30, var32);
                    var6.addVertexWithUV(var40, (double)y - 0.01D, var56, var30, var34);
                    var6.addVertexWithUV(var40, (double)y - 0.01D, var54, var28, var34);
                    var6.addVertexWithUV(var42, (double)y - 0.01D, var54, var28, var32);
                }
            }
        }

        if ((!north || !south) && (east || west || north || south))
        {
            if (north && !south)
            {
                var6.addVertexWithUV(var40, (double)(y + 1), var44, var16, var22);
                var6.addVertexWithUV(var40, (double)(y + 0), var44, var16, var24);
                var6.addVertexWithUV(var40, (double)(y + 0), var46, var18, var24);
                var6.addVertexWithUV(var40, (double)(y + 1), var46, var18, var22);
                var6.addVertexWithUV(var40, (double)(y + 1), var46, var16, var22);
                var6.addVertexWithUV(var40, (double)(y + 0), var46, var16, var24);
                var6.addVertexWithUV(var40, (double)(y + 0), var44, var18, var24);
                var6.addVertexWithUV(var40, (double)(y + 1), var44, var18, var22);

                if (!west && !east)
                {
                    var6.addVertexWithUV(var50, (double)(y + 1), var46, var28, var32);
                    var6.addVertexWithUV(var50, (double)(y + 0), var46, var28, var36);
                    var6.addVertexWithUV(var52, (double)(y + 0), var46, var30, var36);
                    var6.addVertexWithUV(var52, (double)(y + 1), var46, var30, var32);
                    var6.addVertexWithUV(var52, (double)(y + 1), var46, var28, var32);
                    var6.addVertexWithUV(var52, (double)(y + 0), var46, var28, var36);
                    var6.addVertexWithUV(var50, (double)(y + 0), var46, var30, var36);
                    var6.addVertexWithUV(var50, (double)(y + 1), var46, var30, var32);
                }

                if (var62 || y < var5 - 1 && render.blockAccess.isAirBlock(x, y + 1, z - 1))
                {
                    var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var44, var30, var32);
                    var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var46, var30, var34);
                    var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var46, var28, var34);
                    var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var44, var28, var32);
                    var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var46, var30, var32);
                    var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var44, var30, var34);
                    var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var44, var28, var34);
                    var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var46, var28, var32);
                }

                if (var63 || y > 1 && render.blockAccess.isAirBlock(x, y - 1, z - 1))
                {
                    var6.addVertexWithUV(var50, (double)y - 0.005D, var44, var30, var32);
                    var6.addVertexWithUV(var50, (double)y - 0.005D, var46, var30, var34);
                    var6.addVertexWithUV(var52, (double)y - 0.005D, var46, var28, var34);
                    var6.addVertexWithUV(var52, (double)y - 0.005D, var44, var28, var32);
                    var6.addVertexWithUV(var50, (double)y - 0.005D, var46, var30, var32);
                    var6.addVertexWithUV(var50, (double)y - 0.005D, var44, var30, var34);
                    var6.addVertexWithUV(var52, (double)y - 0.005D, var44, var28, var34);
                    var6.addVertexWithUV(var52, (double)y - 0.005D, var46, var28, var32);
                }
            }
            else if (!north && south)
            {
                var6.addVertexWithUV(var40, (double)(y + 1), var46, var18, var22);
                var6.addVertexWithUV(var40, (double)(y + 0), var46, var18, var24);
                var6.addVertexWithUV(var40, (double)(y + 0), var48, var20, var24);
                var6.addVertexWithUV(var40, (double)(y + 1), var48, var20, var22);
                var6.addVertexWithUV(var40, (double)(y + 1), var48, var18, var22);
                var6.addVertexWithUV(var40, (double)(y + 0), var48, var18, var24);
                var6.addVertexWithUV(var40, (double)(y + 0), var46, var20, var24);
                var6.addVertexWithUV(var40, (double)(y + 1), var46, var20, var22);

                if (!west && !east)
                {
                    var6.addVertexWithUV(var52, (double)(y + 1), var46, var28, var32);
                    var6.addVertexWithUV(var52, (double)(y + 0), var46, var28, var36);
                    var6.addVertexWithUV(var50, (double)(y + 0), var46, var30, var36);
                    var6.addVertexWithUV(var50, (double)(y + 1), var46, var30, var32);
                    var6.addVertexWithUV(var50, (double)(y + 1), var46, var28, var32);
                    var6.addVertexWithUV(var50, (double)(y + 0), var46, var28, var36);
                    var6.addVertexWithUV(var52, (double)(y + 0), var46, var30, var36);
                    var6.addVertexWithUV(var52, (double)(y + 1), var46, var30, var32);
                }

                if (var62 || y < var5 - 1 && render.blockAccess.isAirBlock(x, y + 1, z + 1))
                {
                    var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var46, var28, var34);
                    var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var48, var28, var36);
                    var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var48, var30, var36);
                    var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var46, var30, var34);
                    var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var48, var28, var34);
                    var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var46, var28, var36);
                    var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var46, var30, var36);
                    var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var48, var30, var34);
                }

                if (var63 || y > 1 && render.blockAccess.isAirBlock(x, y - 1, z + 1))
                {
                    var6.addVertexWithUV(var50, (double)y - 0.005D, var46, var28, var34);
                    var6.addVertexWithUV(var50, (double)y - 0.005D, var48, var28, var36);
                    var6.addVertexWithUV(var52, (double)y - 0.005D, var48, var30, var36);
                    var6.addVertexWithUV(var52, (double)y - 0.005D, var46, var30, var34);
                    var6.addVertexWithUV(var50, (double)y - 0.005D, var48, var28, var34);
                    var6.addVertexWithUV(var50, (double)y - 0.005D, var46, var28, var36);
                    var6.addVertexWithUV(var52, (double)y - 0.005D, var46, var30, var36);
                    var6.addVertexWithUV(var52, (double)y - 0.005D, var48, var30, var34);
                }
            }
        }
        else
        {
            var6.addVertexWithUV(var40, (double)(y + 1), var48, var16, var22);
            var6.addVertexWithUV(var40, (double)(y + 0), var48, var16, var24);
            var6.addVertexWithUV(var40, (double)(y + 0), var44, var20, var24);
            var6.addVertexWithUV(var40, (double)(y + 1), var44, var20, var22);
            var6.addVertexWithUV(var40, (double)(y + 1), var44, var16, var22);
            var6.addVertexWithUV(var40, (double)(y + 0), var44, var16, var24);
            var6.addVertexWithUV(var40, (double)(y + 0), var48, var20, var24);
            var6.addVertexWithUV(var40, (double)(y + 1), var48, var20, var22);

            if (var62)
            {
                var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var48, var30, var36);
                var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var44, var30, var32);
                var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var44, var28, var32);
                var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var48, var28, var36);
                var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var44, var30, var36);
                var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var48, var30, var32);
                var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var48, var28, var32);
                var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var44, var28, var36);
            }
            else
            {
                if (y < var5 - 1 && render.blockAccess.isAirBlock(x, y + 1, z - 1))
                {
                    var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var44, var30, var32);
                    var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var46, var30, var34);
                    var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var46, var28, var34);
                    var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var44, var28, var32);
                    var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var46, var30, var32);
                    var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var44, var30, var34);
                    var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var44, var28, var34);
                    var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var46, var28, var32);
                }

                if (y < var5 - 1 && render.blockAccess.isAirBlock(x, y + 1, z + 1))
                {
                    var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var46, var28, var34);
                    var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var48, var28, var36);
                    var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var48, var30, var36);
                    var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var46, var30, var34);
                    var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var48, var28, var34);
                    var6.addVertexWithUV(var50, (double)(y + 1) + 0.005D, var46, var28, var36);
                    var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var46, var30, var36);
                    var6.addVertexWithUV(var52, (double)(y + 1) + 0.005D, var48, var30, var34);
                }
            }

            if (var63)
            {
                var6.addVertexWithUV(var52, (double)y - 0.005D, var48, var30, var36);
                var6.addVertexWithUV(var52, (double)y - 0.005D, var44, var30, var32);
                var6.addVertexWithUV(var50, (double)y - 0.005D, var44, var28, var32);
                var6.addVertexWithUV(var50, (double)y - 0.005D, var48, var28, var36);
                var6.addVertexWithUV(var52, (double)y - 0.005D, var44, var30, var36);
                var6.addVertexWithUV(var52, (double)y - 0.005D, var48, var30, var32);
                var6.addVertexWithUV(var50, (double)y - 0.005D, var48, var28, var32);
                var6.addVertexWithUV(var50, (double)y - 0.005D, var44, var28, var36);
            }
            else
            {
                if (y > 1 && render.blockAccess.isAirBlock(x, y - 1, z - 1))
                {
                    var6.addVertexWithUV(var50, (double)y - 0.005D, var44, var30, var32);
                    var6.addVertexWithUV(var50, (double)y - 0.005D, var46, var30, var34);
                    var6.addVertexWithUV(var52, (double)y - 0.005D, var46, var28, var34);
                    var6.addVertexWithUV(var52, (double)y - 0.005D, var44, var28, var32);
                    var6.addVertexWithUV(var50, (double)y - 0.005D, var46, var30, var32);
                    var6.addVertexWithUV(var50, (double)y - 0.005D, var44, var30, var34);
                    var6.addVertexWithUV(var52, (double)y - 0.005D, var44, var28, var34);
                    var6.addVertexWithUV(var52, (double)y - 0.005D, var46, var28, var32);
                }

                if (y > 1 && render.blockAccess.isAirBlock(x, y - 1, z + 1))
                {
                    var6.addVertexWithUV(var50, (double)y - 0.005D, var46, var28, var34);
                    var6.addVertexWithUV(var50, (double)y - 0.005D, var48, var28, var36);
                    var6.addVertexWithUV(var52, (double)y - 0.005D, var48, var30, var36);
                    var6.addVertexWithUV(var52, (double)y - 0.005D, var46, var30, var34);
                    var6.addVertexWithUV(var50, (double)y - 0.005D, var48, var28, var34);
                    var6.addVertexWithUV(var50, (double)y - 0.005D, var46, var28, var36);
                    var6.addVertexWithUV(var52, (double)y - 0.005D, var46, var30, var36);
                    var6.addVertexWithUV(var52, (double)y - 0.005D, var48, var30, var34);
                }
            }
        }

        return true;
	}
	
	public static boolean renderCrossedSquares(RenderBlocks render, Block block, int x, int y, int z) {
		return renderCrossedSquares(render, block, x, y, z, 0);
	}
	
	public static boolean renderCrossedSquares(RenderBlocks render, Block block, int x, int y, int z, int facing) {
        Tessellator tesselator = Tessellator.instance;
        tesselator.setBrightness(block.getMixedBrightnessForBlock(render.blockAccess, x, y, z));
        float colorScalar = 1.0F;
        int colorMultiplier = block.colorMultiplier(render.blockAccess, x, y, z);
        float colorRed = (float)(colorMultiplier >> 16 & 255) / 255.0F;
        float colorGreen = (float)(colorMultiplier >> 8 & 255) / 255.0F;
        float colorBlue = (float)(colorMultiplier & 255) / 255.0F;
        tesselator.setColorOpaque_F(colorScalar * colorRed, colorScalar * colorGreen, colorScalar * colorBlue);
        double dX = (double)x;
        double dY = (double)y;
        double dZ = (double)z;

        if (block == Block.tallGrass) {
            long offset = (long)(x * 3129871) ^ (long)z * 116129781L ^ (long)y;
            offset = offset * offset * 42317861L + offset * 11L;
            dX += ((double)((float)(offset >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D;
            dY += ((double)((float)(offset >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D;
            dZ += ((double)((float)(offset >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D;
        }

        drawCrossedSquares(render, block, render.blockAccess.getBlockMetadata(x, y, z), dX, dY, dZ, 1.0F, facing);
        return true;
    }

    /**
     * Utility function to draw crossed swuares
     */
    public static void drawCrossedSquares(RenderBlocks render, Block block, int meta, double x, double y, double z, float yHeight, int facing) {
        Tessellator tesselator = Tessellator.instance;
        Icon blockIcon = render.getBlockIconFromSideAndMetadata(block, 0, meta);

        if (render.hasOverrideBlockTexture())
        {
            blockIcon = render.GetOverrideTexture();
        }

        double minU = (double)blockIcon.getMinU();
        double minV = (double)blockIcon.getMinV();
        double maxU = (double)blockIcon.getMaxU();
        double maxV = (double)blockIcon.getMaxV();
        double diagonalOffset = 0.45D * (double)yHeight;
        double minX = 0.5D - diagonalOffset;
        double maxX = 0.5D + diagonalOffset;
        double minY = 0;
        double maxY = yHeight;
        double minZ = 0.5D - diagonalOffset;
        double maxZ = 0.5D + diagonalOffset;
        
        double tempX;
        double tempY;
        double tempZ; 
        
        switch (facing) {
        case 0: //Down
        	//Swap min and max
        	tempX = minX;
            tempY = minY;
            tempZ = minZ;
            
        	minX = maxX;
        	minY = maxY;
        	minZ = maxZ;
        	
        	maxX = tempX;
        	maxY = tempY;
        	maxZ = tempZ;
        default:
        case 1: //Up
            minX += x;
            maxX += x;
            minY += y;
            maxY += y;
            minZ += z;
            maxZ += z;
            
        	tesselator.addVertexWithUV(minX, maxY, minZ, minU, minV);
            tesselator.addVertexWithUV(minX, minY, minZ, minU, maxV);
            tesselator.addVertexWithUV(maxX, minY, maxZ, maxU, maxV);
            tesselator.addVertexWithUV(maxX, maxY, maxZ, maxU, minV);
            
            tesselator.addVertexWithUV(maxX, maxY, maxZ, minU, minV);
            tesselator.addVertexWithUV(maxX, minY, maxZ, minU, maxV);
            tesselator.addVertexWithUV(minX, minY, minZ, maxU, maxV);
            tesselator.addVertexWithUV(minX, maxY, minZ, maxU, minV);
            
            tesselator.addVertexWithUV(minX, maxY, maxZ, minU, minV);
            tesselator.addVertexWithUV(minX, minY, maxZ, minU, maxV);
            tesselator.addVertexWithUV(maxX, minY, minZ, maxU, maxV);
            tesselator.addVertexWithUV(maxX, maxY, minZ, maxU, minV);
            
            tesselator.addVertexWithUV(maxX, maxY, minZ, minU, minV);
            tesselator.addVertexWithUV(maxX, minY, minZ, minU, maxV);
            tesselator.addVertexWithUV(minX, minY, maxZ, maxU, maxV);
            tesselator.addVertexWithUV(minX, maxY, maxZ, maxU, minV);
            
        	break;
        case 3: //South
        	//Swap min and max
        	tempX = minX;
            tempY = minY;
            tempZ = minZ;
            
        	minX = maxX;
        	minY = maxY;
        	minZ = maxZ;
        	
        	maxX = tempX;
        	maxY = tempY;
        	maxZ = tempZ;
        case 2: //North
        	//Swap Y and Z
        	tempZ = minZ;
        	minZ = minY;
        	minY = tempZ;
        	
        	tempZ = maxZ;
        	maxZ = maxY;
        	maxY = tempZ;
        	
            minX += x;
            maxX += x;
            minY += y;
            maxY += y;
            minZ += z;
            maxZ += z;
            
        	tesselator.addVertexWithUV(minX, maxY, minZ, minU, minV);
        	tesselator.addVertexWithUV(minX, maxY, maxZ, minU, maxV);
        	tesselator.addVertexWithUV(maxX, minY, maxZ, maxU, maxV);
        	tesselator.addVertexWithUV(maxX, minY, minZ, maxU, minV);

        	tesselator.addVertexWithUV(maxX, minY, minZ, minU, minV);
        	tesselator.addVertexWithUV(maxX, minY, maxZ, minU, maxV);
        	tesselator.addVertexWithUV(minX, maxY, maxZ, maxU, maxV);
        	tesselator.addVertexWithUV(minX, maxY, minZ, maxU, minV);
            
        	tesselator.addVertexWithUV(maxX, maxY, minZ, minU, minV);
        	tesselator.addVertexWithUV(maxX, maxY, maxZ, minU, maxV);
        	tesselator.addVertexWithUV(minX, minY, maxZ, maxU, maxV);
        	tesselator.addVertexWithUV(minX, minY, minZ, maxU, minV);

        	tesselator.addVertexWithUV(minX, minY, minZ, minU, minV);
        	tesselator.addVertexWithUV(minX, minY, maxZ, minU, maxV);
        	tesselator.addVertexWithUV(maxX, maxY, maxZ, maxU, maxV);
        	tesselator.addVertexWithUV(maxX, maxY, minZ, maxU, minV);
        	
        	break;
        case 5: //West
        	//Swap min and max
        	tempX = minX;
            tempY = minY;
            tempZ = minZ;
            
        	minX = maxX;
        	minY = maxY;
        	minZ = maxZ;
        	
        	maxX = tempX;
        	maxY = tempY;
        	maxZ = tempZ;
        case 4: //East
        	//Swap X and Y
        	tempX = minX;
        	minX = minY;
        	minY = tempX;
        	
        	tempX = maxX;
        	maxX = maxY;
        	maxY = tempX;
        	
            minX += x;
            maxX += x;
            minY += y;
            maxY += y;
            minZ += z;
            maxZ += z;
            
        	tesselator.addVertexWithUV(minX, maxY, minZ, minU, minV);
        	tesselator.addVertexWithUV(maxX, maxY, minZ, minU, maxV);
        	tesselator.addVertexWithUV(maxX, minY, maxZ, maxU, maxV);
        	tesselator.addVertexWithUV(minX, minY, maxZ, maxU, minV);

        	tesselator.addVertexWithUV(minX, minY, maxZ, minU, minV);
        	tesselator.addVertexWithUV(maxX, minY, maxZ, minU, maxV);
        	tesselator.addVertexWithUV(maxX, maxY, minZ, maxU, maxV);
        	tesselator.addVertexWithUV(minX, maxY, minZ, maxU, minV);
            
        	tesselator.addVertexWithUV(minX, maxY, maxZ, minU, minV);
        	tesselator.addVertexWithUV(maxX, maxY, maxZ, minU, maxV);
        	tesselator.addVertexWithUV(maxX, minY, minZ, maxU, maxV);
        	tesselator.addVertexWithUV(minX, minY, minZ, maxU, minV);

        	tesselator.addVertexWithUV(minX, minY, minZ, minU, minV);
        	tesselator.addVertexWithUV(maxX, minY, minZ, minU, maxV);
        	tesselator.addVertexWithUV(maxX, maxY, maxZ, maxU, maxV);
        	tesselator.addVertexWithUV(minX, maxY, maxZ, maxU, minV);
        	
        	break;
        }
    }
}

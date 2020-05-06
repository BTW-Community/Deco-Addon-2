package net.minecraft.src;

public class AddonClientUtilsRender {
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
        boolean north = AddonUtilsBlock.canPaneConnect(render.blockAccess, blockPos.i, blockPos.j, blockPos.k, 2, pane);
		blockPos = new FCUtilsBlockPos(x, y, z, 3);
        boolean south = AddonUtilsBlock.canPaneConnect(render.blockAccess, blockPos.i, blockPos.j, blockPos.k, 3, pane);
		blockPos = new FCUtilsBlockPos(x, y, z, 4);
        boolean east = AddonUtilsBlock.canPaneConnect(render.blockAccess, blockPos.i, blockPos.j, blockPos.k, 4, pane);
		blockPos = new FCUtilsBlockPos(x, y, z, 5);
        boolean west = AddonUtilsBlock.canPaneConnect(render.blockAccess, blockPos.i, blockPos.j, blockPos.k, 5, pane);
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
}

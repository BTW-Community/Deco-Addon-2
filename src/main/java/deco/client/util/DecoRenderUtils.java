package deco.client.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.Block;
import net.minecraft.src.Icon;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;

@Environment(EnvType.CLIENT)
public class DecoRenderUtils {
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
	
	public static void drawCrossedSquares(RenderBlocks render, Block block, int meta, double x, double y, double z, float yHeight, int facing) {
		Tessellator tesselator = Tessellator.instance;
		Icon blockIcon = render.getBlockIconFromSideAndMetadata(block, 0, meta);
		
		if (render.hasOverrideBlockTexture()) {
			blockIcon = render.getOverrideTexture();
		}
		
		double minU = blockIcon.getMinU();
		double minV = blockIcon.getMinV();
		double maxU = blockIcon.getMaxU();
		double maxV = blockIcon.getMaxV();
		double diagonalOffset = 0.45D * (double) yHeight;
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

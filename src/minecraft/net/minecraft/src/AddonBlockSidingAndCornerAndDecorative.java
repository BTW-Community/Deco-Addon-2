package net.minecraft.src;

import java.util.List;

import org.lwjgl.opengl.GL11;

public class AddonBlockSidingAndCornerAndDecorative extends FCBlockSidingAndCornerAndDecorative {
	public AddonBlockSidingAndCornerAndDecorative(int var1, Material var2, String var3, float var4, float var5, StepSound var6, String var7)
	{
		super(var1, var2, var3, var4, var5, var6, var7);
	}

	@Override
    public AxisAlignedBB GetBlockBoundsFromPoolForFence(IBlockAccess var1, int var2, int var3, int var4)
    {
        AxisAlignedBB var5 = AxisAlignedBB.getAABBPool().getAABB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);

        if (this.CanConnectToBlockToFacing(var1, var2, var3, var4, 2))
        {
            var5.minZ = 0.0D;
        }

        if (this.CanConnectToBlockToFacing(var1, var2, var3, var4, 3))
        {
            var5.maxZ = 1.0D;
        }

        if (this.CanConnectToBlockToFacing(var1, var2, var3, var4, 4))
        {
            var5.minX = 0.0D;
        }

        if (this.CanConnectToBlockToFacing(var1, var2, var3, var4, 5))
        {
            var5.maxX = 1.0D;
        }

        return var5;
    }

	@Override
    public void AddCollisionBoxesToListForFence(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7)
    {
        boolean var8 = this.CanConnectToBlockToFacing(var1, var2, var3, var4, 4);
        boolean var9 = this.CanConnectToBlockToFacing(var1, var2, var3, var4, 5);
        boolean var10 = this.CanConnectToBlockToFacing(var1, var2, var3, var4, 2);
        boolean var11 = this.CanConnectToBlockToFacing(var1, var2, var3, var4, 3);
        float var12 = 0.375F;
        float var13 = 0.625F;
        float var14 = 0.375F;
        float var15 = 0.625F;
        
        if (var10)
        {
            var14 = 0.0F;
        }

        if (var11)
        {
            var15 = 1.0F;
        }

        if (var10 || var11)
        {
            AxisAlignedBB.getAABBPool().getAABB((double)var12, 0.0D, (double)var14, (double)var13, 1.5D, (double)var15).offset((double)var2, (double)var3, (double)var4).AddToListIfIntersects(var5, var6);
        }

        if (var8)
        {
            var12 = 0.0F;
        }

        if (var9)
        {
            var13 = 1.0F;
        }

        if (var8 || var9 || !var10 && !var11)
        {
            AxisAlignedBB.getAABBPool().getAABB((double)var12, 0.0D, 0.375D, (double)var13, 1.5D, 0.625D).offset((double)var2, (double)var3, (double)var4).AddToListIfIntersects(var5, var6);
        }
    }

    protected boolean CanConnectToBlockToFacing(IBlockAccess blockAccess, int x, int y, int z, int facing)
    {
        FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z, facing);
        return AddonUtilsBlock.canFenceConnect(blockAccess, blockPos.i, blockPos.j, blockPos.k, facing, this);
    }

    protected boolean CanConnectToBlockToFacing(World world, int x, int y, int z, int facing)
    {
        FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z, facing);
        return AddonUtilsBlock.canFenceConnect(world, blockPos.i, blockPos.j, blockPos.k, facing, this);
    }
    
    public boolean isFenceFromMetadata(int metadata) {
    	return metadata == 14;
    }
	
//CLIENT ONLY
    @Override
    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        int var6 = var5.getBlockMetadata(var2, var3, var4);
        return var6 == 12 ? RenderBench(var1, var5, var2, var3, var4, this) : (var6 == 14 ? this.renderBlockFence(var1, var2, var3, var4) : super.RenderBlock(var1, var2, var3, var4));
    }
    
    public boolean renderBlockFence(RenderBlocks render, int par2, int par3, int par4)
    {
        boolean var5 = false;
        float var6 = 0.375F;
        float var7 = 0.625F;
        render.setRenderBounds((double)var6, 0.0D, (double)var6, (double)var7, 1.0D, (double)var7);
        render.renderStandardBlock(this, par2, par3, par4);
        var5 = true;
        boolean var8 = false;
        boolean var9 = false;

        if (this.CanConnectToBlockToFacing(render.blockAccess, par2, par3, par4, 4) || this.CanConnectToBlockToFacing(render.blockAccess, par2, par3, par4, 5))
        {
            var8 = true;
        }

        if (this.CanConnectToBlockToFacing(render.blockAccess, par2, par3, par4, 2) || this.CanConnectToBlockToFacing(render.blockAccess, par2, par3, par4, 3))
        {
            var9 = true;
        }

        boolean var10 = this.CanConnectToBlockToFacing(render.blockAccess, par2, par3, par4, 4);
        boolean var11 = this.CanConnectToBlockToFacing(render.blockAccess, par2, par3, par4, 5);
        boolean var12 = this.CanConnectToBlockToFacing(render.blockAccess, par2, par3, par4, 2);
        boolean var13 = this.CanConnectToBlockToFacing(render.blockAccess, par2, par3, par4, 3);

        if (!var8 && !var9)
        {
            var8 = true;
        }

        var6 = 0.4375F;
        var7 = 0.5625F;
        float var14 = 0.75F;
        float var15 = 0.9375F;
        float var16 = var10 ? 0.0F : var6;
        float var17 = var11 ? 1.0F : var7;
        float var18 = var12 ? 0.0F : var6;
        float var19 = var13 ? 1.0F : var7;

        if (var8)
        {
        	render.setRenderBounds((double)var16, (double)var14, (double)var6, (double)var17, (double)var15, (double)var7);
        	render.renderStandardBlock(this, par2, par3, par4);
            var5 = true;
        }

        if (var9)
        {
        	render.setRenderBounds((double)var6, (double)var14, (double)var18, (double)var7, (double)var15, (double)var19);
        	render.renderStandardBlock(this, par2, par3, par4);
            var5 = true;
        }

        var14 = 0.375F;
        var15 = 0.5625F;

        if (var8)
        {
        	render.setRenderBounds((double)var16, (double)var14, (double)var6, (double)var17, (double)var15, (double)var7);
        	render.renderStandardBlock(this, par2, par3, par4);
            var5 = true;
        }

        if (var9)
        {
        	render.setRenderBounds((double)var6, (double)var14, (double)var18, (double)var7, (double)var15, (double)var19);
        	render.renderStandardBlock(this, par2, par3, par4);
            var5 = true;
        }

        this.setBlockBoundsBasedOnState(render.blockAccess, par2, par3, par4);
        return var5;
    }
    
    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        int var4 = var2;
        Object var5 = this;

        if (var4 == 12)
        {
            RenderBenchInvBlock(var1, (Block)var5, var4);
        }
        else if (var4 == 14)
        {
            RenderFenceInvBlock(var1, (Block)var5, var4);
        }
        else
        {
        	if ((var2 & 1) != 0)
            {
                var1.setRenderBounds(0.25D, 0.25D, 0.25D, 0.75D, 0.75D, 0.75D);
            }
            else
            {
                var1.setRenderBounds(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, 1.0D);
            }
            
        	FCClientUtilsRender.RenderInvBlockWithMetadata(var1, this, -0.5F, -0.5F, -0.5F, 0);
        }
    }

    public static void RenderFenceInvBlock(RenderBlocks var0, Block var1, int var2)
    {
        Tessellator var3 = Tessellator.instance;

        for (int var4 = 0; var4 < 4; ++var4)
        {
            float var5 = 0.125F;

            if (var4 == 0)
            {
                var0.setRenderBounds((double)(0.5F - var5), 0.0D, 0.0D, (double)(0.5F + var5), 1.0D, (double)(var5 * 2.0F));
            }

            if (var4 == 1)
            {
                var0.setRenderBounds((double)(0.5F - var5), 0.0D, (double)(1.0F - var5 * 2.0F), (double)(0.5F + var5), 1.0D, 1.0D);
            }

            var5 = 0.0625F;

            if (var4 == 2)
            {
                var0.setRenderBounds((double)(0.5F - var5), (double)(1.0F - var5 * 4.0F), (double)(-var5 * 2.0F), (double)(0.5F + var5), (double)(1.0F - var5), (double)(1.0F + var5 * 2.0F));
            }

            if (var4 == 3)
            {
                var0.setRenderBounds((double)(0.5F - var5), (double)(0.5F - var5 * 2.0F), (double)(-var5 * 2.0F), (double)(0.5F + var5), (double)(0.5F + var5), (double)(1.0F + var5 * 2.0F));
            }

            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            var3.startDrawingQuads();
            var3.setNormal(0.0F, -1.0F, 0.0F);
            var0.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(0));
            var3.draw();
            var3.startDrawingQuads();
            var3.setNormal(0.0F, 1.0F, 0.0F);
            var0.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(1));
            var3.draw();
            var3.startDrawingQuads();
            var3.setNormal(0.0F, 0.0F, -1.0F);
            var0.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(2));
            var3.draw();
            var3.startDrawingQuads();
            var3.setNormal(0.0F, 0.0F, 1.0F);
            var0.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(3));
            var3.draw();
            var3.startDrawingQuads();
            var3.setNormal(-1.0F, 0.0F, 0.0F);
            var0.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(4));
            var3.draw();
            var3.startDrawingQuads();
            var3.setNormal(1.0F, 0.0F, 0.0F);
            var0.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(5));
            var3.draw();
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        }

        var0.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    }
}

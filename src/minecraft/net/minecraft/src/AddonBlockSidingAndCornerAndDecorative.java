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
    
    /*
    public static boolean RenderFence(RenderBlocks var0, IBlockAccess var1, int var2, int var3, int var4, Block var5)
    {
        var0.setRenderBounds(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
        var0.renderStandardBlock(var5, var2, var3, var4);
        AddonBlockSidingAndCornerAndDecorative var6 = (AddonBlockSidingAndCornerAndDecorative)var5;
        boolean var7 = false;
        
        boolean var8 = var6.CanConnectToBlockToFacing(var0.blockAccess, var2, var3, var4, 2);
        boolean var9 = var6.CanConnectToBlockToFacing(var0.blockAccess, var2, var3, var4, 3);
        boolean var10 = var6.CanConnectToBlockToFacing(var0.blockAccess, var2, var3, var4, 4);
        boolean var11 = var6.CanConnectToBlockToFacing(var0.blockAccess, var2, var3, var4, 5);

        if (var8 || var9)
        {
            var7 = true;
        }

        boolean var12 = false;

        if (var10 || var11)
        {
            var12 = true;
        }

        if (!var7 && !var12)
        {
            var7 = true;
        }

        float var13 = 0.4375F;
        float var14 = 0.5625F;
        float var15 = 0.75F;
        float var16 = 0.9375F;
        float var17 = var8 ? 0.0F : var13;
        float var18 = var9 ? 1.0F : var14;
        float var19 = var10 ? 0.0F : var13;
        float var20 = var11 ? 1.0F : var14;

        if (var7)
        {
            var0.setRenderBounds((double)var17, (double)var15, (double)var13, (double)var18, (double)var16, (double)var14);
            var0.renderStandardBlock(var6, var2, var3, var4);
        }

        if (var12)
        {
            var0.setRenderBounds((double)var13, (double)var15, (double)var19, (double)var14, (double)var16, (double)var20);
            var0.renderStandardBlock(var6, var2, var3, var4);
        }

        var15 = 0.375F;
        var16 = 0.5625F;

        if (var7)
        {
            var0.setRenderBounds((double)var17, (double)var15, (double)var13, (double)var18, (double)var16, (double)var14);
            var0.renderStandardBlock(var6, var2, var3, var4);
        }

        if (var12)
        {
            var0.setRenderBounds((double)var13, (double)var15, (double)var19, (double)var14, (double)var16, (double)var20);
            var0.renderStandardBlock(var6, var2, var3, var4);
        }

        return true;
    }*/
}

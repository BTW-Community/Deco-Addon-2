package net.minecraft.src;

import java.util.List;

public class DecoBlockSidingAndCornerAndDecorative extends FCBlockSidingAndCornerAndDecorative {
	public DecoBlockSidingAndCornerAndDecorative(int var1, Material var2, String var3, float var4, float var5, StepSound var6, String var7)
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
        return DecoUtilsBlock.canFenceConnect(blockAccess, blockPos.i, blockPos.j, blockPos.k, facing, this);
    }

    protected boolean CanConnectToBlockToFacing(World world, int x, int y, int z, int facing)
    {
        FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z, facing);
        return DecoUtilsBlock.canFenceConnect(world, blockPos.i, blockPos.j, blockPos.k, facing, this);
    }
    
    public boolean isFenceFromMetadata(int metadata) {
    	return metadata == 14;
    }
}

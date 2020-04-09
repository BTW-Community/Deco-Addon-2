package net.minecraft.src;

public class AddonBlockWall extends FCBlockWall {
	public AddonBlockWall(int var1, Block var2) {
		super(var1, var2);
	}

	protected boolean CanConnectToBlockToFacing(IBlockAccess blockAccess, int x, int y, int z, int facing)
	{
		FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z, facing);
		return AddonUtilsBlock.canWallConnect(blockAccess, blockPos.i, blockPos.j, blockPos.k, facing, this);
	}

	protected boolean CanConnectToBlockToFacing(World world, int x, int y, int z, int facing)
	{
		FCUtilsBlockPos blockPos = new FCUtilsBlockPos(x, y, z, facing);
		return AddonUtilsBlock.canWallConnect(world, blockPos.i, blockPos.j, blockPos.k, facing, this);
	}

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
		boolean var5 = this.CanConnectToBlockToFacing(var1, var2, var3, var4, 2);
		boolean var6 = this.CanConnectToBlockToFacing(var1, var2, var3, var4, 3);
		boolean var7 = this.CanConnectToBlockToFacing(var1, var2, var3, var4, 4);
		boolean var8 = this.CanConnectToBlockToFacing(var1, var2, var3, var4, 5);
        float var9 = 0.25F;
        float var10 = 0.75F;
        float var11 = 0.25F;
        float var12 = 0.75F;
        float var13 = 1.0F;

        if (var5)
        {
            var11 = 0.0F;
        }

        if (var6)
        {
            var12 = 1.0F;
        }

        if (var7)
        {
            var9 = 0.0F;
        }

        if (var8)
        {
            var10 = 1.0F;
        }

        if (var5 && var6 && !var7 && !var8)
        {
            var13 = 0.8125F;
            var9 = 0.3125F;
            var10 = 0.6875F;
        }
        else if (!var5 && !var6 && var7 && var8)
        {
            var13 = 0.8125F;
            var11 = 0.3125F;
            var12 = 0.6875F;
        }

        return AxisAlignedBB.getAABBPool().getAABB((double)var9, 0.0D, (double)var11, (double)var10, (double)var13, (double)var12);
    }

    //CLIENT ONLY
    public boolean RenderBlock(RenderBlocks render, int par2, int par3, int par4)
	{
		boolean var5 = this.CanConnectToBlockToFacing(render.blockAccess, par2, par3, par4, 4);
		boolean var6 = this.CanConnectToBlockToFacing(render.blockAccess, par2, par3, par4, 5);
		boolean var7 = this.CanConnectToBlockToFacing(render.blockAccess, par2, par3, par4, 2);
		boolean var8 = this.CanConnectToBlockToFacing(render.blockAccess, par2, par3, par4, 3);
		boolean var9 = var7 && var8 && !var5 && !var6;
		boolean var10 = !var7 && !var8 && var5 && var6;
		boolean var11 = render.blockAccess.isAirBlock(par2, par3 + 1, par4);
		var11 = var11 || FCUtilsWorld.IsGroundCoverOnBlock(render.blockAccess, par2, par3, par4);

		if ((var9 || var10) && var11)
		{
			if (var9)
			{
				render.setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, 0.8125D, 1.0D);
				render.renderStandardBlock(this, par2, par3, par4);
			}
			else
			{
				render.setRenderBounds(0.0D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
				render.renderStandardBlock(this, par2, par3, par4);
			}
		}
		else
		{
			render.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
			render.renderStandardBlock(this, par2, par3, par4);

			if (var5)
			{
				render.setRenderBounds(0.0D, 0.0D, 0.3125D, 0.25D, 0.8125D, 0.6875D);
				render.renderStandardBlock(this, par2, par3, par4);
			}

			if (var6)
			{
				render.setRenderBounds(0.75D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
				render.renderStandardBlock(this, par2, par3, par4);
			}

			if (var7)
			{
				render.setRenderBounds(0.3125D, 0.0D, 0.0D, 0.6875D, 0.8125D, 0.25D);
				render.renderStandardBlock(this, par2, par3, par4);
			}

			if (var8)
			{
				render.setRenderBounds(0.3125D, 0.0D, 0.75D, 0.6875D, 0.8125D, 1.0D);
				render.renderStandardBlock(this, par2, par3, par4);
			}
		}

		this.setBlockBoundsBasedOnState(render.blockAccess, par2, par3, par4);
		return true;
    }
}
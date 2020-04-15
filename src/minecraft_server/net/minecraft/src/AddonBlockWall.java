package net.minecraft.src;

import java.util.Random;

public class AddonBlockWall extends FCBlockWall {
	public AddonBlockWall(int var1, Block var2) {
		super(var1, var2);
	}

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return this.blockID;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        if (!par1World.isRemote)
        {
            int var8 = this.quantityDroppedWithBonus(par7, par1World.rand);

            for (int var9 = 0; var9 < var8; ++var9)
            {
                if (par1World.rand.nextFloat() <= par6)
                {
                    int var10 = this.idDropped(par5, par1World.rand, par7);

                    if (var10 > 0)
                    {
                        this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(var10, 1, this.damageDropped(par5)));
                    }
                }
            }
        }
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
}
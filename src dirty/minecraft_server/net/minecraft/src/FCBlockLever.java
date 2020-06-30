package net.minecraft.src;

public class FCBlockLever extends BlockLever
{
    protected FCBlockLever(int var1)
    {
        super(var1);
        this.SetPicksEffectiveOn(true);
    }

    public int GetFacing(int var1)
    {
        return MathHelper.clamp_int(6 - (var1 & 7), 1, 5);
    }

    public boolean CanRotateAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return var5 == Block.GetOppositeFacing(this.GetFacing(var1, var2, var3, var4));
    }

    public boolean OnRotatedAroundBlockOnTurntableToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
        var1.setBlockToAir(var2, var3, var4);
        return false;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1.0F;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4) & 7;
        float var6 = 0.1875F;

        if (var5 == 1)
        {
            return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.20000000298023224D, (double)(0.5F - var6), (double)(var6 * 2.0F), 0.800000011920929D, (double)(0.5F + var6));
        }
        else if (var5 == 2)
        {
            return AxisAlignedBB.getAABBPool().getAABB((double)(1.0F - var6 * 2.0F), 0.20000000298023224D, (double)(0.5F - var6), 1.0D, 0.800000011920929D, (double)(0.5F + var6));
        }
        else if (var5 == 3)
        {
            return AxisAlignedBB.getAABBPool().getAABB((double)(0.5F - var6), 0.20000000298023224D, 0.0D, (double)(0.5F + var6), 0.800000011920929D, (double)(var6 * 2.0F));
        }
        else if (var5 == 4)
        {
            return AxisAlignedBB.getAABBPool().getAABB((double)(0.5F - var6), 0.20000000298023224D, (double)(1.0F - var6 * 2.0F), (double)(0.5F + var6), 0.800000011920929D, 1.0D);
        }
        else
        {
            var6 = 0.25F;
            return var5 != 0 && var5 != 7 ? AxisAlignedBB.getAABBPool().getAABB((double)(0.5F - var6), 0.0D, (double)(0.5F - var6), (double)(0.5F + var6), 0.6000000238418579D, (double)(0.5F + var6)) : AxisAlignedBB.getAABBPool().getAABB((double)(0.5F - var6), 0.4000000059604645D, (double)(0.5F - var6), (double)(0.5F + var6), 1.0D, (double)(0.5F + var6));
        }
    }
}

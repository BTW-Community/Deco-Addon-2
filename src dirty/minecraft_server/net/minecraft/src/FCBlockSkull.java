package net.minecraft.src;

public class FCBlockSkull extends BlockSkull
{
    protected FCBlockSkull(int var1)
    {
        super(var1);
        this.SetAxesEffectiveOn(true);
        this.setHardness(1.0F);
        this.InitBlockBounds(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
        this.setStepSound(soundStoneFootstep);
        this.setUnlocalizedName("skull");
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4) & 7;

        switch (var5)
        {
            case 2:
                return AxisAlignedBB.getAABBPool().getAABB(0.25D, 0.25D, 0.5D, 0.75D, 0.75D, 1.0D);

            case 3:
                return AxisAlignedBB.getAABBPool().getAABB(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 0.5D);

            case 4:
                return AxisAlignedBB.getAABBPool().getAABB(0.5D, 0.25D, 0.25D, 1.0D, 0.75D, 0.75D);

            case 5:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.25D, 0.25D, 0.5D, 0.75D, 0.75D);

            default:
                return AxisAlignedBB.getAABBPool().getAABB(0.25D, 0.0D, 0.25D, 0.75D, 0.5D, 0.75D);
        }
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return this.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4).offset((double)var2, (double)var3, (double)var4);
    }

    /**
     * This method attempts to create a wither at the given location and skull
     */
    public void makeWither(World var1, int var2, int var3, int var4, TileEntitySkull var5) {}

    public boolean IsBlockSummoningBase(World var1, int var2, int var3, int var4)
    {
        if (var1.getBlockId(var2, var3, var4) == FCBetterThanWolves.fcAestheticOpaque.blockID)
        {
            int var5 = var1.getBlockMetadata(var2, var3, var4);

            if (var5 == 15)
            {
                return true;
            }
        }

        return false;
    }

    public boolean IsBlockRestingOnThatBelow(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 1;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean RotateAroundJAxis(World var1, int var2, int var3, int var4, boolean var5)
    {
        TileEntity var6 = var1.getBlockTileEntity(var2, var3, var4);

        if (var6 != null && var6 instanceof TileEntitySkull)
        {
            TileEntitySkull var7 = (TileEntitySkull)var6;
            int var8 = var7.GetSkullRotationServerSafe();

            if (var5)
            {
                var8 += 4;

                if (var8 > 15)
                {
                    var8 -= 16;
                }
            }
            else
            {
                var8 -= 4;

                if (var8 < 0)
                {
                    var8 += 16;
                }
            }

            var7.setSkullRotation(var8);
            var1.markBlockForUpdate(var2, var3, var4);
            return true;
        }
        else
        {
            return false;
        }
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        return null;
    }
}

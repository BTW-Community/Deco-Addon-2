package net.minecraft.src;

import java.util.List;

public class FCBlockFence extends Block
{
    protected static final FCModelBlockFence m_model = new FCModelBlockFence();
    protected final String m_sIconName;

    public FCBlockFence(int var1, String var2, Material var3)
    {
        super(var1, var3);
        this.m_sIconName = var2;
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.5D, 1.0D);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean getBlocksMovement(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCModelBlock var7 = this.AssembleTemporaryModel(var1, var2, var3, var4);
        return var7.CollisionRayTrace(var1, var2, var3, var4, var5, var6);
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7)
    {
        AxisAlignedBB var8 = m_model.m_boxCollisionCenter.MakeTemporaryCopy();
        var8.offset((double)var2, (double)var3, (double)var4);

        if (var8.intersectsWith(var5))
        {
            var6.add(var8);
        }

        for (int var9 = 2; var9 <= 5; ++var9)
        {
            if (this.CanConnectToBlockToFacing(var1, var2, var3, var4, var9))
            {
                var8 = m_model.m_boxCollisionStruts.MakeTemporaryCopy();
                var8.RotateAroundJToFacing(var9);
                var8.offset((double)var2, (double)var3, (double)var4);

                if (var8.intersectsWith(var5))
                {
                    var6.add(var8);
                }
            }
        }
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        AxisAlignedBB var5 = m_model.m_boxBoundsCenter.MakeTemporaryCopy();

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

    public int GetWeightOnPathBlocked(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -3;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1.0F;
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var5 == 0 || var5 == 1;
    }

    public float MobSpawnOnVerticalOffset(World var1, int var2, int var3, int var4)
    {
        return 0.5F;
    }

    protected boolean CanConnectToBlockToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);
        return this.CanConnectToBlockAt(var1, var6.i, var6.j, var6.k);
    }

    protected boolean CanConnectToBlockAt(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);

        if (var5 != this.blockID && var5 != Block.fenceGate.blockID)
        {
            Block var6 = Block.blocksList[var5];
            return var6 != null && var6.blockMaterial.isOpaque() && var6.renderAsNormalBlock() ? var6.blockMaterial != Material.pumpkin : false;
        }
        else
        {
            return true;
        }
    }

    protected FCModelBlock AssembleTemporaryModel(IBlockAccess var1, int var2, int var3, int var4)
    {
        FCModelBlock var5 = m_model.MakeTemporaryCopy();

        for (int var6 = 2; var6 <= 5; ++var6)
        {
            if (this.CanConnectToBlockToFacing(var1, var2, var3, var4, var6))
            {
                FCModelBlock var7 = m_model.m_modelStruts.MakeTemporaryCopy();
                var7.RotateAroundJToFacing(var6);
                var7.MakeTemporaryCopyOfPrimitiveList(var5);
            }
        }

        return var5;
    }
}

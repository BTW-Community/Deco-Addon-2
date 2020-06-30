package net.minecraft.src;

public class FCBlockChunkOre extends Block
{
    protected static FCModelBlockChunkOre m_model = new FCModelBlockChunkOre();

    protected FCBlockChunkOre(int var1)
    {
        super(var1, Material.circuits);
        this.setHardness(0.0F);
        this.SetPicksEffectiveOn(true);
        FCModelBlockChunkOre var10002 = m_model;
        double var10001 = 0.5D - 0.125D;
        var10002 = m_model;
        FCModelBlockChunkOre var10004 = m_model;
        double var10003 = 0.5D - 0.125D;
        FCModelBlockChunkOre var10005 = m_model;
        double var3 = 0.5D + 0.125D;
        var10005 = m_model;
        FCModelBlockChunkOre var10006 = m_model;
        double var2 = 0.03125D + 0.1875D;
        FCModelBlockChunkOre var10007 = m_model;
        this.InitBlockBounds(var10001, 0.03125D, var10003, var3, var2, 0.5D + 0.125D);
        this.setStepSound(soundStoneFootstep);
        this.SetCanBeCookedByKiln(true);
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

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        int var10 = var1.rand.nextInt(4) + 2;
        return this.SetFacing(var9, var10);
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        return FCUtilsWorld.DoesBlockHaveSmallCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true) ? super.canPlaceBlockAt(var1, var2, var3, var4) : false;
    }

    public boolean IsBlockRestingOnThatBelow(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!FCUtilsWorld.DoesBlockHaveSmallCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockToAir(var2, var3, var4);
        }
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return null;
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCModelBlock var7 = m_model.MakeTemporaryCopy();
        int var8 = this.GetFacing(var1, var2, var3, var4);
        var7.RotateAroundJToFacing(var8);
        return var7.CollisionRayTrace(var1, var2, var3, var4, var5, var6);
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1.0F;
    }

    public int GetFacing(int var1)
    {
        return (var1 & 3) + 2;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 &= -4;
        var1 |= MathHelper.clamp_int(var2, 2, 5) - 2;
        return var1;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public int GetCookTimeMultiplierInKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 8;
    }
}

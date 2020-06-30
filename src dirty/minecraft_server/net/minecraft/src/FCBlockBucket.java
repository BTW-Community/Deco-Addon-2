package net.minecraft.src;

import java.util.Random;

public class FCBlockBucket extends FCBlockFalling
{
    protected FCModelBlockBucket m_model;
    protected FCModelBlock m_modelTransformed;

    public FCBlockBucket(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialMiscellaneous);
        this.setHardness(0.0F);
        this.setResistance(0.0F);
        this.InitBlockBounds(0.28125D, 0.0625D, 0.28125D, 0.71875D, 0.5D, 0.71875D);
        this.setStepSound(soundMetalFootstep);
        this.setUnlocalizedName("bucket");
        this.InitModels();
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
        var9 = this.SetFacing(var9, var5);
        return var9;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Item.bucketEmpty.itemID;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, Item.bucketEmpty.itemID, 1, 0, var6);
        return true;
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
        this.m_modelTransformed = this.m_model.MakeTemporaryCopy();
        int var7 = this.GetFacing(var1, var2, var3, var4);
        this.m_modelTransformed.TiltToFacingAlongJ(var7);
        FCModelBlockBucket.OffsetModelForFacing(this.m_modelTransformed, var7);
        return this.m_modelTransformed.CollisionRayTrace(var1, var2, var3, var4, var5, var6);
    }

    public int GetFacing(int var1)
    {
        return var1 & 7;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 &= -8;
        return var1 | var2;
    }

    public int OnPreBlockPlacedByPiston(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        if (!FCUtilsWorld.DoesBlockHaveCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true) && var6 >= 2)
        {
            int var7 = this.GetFacing(var5);

            if (var7 == 0)
            {
                var7 = Block.GetOppositeFacing(var6);
            }
            else if (var7 == 1)
            {
                var7 = var6;
            }
            else if (var7 == var6)
            {
                var7 = 0;
            }
            else if (var7 == Block.GetOppositeFacing(var6))
            {
                var7 = 1;
            }

            var5 = this.SetFacing(var5, var7);
        }

        return var5;
    }

    public boolean GetPreventsFluidFlow(World var1, int var2, int var3, int var4, Block var5)
    {
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

    protected void InitModels()
    {
        this.m_model = new FCModelBlockBucket();
        this.m_modelTransformed = this.m_model;
    }
}

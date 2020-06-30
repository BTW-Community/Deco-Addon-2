package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockUnfiredBrick extends BlockContainer
{
    public static final float m_fBrickHeight = 0.25F;
    public static final float m_fBrickWidth = 0.375F;
    public static final float m_fBrickHalfWidth = 0.1875F;
    public static final float m_fBrickLength = 0.75F;
    public static final float m_fBrickHalfLength = 0.375F;

    public FCBlockUnfiredBrick(int var1)
    {
        super(var1, Material.circuits);
        this.setHardness(0.0F);
        this.SetShovelsEffectiveOn(true);
        this.setStepSound(FCBetterThanWolves.fcStepSoundSquish);
        this.SetCanBeCookedByKiln(true);
        this.setUnlocalizedName("fcBlockUnfiredBrick");
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityUnfiredBrick();
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        return this.SetIAligned(var9, this.IsFacingIAligned(var5));
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
    {
        int var7 = FCUtilsMisc.ConvertOrientationToFlatBlockFacingReversed(var5);
        this.SetIAligned(var1, var2, var3, var4, this.IsFacingIAligned(var7));
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Item.clay.itemID;
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
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return null;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsIAligned(var1, var2, var3, var4) ? AxisAlignedBB.getAABBPool().getAABB(0.125D, 0.0D, 0.3125D, 0.875D, 0.25D, 0.6875D) : AxisAlignedBB.getAABBPool().getAABB(0.3125D, 0.0D, 0.125D, 0.6875D, 0.25D, 0.875D);
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        return FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true) && var1.getBlockId(var2, var3 - 1, var4) != Block.leaves.blockID;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    public int GetItemIndexDroppedWhenCookedByKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        return Item.brick.itemID;
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
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        if (!var1.isRemote && !var5.isDead && var5 instanceof EntityLiving && !(var5 instanceof EntityAmbientCreature))
        {
            List var6 = var1.getEntitiesWithinAABB(EntityLiving.class, this.GetVisualBB(var1, var2, var3, var4));

            if (var6 != null && var6.size() > 0)
            {
                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, this.stepSound.getStepSound(), (this.stepSound.getVolume() + 1.0F) / 2.0F, this.stepSound.getPitch() * 0.8F);
                this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
                var1.setBlockWithNotify(var2, var3, var4, 0);
            }
        }
    }

    public int GetFacing(int var1)
    {
        return this.GetIsIAligned(var1) ? 4 : 2;
    }

    public int SetFacing(int var1, int var2)
    {
        return this.SetIAligned(var1, this.IsFacingIAligned(var2));
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        return this.SetIAligned(var1, !this.GetIsIAligned(var1));
    }

    public int GetCookTimeMultiplierInKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 4;
    }

    public void OnFinishedCooking(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4) & 1;
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockCookedBrick.blockID, var5);
    }

    public void SetIAligned(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.SetIAligned(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetIAligned(int var1, boolean var2)
    {
        if (var2)
        {
            var1 |= 1;
        }
        else
        {
            var1 &= -2;
        }

        return var1;
    }

    public boolean GetIsIAligned(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsIAligned(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean GetIsIAligned(int var1)
    {
        return (var1 & 1) != 0;
    }

    public boolean IsFacingIAligned(int var1)
    {
        return var1 >= 4;
    }

    public void SetCookLevel(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.SetCookLevel(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetCookLevel(int var1, int var2)
    {
        var1 &= 1;
        var1 |= var2 << 1;
        return var1;
    }

    public int GetCookLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetCookLevel(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetCookLevel(int var1)
    {
        return var1 >> 1;
    }

    public AxisAlignedBB GetVisualBB(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsIAligned(var1, var2, var3, var4) ? AxisAlignedBB.getAABBPool().getAABB((double)((float)var2 + 0.125F), (double)var3, (double)((float)var4 + 0.3125F), (double)((float)var2 + 0.875F), (double)((float)var3 + 0.25F), (double)((float)var4 + 0.6875F)) : AxisAlignedBB.getAABBPool().getAABB((double)((float)var2 + 0.3125F), (double)var3, (double)((float)var4 + 0.125F), (double)((float)var2 + 0.6875F), (double)((float)var3 + 0.25F), (double)((float)var4 + 0.875F));
    }
}

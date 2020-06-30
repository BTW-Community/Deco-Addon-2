package net.minecraft.src;

import java.util.Random;

public abstract class BlockRailBase extends Block
{
    /** Power related rails have this field at true. */
    protected final boolean isPowered;

    /**
     * Returns true if the block at the coordinates of world passed is a valid rail block (current is rail, powered or
     * detector).
     */
    public static final boolean isRailBlockAt(World par0World, int par1, int par2, int par3)
    {
        return isRailBlock(par0World.getBlockId(par1, par2, par3));
    }

    /**
     * Return true if the parameter is a blockID for a valid rail block (current is rail, powered or detector).
     */
    public static final boolean isRailBlock(int par0)
    {
        return Block.blocksList[par0] instanceof BlockRailBase;
    }

    protected BlockRailBase(int par1, boolean par2)
    {
        super(par1, Material.circuits);
        this.isPowered = par2;
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
        this.setCreativeTab(CreativeTabs.tabTransport);
    }

    /**
     * Returns true if the block is power related rail.
     */
    public boolean isPowered()
    {
        return this.isPowered;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
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
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 9;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            this.refreshTrackShape(par1World, par2, par3, par4, true);

            if (this.isPowered)
            {
                this.onNeighborBlockChange(par1World, par2, par3, par4, this.blockID);
            }
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.isRemote)
        {
            int var6 = par1World.getBlockMetadata(par2, par3, par4);
            int var7 = var6;

            if (this.isPowered)
            {
                var7 = var6 & 7;
            }

            boolean var8 = false;

            if (!par1World.doesBlockHaveSolidTopSurface(par2, par3 - 1, par4))
            {
                var8 = true;
            }

            if (var7 == 2 && !par1World.doesBlockHaveSolidTopSurface(par2 + 1, par3, par4))
            {
                var8 = true;
            }

            if (var7 == 3 && !par1World.doesBlockHaveSolidTopSurface(par2 - 1, par3, par4))
            {
                var8 = true;
            }

            if (var7 == 4 && !par1World.doesBlockHaveSolidTopSurface(par2, par3, par4 - 1))
            {
                var8 = true;
            }

            if (var7 == 5 && !par1World.doesBlockHaveSolidTopSurface(par2, par3, par4 + 1))
            {
                var8 = true;
            }

            if (var8)
            {
                if (par1World.getBlockId(par2, par3, par4) != this.blockID)
                {
                    return;
                }

                this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
                par1World.setBlockToAir(par2, par3, par4);
            }
            else
            {
                this.func_94358_a(par1World, par2, par3, par4, var6, var7, par5);
            }
        }
    }

    protected void func_94358_a(World par1World, int par2, int par3, int par4, int par5, int par6, int par7) {}

    /**
     * Completely recalculates the track shape based on neighboring tracks
     */
    protected void refreshTrackShape(World par1World, int par2, int par3, int par4, boolean par5)
    {
        if (!par1World.isRemote)
        {
            (new BlockBaseRailLogic(this, par1World, par2, par3, par4)).func_94511_a(par1World.isBlockIndirectlyGettingPowered(par2, par3, par4), par5);
        }
    }

    /**
     * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
     * and stop pistons
     */
    public int getMobilityFlag()
    {
        return 0;
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        int var7 = par6;

        if (this.isPowered)
        {
            var7 = par6 & 7;
        }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);

        if (var7 == 2 || var7 == 3 || var7 == 4 || var7 == 5)
        {
            par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4, par5);
        }

        if (this.isPowered)
        {
            par1World.notifyBlocksOfNeighborChange(par2, par3, par4, par5);
            par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, par5);
        }
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        int var3 = var1;

        if (this.isPowered())
        {
            var3 = var1 & 7;
        }

        if (var3 == 0)
        {
            var3 = 1;
        }
        else if (var3 == 1)
        {
            var3 = 0;
        }
        else if (var3 != 2 && var3 != 3 && var3 != 4 && var3 != 5)
        {
            if (var3 == 6)
            {
                if (var2)
                {
                    var3 = 7;
                }
                else
                {
                    var3 = 9;
                }
            }
            else if (var3 == 7)
            {
                if (var2)
                {
                    var3 = 8;
                }
                else
                {
                    var3 = 6;
                }
            }
            else if (var3 == 8)
            {
                if (var2)
                {
                    var3 = 9;
                }
                else
                {
                    var3 = 7;
                }
            }
            else if (var3 == 9)
            {
                if (var2)
                {
                    var3 = 6;
                }
                else
                {
                    var3 = 8;
                }
            }
        }

        if (this.isPowered())
        {
            var1 = var1 & 8 | var3;
        }
        else
        {
            var1 = var3;
        }

        return var1;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 >= 2 && var5 <= 5 ? AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.625D, 1.0D) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return var1.renderBlockMinecartTrack(this, var2, var3, var4);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.m_currentBlockRenderer.ShouldSideBeRenderedBasedOnCurrentBounds(var2, var3, var4, var5);
    }
}

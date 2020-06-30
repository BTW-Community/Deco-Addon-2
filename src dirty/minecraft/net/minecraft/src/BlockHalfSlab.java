package net.minecraft.src;

import java.util.Random;

public abstract class BlockHalfSlab extends Block
{
    protected final boolean isDoubleSlab;

    public BlockHalfSlab(int par1, boolean par2, Material par3Material)
    {
        super(par1, par3Material);
        this.isDoubleSlab = par2;

        if (par2)
        {
            opaqueCubeLookup[par1] = true;
            this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        }
        else
        {
            this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
            useNeighborBrightness[par1] = true;
        }

        this.setLightOpacity(255);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return this.isDoubleSlab;
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        return this.isDoubleSlab ? par9 : (par5 != 0 && (par5 == 1 || (double)par7 <= 0.5D) ? par9 : par9 | 8);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return this.isDoubleSlab ? 2 : 1;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1 & 7;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return this.isDoubleSlab;
    }

    /**
     * Takes a block ID, returns true if it's the same as the ID for a stone or wooden single slab.
     */
    private static boolean isBlockSingleSlab(int par0)
    {
        return par0 == Block.stoneSingleSlab.blockID || par0 == Block.woodSingleSlab.blockID;
    }

    /**
     * Returns the slab block name with step type.
     */
    public abstract String getFullSlabName(int var1);

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        return super.getDamageValue(par1World, par2, par3, par4) & 7;
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return isBlockSingleSlab(this.blockID) ? this.blockID : (this.blockID == Block.stoneDoubleSlab.blockID ? Block.stoneSingleSlab.blockID : (this.blockID == Block.woodDoubleSlab.blockID ? Block.woodSingleSlab.blockID : Block.stoneSingleSlab.blockID));
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        AxisAlignedBB var5;

        if (this.isDoubleSlab)
        {
            var5 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        }
        else if (this.GetIsUpsideDown(var1, var2, var3, var4))
        {
            var5 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D);
        }
        else
        {
            var5 = AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
        }

        return var5;
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        if (!this.isDoubleSlab)
        {
            boolean var7 = this.GetIsUpsideDown(var1, var2, var3, var4);

            if (var5 == 0)
            {
                if (!var7)
                {
                    return true;
                }
            }
            else if (var5 == 1 && var7)
            {
                return true;
            }
        }

        return super.HasLargeCenterHardPointToFacing(var1, var2, var3, var4, var5, var6);
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return !this.isDoubleSlab && (var1.getBlockMetadata(var2, var3, var4) & 8) == 0 ? -0.5F : 0.0F;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanMobsSpawnOn(World var1, int var2, int var3, int var4)
    {
        return this.blockMaterial.GetMobsCanSpawnOn(var1.provider.dimensionId);
    }

    public float MobSpawnOnVerticalOffset(World var1, int var2, int var3, int var4)
    {
        return !this.isDoubleSlab && !this.GetIsUpsideDown(var1, var2, var3, var4) ? -0.5F : 0.0F;
    }

    public boolean GetIsUpsideDown(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsUpsideDown(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean GetIsUpsideDown(int var1)
    {
        return (var1 & 8) > 0;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (this.isDoubleSlab)
        {
            return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
        }
        else
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(par2, par3, par4, GetOppositeFacing(par5));
            boolean var7 = this.GetIsUpsideDown(par1IBlockAccess, var6.i, var6.j, var6.k);
            return par5 >= 2 ? FCClientUtilsRender.ShouldRenderNeighborHalfSlabSide(par1IBlockAccess, par2, par3, par4, par5, var7) : (par5 == 0 ? var7 || !par1IBlockAccess.isBlockOpaqueCube(par2, par3, par4) : !var7 || !par1IBlockAccess.isBlockOpaqueCube(par2, par3, par4));
        }
    }

    public boolean ShouldRenderNeighborHalfSlabSide(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return this.isDoubleSlab ? false : this.GetIsUpsideDown(var1, var2, var3, var4) != var6;
    }

    public boolean ShouldRenderNeighborFullFaceSide(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        if (this.isDoubleSlab)
        {
            return false;
        }
        else if (var5 < 2)
        {
            boolean var6 = this.GetIsUpsideDown(var1, var2, var3, var4);
            return var5 == 0 ? !var6 : var6;
        }
        else
        {
            return true;
        }
    }
}

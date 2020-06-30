package net.minecraft.src;

public abstract class FCBlockPlants extends Block
{
    protected FCBlockPlants(int var1, Material var2)
    {
        super(var1, var2);
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

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        super.breakBlock(var1, var2, var3, var4, var5, var6);
        Block var7 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

        if (var7 != null)
        {
            var7.NotifyOfPlantAboveRemoved(var1, var2, var3 - 1, var4, this);
        }
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        if (var5.IsAffectedByMovementModifiers() && var5.onGround)
        {
            var5.motionX *= 0.8D;
            var5.motionZ *= 0.8D;
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        return super.canPlaceBlockAt(var1, var2, var3, var4) && this.CanGrowOnBlock(var1, var2, var3 - 1, var4);
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World var1, int var2, int var3, int var4)
    {
        return this.CanGrowOnBlock(var1, var2, var3 - 1, var4);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (this.GetWeedsGrowthLevel(var1, var2, var3, var4) > 0)
        {
            if (!var1.isRemote)
            {
                this.RemoveWeeds(var1, var2, var3, var4);
                var1.playAuxSFX(2001, var2, var3, var4, Block.crops.blockID + 24576);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public int GetWeedsGrowthLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3 - 1, var4);
        Block var6 = Block.blocksList[var5];
        return var6 != null && var5 != this.blockID ? var6.GetWeedsGrowthLevel(var1, var2, var3 - 1, var4) : 0;
    }

    public void RemoveWeeds(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

        if (var5 != null)
        {
            var5.RemoveWeeds(var1, var2, var3 - 1, var4);
        }
    }

    public boolean AttemptToApplyFertilizerTo(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];
        return var5 != null ? var5.AttemptToApplyFertilizerTo(var1, var2, var3 - 1, var4) : false;
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1.0F;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4) && this.blockID != Block.waterlily.blockID;
    }

    public void ClientBreakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        FCUtilsWorld.ClearAnyGroundCoverOnBlock(var1, var2, var3, var4);
    }

    protected boolean CanGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3, var4)];
        return var5 != null && var5.CanWildVegetationGrowOnBlock(var1, var2, var3, var4);
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        var1.renderCrossedSquares(this, var2, var3, var4);
        FCBetterThanWolves.fcBlockWeeds.RenderWeeds(this, var1, var2, var3, var4);
        return true;
    }
}

package net.minecraft.src;

import java.util.Random;

public class FCBlockVine extends BlockVine
{
    public FCBlockVine(int var1)
    {
        super(var1);
        this.setHardness(0.2F);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.VINES);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("vine");
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var1.provider.dimensionId != 1 && var1.rand.nextInt(4) == 0)
        {
            byte var6 = 4;
            int var7 = 5;
            boolean var8 = false;
            int var9;
            int var10;
            int var11;
            label134:

            for (var9 = var2 - var6; var9 <= var2 + var6; ++var9)
            {
                for (var10 = var4 - var6; var10 <= var4 + var6; ++var10)
                {
                    for (var11 = var3 - 1; var11 <= var3 + 1; ++var11)
                    {
                        if (var1.getBlockId(var9, var11, var10) == this.blockID)
                        {
                            --var7;

                            if (var7 <= 0)
                            {
                                var8 = true;
                                break label134;
                            }
                        }
                    }
                }
            }

            var9 = var1.getBlockMetadata(var2, var3, var4);
            var10 = var1.rand.nextInt(6);
            var11 = Direction.facingToDirection[var10];
            int var12;
            int var13;

            if (var10 == 1 && var3 < 255 && var1.isAirBlock(var2, var3 + 1, var4))
            {
                if (var8)
                {
                    return;
                }

                var12 = var1.rand.nextInt(16) & var9;

                if (var12 > 0)
                {
                    for (var13 = 0; var13 <= 3; ++var13)
                    {
                        if (!this.canBePlacedOn(var1.getBlockId(var2 + Direction.offsetX[var13], var3 + 1, var4 + Direction.offsetZ[var13])))
                        {
                            var12 &= ~(1 << var13);
                        }
                    }

                    if (var12 > 0)
                    {
                        var1.setBlockAndMetadataWithNotify(var2, var3 + 1, var4, this.blockID, var12);
                    }
                }
            }
            else
            {
                int var14;

                if (var10 >= 2 && var10 <= 5 && (var9 & 1 << var11) == 0)
                {
                    if (var8)
                    {
                        return;
                    }

                    var12 = var1.getBlockId(var2 + Direction.offsetX[var11], var3, var4 + Direction.offsetZ[var11]);

                    if (!var1.isAirBlock(var2 + Direction.offsetX[var11], var3, var4 + Direction.offsetZ[var11]) && Block.blocksList[var12] != null)
                    {
                        if (Block.blocksList[var12].blockMaterial.isOpaque() && Block.blocksList[var12].renderAsNormalBlock())
                        {
                            var1.setBlockMetadataWithNotify(var2, var3, var4, var9 | 1 << var11);
                        }
                    }
                    else
                    {
                        var13 = var11 + 1 & 3;
                        var14 = var11 + 3 & 3;

                        if ((var9 & 1 << var13) != 0 && this.canBePlacedOn(var1.getBlockId(var2 + Direction.offsetX[var11] + Direction.offsetX[var13], var3, var4 + Direction.offsetZ[var11] + Direction.offsetZ[var13])))
                        {
                            var1.setBlockAndMetadataWithNotify(var2 + Direction.offsetX[var11], var3, var4 + Direction.offsetZ[var11], this.blockID, 1 << var13);
                        }
                        else if ((var9 & 1 << var14) != 0 && this.canBePlacedOn(var1.getBlockId(var2 + Direction.offsetX[var11] + Direction.offsetX[var14], var3, var4 + Direction.offsetZ[var11] + Direction.offsetZ[var14])))
                        {
                            var1.setBlockAndMetadataWithNotify(var2 + Direction.offsetX[var11], var3, var4 + Direction.offsetZ[var11], this.blockID, 1 << var14);
                        }
                        else if ((var9 & 1 << var13) != 0 && var1.isAirBlock(var2 + Direction.offsetX[var11] + Direction.offsetX[var13], var3, var4 + Direction.offsetZ[var11] + Direction.offsetZ[var13]) && this.canBePlacedOn(var1.getBlockId(var2 + Direction.offsetX[var13], var3, var4 + Direction.offsetZ[var13])))
                        {
                            var1.setBlockAndMetadataWithNotify(var2 + Direction.offsetX[var11] + Direction.offsetX[var13], var3, var4 + Direction.offsetZ[var11] + Direction.offsetZ[var13], this.blockID, 1 << (var11 + 2 & 3));
                        }
                        else if ((var9 & 1 << var14) != 0 && var1.isAirBlock(var2 + Direction.offsetX[var11] + Direction.offsetX[var14], var3, var4 + Direction.offsetZ[var11] + Direction.offsetZ[var14]) && this.canBePlacedOn(var1.getBlockId(var2 + Direction.offsetX[var14], var3, var4 + Direction.offsetZ[var14])))
                        {
                            var1.setBlockAndMetadataWithNotify(var2 + Direction.offsetX[var11] + Direction.offsetX[var14], var3, var4 + Direction.offsetZ[var11] + Direction.offsetZ[var14], this.blockID, 1 << (var11 + 2 & 3));
                        }
                        else if (this.canBePlacedOn(var1.getBlockId(var2 + Direction.offsetX[var11], var3 + 1, var4 + Direction.offsetZ[var11])))
                        {
                            var1.setBlockAndMetadataWithNotify(var2 + Direction.offsetX[var11], var3, var4 + Direction.offsetZ[var11], this.blockID, 0);
                        }
                    }
                }
                else if (var3 > 1)
                {
                    var12 = var1.getBlockId(var2, var3 - 1, var4);

                    if (var1.isAirBlock(var2, var3 - 1, var4))
                    {
                        var13 = var1.rand.nextInt(16) & var9;

                        if (var13 > 0)
                        {
                            var1.setBlockAndMetadataWithNotify(var2, var3 - 1, var4, this.blockID, var13);
                        }
                    }
                    else if (var12 == this.blockID)
                    {
                        var13 = var1.rand.nextInt(16) & var9;
                        var14 = var1.getBlockMetadata(var2, var3 - 1, var4);

                        if (var14 != (var14 | var13))
                        {
                            var1.setBlockMetadataWithNotify(var2, var3 - 1, var4, var14 | var13);
                        }
                    }
                }
            }
        }
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return Block.vine.blockID;
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 1;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        if (var5.IsAffectedByMovementModifiers() && var5.onGround)
        {
            boolean var6 = false;

            if (var5 instanceof EntityLiving)
            {
                var6 = ((EntityLiving)var5).isOnLadder();
            }

            if (!var6)
            {
                var5.motionX *= 0.8D;
                var5.motionZ *= 0.8D;
            }
        }
    }

    public boolean CanSpitWebReplaceBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean IsReplaceableVegetation(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean IsBlockClimbable(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return this.createStackedBlock(var5);
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess var1, int var2, int var3, int var4) {}

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender() {}

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        float var6 = 1.0F;
        float var7 = 1.0F;
        float var8 = 1.0F;
        float var9 = 0.0F;
        float var10 = 0.0F;
        float var11 = 0.0F;
        boolean var12 = var5 > 0;

        if ((var5 & 2) != 0)
        {
            var9 = Math.max(var9, 0.0625F);
            var6 = 0.0F;
            var7 = 0.0F;
            var10 = 1.0F;
            var8 = 0.0F;
            var11 = 1.0F;
            var12 = true;
        }

        if ((var5 & 8) != 0)
        {
            var6 = Math.min(var6, 0.9375F);
            var9 = 1.0F;
            var7 = 0.0F;
            var10 = 1.0F;
            var8 = 0.0F;
            var11 = 1.0F;
            var12 = true;
        }

        if ((var5 & 4) != 0)
        {
            var11 = Math.max(var11, 0.0625F);
            var8 = 0.0F;
            var6 = 0.0F;
            var9 = 1.0F;
            var7 = 0.0F;
            var10 = 1.0F;
            var12 = true;
        }

        if ((var5 & 1) != 0)
        {
            var8 = Math.min(var8, 0.9375F);
            var11 = 1.0F;
            var6 = 0.0F;
            var9 = 1.0F;
            var7 = 0.0F;
            var10 = 1.0F;
            var12 = true;
        }

        if (!var12 && this.canBePlacedOn(var1.getBlockId(var2, var3 + 1, var4)))
        {
            var7 = Math.min(var7, 0.9375F);
            var10 = 1.0F;
            var6 = 0.0F;
            var9 = 1.0F;
            var8 = 0.0F;
            var11 = 1.0F;
        }

        return AxisAlignedBB.getAABBPool().getAABB((double)var6, (double)var7, (double)var8, (double)var9, (double)var10, (double)var11);
    }

    private boolean canBePlacedOn(int var1)
    {
        if (var1 == 0)
        {
            return false;
        }
        else
        {
            Block var2 = Block.blocksList[var1];
            return var2.renderAsNormalBlock() && var2.blockMaterial.blocksMovement();
        }
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        return var1.renderBlockVine(this, var2, var3, var4);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return true;
    }
}

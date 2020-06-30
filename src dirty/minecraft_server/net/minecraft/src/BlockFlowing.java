package net.minecraft.src;

import java.util.Random;

public class BlockFlowing extends BlockFluid
{
    /**
     * Number of horizontally adjacent liquid source blocks. Diagonal doesn't count. Only source blocks of the same
     * liquid as the block using the field are counted.
     */
    int numAdjacentSources = 0;

    /**
     * Indicates whether the flow direction is optimal. Each array index corresponds to one of the four cardinal
     * directions.
     */
    boolean[] isOptimalFlowDirection = new boolean[4];

    /**
     * The estimated cost to flow in a given direction from the current point. Each array index corresponds to one of
     * the four cardinal directions.
     */
    int[] flowCost = new int[4];

    protected BlockFlowing(int par1, Material par2Material)
    {
        super(par1, par2Material);
    }

    /**
     * Updates the flow for the BlockFlowing object.
     */
    private void updateFlow(World par1World, int par2, int par3, int par4)
    {
        int var5 = par1World.getBlockMetadata(par2, par3, par4);
        par1World.setBlock(par2, par3, par4, this.blockID + 1, var5, 2);
    }

    public boolean getBlocksMovement(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return this.blockMaterial != Material.lava;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int var6 = this.getFlowDecay(par1World, par2, par3, par4);
        byte var7 = 1;

        if (this.blockMaterial == Material.lava && !par1World.provider.isHellWorld)
        {
            var7 = 2;
        }

        boolean var8 = true;
        int var9;

        if (var6 > 0)
        {
            byte var10 = -100;
            this.numAdjacentSources = 0;
            int var11 = this.getSmallestFlowDecay(par1World, par2 - 1, par3, par4, var10);
            var11 = this.getSmallestFlowDecay(par1World, par2 + 1, par3, par4, var11);
            var11 = this.getSmallestFlowDecay(par1World, par2, par3, par4 - 1, var11);
            var11 = this.getSmallestFlowDecay(par1World, par2, par3, par4 + 1, var11);
            var11 = this.GetSmallestFlowDecayFromCustomSources(par1World, par2, par3, par4, var11);
            int var12 = this.tickRate(par1World);
            var9 = var11 + var7;

            if (var9 >= 8 || var11 < 0)
            {
                var9 = -1;
            }

            if (this.getFlowDecay(par1World, par2, par3 + 1, par4) >= 0)
            {
                int var13 = this.getFlowDecay(par1World, par2, par3 + 1, par4);

                if (var13 >= 8)
                {
                    var9 = var13;
                }
                else
                {
                    var9 = var13 + 8;
                }
            }

            if (this.numAdjacentSources >= 2 && this.blockMaterial == Material.water)
            {
                if (par1World.getBlockMaterial(par2, par3 - 1, par4).isSolid())
                {
                    var9 = 0;
                }
                else if (par1World.getBlockMaterial(par2, par3 - 1, par4) == this.blockMaterial && par1World.getBlockMetadata(par2, par3 - 1, par4) == 0)
                {
                    var9 = 0;
                }
            }

            if (this.blockMaterial == Material.lava && var6 < 8 && var9 < 8 && var9 > var6)
            {
                var12 += var12 * (1 + par5Random.nextInt(4));
            }

            if (var9 == var6)
            {
                if (var8)
                {
                    this.updateFlow(par1World, par2, par3, par4);
                }
            }
            else
            {
                var6 = var9;

                if (var9 < 0)
                {
                    par1World.setBlockToAir(par2, par3, par4);
                }
                else
                {
                    par1World.setBlockMetadata(par2, par3, par4, var9, 2);
                    par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, var12);
                    par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this.blockID);
                }
            }
        }
        else
        {
            this.updateFlow(par1World, par2, par3, par4);
        }

        if (this.liquidCanDisplaceBlock(par1World, par2, par3 - 1, par4))
        {
            if (this.blockMaterial == Material.lava && par1World.getBlockMaterial(par2, par3 - 1, par4) == Material.water)
            {
                par1World.setBlock(par2, par3 - 1, par4, FCBetterThanWolves.fcBlockLavaPillow.blockID);
                this.triggerLavaMixEffects(par1World, par2, par3 - 1, par4);
                return;
            }

            if (var6 >= 8)
            {
                this.flowIntoBlock(par1World, par2, par3 - 1, par4, var6);
            }
            else
            {
                this.flowIntoBlock(par1World, par2, par3 - 1, par4, var6 + 8);
            }
        }
        else if (var6 >= 0 && (var6 == 0 || this.blockBlocksFlow(par1World, par2, par3 - 1, par4)))
        {
            boolean[] var14 = this.getOptimalFlowDirections(par1World, par2, par3, par4);
            var9 = var6 + var7;

            if (var6 >= 8)
            {
                var9 = 1;
            }

            if (var9 >= 8)
            {
                return;
            }

            if (var14[0])
            {
                this.flowIntoBlock(par1World, par2 - 1, par3, par4, var9);
            }

            if (var14[1])
            {
                this.flowIntoBlock(par1World, par2 + 1, par3, par4, var9);
            }

            if (var14[2])
            {
                this.flowIntoBlock(par1World, par2, par3, par4 - 1, var9);
            }

            if (var14[3])
            {
                this.flowIntoBlock(par1World, par2, par3, par4 + 1, var9);
            }
        }
    }

    /**
     * flowIntoBlock(World world, int x, int y, int z, int newFlowDecay) - Flows into the block at the coordinates and
     * changes the block type to the liquid.
     */
    private void flowIntoBlock(World par1World, int par2, int par3, int par4, int par5)
    {
        if (this.liquidCanDisplaceBlock(par1World, par2, par3, par4))
        {
            int var6 = par1World.getBlockId(par2, par3, par4);

            if (var6 > 0)
            {
                if (this.blockMaterial == Material.lava)
                {
                    this.triggerLavaMixEffects(par1World, par2, par3, par4);
                }
                else
                {
                    Block.blocksList[var6].OnFluidFlowIntoBlock(par1World, par2, par3, par4, this);
                }
            }

            par1World.setBlock(par2, par3, par4, this.blockID, par5, 3);
        }
    }

    /**
     * calculateFlowCost(World world, int x, int y, int z, int accumulatedCost, int previousDirectionOfFlow) - Used to
     * determine the path of least resistance, this method returns the lowest possible flow cost for the direction of
     * flow indicated. Each necessary horizontal flow adds to the flow cost.
     */
    private int calculateFlowCost(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        int var7 = 1000;

        for (int var8 = 0; var8 < 4; ++var8)
        {
            if ((var8 != 0 || par6 != 1) && (var8 != 1 || par6 != 0) && (var8 != 2 || par6 != 3) && (var8 != 3 || par6 != 2))
            {
                int var9 = par2;
                int var10 = par4;

                if (var8 == 0)
                {
                    var9 = par2 - 1;
                }

                if (var8 == 1)
                {
                    ++var9;
                }

                if (var8 == 2)
                {
                    var10 = par4 - 1;
                }

                if (var8 == 3)
                {
                    ++var10;
                }

                if (!this.blockBlocksFlow(par1World, var9, par3, var10) && (par1World.getBlockMaterial(var9, par3, var10) != this.blockMaterial || par1World.getBlockMetadata(var9, par3, var10) != 0))
                {
                    if (!this.blockBlocksFlow(par1World, var9, par3 - 1, var10))
                    {
                        return par5;
                    }

                    if (par5 < 4)
                    {
                        int var11 = this.calculateFlowCost(par1World, var9, par3, var10, par5 + 1, var8);

                        if (var11 < var7)
                        {
                            var7 = var11;
                        }
                    }
                }
            }
        }

        return var7;
    }

    /**
     * Returns a boolean array indicating which flow directions are optimal based on each direction's calculated flow
     * cost. Each array index corresponds to one of the four cardinal directions. A value of true indicates the
     * direction is optimal.
     */
    private boolean[] getOptimalFlowDirections(World par1World, int par2, int par3, int par4)
    {
        int var5;
        int var6;

        for (var5 = 0; var5 < 4; ++var5)
        {
            this.flowCost[var5] = 1000;
            var6 = par2;
            int var7 = par4;

            if (var5 == 0)
            {
                var6 = par2 - 1;
            }

            if (var5 == 1)
            {
                ++var6;
            }

            if (var5 == 2)
            {
                var7 = par4 - 1;
            }

            if (var5 == 3)
            {
                ++var7;
            }

            if (!this.blockBlocksFlow(par1World, var6, par3, var7) && (par1World.getBlockMaterial(var6, par3, var7) != this.blockMaterial || par1World.getBlockMetadata(var6, par3, var7) != 0))
            {
                if (this.blockBlocksFlow(par1World, var6, par3 - 1, var7))
                {
                    this.flowCost[var5] = this.calculateFlowCost(par1World, var6, par3, var7, 1, var5);
                }
                else
                {
                    this.flowCost[var5] = 0;
                }
            }
        }

        var5 = this.flowCost[0];

        for (var6 = 1; var6 < 4; ++var6)
        {
            if (this.flowCost[var6] < var5)
            {
                var5 = this.flowCost[var6];
            }
        }

        for (var6 = 0; var6 < 4; ++var6)
        {
            this.isOptimalFlowDirection[var6] = this.flowCost[var6] == var5;
        }

        return this.isOptimalFlowDirection;
    }

    /**
     * getSmallestFlowDecay(World world, intx, int y, int z, int currentSmallestFlowDecay) - Looks up the flow decay at
     * the coordinates given and returns the smaller of this value or the provided currentSmallestFlowDecay. If one
     * value is valid and the other isn't, the valid value will be returned. Valid values are >= 0. Flow decay is the
     * amount that a liquid has dissipated. 0 indicates a source block.
     */
    protected int getSmallestFlowDecay(World par1World, int par2, int par3, int par4, int par5)
    {
        int var6 = this.getFlowDecay(par1World, par2, par3, par4);

        if (var6 < 0)
        {
            return par5;
        }
        else
        {
            if (var6 == 0)
            {
                ++this.numAdjacentSources;
            }

            if (var6 >= 8)
            {
                var6 = 0;
            }

            return par5 >= 0 && var6 >= par5 ? par5 : var6;
        }
    }

    /**
     * Returns true if the block at the coordinates can be displaced by the liquid.
     */
    private boolean liquidCanDisplaceBlock(World par1World, int par2, int par3, int par4)
    {
        Material var5 = par1World.getBlockMaterial(par2, par3, par4);
        return var5 == this.blockMaterial ? false : (var5 == Material.lava ? false : !this.blockBlocksFlow(par1World, par2, par3, par4));
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);

        if (par1World.getBlockId(par2, par3, par4) == this.blockID)
        {
            par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, this.tickRate(par1World));
        }
    }

    public boolean func_82506_l()
    {
        return false;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        super.onNeighborBlockChange(var1, var2, var3, var4, var5);

        if (!var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    private int GetSmallestFlowDecayFromCustomSources(World var1, int var2, int var3, int var4, int var5)
    {
        if (var5 != 0)
        {
            for (int var6 = 0; var6 < 6; ++var6)
            {
                int var7 = FCUtilsWorld.IsValidSourceForFluidBlockToFacing(var1, var2, var3, var4, var6);

                if (var7 == 0)
                {
                    var5 = 0;
                    break;
                }

                if (var7 > 0 && (var5 < 0 || var7 < var5))
                {
                    var5 = var7;
                }
            }
        }

        return var5;
    }

    /**
     * Returns true if block at coords blocks fluids
     */
    public boolean blockBlocksFlow(World par1World, int par2, int par3, int par4)
    {
        Block var5 = blocksList[par1World.getBlockId(par2, par3, par4)];
        return var5 != null && var5.GetPreventsFluidFlow(par1World, par2, par3, par4, this);
    }
}

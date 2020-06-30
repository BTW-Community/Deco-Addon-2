package net.minecraft.src;

import java.util.Random;

public abstract class FCBlockLavaReceiver extends FCBlockMortarReceiver
{
    public static final int m_iLavaFillTickRate = 20;
    public static final int m_iLavaHardenTickRate = 2;

    public FCBlockLavaReceiver(int var1, Material var2)
    {
        super(var1, var2);
        this.setTickRandomly(true);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        if (!this.ScheduleUpdatesForLavaAndWaterContact(var1, var2, var3, var4))
        {
            super.onBlockAdded(var1, var2, var3, var4);
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.CheckForFall(var1, var2, var3, var4))
        {
            if (this.GetHasLavaInCracks(var1, var2, var3, var4))
            {
                if (this.HasWaterAbove(var1, var2, var3, var4))
                {
                    var1.playAuxSFX(2227, var2, var3, var4, 0);
                    var1.setBlockWithNotify(var2, var3, var4, Block.stone.blockID);
                    return;
                }
            }
            else if (this.HasLavaAbove(var1, var2, var3, var4))
            {
                this.SetHasLavaInCracks(var1, var2, var3, var4, true);
            }
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.GetHasLavaInCracks(var1, var2, var3, var4) && var1.IsRainingAtPos(var2, var3 + 1, var4))
        {
            var1.playAuxSFX(2227, var2, var3, var4, 0);
            var1.setBlockWithNotify(var2, var3, var4, Block.stone.blockID);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!this.ScheduleUpdatesForLavaAndWaterContact(var1, var2, var3, var4))
        {
            super.onNeighborBlockChange(var1, var2, var3, var4, var5);
        }
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return false;
    }

    public boolean GetIsBlockWarm(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetHasLavaInCracks(var1, var2, var3, var4);
    }

    protected boolean GetHasLavaInCracks(int var1)
    {
        return (var1 & 1) != 0;
    }

    protected boolean GetHasLavaInCracks(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetHasLavaInCracks(var1.getBlockMetadata(var2, var3, var4));
    }

    protected int SetHasLavaInCracks(int var1, boolean var2)
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

    protected void SetHasLavaInCracks(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.SetHasLavaInCracks(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    protected boolean HasLavaAbove(IBlockAccess var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3 + 1, var4)];
        return var5 != null && var5.blockMaterial == Material.lava;
    }

    protected boolean HasWaterAbove(IBlockAccess var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3 + 1, var4)];
        return var5 != null && var5.blockMaterial == Material.water;
    }

    protected boolean ScheduleUpdatesForLavaAndWaterContact(World var1, int var2, int var3, int var4)
    {
        if (this.GetHasLavaInCracks(var1, var2, var3, var4))
        {
            if (this.HasWaterAbove(var1, var2, var3, var4))
            {
                if (!var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
                {
                    var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, 2);
                }

                return true;
            }
        }
        else if (this.HasLavaAbove(var1, var2, var3, var4))
        {
            if (!var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
            {
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, 20);
            }

            return true;
        }

        return false;
    }

    protected abstract Icon GetLavaCracksOverlay();

    public void RenderBlockSecondPass(RenderBlocks var1, int var2, int var3, int var4, boolean var5)
    {
        if (var5 && this.GetHasLavaInCracks(var1.blockAccess, var2, var3, var4))
        {
            FCClientUtilsRender.RenderBlockFullBrightWithTexture(var1, var1.blockAccess, var2, var3, var4, this.GetLavaCracksOverlay());
        }
    }

    public void RenderFallingBlock(RenderBlocks var1, int var2, int var3, int var4, int var5)
    {
        var1.SetRenderAllFaces(true);
        var1.setRenderBounds(this.GetFixedBlockBoundsFromPool());
        var1.renderStandardBlock(this, var2, var3, var4);

        if (this.GetHasLavaInCracks(var5))
        {
            FCClientUtilsRender.RenderBlockFullBrightWithTexture(var1, var1.blockAccess, var2, var3, var4, this.GetLavaCracksOverlay());
        }

        var1.SetRenderAllFaces(false);
    }
}

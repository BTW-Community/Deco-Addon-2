package net.minecraft.src;

public class FCBlockRedstoneRepeater extends BlockRedstoneRepeater
{
    protected FCBlockRedstoneRepeater(int var1, boolean var2)
    {
        super(var1, var2);
        this.setHardness(0.0F);

        if (var2)
        {
            this.setLightValue(0.625F);
        }

        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("diode");
        this.disableStats();
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean RotateAroundJAxis(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        int var7 = this.RotateMetadataAroundJAxis(var6, var5);

        if (var7 != var6)
        {
            var1.setBlockMetadataWithNotify(var2, var3, var4, var7);
            this.onNeighborBlockChange(var1, var2, var3, var4, 0);
            int var8 = BlockDirectional.getDirection(var6);

            if (var8 == 1)
            {
                var1.notifyBlockOfNeighborChange(var2 + 1, var3, var4, this.blockID);
                var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this.blockID, 4);
            }
            else if (var8 == 3)
            {
                var1.notifyBlockOfNeighborChange(var2 - 1, var3, var4, this.blockID);
                var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this.blockID, 5);
            }
            else if (var8 == 2)
            {
                var1.notifyBlockOfNeighborChange(var2, var3, var4 + 1, this.blockID);
                var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this.blockID, 2);
            }
            else if (var8 == 0)
            {
                var1.notifyBlockOfNeighborChange(var2, var3, var4 - 1, this.blockID);
                var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this.blockID, 3);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        int var4 = var1 & 3;

        if (var2)
        {
            ++var4;

            if (var4 > 3)
            {
                var4 = 0;
            }
        }
        else
        {
            --var4;

            if (var4 < 0)
            {
                var4 = 3;
            }
        }

        return var1 & -4 | var4;
    }

    public void OnRemovedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        super.OnRemovedByBlockDispenser(var1, var2, var3, var4);

        if (this.isRepeaterPowered)
        {
            this.onBlockDestroyedByPlayer(var1, var2, var3, var4, var5);
        }
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        byte var10;

        if (var5 == 4)
        {
            var10 = 1;
        }
        else if (var5 == 2)
        {
            var10 = 2;
        }
        else if (var5 == 5)
        {
            var10 = 3;
        }
        else
        {
            var10 = 0;
        }

        return var10;
    }

    /**
     * Called after a block is placed
     */
    public void onPostBlockPlaced(World var1, int var2, int var3, int var4, int var5)
    {
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, 1);
    }

    public boolean TriggersBuddy()
    {
        return false;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return var1.renderBlockRepeater(this, var2, var3, var4);
    }
}

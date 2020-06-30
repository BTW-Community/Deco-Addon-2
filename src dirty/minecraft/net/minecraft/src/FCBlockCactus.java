package net.minecraft.src;

import java.util.Random;

public class FCBlockCactus extends BlockCactus
{
    protected FCBlockCactus(int var1)
    {
        super(var1);
        this.SetAxesEffectiveOn(true);
        this.setHardness(0.4F);
        this.SetBuoyant();
        this.setStepSound(soundClothFootstep);
        this.setUnlocalizedName("cactus");
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var1.provider.dimensionId != 1)
        {
            super.updateTick(var1, var2, var3, var4, var5);
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        if (!super.canPlaceBlockAt(var1, var2, var3, var4))
        {
            return false;
        }
        else
        {
            int var5 = var1.getBlockId(var2, var3 - 1, var4);
            return var5 == FCBetterThanWolves.fcPlanter.blockID || var5 == FCBetterThanWolves.fcBlockPlanterSoil.blockID;
        }
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World var1, int var2, int var3, int var4)
    {
        if (this.CanStayNextToBlock(var1, var2 - 1, var3, var4) && this.CanStayNextToBlock(var1, var2 + 1, var3, var4) && this.CanStayNextToBlock(var1, var2, var3, var4 - 1) && this.CanStayNextToBlock(var1, var2, var3, var4 + 1))
        {
            int var5 = var1.getBlockId(var2, var3 - 1, var4);
            Block var6 = Block.blocksList[var5];
            return var5 == this.blockID || var6 != null && var6.CanCactusGrowOnBlock(var1, var2, var3 - 1, var4);
        }
        else
        {
            return false;
        }
    }

    public void OnStruckByLightning(World var1, int var2, int var3, int var4)
    {
        var1.setBlockToAir(var2, var3, var4);
        var1.playAuxSFX(2282, var2, var3, var4, 0);

        if (var1.getBlockId(var2, var3 - 1, var4) == this.blockID)
        {
            this.OnStruckByLightning(var1, var2, var3 - 1, var4);
        }
    }

    protected boolean CanStayNextToBlock(World var1, int var2, int var3, int var4)
    {
        return !var1.getBlockMaterial(var2, var3, var4).isSolid() || var1.getBlockId(var2, var3, var4) == FCBetterThanWolves.fcBlockWeb.blockID;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        return var1.renderBlockCactus(this, var2, var3, var4);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return var5 != 0 && var5 != 1 ? true : !var1.isBlockOpaqueCube(var2, var3, var4) && var1.getBlockId(var2, var3, var4) != Block.cactus.blockID;
    }
}

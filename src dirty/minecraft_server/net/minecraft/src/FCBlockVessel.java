package net.minecraft.src;

import java.util.Random;

public abstract class FCBlockVessel extends BlockContainer implements FCIBlockMechanical
{
    private static final int m_iTickRate = 1;
    public static final double m_dCollisionBoxHeight = 1.0D;
    public static final float m_fModelHeight = 1.0F;
    public static final float m_fModelWidth = 0.875F;
    public static final float m_fModelHalfWidth = 0.4375F;
    public static final float m_fModelBandHeight = 0.75F;
    public static final float m_fModelBandHalfHeight = 0.375F;

    public FCBlockVessel(int var1, Material var2)
    {
        super(var1, var2);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 1;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
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
        return AxisAlignedBB.getAABBPool().getAABB((double)var2, (double)var3, (double)var4, (double)(var2 + 1), (double)var3 + 1.0D, (double)var4 + 1.0D);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        boolean var6 = this.GetMechanicallyPoweredFlag(var1, var2, var3, var4);
        boolean var7 = false;
        int var8 = 0;

        for (int var9 = 2; var9 <= 5; ++var9)
        {
            if (FCUtilsMechPower.IsBlockPoweredByAxleToSide(var1, var2, var3, var4, var9) || FCUtilsMechPower.IsBlockPoweredByHandCrankToSide(var1, var2, var3, var4, var9))
            {
                var7 = true;
                var8 = var9;
                this.BreakPowerSourceThatOpposePoweredFacing(var1, var2, var3, var4, var9);
            }
        }

        if (var6 != var7)
        {
            var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "step.gravel", 2.0F + var5.nextFloat() * 0.1F, 0.5F + var5.nextFloat() * 0.1F);
            this.SetMechanicallyPoweredFlag(var1, var2, var3, var4, var7);

            if (!var7)
            {
                this.SetTiltFacing(var1, var2, var3, var4, 0);
            }
            else
            {
                this.SetFacingBasedOnPoweredFromFacing(var1, var2, var3, var4, var8);
            }
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    public int GetFacing(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = 1;

        if (this.GetMechanicallyPoweredFlag(var1, var2, var3, var4))
        {
            var5 = this.GetTiltFacing(var1, var2, var3, var4);
        }

        return var5;
    }

    public void SetFacing(World var1, int var2, int var3, int var4, int var5) {}

    public int GetFacing(int var1)
    {
        return 0;
    }

    public int SetFacing(int var1, int var2)
    {
        return var1;
    }

    public boolean CanOutputMechanicalPower()
    {
        return false;
    }

    public boolean CanInputMechanicalPower()
    {
        return true;
    }

    public boolean IsInputtingMechanicalPower(World var1, int var2, int var3, int var4)
    {
        return FCUtilsMechPower.IsBlockPoweredByAxle(var1, var2, var3, var4, this);
    }

    public boolean CanInputAxlePowerToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return var5 >= 2;
    }

    public boolean IsOutputtingMechanicalPower(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public void Overpower(World var1, int var2, int var3, int var4) {}

    public int GetTiltFacing(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 3) + 2;
    }

    public void SetTiltFacing(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var5 - 2;

        if (var6 < 0)
        {
            var6 = 0;
        }

        int var7 = var1.getBlockMetadata(var2, var3, var4) & -4;
        var7 |= var6 & 3;
        var1.setBlockMetadataWithNotify(var2, var3, var4, var7);
        var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
    }

    public boolean GetMechanicallyPoweredFlag(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 4) > 0;
    }

    private void SetMechanicallyPoweredFlag(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -5;

        if (var5)
        {
            var6 |= 4;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    private void SetFacingBasedOnPoweredFromFacing(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = Block.RotateFacingAroundJ(var5, false);
        this.SetTiltFacing(var1, var2, var3, var4, var6);
    }

    private void BreakPowerSourceThatOpposePoweredFacing(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = Block.GetOppositeFacing(var5);

        for (int var7 = 2; var7 <= 5; ++var7)
        {
            if (var7 != var5)
            {
                boolean var8 = false;

                if (var7 == var6)
                {
                    if (FCUtilsMechPower.IsBlockPoweredByAxleToSide(var1, var2, var3, var4, var7))
                    {
                        var8 = true;
                    }
                }
                else if (FCUtilsMechPower.DoesBlockHaveFacingAxleToSide(var1, var2, var3, var4, var7))
                {
                    var8 = true;
                }

                FCUtilsBlockPos var9;

                if (var8)
                {
                    var9 = new FCUtilsBlockPos(var2, var3, var4);
                    var9.AddFacingAsOffset(var7);
                    ((FCBlockAxle)FCBetterThanWolves.fcBlockAxle).BreakAxle(var1, var9.i, var9.j, var9.k);
                }

                if (FCUtilsMechPower.IsBlockPoweredByHandCrankToSide(var1, var2, var3, var4, var7))
                {
                    var9 = new FCUtilsBlockPos(var2, var3, var4);
                    var9.AddFacingAsOffset(var7);
                    ((FCBlockHandCrank)FCBetterThanWolves.fcHandCrank).BreakCrankWithDrop(var1, var9.i, var9.j, var9.k);
                }
            }
        }
    }

    protected boolean IsOpenSideBlocked(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);
        return var1.isBlockNormalCube(var6.i, var6.j, var6.k);
    }
}

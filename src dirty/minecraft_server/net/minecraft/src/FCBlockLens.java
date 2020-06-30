package net.minecraft.src;

import java.util.Random;

public class FCBlockLens extends Block
{
    public static final int m_iLensMaxRange = 128;
    private static final int iLensTickRate = 1;
    private static final float fMinTriggerLightValue = 12.0F;

    public FCBlockLens(int var1)
    {
        super(var1, Material.iron);
        this.setHardness(3.5F);
        this.setTickRandomly(true);
        this.setStepSound(Block.soundStoneFootstep);
        this.setUnlocalizedName("fcBlockLens");
        this.setCreativeTab(CreativeTabs.tabRedstone);
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
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        return this.SetFacing(var9, Block.GetOppositeFacing(var5));
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
    {
        int var7 = FCUtilsMisc.ConvertPlacingEntityOrientationToBlockFacingReversed(var5);
        this.SetFacing(var1, var2, var3, var4, var7);
    }

    /**
     * Called after a block is placed
     */
    public void onPostBlockPlaced(World var1, int var2, int var3, int var4, int var5)
    {
        this.SetupBeam(var1, var2, var3, var4);
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        this.RemoveBeam(var1, var2, var3, var4);
        super.breakBlock(var1, var2, var3, var4, var5, var6);
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

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        boolean var7 = this.IsDirectlyFacingBlockDetector(var1, var2, var3, var4);

        if (!var7)
        {
            boolean var8 = this.CheckForInputLight(var1, var2, var3, var4);

            if (this.IsLit(var1, var2, var3, var4) != var8)
            {
                this.SetLit(var1, var2, var3, var4, var8);

                if (var8)
                {
                    this.TurnBeamOn(var1, var2, var3, var4);
                }
                else
                {
                    this.TurnBeamOff(var1, var2, var3, var4);
                }
            }

            FCUtilsBlockPos var9 = new FCUtilsBlockPos(var2, var3, var4);
            var9.AddFacingAsOffset(var6);

            if (var1.getBlockId(var9.i, var9.j, var9.k) == 0)
            {
                FCBlockDetectorLogic var10 = (FCBlockDetectorLogic)((FCBlockDetectorLogic)FCBetterThanWolves.fcBlockDetectorLogic);
                var10.PropagateBeamsThroughBlock(var1, var9.i, var9.j, var9.k);
            }
        }
        else
        {
            FCUtilsBlockPos var12 = new FCUtilsBlockPos(var2, var3, var4);
            var12.AddFacingAsOffset(Block.GetOppositeFacing(var6));
            int var13 = var1.getBlockLightValue(var12.i, var12.j, var12.k);
            boolean var11 = var13 >= 8;

            if (this.IsLit(var1, var2, var3, var4) != var11)
            {
                this.SetLit(var1, var2, var3, var4, var11);
            }

            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    /**
     * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
     * and stop pistons
     */
    public int getMobilityFlag()
    {
        return 2;
    }

    public int GetFacing(int var1)
    {
        return (var1 & -2) >> 1;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 = var1 & 1 | var2 << 1;
        return var1;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        var6 = Block.CycleFacing(var6, var5);
        this.SetFacing(var1, var2, var3, var4, var6);
        var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
        return true;
    }

    public boolean IsLit(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 1) > 0;
    }

    public void SetLit(World var1, int var2, int var3, int var4, boolean var5)
    {
        if (var5 != this.IsLit(var1, var2, var3, var4))
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);

            if (var5)
            {
                var6 |= 1;
            }
            else
            {
                var6 &= -2;
            }

            var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
            var1.notifyBlocksOfNeighborChange(var2, var3, var4, this.blockID);
            var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
        }
    }

    private boolean CheckForInputLight(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        int var6 = Block.GetOppositeFacing(var5);
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);
        var7.AddFacingAsOffset(var6);
        int var8 = var1.getBlockId(var7.i, var7.j, var7.k);

        if (var8 > 0)
        {
            if ((float)lightValue[var8] > 12.0F)
            {
                if (var8 != FCBetterThanWolves.fcBlockDetectorGlowingLogic.blockID)
                {
                    return true;
                }

                FCBlockDetectorLogic var9 = (FCBlockDetectorLogic)((FCBlockDetectorLogic)FCBetterThanWolves.fcBlockDetectorGlowingLogic);

                if (var9.ShouldBeProjectingToFacing(var1, var7.i, var7.j, var7.k, var5))
                {
                    return true;
                }
            }
            else if (var8 == this.blockID && this.IsLit(var1, var7.i, var7.j, var7.k) && this.GetFacing(var1, var7.i, var7.j, var7.k) == var5)
            {
                return true;
            }
        }

        return false;
    }

    public void SetupBeam(World var1, int var2, int var3, int var4)
    {
        FCBlockDetectorLogic var5 = (FCBlockDetectorLogic)((FCBlockDetectorLogic)FCBetterThanWolves.fcBlockDetectorLogic);
        var5.CreateLensBeamFromBlock(var1, var2, var3, var4, this.GetFacing(var1, var2, var3, var4), 128);
    }

    private void RemoveBeam(World var1, int var2, int var3, int var4)
    {
        FCBlockDetectorLogic var5 = (FCBlockDetectorLogic)((FCBlockDetectorLogic)FCBetterThanWolves.fcBlockDetectorLogic);
        var5.RemoveLensBeamFromBlock(var1, var2, var3, var4, this.GetFacing(var1, var2, var3, var4), 128);
    }

    private void TurnBeamOn(World var1, int var2, int var3, int var4)
    {
        FCBlockDetectorLogic var5 = (FCBlockDetectorLogic)((FCBlockDetectorLogic)FCBetterThanWolves.fcBlockDetectorLogic);
        var5.TurnBeamOnFromBlock(var1, var2, var3, var4, this.GetFacing(var1, var2, var3, var4), 128);
    }

    private void TurnBeamOff(World var1, int var2, int var3, int var4)
    {
        FCBlockDetectorLogic var5 = (FCBlockDetectorLogic)((FCBlockDetectorLogic)FCBetterThanWolves.fcBlockDetectorLogic);
        var5.TurnBeamOffFromBlock(var1, var2, var3, var4, this.GetFacing(var1, var2, var3, var4), 128);
    }

    private boolean IsDirectlyFacingBlockDetector(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4);
        var6.AddFacingAsOffset(var5);
        int var7 = var1.getBlockId(var6.i, var6.j, var6.k);

        if (var7 == FCBetterThanWolves.fcBlockDetector.blockID)
        {
            int var8 = ((FCBlockDetectorBlock)((FCBlockDetectorBlock)FCBetterThanWolves.fcBlockDetector)).GetFacing(var1, var6.i, var6.j, var6.k);

            if (var8 == Block.GetOppositeFacing(var5))
            {
                return true;
            }
        }

        return false;
    }
}

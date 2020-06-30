package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockDetectorLogic extends Block
{
    private static final int iDetectorLogicTickRate = 5;
    public static final boolean bLogicDebugDisplay = false;

    public FCBlockDetectorLogic(int var1)
    {
        super(var1, Material.air);
        this.setTickRandomly(true);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 5;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
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
     * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
     * and stop pistons
     */
    public int getMobilityFlag()
    {
        return 1;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        if (!FCBetterThanWolves.bIsLensBeamBeingRemoved && (!this.IsDetectorLogicFlagOn(var1, var2, var3, var4) || this.IsIntersectionPointFlagOn(var1, var2, var3, var4)))
        {
            for (int var7 = 0; var7 <= 5; ++var7)
            {
                int var8 = this.GetRangeToValidLensSourceToFacing(var1, var2, var3, var4, var7);

                if (var8 > 0)
                {
                    int var9 = 128 - var8;

                    if (var9 > 0)
                    {
                        this.RemoveLensBeamFromBlock(var1, var2, var3, var4, Block.GetOppositeFacing(var7), var9);
                    }
                }
            }
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return 0;
    }

    /**
     * Returns whether this block is collideable based on the arguments passed in Args: blockMetaData, unknownFlag
     */
    public boolean canCollideCheck(int var1, boolean var2)
    {
        return false;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 0;
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
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (this.IsDetectorLogicFlagOn(var1, var2, var3, var4))
        {
            if (!this.CheckForNeighboringDetector(var1, var2, var3, var4))
            {
                if (this.IsIntersectionPointFlagOn(var1, var2, var3, var4))
                {
                    this.SetIsDetectorLogicFlag(var1, var2, var3, var4, false);

                    if (!this.HasMultipleValidLensSources(var1, var2, var3, var4))
                    {
                        this.SetIsIntersectionPointFlag(var1, var2, var3, var4, false);
                    }
                }
                else
                {
                    this.RemoveSelf(var1, var2, var3, var4);
                }
            }
            else
            {
                this.NotifyNeighboringDetectorBlocksOfChange(var1, var2, var3, var4);
            }
        }

        if ((!this.IsDetectorLogicFlagOn(var1, var2, var3, var4) || this.IsIntersectionPointFlagOn(var1, var2, var3, var4)) && !var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, var1.getBlockId(var2, var3, var4), this.tickRate(var1));
        }

        if (this.IsLitFlagOn(var1, var2, var3, var4) && this.IsBlockGlowing(var1, var2, var3, var4))
        {
            var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
        }
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        if (!var1.isRemote && this.IsEntityWithinBounds(var1, var2, var3, var4))
        {
            boolean var6 = this.IsEntityCollidingFlagOn(var1, var2, var3, var4);

            if (!var6)
            {
                this.ChangeStateToRegisterEntityCollision(var1, var2, var3, var4);
                var1.scheduleBlockUpdate(var2, var3, var4, var1.getBlockId(var2, var3, var4), this.tickRate(var1));
            }
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        boolean var6 = this.IsEntityCollidingFlagOn(var1, var2, var3, var4);
        boolean var7 = this.IsEntityWithinBounds(var1, var2, var3, var4);

        if (var7)
        {
            if (!var6)
            {
                this.ChangeStateToRegisterEntityCollision(var1, var2, var3, var4);
            }

            var1.scheduleBlockUpdate(var2, var3, var4, var1.getBlockId(var2, var3, var4), this.tickRate(var1));
        }
        else if (var6)
        {
            this.ChangeStateToClearEntityCollision(var1, var2, var3, var4);
        }

        this.FullyValidateBlock(var1, var2, var3, var4);
    }

    public boolean IsAirBlock()
    {
        return true;
    }

    public boolean TriggersBuddy()
    {
        return false;
    }

    protected void RemoveSelf(World var1, int var2, int var3, int var4)
    {
        var1.setBlock(var2, var3, var4, 0, 0, 0);
    }

    public boolean IsEntityCollidingFlagOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return (var5 & 1) > 0;
    }

    public void SetEntityCollidingFlag(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -2;

        if (var5)
        {
            var6 |= 1;
        }

        var1.setBlockMetadataWithNotifyNoClient(var2, var3, var4, var6);
    }

    public boolean IsDetectorLogicFlagOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return (var5 & 2) > 0;
    }

    public void SetIsDetectorLogicFlag(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -3;

        if (var5)
        {
            var6 |= 2;
        }

        var1.setBlockMetadata(var2, var3, var4, var6);
    }

    public boolean IsIntersectionPointFlagOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return (var5 & 4) > 0;
    }

    public void SetIsIntersectionPointFlag(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -5;

        if (var5)
        {
            var6 |= 4;
        }

        var1.setBlockMetadata(var2, var3, var4, var6);
    }

    public boolean IsLitFlagOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return (var5 & 8) > 0;
    }

    public void SetIsLitFlag(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -9;

        if (var5)
        {
            var6 |= 8;
        }

        var1.setBlockMetadata(var2, var3, var4, var6);
    }

    public boolean IsBlockGlowing(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);
        return var5 == FCBetterThanWolves.fcBlockDetectorGlowingLogic.blockID;
    }

    public void SetBlockAsGlowing(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        FCBetterThanWolves.bIsLensBeamBeingRemoved = true;
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDetectorGlowingLogic.blockID, var5);
        FCBetterThanWolves.bIsLensBeamBeingRemoved = false;

        if (this.IsEntityCollidingFlagOn(var1, var2, var3, var4))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, var1.getBlockId(var2, var3, var4), this.tickRate(var1));
        }
    }

    public void SetBlockAsNotGlowing(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        FCBetterThanWolves.bIsLensBeamBeingRemoved = true;
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockDetectorLogic.blockID, var5);
        FCBetterThanWolves.bIsLensBeamBeingRemoved = false;

        if (this.IsEntityCollidingFlagOn(var1, var2, var3, var4))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, var1.getBlockId(var2, var3, var4), this.tickRate(var1));
        }
    }

    private boolean IsEntityWithinBounds(World var1, int var2, int var3, int var4)
    {
        List var5 = var1.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getAABBPool().getAABB((double)var2, (double)var3, (double)var4, (double)(var2 + 1), (double)(var3 + 1), (double)(var4 + 1)));

        if (var5 != null && var5.size() > 0)
        {
            for (int var6 = 0; var6 < var5.size(); ++var6)
            {
                Entity var7 = (Entity)var5.get(var6);

                if (!(var7 instanceof FCEntityBlockLiftedByPlatform))
                {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean CheckForNeighboringDetector(World var1, int var2, int var3, int var4)
    {
        for (int var5 = 0; var5 <= 5; ++var5)
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4);
            var6.AddFacingAsOffset(var5);

            if (var1.getBlockId(var6.i, var6.j, var6.k) == FCBetterThanWolves.fcBlockDetector.blockID && ((FCBlockDetectorBlock)((FCBlockDetectorBlock)FCBetterThanWolves.fcBlockDetector)).GetFacing(var1, var6.i, var6.j, var6.k) == Block.GetOppositeFacing(var5))
            {
                return true;
            }
        }

        return false;
    }

    public void NotifyNeighboringDetectorBlocksOfChange(World var1, int var2, int var3, int var4)
    {
        for (int var5 = 0; var5 <= 5; ++var5)
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4);
            var6.AddFacingAsOffset(var5);
            int var7 = var1.getBlockId(var6.i, var6.j, var6.k);

            if (var7 == FCBetterThanWolves.fcBlockDetector.blockID)
            {
                Block.blocksList[var7].onNeighborBlockChange(var1, var6.i, var6.j, var6.k, var1.getBlockId(var2, var3, var4));
            }
        }
    }

    public boolean HasValidLensSource(World var1, int var2, int var3, int var4)
    {
        for (int var5 = 0; var5 <= 5; ++var5)
        {
            if (this.HasValidLensSourceToFacing(var1, var2, var3, var4, var5))
            {
                return true;
            }
        }

        return false;
    }

    public boolean HasValidLensSourceIgnoreFacing(World var1, int var2, int var3, int var4, int var5)
    {
        for (int var6 = 0; var6 <= 5; ++var6)
        {
            if (var6 != var5 && this.HasValidLensSourceToFacing(var1, var2, var3, var4, var6))
            {
                return true;
            }
        }

        return false;
    }

    public boolean HasMultipleValidLensSources(World var1, int var2, int var3, int var4)
    {
        int var5 = 0;

        for (int var6 = 0; var6 <= 5; ++var6)
        {
            if (this.HasValidLensSourceToFacing(var1, var2, var3, var4, var6))
            {
                ++var5;

                if (var5 > 1)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean HasMultipleValidLensSourcesIgnoreFacing(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = 0;

        for (int var7 = 0; var7 <= 5; ++var7)
        {
            if (var7 != var5 && this.HasValidLensSourceToFacing(var1, var2, var3, var4, var7))
            {
                ++var6;

                if (var6 > 1)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public int CountValidLensSources(World var1, int var2, int var3, int var4)
    {
        int var5 = 0;

        for (int var6 = 0; var6 <= 5; ++var6)
        {
            if (this.HasValidLensSourceToFacing(var1, var2, var3, var4, var6))
            {
                ++var5;
            }
        }

        return var5;
    }

    public int CountValidLensSourcesIgnoreFacing(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = 0;

        for (int var7 = 0; var7 <= 5; ++var7)
        {
            if (var7 != var5 && this.HasValidLensSourceToFacing(var1, var2, var3, var4, var7))
            {
                ++var6;
            }
        }

        return var6;
    }

    public boolean HasValidLensSourceToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return this.GetRangeToValidLensSourceToFacing(var1, var2, var3, var4, var5) > 0;
    }

    public int GetRangeToValidLensSourceToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4);

        for (int var7 = 1; var7 <= 128; ++var7)
        {
            var6.AddFacingAsOffset(var5);
            int var8 = var1.getBlockId(var6.i, var6.j, var6.k);

            if (var8 == FCBetterThanWolves.fcLens.blockID)
            {
                FCBlockLens var9 = (FCBlockLens)((FCBlockLens)FCBetterThanWolves.fcLens);

                if (var9.GetFacing(var1, var6.i, var6.j, var6.k) == Block.GetOppositeFacing(var5))
                {
                    return var7;
                }

                return 0;
            }

            if (!IsLogicBlock(var8))
            {
                return 0;
            }
        }

        return 0;
    }

    public boolean VerifyLitByLens(World var1, int var2, int var3, int var4)
    {
        for (int var5 = 0; var5 <= 5; ++var5)
        {
            if (this.HasValidLitLensSourceToFacing(var1, var2, var3, var4, var5))
            {
                return true;
            }
        }

        return false;
    }

    public boolean VerifyLitByLensIgnoreFacing(World var1, int var2, int var3, int var4, int var5)
    {
        for (int var6 = 0; var6 <= 5; ++var6)
        {
            if (var6 != var5 && this.HasValidLitLensSourceToFacing(var1, var2, var3, var4, var6))
            {
                return true;
            }
        }

        return false;
    }

    public boolean HasValidLitLensSourceToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.GetRangeToValidLitLensSourceToFacing(var1, var2, var3, var4, var5) > 0;
    }

    public int GetRangeToValidLitLensSourceToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4);

        for (int var7 = 1; var7 <= 128; ++var7)
        {
            var6.AddFacingAsOffset(var5);
            int var8 = var1.getBlockId(var6.i, var6.j, var6.k);

            if (var8 == FCBetterThanWolves.fcLens.blockID)
            {
                FCBlockLens var9 = (FCBlockLens)((FCBlockLens)FCBetterThanWolves.fcLens);

                if (var9.GetFacing(var1, var6.i, var6.j, var6.k) == Block.GetOppositeFacing(var5) && var9.IsLit(var1, var6.i, var6.j, var6.k))
                {
                    return var7;
                }

                return 0;
            }

            if (!IsLogicBlock(var8))
            {
                return 0;
            }

            if (!this.IsLitFlagOn(var1, var6.i, var6.j, var6.k) || this.IsEntityCollidingFlagOn(var1, var6.i, var6.j, var6.k))
            {
                return 0;
            }
        }

        return 0;
    }

    public void CreateLensBeamFromBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);

        for (int var8 = 1; var8 <= var6; ++var8)
        {
            var7.AddFacingAsOffset(var5);
            int var9 = var1.getBlockId(var7.i, var7.j, var7.k);

            if (var9 == 0)
            {
                if (!var1.setBlock(var7.i, var7.j, var7.k, FCBetterThanWolves.fcBlockDetectorLogic.blockID, 0, 0))
                {
                    break;
                }
            }
            else
            {
                if (!IsLogicBlock(var9))
                {
                    break;
                }

                this.SetIsIntersectionPointFlag(var1, var7.i, var7.j, var7.k, true);
            }
        }
    }

    public void RemoveLensBeamFromBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        FCBetterThanWolves.bIsLensBeamBeingRemoved = true;
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);
        int var8 = Block.GetOppositeFacing(var5);

        for (int var9 = 1; var9 <= 128; ++var9)
        {
            var7.AddFacingAsOffset(var5);

            if (!IsLogicBlock(var1, var7.i, var7.j, var7.k))
            {
                break;
            }

            if (this.IsIntersectionPointFlagOn(var1, var7.i, var7.j, var7.k))
            {
                if (this.IsDetectorLogicFlagOn(var1, var7.i, var7.j, var7.k))
                {
                    if (!this.HasValidLensSourceIgnoreFacing(var1, var7.i, var7.j, var7.k, var8))
                    {
                        this.SetIsIntersectionPointFlag(var1, var7.i, var7.j, var7.k, false);
                    }
                }
                else if (!this.HasMultipleValidLensSourcesIgnoreFacing(var1, var7.i, var7.j, var7.k, var8))
                {
                    this.SetIsIntersectionPointFlag(var1, var7.i, var7.j, var7.k, false);
                }

                if (this.IsLitFlagOn(var1, var7.i, var7.j, var7.k))
                {
                    if (!this.VerifyLitByLensIgnoreFacing(var1, var7.i, var7.j, var7.k, var8))
                    {
                        this.UnlightBlock(var1, var7.i, var7.j, var7.k);
                    }
                    else if (this.IsBlockGlowing(var1, var7.i, var7.j, var7.k) && !this.ShouldBeGlowing(var1, var7.i, var7.j, var7.k))
                    {
                        this.SetBlockAsNotGlowing(var1, var7.i, var7.j, var7.k);
                    }
                }
            }
            else if (!this.IsBlockGlowing(var1, var7.i, var7.j, var7.k))
            {
                if (!var1.setBlock(var7.i, var7.j, var7.k, 0, 0, 0))
                {
                    break;
                }
            }
            else if (!var1.setBlockWithNotify(var7.i, var7.j, var7.k, 0))
            {
                break;
            }
        }

        FCBetterThanWolves.bIsLensBeamBeingRemoved = false;
    }

    public void LightBlock(World var1, int var2, int var3, int var4)
    {
        this.SetIsLitFlag(var1, var2, var3, var4, true);

        if (this.IsDetectorLogicFlagOn(var1, var2, var3, var4))
        {
            this.NotifyNeighboringDetectorBlocksOfChange(var1, var2, var3, var4);
        }
    }

    public void UnlightBlock(World var1, int var2, int var3, int var4)
    {
        this.SetIsLitFlag(var1, var2, var3, var4, false);

        if (this.IsBlockGlowing(var1, var2, var3, var4))
        {
            this.SetBlockAsNotGlowing(var1, var2, var3, var4);
        }

        if (this.IsDetectorLogicFlagOn(var1, var2, var3, var4))
        {
            this.NotifyNeighboringDetectorBlocksOfChange(var1, var2, var3, var4);
        }
    }

    public void ChangeStateToRegisterEntityCollision(World var1, int var2, int var3, int var4)
    {
        this.SetEntityCollidingFlag(var1, var2, var3, var4, true);

        if (this.IsLitFlagOn(var1, var2, var3, var4))
        {
            if (!this.IsBlockGlowing(var1, var2, var3, var4))
            {
                this.SetBlockAsGlowing(var1, var2, var3, var4);
            }
            else
            {
                var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
            }

            for (int var5 = 0; var5 <= 5; ++var5)
            {
                int var6 = this.GetRangeToValidLitLensSourceToFacing(var1, var2, var3, var4, var5);

                if (var6 > 0)
                {
                    int var7 = 128 - var6;

                    if (var7 > 0)
                    {
                        this.TurnBeamOffFromBlock(var1, var2, var3, var4, Block.GetOppositeFacing(var5), var7);
                    }
                }
            }
        }
    }

    public void ChangeStateToClearEntityCollision(World var1, int var2, int var3, int var4)
    {
        this.SetEntityCollidingFlag(var1, var2, var3, var4, false);

        for (int var5 = 0; var5 <= 5; ++var5)
        {
            int var6 = this.GetRangeToValidLitLensSourceToFacing(var1, var2, var3, var4, var5);

            if (var6 > 0)
            {
                int var7 = 128 - var6;

                if (var7 > 0)
                {
                    this.TurnBeamOnFromBlock(var1, var2, var3, var4, Block.GetOppositeFacing(var5), var7);
                }
            }
        }

        if (this.IsLitFlagOn(var1, var2, var3, var4) && this.IsBlockGlowing(var1, var2, var3, var4))
        {
            if (!this.ShouldBeGlowing(var1, var2, var3, var4))
            {
                this.SetBlockAsNotGlowing(var1, var2, var3, var4);
            }
            else
            {
                var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
            }
        }
    }

    public void TurnBeamOnFromBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);

        for (int var8 = 1; var8 <= var6; ++var8)
        {
            var7.AddFacingAsOffset(var5);
            int var9 = var1.getBlockId(var7.i, var7.j, var7.k);

            if (!IsLogicBlock(var9))
            {
                if (!var1.isAirBlock(var7.i, var7.j, var7.k))
                {
                    FCUtilsBlockPos var10 = new FCUtilsBlockPos(var7.i, var7.j, var7.k);
                    var10.AddFacingAsOffset(Block.GetOppositeFacing(var5));

                    if (IsLogicBlock(var1, var10.i, var10.j, var10.k) && !this.IsBlockGlowing(var1, var10.i, var10.j, var10.k))
                    {
                        this.SetBlockAsGlowing(var1, var10.i, var10.j, var10.k);
                    }
                }

                break;
            }

            this.LightBlock(var1, var7.i, var7.j, var7.k);

            if (this.IsEntityCollidingFlagOn(var1, var7.i, var7.j, var7.k))
            {
                if (IsLogicBlock(var1, var7.i, var7.j, var7.k) && !this.IsBlockGlowing(var1, var7.i, var7.j, var7.k))
                {
                    this.SetBlockAsGlowing(var1, var7.i, var7.j, var7.k);
                }

                break;
            }
        }
    }

    public void TurnBeamOffFromBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);
        int var8 = Block.GetOppositeFacing(var5);

        for (int var9 = 1; var9 <= var6; ++var9)
        {
            var7.AddFacingAsOffset(var5);
            int var10 = var1.getBlockId(var7.i, var7.j, var7.k);

            if (!IsLogicBlock(var10))
            {
                break;
            }

            if (this.IsIntersectionPointFlagOn(var1, var7.i, var7.j, var7.k))
            {
                if (!this.VerifyLitByLensIgnoreFacing(var1, var7.i, var7.j, var7.k, var8))
                {
                    this.UnlightBlock(var1, var7.i, var7.j, var7.k);
                }
                else if (this.IsBlockGlowing(var1, var7.i, var7.j, var7.k) && !this.ShouldBeGlowing(var1, var7.i, var7.j, var7.k))
                {
                    this.SetBlockAsNotGlowing(var1, var7.i, var7.j, var7.k);
                }
            }
            else
            {
                this.UnlightBlock(var1, var7.i, var7.j, var7.k);
            }

            if (this.IsEntityCollidingFlagOn(var1, var7.i, var7.j, var7.k))
            {
                break;
            }
        }
    }

    protected boolean ShouldBeProjectingToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        if (this.IsEntityCollidingFlagOn(var1, var2, var3, var4))
        {
            return false;
        }
        else
        {
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);
            return var1.isBlockNormalCube(var6.i, var6.j, var6.k) && this.HasValidLitLensSourceToFacing(var1, var2, var3, var4, Block.GetOppositeFacing(var5));
        }
    }

    protected boolean ShouldBeGlowingToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4);
        var6.AddFacingAsOffset(var5);
        return !var1.isAirBlock(var6.i, var6.j, var6.k) && this.HasValidLitLensSourceToFacing(var1, var2, var3, var4, Block.GetOppositeFacing(var5));
    }

    private boolean ShouldBeGlowing(World var1, int var2, int var3, int var4)
    {
        if (this.IsEntityCollidingFlagOn(var1, var2, var3, var4))
        {
            return true;
        }
        else
        {
            for (int var5 = 0; var5 <= 5; ++var5)
            {
                if (this.ShouldBeGlowingToFacing(var1, var2, var3, var4, var5))
                {
                    return true;
                }
            }

            return false;
        }
    }

    static boolean IsLogicBlock(IBlockAccess var0, int var1, int var2, int var3)
    {
        int var4 = var0.getBlockId(var1, var2, var3);
        return IsLogicBlock(var4);
    }

    static boolean IsLogicBlock(int var0)
    {
        return var0 == FCBetterThanWolves.fcBlockDetectorLogic.blockID || var0 == FCBetterThanWolves.fcBlockDetectorGlowingLogic.blockID;
    }

    void PropagateBeamsThroughBlock(World var1, int var2, int var3, int var4)
    {
        boolean var5 = false;
        int var6 = 0;

        if (var1.setBlock(var2, var3, var4, FCBetterThanWolves.fcBlockDetectorLogic.blockID, 0, 0))
        {
            for (int var7 = 0; var7 <= 5; ++var7)
            {
                int var8 = this.GetRangeToValidLensSourceToFacing(var1, var2, var3, var4, var7);

                if (var8 > 0)
                {
                    ++var6;
                    int var9 = 128 - var8;

                    if (var9 > 0)
                    {
                        int var10 = Block.GetOppositeFacing(var7);
                        this.CreateLensBeamFromBlock(var1, var2, var3, var4, var10, var9);

                        if (this.HasValidLitLensSourceToFacing(var1, var2, var3, var4, var7))
                        {
                            this.TurnBeamOnFromBlock(var1, var2, var3, var4, var10, var9);
                            var5 = true;
                        }
                    }
                    else if (this.HasValidLitLensSourceToFacing(var1, var2, var3, var4, var7))
                    {
                        var5 = true;
                    }
                }
            }

            if (var5)
            {
                this.LightBlock(var1, var2, var3, var4);

                if (this.ShouldBeGlowing(var1, var2, var3, var4))
                {
                    this.SetBlockAsGlowing(var1, var2, var3, var4);
                }
            }

            if (var6 > 1)
            {
                this.SetIsIntersectionPointFlag(var1, var2, var3, var4, true);
            }
        }
    }

    public void FullyValidateBlock(World var1, int var2, int var3, int var4)
    {
        boolean var5 = this.CheckForNeighboringDetector(var1, var2, var3, var4);

        if (var5 != this.IsDetectorLogicFlagOn(var1, var2, var3, var4))
        {
            this.SetIsDetectorLogicFlag(var1, var2, var3, var4, var5);
        }

        boolean var6 = false;
        int var7 = 0;

        for (int var8 = 0; var8 <= 5; ++var8)
        {
            int var9 = this.GetRangeToValidLensSourceToFacing(var1, var2, var3, var4, var8);

            if (var9 > 0)
            {
                ++var7;

                if (!var6 && this.HasValidLitLensSourceToFacing(var1, var2, var3, var4, var8))
                {
                    var6 = true;
                }

                int var10 = 128 - var9;

                if (var10 > 0)
                {
                    FCUtilsBlockPos var11 = new FCUtilsBlockPos(var2, var3, var4);
                    var11.AddFacingAsOffset(Block.GetOppositeFacing(var8));

                    if (var1.getBlockId(var11.i, var11.j, var11.k) == 0)
                    {
                        this.PropagateBeamsThroughBlock(var1, var11.i, var11.j, var11.k);
                    }
                }
            }
        }

        boolean var12;

        if (var7 == 0 && !this.IsDetectorLogicFlagOn(var1, var2, var3, var4))
        {
            FCBetterThanWolves.bIsLensBeamBeingRemoved = true;
            this.RemoveSelf(var1, var2, var3, var4);
            FCBetterThanWolves.bIsLensBeamBeingRemoved = false;
        }
        else
        {
            var12 = false;

            if (var7 > 1 || var7 == 1 && this.IsDetectorLogicFlagOn(var1, var2, var3, var4))
            {
                var12 = true;
            }

            if (var12 != this.IsIntersectionPointFlagOn(var1, var2, var3, var4))
            {
                this.SetIsIntersectionPointFlag(var1, var2, var3, var4, var12);
            }

            if (var6 != this.IsLitFlagOn(var1, var2, var3, var4))
            {
                if (var6)
                {
                    this.LightBlock(var1, var2, var3, var4);
                }
                else
                {
                    this.UnlightBlock(var1, var2, var3, var4);
                }
            }
        }

        if (this.IsLitFlagOn(var1, var2, var3, var4))
        {
            var12 = this.ShouldBeGlowing(var1, var2, var3, var4);

            if (var12 != this.IsBlockGlowing(var1, var2, var3, var4))
            {
                if (var12)
                {
                    this.SetBlockAsGlowing(var1, var2, var3, var4);
                }
                else
                {
                    this.SetBlockAsNotGlowing(var1, var2, var3, var4);
                }
            }
        }
    }
}

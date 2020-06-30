package net.minecraft.src;

import java.util.Random;

public class FCBlockDetectorBlock extends Block
{
    private static final int iDetectorTickRate = 4;
    private Icon[] m_IconBySideArray = new Icon[6];
    private Icon m_IconFront;
    private Icon m_IconFrontOn;

    public FCBlockDetectorBlock(int var1)
    {
        super(var1, Material.rock);
        this.setHardness(3.5F);
        this.setStepSound(Block.soundStoneFootstep);
        this.setUnlocalizedName("fcBlockDetectorBlock");
        this.setTickRandomly(true);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 4;
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
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World var1, int var2, int var3, int var4)
    {
        super.onBlockAdded(var1, var2, var3, var4);
        this.SetBlockOn(var1, var2, var3, var4, false);
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
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
        boolean var6 = this.PlaceDetectorLogicIfNecessary(var1, var2, var3, var4);
        boolean var7 = this.CheckForDetection(var1, var2, var3, var4);
        int var8 = this.GetFacing(var1, var2, var3, var4);

        if (var8 == 1)
        {
            if (!var7 && var1.IsPrecipitatingAtPos(var2, var3 + 1, var4))
            {
                var7 = true;
            }

            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }

        if (var7)
        {
            if (!this.IsBlockOn(var1, var2, var3, var4))
            {
                this.SetBlockOn(var1, var2, var3, var4, true);
            }
        }
        else if (this.IsBlockOn(var1, var2, var3, var4))
        {
            if (!var6)
            {
                this.SetBlockOn(var1, var2, var3, var4, false);
            }
            else
            {
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
            }
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID))
        {
            int var6 = this.GetFacing(var1, var2, var3, var4);

            if (var6 == 1)
            {
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
            }
            else if (this.CheckForDetection(var1, var2, var3, var4) != this.IsBlockOn(var1, var2, var3, var4))
            {
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
            }
        }
    }

    /**
     * Returns true if the block is emitting indirect/weak redstone power on the specified side. If isBlockNormalCube
     * returns true, standard redstone propagation rules will apply instead and this will not be called. Args: World, X,
     * Y, Z, side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingWeakPower(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.IsBlockOn(var1, var2, var3, var4) ? 15 : 0;
    }

    /**
     * Returns true if the block is emitting direct/strong redstone power on the specified side. Args: World, X, Y, Z,
     * side. Note that the side is reversed - eg it is 1 (up) when checking the bottom of the block.
     */
    public int isProvidingStrongPower(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return 0;
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     */
    public boolean canProvidePower()
    {
        return true;
    }

    public void OnArrowCollide(World var1, int var2, int var3, int var4, EntityArrow var5)
    {
        if (!var1.isRemote)
        {
            int var6 = this.GetFacing(var1, var2, var3, var4);
            FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);
            var7.AddFacingAsOffset(var6);

            if (var1.getBlockId(var7.i, var7.j, var7.k) == FCBetterThanWolves.fcBlockDetectorLogic.blockID)
            {
                FCBetterThanWolves.fcBlockDetectorLogic.onEntityCollidedWithBlock(var1, var7.i, var7.j, var7.k, var5);
            }
        }
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

    public boolean RotateAroundJAxis(World var1, int var2, int var3, int var4, boolean var5)
    {
        if (super.RotateAroundJAxis(var1, var2, var3, var4, var5))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        var6 = Block.CycleFacing(var6, var5);
        this.SetFacing(var1, var2, var3, var4, var6);
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
        var1.notifyBlockChange(var2, var3, var4, this.blockID);
        return true;
    }

    public boolean IsBlockOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.IsBlockOnFromMetadata(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean IsBlockOnFromMetadata(int var1)
    {
        return (var1 & 1) > 0;
    }

    public void SetBlockOn(World var1, int var2, int var3, int var4, boolean var5)
    {
        if (var5 != this.IsBlockOn(var1, var2, var3, var4))
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);

            if (var5)
            {
                var6 |= 1;
                var1.playAuxSFX(2234, var2, var3, var4, 0);
            }
            else
            {
                var6 &= -2;
            }

            var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
            var1.notifyBlocksOfNeighborChange(var2, var3 - 1, var4, this.blockID);
            var1.notifyBlocksOfNeighborChange(var2, var3 + 1, var4, this.blockID);
            var1.notifyBlocksOfNeighborChange(var2 - 1, var3, var4, this.blockID);
            var1.notifyBlocksOfNeighborChange(var2 + 1, var3, var4, this.blockID);
            var1.notifyBlocksOfNeighborChange(var2, var3, var4 - 1, this.blockID);
            var1.notifyBlocksOfNeighborChange(var2, var3, var4 + 1, this.blockID);
            var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
        }
    }

    public boolean PlaceDetectorLogicIfNecessary(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4);
        var6.AddFacingAsOffset(var5);
        int var7 = var1.getBlockId(var6.i, var6.j, var6.k);
        FCBlockDetectorLogic var8;

        if (var7 == 0)
        {
            var8 = (FCBlockDetectorLogic)((FCBlockDetectorLogic)FCBetterThanWolves.fcBlockDetectorLogic);
            var1.setBlock(var6.i, var6.j, var6.k, var8.blockID, 0, 0);
            var8.SetIsDetectorLogicFlag(var1, var6.i, var6.j, var6.k, true);
            var8.FullyValidateBlock(var1, var6.i, var6.j, var6.k);
            return true;
        }
        else
        {
            if (var7 == FCBetterThanWolves.fcBlockDetectorLogic.blockID || var7 == FCBetterThanWolves.fcBlockDetectorGlowingLogic.blockID)
            {
                var8 = (FCBlockDetectorLogic)((FCBlockDetectorLogic)FCBetterThanWolves.fcBlockDetectorLogic);

                if (!var8.IsDetectorLogicFlagOn(var1, var6.i, var6.j, var6.k))
                {
                    var8.SetIsDetectorLogicFlag(var1, var6.i, var6.j, var6.k, true);

                    if (var8.HasValidLensSource(var1, var6.i, var6.j, var6.k))
                    {
                        var8.SetIsIntersectionPointFlag(var1, var6.i, var6.j, var6.k, true);
                    }
                }
            }

            return false;
        }
    }

    public boolean CheckForDetection(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4);
        var6.AddFacingAsOffset(var5);
        int var7 = var1.getBlockId(var6.i, var6.j, var6.k);

        if (var7 > 0)
        {
            if (!FCBlockDetectorLogic.IsLogicBlock(var7))
            {
                if (var7 == FCBetterThanWolves.fcLens.blockID)
                {
                    FCBlockLens var10 = (FCBlockLens)((FCBlockLens)FCBetterThanWolves.fcLens);

                    if (var10.GetFacing(var1, var6.i, var6.j, var6.k) == Block.GetOppositeFacing(var5))
                    {
                        return var10.IsLit(var1, var6.i, var6.j, var6.k);
                    }
                }

                return true;
            }

            FCBlockDetectorLogic var8 = (FCBlockDetectorLogic)((FCBlockDetectorLogic)FCBetterThanWolves.fcBlockDetectorLogic);

            if (var8.IsEntityCollidingFlagOn(var1, var6.i, var6.j, var6.k) || var8.IsLitFlagOn(var1, var6.i, var6.j, var6.k))
            {
                return true;
            }

            int var9 = var1.getBlockId(var6.i, var6.j - 1, var6.k);

            if (var9 == Block.crops.blockID && var1.getBlockMetadata(var6.i, var6.j - 1, var6.k) >= 7)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        Icon var2 = var1.registerIcon("fcBlockDetectorBlock_top");
        this.blockIcon = var2;
        this.m_IconFront = var1.registerIcon("fcBlockDetectorBlock_front");
        this.m_IconFrontOn = var1.registerIcon("fcBlockDetectorBlock_front_on");
        this.m_IconBySideArray[0] = var1.registerIcon("fcBlockDetectorBlock_bottom");
        this.m_IconBySideArray[1] = var2;
        Icon var3 = var1.registerIcon("fcBlockDetectorBlock_side");
        this.m_IconBySideArray[2] = var3;
        this.m_IconBySideArray[3] = var3;
        this.m_IconBySideArray[4] = var3;
        this.m_IconBySideArray[5] = var3;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var1 == 3 ? this.m_IconFront : this.m_IconBySideArray[var1];
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public Icon getBlockTexture(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        int var7 = this.GetFacing(var6);
        return var7 == var5 ? (this.IsBlockOnFromMetadata(var6) ? this.m_IconFrontOn : this.m_IconFront) : this.m_IconBySideArray[var5];
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.IsBlockOn(var1, var2, var3, var4))
        {
            int var6 = this.GetFacing(var1, var2, var3, var4);
            float var7 = (float)var2;
            float var8 = (float)var3;
            float var9 = (float)var4;
            float var10 = var7;
            float var12 = var9;
            float var11;

            if (var6 == 0)
            {
                var11 = var8 -= 0.2F;
                var12 = var9 += 0.25F;
                var7 += 0.33F;
                var10 += 0.66F;
            }
            else if (var6 == 1)
            {
                var11 = ++var8;
                var12 = var9 += 0.25F;
                var7 += 0.33F;
                var10 += 0.66F;
            }
            else if (var6 == 3)
            {
                var11 = var8 += 0.75F;
                var12 = ++var9;
                var7 += 0.33F;
                var10 += 0.66F;
            }
            else if (var6 == 2)
            {
                var11 = var8 += 0.75F;
                var12 = var9 -= 0.1F;
                var7 += 0.33F;
                var10 += 0.66F;
            }
            else if (var6 == 5)
            {
                var10 = ++var7;
                var11 = var8 += 0.75F;
                var9 = (float)((double)var9 + 0.33D);
                var12 += 0.66F;
            }
            else
            {
                var10 = var7 -= 0.1F;
                var11 = var8 += 0.75F;
                var9 += 0.33F;
                var12 += 0.66F;
            }

            var7 += (var5.nextFloat() - 0.5F) * 0.1F;
            var8 += (var5.nextFloat() - 0.5F) * 0.1F;
            var9 += (var5.nextFloat() - 0.5F) * 0.1F;
            float var13 = 0.06666667F;
            float var14 = var13 * 0.6F + 0.4F;
            float var15 = var13 * var13 * 0.7F - 0.5F;
            float var16 = var13 * var13 * 0.6F - 0.7F;

            if (var15 < 0.0F)
            {
                var15 = 0.0F;
            }

            if (var16 < 0.0F)
            {
                var16 = 0.0F;
            }

            if (var5.nextFloat() >= 0.5F)
            {
                var1.spawnParticle("reddust", (double)var7, (double)var8, (double)var9, (double)var14, (double)var15, (double)var16);
            }
            else
            {
                var1.spawnParticle("reddust", (double)var10, (double)var11, (double)var12, (double)var14, (double)var15, (double)var16);
            }
        }
    }
}

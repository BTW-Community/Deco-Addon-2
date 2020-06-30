package net.minecraft.src;

import java.util.Random;

public class FCBlockAxle extends Block
{
    protected static final double m_dAxleWidth = 0.25D;
    protected static final double m_dAxleHalfWidth = 0.125D;
    public static final int iAxleTickRate = 1;
    protected static final int[][] m_AxleFacingsForAlignment = new int[][] {{0, 1}, {2, 3}, {4, 5}};

    protected FCBlockAxle(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(2.0F);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.InitBlockBounds(0.375D, 0.375D, 0.0D, 0.625D, 0.625D, 1.0D);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockAxle");
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
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        return this.SetAxisAlignmentInMetadataBasedOnFacing(var9, var5);
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
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        this.SetPowerLevel(var1, var2, var3, var4, 0);
        this.ValidatePowerLevel(var1, var2, var3, var4);
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetAxisAlignment(var1, var2, var3, var4);

        switch (var5)
        {
            case 0:
                return AxisAlignedBB.getAABBPool().getAABB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);

            case 1:
                return AxisAlignedBB.getAABBPool().getAABB(0.375D, 0.375D, 0.0D, 0.625D, 0.625D, 1.0D);

            default:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.375D, 0.375D, 1.0D, 0.625D, 0.625D);
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        this.ValidatePowerLevel(var1, var2, var3, var4);
    }

    /**
     * Returns the mobility information of the block, 0 = free, 1 = can't push but can move over, 2 = total immobility
     * and stop pistons
     */
    public int getMobilityFlag()
    {
        return 1;
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return this.IsAxleOrientedTowardsFacing(var1, var2, var3, var4, var5);
    }

    public int GetMechanicalPowerLevelProvidedToAxleAtFacing(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetAxisAlignment(var1, var2, var3, var4);
        return var5 >> 1 == var6 ? this.GetPowerLevel(var1, var2, var3, var4) : 0;
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemHempFibers.itemID, 4, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 2, 0, var6);
        return true;
    }

    public int GetFacing(int var1)
    {
        return this.GetAxisAlignmentFromMetadata(var1) << 1;
    }

    public int SetFacing(int var1, int var2)
    {
        return this.SetAxisAlignmentInMetadataBasedOnFacing(var1, var2);
    }

    public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.GetAxisAlignment(var1, var2, var3, var4);

        if (!var5)
        {
            ++var6;

            if (var6 > 2)
            {
                var6 = 0;
            }
        }
        else
        {
            --var6;

            if (var6 < 0)
            {
                var6 = 2;
            }
        }

        this.SetAxisAlignment(var1, var2, var3, var4, var6);
        var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
        this.SetPowerLevel(var1, var2, var3, var4, 0);
        this.ValidatePowerLevel(var1, var2, var3, var4);
        var1.markBlockForUpdate(var2, var3, var4);
        return true;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1.0F;
    }

    public int GetAxisAlignment(IBlockAccess var1, int var2, int var3, int var4)
    {
        return var1.getBlockMetadata(var2, var3, var4) >> 2;
    }

    public void SetAxisAlignment(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & 3;
        var6 |= var5 << 2;
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int GetAxisAlignmentFromMetadata(int var1)
    {
        return var1 >> 2;
    }

    public void SetAxisAlignmentBasedOnFacing(World var1, int var2, int var3, int var4, int var5)
    {
        byte var6;

        switch (var5)
        {
            case 0:
            case 1:
                var6 = 0;
                break;

            case 2:
            case 3:
                var6 = 1;
                break;

            default:
                var6 = 2;
        }

        int var7 = var1.getBlockMetadata(var2, var3, var4) & 3;
        var7 |= var6 << 2;
        var1.setBlockMetadataWithNotify(var2, var3, var4, var7);
    }

    public int SetAxisAlignmentInMetadataBasedOnFacing(int var1, int var2)
    {
        byte var3;

        switch (var2)
        {
            case 0:
            case 1:
                var3 = 0;
                break;

            case 2:
            case 3:
                var3 = 1;
                break;

            default:
                var3 = 2;
        }

        var1 &= 3;
        var1 |= var3 << 2;
        return var1;
    }

    public int GetPowerLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetPowerLevelFromMetadata(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetPowerLevelFromMetadata(int var1)
    {
        return var1 & 3;
    }

    public void SetPowerLevel(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        var6 = this.SetPowerLevelInMetadata(var6, var5);
        var1.setBlockMetadataWithNotifyNoClient(var2, var3, var4, var6);
    }

    public int SetPowerLevelInMetadata(int var1, int var2)
    {
        var2 &= 3;
        var1 &= 12;
        var1 |= var2;
        return var1;
    }

    public void SetPowerLevelWithoutNotify(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        var6 = this.SetPowerLevelInMetadata(var6, var5);
        var1.setBlockMetadata(var2, var3, var4, var6);
    }

    public boolean IsAxleOrientedTowardsFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetAxisAlignment(var1, var2, var3, var4);

        switch (var6)
        {
            case 0:
                if (var5 == 0 || var5 == 1)
                {
                    return true;
                }

                break;

            case 1:
                if (var5 == 2 || var5 == 3)
                {
                    return true;
                }

                break;

            default:
                if (var5 == 4 || var5 == 5)
                {
                    return true;
                }
        }

        return false;
    }

    public void BreakAxle(World var1, int var2, int var3, int var4)
    {
        if (var1.getBlockId(var2, var3, var4) == this.blockID)
        {
            this.DropComponentItemsOnBadBreak(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 1.0F);
            var1.playAuxSFX(2235, var2, var3, var4, 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    protected void ValidatePowerLevel(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetPowerLevel(var1, var2, var3, var4);
        int var6 = this.GetAxisAlignment(var1, var2, var3, var4);
        int var7 = 0;
        int var8 = 0;
        int var9;

        for (var9 = 0; var9 < 2; ++var9)
        {
            int var10 = m_AxleFacingsForAlignment[var6][var9];
            FCUtilsBlockPos var11 = new FCUtilsBlockPos(var2, var3, var4, var10);
            int var12 = var1.getBlockId(var11.i, var11.j, var11.k);

            if (var12 != 0)
            {
                Block var13 = Block.blocksList[var12];
                int var14 = var13.GetMechanicalPowerLevelProvidedToAxleAtFacing(var1, var11.i, var11.j, var11.k, Block.GetOppositeFacing(var10));

                if (var14 > var7)
                {
                    var7 = var14;
                }

                if (var14 > var5)
                {
                    ++var8;
                }
            }
        }

        if (var8 >= 2)
        {
            this.BreakAxle(var1, var2, var3, var4);
        }
        else
        {
            if (var7 > var5)
            {
                if (var7 == 1)
                {
                    this.BreakAxle(var1, var2, var3, var4);
                    return;
                }

                var9 = var7 - 1;
            }
            else
            {
                var9 = 0;
            }

            if (var9 != var5)
            {
                this.SetPowerLevel(var1, var2, var3, var4, var9);
            }
        }
    }

    private void EmitAxleParticles(World var1, int var2, int var3, int var4, Random var5)
    {
        for (int var6 = 0; var6 < 2; ++var6)
        {
            float var7 = (float)var2 + var5.nextFloat();
            float var8 = (float)var3 + var5.nextFloat() * 0.5F + 0.625F;
            float var9 = (float)var4 + var5.nextFloat();
            var1.spawnParticle("smoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
        }
    }

    public void Overpower(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetAxisAlignment(var1, var2, var3, var4);

        switch (var5)
        {
            case 0:
                this.OverpowerBlockToFacing(var1, var2, var3, var4, var5, 0);
                this.OverpowerBlockToFacing(var1, var2, var3, var4, var5, 1);
                break;

            case 1:
                this.OverpowerBlockToFacing(var1, var2, var3, var4, var5, 2);
                this.OverpowerBlockToFacing(var1, var2, var3, var4, var5, 3);
                break;

            default:
                this.OverpowerBlockToFacing(var1, var2, var3, var4, var5, 4);
                this.OverpowerBlockToFacing(var1, var2, var3, var4, var5, 5);
        }
    }

    private void OverpowerBlockToFacing(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);
        var7.AddFacingAsOffset(var6);
        int var8 = var1.getBlockId(var7.i, var7.j, var7.k);

        if (var8 != FCBetterThanWolves.fcBlockAxle.blockID && var8 != FCBetterThanWolves.fcBlockAxlePowerSource.blockID)
        {
            if (Block.blocksList[var8] instanceof FCIBlockMechanical)
            {
                FCIBlockMechanical var10 = (FCIBlockMechanical)((FCIBlockMechanical)Block.blocksList[var8]);

                if (var10.CanInputAxlePowerToFacing(var1, var7.i, var7.j, var7.k, Block.GetOppositeFacing(var6)))
                {
                    var10.Overpower(var1, var7.i, var7.j, var7.k);
                }
            }
        }
        else
        {
            int var9 = this.GetAxisAlignment(var1, var7.i, var7.j, var7.k);

            if (var9 == var5)
            {
                this.OverpowerBlockToFacing(var1, var7.i, var7.j, var7.k, var5, var6);
            }
        }
    }
}

package net.minecraft.src;

import java.util.List;
import org.lwjgl.opengl.GL11;

public class FCBlockSidingAndCornerAndDecorative extends FCBlockSidingAndCorner
{
    public static final int m_iSubtypeBench = 12;
    public static final int m_iSubtypeFence = 14;
    private static final float m_fBenchTopHeight = 0.125F;
    private static final float m_fBenchLegHeight = 0.375F;
    private static final float m_fBenchLegWidth = 0.25F;
    private static final float m_fBenchLegHalfWidth = 0.125F;
    public static final int m_iOakBenchTopTextureID = 93;
    public static final int m_iOakBenchLegTextureID = 94;

    protected FCBlockSidingAndCornerAndDecorative(int var1, Material var2, String var3, float var4, float var5, StepSound var6, String var7)
    {
        super(var1, var2, var3, var4, var5, var6, var7);
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7)
    {
        int var8 = var1.getBlockMetadata(var2, var3, var4);

        if (var8 == 14)
        {
            this.AddCollisionBoxesToListForFence(var1, var2, var3, var4, var5, var6, var7);
        }
        else
        {
            super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6, var7);
        }
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 12 ? this.GetBlockBoundsFromPoolForBench(var1, var2, var3, var4) : (var5 == 14 ? this.GetBlockBoundsFromPoolForFence(var1, var2, var3, var4) : super.GetBlockBoundsFromPoolBasedOnState(var1, var2, var3, var4));
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        int var7 = var1.getBlockId(var2, var3, var4);
        return this.IsBlockBench(var1, var2, var3, var4) && this.DoesBenchHaveLeg(var1, var2, var3, var4) ? this.CollisionRayTraceBenchWithLeg(var1, var2, var3, var4, var5, var6) : ((var7 != this.blockID || var1.getBlockMetadata(var2, var3, var4) != 14) && var7 != Block.fenceGate.blockID ? super.collisionRayTrace(var1, var2, var3, var4, var5, var6) : this.CollisionRayTraceFence(var1, var2, var3, var4, var5, var6));
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        int var10 = var1.getBlockMetadata(var2, var3, var4);
        return var10 != 12 && var10 != 14 ? super.onBlockPlaced(var1, var2, var3, var4, var5, var6, var7, var8, var9) : var9;
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        int var7 = var1.getBlockMetadata(var2, var3, var4);
        return var7 == 12 ? var5 == 0 : (var7 != 14 ? super.HasCenterHardPointToFacing(var1, var2, var3, var4, var5, var6) : var5 == 0 || var5 == 1);
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        int var7 = var1.getBlockMetadata(var2, var3, var4);
        return var7 != 12 && var7 != 14 ? super.HasLargeCenterHardPointToFacing(var1, var2, var3, var4, var5, var6) : false;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return IsDecorativeFromMetadata(var1) ? var1 : super.damageDropped(var1);
    }

    public boolean getBlocksMovement(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 14 ? false : super.getBlocksMovement(var1, var2, var3, var4);
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 12 ? true : (IsDecorativeFromMetadata(var5) ? var1.doesBlockHaveSolidTopSurface(var2, var3, var4) : super.CanGroundCoverRestOnBlock(var1, var2, var3, var4));
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 12 ? -0.5F : (IsDecorativeFromMetadata(var5) ? 0.0F : super.GroundCoverRestingOnVisualOffset(var1, var2, var3, var4));
    }

    public int GetWeightOnPathBlocked(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 14 ? -3 : 0;
    }

    public int GetFacing(int var1)
    {
        return var1 != 12 && var1 != 14 ? super.GetFacing(var1) : 0;
    }

    public int SetFacing(int var1, int var2)
    {
        return var1 != 12 && var1 != 14 ? super.SetFacing(var1, var2) : var1;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 != 12 && var5 != 14 ? super.CanRotateOnTurntable(var1, var2, var3, var4) : true;
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 14 ? true : (var5 == 12 ? false : super.CanTransmitRotationVerticallyOnTurntable(var1, var2, var3, var4));
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        return var1 != 12 && var1 != 14 ? super.RotateMetadataAroundJAxis(var1, var2) : var1;
    }

    public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        return var6 != 12 && var6 != 14 ? super.ToggleFacing(var1, var2, var3, var4, var5) : false;
    }

    public float MobSpawnOnVerticalOffset(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 14 ? 0.5F : (var5 == 12 ? -0.5F : super.MobSpawnOnVerticalOffset(var1, var2, var3, var4));
    }

    public boolean IsDecorative(IBlockAccess var1, int var2, int var3, int var4)
    {
        return IsDecorativeFromMetadata(var1.getBlockMetadata(var2, var3, var4));
    }

    public static boolean IsDecorativeFromMetadata(int var0)
    {
        return var0 == 12 || var0 == 14;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolForBench(IBlockAccess var1, int var2, int var3, int var4)
    {
        return !this.DoesBenchHaveLeg(var1, var2, var3, var4) ? AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.375D, 0.0D, 1.0D, 0.5D, 1.0D) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
    }

    public AxisAlignedBB GetBlockBoundsFromPoolForFence(IBlockAccess var1, int var2, int var3, int var4)
    {
        AxisAlignedBB var5 = AxisAlignedBB.getAABBPool().getAABB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);

        if (this.DoesFenceConnectTo(var1, var2, var3, var4 - 1))
        {
            var5.minZ = 0.0D;
        }

        if (this.DoesFenceConnectTo(var1, var2, var3, var4 + 1))
        {
            var5.maxZ = 1.0D;
        }

        if (this.DoesFenceConnectTo(var1, var2 - 1, var3, var4))
        {
            var5.minX = 0.0D;
        }

        if (this.DoesFenceConnectTo(var1, var2 + 1, var3, var4))
        {
            var5.maxX = 1.0D;
        }

        return var5;
    }

    public void AddCollisionBoxesToListForFence(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7)
    {
        boolean var8 = this.DoesFenceConnectTo(var1, var2 - 1, var3, var4);
        boolean var9 = this.DoesFenceConnectTo(var1, var2 + 1, var3, var4);
        boolean var10 = this.DoesFenceConnectTo(var1, var2, var3, var4 - 1);
        boolean var11 = this.DoesFenceConnectTo(var1, var2, var3, var4 + 1);
        float var12 = 0.375F;
        float var13 = 0.625F;
        float var14 = 0.375F;
        float var15 = 0.625F;

        if (var10)
        {
            var14 = 0.0F;
        }

        if (var11)
        {
            var15 = 1.0F;
        }

        if (var10 || var11)
        {
            AxisAlignedBB.getAABBPool().getAABB((double)var12, 0.0D, (double)var14, (double)var13, 1.5D, (double)var15).offset((double)var2, (double)var3, (double)var4).AddToListIfIntersects(var5, var6);
        }

        if (var8)
        {
            var12 = 0.0F;
        }

        if (var9)
        {
            var13 = 1.0F;
        }

        if (var8 || var9 || !var10 && !var11)
        {
            AxisAlignedBB.getAABBPool().getAABB((double)var12, 0.0D, 0.375D, (double)var13, 1.5D, 0.625D).offset((double)var2, (double)var3, (double)var4).AddToListIfIntersects(var5, var6);
        }
    }

    public boolean DoesBenchHaveLeg(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3 - 1, var4);

        if (this.blockID == FCBetterThanWolves.fcBlockNetherBrickSidingAndCorner.blockID)
        {
            if (var5 == Block.netherFence.blockID)
            {
                return true;
            }
        }
        else if (this.blockID == var5)
        {
            int var6 = var1.getBlockMetadata(var2, var3 - 1, var4);

            if (var6 == 14)
            {
                return true;
            }
        }

        boolean var10 = this.IsBlockBench(var1, var2 + 1, var3, var4);
        boolean var7 = this.IsBlockBench(var1, var2 - 1, var3, var4);
        boolean var8 = this.IsBlockBench(var1, var2, var3, var4 + 1);
        boolean var9 = this.IsBlockBench(var1, var2, var3, var4 - 1);
        return !var10 && (!var8 || !var9) || !var7 && (!var8 || !var9);
    }

    public boolean IsBlockBench(IBlockAccess var1, int var2, int var3, int var4)
    {
        return var1.getBlockId(var2, var3, var4) == this.blockID && var1.getBlockMetadata(var2, var3, var4) == 12;
    }

    public MovingObjectPosition CollisionRayTraceBenchWithLeg(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCUtilsRayTraceVsComplexBlock var7 = new FCUtilsRayTraceVsComplexBlock(var1, var2, var3, var4, var5, var6);
        var7.AddBoxWithLocalCoordsToIntersectionList(0.0D, 0.375D, 0.0D, 1.0D, 0.5D, 1.0D);
        var7.AddBoxWithLocalCoordsToIntersectionList(0.375D, 0.0D, 0.375D, 0.625D, 0.375D, 0.625D);
        return var7.GetFirstIntersection();
    }

    public MovingObjectPosition CollisionRayTraceFence(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCUtilsRayTraceVsComplexBlock var7 = new FCUtilsRayTraceVsComplexBlock(var1, var2, var3, var4, var5, var6);
        var7.AddBoxWithLocalCoordsToIntersectionList(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
        boolean var8 = false;
        boolean var9 = this.DoesFenceConnectTo(var1, var2 - 1, var3, var4);
        boolean var10 = this.DoesFenceConnectTo(var1, var2 + 1, var3, var4);
        boolean var11 = this.DoesFenceConnectTo(var1, var2, var3, var4 - 1);
        boolean var12 = this.DoesFenceConnectTo(var1, var2, var3, var4 + 1);

        if (var9 || var10)
        {
            var8 = true;
        }

        boolean var13 = false;

        if (var11 || var12)
        {
            var13 = true;
        }

        if (!var8 && !var13)
        {
            var8 = true;
        }

        float var14 = 0.4375F;
        float var15 = 0.5625F;
        float var16 = 0.75F;
        float var17 = 0.9375F;
        float var18 = var9 ? 0.0F : var14;
        float var19 = var10 ? 1.0F : var15;
        float var20 = var11 ? 0.0F : var14;
        float var21 = var12 ? 1.0F : var15;

        if (var8)
        {
            var7.AddBoxWithLocalCoordsToIntersectionList((double)var18, (double)var16, (double)var14, (double)var19, (double)var17, (double)var15);
        }

        if (var13)
        {
            var7.AddBoxWithLocalCoordsToIntersectionList((double)var14, (double)var16, (double)var20, (double)var15, (double)var17, (double)var21);
        }

        var16 = 0.375F;
        var17 = 0.5625F;

        if (var8)
        {
            var7.AddBoxWithLocalCoordsToIntersectionList((double)var18, (double)var16, (double)var14, (double)var19, (double)var17, (double)var15);
        }

        if (var13)
        {
            var7.AddBoxWithLocalCoordsToIntersectionList((double)var14, (double)var16, (double)var20, (double)var15, (double)var17, (double)var21);
        }

        return var7.GetFirstIntersection();
    }

    public boolean DoesFenceConnectTo(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);

        if ((var5 != this.blockID || var1.getBlockMetadata(var2, var3, var4) != 14) && var5 != Block.fenceGate.blockID)
        {
            Block var6 = Block.blocksList[var5];
            return var6 != null && var6.blockMaterial.isOpaque() && var6.renderAsNormalBlock() && var6.blockMaterial != Material.pumpkin;
        }
        else
        {
            return true;
        }
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        super.getSubBlocks(var1, var2, var3);
        var3.add(new ItemStack(var1, 1, 12));

        if (var1 != FCBetterThanWolves.fcBlockNetherBrickSidingAndCorner.blockID)
        {
            var3.add(new ItemStack(var1, 1, 14));
        }
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, Block.GetOppositeFacing(var5));
        int var7 = var1.getBlockMetadata(var6.i, var6.j, var6.k);
        return var7 != 14 && var7 != 12 ? super.shouldSideBeRendered(var1, var2, var3, var4, var5) : true;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        int var6 = var5.getBlockMetadata(var2, var3, var4);
        return var6 == 12 ? RenderBench(var1, var5, var2, var3, var4, this) : (var6 == 14 ? RenderFence(var1, var5, var2, var3, var4, this) : super.RenderBlock(var1, var2, var3, var4));
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        int var4 = var2;
        Object var5 = this;

        if (this.blockID == FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID)
        {
            int var6 = FCItemBlockWoodSidingDecorativeStub.GetBlockType(var2);
            int var7 = FCItemBlockWoodSidingDecorativeStub.GetWoodType(var2);

            if (var6 == 0)
            {
                var4 = 12;
            }
            else
            {
                var4 = 14;
            }

            if (var7 == 0)
            {
                var5 = FCBetterThanWolves.fcBlockWoodOakSidingAndCorner;
            }
            else if (var7 == 1)
            {
                var5 = FCBetterThanWolves.fcBlockWoodSpruceSidingAndCorner;
            }
            else if (var7 == 2)
            {
                var5 = FCBetterThanWolves.fcBlockWoodBirchSidingAndCorner;
            }
            else if (var7 == 3)
            {
                var5 = FCBetterThanWolves.fcBlockWoodJungleSidingAndCorner;
            }
            else
            {
                var5 = FCBetterThanWolves.fcBlockWoodBloodSidingAndCorner;
            }
        }

        if (var4 == 12)
        {
            RenderBenchInvBlock(var1, (Block)var5, var4);
        }
        else if (var4 == 14)
        {
            RenderFenceInvBlock(var1, (Block)var5, var4);
        }
        else
        {
            super.RenderBlockAsItem(var1, var2, var3);
        }
    }

    public static boolean RenderBench(RenderBlocks var0, IBlockAccess var1, int var2, int var3, int var4, Block var5)
    {
        FCBlockSidingAndCornerAndDecorative var6;

        if (var5.blockID != FCBetterThanWolves.fcBlockWoodOakSidingAndCorner.blockID)
        {
            var0.setRenderBounds(0.0D, 0.375D, 0.0D, 1.0D, 0.5D, 1.0D);
            var0.renderStandardBlock(var5, var2, var3, var4);
            var6 = (FCBlockSidingAndCornerAndDecorative)var5;

            if (var6.DoesBenchHaveLeg(var1, var2, var3, var4))
            {
                var0.setRenderBounds(0.375D, 0.0D, 0.375D, 0.625D, 0.375D, 0.625D);
                var0.renderStandardBlock(var5, var2, var3, var4);
            }
        }
        else
        {
            var0.setRenderBounds(0.0D, 0.375D, 0.0D, 1.0D, 0.5D, 1.0D);
            FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, ((FCBlockAestheticNonOpaque)FCBetterThanWolves.fcAestheticNonOpaque).m_IconTableWoodOakTop);
            var6 = (FCBlockSidingAndCornerAndDecorative)var5;

            if (var6.DoesBenchHaveLeg(var1, var2, var3, var4))
            {
                var0.setRenderBounds(0.375D, 0.0D, 0.375D, 0.625D, 0.375D, 0.625D);
                FCClientUtilsRender.RenderStandardBlockWithTexture(var0, var5, var2, var3, var4, ((FCBlockAestheticNonOpaque)FCBetterThanWolves.fcAestheticNonOpaque).m_IconTableWoodOakLeg);
            }
        }

        return true;
    }

    public static void RenderBenchInvBlock(RenderBlocks var0, Block var1, int var2)
    {
        if (var1.blockID != FCBetterThanWolves.fcBlockWoodOakSidingAndCorner.blockID)
        {
            var0.setRenderBounds(0.0D, 0.375D, 0.0D, 1.0D, 0.5D, 1.0D);
            FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, 12);
            var0.setRenderBounds(0.375D, 0.0D, 0.375D, 0.625D, 0.375D, 0.625D);
            FCClientUtilsRender.RenderInvBlockWithMetadata(var0, var1, -0.5F, -0.5F, -0.5F, 12);
        }
        else
        {
            var0.setRenderBounds(0.0D, 0.375D, 0.0D, 1.0D, 0.5D, 1.0D);
            FCClientUtilsRender.RenderInvBlockWithTexture(var0, var1, -0.5F, -0.5F, -0.5F, ((FCBlockAestheticNonOpaque)FCBetterThanWolves.fcAestheticNonOpaque).m_IconTableWoodOakTop);
            var0.setRenderBounds(0.375D, 0.0D, 0.375D, 0.625D, 0.375D, 0.625D);
            FCClientUtilsRender.RenderInvBlockWithTexture(var0, var1, -0.5F, -0.5F, -0.5F, ((FCBlockAestheticNonOpaque)FCBetterThanWolves.fcAestheticNonOpaque).m_IconTableWoodOakLeg);
        }
    }

    public static boolean RenderFence(RenderBlocks var0, IBlockAccess var1, int var2, int var3, int var4, Block var5)
    {
        var0.setRenderBounds(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
        var0.renderStandardBlock(var5, var2, var3, var4);
        FCBlockSidingAndCornerAndDecorative var6 = (FCBlockSidingAndCornerAndDecorative)var5;
        boolean var7 = false;
        boolean var8 = var6.DoesFenceConnectTo(var0.blockAccess, var2 - 1, var3, var4);
        boolean var9 = var6.DoesFenceConnectTo(var0.blockAccess, var2 + 1, var3, var4);
        boolean var10 = var6.DoesFenceConnectTo(var0.blockAccess, var2, var3, var4 - 1);
        boolean var11 = var6.DoesFenceConnectTo(var0.blockAccess, var2, var3, var4 + 1);

        if (var8 || var9)
        {
            var7 = true;
        }

        boolean var12 = false;

        if (var10 || var11)
        {
            var12 = true;
        }

        if (!var7 && !var12)
        {
            var7 = true;
        }

        float var13 = 0.4375F;
        float var14 = 0.5625F;
        float var15 = 0.75F;
        float var16 = 0.9375F;
        float var17 = var8 ? 0.0F : var13;
        float var18 = var9 ? 1.0F : var14;
        float var19 = var10 ? 0.0F : var13;
        float var20 = var11 ? 1.0F : var14;

        if (var7)
        {
            var0.setRenderBounds((double)var17, (double)var15, (double)var13, (double)var18, (double)var16, (double)var14);
            var0.renderStandardBlock(var6, var2, var3, var4);
        }

        if (var12)
        {
            var0.setRenderBounds((double)var13, (double)var15, (double)var19, (double)var14, (double)var16, (double)var20);
            var0.renderStandardBlock(var6, var2, var3, var4);
        }

        var15 = 0.375F;
        var16 = 0.5625F;

        if (var7)
        {
            var0.setRenderBounds((double)var17, (double)var15, (double)var13, (double)var18, (double)var16, (double)var14);
            var0.renderStandardBlock(var6, var2, var3, var4);
        }

        if (var12)
        {
            var0.setRenderBounds((double)var13, (double)var15, (double)var19, (double)var14, (double)var16, (double)var20);
            var0.renderStandardBlock(var6, var2, var3, var4);
        }

        return true;
    }

    public static void RenderFenceInvBlock(RenderBlocks var0, Block var1, int var2)
    {
        Tessellator var3 = Tessellator.instance;

        for (int var4 = 0; var4 < 4; ++var4)
        {
            float var5 = 0.125F;

            if (var4 == 0)
            {
                var0.setRenderBounds((double)(0.5F - var5), 0.0D, 0.0D, (double)(0.5F + var5), 1.0D, (double)(var5 * 2.0F));
            }

            if (var4 == 1)
            {
                var0.setRenderBounds((double)(0.5F - var5), 0.0D, (double)(1.0F - var5 * 2.0F), (double)(0.5F + var5), 1.0D, 1.0D);
            }

            var5 = 0.0625F;

            if (var4 == 2)
            {
                var0.setRenderBounds((double)(0.5F - var5), (double)(1.0F - var5 * 3.0F), (double)(-var5 * 2.0F), (double)(0.5F + var5), (double)(1.0F - var5), (double)(1.0F + var5 * 2.0F));
            }

            if (var4 == 3)
            {
                var0.setRenderBounds((double)(0.5F - var5), (double)(0.5F - var5 * 3.0F), (double)(-var5 * 2.0F), (double)(0.5F + var5), (double)(0.5F - var5), (double)(1.0F + var5 * 2.0F));
            }

            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            var3.startDrawingQuads();
            var3.setNormal(0.0F, -1.0F, 0.0F);
            var0.renderFaceYNeg(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(0));
            var3.draw();
            var3.startDrawingQuads();
            var3.setNormal(0.0F, 1.0F, 0.0F);
            var0.renderFaceYPos(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(1));
            var3.draw();
            var3.startDrawingQuads();
            var3.setNormal(0.0F, 0.0F, -1.0F);
            var0.renderFaceZNeg(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(2));
            var3.draw();
            var3.startDrawingQuads();
            var3.setNormal(0.0F, 0.0F, 1.0F);
            var0.renderFaceZPos(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(3));
            var3.draw();
            var3.startDrawingQuads();
            var3.setNormal(-1.0F, 0.0F, 0.0F);
            var0.renderFaceXNeg(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(4));
            var3.draw();
            var3.startDrawingQuads();
            var3.setNormal(1.0F, 0.0F, 0.0F);
            var0.renderFaceXPos(var1, 0.0D, 0.0D, 0.0D, var1.getBlockTextureFromSide(5));
            var3.draw();
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        }

        var0.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    }
}

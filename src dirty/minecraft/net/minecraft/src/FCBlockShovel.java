package net.minecraft.src;

import java.util.List;

public class FCBlockShovel extends Block
{
    protected FCModelBlockShovel m_model = new FCModelBlockShovel();
    private Icon m_iconEdge;
    private Icon m_iconEdgeBack;
    private Icon m_iconEdgeMiddle;

    public FCBlockShovel(int var1)
    {
        super(var1, Material.iron);
        this.setHardness(5.0F);
        this.SetPicksEffectiveOn();
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        this.setStepSound(soundMetalFootstep);
        this.setUnlocalizedName("fcBlockShovel");
        this.setCreativeTab(CreativeTabs.tabRedstone);
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
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    public void addCollisionBoxesToList(World var1, int var2, int var3, int var4, AxisAlignedBB var5, List var6, Entity var7)
    {
        FCModelBlock var8 = this.GetTransformedModelForMetadata(this.m_model.m_collisionModel, var1.getBlockMetadata(var2, var3, var4));
        var8.AddIntersectingBoxesToCollisionList(var1, var2, var3, var4, var5, var6);
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        byte var10 = 1;
        int var11 = 2;

        if (var5 >= 2)
        {
            if (var7 > 0.5F)
            {
                var10 = 0;
            }

            var11 = var5;
        }
        else if (var5 == 0)
        {
            var10 = 0;
        }

        var9 = this.SetFacing(var9, var11);
        var9 = this.SetVerticalOrientation(var9, var10);
        return var9;
    }

    public int PreBlockPlacedBy(World var1, int var2, int var3, int var4, int var5, EntityLiving var6)
    {
        int var7 = FCUtilsMisc.ConvertOrientationToFlatBlockFacingReversed(var6);
        return this.SetFacing(var5, var7);
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCUtilsRayTraceVsComplexBlock var7 = new FCUtilsRayTraceVsComplexBlock(var1, var2, var3, var4, var5, var6);
        FCModelBlock var8 = this.GetTransformedModelForMetadata(this.m_model.m_rayTraceModel, var1.getBlockMetadata(var2, var3, var4));
        var8.AddToRayTrace(var7);
        return var7.GetFirstIntersection();
    }

    public int GetFacing(int var1)
    {
        return (var1 & 3) + 2;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 &= -4;
        var1 |= MathHelper.clamp_int(var2, 2, 5) - 2;
        return var1;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public int GetPistonShovelEjectDirection(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (var5 >= 2)
        {
            if (var5 == this.GetFacing(var6))
            {
                return this.GetVerticalOrientation(var6);
            }
        }
        else if (var5 == this.GetVerticalOrientation(var6))
        {
            return this.GetFacing(var6);
        }

        return -1;
    }

    public int GetVerticalOrientation(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetVerticalOrientation(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetVerticalOrientation(int var1)
    {
        return (var1 & 12) >> 2;
    }

    public void SetVerticalOrientation(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.SetVerticalOrientation(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetVerticalOrientation(int var1, int var2)
    {
        var1 &= -13;
        var1 |= var2 << 2;
        return var1;
    }

    private FCModelBlock GetTransformedModelForMetadata(FCModelBlock var1, int var2)
    {
        FCModelBlock var3 = var1.MakeTemporaryCopy();

        if (this.GetVerticalOrientation(var2) == 0)
        {
            var3.TiltToFacingAlongJ(0);
        }

        var3.RotateAroundJToFacing(this.GetFacing(var2));
        return var3;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_iconEdge = var1.registerIcon("fcBlockShovel_edge");
        this.m_iconEdgeBack = var1.registerIcon("fcBlockShovel_edge_back");
        this.m_iconEdgeMiddle = var1.registerIcon("fcBlockShovel_edge_middle");
    }

    public Icon GetIconByIndex(int var1)
    {
        return var1 == 1 ? this.m_iconEdge : (var1 == 2 ? this.m_iconEdgeBack : (var1 == 3 ? this.m_iconEdgeMiddle : this.blockIcon));
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.m_currentBlockRenderer.ShouldSideBeRenderedBasedOnCurrentBounds(var2, var3, var4, var5);
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        FCModelBlock var5 = this.GetTransformedModelForMetadata(this.m_model, var1.blockAccess.getBlockMetadata(var2, var3, var4));
        return var5.RenderAsBlockWithColorMultiplier(var1, this, var2, var3, var4);
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        FCModelBlock var4 = this.m_model.MakeTemporaryCopy();
        var4.RotateAroundJToFacing(3);
        var4.RenderAsItemBlock(var1, this, var2);
    }
}

package net.minecraft.src;

import java.util.Random;

public class FCBlockAnvil extends Block
{
    FCModelBlockAnvil m_model = new FCModelBlockAnvil();

    protected FCBlockAnvil(int var1)
    {
        super(var1, Material.anvil);
        this.setHardness(50.0F);
        this.setResistance(10.0F);
        this.SetPicksEffectiveOn();
        this.setLightOpacity(0);
        this.setStepSound(soundAnvilFootstep);
        this.setUnlocalizedName("anvil");
        this.setCreativeTab(CreativeTabs.tabDecorations);
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
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 7;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcItemMetalFragment.itemID;
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        if (!this.canSilkHarvest(var6) || !EnchantmentHelper.getSilkTouchModifier(var2))
        {
            var1.playAuxSFX(2272, var3, var4, var5, this.blockID + (var6 << 12));
        }

        super.harvestBlock(var1, var2, var3, var4, var5, var6);
    }

    public void OnBlockDestroyedWithImproperTool(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        this.dropBlockAsItem(var1, var3, var4, var5, var6, 0);
    }

    protected boolean canSilkHarvest(int var1)
    {
        return true;
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        return !FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true) ? false : super.canPlaceBlockAt(var1, var2, var3, var4);
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        return this.SetFacing(var9, var5);
    }

    public int PreBlockPlacedBy(World var1, int var2, int var3, int var4, int var5, EntityLiving var6)
    {
        int var7 = FCUtilsMisc.ConvertOrientationToFlatBlockFacing(var6);
        return this.SetFacing(var5, var7);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true))
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            var1.playAuxSFX(2272, var2, var3, var4, this.blockID + (var6 << 12));
            this.dropBlockAsItem(var1, var2, var3, var4, var6, 0);
            var1.setBlockToAir(var2, var3, var4);
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (!var1.isRemote && !FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 + 1, var4, 0) && var5 instanceof EntityPlayerMP)
        {
            FCContainerWorkbench var10 = new FCContainerWorkbench(var5.inventory, var1, var2, var3, var4);
            FCBetterThanWolves.ServerOpenCustomInterface((EntityPlayerMP)var5, var10, FCBetterThanWolves.fcVanillaAnvilContainerID);
        }

        return true;
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        int var7 = this.GetFacing(var1, var2, var3, var4);
        FCModelBlock var8 = this.m_model.MakeTemporaryCopy();
        var8.RotateAroundJToFacing(var7);
        return var8.CollisionRayTrace(var1, var2, var3, var4, var5, var6);
    }

    public int GetFacing(int var1)
    {
        int var2 = var1 & 3;
        return (var2 & 1) == 0 ? (var2 == 0 ? 3 : 2) : (var2 == 1 ? 4 : 5);
    }

    public int SetFacing(int var1, int var2)
    {
        byte var3;

        if (var2 == 2)
        {
            var3 = 2;
        }
        else if (var2 == 3)
        {
            var3 = 0;
        }
        else if (var2 == 4)
        {
            var3 = 1;
        }
        else
        {
            var3 = 3;
        }

        var1 &= -4;
        return var1 | var3;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("anvil_base");
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        int var5 = var1.blockAccess.getBlockMetadata(var2, var3, var4);
        int var6 = this.GetFacing(var5);
        FCModelBlock var7 = this.m_model.MakeTemporaryCopy();
        var7.RotateAroundJToFacing(var6);
        var1.SetUvRotateTop(this.ConvertFacingToTopTextureRotation(var6));
        var1.SetUvRotateBottom(this.ConvertFacingToBottomTextureRotation(var6));
        boolean var8 = var7.RenderAsBlock(var1, this, var2, var3, var4);
        var1.ClearUvRotation();
        return var8;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.m_currentBlockRenderer.ShouldSideBeRenderedBasedOnCurrentBounds(var2, var3, var4, var5);
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        this.m_model.RenderAsItemBlock(var1, this, var2);
    }

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        AxisAlignedBB var5 = this.m_model.m_boxSelection.MakeTemporaryCopy();
        var5.RotateAroundJToFacing(this.GetFacing(var1, var2, var3, var4));
        var5.offset((double)var2, (double)var3, (double)var4);
        return var5;
    }
}

package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockAestheticVegetation extends Block
{
    public static final int m_iSubtypeVineTrap = 0;
    public static final int m_iSubtypeVineTrapTriggeredByEntity = 1;
    public static final int m_iSubtypeBloodWoodSapling = 2;
    public static final int m_iSubtypeBloodLeaves = 3;
    public static final int m_iSubtypeVineTrapUpsideDown = 4;
    public static final int m_iSubtypeVineTrapUpsideDownTriggeredByEntity = 5;
    public static final int m_iNumSubtypes = 6;
    private static final double m_dVineTrapHeight = 0.125D;
    private static final float m_fHardness = 0.2F;
    private static final int m_iTickRate = 10;
    public static final int m_iBloodWoodSaplingMinTrunkHeight = 4;
    private static final int m_iVineTrapTextureID = 105;
    private static final int m_iBloodWoodSaplingTextureID = 108;
    private static final int m_iBloodLeavesTextureID = 109;
    private Icon m_IconVineTrap;
    private Icon m_IconSaplingBloodWood;
    private Icon m_IconLeavesBloodWood;

    public FCBlockAestheticVegetation(int var1)
    {
        super(var1, Material.leaves);
        this.setHardness(0.2F);
        this.SetAxesEffectiveOn();
        this.SetBuoyancy(1.0F);
        this.SetFireProperties(FCEnumFlammability.EXTREME);
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("fcBlockAestheticVegetation");
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabDecorations);
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
        if (var9 == 0 && var5 != 1)
        {
            boolean var11 = true;

            if (var5 >= 2 && var7 < 0.5F)
            {
                var11 = false;
            }

            if (var11)
            {
                return 4;
            }
        }

        return var9;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        super.onNeighborBlockChange(var1, var2, var3, var4, var5);
        int var6 = this.GetSubtype(var1, var2, var3, var4);

        if (var6 == 2)
        {
            this.ValidateBloodWoodSapling(var1, var2, var3, var4);
        }
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetSubtype(var1, var2, var3, var4);
        return var5 == 2 ? this.CanBloodwoodSaplingStayAtLocation(var1, var2, var3, var4) : super.canBlockStay(var1, var2, var3, var4);
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        int var2 = var1;

        switch (var1)
        {
            case 0:
            case 1:
            case 4:
            case 5:
                var2 = 0;

            case 2:
            default:
                break;

            case 3:
                var2 = 2;
        }

        return var2;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        if (var5 == 3)
        {
            if (var1.isRemote)
            {
                return;
            }

            int var8 = var1.rand.nextInt(20) != 0 ? 0 : 1;

            for (int var9 = 0; var9 < var8; ++var9)
            {
                if (var1.rand.nextFloat() <= var6)
                {
                    int var10 = this.idDropped(var5, var1.rand, var7);

                    if (var10 > 0)
                    {
                        this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(var10, 1, this.damageDropped(var5)));
                    }
                }
            }
        }
        else
        {
            super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);
        }
    }

    /**
     * Spawns EntityItem in the world for the given ItemStack if the world is not remote.
     */
    protected void dropBlockAsItem_do(World var1, int var2, int var3, int var4, ItemStack var5)
    {
        if (var5.itemID == this.blockID && var5.getItemDamage() == 2)
        {
            if (!var1.isRemote)
            {
                float var6 = 0.7F;
                double var7 = (double)(var1.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
                double var9 = (double)(var1.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
                double var11 = (double)(var1.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
                FCEntityItemBloodWoodSapling var13 = new FCEntityItemBloodWoodSapling(var1, (double)var2 + var7, (double)var3 + var9, (double)var4 + var11, var5);
                var13.delayBeforeCanPickup = 10;
                var1.spawnEntityInWorld(var13);
            }
        }
        else
        {
            super.dropBlockAsItem_do(var1, var2, var3, var4, var5);
        }
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        if (!var1.isRemote && var2.getCurrentEquippedItem() != null && var2.getCurrentEquippedItem().itemID == Item.shears.itemID && var6 == 3)
        {
            this.dropBlockAsItem_do(var1, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcBlockBloodLeaves, 1, 0));
            var2.getCurrentEquippedItem().damageItem(1, var2);
        }
        else
        {
            super.harvestBlock(var1, var2, var3, var4, var5, var6);
        }
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetSubtype(var1, var2, var3, var4);
        return var5 != 0 && var5 != 1 && var5 != 5 && var5 != 4 && var5 != 2 ? super.getCollisionBoundingBoxFromPool(var1, var2, var3, var4) : null;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetSubtype(var1, var2, var3, var4);
        return this.GetBlockBoundsFromPoolBasedOnSubtype(var5);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 10;
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        return 0.8F;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        int var6 = this.GetSubtype(var1, var2, var3, var4);

        if ((var6 == 0 || var6 == 4 || var6 == 1 || var6 == 5 || var6 == 2) && var5.IsAffectedByMovementModifiers() && var5.onGround)
        {
            var5.motionX *= 0.8D;
            var5.motionZ *= 0.8D;
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = this.GetSubtype(var1, var2, var3, var4);

        if (var6 != 1 && var6 != 5)
        {
            if (var6 == 2 && this.ValidateBloodWoodSapling(var1, var2, var3, var4) && var5.nextInt(14) == 0)
            {
                WorldChunkManager var7 = var1.getWorldChunkManager();

                if (var7 != null)
                {
                    BiomeGenBase var8 = var7.getBiomeGenAt(var2, var4);

                    if (var8 instanceof BiomeGenHell)
                    {
                        this.AttemptToGrowBloodwoodSapling(var1, var2, var3, var4, var5);
                    }
                }
            }
        }
        else
        {
            this.SetSubtype(var1, var2, var3, var4, var6 - 1);
        }
    }

    public boolean OnBlockSawed(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetSubtype(var1, var2, var3, var4);
        return var5 == 2 ? false : super.OnBlockSawed(var1, var2, var3, var4);
    }

    public boolean DoesBlockHopperEject(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetSubtype(var1, var2, var3, var4);
        return var5 != 0 && var5 != 1 && var5 != 2 && var5 != 4 && var5 != 5 ? super.DoesBlockHopperEject(var1, var2, var3, var4) : false;
    }

    public boolean canPlaceBlockOnSide(World var1, int var2, int var3, int var4, int var5, ItemStack var6)
    {
        return var6 != null && var6.getItemDamage() == 2 && !this.CanBloodwoodSaplingStayAtLocation(var1, var2, var3, var4) ? false : super.canPlaceBlockOnSide(var1, var2, var3, var4, var5, var6);
    }

    public int GetSubtype(IBlockAccess var1, int var2, int var3, int var4)
    {
        return var1.getBlockMetadata(var2, var3, var4);
    }

    public void SetSubtype(World var1, int var2, int var3, int var4, int var5)
    {
        var1.setBlockMetadata(var2, var3, var4, var5);
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnSubtype(int var1)
    {
        switch (var1)
        {
            case 0:
            case 1:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D);

            case 2:
            case 3:
            default:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

            case 4:
            case 5:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.875D, 0.0D, 1.0D, 1.0D, 1.0D);
        }
    }

    public boolean ValidateBloodWoodSapling(World var1, int var2, int var3, int var4)
    {
        if (!this.canBlockStay(var1, var2, var3, var4))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
            return false;
        }
        else
        {
            return true;
        }
    }

    public void AttemptToGrowBloodwoodSapling(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6;

        for (var6 = var3 + 1; var6 < var3 + 4; ++var6)
        {
            if (var6 >= 256 || !var1.isAirBlock(var2, var6, var4))
            {
                return;
            }
        }

        for (var6 = var3; var6 < var3 + 4 - 1; ++var6)
        {
            var1.setBlockAndMetadataWithNotify(var2, var6, var4, FCBetterThanWolves.fcBloodWood.blockID, 0);
        }

        FCBlockBloodWood var12 = (FCBlockBloodWood)((FCBlockBloodWood)FCBetterThanWolves.fcBloodWood);
        int var7 = var3 + 4 - 1;
        var1.setBlockAndMetadataWithNotify(var2, var7, var4, FCBetterThanWolves.fcBloodWood.blockID, 1);
        var12.GrowLeaves(var1, var2, var7, var4);
        var12.Grow(var1, var2, var7, var4, var5);

        for (int var8 = var2 - 1; var8 <= var2 + 1; ++var8)
        {
            for (int var9 = var7; var9 <= var7 + 1; ++var9)
            {
                for (int var10 = var4 - 1; var10 <= var4 + 1; ++var10)
                {
                    if (var1.getBlockId(var8, var9, var10) == FCBetterThanWolves.fcBloodWood.blockID)
                    {
                        int var11 = var12.GetFacing(var1, var8, var9, var10);

                        if (var11 != 0 && (var8 != var2 || var9 != var7 || var10 != var4))
                        {
                            var12.Grow(var1, var8, var9, var10, var5);
                        }
                    }
                }
            }
        }

        var1.playAuxSFX(2228, var2, var3, var4, 0);
    }

    public boolean CanBloodwoodSaplingStayAtLocation(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3 - 1, var4);
        return var5 == Block.slowSand.blockID ? true : var5 == FCBetterThanWolves.fcPlanter.blockID && ((FCBlockPlanter)FCBetterThanWolves.fcPlanter).GetPlanterType(var1, var2, var3 - 1, var4) == 8;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        Icon var2 = var1.registerIcon("fcBlockVineTrap");
        this.blockIcon = var2;
        this.m_IconVineTrap = var2;
        this.m_IconSaplingBloodWood = var1.registerIcon("fcBlockSaplingBloodWood");
        this.m_IconLeavesBloodWood = var1.registerIcon("fcBlockLeavesBloodWood_old");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        switch (var2)
        {
            case 0:
            case 1:
            case 4:
            case 5:
                return this.m_IconVineTrap;

            case 2:
                return this.m_IconSaplingBloodWood;

            case 3:
                return this.m_IconLeavesBloodWood;

            default:
                return this.blockIcon;
        }
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        var3.add(new ItemStack(var1, 1, 0));
        var3.add(new ItemStack(var1, 1, 2));
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        if (var5 >= 2 && var1.getBlockId(var2, var3, var4) == this.blockID)
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            FCUtilsBlockPos var7;
            int var8;

            if (var6 != 0 && var6 != 1)
            {
                if (var6 == 4 || var6 == 5)
                {
                    var7 = new FCUtilsBlockPos(var2, var3, var4);
                    var7.AddFacingAsOffset(Block.GetOppositeFacing(var5));
                    var8 = var1.getBlockMetadata(var7.i, var7.j, var7.k);

                    if (var8 == 4 || var8 == 5)
                    {
                        return false;
                    }
                }
            }
            else
            {
                var7 = new FCUtilsBlockPos(var2, var3, var4);
                var7.AddFacingAsOffset(Block.GetOppositeFacing(var5));
                var8 = var1.getBlockMetadata(var7.i, var7.j, var7.k);

                if (var8 == 0 || var8 == 1)
                {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 3 ? FCBetterThanWolves.fcBlockBloodLeaves.blockID : this.idDropped(var5, var1.rand, 0);
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 3 ? 0 : super.getDamageValue(var1, var2, var3, var4);
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        int var6 = this.GetSubtype(var5, var2, var3, var4);
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnSubtype(var6));

        switch (var6)
        {
            case 2:
                return var1.renderCrossedSquares(this, var2, var3, var4);

            default:
                return var1.renderStandardBlock(this, var2, var3, var4);
        }
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnSubtype(var2));
        FCClientUtilsRender.RenderInvBlockWithMetadata(var1, this, -0.5F, -0.5F, -0.5F, var2);
    }

    public boolean DoesItemRenderAsBlock(int var1)
    {
        return var1 == 2 ? false : super.DoesItemRenderAsBlock(var1);
    }
}

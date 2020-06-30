package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockSaw extends Block implements FCIBlockMechanical
{
    private static final int m_iPowerChangeTickRate = 10;
    private static final int m_iSawTimeBaseTickRate = 20;
    private static final int m_iSawTimeTickRateVariance = 4;
    public static final float m_fBaseHeight = 0.75F;
    public static final float m_fBladeLength = 0.625F;
    public static final float m_fBladeHalfLength = 0.3125F;
    public static final float m_fBladeWidth = 0.015625F;
    public static final float m_fBladeHalfWidth = 0.0078125F;
    public static final float m_fBladeHeight = 0.25F;

    protected FCBlockSaw(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(2.0F);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);
        this.SetFireProperties(5, 20);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockSaw");
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 10;
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
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, 10);
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
        float var5 = 0.71875F;
        return this.GetBlockBoundsFromPoolForBaseHeight(var1, var2, var3, var4, var5).offset((double)var2, (double)var3, (double)var4);
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetBlockBoundsFromPoolForBaseHeight(var1, var2, var3, var4, 0.75F);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
        {
            this.ScheduleUpdateIfRequired(var1, var2, var3, var4);
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        boolean var6 = this.IsInputtingMechanicalPower(var1, var2, var3, var4);
        boolean var7 = this.IsBlockOn(var1, var2, var3, var4);

        if (var7 != var6)
        {
            this.EmitSawParticles(var1, var2, var3, var4, var5);
            this.SetBlockOn(var1, var2, var3, var4, var6);

            if (var6)
            {
                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "minecart.base", 1.0F + var5.nextFloat() * 0.1F, 1.5F + var5.nextFloat() * 0.1F);
                this.ScheduleUpdateIfRequired(var1, var2, var3, var4);
            }
            else
            {
                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "minecart.base", 1.0F + var5.nextFloat() * 0.1F, 0.75F + var5.nextFloat() * 0.1F);
            }
        }
        else if (var7)
        {
            this.SawBlockToFront(var1, var2, var3, var4, var5);
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID))
        {
            this.ScheduleUpdateIfRequired(var1, var2, var3, var4);
        }
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        if (!var1.isRemote)
        {
            if (this.IsBlockOn(var1, var2, var3, var4) && var5 instanceof EntityLiving)
            {
                int var6 = this.GetFacing(var1, var2, var3, var4);
                float var7 = 0.3125F;
                float var8 = 0.0078125F;
                float var9 = 0.25F;
                AxisAlignedBB var10;

                switch (var6)
                {
                    case 0:
                        var10 = AxisAlignedBB.getAABBPool().getAABB((double)(0.5F - var7), 0.0D, (double)(0.5F - var8), (double)(0.5F + var7), (double)var9, (double)(0.5F + var8));
                        break;

                    case 1:
                        var10 = AxisAlignedBB.getAABBPool().getAABB((double)(0.5F - var7), (double)(1.0F - var9), (double)(0.5F - var8), (double)(0.5F + var7), 1.0D, (double)(0.5F + var8));
                        break;

                    case 2:
                        var10 = AxisAlignedBB.getAABBPool().getAABB((double)(0.5F - var7), (double)(0.5F - var8), 0.0D, (double)(0.5F + var7), (double)(0.5F + var8), (double)var9);
                        break;

                    case 3:
                        var10 = AxisAlignedBB.getAABBPool().getAABB((double)(0.5F - var7), (double)(0.5F - var8), (double)(1.0F - var9), (double)(0.5F + var7), (double)(0.5F + var8), 1.0D);
                        break;

                    case 4:
                        var10 = AxisAlignedBB.getAABBPool().getAABB(0.0D, (double)(0.5F - var8), (double)(0.5F - var7), (double)var9, (double)(0.5F + var8), (double)(0.5F + var7));
                        break;

                    default:
                        var10 = AxisAlignedBB.getAABBPool().getAABB((double)(1.0F - var9), (double)(0.5F - var8), (double)(0.5F - var7), 1.0D, (double)(0.5F + var8), (double)(0.5F + var7));
                }

                var10 = var10.getOffsetBoundingBox((double)var2, (double)var3, (double)var4);
                List var11 = null;
                var11 = var1.getEntitiesWithinAABB(EntityLiving.class, var10);

                if (var11 != null && var11.size() > 0)
                {
                    DamageSource var12 = FCDamageSourceCustom.m_DamageSourceSaw;
                    int var13 = 4;
                    FCUtilsBlockPos var14 = new FCUtilsBlockPos(var2, var3, var4);
                    var14.AddFacingAsOffset(var6);
                    int var15 = var1.getBlockId(var14.i, var14.j, var14.k);
                    int var16 = var1.getBlockMetadata(var14.i, var14.j, var14.k);

                    if (var15 == FCBetterThanWolves.fcAestheticOpaque.blockID && (var16 == 13 || var16 == 12))
                    {
                        var12 = FCDamageSourceCustom.m_DamageSourceChoppingBlock;
                        var13 *= 3;

                        if (var16 == 13)
                        {
                            var1.setBlockMetadataWithNotify(var14.i, var14.j, var14.k, 12);
                        }
                    }

                    for (int var17 = 0; var17 < var11.size(); ++var17)
                    {
                        EntityLiving var18 = (EntityLiving)var11.get(var17);

                        if (var18.attackEntityFrom(var12, var13))
                        {
                            var1.playAuxSFX(2223, var2, var3, var4, var6);
                        }
                    }
                }
            }
        }
    }

    public boolean HasCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var5 != this.GetFacing(var1, var2, var3, var4);
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return Block.GetOppositeFacing(var5) == this.GetFacing(var1, var2, var3, var4);
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemGear.itemID, 1, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, Item.stick.itemID, 2, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 3, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, Item.ingotIron.itemID, 2, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemNuggetIron.itemID, 4, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemStrap.itemID, 3, 0, var6);
        return true;
    }

    public int GetFacing(int var1)
    {
        return var1 & 7;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 &= -8;
        var1 |= var2;
        return var1;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        return var5 != 0;
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        return var5 != 0 && var5 != 1;
    }

    public boolean RotateAroundJAxis(World var1, int var2, int var3, int var4, boolean var5)
    {
        if (super.RotateAroundJAxis(var1, var2, var3, var4, var5))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
            FCUtilsMechPower.DestroyHorizontallyAttachedAxles(var1, var2, var3, var4);
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
        var1.notifyBlockChange(var2, var3, var4, this.blockID);
        return true;
    }

    public boolean IsIncineratedInCrucible()
    {
        return false;
    }

    protected boolean IsCurrentPowerStateValid(World var1, int var2, int var3, int var4)
    {
        boolean var5 = this.IsInputtingMechanicalPower(var1, var2, var3, var4);
        boolean var6 = this.IsBlockOn(var1, var2, var3, var4);
        return var6 == var5;
    }

    public boolean IsBlockOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 8) > 0;
    }

    public void SetBlockOn(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & 7;

        if (var5)
        {
            var6 |= 8;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    protected void ScheduleUpdateIfRequired(World var1, int var2, int var3, int var4)
    {
        if (!this.IsCurrentPowerStateValid(var1, var2, var3, var4))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, 10);
        }
        else if (this.IsBlockOn(var1, var2, var3, var4))
        {
            int var5 = this.GetFacing(var1, var2, var3, var4);
            FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4, var5);
            Block var7 = Block.blocksList[var1.getBlockId(var6.i, var6.j, var6.k)];

            if (var7 != null && (var7.blockMaterial.isSolid() || var7.GetItemIDDroppedOnSaw(var1, var6.i, var6.j, var6.k) > 0 || var7.DoesBlockDropAsItemOnSaw(var1, var6.i, var6.j, var6.k)))
            {
                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "minecart.base", 1.5F + var1.rand.nextFloat() * 0.1F, 1.9F + var1.rand.nextFloat() * 0.1F);
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, 20 + var1.rand.nextInt(4));
            }
        }
    }

    public AxisAlignedBB GetBlockBoundsFromPoolForBaseHeight(IBlockAccess var1, int var2, int var3, int var4, float var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);

        switch (var6)
        {
            case 0:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, (double)(1.0F - var5), 0.0D, 1.0D, 1.0D, 1.0D);

            case 1:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, (double)var5, 1.0D);

            case 2:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, (double)(1.0F - var5), 1.0D, 1.0D, 1.0D);

            case 3:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, (double)var5);

            case 4:
                return AxisAlignedBB.getAABBPool().getAABB((double)(1.0F - var5), 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

            default:
                return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, (double)var5, 1.0D, 1.0D);
        }
    }

    void EmitSawParticles(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        float var7 = (float)var2;
        float var8 = (float)var3;
        float var9 = (float)var4;
        float var10 = 0.0F;
        float var11 = 0.0F;

        switch (var6)
        {
            case 0:
                var7 += 0.5F;
                var9 += 0.5F;
                var10 = 1.0F;
                break;

            case 1:
                var7 += 0.5F;
                var9 += 0.5F;
                ++var8;
                var10 = 1.0F;
                break;

            case 2:
                var7 += 0.5F;
                var8 += 0.5F;
                var10 = 1.0F;
                break;

            case 3:
                var7 += 0.5F;
                var8 += 0.5F;
                ++var9;
                var10 = 1.0F;
                break;

            case 4:
                var8 += 0.5F;
                var9 += 0.5F;
                var11 = 1.0F;
                break;

            default:
                var8 += 0.5F;
                var9 += 0.5F;
                ++var7;
                var11 = 1.0F;
        }

        for (int var12 = 0; var12 < 5; ++var12)
        {
            float var13 = var7 + (var5.nextFloat() - 0.5F) * var10;
            float var14 = var8 + var5.nextFloat() * 0.1F;
            float var15 = var9 + (var5.nextFloat() - 0.5F) * var11;
            var1.spawnParticle("smoke", (double)var13, (double)var14, (double)var15, 0.0D, 0.0D, 0.0D);
        }
    }

    protected void SawBlockToFront(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4, var6);

        if (!var1.isAirBlock(var7.i, var7.j, var7.k) && !this.HandleSawingExceptionCases(var1, var7.i, var7.j, var7.k, var2, var3, var4, var6, var5))
        {
            Block var8 = Block.blocksList[var1.getBlockId(var7.i, var7.j, var7.k)];

            if (var8 != null)
            {
                if (var8.DoesBlockBreakSaw(var1, var7.i, var7.j, var7.k))
                {
                    this.BreakSaw(var1, var2, var3, var4);
                }
                else if (var8.OnBlockSawed(var1, var7.i, var7.j, var7.k, var2, var3, var4))
                {
                    this.EmitSawParticles(var1, var7.i, var7.j, var7.k, var5);
                }
            }
        }
    }

    private boolean HandleSawingExceptionCases(World var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, Random var9)
    {
        int var10 = var1.getBlockId(var2, var3, var4);

        if (var10 == Block.pistonMoving.blockID)
        {
            return true;
        }
        else
        {
            int var11 = var1.getBlockMetadata(var2, var3, var4);
            boolean var12 = false;
            int var13;

            if (var10 == Block.wood.blockID)
            {
                var11 &= 3;

                for (var13 = 0; var13 < 4; ++var13)
                {
                    FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, Block.planks.blockID, var11);
                }

                for (var13 = 0; var13 < 2; ++var13)
                {
                    FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 0);
                }

                FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcItemBark.itemID, var11);
                var12 = true;
            }
            else if (var10 == Block.stairsWoodOak.blockID)
            {
                FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcBlockWoodSidingItemStubID, 0);
                FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 0);
                var12 = true;
            }
            else if (var10 == Block.stairsWoodSpruce.blockID)
            {
                FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1);
                FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1);
                var12 = true;
            }
            else if (var10 == Block.stairsWoodBirch.blockID)
            {
                FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcBlockWoodSidingItemStubID, 2);
                FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 2);
                var12 = true;
            }
            else if (var10 == Block.stairsWoodJungle.blockID)
            {
                FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcBlockWoodSidingItemStubID, 3);
                FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 3);
                var12 = true;
            }
            else if (var10 == Block.woodDoubleSlab.blockID)
            {
                for (var13 = 0; var13 < 2; ++var13)
                {
                    FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, Block.woodSingleSlab.blockID, var11 & 7);
                }

                var12 = true;
            }
            else if (var10 == Block.woodSingleSlab.blockID)
            {
                for (var13 = 0; var13 < 2; ++var13)
                {
                    FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcBlockWoodMouldingItemStubID, var11 & 7);
                }

                var12 = true;
            }
            else if (var10 == Block.stoneDoubleSlab.blockID && (var11 & 7) == 2)
            {
                for (var13 = 0; var13 < 2; ++var13)
                {
                    FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, Block.woodSingleSlab.blockID, 0);
                }

                var12 = true;
            }
            else if (var10 == Block.stoneSingleSlab.blockID && (var11 & 7) == 2)
            {
                for (var13 = 0; var13 < 2; ++var13)
                {
                    FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 0);
                }

                var12 = true;
            }

            if (var12)
            {
                this.EmitSawParticles(var1, var2, var3, var4, var9);
                var1.setBlockToAir(var2, var3, var4);
            }

            return var12;
        }
    }

    public void BreakSaw(World var1, int var2, int var3, int var4)
    {
        this.DropComponentItemsOnBadBreak(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 1.0F);
        var1.playAuxSFX(2235, var2, var3, var4, 0);
        var1.setBlockWithNotify(var2, var3, var4, 0);
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
        int var6 = this.GetFacing(var1, var2, var3, var4);
        return var5 != var6;
    }

    public boolean IsOutputtingMechanicalPower(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public void Overpower(World var1, int var2, int var3, int var4)
    {
        this.BreakSaw(var1, var2, var3, var4);
    }
}

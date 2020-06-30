package net.minecraft.src;

import java.util.Random;

public class FCBlockCampfire extends BlockContainer
{
    public final int m_iFireLevel;
    public static final int m_iCampfireFuelStateNormal = 0;
    public static final int m_iCampfireFuelStateBurnedOut = 1;
    public static final int m_iCampfireFuelStateSmouldering = 2;
    private FCModelBlockCampfire m_modelCampfire = new FCModelBlockCampfire();
    private FCModelBlock m_modelCollisionBase;
    private FCModelBlock m_modelCollisionWithSpit;
    public static FCBlockCampfire[] m_fireLevelBlockArray = new FCBlockCampfire[4];
    public static boolean m_bCampfireChangingState = false;
    private static final float m_fSpitThickness = 0.0625F;
    private static final float m_fHalfSpitThickness = 0.03125F;
    private static final float m_fSpitHeight = 0.75F;
    private static final float m_fSpitMinY = 0.71875F;
    private static final float m_fSpitMaxY = 0.78125F;
    private static final float m_fSpitSupportWidth = 0.0625F;
    private static final float m_fHalfSpitSupportWidth = 0.03125F;
    private static final float m_fSpitSupportBorder = 0.03125F;
    private static final float m_fSpitForkWidth = 0.0625F;
    private static final float m_fSpitForkHeight = 0.1875F;
    private static final float m_fSpitForkHeightOffset = 0.0625F;
    private static final float m_fSpitForkMinY = 0.65625F;
    private static final float m_fSpitForkMaxY = 0.84375F;
    private static final double m_dSpitCollisionHeight = 0.84375D;
    private static final double m_dSpitCollisionWidth = 0.1875D;
    private static final double m_dSpitCollisionHalfWidth = 0.09375D;

    public FCBlockCampfire(int var1, int var2)
    {
        super(var1, Material.circuits);
        this.m_iFireLevel = var2;
        m_fireLevelBlockArray[var2] = this;
        this.setHardness(0.1F);
        this.SetBuoyant();
        this.SetFurnaceBurnTime(4 * FCEnumFurnaceBurnTime.SHAFT.m_iBurnTime);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockCampfire");
        this.InitModels();
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityCampfire();
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        if (!m_bCampfireChangingState)
        {
            FCTileEntityCampfire var7 = (FCTileEntityCampfire)var1.getBlockTileEntity(var2, var3, var4);
            var7.EjectContents();
            super.breakBlock(var1, var2, var3, var4, var5, var6);
        }
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
        return null;
    }

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetHasSpit(var1, var2, var3, var4) ? AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.84375D, 1.0D) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
    }

    /**
     * Return true if a player with Silk Touch can harvest this block directly, and not its normal drops.
     */
    protected boolean canSilkHarvest()
    {
        return false;
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
        return this.SetIAligned(var9, this.IsFacingIAligned(var5));
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
    {
        int var7 = FCUtilsMisc.ConvertOrientationToFlatBlockFacingReversed(var5);
        this.SetIAligned(var1, var2, var3, var4, this.IsFacingIAligned(var7));
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return this.m_iFireLevel == 0 && this.GetFuelState(var1) == 0 ? super.idDropped(var1, var2, var3) : 0;
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 2;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true))
        {
            if (this.m_iFireLevel > 0)
            {
                var1.playAuxSFX(2227, var2, var3, var4, 1);
            }

            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockToAir(var2, var3, var4);
        }
    }

    public boolean GetCanBeSetOnFireDirectly(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.m_iFireLevel == 0 && this.GetFuelState(var1, var2, var3, var4) == 0;
    }

    public boolean SetOnFireDirectly(World var1, int var2, int var3, int var4)
    {
        if (!this.GetCanBeSetOnFireDirectly(var1, var2, var3, var4))
        {
            return false;
        }
        else
        {
            if (!this.IsRainingOnCampfire(var1, var2, var3, var4))
            {
                this.ChangeFireLevel(var1, var2, var3, var4, 1, var1.getBlockMetadata(var2, var3, var4));
                FCTileEntityCampfire var5 = (FCTileEntityCampfire)var1.getBlockTileEntity(var2, var3, var4);
                var5.OnFirstLit();
                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "mob.ghast.fireball", 1.0F, var1.rand.nextFloat() * 0.4F + 0.8F);

                if (!Block.portal.tryToCreatePortal(var1, var2, var3, var4))
                {
                    int var6 = var1.getBlockId(var2, var3 - 1, var4);

                    if (var6 == Block.netherrack.blockID || var6 == FCBetterThanWolves.fcBlockNetherrackFalling.blockID)
                    {
                        var1.setBlockWithNotify(var2, var3, var4, Block.fire.blockID);
                    }
                }
            }
            else
            {
                var1.playAuxSFX(2227, var2, var3, var4, 0);
            }

            return true;
        }
    }

    public int GetChanceOfFireSpreadingDirectlyTo(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.m_iFireLevel == 0 && this.GetFuelState(var1, var2, var3, var4) == 0 ? 60 : 0;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        ItemStack var10 = var5.getCurrentEquippedItem();
        ItemStack var13;

        if (var10 != null)
        {
            Item var11 = var10.getItem();
            FCTileEntityCampfire var12;

            if (!this.GetHasSpit(var1, var2, var3, var4))
            {
                if (var11 == FCBetterThanWolves.fcItemChiselWood)
                {
                    this.SetHasSpit(var1, var2, var3, var4, true);
                    var12 = (FCTileEntityCampfire)var1.getBlockTileEntity(var2, var3, var4);
                    var12.SetSpitStack(var10);
                    --var10.stackSize;
                    return true;
                }
            }
            else
            {
                var12 = (FCTileEntityCampfire)var1.getBlockTileEntity(var2, var3, var4);
                var13 = var12.GetCookStack();

                if (var13 == null)
                {
                    if (this.IsValidCookItem(var10))
                    {
                        ItemStack var14 = var12.GetSpitStack();

                        if (var14.getItemDamage() == 0)
                        {
                            var12.SetCookStack(var10);
                        }
                        else
                        {
                            var12.SetSpitStack((ItemStack)null);
                            this.SetHasSpit(var1, var2, var3, var4, false);

                            if (!var1.isRemote)
                            {
                                ItemStack var15 = var10.copy();
                                var15.stackSize = 1;
                                FCUtilsItem.EjectStackWithRandomOffset(var1, var2, var3, var4, var15);
                                FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 0);
                                var1.playAuxSFX(2271, var2, var3, var4, 0);
                            }
                        }

                        --var10.stackSize;
                        return true;
                    }
                }
                else if (var13.itemID == var10.itemID && var10.stackSize < var10.getMaxStackSize())
                {
                    var5.worldObj.playSoundAtEntity(var5, "random.pop", 0.2F, ((var5.rand.nextFloat() - var5.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    ++var10.stackSize;
                    var12.SetCookStack((ItemStack)null);
                    return true;
                }
            }

            if (this.m_iFireLevel > 0 || this.GetFuelState(var1, var2, var3, var4) == 2)
            {
                int var17 = var10.getItemDamage();

                if (var11.GetCanBeFedDirectlyIntoCampfire(var17))
                {
                    if (!var1.isRemote)
                    {
                        FCTileEntityCampfire var19 = (FCTileEntityCampfire)var1.getBlockTileEntity(var2, var3, var4);
                        var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "mob.ghast.fireball", 0.2F + var1.rand.nextFloat() * 0.1F, var1.rand.nextFloat() * 0.25F + 1.25F);
                        var19.AddBurnTime(var11.GetCampfireBurnTime(var17));
                    }

                    --var10.stackSize;
                    return true;
                }
            }
        }
        else
        {
            FCTileEntityCampfire var16 = (FCTileEntityCampfire)var1.getBlockTileEntity(var2, var3, var4);
            ItemStack var18 = var16.GetCookStack();

            if (var18 != null)
            {
                FCUtilsItem.GivePlayerStackOrEject(var5, var18, var2, var3, var4);
                var16.SetCookStack((ItemStack)null);
                return true;
            }

            var13 = var16.GetSpitStack();

            if (var13 != null)
            {
                FCUtilsItem.GivePlayerStackOrEject(var5, var13, var2, var3, var4);
                var16.SetSpitStack((ItemStack)null);
                this.SetHasSpit(var1, var2, var3, var4, false);
                return true;
            }
        }

        return false;
    }

    public boolean ShouldDeleteTileEntityOnBlockChange(int var1)
    {
        for (int var2 = 0; var2 < m_fireLevelBlockArray.length; ++var2)
        {
            if (m_fireLevelBlockArray[var2].blockID == var1)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World var1, int var2, int var3, int var4, Entity var5)
    {
        if (!var1.isRemote && var5.isEntityAlive() && (this.m_iFireLevel > 0 || this.GetFuelState(var1, var2, var3, var4) == 2) && var5 instanceof EntityItem)
        {
            EntityItem var6 = (EntityItem)var5;
            ItemStack var7 = var6.getEntityItem();
            Item var8 = var7.getItem();
            int var9 = var8.GetCampfireBurnTime(var7.getItemDamage());

            if (var9 > 0)
            {
                var9 *= var7.stackSize;
                FCTileEntityCampfire var10 = (FCTileEntityCampfire)var1.getBlockTileEntity(var2, var3, var4);
                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "mob.ghast.fireball", var1.rand.nextFloat() * 0.1F + 0.2F, var1.rand.nextFloat() * 0.25F + 1.25F);
                var10.AddBurnTime(var9);
                var5.setDead();
            }
        }
    }

    public boolean GetDoesFireDamageToEntities(World var1, int var2, int var3, int var4, Entity var5)
    {
        return this.m_iFireLevel > 2 || this.m_iFireLevel == 2 && var5 instanceof EntityLiving;
    }

    public boolean GetCanBlockLightItemOnFire(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.m_iFireLevel > 0;
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        int var7 = var1.getBlockMetadata(var2, var3, var4);
        FCModelBlock var8 = this.m_modelCollisionBase;

        if (this.GetHasSpit(var7))
        {
            var8 = this.m_modelCollisionWithSpit;
        }

        if (this.GetIsIAligned(var7))
        {
            var8 = var8.MakeTemporaryCopy();
            var8.RotateAroundJToFacing(4);
        }

        return var8.CollisionRayTrace(var1, var2, var3, var4, var5, var6);
    }

    public void OnFluidFlowIntoBlock(World var1, int var2, int var3, int var4, BlockFluid var5)
    {
        if (this.m_iFireLevel > 0)
        {
            var1.playAuxSFX(2227, var2, var3, var4, 0);
        }

        super.OnFluidFlowIntoBlock(var1, var2, var3, var4, var5);
    }

    public boolean CanBeCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        return true;
    }

    public void OnCrushedByFallingEntity(World var1, int var2, int var3, int var4, EntityFallingSand var5)
    {
        if (!var1.isRemote && this.m_iFireLevel > 0)
        {
            var1.playAuxSFX(2227, var2, var3, var4, 0);
        }
    }

    public int GetFacing(int var1)
    {
        return this.GetIsIAligned(var1) ? 4 : 2;
    }

    public int SetFacing(int var1, int var2)
    {
        return this.SetIAligned(var1, this.IsFacingIAligned(var2));
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean RotateAroundJAxis(World var1, int var2, int var3, int var4, boolean var5)
    {
        this.SetIAligned(var1, var2, var3, var4, !this.GetIsIAligned(var1, var2, var3, var4));
        return true;
    }

    public int RotateMetadataAroundJAxis(int var1, boolean var2)
    {
        return this.SetIAligned(var1, !this.GetIsIAligned(var1));
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return this.m_iFireLevel == 0 && var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4);
    }

    public float GroundCoverRestingOnVisualOffset(IBlockAccess var1, int var2, int var3, int var4)
    {
        return -1.0F;
    }

    public void SetIAligned(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.SetIAligned(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetIAligned(int var1, boolean var2)
    {
        if (var2)
        {
            var1 |= 1;
        }
        else
        {
            var1 &= -2;
        }

        return var1;
    }

    public boolean GetIsIAligned(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsIAligned(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean GetIsIAligned(int var1)
    {
        return (var1 & 1) != 0;
    }

    public boolean IsFacingIAligned(int var1)
    {
        return var1 >= 4;
    }

    public void SetHasSpit(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.SetHasSpit(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetHasSpit(int var1, boolean var2)
    {
        if (var2)
        {
            var1 |= 2;
        }
        else
        {
            var1 &= -3;
        }

        return var1;
    }

    public boolean GetHasSpit(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetHasSpit(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean GetHasSpit(int var1)
    {
        return (var1 & 2) != 0;
    }

    public void SetFuelState(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.SetFuelState(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public int SetFuelState(int var1, int var2)
    {
        var1 &= -13;
        return var1 | var2 << 2;
    }

    public int GetFuelState(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetFuelState(var1.getBlockMetadata(var2, var3, var4));
    }

    public int GetFuelState(int var1)
    {
        return (var1 & 12) >> 2;
    }

    public boolean IsValidCookItem(ItemStack var1)
    {
        return FCCraftingManagerCampfire.instance.GetRecipeResult(var1.getItem().itemID) != null;
    }

    public void ExtinguishFire(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (var5)
        {
            var6 = this.SetFuelState(var6, 2);
        }
        else
        {
            var6 = this.SetFuelState(var6, 1);
        }

        this.ChangeFireLevel(var1, var2, var3, var4, 0, var6);

        if (!var1.isRemote)
        {
            var1.playAuxSFX(2227, var2, var3, var4, 1);
        }
    }

    public void RelightFire(World var1, int var2, int var3, int var4)
    {
        this.ChangeFireLevel(var1, var2, var3, var4, 1, this.SetFuelState(var1.getBlockMetadata(var2, var3, var4), 0));
    }

    public void StopSmouldering(World var1, int var2, int var3, int var4)
    {
        this.SetFuelState(var1, var2, var3, var4, 1);
    }

    public void ChangeFireLevel(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        m_bCampfireChangingState = true;
        var1.setBlockAndMetadataWithNotify(var2, var3, var4, m_fireLevelBlockArray[var5].blockID, var6);
        m_bCampfireChangingState = false;
    }

    public boolean IsRainingOnCampfire(World var1, int var2, int var3, int var4)
    {
        return var1.IsRainingAtPos(var2, var3, var4);
    }

    private void InitModels()
    {
        this.m_modelCollisionBase = new FCModelBlock();
        this.m_modelCollisionWithSpit = new FCModelBlock();
        this.m_modelCollisionBase.AddBox(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
        this.m_modelCollisionWithSpit.AddBox(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D);
        this.m_modelCollisionWithSpit.AddBox(0.0D, 0.0D, 0.40625D, 1.0D, 0.84375D, 0.59375D);
    }
}

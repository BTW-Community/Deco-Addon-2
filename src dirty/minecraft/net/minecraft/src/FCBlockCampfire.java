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
    private Icon m_spitIcon;
    private Icon m_spitSupportIcon;
    private Icon m_burnedIcon;
    private Icon m_embersIcon;
    static final double[] m_dFireAnimationScaleArray = new double[] {0.0D, 0.25D, 0.5D, 0.875D};

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

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("fcBlockCampfire");
        this.m_spitIcon = var1.registerIcon("fcBlockCampfire_spit");
        this.m_spitSupportIcon = var1.registerIcon("fcBlockCampfire_support");
        this.m_burnedIcon = var1.registerIcon("fcBlockCampfire_burned");
        this.m_embersIcon = var1.registerIcon("fcOverlayEmbers");
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return true;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        if (this.RenderCampfireModel(var1, var2, var3, var4))
        {
            if (this.GetHasSpit(var1.blockAccess, var2, var3, var4))
            {
                this.RenderSpit(var1, var2, var3, var4);
            }

            if (this.m_iFireLevel > 0 && !var1.hasOverrideBlockTexture())
            {
                this.RenderFirePortion(var1, var2, var3, var4);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public void RenderBlockSecondPass(RenderBlocks var1, int var2, int var3, int var4, boolean var5)
    {
        if (var5 && this.m_iFireLevel == 0 && this.GetFuelState(var1.blockAccess, var2, var3, var4) == 2)
        {
            this.RenderCampfireModelEmbers(var1, var2, var3, var4);
        }
    }

    public void RenderBlockAsItem(RenderBlocks var1, int var2, float var3)
    {
        this.m_modelCampfire.RenderAsItemBlock(var1, this, var2);
    }

    private boolean RenderCampfireModelEmbers(RenderBlocks var1, int var2, int var3, int var4)
    {
        int var5 = var1.blockAccess.getBlockMetadata(var2, var3, var4);
        FCModelBlock var6;

        if (!FCUtilsWorld.IsGroundCoverOnBlock(var1.blockAccess, var2, var3, var4))
        {
            var6 = this.m_modelCampfire.MakeTemporaryCopy();
        }
        else
        {
            var6 = this.m_modelCampfire.m_modelInSnow.MakeTemporaryCopy();
        }

        if (this.GetIsIAligned(var5))
        {
            var6.RotateAroundJToFacing(4);
        }

        return var6.RenderAsBlockFullBrightWithTexture(var1, this, var2, var3, var4, this.m_embersIcon);
    }

    private boolean RenderCampfireModel(RenderBlocks var1, int var2, int var3, int var4)
    {
        int var5 = var1.blockAccess.getBlockMetadata(var2, var3, var4);
        FCModelBlock var6;

        if (!FCUtilsWorld.IsGroundCoverOnBlock(var1.blockAccess, var2, var3, var4))
        {
            var6 = this.m_modelCampfire.MakeTemporaryCopy();
        }
        else
        {
            var6 = this.m_modelCampfire.m_modelInSnow.MakeTemporaryCopy();
        }

        if (this.GetIsIAligned(var5))
        {
            var6.RotateAroundJToFacing(4);
        }

        return this.GetFuelState(var5) != 0 && !var1.hasOverrideBlockTexture() ? var6.RenderAsBlockWithTexture(var1, this, var2, var3, var4, this.m_burnedIcon) : var6.RenderAsBlock(var1, this, var2, var3, var4);
    }

    private void RenderSpit(RenderBlocks var1, int var2, int var3, int var4)
    {
        boolean var5 = this.GetIsIAligned(var1.blockAccess, var2, var3, var4);
        FCClientUtilsRender.SetRenderBoundsWithAxisAlignment(var1, 0.0F, 0.71875F, 0.46875F, 1.0F, 0.78125F, 0.53125F, var5);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var1, this, var2, var3, var4, this.m_spitIcon);
        boolean var6 = true;

        if (var5 && !FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1.blockAccess, var2, var3, var4 - 1, 3) || !var5 && !FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1.blockAccess, var2 - 1, var3, var4, 5))
        {
            this.RenderSpitSupport(var1, var2, var3, var4, 0.03125F);
        }

        if (var5 && !FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1.blockAccess, var2, var3, var4 + 1, 2) || !var5 && !FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1.blockAccess, var2 + 1, var3, var4, 4))
        {
            this.RenderSpitSupport(var1, var2, var3, var4, 0.90625F);
        }
    }

    private void RenderSpitSupport(RenderBlocks var1, int var2, int var3, int var4, float var5)
    {
        boolean var6 = this.GetIsIAligned(var1.blockAccess, var2, var3, var4);
        FCClientUtilsRender.SetRenderBoundsWithAxisAlignment(var1, var5, 0.0F, 0.46875F, var5 + 0.0625F, 0.71875F, 0.53125F, var6);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var1, this, var2, var3, var4, this.m_spitSupportIcon);
        FCClientUtilsRender.SetRenderBoundsWithAxisAlignment(var1, var5, 0.65625F, 0.53125F, var5 + 0.0625F, 0.84375F, 0.59375F, var6);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var1, this, var2, var3, var4, this.m_spitSupportIcon);
        FCClientUtilsRender.SetRenderBoundsWithAxisAlignment(var1, var5, 0.65625F, 0.40625F, var5 + 0.0625F, 0.84375F, 0.46875F, var6);
        FCClientUtilsRender.RenderStandardBlockWithTexture(var1, this, var2, var3, var4, this.m_spitSupportIcon);
    }

    private void RenderFirePortion(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        Tessellator var6 = Tessellator.instance;
        double var7 = m_dFireAnimationScaleArray[this.m_iFireLevel];
        double var9 = (double)var2;
        double var11 = (double)var3;
        double var13 = (double)var4;
        Icon var15 = Block.fire.func_94438_c(0);
        Icon var16 = Block.fire.func_94438_c(1);

        if ((var2 + var4 & 1) != 0)
        {
            var15 = Block.fire.func_94438_c(1);
            var16 = Block.fire.func_94438_c(0);
        }

        var6.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        var6.setBrightness(this.getMixedBrightnessForBlock(var5, var2, var3, var4));
        double var17 = (double)var15.getMinU();
        double var19 = (double)var15.getMinV();
        double var21 = (double)var15.getMaxU();
        double var23 = (double)var15.getMaxV();
        double var25 = 1.4D * var7;
        double var27 = 0.5D - 0.5D * var7;
        double var29 = 0.5D + 0.5D * var7;
        double var31 = 0.2D * var7;
        double var33 = var9 + 0.5D + var31;
        double var35 = var9 + 0.5D - var31;
        double var37 = var13 + 0.5D + var31;
        double var39 = var13 + 0.5D - var31;
        var31 = 0.3D * var7;
        double var41 = var9 + 0.5D - var31;
        double var43 = var9 + 0.5D + var31;
        double var45 = var13 + 0.5D - var31;
        double var47 = var13 + 0.5D + var31;
        var6.addVertexWithUV(var41, var11 + var25, var13 + var29, var21, var19);
        var6.addVertexWithUV(var33, var11 + 0.0D, var13 + var29, var21, var23);
        var6.addVertexWithUV(var33, var11 + 0.0D, var13 + var27, var17, var23);
        var6.addVertexWithUV(var41, var11 + var25, var13 + var27, var17, var19);
        var6.addVertexWithUV(var43, var11 + var25, var13 + var27, var21, var19);
        var6.addVertexWithUV(var35, var11 + 0.0D, var13 + var27, var21, var23);
        var6.addVertexWithUV(var35, var11 + 0.0D, var13 + var29, var17, var23);
        var6.addVertexWithUV(var43, var11 + var25, var13 + var29, var17, var19);
        var17 = (double)var16.getMinU();
        var19 = (double)var16.getMinV();
        var21 = (double)var16.getMaxU();
        var23 = (double)var16.getMaxV();
        var6.addVertexWithUV(var9 + var29, var11 + var25, var47, var21, var19);
        var6.addVertexWithUV(var9 + var29, var11 + 0.0D, var39, var21, var23);
        var6.addVertexWithUV(var9 + var27, var11 + 0.0D, var39, var17, var23);
        var6.addVertexWithUV(var9 + var27, var11 + var25, var47, var17, var19);
        var6.addVertexWithUV(var9 + var27, var11 + var25, var45, var21, var19);
        var6.addVertexWithUV(var9 + var27, var11 + 0.0D, var37, var21, var23);
        var6.addVertexWithUV(var9 + var29, var11 + 0.0D, var37, var17, var23);
        var6.addVertexWithUV(var9 + var29, var11 + var25, var45, var17, var19);
        var31 = 0.5D * var7;
        var33 = var9 + 0.5D - var31;
        var35 = var9 + 0.5D + var31;
        var37 = var13 + 0.5D - var31;
        var39 = var13 + 0.5D + var31;
        var31 = 0.4D * var7;
        var41 = var9 + 0.5D - var31;
        var43 = var9 + 0.5D + var31;
        var45 = var13 + 0.5D - var31;
        var47 = var13 + 0.5D + var31;
        var6.addVertexWithUV(var41, var11 + var25, var13 + var27, var17, var19);
        var6.addVertexWithUV(var33, var11 + 0.0D, var13 + var27, var17, var23);
        var6.addVertexWithUV(var33, var11 + 0.0D, var13 + var29, var21, var23);
        var6.addVertexWithUV(var41, var11 + var25, var13 + var29, var21, var19);
        var6.addVertexWithUV(var43, var11 + var25, var13 + var29, var17, var19);
        var6.addVertexWithUV(var35, var11 + 0.0D, var13 + var29, var17, var23);
        var6.addVertexWithUV(var35, var11 + 0.0D, var13 + var27, var21, var23);
        var6.addVertexWithUV(var43, var11 + var25, var13 + var27, var21, var19);
        var17 = (double)var15.getMinU();
        var19 = (double)var15.getMinV();
        var21 = (double)var15.getMaxU();
        var23 = (double)var15.getMaxV();
        var6.addVertexWithUV(var9 + var27, var11 + var25, var47, var17, var19);
        var6.addVertexWithUV(var9 + var27, var11 + 0.0D, var39, var17, var23);
        var6.addVertexWithUV(var9 + var29, var11 + 0.0D, var39, var21, var23);
        var6.addVertexWithUV(var9 + var29, var11 + var25, var47, var21, var19);
        var6.addVertexWithUV(var9 + var29, var11 + var25, var45, var17, var19);
        var6.addVertexWithUV(var9 + var29, var11 + 0.0D, var37, var17, var23);
        var6.addVertexWithUV(var9 + var27, var11 + 0.0D, var37, var21, var23);
        var6.addVertexWithUV(var9 + var27, var11 + var25, var45, var21, var19);
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        double var8;
        double var10;

        if (this.m_iFireLevel > 1)
        {
            for (int var6 = 0; var6 < this.m_iFireLevel; ++var6)
            {
                double var7 = (double)((float)var2 + var5.nextFloat());
                double var9 = (double)((float)var3 + 0.5F + var5.nextFloat() * 0.5F);
                double var11 = (double)((float)var4 + var5.nextFloat());
                var1.spawnParticle("smoke", var7, var9, var11, 0.0D, 0.0D, 0.0D);
            }

            FCTileEntityCampfire var14 = (FCTileEntityCampfire)var1.getBlockTileEntity(var2, var3, var4);
            double var12;
            int var17;

            if (var14.GetIsFoodBurning())
            {
                for (var17 = 0; var17 < 1; ++var17)
                {
                    var8 = (double)((float)var2 + 0.375F + var5.nextFloat() * 0.25F);
                    var10 = (double)((float)var3 + 0.5F + var5.nextFloat() * 0.5F);
                    var12 = (double)((float)var4 + 0.375F + var5.nextFloat() * 0.25F);
                    var1.spawnParticle("largesmoke", var8, var10, var12, 0.0D, 0.0D, 0.0D);
                }
            }
            else if (var14.GetIsCooking())
            {
                for (var17 = 0; var17 < 1; ++var17)
                {
                    var8 = (double)((float)var2 + 0.375F + var5.nextFloat() * 0.25F);
                    var10 = (double)((float)var3 + 0.5F + var5.nextFloat() * 0.5F);
                    var12 = (double)((float)var4 + 0.375F + var5.nextFloat() * 0.25F);
                    var1.spawnParticle("fcwhitesmoke", var8, var10, var12, 0.0D, 0.0D, 0.0D);
                }
            }
        }
        else if (this.m_iFireLevel == 1 || this.GetFuelState(var1, var2, var3, var4) == 2)
        {
            double var15 = (double)var2 + 0.375D + var5.nextDouble() * 0.25D;
            var8 = (double)var3 + 0.25D + var5.nextDouble() * 0.25D;
            var10 = (double)var4 + 0.375D + var5.nextDouble() * 0.25D;
            var1.spawnParticle("smoke", var15, var8, var10, 0.0D, 0.0D, 0.0D);
        }

        if (this.m_iFireLevel > 0 && var5.nextInt(24) == 0)
        {
            float var16 = (float)this.m_iFireLevel * 0.25F + var5.nextFloat();
            var1.playSound((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "fire.fire", var16, var5.nextFloat() * 0.7F + 0.3F, false);
        }
    }
}

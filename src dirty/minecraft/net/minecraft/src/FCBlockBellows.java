package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockBellows extends Block implements FCIBlockMechanical
{
    private static final int m_iBellowsTickRate = 35;
    public static final float m_fBellowsContractedHeight = 0.6875F;
    private static final double m_dBlowItemStrength = 0.2D;
    private static final double m_dParticleSpeed = 0.10000000149011612D;
    private Icon[] m_IconBySideArray = new Icon[6];
    private Icon m_IconFront;

    protected FCBlockBellows(int var1)
    {
        super(var1, Material.wood);
        this.setHardness(2.0F);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.6875D, 1.0D);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockBellows");
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 35;
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        if (var5 < 2)
        {
            var5 = 2;
        }

        return this.SetFacing(var9, var5);
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World var1, int var2, int var3, int var4, EntityLiving var5, ItemStack var6)
    {
        int var7 = FCUtilsMisc.ConvertOrientationToFlatBlockFacingReversed(var5);
        this.SetFacing(var1, var2, var3, var4, var7);
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

    public AxisAlignedBB GetBlockBoundsFromPoolBasedOnState(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.IsBlockMechanicalOn(var1, var2, var3, var4) ? AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 0.6875D, 1.0D) : AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        boolean var6 = var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID);

        if (!var6)
        {
            if (!this.IsCurrentStateValid(var1, var2, var3, var4) && !var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
            {
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
                this.SetIsContinuousMechanicalStateChange(var1, var2, var3, var4, true);
            }
        }
        else
        {
            boolean var7 = this.IsContinuousMechanicalStateChange(var1, var2, var3, var4);

            if (var7 && this.IsCurrentStateValid(var1, var2, var3, var4))
            {
                this.SetIsContinuousMechanicalStateChange(var1, var2, var3, var4, false);
            }
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        boolean var6 = this.IsInputtingMechanicalPower(var1, var2, var3, var4);
        boolean var7 = this.IsBlockMechanicalOn(var1, var2, var3, var4);
        boolean var8 = this.IsContinuousMechanicalStateChange(var1, var2, var3, var4);

        if (var7 != var6)
        {
            if (var8)
            {
                this.SetIsContinuousMechanicalStateChange(var1, var2, var3, var4, false);
                this.SetBlockMechanicalOn(var1, var2, var3, var4, var6);

                if (var6)
                {
                    this.Blow(var1, var2, var3, var4);
                }
                else
                {
                    this.LiftCollidingEntities(var1, var2, var3, var4);
                }
            }
            else
            {
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
                this.SetIsContinuousMechanicalStateChange(var1, var2, var3, var4, true);
            }
        }
        else if (var8)
        {
            this.SetIsContinuousMechanicalStateChange(var1, var2, var3, var4, false);
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.IsCurrentStateValid(var1, var2, var3, var4) && !var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
            this.SetIsContinuousMechanicalStateChange(var1, var2, var3, var4, true);
        }
    }

    public int GetFacing(int var1)
    {
        return (var1 & 3) + 2;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 &= -4;

        if (var2 >= 2)
        {
            var2 -= 2;
        }
        else
        {
            var2 = 0;
        }

        var1 |= var2;
        return var1;
    }

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
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
        return FCUtilsMechPower.IsBlockPoweredByAxle(var1, var2, var3, var4, this) || FCUtilsMechPower.IsBlockPoweredByHandCrank(var1, var2, var3, var4);
    }

    public boolean CanInputAxlePowerToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        return var5 != var6 && var5 != 1;
    }

    public boolean IsOutputtingMechanicalPower(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public void Overpower(World var1, int var2, int var3, int var4)
    {
        this.BreakBellows(var1, var2, var3, var4);
    }

    public boolean IsBlockMechanicalOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetIsBlockMechanicalOnFromMetadata(var1.getBlockMetadata(var2, var3, var4));
    }

    public void SetBlockMechanicalOn(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -5;

        if (var5)
        {
            var6 |= 4;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public boolean GetIsBlockMechanicalOnFromMetadata(int var1)
    {
        return (var1 & 4) > 0;
    }

    public boolean IsContinuousMechanicalStateChange(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 8) > 0;
    }

    public void SetIsContinuousMechanicalStateChange(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -9;

        if (var5)
        {
            var6 |= 8;
        }

        var1.setBlockMetadata(var2, var3, var4, var6);
    }

    public boolean IsCurrentStateValid(World var1, int var2, int var3, int var4)
    {
        boolean var5 = this.IsInputtingMechanicalPower(var1, var2, var3, var4);
        boolean var6 = this.IsBlockMechanicalOn(var1, var2, var3, var4);
        return var5 == var6;
    }

    private void Blow(World var1, int var2, int var3, int var4)
    {
        this.StokeFiresInFront(var1, var2, var3, var4);
        this.BlowLightItemsInFront(var1, var2, var3, var4);
    }

    private void StokeFiresInFront(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        int var6 = Block.RotateFacingAroundJ(var5, false);
        int var7 = Block.RotateFacingAroundJ(var5, true);
        FCUtilsBlockPos var8 = new FCUtilsBlockPos(var2, var3, var4);

        for (int var9 = 0; var9 < 3; ++var9)
        {
            var8.AddFacingAsOffset(var5);
            int var10 = var1.getBlockId(var8.i, var8.j, var8.k);

            if (var10 != Block.fire.blockID && var10 != FCBetterThanWolves.fcBlockFireStoked.blockID)
            {
                if (!var1.isAirBlock(var8.i, var8.j, var8.k))
                {
                    break;
                }
            }
            else
            {
                this.StokeFire(var1, var8.i, var8.j, var8.k);
            }

            FCUtilsBlockPos var11 = new FCUtilsBlockPos(var8.i, var8.j, var8.k);
            var11.AddFacingAsOffset(var6);
            var10 = var1.getBlockId(var11.i, var11.j, var11.k);

            if (var10 == Block.fire.blockID || var10 == FCBetterThanWolves.fcBlockFireStoked.blockID)
            {
                this.StokeFire(var1, var11.i, var11.j, var11.k);
            }

            FCUtilsBlockPos var12 = new FCUtilsBlockPos(var8.i, var8.j, var8.k);
            var12.AddFacingAsOffset(var7);
            var10 = var1.getBlockId(var12.i, var12.j, var12.k);

            if (var10 == Block.fire.blockID || var10 == FCBetterThanWolves.fcBlockFireStoked.blockID)
            {
                this.StokeFire(var1, var12.i, var12.j, var12.k);
            }
        }
    }

    private void BlowLightItemsInFront(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetFacing(var1, var2, var3, var4);
        FCUtilsBlockPos var6 = new FCUtilsBlockPos(var2, var3, var4);
        var6.AddFacingAsOffset(var5);
        List var7 = null;
        int var8 = this.ComputeBlowRange(var1, var2, var3, var4);

        if (var8 > 0)
        {
            AxisAlignedBB var9 = this.CreateBlowBoundingBox(var1, var2, var3, var4, var8);

            if (var9 != null)
            {
                var7 = var1.getEntitiesWithinAABB(EntityItem.class, var9);

                if (var7 != null && var7.size() > 0)
                {
                    Vec3 var10 = FCUtilsMisc.ConvertBlockFacingToVector(var5);
                    var10.xCoord *= 0.2D;
                    var10.yCoord *= 0.2D;
                    var10.zCoord *= 0.2D;

                    for (int var11 = 0; var11 < var7.size(); ++var11)
                    {
                        EntityItem var12 = (EntityItem)var7.get(var11);

                        if (!var12.isDead)
                        {
                            ItemStack var13 = var12.getEntityItem();
                            int var14 = var13.getItem().GetBellowsBlowDistance(var13.getItemDamage());

                            if (var14 > 0 && (var14 >= var8 || this.IsEntityWithinBlowRange(var1, var2, var3, var4, var14, var12)))
                            {
                                var12.motionX += var10.xCoord;
                                var12.motionY += var10.yCoord;
                                var12.motionZ += var10.zCoord;
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean IsEntityWithinBlowRange(World var1, int var2, int var3, int var4, int var5, Entity var6)
    {
        AxisAlignedBB var7 = this.CreateBlowBoundingBox(var1, var2, var3, var4, var5);
        return var7.intersectsWith(var6.boundingBox);
    }

    private AxisAlignedBB CreateBlowBoundingBox(World var1, int var2, int var3, int var4, int var5)
    {
        AxisAlignedBB var6 = null;

        if (var5 > 0)
        {
            int var7 = this.GetFacing(var1, var2, var3, var4);
            FCUtilsBlockPos var8 = new FCUtilsBlockPos(var2, var3, var4);
            var8.AddFacingAsOffset(var7);
            var6 = AxisAlignedBB.getAABBPool().getAABB((double)((float)var8.i), (double)((float)var8.j), (double)((float)var8.k), (double)((float)(var8.i + 1)), (double)((float)(var8.j + 1)), (double)((float)(var8.k + 1)));

            if (var5 > 1)
            {
                Vec3 var9 = FCUtilsMisc.ConvertBlockFacingToVector(var7);
                double var10 = (double)(var5 - 1);
                var9.xCoord *= var10;
                var9.yCoord *= var10;
                var9.zCoord *= var10;
                var6 = var6.addCoord(var9.xCoord, var9.yCoord, var9.zCoord);
            }
        }

        return var6;
    }

    private int ComputeBlowRange(World var1, int var2, int var3, int var4)
    {
        int var5 = 0;
        int var6 = this.GetFacing(var1, var2, var3, var4);
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);

        for (int var8 = 0; var8 < 3; ++var8)
        {
            var7.AddFacingAsOffset(var6);

            if (!this.CanBlowThroughBlock(var1, var7.i, var7.j, var7.k))
            {
                break;
            }

            ++var5;
        }

        return var5;
    }

    private boolean CanBlowThroughBlock(World var1, int var2, int var3, int var4)
    {
        if (!var1.isAirBlock(var2, var3, var4))
        {
            int var5 = var1.getBlockId(var2, var3, var4);

            if (var5 != Block.fire.blockID && var5 != FCBetterThanWolves.fcBlockFireStoked.blockID && var5 != Block.trapdoor.blockID && Block.blocksList[var5].getCollisionBoundingBoxFromPool(var1, var2, var3, var4) != null)
            {
                return false;
            }
        }

        return true;
    }

    private void StokeFire(World var1, int var2, int var3, int var4)
    {
        if (var1.getBlockId(var2, var3 - 1, var4) == FCBetterThanWolves.fcBBQ.blockID)
        {
            if (var1.getBlockId(var2, var3, var4) == FCBetterThanWolves.fcBlockFireStoked.blockID)
            {
                var1.setBlockMetadata(var2, var3, var4, 0);
            }
            else
            {
                var1.setBlockWithNotify(var2, var3, var4, FCBetterThanWolves.fcBlockFireStoked.blockID);
            }

            if (var1.isAirBlock(var2, var3 + 1, var4))
            {
                var1.setBlockWithNotify(var2, var3 + 1, var4, fire.blockID);
            }
        }
        else
        {
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    private void LiftCollidingEntities(World var1, int var2, int var3, int var4)
    {
        List var5 = var1.getEntitiesWithinAABBExcludingEntity((Entity)null, AxisAlignedBB.getAABBPool().getAABB((double)((float)var2), (double)((float)var3 + 0.6875F), (double)((float)var4), (double)((float)(var2 + 1)), (double)((float)(var3 + 1)), (double)((float)(var4 + 1))));
        float var6 = (float)(var3 + 1);

        if (var5 != null && var5.size() > 0)
        {
            for (int var7 = 0; var7 < var5.size(); ++var7)
            {
                Entity var8 = (Entity)var5.get(var7);

                if (!var8.isDead && (var8.canBePushed() || var8 instanceof EntityItem))
                {
                    double var9 = var8.boundingBox.minY;

                    if (var9 < (double)var6)
                    {
                        double var11 = (double)var6 - var9;
                        var8.setPosition(var8.posX, var8.posY + var11, var8.posZ);
                    }
                }
            }
        }
    }

    public void BreakBellows(World var1, int var2, int var3, int var4)
    {
        int var5;

        for (var5 = 0; var5 < 2; ++var5)
        {
            FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcBlockWoodSidingItemStubID, 0);
        }

        for (var5 = 0; var5 < 1; ++var5)
        {
            FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcItemGear.itemID, 0);
        }

        for (var5 = 0; var5 < 2; ++var5)
        {
            ItemStack var6 = new ItemStack(FCBetterThanWolves.fcItemTannedLeather.itemID, 4, 0);
            FCUtilsItem.EjectStackWithRandomOffset(var1, var2, var3, var4, var6);
        }

        var1.playAuxSFX(2235, var2, var3, var4, 0);
        var1.setBlockWithNotify(var2, var3, var4, 0);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        Icon var2 = var1.registerIcon("fcBlockBellows_side");
        this.blockIcon = var2;
        this.m_IconBySideArray[0] = var1.registerIcon("fcBlockBellows_bottom");
        this.m_IconBySideArray[1] = var1.registerIcon("fcBlockBellows_top");
        this.m_IconBySideArray[2] = var2;
        this.m_IconBySideArray[3] = var2;
        this.m_IconBySideArray[4] = var2;
        this.m_IconBySideArray[5] = var2;
        this.m_IconFront = var1.registerIcon("fcBlockBellows_front");
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
        int var6 = this.GetFacing(var1, var2, var3, var4);
        return var5 == var6 ? this.m_IconFront : this.m_IconBySideArray[var5];
    }

    public void ClientNotificationOfMetadataChange(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        if (!this.GetIsBlockMechanicalOnFromMetadata(var5) && this.GetIsBlockMechanicalOnFromMetadata(var6))
        {
            this.BlowLightItemsInFront(var1, var2, var3, var4);
            var1.playSound((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "mob.cow.say4", 0.25F, var1.rand.nextFloat() * 0.4F + 2.0F);
            int var7 = this.GetFacing(var6);
            this.EmitBellowsParticles(var1, var2, var3, var4, var7, var1.rand);
        }
        else if (this.GetIsBlockMechanicalOnFromMetadata(var5) && !this.GetIsBlockMechanicalOnFromMetadata(var6))
        {
            this.LiftCollidingEntities(var1, var2, var3, var4);
            var1.playSound((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "mob.cow.say2", 1.0F, var1.rand.nextFloat() * 0.4F + 2.0F);
        }
    }

    private void EmitBellowsParticles(World var1, int var2, int var3, int var4, int var5, Random var6)
    {
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);
        var7.AddFacingAsOffset(var5);
        Vec3 var8 = FCUtilsMisc.ConvertBlockFacingToVector(var5);
        var8.xCoord *= 0.10000000149011612D;
        var8.yCoord *= 0.10000000149011612D;
        var8.zCoord *= 0.10000000149011612D;

        for (int var9 = 0; var9 < 10; ++var9)
        {
            float var10 = (float)var7.i + var6.nextFloat();
            float var11 = (float)var7.j + var6.nextFloat() * 0.5F;
            float var12 = (float)var7.k + var6.nextFloat();
            var1.spawnParticle("smoke", (double)var10, (double)var11, (double)var12, var8.xCoord + (double)(var6.nextFloat() * 0.1F) - 0.05000000074505806D, var8.yCoord + (double)(var6.nextFloat() * 0.1F) - 0.05000000074505806D, var8.zCoord + (double)(var6.nextFloat() * 0.1F) - 0.05000000074505806D);
        }
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return this.m_currentBlockRenderer.ShouldSideBeRenderedBasedOnCurrentBounds(var2, var3, var4, var5);
    }
}

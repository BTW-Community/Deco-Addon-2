package net.minecraft.src;

import java.util.Random;

public class FCBlockScrewPump extends Block implements FCIBlockMechanical, FCIBlockFluidSource
{
    public static final int m_iTickRate = 20;
    private Icon[] m_IconBySideArray = new Icon[6];
    private Icon m_IconFront;

    public FCBlockScrewPump(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockScrewPump");
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 20;
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
        boolean var6 = this.IsJammed(var1, var2, var3, var4);

        if (var6)
        {
            FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);
            var7.AddFacingAsOffset(this.GetFacing(var1, var2, var3, var4));
            int var8 = var1.getBlockId(var7.i, var7.j, var7.k);

            if (var8 != Block.waterMoving.blockID && var8 != Block.waterStill.blockID)
            {
                this.SetIsJammed(var1, var2, var3, var4, false);
            }
        }

        boolean var12 = this.IsInputtingMechanicalPower(var1, var2, var3, var4);
        boolean var13 = this.IsMechanicalOn(var1, var2, var3, var4);

        if (var12 != var13)
        {
            this.SetMechanicalOn(var1, var2, var3, var4, var12);
            var1.markBlockForUpdate(var2, var3, var4);

            if (this.IsPumpingWater(var1, var2, var3, var4))
            {
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
            }

            if (!var12 && this.IsJammed(var1, var2, var3, var4))
            {
                this.SetIsJammed(var1, var2, var3, var4, false);
            }
        }
        else if (var13)
        {
            if (this.IsPumpingWater(var1, var2, var3, var4))
            {
                boolean var9 = false;
                int var10 = var1.getBlockId(var2, var3 + 1, var4);

                if (var10 != Block.waterMoving.blockID && var10 != Block.waterStill.blockID)
                {
                    if (var1.isAirBlock(var2, var3 + 1, var4))
                    {
                        if (this.StartPumpSourceCheck(var1, var2, var3, var4))
                        {
                            var1.setBlockAndMetadataWithNotify(var2, var3 + 1, var4, Block.waterMoving.blockID, 7);
                            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
                        }
                        else
                        {
                            this.SetIsJammed(var1, var2, var3, var4, true);
                        }
                    }
                }
                else if (this.OnNeighborChangeShortPumpSourceCheck(var1, var2, var3, var4))
                {
                    int var11 = var1.getBlockMetadata(var2, var3 + 1, var4);

                    if (var11 > 1 && var11 < 8)
                    {
                        var1.setBlockAndMetadataWithNotify(var2, var3 + 1, var4, Block.waterMoving.blockID, var11 - 1);
                        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
                    }
                }
                else
                {
                    this.SetIsJammed(var1, var2, var3, var4, true);
                }
            }
            else
            {
                int var14 = var1.getBlockId(var2, var3 + 1, var4);

                if (var14 == Block.waterMoving.blockID || var14 == Block.waterStill.blockID)
                {
                    Block.blocksList[var14].onNeighborBlockChange(var1, var2, var3 + 1, var4, this.blockID);
                }
            }
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        boolean var6 = this.IsJammed(var1, var2, var3, var4);
        boolean var8 = this.IsMechanicalOn(var1, var2, var3, var4);
        boolean var9 = this.IsInputtingMechanicalPower(var1, var2, var3, var4);

        if (var9 != var8 && !var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
        else
        {
            boolean var7;

            if (var8)
            {
                FCUtilsBlockPos var10 = new FCUtilsBlockPos(var2, var3, var4);
                var10.AddFacingAsOffset(this.GetFacing(var1, var2, var3, var4));
                int var11 = var1.getBlockId(var10.i, var10.j, var10.k);

                if (var11 != Block.waterMoving.blockID && var11 != Block.waterStill.blockID)
                {
                    var7 = false;
                }
                else
                {
                    int var12 = this.GetRandomDistanceForSourceCheck(var5);
                    var7 = !FCUtilsMisc.DoesWaterHaveValidSource(var1, var10.i, var10.j, var10.k, var12);

                    if (!var7 && var6)
                    {
                        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
                    }
                }
            }
            else
            {
                var7 = false;
            }

            if (var6 != var7)
            {
                this.SetIsJammed(var1, var2, var3, var4, var7);
            }
        }
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemScrew.itemID, 1, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, Item.stick.itemID, 4, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 4, 0, var6);
        return true;
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

    public boolean IsIncineratedInCrucible()
    {
        return false;
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
        return FCUtilsMechPower.IsBlockPoweredByAxleToSide(var1, var2, var3, var4, 0);
    }

    public boolean CanInputAxlePowerToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return var5 == 0;
    }

    public boolean IsOutputtingMechanicalPower(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public void Overpower(World var1, int var2, int var3, int var4)
    {
        this.BreakScrewPump(var1, var2, var3, var4);
    }

    public int IsSourceToFluidBlockAtFacing(World var1, int var2, int var3, int var4, int var5)
    {
        if (var5 == 1 && this.IsPumpingWater(var1, var2, var3, var4))
        {
            int var6 = var1.getBlockId(var2, var3 + 1, var4);

            if (var6 == Block.waterMoving.blockID || var6 == Block.waterStill.blockID)
            {
                int var7 = 0;
                int var8 = var1.getBlockMetadata(var2, var3 + 1, var4);

                if (var8 > 0 && var8 < 8)
                {
                    var7 = var8 - 1;
                }

                return var7;
            }
        }

        return -1;
    }

    public boolean IsMechanicalOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 4) > 0;
    }

    public void SetMechanicalOn(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -5;

        if (var5)
        {
            var6 |= 4;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public boolean IsJammed(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 8) > 0;
    }

    public void SetIsJammed(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -9;

        if (var5)
        {
            var6 |= 8;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public boolean IsPumpingWater(World var1, int var2, int var3, int var4)
    {
        if (this.IsMechanicalOn(var1, var2, var3, var4) && !this.IsJammed(var1, var2, var3, var4))
        {
            FCUtilsBlockPos var5 = new FCUtilsBlockPos(var2, var3, var4);
            var5.AddFacingAsOffset(this.GetFacing(var1, var2, var3, var4));
            int var6 = var1.getBlockId(var5.i, var5.j, var5.k);

            if (var6 == Block.waterMoving.blockID || var6 == Block.waterStill.blockID)
            {
                return true;
            }
        }

        return false;
    }

    private boolean StartPumpSourceCheck(World var1, int var2, int var3, int var4)
    {
        FCUtilsBlockPos var5 = new FCUtilsBlockPos(var2, var3, var4);
        var5.AddFacingAsOffset(this.GetFacing(var1, var2, var3, var4));
        int var6 = var1.getBlockId(var5.i, var5.j, var5.k);

        if (var6 != Block.waterMoving.blockID && var6 != Block.waterStill.blockID)
        {
            return false;
        }
        else
        {
            short var7 = 128;
            return FCUtilsMisc.DoesWaterHaveValidSource(var1, var5.i, var5.j, var5.k, var7);
        }
    }

    private boolean OnNeighborChangeShortPumpSourceCheck(World var1, int var2, int var3, int var4)
    {
        FCUtilsBlockPos var5 = new FCUtilsBlockPos(var2, var3, var4);
        var5.AddFacingAsOffset(this.GetFacing(var1, var2, var3, var4));
        int var6 = var1.getBlockId(var5.i, var5.j, var5.k);

        if (var6 != Block.waterMoving.blockID && var6 != Block.waterStill.blockID)
        {
            return false;
        }
        else
        {
            byte var7 = 4;
            return FCUtilsMisc.DoesWaterHaveValidSource(var1, var5.i, var5.j, var5.k, var7);
        }
    }

    private int GetRandomDistanceForSourceCheck(Random var1)
    {
        short var2 = 32;
        int var3 = var1.nextInt(32);

        if (var3 == 0)
        {
            var2 = 512;
        }
        else if (var3 <= 2)
        {
            var2 = 256;
        }
        else if (var3 <= 6)
        {
            var2 = 128;
        }
        else if (var3 <= 14)
        {
            var2 = 64;
        }

        return var2;
    }

    private void BreakScrewPump(World var1, int var2, int var3, int var4)
    {
        this.DropComponentItemsOnBadBreak(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 1.0F);
        var1.playAuxSFX(2235, var2, var3, var4, 0);
        var1.setBlockWithNotify(var2, var3, var4, 0);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        Icon var2 = var1.registerIcon("fcBlockScrewPump_side");
        this.blockIcon = var2;
        this.m_IconFront = var1.registerIcon("fcBlockScrewPump_front");
        this.m_IconBySideArray[0] = var1.registerIcon("fcBlockScrewPump_bottom");
        this.m_IconBySideArray[1] = var1.registerIcon("fcBlockScrewPump_top");
        this.m_IconBySideArray[2] = var2;
        this.m_IconBySideArray[3] = var2;
        this.m_IconBySideArray[4] = var2;
        this.m_IconBySideArray[5] = var2;
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
        return var6 == var5 ? this.m_IconFront : this.m_IconBySideArray[var5];
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.IsMechanicalOn(var1, var2, var3, var4))
        {
            this.EmitPoweredParticles(var1, var2, var3, var4, var5);
        }
    }

    public void EmitPoweredParticles(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockId(var2, var3 + 1, var4);
        int var7;
        float var8;
        float var9;
        float var10;

        if (var6 != Block.waterMoving.blockID && var6 != Block.waterStill.blockID)
        {
            for (var7 = 0; var7 < 5; ++var7)
            {
                var8 = (float)var2 + var5.nextFloat();
                var9 = (float)var3 + var5.nextFloat() * 0.5F + 1.0F;
                var10 = (float)var4 + var5.nextFloat();
                var1.spawnParticle("smoke", (double)var8, (double)var9, (double)var10, 0.0D, 0.0D, 0.0D);
            }
        }
        else
        {
            for (var7 = 0; var7 < 5; ++var7)
            {
                var8 = (float)var2 + var5.nextFloat();
                var9 = (float)var3 + var5.nextFloat() * 0.1F + 1.0F;
                var10 = (float)var4 + var5.nextFloat();
                var1.spawnParticle("bubble", (double)var8, (double)var9, (double)var10, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}

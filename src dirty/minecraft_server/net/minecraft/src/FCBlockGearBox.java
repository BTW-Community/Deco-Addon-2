package net.minecraft.src;

import java.util.Random;

public class FCBlockGearBox extends Block implements FCIBlockMechanical
{
    public static final int m_iTickRate = 10;
    private static final int m_iTurnOnTickRate = 10;
    private static final int m_iTurnOffTickRate = 9;

    protected FCBlockGearBox(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(2.0F);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.setStepSound(soundWoodFootstep);
        this.setTickRandomly(true);
        this.setUnlocalizedName("fcBlockGearBox");
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
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (var5.getCurrentEquippedItem() == null && !FCUtilsMechPower.DoesBlockHaveAnyFacingAxles(var1, var2, var3, var4))
        {
            if (!var1.isRemote)
            {
                this.ToggleFacing(var1, var2, var3, var4, false);
                FCUtilsMisc.PlayPlaceSoundForBlock(var1, var2, var3, var4);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        boolean var6 = this.IsInputtingMechanicalPower(var1, var2, var3, var4);
        this.UpdateMechPoweredState(var1, var2, var3, var4, var6);
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!this.IsCurrentStateValid(var1, var2, var3, var4) && !var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        if (!this.IsCurrentStateValid(var1, var2, var3, var4) && !var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID) && !var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
        {
            if (!FCBetterThanWolves.fcDisableGearBoxPowerDrain && this.IsGearBoxOn(var1, var2, var3, var4))
            {
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, 9);
            }
            else
            {
                var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, 10);
            }
        }
    }

    public int GetMechanicalPowerLevelProvidedToAxleAtFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return this.IsGearBoxOn(var1, var2, var3, var4) && this.GetFacing(var1, var2, var3, var4) != var5 ? 4 : 0;
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, Item.stick.itemID, 2, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 3, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemGear.itemID, 2, 0, var6);
        return true;
    }

    public int GetFacing(int var1)
    {
        return var1 & 7;
    }

    public int SetFacing(int var1, int var2)
    {
        var1 &= 8;
        var1 |= var2;
        return var1;
    }

    public boolean RotateAroundJAxis(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        int var7 = Block.RotateFacingAroundJ(var6, var5);

        if (var7 != var6)
        {
            if (this.IsGearBoxOn(var1, var2, var3, var4))
            {
                this.SetGearBoxOn(var1, var2, var3, var4, false);
            }

            this.SetFacing(var1, var2, var3, var4, var7);
            var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
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
        if (this.IsGearBoxOn(var1, var2, var3, var4))
        {
            this.SetGearBoxOn(var1, var2, var3, var4, false);
        }

        int var6 = this.GetFacing(var1, var2, var3, var4);
        var6 = Block.CycleFacing(var6, var5);
        this.SetFacing(var1, var2, var3, var4, var6);
        var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        var1.notifyBlockChange(var2, var3, var4, this.blockID);
        return true;
    }

    public boolean CanOutputMechanicalPower()
    {
        return true;
    }

    public boolean CanInputMechanicalPower()
    {
        return true;
    }

    public boolean IsInputtingMechanicalPower(World var1, int var2, int var3, int var4)
    {
        return FCUtilsMechPower.IsBlockPoweredByAxleToSide(var1, var2, var3, var4, this.GetFacing(var1, var2, var3, var4));
    }

    public boolean CanInputAxlePowerToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        return var5 == var6;
    }

    public boolean IsOutputtingMechanicalPower(World var1, int var2, int var3, int var4)
    {
        return this.IsGearBoxOn(var1, var2, var3, var4);
    }

    public void Overpower(World var1, int var2, int var3, int var4)
    {
        if (this.IsGearBoxOn(var1, var2, var3, var4))
        {
            this.BreakGearBox(var1, var2, var3, var4);
        }
    }

    protected void UpdateMechPoweredState(World var1, int var2, int var3, int var4, boolean var5)
    {
        if (this.IsGearBoxOn(var1, var2, var3, var4) != var5)
        {
            this.SetGearBoxOn(var1, var2, var3, var4, var5);
        }
    }

    protected boolean IsCurrentStateValid(World var1, int var2, int var3, int var4)
    {
        return this.IsGearBoxOn(var1, var2, var3, var4) == this.IsInputtingMechanicalPower(var1, var2, var3, var4);
    }

    public boolean IsGearBoxOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.IsGearBoxOn(var1.getBlockMetadata(var2, var3, var4));
    }

    public boolean IsGearBoxOn(int var1)
    {
        return (var1 & 8) > 0;
    }

    public int SetGearBoxOn(int var1, boolean var2)
    {
        var1 &= 7;

        if (var2)
        {
            var1 |= 8;
        }

        return var1;
    }

    public void SetGearBoxOn(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.SetGearBoxOn(var1.getBlockMetadata(var2, var3, var4), var5);
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public void BreakGearBox(World var1, int var2, int var3, int var4)
    {
        this.DropComponentItemsOnBadBreak(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 1.0F);
        var1.playAuxSFX(2235, var2, var3, var4, 0);
        var1.setBlockWithNotify(var2, var3, var4, 0);
    }
}

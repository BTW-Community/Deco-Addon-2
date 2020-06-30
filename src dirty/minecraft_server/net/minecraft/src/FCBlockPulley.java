package net.minecraft.src;

import java.util.Random;

public class FCBlockPulley extends BlockContainer implements FCIBlockMechanical
{
    private static int iPulleyTickRate = 10;

    protected FCBlockPulley(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(2.0F);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.SetFireProperties(FCEnumFlammability.PLANKS);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockPulley");
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (!var1.isRemote)
        {
            FCTileEntityPulley var10 = (FCTileEntityPulley)var1.getBlockTileEntity(var2, var3, var4);

            if (var5 instanceof EntityPlayerMP)
            {
                FCContainerPulley var11 = new FCContainerPulley(var5.inventory, var10);
                FCBetterThanWolves.ServerOpenCustomInterface((EntityPlayerMP)var5, var11, FCBetterThanWolves.fcPulleyContainerID);
            }
        }

        return true;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World var1)
    {
        return new FCTileEntityPulley();
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
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        TileEntity var7 = var1.getBlockTileEntity(var2, var3, var4);

        if (var7 != null)
        {
            FCUtilsInventory.EjectInventoryContents(var1, var2, var3, var4, (IInventory)var7);
        }

        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return iPulleyTickRate;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        boolean var6 = this.IsInputtingMechanicalPower(var1, var2, var3, var4);
        boolean var7 = this.IsBlockOn(var1, var2, var3, var4);
        boolean var8 = false;

        if (var7 != var6)
        {
            this.SetBlockOn(var1, var2, var3, var4, var6);
            var8 = true;
        }

        boolean var9 = this.IsRedstoneOn(var1, var2, var3, var4);
        boolean var10 = var1.isBlockGettingPowered(var2, var3, var4) || var1.isBlockGettingPowered(var2, var3 + 1, var4);

        if (var9 != var10)
        {
            this.SetRedstoneOn(var1, var2, var3, var4, var10);
            var8 = true;
        }

        if (var8)
        {
            ((FCTileEntityPulley)var1.getBlockTileEntity(var2, var3, var4)).NotifyPulleyEntityOfBlockStateChange();
        }
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
        if (!this.IsCurrentStateValid(var1, var2, var3, var4) && !var1.IsUpdatePendingThisTickForBlock(var2, var3, var4, this.blockID))
        {
            var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
        }
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, Item.stick.itemID, 2, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 2, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemGear.itemID, 1, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, Item.goldNugget.itemID, 2, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, Item.ingotIron.itemID, 1, 0, var6);
        return true;
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
        return FCUtilsMechPower.IsBlockPoweredByAxle(var1, var2, var3, var4, this) || FCUtilsMechPower.IsBlockPoweredByHandCrank(var1, var2, var3, var4);
    }

    public boolean CanInputAxlePowerToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return var5 >= 2;
    }

    public boolean IsOutputtingMechanicalPower(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public void Overpower(World var1, int var2, int var3, int var4)
    {
        this.BreakPulley(var1, var2, var3, var4);
    }

    public boolean IsBlockOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 1) > 0;
    }

    public void SetBlockOn(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (var5)
        {
            var6 |= 1;
        }
        else
        {
            var6 &= -2;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    public boolean IsRedstoneOn(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 2) > 0;
    }

    public void SetRedstoneOn(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -3;

        if (var5)
        {
            var6 |= 2;
        }

        var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
    }

    void EmitPulleyParticles(World var1, int var2, int var3, int var4, Random var5)
    {
        for (int var6 = 0; var6 < 5; ++var6)
        {
            float var7 = (float)var2 + var5.nextFloat();
            float var8 = (float)var3 + var5.nextFloat() * 0.5F + 1.0F;
            float var9 = (float)var4 + var5.nextFloat();
            var1.spawnParticle("smoke", (double)var7, (double)var8, (double)var9, 0.0D, 0.0D, 0.0D);
        }
    }

    public void BreakPulley(World var1, int var2, int var3, int var4)
    {
        this.DropComponentItemsOnBadBreak(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 1.0F);
        var1.playAuxSFX(2235, var2, var3, var4, 0);
        var1.setBlockWithNotify(var2, var3, var4, 0);
    }

    public boolean IsCurrentStateValid(World var1, int var2, int var3, int var4)
    {
        boolean var5 = this.IsInputtingMechanicalPower(var1, var2, var3, var4);
        boolean var6 = this.IsBlockOn(var1, var2, var3, var4);

        if (var5 != var6)
        {
            return false;
        }
        else
        {
            boolean var7 = this.IsRedstoneOn(var1, var2, var3, var4);
            boolean var8 = var1.isBlockGettingPowered(var2, var3, var4) || var1.isBlockGettingPowered(var2, var3 + 1, var4);
            return var7 == var8;
        }
    }
}

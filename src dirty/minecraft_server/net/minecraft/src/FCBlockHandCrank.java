package net.minecraft.src;

import java.util.Random;

public class FCBlockHandCrank extends Block implements FCIBlockMechanical
{
    private static int m_iHandCrankTickRate = 3;
    private static int m_iHandCrankDelayBeforeReset = 15;
    private static double m_dBaseHeight = 0.25D;
    private static double m_dShaftSelectionWidth = 0.25D;
    private static double m_dShaftSelectionHalfWidth = m_dShaftSelectionWidth / 2.0D;

    protected FCBlockHandCrank(int var1)
    {
        super(var1, Material.circuits);
        this.setHardness(0.5F);
        this.SetPicksEffectiveOn();
        this.InitBlockBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("fcBlockHandCrank");
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return m_iHandCrankTickRate;
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World var1, int var2, int var3, int var4)
    {
        return null;
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
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    public boolean canPlaceBlockAt(World var1, int var2, int var3, int var4)
    {
        return this.CanRestAtPosition(var1, var2, var3, var4) && super.canPlaceBlockAt(var1, var2, var3, var4);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        int var10 = var1.getBlockMetadata(var2, var3, var4);

        if (var10 == 0)
        {
            if (var5.getFoodStats().getFoodLevel() > 18)
            {
                var5.addExhaustion(2.0F);

                if (!var1.isRemote)
                {
                    if (!this.CheckForOverpower(var1, var2, var3, var4))
                    {
                        var1.setBlockMetadataWithNotify(var2, var3, var4, 1);
                        var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
                        var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.click", 1.0F, 2.0F);
                        var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1));
                        var1.markBlockForUpdate(var2, var3, var4);
                    }
                    else
                    {
                        this.BreakCrankWithDrop(var1, var2, var3, var4);
                    }
                }
            }
            else if (var1.isRemote)
            {
                var5.addChatMessage("You\'re too exhausted for manual labor.");
                return false;
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
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (var6 > 0)
        {
            if (var6 < 7)
            {
                if (var6 <= 6)
                {
                    var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.click", 1.0F, 2.0F);
                }

                if (var6 <= 5)
                {
                    var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, this.tickRate(var1) + var6);
                }
                else
                {
                    var1.scheduleBlockUpdate(var2, var3, var4, this.blockID, m_iHandCrankDelayBeforeReset);
                }

                var1.setBlockMetadata(var2, var3, var4, var6 + 1);
            }
            else
            {
                var1.setBlockMetadataWithNotify(var2, var3, var4, 0);
                var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
                var1.markBlockForUpdate(var2, var3, var4);
                var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.click", 0.3F, 0.7F);
            }
        }
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (var6 > 0 && !var1.IsUpdateScheduledForBlock(var2, var3, var4, this.blockID))
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
        if (!this.CanRestAtPosition(var1, var2, var3, var4))
        {
            this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
            var1.setBlockWithNotify(var2, var3, var4, 0);
        }
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector returning a ray trace hit. Args: world,
     * x, y, z, startVec, endVec
     */
    public MovingObjectPosition collisionRayTrace(World var1, int var2, int var3, int var4, Vec3 var5, Vec3 var6)
    {
        FCUtilsRayTraceVsComplexBlock var7 = new FCUtilsRayTraceVsComplexBlock(var1, var2, var3, var4, var5, var6);
        var7.AddBoxWithLocalCoordsToIntersectionList(this.GetBaseBoundsFromPool());
        var7.AddBoxWithLocalCoordsToIntersectionList(this.GetShaftSelectionBoundsFromPool());
        return var7.GetFirstIntersection();
    }

    public boolean CanOutputMechanicalPower()
    {
        return true;
    }

    public boolean CanInputMechanicalPower()
    {
        return false;
    }

    public boolean CanInputAxlePowerToFacing(World var1, int var2, int var3, int var4, int var5)
    {
        return false;
    }

    public boolean IsInputtingMechanicalPower(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean IsOutputtingMechanicalPower(World var1, int var2, int var3, int var4)
    {
        return var1.getBlockMetadata(var2, var3, var4) > 0;
    }

    public void Overpower(World var1, int var2, int var3, int var4) {}

    public boolean CanRestAtPosition(World var1, int var2, int var3, int var4)
    {
        return FCUtilsWorld.DoesBlockHaveLargeCenterHardpointToFacing(var1, var2, var3 - 1, var4, 1, true);
    }

    public boolean CheckForOverpower(World var1, int var2, int var3, int var4)
    {
        int var5 = 0;

        for (int var6 = 0; var6 <= 5; ++var6)
        {
            if (var6 != 1)
            {
                FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);
                var7.AddFacingAsOffset(var6);
                int var8 = var1.getBlockId(var7.i, var7.j, var7.k);
                Block var9 = Block.blocksList[var8];

                if (var9 != null && var9 instanceof FCIBlockMechanical)
                {
                    FCIBlockMechanical var10 = (FCIBlockMechanical)var9;

                    if (var10.CanInputMechanicalPower())
                    {
                        ++var5;
                    }
                }
            }
        }

        return var5 > 1;
    }

    public void BreakCrankWithDrop(World var1, int var2, int var3, int var4)
    {
        FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, Item.stick.itemID, 0);
        FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcItemGear.itemID, 0);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemStone.itemID, 12, 0, 0.75F);
        var1.playAuxSFX(2235, var2, var3, var4, 0);
        var1.setBlockWithNotify(var2, var3, var4, 0);
    }

    protected AxisAlignedBB GetBaseBoundsFromPool()
    {
        return AxisAlignedBB.getAABBPool().getAABB(0.0D, 0.0D, 0.0D, 1.0D, m_dBaseHeight, 1.0D);
    }

    protected AxisAlignedBB GetShaftSelectionBoundsFromPool()
    {
        return AxisAlignedBB.getAABBPool().getAABB(0.0D, m_dBaseHeight, 0.5D - m_dShaftSelectionHalfWidth, 1.0D, 1.0D, 0.5D + m_dShaftSelectionHalfWidth);
    }
}

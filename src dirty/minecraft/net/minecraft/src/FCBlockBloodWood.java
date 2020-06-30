package net.minecraft.src;

import java.util.Random;

public class FCBlockBloodWood extends Block
{
    private static final float m_fHardness = 2.0F;
    private Icon m_IconSide;

    public FCBlockBloodWood(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialLog);
        this.setHardness(2.0F);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.SetFurnaceBurnTime(4 * FCEnumFurnaceBurnTime.PLANKS_BLOOD.m_iBurnTime);
        this.SetFireProperties(FCEnumFlammability.EXTREME);
        this.SetCanBeCookedByKiln(true);
        this.SetItemIndexDroppedWhenCookedByKiln(Item.coal.itemID);
        this.SetItemDamageDroppedWhenCookedByKiln(1);
        this.setTickRandomly(true);
        this.setStepSound(FCBetterThanWolves.fcStepSoundSquish);
        this.setUnlocalizedName("fcBlockBloodWood");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8, int var9)
    {
        var9 = this.SetFacing(var9, var5);
        return var9;
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        var1.playAuxSFX(2225, var2, var3, var4, 0);
        this.NotifySurroundingBloodLeavesOfBlockRemoval(var1, var2, var3, var4);
        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.GetCanGrow(var1, var2, var3, var4))
        {
            int var6 = this.GetFacing(var1, var2, var3, var4);

            if (var6 != 0)
            {
                WorldChunkManager var7 = var1.getWorldChunkManager();

                if (var7 != null)
                {
                    BiomeGenBase var8 = var7.getBiomeGenAt(var2, var4);

                    if (var8 instanceof BiomeGenHell)
                    {
                        this.Grow(var1, var2, var3, var4, var5);
                    }
                }
            }

            this.SetCanGrow(var1, var2, var3, var4, false);
        }
    }

    public boolean OnBlockSawed(World var1, int var2, int var3, int var4)
    {
        super.OnBlockSawed(var1, var2, var3, var4);

        for (int var5 = 0; var5 < 2; ++var5)
        {
            FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcItemSoulDust.itemID, 0);
        }

        FCUtilsItem.EjectSingleItemWithRandomOffset(var1, var2, var3, var4, FCBetterThanWolves.fcItemBark.itemID, 4);
        return true;
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return Block.planks.blockID;
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 4;
    }

    public int GetItemDamageDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 4;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 4, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemBark.itemID, 1, 4, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSoulDust.itemID, 1, 0, var6);
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

    public boolean ToggleFacing(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = this.GetFacing(var1, var2, var3, var4);
        var6 = Block.CycleFacing(var6, var5);
        this.SetFacing(var1, var2, var3, var4, var6);
        var1.markBlockRangeForRenderUpdate(var2, var3, var4, var2, var3, var4);
        return true;
    }

    public int GetCookTimeMultiplierInKiln(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 8;
    }

    public boolean GetCanGrow(IBlockAccess var1, int var2, int var3, int var4)
    {
        return (var1.getBlockMetadata(var2, var3, var4) & 8) > 0;
    }

    public void SetCanGrow(World var1, int var2, int var3, int var4, boolean var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -9;

        if (var5)
        {
            var6 |= 8;
        }

        var1.setBlockMetadata(var2, var3, var4, var6);
    }

    public void Grow(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.CountBloodWoodNeighboringOnBlockWithSoulSand(var1, var2, var3, var4) < 2)
        {
            int var6 = this.GetFacing(var1, var2, var3, var4);
            int var7;
            int var8;
            FCUtilsBlockPos var9;
            int var14;

            if (var6 == 1)
            {
                var7 = var5.nextInt(100);

                if (var7 < 25)
                {
                    this.AttemptToGrowIntoBlock(var1, var2, var3 + 1, var4, 1);
                }
                else if (var7 < 90)
                {
                    var8 = var5.nextInt(4) + 2;
                    var9 = new FCUtilsBlockPos(var2, var3, var4);
                    var9.AddFacingAsOffset(var8);
                    this.AttemptToGrowIntoBlock(var1, var9.i, var9.j, var9.k, var8);
                    this.AttemptToGrowIntoBlock(var1, var2, var3 + 1, var4, 1);
                }
                else
                {
                    for (var8 = 0; var8 < 2; ++var8)
                    {
                        var14 = var5.nextInt(4) + 2;
                        FCUtilsBlockPos var10 = new FCUtilsBlockPos(var2, var3, var4);
                        var10.AddFacingAsOffset(var14);
                        this.AttemptToGrowIntoBlock(var1, var10.i, var10.j, var10.k, var14);
                    }
                }
            }
            else
            {
                var7 = var5.nextInt(100);

                if (var7 < 40)
                {
                    this.AttemptToGrowIntoBlock(var1, var2, var3 + 1, var4, var6);
                    this.SetFacing(var1, var2, var3, var4, 1);
                }
                else if (var7 < 65)
                {
                    FCUtilsBlockPos var13 = new FCUtilsBlockPos(var2, var3, var4);
                    var13.AddFacingAsOffset(var6);
                    this.AttemptToGrowIntoBlock(var1, var13.i, var13.j, var13.k, var6);
                }
                else
                {
                    int var16;

                    if (var7 < 90)
                    {
                        var8 = var5.nextInt(4) + 2;

                        if (var8 == var6)
                        {
                            var8 = 1;
                        }

                        var9 = new FCUtilsBlockPos(var2, var3, var4);
                        var9.AddFacingAsOffset(var8);
                        var16 = var6;

                        if (var8 >= 2)
                        {
                            var16 = var8;
                        }

                        this.AttemptToGrowIntoBlock(var1, var9.i, var9.j, var9.k, var16);
                        var9 = new FCUtilsBlockPos(var2, var3, var4);
                        var9.AddFacingAsOffset(var6);

                        if (!this.AttemptToGrowIntoBlock(var1, var9.i, var9.j, var9.k, var6) && var8 == 1)
                        {
                            this.SetFacing(var1, var2, var3, var4, 1);
                        }
                    }
                    else
                    {
                        int[] var15 = new int[2];

                        for (var14 = 0; var14 < 2; ++var14)
                        {
                            var15[var14] = 0;
                            var16 = var5.nextInt(4) + 2;

                            if (var16 == var6)
                            {
                                var16 = 1;
                            }

                            FCUtilsBlockPos var11 = new FCUtilsBlockPos(var2, var3, var4);
                            var11.AddFacingAsOffset(var16);
                            int var12 = var6;

                            if (var16 >= 2)
                            {
                                var12 = var16;
                            }

                            if (this.AttemptToGrowIntoBlock(var1, var11.i, var11.j, var11.k, var12))
                            {
                                var15[var14] = var16;
                            }
                        }

                        if (var15[0] == 1 && var15[1] <= 1 || var15[1] == 1 && var15[0] == 0)
                        {
                            this.SetFacing(var1, var2, var3, var4, 1);
                        }
                    }
                }
            }
        }
    }

    public boolean AttemptToGrowIntoBlock(World var1, int var2, int var3, int var4, int var5)
    {
        if ((var1.isAirBlock(var2, var3, var4) || this.IsBloodLeafBlock(var1, var2, var3, var4)) && this.CountBloodWoodNeighboringOnBlockWithSoulSand(var1, var2, var3, var4) < 2)
        {
            var1.setBlockAndMetadataWithNotify(var2, var3, var4, this.blockID, var5 | 8);
            this.GrowLeaves(var1, var2, var3, var4);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void GrowLeaves(World var1, int var2, int var3, int var4)
    {
        for (int var5 = var2 - 1; var5 <= var2 + 1; ++var5)
        {
            for (int var6 = var3 - 1; var6 <= var3 + 1; ++var6)
            {
                for (int var7 = var4 - 1; var7 <= var4 + 1; ++var7)
                {
                    if (var1.isAirBlock(var5, var6, var7))
                    {
                        var1.setBlockAndMetadataWithNotify(var5, var6, var7, FCBetterThanWolves.fcBlockBloodLeaves.blockID, 0);
                    }
                }
            }
        }
    }

    public boolean IsBloodLeafBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);

        if (var5 == FCBetterThanWolves.fcBlockBloodLeaves.blockID)
        {
            return true;
        }
        else
        {
            if (var5 == FCBetterThanWolves.fcAestheticVegetation.blockID)
            {
                int var6 = var1.getBlockMetadata(var2, var3, var4);

                if (var6 == 3)
                {
                    return true;
                }
            }

            return false;
        }
    }

    public int CountBloodWoodNeighboringOnBlockWithSoulSand(World var1, int var2, int var3, int var4)
    {
        int var5 = 0;

        for (int var6 = 0; var6 < 6; ++var6)
        {
            FCUtilsBlockPos var7 = new FCUtilsBlockPos(var2, var3, var4);
            var7.AddFacingAsOffset(var6);

            if (var1.getBlockId(var7.i, var7.j, var7.k) == this.blockID)
            {
                ++var5;
            }
        }

        if (var1.getBlockId(var2, var3 - 1, var4) == Block.slowSand.blockID)
        {
            ++var5;
        }

        return var5;
    }

    public int CountBloodWoodNeighboringOnBlockIncludingDiagnals(World var1, int var2, int var3, int var4)
    {
        int var5 = 0;

        for (int var6 = var2 - 1; var6 <= var2 + 1; ++var6)
        {
            for (int var7 = var3 - 1; var7 <= var3 + 1; ++var7)
            {
                for (int var8 = var4 - 1; var8 <= var4 + 1; ++var8)
                {
                    if (var1.getBlockId(var6, var7, var8) == this.blockID && (var6 != var2 || var7 != var3 || var8 != var4))
                    {
                        ++var5;
                    }
                }
            }
        }

        return var5;
    }

    public void NotifySurroundingBloodLeavesOfBlockRemoval(World var1, int var2, int var3, int var4)
    {
        byte var5 = 4;
        int var6 = var5 + 1;

        if (var1.checkChunksExist(var2 - var6, var3 - var6, var4 - var6, var2 + var6, var3 + var6, var4 + var6))
        {
            for (int var7 = -var5; var7 <= var5; ++var7)
            {
                for (int var8 = -var5; var8 <= var5; ++var8)
                {
                    for (int var9 = -var5; var9 <= var5; ++var9)
                    {
                        int var10 = var1.getBlockId(var2 + var7, var3 + var8, var4 + var9);

                        if (var10 == FCBetterThanWolves.fcBlockBloodLeaves.blockID)
                        {
                            int var11 = var1.getBlockMetadata(var2 + var7, var3 + var8, var4 + var9);

                            if ((var11 & 8) == 0)
                            {
                                var1.setBlockMetadata(var2 + var7, var3 + var8, var4 + var9, var11 | 8);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_IconSide = var1.registerIcon("fcBlockBloodWood_side");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        int var3 = var2 & -9;

        if (var3 < 2)
        {
            if (var1 >= 2)
            {
                return this.m_IconSide;
            }
        }
        else if (var3 < 4)
        {
            if (var1 != 2 && var1 != 3)
            {
                return this.m_IconSide;
            }
        }
        else if (var1 < 4)
        {
            return this.m_IconSide;
        }

        return this.blockIcon;
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        IBlockAccess var5 = var1.blockAccess;
        int var6 = this.GetFacing(var5, var2, var3, var4);

        if (var6 == 2)
        {
            var1.SetUvRotateSouth(1);
            var1.SetUvRotateNorth(2);
        }
        else if (var6 == 3)
        {
            var1.SetUvRotateSouth(2);
            var1.SetUvRotateNorth(1);
            var1.SetUvRotateTop(3);
            var1.SetUvRotateBottom(3);
        }
        else if (var6 == 4)
        {
            var1.SetUvRotateEast(1);
            var1.SetUvRotateWest(2);
            var1.SetUvRotateTop(2);
            var1.SetUvRotateBottom(1);
        }
        else if (var6 == 5)
        {
            var1.SetUvRotateEast(2);
            var1.SetUvRotateWest(1);
            var1.SetUvRotateTop(1);
            var1.SetUvRotateBottom(2);
        }

        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        var1.renderStandardBlock(this, var2, var3, var4);
        var1.ClearUvRotation();
        return true;
    }

    public void RenderBlockSecondPass(RenderBlocks var1, int var2, int var3, int var4, boolean var5)
    {
        this.RenderCookingByKilnOverlay(var1, var2, var3, var4, var5);
    }
}

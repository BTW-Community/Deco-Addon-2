package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class FCBlockAestheticOpaque extends Block
{
    public static final int m_iSubtypeWicker = 0;
    public static final int m_iSubtypeDung = 1;
    public static final int m_iSubtypeSteel = 2;
    public static final int m_iSubtypeHellfire = 3;
    public static final int m_iSubtypePadding = 4;
    public static final int m_iSubtypeSoap = 5;
    public static final int m_iSubtypeRope = 6;
    public static final int m_iSubtypeFlint = 7;
    public static final int m_iSubtypeNetherrackWithGrowth = 8;
    public static final int m_iSubtypeWhiteStone = 9;
    public static final int m_iSubtypeWhiteCobble = 10;
    public static final int m_iSubtypeBarrel = 11;
    public static final int m_iSubtypeChoppingBlockDirty = 12;
    public static final int m_iSubtypeChoppingBlockClean = 13;
    public static final int m_iSubtypeEnderBlock = 14;
    public static final int m_iSubtypeBone = 15;
    public static final int m_iNumSubtypes = 16;
    private static final float m_fDefaultHardness = 2.0F;
    private Icon m_IconWicker;
    private Icon m_IconDung;
    private Icon m_IconSteel;
    private Icon m_IconHellfire;
    private Icon m_IconPadding;
    private Icon m_IconSoap;
    private Icon m_IconSoapTop;
    private Icon m_IconRopeSide;
    private Icon m_IconRopeTop;
    private Icon m_IconFlint;
    private Icon m_IconNetherrackWithGrothSide;
    private Icon m_IconNetherrackWithGrothTop;
    private Icon m_IconNetherrackWithGrothBottom;
    private Icon m_IconWhiteStone;
    private Icon m_IconWhiteCobble;
    private Icon m_IconBarrelTop;
    private Icon m_IconBarrelSide;
    private Icon m_IconChoppingBlock;
    private Icon m_IconChoppingBlockDirty;
    private Icon m_IconEnderBlock;
    private Icon m_IconBoneSide;
    private Icon m_IconBoneTop;

    public FCBlockAestheticOpaque(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialMiscellaneous);
        this.setHardness(2.0F);
        this.SetAxesEffectiveOn(true);
        this.SetPicksEffectiveOn(true);
        this.setStepSound(soundStoneFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("fcBlockAestheticOpaque");
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return var1 == 0 ? 0 : (var1 == 2 ? 0 : (var1 == 7 ? 0 : (var1 == 8 ? 0 : (var1 == 9 ? 10 : var1))));
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return var1 == 0 ? FCBetterThanWolves.fcBlockWicker.blockID : (var1 == 2 ? FCBetterThanWolves.fcSoulforgedSteelBlock.blockID : (var1 == 7 ? Item.flint.itemID : (var1 == 8 ? Block.netherrack.blockID : this.blockID)));
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        if (var5 == 7)
        {
            if (var1.isRemote)
            {
                return;
            }

            byte var8 = 9;

            for (int var9 = 0; var9 < var8; ++var9)
            {
                int var10 = this.idDropped(var5, var1.rand, var7);

                if (var10 > 0)
                {
                    this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(var10, 1, this.damageDropped(var5)));
                }
            }
        }
        else
        {
            super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);
        }
    }

    protected boolean canSilkHarvest(int var1)
    {
        return var1 != 8 && var1 != 2;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    public void onNeighborBlockChange(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (var6 == 8)
        {
            int var7 = var1.getBlockId(var2, var3 + 1, var4);

            if (var7 != FCBetterThanWolves.fcBlockBloodMoss.blockID)
            {
                var1.setBlock(var2, var3, var4, Block.netherrack.blockID);
            }
        }
    }

    public boolean DoesInfiniteBurnToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4);
        return var6 == 3;
    }

    public boolean DoesBlockBreakSaw(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 != 0 && var5 != 1 && var5 != 4 && var5 != 5 && var5 != 6 && var5 != 11 && var5 != 12 && var5 != 13 && var5 != 15;
    }

    public boolean OnBlockSawed(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 != 12 && var5 != 13 ? super.OnBlockSawed(var1, var2, var3, var4) : false;
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 0 ? FCBetterThanWolves.fcBlockWickerSlab.blockID : (var5 == 15 ? Item.bone.itemID : super.GetItemIDDroppedOnSaw(var1, var2, var3, var4));
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 0 ? 2 : (var5 == 15 ? 5 : super.GetItemCountDroppedOnSaw(var1, var2, var3, var4));
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 1 ? 1.0F : 1.2F;
    }

    public StepSound GetStepSound(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 1 ? FCBetterThanWolves.fcStepSoundSquish : (var5 == 15 ? soundGravelFootstep : this.stepSound);
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 1 || var5 == 15 || var5 == 5;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("stone");
        this.m_IconWicker = var1.registerIcon("fcBlockWicker");
        this.m_IconDung = var1.registerIcon("fcBlockDung");
        this.m_IconSteel = var1.registerIcon("fcBlockSoulforgedSteel");
        this.m_IconHellfire = var1.registerIcon("fcBlockConcentratedHellfire");
        this.m_IconPadding = var1.registerIcon("fcBlockPadding");
        this.m_IconSoap = var1.registerIcon("fcBlockSoap");
        this.m_IconSoapTop = var1.registerIcon("fcBlockSoap_top");
        this.m_IconRopeSide = var1.registerIcon("fcBlockRope_side");
        this.m_IconRopeTop = var1.registerIcon("fcBlockRope_top");
        this.m_IconFlint = var1.registerIcon("bedrock");
        this.m_IconNetherrackWithGrothSide = var1.registerIcon("fcBlockNetherrackGrothed_side");
        this.m_IconNetherrackWithGrothTop = var1.registerIcon("fcBlockNetherrackGrothed_top");
        this.m_IconNetherrackWithGrothBottom = var1.registerIcon("fcBlockNetherrackGrothed_bottom");
        this.m_IconWhiteStone = var1.registerIcon("fcBlockWhiteStone");
        this.m_IconWhiteCobble = var1.registerIcon("fcBlockWhiteCobble");
        this.m_IconBarrelTop = var1.registerIcon("fcBlockBarrel_top");
        this.m_IconBarrelSide = var1.registerIcon("fcBlockBarrel_side");
        this.m_IconChoppingBlock = var1.registerIcon("fcBlockChoppingBlock");
        this.m_IconChoppingBlockDirty = var1.registerIcon("fcBlockChoppingBlock_dirty");
        this.m_IconEnderBlock = var1.registerIcon("fcBlockEnder");
        this.m_IconBoneSide = var1.registerIcon("fcBlockBone_side");
        this.m_IconBoneTop = var1.registerIcon("fcBlockBone_top");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        switch (var2)
        {
            case 0:
                return this.m_IconWicker;

            case 1:
                return this.m_IconDung;

            case 2:
                return this.m_IconSteel;

            case 3:
                return this.m_IconHellfire;

            case 4:
                return this.m_IconPadding;

            case 5:
                if (var1 == 1)
                {
                    return this.m_IconSoapTop;
                }

                return this.m_IconSoap;

            case 6:
                if (var1 < 2)
                {
                    return this.m_IconRopeTop;
                }

                return this.m_IconRopeSide;

            case 7:
                return this.m_IconFlint;

            case 8:
                if (var1 == 0)
                {
                    return this.m_IconNetherrackWithGrothBottom;
                }
                else
                {
                    if (var1 == 1)
                    {
                        return this.m_IconNetherrackWithGrothTop;
                    }

                    return this.m_IconNetherrackWithGrothSide;
                }

            case 9:
                return this.m_IconWhiteStone;

            case 10:
                return this.m_IconWhiteCobble;

            case 11:
                if (var1 < 2)
                {
                    return this.m_IconBarrelTop;
                }

                return this.m_IconBarrelSide;

            case 12:
                return this.m_IconChoppingBlockDirty;

            case 13:
                return this.m_IconChoppingBlock;

            case 14:
                return this.m_IconEnderBlock;

            case 15:
                if (var1 < 2)
                {
                    return this.m_IconBoneTop;
                }

                return this.m_IconBoneSide;

            default:
                return this.blockIcon;
        }
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int var1, CreativeTabs var2, List var3)
    {
        var3.add(new ItemStack(var1, 1, 3));
        var3.add(new ItemStack(var1, 1, 4));
        var3.add(new ItemStack(var1, 1, 5));
        var3.add(new ItemStack(var1, 1, 6));
        var3.add(new ItemStack(var1, 1, 7));
        var3.add(new ItemStack(var1, 1, 9));
        var3.add(new ItemStack(var1, 1, 10));
        var3.add(new ItemStack(var1, 1, 11));
        var3.add(new ItemStack(var1, 1, 13));
        var3.add(new ItemStack(var1, 1, 14));
        var3.add(new ItemStack(var1, 1, 15));
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 7 ? this.blockID : this.idDropped(var5, var1.rand, 0);
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockMetadata(var2, var3, var4);
        return var5 == 7 ? 7 : super.getDamageValue(var1, var2, var3, var4);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return var5 == 1 && var1.getBlockId(var2, var3, var4) == FCBetterThanWolves.fcBlockBloodMoss.blockID ? false : !var1.isBlockOpaqueCube(var2, var3, var4);
    }
}

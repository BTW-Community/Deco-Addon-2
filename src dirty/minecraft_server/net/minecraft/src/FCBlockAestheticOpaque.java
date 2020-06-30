package net.minecraft.src;

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
}

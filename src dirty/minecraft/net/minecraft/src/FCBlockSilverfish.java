package net.minecraft.src;

import java.util.Random;

public class FCBlockSilverfish extends BlockSilverfish
{
    private static final int m_iHatchFrequency = 1200;

    public FCBlockSilverfish(int var1)
    {
        super(var1);
        this.setHardness(1.5F);
        this.SetPicksEffectiveOn();
        this.SetChiselsEffectiveOn();
        this.setTickRandomly(true);
        this.setUnlocalizedName("monsterStoneEgg");
    }

    public void RandomUpdateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var1.provider.dimensionId == 1 && var5.nextInt(1200) == 0)
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            var1.playAuxSFX(2252, var2, var3, var4, this.blockID + (var6 << 12));
            var1.setBlockWithNotify(var2, var3, var4, 0);
            int var7 = 1;

            if (var5.nextInt(2) == 0)
            {
                ++var7;
            }

            for (int var8 = 0; var8 < var7; ++var8)
            {
                EntitySilverfish var9 = new EntitySilverfish(var1);
                var9.setLocationAndAngles((double)var2 + 0.5D, (double)var3, (double)var4 + 0.5D, 0.0F, 0.0F);
                var1.spawnEntityInWorld(var9);
            }

            FCUtilsItem.DropSingleItemAsIfBlockHarvested(var1, var2, var3, var4, Block.gravel.blockID, 0);
            FCUtilsItem.DropSingleItemAsIfBlockHarvested(var1, var2, var3, var4, Item.clay.itemID, 0);
        }
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int var1)
    {
        Block var2 = Block.stone;
        byte var3 = 0;

        if (var1 == 1)
        {
            var2 = Block.cobblestone;
        }
        else if (var1 == 2)
        {
            var2 = Block.stoneBrick;
        }
        else if (var1 == 14)
        {
            var3 = 1;
        }
        else if (var1 == 15)
        {
            var3 = 2;
        }

        return new ItemStack(var2, 1, var3);
    }

    public boolean HasStrata()
    {
        return true;
    }

    public int GetMetadataConversionForStrataLevel(int var1, int var2)
    {
        if (var2 == 0)
        {
            if (var1 == 1)
            {
                var2 = 14;
            }
            else if (var1 == 2)
            {
                var2 = 15;
            }
        }

        return var2;
    }

    public static int GetMetadataConversionOnInfest(int var0, int var1)
    {
        byte var2 = 0;

        if (var0 == Block.cobblestone.blockID)
        {
            var2 = 1;
        }
        else if (var0 == Block.stoneBrick.blockID)
        {
            var2 = 2;
        }
        else if (var1 == 1)
        {
            var2 = 14;
        }
        else if (var1 == 2)
        {
            var2 = 15;
        }

        return var2;
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var5.nextInt(32) == 0)
        {
            var1.playSound((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "mob.silverfish.step", var5.nextFloat() * 0.05F + 0.2F, var5.nextFloat() * 1.0F + 0.5F, false);
        }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var2 == 1 ? Block.cobblestone.getBlockTextureFromSide(var1) : (var2 == 2 ? Block.stoneBrick.getBlockTextureFromSide(var1) : (var2 == 14 ? Block.stone.getIcon(var1, 1) : (var2 == 15 ? Block.stone.getIcon(var1, 2) : Block.stone.getBlockTextureFromSide(var1))));
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        return var1.RenderStandardFullBlock(this, var2, var3, var4);
    }

    public boolean DoesItemRenderAsBlock(int var1)
    {
        return true;
    }

    public void RenderBlockMovedByPiston(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.RenderStandardFullBlockMovedByPiston(this, var2, var3, var4);
    }
}

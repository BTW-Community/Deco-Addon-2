package net.minecraft.src;

import java.util.Random;

public class FCBlockRedstoneOre extends FCBlockOreStaged
{
    private boolean m_bIsGlowing;

    public FCBlockRedstoneOre(int var1, boolean var2)
    {
        super(var1);

        if (var2)
        {
            this.setTickRandomly(true);
        }

        this.m_bIsGlowing = var2;
    }

    /**
     * How many world ticks before ticking
     */
    public int tickRate(World var1)
    {
        return 30;
    }

    /**
     * Called when the block is clicked by a player. Args: x, y, z, entityPlayer
     */
    public void onBlockClicked(World var1, int var2, int var3, int var4, EntityPlayer var5)
    {
        this.SetGlowing(var1, var2, var3, var4);
        super.onBlockClicked(var1, var2, var3, var4, var5);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        this.SetGlowing(var1, var2, var3, var4);
        return super.onBlockActivated(var1, var2, var3, var4, var5, var6, var7, var8, var9);
    }

    /**
     * Called whenever an entity is walking on top of this block. Args: world, x, y, z, entity
     */
    public void onEntityWalking(World var1, int var2, int var3, int var4, Entity var5)
    {
        this.SetGlowing(var1, var2, var3, var4);
        super.onEntityWalking(var1, var2, var3, var4, var5);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.m_bIsGlowing)
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            var1.setBlock(var2, var3, var4, Block.oreRedstone.blockID, var6, 3);
        }
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int var1)
    {
        return new ItemStack(Block.oreRedstone, 1, var1);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return Item.redstone.itemID;
    }

    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int var1, Random var2)
    {
        return this.quantityDropped(var2) + var2.nextInt(var1 + 1);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 4 + var1.nextInt(2);
    }

    public int IdDroppedOnConversion(int var1)
    {
        return Item.redstone.itemID;
    }

    public int QuantityDroppedOnConversion(Random var1)
    {
        return 4 + var1.nextInt(2);
    }

    public int GetRequiredToolLevelForOre(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    protected void SetGlowing(World var1, int var2, int var3, int var4)
    {
        this.EmitParticles(var1, var2, var3, var4);

        if (!this.m_bIsGlowing)
        {
            int var5 = var1.getBlockMetadata(var2, var3, var4);
            var1.setBlock(var2, var3, var4, Block.oreRedstoneGlowing.blockID, var5, 3);
        }
    }

    protected void EmitParticles(World var1, int var2, int var3, int var4)
    {
        Random var5 = var1.rand;
        double var6 = 0.0625D;

        for (int var8 = 0; var8 < 6; ++var8)
        {
            double var9 = (double)((float)var2 + var5.nextFloat());
            double var11 = (double)((float)var3 + var5.nextFloat());
            double var13 = (double)((float)var4 + var5.nextFloat());

            if (var8 == 0 && !var1.isBlockOpaqueCube(var2, var3 + 1, var4))
            {
                var11 = (double)(var3 + 1) + var6;
            }

            if (var8 == 1 && !var1.isBlockOpaqueCube(var2, var3 - 1, var4))
            {
                var11 = (double)(var3 + 0) - var6;
            }

            if (var8 == 2 && !var1.isBlockOpaqueCube(var2, var3, var4 + 1))
            {
                var13 = (double)(var4 + 1) + var6;
            }

            if (var8 == 3 && !var1.isBlockOpaqueCube(var2, var3, var4 - 1))
            {
                var13 = (double)(var4 + 0) - var6;
            }

            if (var8 == 4 && !var1.isBlockOpaqueCube(var2 + 1, var3, var4))
            {
                var9 = (double)(var2 + 1) + var6;
            }

            if (var8 == 5 && !var1.isBlockOpaqueCube(var2 - 1, var3, var4))
            {
                var9 = (double)(var2 + 0) - var6;
            }

            if (var9 < (double)var2 || var9 > (double)(var2 + 1) || var11 < 0.0D || var11 > (double)(var3 + 1) || var13 < (double)var4 || var13 > (double)(var4 + 1))
            {
                var1.spawnParticle("reddust", var9, var11, var13, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}

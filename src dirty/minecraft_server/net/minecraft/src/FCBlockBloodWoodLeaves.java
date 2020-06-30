package net.minecraft.src;

import java.util.Random;

public class FCBlockBloodWoodLeaves extends FCBlockLeaves
{
    public FCBlockBloodWoodLeaves(int var1)
    {
        super(var1);
        this.setUnlocalizedName("fcBlockBloodWoodLeaves");
        this.setHardness(0.2F);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.setLightOpacity(1);
        this.SetFireProperties(FCEnumFlammability.EXTREME);
        this.setStepSound(soundGrassFootstep);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World var1, int var2, int var3, int var4, int var5, int var6)
    {
        byte var7 = 1;
        int var8 = var7 + 1;

        if (var1.checkChunksExist(var2 - var8, var3 - var8, var4 - var8, var2 + var8, var3 + var8, var4 + var8))
        {
            for (int var9 = -var7; var9 <= var7; ++var9)
            {
                for (int var10 = -var7; var10 <= var7; ++var10)
                {
                    for (int var11 = -var7; var11 <= var7; ++var11)
                    {
                        int var12 = var1.getBlockId(var2 + var9, var3 + var10, var4 + var11);

                        if (var12 == this.blockID)
                        {
                            int var13 = var1.getBlockMetadata(var2 + var9, var3 + var10, var4 + var11);
                            var1.setBlockMetadata(var2 + var9, var3 + var10, var4 + var11, var13 | 8);
                        }
                    }
                }
            }
        }

        super.breakBlock(var1, var2, var3, var4, var5, var6);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return FCBetterThanWolves.fcAestheticVegetation.blockID;
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World var1, EntityPlayer var2, int var3, int var4, int var5, int var6)
    {
        if (!var1.isRemote && var2.getCurrentEquippedItem() != null && var2.getCurrentEquippedItem().itemID == Item.shears.itemID)
        {
            this.dropBlockAsItem_do(var1, var3, var4, var5, new ItemStack(FCBetterThanWolves.fcBlockBloodLeaves.blockID, 1, var6 & 3));
            var2.getCurrentEquippedItem().damageItem(1, var2);
        }
        else
        {
            super.harvestBlock(var1, var2, var3, var4, var5, var6);
        }
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        if (!var1.isRemote)
        {
            int var8 = this.quantityDroppedWithBonus(var7, var1.rand);

            for (int var9 = 0; var9 < var8; ++var9)
            {
                if (var1.rand.nextFloat() <= var6)
                {
                    int var10 = this.idDropped(var5, var1.rand, var7);

                    if (var10 > 0)
                    {
                        this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(var10, 1, this.damageDropped(var5)));
                    }
                }
            }
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int var1)
    {
        return 2;
    }

    /**
     * Spawns EntityItem in the world for the given ItemStack if the world is not remote.
     */
    protected void dropBlockAsItem_do(World var1, int var2, int var3, int var4, ItemStack var5)
    {
        if (var5.itemID == FCBetterThanWolves.fcAestheticVegetation.blockID && var5.getItemDamage() == 2)
        {
            if (!var1.isRemote)
            {
                float var6 = 0.7F;
                double var7 = (double)(var1.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
                double var9 = (double)(var1.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
                double var11 = (double)(var1.rand.nextFloat() * var6) + (double)(1.0F - var6) * 0.5D;
                FCEntityItemBloodWoodSapling var13 = new FCEntityItemBloodWoodSapling(var1, (double)var2 + var7, (double)var3 + var9, (double)var4 + var11, var5);
                var13.delayBeforeCanPickup = 10;
                var1.spawnEntityInWorld(var13);
            }
        }
        else
        {
            super.dropBlockAsItem_do(var1, var2, var3, var4, var5);
        }
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return !Block.leaves.graphicsLevel;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (!var1.isRemote)
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);

            if ((var6 & 8) != 0 && (var6 & 4) == 0)
            {
                byte var7 = 4;
                int var8 = var7 + 1;
                byte var9 = 32;
                int var10 = var9 * var9;
                int var11 = var9 / 2;

                if (this.adjacentTreeBlocks == null)
                {
                    this.adjacentTreeBlocks = new int[var9 * var9 * var9];
                }

                int var12;

                if (var1.checkChunksExist(var2 - var8, var3 - var8, var4 - var8, var2 + var8, var3 + var8, var4 + var8))
                {
                    int var13;
                    int var14;
                    int var15;

                    for (var12 = -var7; var12 <= var7; ++var12)
                    {
                        for (var13 = -var7; var13 <= var7; ++var13)
                        {
                            for (var14 = -var7; var14 <= var7; ++var14)
                            {
                                var15 = var1.getBlockId(var2 + var12, var3 + var13, var4 + var14);

                                if (var15 == FCBetterThanWolves.fcBloodWood.blockID)
                                {
                                    this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = 0;
                                }
                                else if (var15 == FCBetterThanWolves.fcBlockBloodLeaves.blockID)
                                {
                                    this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -2;
                                }
                                else
                                {
                                    this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = -1;
                                }
                            }
                        }
                    }

                    for (var12 = 1; var12 <= 4; ++var12)
                    {
                        for (var13 = -var7; var13 <= var7; ++var13)
                        {
                            for (var14 = -var7; var14 <= var7; ++var14)
                            {
                                for (var15 = -var7; var15 <= var7; ++var15)
                                {
                                    if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11] == var12 - 1)
                                    {
                                        if (this.adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
                                        }

                                        if (this.adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
                                        }

                                        if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] = var12;
                                        }

                                        if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] = var12;
                                        }

                                        if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1)] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1)] = var12;
                                        }

                                        if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] == -2)
                                        {
                                            this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] = var12;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                var12 = this.adjacentTreeBlocks[var11 * var10 + var11 * var9 + var11];

                if (var12 >= 0)
                {
                    var1.setBlockMetadata(var2, var3, var4, var6 & -9);
                }
                else
                {
                    this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
                    var1.setBlockWithNotify(var2, var3, var4, 0);
                }
            }
        }
    }

    public void OnDestroyedByFire(World var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
        super.OnDestroyedByFire(var1, var2, var3, var4, var5, var6);
    }

    protected void GenerateAshOnBurn(World var1, int var2, int var3, int var4) {}

    public boolean CanRotateOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean CanTransmitRotationHorizontallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }

    public boolean CanTransmitRotationVerticallyOnTurntable(IBlockAccess var1, int var2, int var3, int var4)
    {
        return false;
    }
}

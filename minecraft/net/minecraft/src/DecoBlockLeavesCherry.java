package net.minecraft.src;

import java.util.List;
import java.util.Random;

public class DecoBlockLeavesCherry extends BlockLeavesBase
{
    private int field_94394_cP;
    private Icon[][] iconArray = new Icon[2][];
    int[] adjacentTreeBlocks;

    protected DecoBlockLeavesCherry(int par1)
    {
        super(par1, DecoDefs.materialHedge, false);
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setHardness(0.2F);
        this.SetAxesEffectiveOn(true);
        this.SetBuoyant();
        this.setLightOpacity(1);
        this.SetFireProperties(FCEnumFlammability.LEAVES);
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("decoBlockLeavesCherry");
    }

    public float GetMovementModifier(World var1, int var2, int var3, int var4)
    {
        return 0.5F;
    }

    public boolean CanGroundCoverRestOnBlock(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    public boolean HasLargeCenterHardPointToFacing(IBlockAccess var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        return var6;
    }

    public void OnDestroyedByFire(World var1, int var2, int var3, int var4, int var5, boolean var6)
    {
        super.OnDestroyedByFire(var1, var2, var3, var4, var5, var6);
        this.GenerateAshOnBurn(var1, var2, var3, var4);
    }

    public ItemStack GetStackRetrievedByBlockDispenser(World var1, int var2, int var3, int var4)
    {
        return this.createStackedBlock(var1.getBlockMetadata(var2, var3, var4));
    }

    protected void GenerateAshOnBurn(World var1, int var2, int var3, int var4)
    {
        for (int var5 = var3; var5 > 0; --var5)
        {
            if (FCBlockAshGroundCover.CanAshReplaceBlock(var1, var2, var5, var4))
            {
                int var6 = var1.getBlockId(var2, var5 - 1, var4);
                Block var7 = Block.blocksList[var6];

                if (var7 != null && var7.CanGroundCoverRestOnBlock(var1, var2, var5 - 1, var4))
                {
                    var1.setBlockWithNotify(var2, var5, var4, FCBetterThanWolves.fcBlockAshGroundCover.blockID);
                    break;
                }
            }
            else if (var1.getBlockId(var2, var5, var4) != Block.fire.blockID)
            {
                break;
            }
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        byte var7 = 1;
        int var8 = var7 + 1;

        if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8))
        {
            for (int var9 = -var7; var9 <= var7; ++var9)
            {
                for (int var10 = -var7; var10 <= var7; ++var10)
                {
                    for (int var11 = -var7; var11 <= var7; ++var11)
                    {
                        int var12 = par1World.getBlockId(par2 + var9, par3 + var10, par4 + var11);

                        if (var12 == Block.leaves.blockID || var12 == this.blockID)
                        {
                            int var13 = par1World.getBlockMetadata(par2 + var9, par3 + var10, par4 + var11);
                            par1World.setBlockMetadataWithNotify(par2 + var9, par3 + var10, par4 + var11, var13 | 8, 4);
                        }
                    }
                }
            }
        }
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            int var6 = par1World.getBlockMetadata(par2, par3, par4);

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

                if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8))
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
                                var15 = par1World.getBlockId(par2 + var12, par3 + var13, par4 + var14);

                                if (var15 == Block.wood.blockID || var15 == DecoDefs.cherryLog.blockID)
                                {
                                    this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = 0;
                                }
                                else if (var15 == Block.leaves.blockID || var15 == this.blockID)
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
                    par1World.setBlockMetadataWithNotify(par2, par3, par4, var6 & -9, 4);
                }
                else
                {
                    this.removeLeaves(par1World, par2, par3, par4);
                }
            }
        }
    }

    private void removeLeaves(World par1World, int par2, int par3, int par4)
    {
        this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
        par1World.setBlockToAir(par2, par3, par4);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return par1Random.nextInt(20) == 0 ? 1 : 0;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return DecoDefs.cherrySapling.blockID;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        if (!par1World.isRemote)
        {

            if (par1World.rand.nextInt(20) == 0)
            {
                int var9 = this.idDropped(par5, par1World.rand, par7);
                this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(var9, 1, 0));
            }
        }
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
        if (!par1World.isRemote && par2EntityPlayer.getCurrentEquippedItem() != null && (par2EntityPlayer.getCurrentEquippedItem().itemID == Item.shears.itemID || par2EntityPlayer.getCurrentEquippedItem().itemID == DecoDefs.shearsDiamond.itemID))
        {
            par2EntityPlayer.addStat(StatList.mineBlockStatArray[this.blockID], 1);
            this.dropBlockAsItem_do(par1World, par3, par4, par5, new ItemStack(this.blockID, 1, par6 & 3));
        }
        else
        {
            super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
        }
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1 & 3;
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
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(this.blockID, 1, par1 & 3);
    }
    
    //CLIENT ONLY
    public boolean ShouldRenderNeighborFullFaceSide(IBlockAccess var1, int var2, int var3, int var4, int var5)
    {
        return true;
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
    	return true;
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5)
    {
        if (var1.IsRainingAtPos(var2, var3 + 1, var4) && !var1.doesBlockHaveSolidTopSurface(var2, var3 - 1, var4) && var5.nextInt(15) == 1)
        {
            var1.spawnParticle("dripWater", (double)var2 + var5.nextDouble(), (double)var3 - 0.05D, (double)var4 + var5.nextDouble(), 0.0D, 0.0D, 0.0D);
        }
    }
}

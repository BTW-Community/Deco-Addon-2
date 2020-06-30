package net.minecraft.src;

import java.util.Random;

public class BlockCrops extends BlockFlower
{
    private Icon[] iconArray;

    protected BlockCrops(int par1)
    {
        super(par1);
        this.setTickRandomly(true);
        float var2 = 0.5F;
        this.InitBlockBounds((double)(0.5F - var2), 0.0D, (double)(0.5F - var2), (double)(0.5F + var2), 0.25D, (double)(0.5F + var2));
        this.setCreativeTab((CreativeTabs)null);
        this.setHardness(0.0F);
        this.setStepSound(soundGrassFootstep);
        this.disableStats();
    }

    /**
     * Apply bonemeal to the crops.
     */
    public void fertilize(World par1World, int par2, int par3, int par4)
    {
        int var5 = par1World.getBlockMetadata(par2, par3, par4) + MathHelper.getRandomIntegerInRange(par1World.rand, 2, 5);

        if (var5 > 7)
        {
            var5 = 7;
        }

        par1World.setBlockMetadataWithNotify(par2, par3, par4, var5, 2);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        if (par2 < 0 || par2 > 7)
        {
            par2 = 7;
        }

        return this.iconArray[par2];
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 6;
    }

    /**
     * Generate a seed ItemStack for this crop.
     */
    protected int getSeedItem()
    {
        return Item.seeds.itemID;
    }

    /**
     * Generate a crop produce ItemStack for this crop.
     */
    protected int getCropItem()
    {
        return Item.wheat.itemID;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, 0);

        if (!par1World.isRemote && par5 >= 7)
        {
            this.DropSeeds(par1World, par2, par3, par4, par5, par6, par7);
        }
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return this.getSeedItem();
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.iconArray = new Icon[8];

        for (int var2 = 0; var2 < this.iconArray.length; ++var2)
        {
            this.iconArray[var2] = par1IconRegister.registerIcon("crops_" + var2);
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return par1 == 7 ? this.getCropItem() : 0;
    }

    public boolean CanBeGrazedOn(IBlockAccess var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        return true;
    }

    public void OnGrazed(World var1, int var2, int var3, int var4, EntityAnimal var5)
    {
        this.dropBlockAsItem(var1, var2, var3, var4, var1.getBlockMetadata(var2, var3, var4), 0);
        super.OnGrazed(var1, var2, var3, var4, var5);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);

        if (par1World.provider.dimensionId != 1 && par1World.getBlockId(par2, par3, par4) == this.blockID)
        {
            this.AttemptToGrow(par1World, par2, par3, par4, par5Random);
        }
    }

    protected boolean CanGrowOnBlock(World var1, int var2, int var3, int var4)
    {
        Block var5 = Block.blocksList[var1.getBlockId(var2, var3, var4)];
        return var5 != null && var5.CanDomesticatedCropsGrowOnBlock(var1, var2, var3, var4);
    }

    public boolean CanWeedsGrowInBlock(IBlockAccess var1, int var2, int var3, int var4)
    {
        return true;
    }

    protected void AttemptToGrow(World var1, int var2, int var3, int var4, Random var5)
    {
        if (this.GetWeedsGrowthLevel(var1, var2, var3, var4) == 0 && this.GetGrowthLevel(var1, var2, var3, var4) < 7 && var1.getBlockLightValue(var2, var3 + 1, var4) >= 9)
        {
            Block var6 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

            if (var6 != null && var6.IsBlockHydratedForPlantGrowthOn(var1, var2, var3 - 1, var4))
            {
                float var7 = this.GetBaseGrowthChance(var1, var2, var3, var4) * var6.GetPlantGrowthOnMultiplier(var1, var2, var3 - 1, var4, this);

                if (var5.nextFloat() <= var7)
                {
                    this.IncrementGrowthLevel(var1, var2, var3, var4);
                }
            }
        }
    }

    public void DropSeeds(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(this.getSeedItem(), 1, 0));

        if (var1.rand.nextInt(16) - var7 < 4)
        {
            this.dropBlockAsItem_do(var1, var2, var3, var4, new ItemStack(this.getSeedItem(), 1, 0));
        }
    }

    public float GetBaseGrowthChance(World var1, int var2, int var3, int var4)
    {
        return 0.05F;
    }

    protected void IncrementGrowthLevel(World var1, int var2, int var3, int var4)
    {
        int var5 = this.GetGrowthLevel(var1, var2, var3, var4) + 1;
        this.SetGrowthLevel(var1, var2, var3, var4, var5);

        if (var5 == 7)
        {
            Block var6 = Block.blocksList[var1.getBlockId(var2, var3 - 1, var4)];

            if (var6 != null)
            {
                var6.NotifyOfFullStagePlantGrowthOn(var1, var2, var3 - 1, var4, this);
            }
        }
    }

    protected int GetGrowthLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return this.GetGrowthLevel(var1.getBlockMetadata(var2, var3, var4));
    }

    protected int GetGrowthLevel(int var1)
    {
        return var1 & 7;
    }

    protected void SetGrowthLevel(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -8;
        var1.setBlockMetadataWithNotify(var2, var3, var4, var6 | var5);
    }

    protected void SetGrowthLevelNoNotify(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockMetadata(var2, var3, var4) & -8;
        var1.setBlockMetadata(var2, var3, var4, var6 | var5);
    }

    public boolean RenderBlock(RenderBlocks var1, int var2, int var3, int var4)
    {
        var1.setRenderBounds(this.GetBlockBoundsFromPoolBasedOnState(var1.blockAccess, var2, var3, var4));
        var1.renderBlockCrops(this, var2, var3, var4);
        FCBetterThanWolves.fcBlockWeeds.RenderWeeds(this, var1, var2, var3, var4);
        return true;
    }
}

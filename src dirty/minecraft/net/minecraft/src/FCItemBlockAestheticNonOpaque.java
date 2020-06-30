package net.minecraft.src;

public class FCItemBlockAestheticNonOpaque extends ItemBlock
{
    public FCItemBlockAestheticNonOpaque(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("fcBlockAestheticNonOpaque");
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return var1;
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack var1)
    {
        switch (var1.getItemDamage())
        {
            case 0:
                return super.getUnlocalizedName() + "." + "urn";

            case 1:
                return super.getUnlocalizedName() + "." + "column";

            case 2:
            case 3:
                return super.getUnlocalizedName() + "." + "pedestal";

            case 4:
                return super.getUnlocalizedName() + "." + "table";

            case 5:
                return super.getUnlocalizedName() + "." + "wickerslab";

            case 6:
            case 7:
            case 8:
            case 9:
            case 11:
            default:
                return super.getUnlocalizedName();

            case 10:
                return super.getUnlocalizedName() + "." + "whitecobbleslab";

            case 12:
                return super.getUnlocalizedName() + "." + "lightningrod";
        }
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        FCUtilsBlockPos var11;

        if (var1.getItemDamage() == 12)
        {
            var11 = new FCUtilsBlockPos(var4, var5, var6);
            var11.AddFacingAsOffset(var7);
            return FCBlockAestheticNonOpaque.CanLightningRodStay(var3, var11.i, var11.j, var11.k) ? super.onItemUse(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10) : false;
        }
        else if (var1.getItemDamage() != 5 && var1.getItemDamage() != 10)
        {
            return super.onItemUse(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
        }
        else if (var1.stackSize == 0)
        {
            return false;
        }
        else if (!var2.canPlayerEdit(var4, var5, var6, var7, var1))
        {
            return false;
        }
        else if (this.attemptToCombineWithBlock(var1, var2, var3, var4, var5, var6, var7, true))
        {
            return true;
        }
        else
        {
            var11 = new FCUtilsBlockPos(var4, var5, var6);
            var11.AddFacingAsOffset(var7);
            return this.attemptToCombineWithBlock(var1, var2, var3, var11.i, var11.j, var11.k, var7, false) ? true : super.onItemUse(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
        }
    }

    public float GetBuoyancy(int var1)
    {
        switch (var1)
        {
            case 0:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return 1.0F;

            case 1:
            case 2:
            case 3:
            default:
                return super.GetBuoyancy(var1);
        }
    }

    public boolean canCombineWithBlock(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockId(var2, var3, var4);
        int var7;

        if (var5 == 5)
        {
            if (var6 == FCBetterThanWolves.fcAestheticNonOpaque.blockID)
            {
                var7 = var1.getBlockMetadata(var2, var3, var4);

                if (var7 == 5 || var7 == 9)
                {
                    return true;
                }
            }
        }
        else if (var5 == 10 && var6 == FCBetterThanWolves.fcAestheticNonOpaque.blockID)
        {
            var7 = var1.getBlockMetadata(var2, var3, var4);

            if (var7 == 10 || var7 == 11)
            {
                return true;
            }
        }

        return false;
    }

    public boolean convertToFullBlock(World var1, int var2, int var3, int var4)
    {
        int var5 = var1.getBlockId(var2, var3, var4);

        if (var5 == FCBetterThanWolves.fcAestheticNonOpaque.blockID)
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            int var7;
            byte var8;

            if (var6 == 5 || var6 == 9)
            {
                var7 = FCBetterThanWolves.fcAestheticOpaque.blockID;
                var8 = 0;
                return var1.setBlockAndMetadataWithNotify(var2, var3, var4, var7, var8);
            }

            if (var6 == 10 || var6 == 11)
            {
                var7 = FCBetterThanWolves.fcAestheticOpaque.blockID;
                var8 = 10;
                return var1.setBlockAndMetadataWithNotify(var2, var3, var4, var7, var8);
            }
        }

        return false;
    }

    public boolean IsSlabUpsideDown(int var1, int var2)
    {
        return var1 == FCBetterThanWolves.fcAestheticNonOpaque.blockID && (var2 == 9 || var2 == 11);
    }

    public boolean attemptToCombineWithBlock(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, boolean var8)
    {
        if (this.canCombineWithBlock(var3, var4, var5, var6, var1.getItemDamage()))
        {
            int var9 = var3.getBlockId(var4, var5, var6);
            Block var10 = Block.blocksList[var9];

            if (var10 != null)
            {
                int var11 = var3.getBlockMetadata(var4, var5, var6);
                boolean var12 = this.IsSlabUpsideDown(var9, var11);

                if (!var8 || var7 == 1 && !var12 || var7 == 0 && var12)
                {
                    if (var3.checkNoEntityCollision(Block.GetFulBlockBoundingBoxFromPool(var3, var4, var5, var6)) && this.convertToFullBlock(var3, var4, var5, var6))
                    {
                        var3.playSoundEffect((double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), var10.stepSound.getStepSound(), (var10.stepSound.getVolume() + 1.0F) / 2.0F, var10.stepSound.getPitch() * 0.8F);
                        --var1.stackSize;
                        Block var13 = Block.blocksList[var3.getBlockId(var4, var5, var6)];

                        if (var13 != null)
                        {
                            var3.NotifyNearbyAnimalsOfPlayerBlockAddOrRemove(var2, var13, var4, var5, var6);
                        }
                    }

                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns true if the given ItemBlock can be placed on the given side of the given block position.
     */
    public boolean canPlaceItemBlockOnSide(World var1, int var2, int var3, int var4, int var5, EntityPlayer var6, ItemStack var7)
    {
        FCUtilsBlockPos var8 = new FCUtilsBlockPos(var2, var3, var4);

        if (this.canCombineWithBlock(var1, var8.i, var8.j, var8.k, var7.getItemDamage()))
        {
            int var9 = var1.getBlockId(var8.i, var8.j, var8.k);
            int var10 = var1.getBlockMetadata(var8.i, var8.j, var8.k);
            boolean var11 = this.IsSlabUpsideDown(var9, var10);

            if (var5 == 1 && !var11 || var5 == 0 && var11)
            {
                return true;
            }
        }

        var8.AddFacingAsOffset(var5);
        return this.canCombineWithBlock(var1, var8.i, var8.j, var8.k, var7.getItemDamage()) ? true : super.canPlaceItemBlockOnSide(var1, var2, var3, var4, var5, var6, var7);
    }
}

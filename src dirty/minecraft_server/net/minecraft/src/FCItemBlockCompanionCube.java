package net.minecraft.src;

public class FCItemBlockCompanionCube extends ItemBlock
{
    public FCItemBlockCompanionCube(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("fcCompanionCube");
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return this.getIsSlab(var1) ? 8 : 0;
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack var1)
    {
        return var1.getItemDamage() > 0 ? super.getUnlocalizedName() + "." + "slab" : super.getUnlocalizedName() + "." + "cube";
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (!this.getIsSlab(var1.getItemDamage()))
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
            FCUtilsBlockPos var11 = new FCUtilsBlockPos(var4, var5, var6);
            var11.AddFacingAsOffset(var7);
            return this.attemptToCombineWithBlock(var1, var2, var3, var11.i, var11.j, var11.k, var7, false) ? true : super.onItemUse(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
        }
    }

    public boolean getIsSlab(int var1)
    {
        return var1 != 0;
    }

    public boolean canCombineWithBlock(World var1, int var2, int var3, int var4, int var5)
    {
        int var6 = var1.getBlockId(var2, var3, var4);

        if (this.getIsSlab(var5) && var6 == FCBetterThanWolves.fcCompanionCube.blockID)
        {
            FCBlockCompanionCube var7 = (FCBlockCompanionCube)((FCBlockCompanionCube)Block.blocksList[var6]);

            if (var7 != null && var7.GetIsSlab(var1, var2, var3, var4))
            {
                return true;
            }
        }

        return false;
    }

    public boolean convertToFullBlock(EntityPlayer var1, World var2, int var3, int var4, int var5)
    {
        int var6 = var2.getBlockId(var3, var4, var5);

        if (var6 == FCBetterThanWolves.fcCompanionCube.blockID)
        {
            FCBlockCompanionCube var7 = (FCBlockCompanionCube)((FCBlockCompanionCube)Block.blocksList[var6]);

            if (var7 != null && var7.GetIsSlab(var2, var3, var4, var5))
            {
                FCBlockCompanionCube.SpawnHearts(var2, var3, var4, var5);
                var2.playSoundEffect((double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), var7.stepSound.getStepSound(), (var7.stepSound.getVolume() + 1.0F) / 2.0F, var7.stepSound.getPitch() * 0.8F);
                int var8 = FCBetterThanWolves.fcCompanionCube.blockID;
                byte var9 = 0;
                int var10 = FCUtilsMisc.ConvertPlacingEntityOrientationToBlockFacingReversed(var1);
                int var11 = var7.SetFacing(var9, var10);
                return var2.setBlockAndMetadataWithNotify(var3, var4, var5, var8, var11);
            }
        }

        return false;
    }

    public boolean isSlabUpsideDown(int var1, int var2)
    {
        if (var1 == FCBetterThanWolves.fcCompanionCube.blockID)
        {
            FCBlockCompanionCube var3 = (FCBlockCompanionCube)((FCBlockCompanionCube)Block.blocksList[var1]);

            if (var3 != null)
            {
                return var3.GetIsUpsideDownSlabFromMetadata(var2);
            }
        }

        return false;
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
                boolean var12 = this.isSlabUpsideDown(var9, var11);

                if (!var8 || var7 == 1 && !var12 || var7 == 0 && var12)
                {
                    if (var3.checkNoEntityCollision(var10.getCollisionBoundingBoxFromPool(var3, var4, var5, var6)) && this.convertToFullBlock(var2, var3, var4, var5, var6))
                    {
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
}

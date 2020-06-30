package net.minecraft.src;

public class FCItemSoulUrn extends FCItemThrowable
{
    public FCItemSoulUrn(int var1)
    {
        super(var1);
        this.maxStackSize = 16;
        this.SetBuoyant();
        this.setUnlocalizedName("fcItemUrnSoul");
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (var3.provider.dimensionId == 1)
        {
            return false;
        }
        else
        {
            boolean var11 = false;
            boolean var12 = true;
            int var13 = var3.getBlockId(var4, var5, var6);
            int var14;

            if (var13 == FCBetterThanWolves.fcAestheticVegetation.blockID)
            {
                var14 = var3.getBlockMetadata(var4, var5, var6);

                if (var14 == 2)
                {
                    if (!var3.isRemote)
                    {
                        ((FCBlockAestheticVegetation)FCBetterThanWolves.fcAestheticVegetation).AttemptToGrowBloodwoodSapling(var3, var4, var5, var6, var3.rand);
                    }

                    var11 = true;
                }
            }
            else if (var13 == Block.netherStalk.blockID)
            {
                var14 = var3.getBlockMetadata(var4, var5, var6);

                if (var14 < 3)
                {
                    if (!var3.isRemote)
                    {
                        var3.setBlockMetadataWithNotify(var4, var5, var6, 3);
                        var3.markBlockRangeForRenderUpdate(var4, var5, var6, var4, var5, var6);
                    }

                    var11 = true;
                }
            }
            else if (var13 == FCBetterThanWolves.fcBlockBloodMoss.blockID)
            {
                FCBlockNetherGrowth var16 = (FCBlockNetherGrowth)FCBetterThanWolves.fcBlockBloodMoss;
                int var15 = var16.GetHeightLevel(var3, var4, var5, var6);

                if (var15 < 7)
                {
                    var16.SetHeightLevel(var3, var4, var5, var6, 7);
                    var3.markBlockRangeForRenderUpdate(var4, var5, var6, var4, var5, var6);
                }

                var11 = true;
            }
            else if (!var3.isRemote && FCEntityUrn.AttemptToCreateGolemOrWither(var3, var4, var5, var6))
            {
                var11 = true;
                var12 = false;
            }

            if (var11)
            {
                if (!var3.isRemote)
                {
                    if (!var2.capabilities.isCreativeMode)
                    {
                        --var1.stackSize;
                    }

                    if (var12)
                    {
                        var3.playAuxSFX(2248, var4, var5, var6, 0);
                    }
                }

                return true;
            }
            else
            {
                return false;
            }
        }
    }

    protected void SpawnThrownEntity(ItemStack var1, World var2, EntityPlayer var3)
    {
        var2.spawnEntityInWorld(new FCEntityUrn(var2, var3, this.itemID));
    }

    protected EntityThrowable GetEntityFiredByByBlockDispenser(World var1, double var2, double var4, double var6)
    {
        return new FCEntityUrn(var1, var2, var4, var6, this.itemID);
    }
}

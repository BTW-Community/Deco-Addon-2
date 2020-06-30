package net.minecraft.src;

import java.util.List;

public class FCItemBlockAestheticVegetation extends ItemBlock
{
    public FCItemBlockAestheticVegetation(int var1)
    {
        super(var1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setUnlocalizedName("fcAestheticVegetation");
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int var1)
    {
        return var1 == 3 ? 4 : var1;
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
            case 1:
                return super.getUnlocalizedName() + "." + "vinetrap";

            case 2:
                return super.getUnlocalizedName() + "." + "bloodwoodsapling";

            case 3:
                return super.getUnlocalizedName() + "." + "bloodleaves";

            default:
                return super.getUnlocalizedName();
        }
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        int var11 = var1.getItemDamage();

        if (var11 == 2)
        {
            if (var7 != 1)
            {
                return false;
            }
            else if (var2 != null && (!var2.canPlayerEdit(var4, var5, var6, var7, var1) || !var2.canPlayerEdit(var4, var5 + 1, var6, var7, var1)))
            {
                return false;
            }
            else
            {
                int var12 = var3.getBlockId(var4, var5, var6);
                boolean var13 = false;

                if (var12 == Block.slowSand.blockID)
                {
                    var13 = true;
                }
                else if (var12 == FCBetterThanWolves.fcPlanter.blockID && ((FCBlockPlanter)FCBetterThanWolves.fcPlanter).GetPlanterType(var3, var4, var5, var6) == 8)
                {
                    var13 = true;
                }

                if (var13 && var3.isAirBlock(var4, var5 + 1, var6))
                {
                    var3.setBlockAndMetadataWithNotify(var4, var5 + 1, var6, FCBetterThanWolves.fcAestheticVegetation.blockID, 2);
                    --var1.stackSize;
                    WorldChunkManager var14 = var3.getWorldChunkManager();

                    if (var14 != null)
                    {
                        BiomeGenBase var15 = var14.getBiomeGenAt(var4, var6);

                        if (var15 instanceof BiomeGenHell)
                        {
                            this.AngerPigmen(var3, var2);
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
        else
        {
            return super.onItemUse(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
        }
    }

    public int GetBlockIDToPlace(int var1, int var2, float var3, float var4, float var5)
    {
        return var1 == 3 ? FCBetterThanWolves.fcBlockBloodLeaves.blockID : super.GetBlockIDToPlace(var1, var2, var3, var4, var5);
    }

    private void AngerPigmen(World var1, EntityPlayer var2)
    {
        List var3 = var1.getEntitiesWithinAABB(FCEntityPigZombie.class, var2.boundingBox.expand(32.0D, 32.0D, 32.0D));

        for (int var4 = 0; var4 < var3.size(); ++var4)
        {
            Entity var5 = (Entity)var3.get(var4);
            var5.attackEntityFrom(DamageSource.causePlayerDamage(var2), 0);
        }
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int var1)
    {
        return var1 == 2 ? Block.blocksList[this.getBlockID()].getIcon(1, var1) : super.getIconFromDamage(var1);
    }
}

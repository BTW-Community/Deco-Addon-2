package net.minecraft.src;

public class FCRecipesFishingRodBaiting implements IRecipe
{
    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(InventoryCrafting var1, World var2)
    {
        ItemStack var3 = null;
        ItemStack var4 = null;

        for (int var5 = 0; var5 < var1.getSizeInventory(); ++var5)
        {
            ItemStack var6 = var1.getStackInSlot(var5);

            if (var6 != null)
            {
                if (var6.itemID == Item.fishingRod.itemID)
                {
                    if (var3 != null)
                    {
                        return false;
                    }

                    var3 = var6;
                }
                else
                {
                    if (!this.IsFishingBait(var6))
                    {
                        return false;
                    }

                    if (var4 != null)
                    {
                        return false;
                    }

                    var4 = var6;
                }
            }
        }

        return var3 != null && var4 != null;
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting var1)
    {
        ItemStack var2 = null;
        ItemStack var3 = null;
        ItemStack var4 = null;

        for (int var5 = 0; var5 < var1.getSizeInventory(); ++var5)
        {
            ItemStack var6 = var1.getStackInSlot(var5);

            if (var6 != null)
            {
                if (var6.itemID == Item.fishingRod.itemID)
                {
                    if (var3 != null)
                    {
                        return null;
                    }

                    var3 = var6;
                    var2 = var6.copy();
                    var2.stackSize = 1;
                    var2.itemID = FCBetterThanWolves.fcItemFishingRodBaited.itemID;
                }
                else
                {
                    if (!this.IsFishingBait(var6))
                    {
                        return null;
                    }

                    if (var4 != null)
                    {
                        return null;
                    }

                    var4 = var6;
                }
            }
        }

        if (var4 != null && var3 != null)
        {
            return var2;
        }
        else
        {
            return null;
        }
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize()
    {
        return 2;
    }

    public ItemStack getRecipeOutput()
    {
        return null;
    }

    public boolean matches(IRecipe var1)
    {
        return false;
    }

    public boolean HasSecondaryOutput()
    {
        return false;
    }

    private boolean IsFishingBait(ItemStack var1)
    {
        int var2 = var1.itemID;
        return var2 == FCBetterThanWolves.fcItemCreeperOysters.itemID || var2 == FCBetterThanWolves.fcItemBatWing.itemID || var2 == FCBetterThanWolves.fcItemWitchWart.itemID || var2 == Item.spiderEye.itemID || var2 == Item.rottenFlesh.itemID;
    }
}

package net.minecraft.src;

public class FCRecipesKnitting implements IRecipe
{
    private ItemStack m_tempStackNeedles;
    private ItemStack m_tempStackWool;
    private ItemStack m_tempStackWool2;

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(InventoryCrafting var1, World var2)
    {
        return this.CheckForIngredients(var1);
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting var1)
    {
        if (this.CheckForIngredients(var1))
        {
            ItemStack var2 = new ItemStack(FCBetterThanWolves.fcItemKnitting.itemID, 1, 599);
            int var3 = FCItemWool.m_iWoolColors[FCItemWool.GetClosestColorIndex(FCItemWool.AverageWoolColorsInGrid(var1))];
            FCItemKnitting.SetColor(var2, var3);
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
        return 3;
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

    private boolean CheckForIngredients(InventoryCrafting var1)
    {
        this.m_tempStackNeedles = null;
        this.m_tempStackWool = null;
        this.m_tempStackWool2 = null;

        for (int var2 = 0; var2 < var1.getSizeInventory(); ++var2)
        {
            ItemStack var3 = var1.getStackInSlot(var2);

            if (var3 != null)
            {
                if (var3.itemID == FCBetterThanWolves.fcItemKnittingNeedles.itemID)
                {
                    if (this.m_tempStackNeedles != null)
                    {
                        return false;
                    }

                    this.m_tempStackNeedles = var3;
                }
                else
                {
                    if (var3.itemID != FCBetterThanWolves.fcItemWool.itemID)
                    {
                        return false;
                    }

                    if (this.m_tempStackWool == null)
                    {
                        this.m_tempStackWool = var3;
                    }
                    else
                    {
                        if (this.m_tempStackWool2 != null)
                        {
                            return false;
                        }

                        this.m_tempStackWool2 = var3;
                    }
                }
            }
        }

        return this.m_tempStackNeedles != null && this.m_tempStackWool != null && this.m_tempStackWool2 != null;
    }
}

package net.minecraft.src;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShapelessRecipes implements IRecipe
{
    /** Is the ItemStack that you get when craft the recipe. */
    private final ItemStack recipeOutput;

    /** Is a List of ItemStack that composes the recipe. */
    private final List recipeItems;
    private boolean m_bHasSecondaryOutput = false;

    public ShapelessRecipes(ItemStack par1ItemStack, List par2List)
    {
        this.recipeOutput = par1ItemStack;
        this.recipeItems = par2List;
    }

    public ItemStack getRecipeOutput()
    {
        return this.recipeOutput;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World)
    {
        ArrayList var3 = new ArrayList(this.recipeItems);

        for (int var4 = 0; var4 < 4; ++var4)
        {
            for (int var5 = 0; var5 < 4; ++var5)
            {
                ItemStack var6 = par1InventoryCrafting.getStackInRowAndColumn(var5, var4);

                if (var6 != null && var6.itemID != FCBetterThanWolves.fcItemMould.itemID)
                {
                    boolean var7 = false;
                    Iterator var8 = var3.iterator();

                    while (var8.hasNext())
                    {
                        ItemStack var9 = (ItemStack)var8.next();

                        if (var6.itemID == var9.itemID && (var9.getItemDamage() == 32767 || var6.getItemDamage() == var9.getItemDamage()))
                        {
                            var7 = true;
                            var3.remove(var9);
                            break;
                        }
                    }

                    if (!var7)
                    {
                        return false;
                    }
                }
            }
        }

        return var3.isEmpty();
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
    {
        return this.recipeOutput.copy();
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize()
    {
        return this.recipeItems.size();
    }

    public boolean matches(IRecipe var1)
    {
        if (var1 instanceof ShapelessRecipes)
        {
            ShapelessRecipes var2 = (ShapelessRecipes)var1;

            if (this.recipeOutput.getItem().itemID == var2.recipeOutput.getItem().itemID && this.recipeOutput.stackSize == var2.recipeOutput.stackSize && this.recipeOutput.getItemDamage() == var2.recipeOutput.getItemDamage() && this.recipeItems.size() == var2.recipeItems.size())
            {
                for (int var3 = 0; var3 < this.recipeItems.size(); ++var3)
                {
                    ItemStack var4 = (ItemStack)this.recipeItems.get(var3);
                    ItemStack var5 = (ItemStack)var2.recipeItems.get(var3);

                    if (var4 != null && var5 != null)
                    {
                        if (var4.getItem().itemID != var5.getItem().itemID || var4.stackSize != var5.stackSize || var4.getItemDamage() != var5.getItemDamage())
                        {
                            return false;
                        }
                    }
                    else if (var4 != null || var5 != null)
                    {
                        return false;
                    }
                }

                return true;
            }
        }

        return false;
    }

    public boolean HasSecondaryOutput()
    {
        return this.m_bHasSecondaryOutput;
    }

    public void SetHasSecondaryOutput(boolean var1)
    {
        this.m_bHasSecondaryOutput = var1;
    }
}

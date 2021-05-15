package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public abstract class FCCraftingManagerBulk
{
    private List m_recipes = new ArrayList();

    public void AddRecipe(ItemStack var1, ItemStack[] var2)
    {
        this.AddRecipe(var1, var2, false);
    }

    public void AddRecipe(ItemStack var1, ItemStack var2)
    {
        this.AddRecipe(var1, var2, false);
    }

    public void AddRecipe(ItemStack[] var1, ItemStack[] var2)
    {
        this.AddRecipe(var1, var2, false);
    }

    public void AddRecipe(ItemStack var1, ItemStack[] var2, boolean var3)
    {
        ItemStack[] var4 = new ItemStack[] {var1.copy()};
        this.AddRecipe(var4, var2, var3);
    }

    public void AddRecipe(ItemStack var1, ItemStack var2, boolean var3)
    {
        ItemStack[] var4 = new ItemStack[] {var1.copy()};
        ItemStack[] var5 = new ItemStack[] {var2.copy()};
        this.AddRecipe(var4, var5, var3);
    }

    public void AddRecipe(ItemStack[] var1, ItemStack[] var2, boolean var3)
    {
        FCCraftingManagerBulkRecipe var4 = this.CreateRecipe(var1, var2, var3);
        this.m_recipes.add(var4);
    }

    public boolean RemoveRecipe(ItemStack var1, ItemStack[] var2)
    {
        return this.RemoveRecipe(var1, var2, false);
    }

    public boolean RemoveRecipe(ItemStack var1, ItemStack var2)
    {
        return this.RemoveRecipe(var1, var2, false);
    }

    public boolean RemoveRecipe(ItemStack[] var1, ItemStack[] var2)
    {
        return this.RemoveRecipe(var1, var2, false);
    }

    public boolean RemoveRecipe(ItemStack var1, ItemStack[] var2, boolean var3)
    {
        ItemStack[] var4 = new ItemStack[] {var1.copy()};
        return this.RemoveRecipe(var4, var2, var3);
    }

    public boolean RemoveRecipe(ItemStack var1, ItemStack var2, boolean var3)
    {
        ItemStack[] var4 = new ItemStack[] {var1.copy()};
        ItemStack[] var5 = new ItemStack[] {var2.copy()};
        return this.RemoveRecipe(var4, var5, var3);
    }

    public boolean RemoveRecipe(ItemStack[] var1, ItemStack[] var2, boolean var3)
    {
        FCCraftingManagerBulkRecipe var4 = this.CreateRecipe(var1, var2, var3);
        int var5 = this.GetMatchingRecipeIndex(var4);

        if (var5 >= 0)
        {
            this.m_recipes.remove(var5);
            return true;
        }
        else
        {
            return false;
        }
    }

    public List GetCraftingResult(IInventory var1)
    {
        for (int var2 = 0; var2 < this.m_recipes.size(); ++var2)
        {
            FCCraftingManagerBulkRecipe var3 = (FCCraftingManagerBulkRecipe)this.m_recipes.get(var2);

            if (var3.DoesInventoryContainIngredients(var1))
            {
                return var3.getCraftingOutputList();
            }
        }

        return null;
    }

    public List GetCraftingResult(ItemStack var1)
    {
        for (int var2 = 0; var2 < this.m_recipes.size(); ++var2)
        {
            FCCraftingManagerBulkRecipe var3 = (FCCraftingManagerBulkRecipe)this.m_recipes.get(var2);

            if (var3.DoesStackSatisfyIngredients(var1))
            {
                return var3.getCraftingOutputList();
            }
        }

        return null;
    }

    public List GetValidCraftingIngrediants(IInventory var1)
    {
        for (int var2 = 0; var2 < this.m_recipes.size(); ++var2)
        {
            FCCraftingManagerBulkRecipe var3 = (FCCraftingManagerBulkRecipe)this.m_recipes.get(var2);

            if (var3.DoesInventoryContainIngredients(var1))
            {
                return var3.getCraftingIngrediantList();
            }
        }

        return null;
    }

    public ItemStack GetValidSingleIngredient(ItemStack var1)
    {
        for (int var2 = 0; var2 < this.m_recipes.size(); ++var2)
        {
            FCCraftingManagerBulkRecipe var3 = (FCCraftingManagerBulkRecipe)this.m_recipes.get(var2);

            if (var3.DoesStackSatisfyIngredients(var1))
            {
                return var3.GetFirstIngredient();
            }
        }

        return null;
    }

    public boolean HasRecipeForSingleIngredient(ItemStack var1)
    {
        return this.GetValidSingleIngredient(var1) != null;
    }

    public List ConsumeIngrediantsAndReturnResult(IInventory var1)
    {
        for (int var2 = 0; var2 < this.m_recipes.size(); ++var2)
        {
            FCCraftingManagerBulkRecipe var3 = (FCCraftingManagerBulkRecipe)this.m_recipes.get(var2);

            if (var3.DoesInventoryContainIngredients(var1))
            {
                var3.ConsumeInventoryIngrediants(var1);
                return var3.getCraftingOutputList();
            }
        }

        return null;
    }

    private FCCraftingManagerBulkRecipe CreateRecipe(ItemStack[] var1, ItemStack[] var2, boolean var3)
    {
        ArrayList var4 = new ArrayList();
        int var5 = var2.length;

        for (int var6 = 0; var6 < var5; ++var6)
        {
            var4.add(var2[var6].copy());
        }

        ArrayList var9 = new ArrayList();
        int var7 = var1.length;

        for (int var8 = 0; var8 < var7; ++var8)
        {
            var9.add(var1[var8].copy());
        }

        return new FCCraftingManagerBulkRecipe(var9, var4, var3);
    }

    private int GetMatchingRecipeIndex(FCCraftingManagerBulkRecipe var1)
    {
        boolean var2 = true;

        for (int var3 = 0; var3 < this.m_recipes.size(); ++var3)
        {
            FCCraftingManagerBulkRecipe var4 = (FCCraftingManagerBulkRecipe)this.m_recipes.get(var3);

            if (var4.matches(var1))
            {
                return var3;
            }
        }

        return -1;
    }
}

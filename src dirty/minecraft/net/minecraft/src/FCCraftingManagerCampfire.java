package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

public class FCCraftingManagerCampfire
{
    public static FCCraftingManagerCampfire instance = new FCCraftingManagerCampfire();
    private Map m_recipeMap = new HashMap();

    public ItemStack GetRecipeResult(int var1)
    {
        return (ItemStack)this.m_recipeMap.get(Integer.valueOf(var1));
    }

    public void AddRecipe(int var1, ItemStack var2)
    {
        this.m_recipeMap.put(Integer.valueOf(var1), var2);
    }
}

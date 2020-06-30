package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FCCraftingManagerAnvil
{
    private static final FCCraftingManagerAnvil instance = new FCCraftingManagerAnvil();
    private List recipes = new ArrayList();

    public static final FCCraftingManagerAnvil getInstance()
    {
        return instance;
    }

    void addRecipe(ItemStack var1, Object[] var2)
    {
        String var3 = "";
        int var4 = 0;
        int var5 = 0;
        int var6 = 0;

        if (var2[var4] instanceof String[])
        {
            String[] var7 = (String[])((String[])var2[var4++]);

            for (int var8 = 0; var8 < var7.length; ++var8)
            {
                String var9 = var7[var8];
                ++var6;
                var5 = var9.length();
                var3 = var3 + var9;
            }
        }
        else
        {
            while (var2[var4] instanceof String)
            {
                String var11 = (String)var2[var4++];
                ++var6;
                var5 = var11.length();
                var3 = var3 + var11;
            }
        }

        HashMap var12;

        for (var12 = new HashMap(); var4 < var2.length; var4 += 2)
        {
            Character var13 = (Character)var2[var4];
            ItemStack var15 = null;

            if (var2[var4 + 1] instanceof Item)
            {
                var15 = new ItemStack((Item)var2[var4 + 1]);
            }
            else if (var2[var4 + 1] instanceof Block)
            {
                var15 = new ItemStack((Block)var2[var4 + 1], 1, 32767);
            }
            else if (var2[var4 + 1] instanceof ItemStack)
            {
                var15 = (ItemStack)var2[var4 + 1];
            }

            var12.put(var13, var15);
        }

        ItemStack[] var14 = new ItemStack[var5 * var6];

        for (int var16 = 0; var16 < var5 * var6; ++var16)
        {
            char var10 = var3.charAt(var16);

            if (var12.containsKey(Character.valueOf(var10)))
            {
                var14[var16] = ((ItemStack)var12.get(Character.valueOf(var10))).copy();
            }
            else
            {
                var14[var16] = null;
            }
        }

        this.recipes.add(new ShapedRecipes(var5, var6, var14, var1));
    }

    void addShapelessRecipe(ItemStack var1, Object[] var2)
    {
        ArrayList var3 = new ArrayList();
        Object[] var4 = var2;
        int var5 = var2.length;

        for (int var6 = 0; var6 < var5; ++var6)
        {
            Object var7 = var4[var6];

            if (var7 instanceof ItemStack)
            {
                var3.add(((ItemStack)var7).copy());
            }
            else if (var7 instanceof Item)
            {
                var3.add(new ItemStack((Item)var7));
            }
            else
            {
                if (!(var7 instanceof Block))
                {
                    throw new RuntimeException("Invalid shapeless recipe!");
                }

                var3.add(new ItemStack((Block)var7));
            }
        }

        this.recipes.add(new ShapelessRecipes(var1, var3));
    }

    public ItemStack findMatchingRecipe(InventoryCrafting var1, World var2)
    {
        for (int var3 = 0; var3 < this.recipes.size(); ++var3)
        {
            IRecipe var4 = (IRecipe)this.recipes.get(var3);

            if (var4.matches(var1, var2))
            {
                return var4.getCraftingResult(var1);
            }
        }

        return null;
    }

    public List getRecipeList()
    {
        return this.recipes;
    }

    public boolean RemoveRecipe(ItemStack var1, Object[] var2)
    {
        ShapedRecipes var3 = this.CreateRecipe(var1, var2);
        int var4 = this.GetMatchingRecipeIndex(var3);

        if (var4 >= 0)
        {
            this.recipes.remove(var4);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean RemoveShapelessRecipe(ItemStack var1, Object[] var2)
    {
        ShapelessRecipes var3 = this.CreateShapelessRecipe(var1, var2);
        int var4 = this.GetMatchingRecipeIndex(var3);

        if (var4 >= 0)
        {
            this.recipes.remove(var4);
            return true;
        }
        else
        {
            return false;
        }
    }

    private int GetMatchingRecipeIndex(IRecipe var1)
    {
        boolean var2 = true;

        for (int var3 = 0; var3 < this.recipes.size(); ++var3)
        {
            IRecipe var4 = (IRecipe)((IRecipe)this.recipes.get(var3));

            if (var4.matches(var1))
            {
                return var3;
            }
        }

        return -1;
    }

    private ShapedRecipes CreateRecipe(ItemStack var1, Object[] var2)
    {
        String var3 = "";
        int var4 = 0;
        int var5 = 0;
        int var6 = 0;
        int var9;

        if (var2[var4] instanceof String[])
        {
            String[] var7 = (String[])((String[])var2[var4++]);
            String[] var8 = var7;
            var9 = var7.length;

            for (int var10 = 0; var10 < var9; ++var10)
            {
                String var11 = var8[var10];
                ++var6;
                var5 = var11.length();
                var3 = var3 + var11;
            }
        }
        else
        {
            while (var2[var4] instanceof String)
            {
                String var13 = (String)var2[var4++];
                ++var6;
                var5 = var13.length();
                var3 = var3 + var13;
            }
        }

        HashMap var14;

        for (var14 = new HashMap(); var4 < var2.length; var4 += 2)
        {
            Character var15 = (Character)var2[var4];
            ItemStack var17 = null;

            if (var2[var4 + 1] instanceof Item)
            {
                var17 = new ItemStack((Item)var2[var4 + 1]);
            }
            else if (var2[var4 + 1] instanceof Block)
            {
                var17 = new ItemStack((Block)var2[var4 + 1], 1, 32767);
            }
            else if (var2[var4 + 1] instanceof ItemStack)
            {
                var17 = (ItemStack)var2[var4 + 1];
            }

            var14.put(var15, var17);
        }

        ItemStack[] var16 = new ItemStack[var5 * var6];

        for (var9 = 0; var9 < var5 * var6; ++var9)
        {
            char var18 = var3.charAt(var9);

            if (var14.containsKey(Character.valueOf(var18)))
            {
                var16[var9] = ((ItemStack)var14.get(Character.valueOf(var18))).copy();
            }
            else
            {
                var16[var9] = null;
            }
        }

        return new ShapedRecipes(var5, var6, var16, var1);
    }

    private ShapelessRecipes CreateShapelessRecipe(ItemStack var1, Object[] var2)
    {
        ArrayList var3 = new ArrayList();
        Object[] var4 = var2;
        int var5 = var2.length;

        for (int var6 = 0; var6 < var5; ++var6)
        {
            Object var7 = var4[var6];

            if (var7 instanceof ItemStack)
            {
                var3.add(((ItemStack)var7).copy());
            }
            else if (var7 instanceof Item)
            {
                var3.add(new ItemStack((Item)var7));
            }
            else
            {
                if (!(var7 instanceof Block))
                {
                    throw new RuntimeException("Invalid shapeless recipe!");
                }

                var3.add(new ItemStack((Block)var7));
            }
        }

        return new ShapelessRecipes(var1, var3);
    }
}

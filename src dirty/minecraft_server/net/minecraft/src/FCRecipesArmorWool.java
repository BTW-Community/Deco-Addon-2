package net.minecraft.src;

public class FCRecipesArmorWool extends ShapedRecipes
{
    public FCRecipesArmorWool(int var1, int var2, ItemStack[] var3, ItemStack var4)
    {
        super(var1, var2, var3, var4);
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting var1)
    {
        ItemStack var2 = super.getCraftingResult(var1);

        if (var2 != null && var2.getItem() instanceof FCItemArmorWool)
        {
            int var3 = FCItemWool.AverageWoolColorsInGrid(var1);
            FCItemArmorWool var4 = (FCItemArmorWool)var2.getItem();
            var4.func_82813_b(var2, var3);
        }

        return var2;
    }
}

package net.minecraft.src;

public class FCRecipesWoolBlock extends ShapedRecipes
{
    public FCRecipesWoolBlock(int var1, int var2, ItemStack[] var3, ItemStack var4)
    {
        super(var1, var2, var3, var4);
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting var1)
    {
        ItemStack var2 = super.getCraftingResult(var1);

        if (var2 != null)
        {
            int var3 = FCItemWool.AverageWoolColorsInGrid(var1);
            int var4 = FCItemWool.GetClosestColorIndex(var3);
            var2.setItemDamage(BlockCloth.getBlockFromDye(var4));
        }

        return var2;
    }
}

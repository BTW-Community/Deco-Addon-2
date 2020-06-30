package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class FCCraftingManagerBulkRecipe
{
    private final List m_recipeOutputStacks;
    private final List m_recipeInputStacks;
    private final boolean m_bMetadataExclusive;

    public FCCraftingManagerBulkRecipe(ItemStack var1, List var2)
    {
        this(var1, var2, false);
    }

    public FCCraftingManagerBulkRecipe(ItemStack var1, List var2, boolean var3)
    {
        ArrayList var4 = new ArrayList();
        var4.add(var1.copy());
        this.m_recipeOutputStacks = var4;
        this.m_recipeInputStacks = var2;
        this.m_bMetadataExclusive = var3;
    }

    public FCCraftingManagerBulkRecipe(List var1, List var2, boolean var3)
    {
        this.m_recipeOutputStacks = var1;
        this.m_recipeInputStacks = var2;
        this.m_bMetadataExclusive = var3;
    }

    public List getCraftingOutputList()
    {
        return this.m_recipeOutputStacks;
    }

    public List getCraftingIngrediantList()
    {
        return this.m_recipeInputStacks;
    }

    public ItemStack GetFirstIngredient()
    {
        return this.m_recipeInputStacks != null && this.m_recipeInputStacks.size() > 0 ? (ItemStack)this.m_recipeInputStacks.get(0) : null;
    }

    public boolean DoesInventoryContainIngredients(IInventory var1)
    {
        if (this.m_recipeInputStacks != null && this.m_recipeInputStacks.size() > 0)
        {
            for (int var2 = 0; var2 < this.m_recipeInputStacks.size(); ++var2)
            {
                ItemStack var3 = (ItemStack)this.m_recipeInputStacks.get(var2);

                if (var3 != null && FCUtilsInventory.CountItemsInInventory(var1, var3.getItem().itemID, var3.getItemDamage(), this.m_bMetadataExclusive) < var3.stackSize)
                {
                    return false;
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean DoesStackSatisfyIngredients(ItemStack var1)
    {
        if (this.m_recipeInputStacks != null && this.m_recipeInputStacks.size() == 1)
        {
            ItemStack var2 = (ItemStack)this.m_recipeInputStacks.get(0);
            int var3 = var2.getItemDamage();

            if (var1.itemID == var2.itemID && var1.stackSize >= var2.stackSize && (var3 == 32767 || !this.m_bMetadataExclusive && var1.getItemDamage() == var3 || this.m_bMetadataExclusive && var1.getItemDamage() != var3))
            {
                return true;
            }
        }

        return false;
    }

    public boolean ConsumeInventoryIngrediants(IInventory var1)
    {
        boolean var2 = true;

        if (this.m_recipeInputStacks != null && this.m_recipeInputStacks.size() > 0)
        {
            for (int var3 = 0; var3 < this.m_recipeInputStacks.size(); ++var3)
            {
                ItemStack var4 = (ItemStack)this.m_recipeInputStacks.get(var3);

                if (var4 != null && !FCUtilsInventory.ConsumeItemsInInventory(var1, var4.getItem().itemID, var4.getItemDamage(), var4.stackSize, this.m_bMetadataExclusive))
                {
                    var2 = false;
                }
            }
        }

        return var2;
    }

    public boolean matches(FCCraftingManagerBulkRecipe var1)
    {
        if (this.m_bMetadataExclusive == var1.m_bMetadataExclusive && this.m_recipeInputStacks.size() == var1.m_recipeInputStacks.size() && this.m_recipeOutputStacks.size() == var1.m_recipeOutputStacks.size())
        {
            int var2;

            for (var2 = 0; var2 < this.m_recipeInputStacks.size(); ++var2)
            {
                if (!this.DoStacksMatch((ItemStack)this.m_recipeInputStacks.get(var2), (ItemStack)var1.m_recipeInputStacks.get(var2)))
                {
                    return false;
                }
            }

            for (var2 = 0; var2 < this.m_recipeInputStacks.size(); ++var2)
            {
                if (!this.DoStacksMatch((ItemStack)this.m_recipeOutputStacks.get(var2), (ItemStack)var1.m_recipeOutputStacks.get(var2)))
                {
                    return false;
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean DoStacksMatch(ItemStack var1, ItemStack var2)
    {
        return var1.getItem().itemID == var2.getItem().itemID && var1.stackSize == var2.stackSize && var1.getItemDamage() == var2.getItemDamage();
    }
}

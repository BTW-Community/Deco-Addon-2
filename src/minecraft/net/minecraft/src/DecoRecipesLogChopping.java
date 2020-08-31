package net.minecraft.src;

public class DecoRecipesLogChopping extends FCRecipesLogChopping {
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
				if (this.IsAxe(var6))
				{
					if (var3 != null)
					{
						return false;
					}

					var3 = var6;
				}
				else
				{
					if (!this.IsLog(var6))
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

		for (int var4 = 0; var4 < var1.getSizeInventory(); ++var4)
		{
			ItemStack var5 = var1.getStackInSlot(var4);

			if (var5 != null)
			{
				if (this.IsAxe(var5))
				{
					if (var2 != null)
					{
						return null;
					}

					var2 = var5;
				}
				else
				{
					if (!this.IsLog(var5))
					{
						return null;
					}

					if (var3 != null)
					{
						return null;
					}

					var3 = var5;
				}
			}
		}

		if (var3 != null && var2 != null)
		{
			ItemStack var7 = null;
			FCItemAxe var8 = (FCItemAxe)var2.getItem();

			if (var8.toolMaterial.getHarvestLevel() <= 1)
			{
				return new ItemStack(Item.stick, 2);
			}
			else
			{
                int var6 = var3.itemID;
                
				if (var6 == DecoDefs.bloodLog.blockID)
				{
					return new ItemStack(Block.planks.blockID, 2, 4);
				}
				else if (var6 == DecoDefs.cherryLog.blockID)
				{
					return new ItemStack(Block.planks.blockID, 2, 5);
				}
				else if (var6 == DecoDefs.stemCrimson.blockID)
				{
					return new ItemStack(Block.planks.blockID, 2, 6);
				}
				else if (var6 == DecoDefs.stemWarped.blockID)
				{
					return new ItemStack(Block.planks.blockID, 2, 7);
				}
				else {
					return new ItemStack(Block.planks.blockID, 2, var3.getItemDamage());
				}
			}
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
		return true;
	}

	private boolean IsAxe(ItemStack var1)
	{
		int var2 = var1.itemID;
		return var1.getItem() instanceof FCItemAxe;
	}

	private boolean IsLog(ItemStack itemstack) {
		int ID = itemstack.itemID;
		return ID == DecoDefs.strippedLog.blockID || ID == DecoDefs.barkLog.blockID || ID == DecoDefs.barkLogStripped.blockID || ID == DecoDefs.bloodLog.blockID || ID == DecoDefs.cherryLog.blockID || ID == DecoDefs.stemCrimson.blockID || ID == DecoDefs.stemWarped.blockID;
	}
}

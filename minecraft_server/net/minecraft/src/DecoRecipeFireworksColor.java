package net.minecraft.src;

import java.util.ArrayList;

public class DecoRecipeFireworksColor implements IRecipe
{
	private ItemStack field_92102_a;

	/**
	* Used to check if a recipe matches current crafting inventory
	*/
	@Override public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World)
	{
		this.field_92102_a = null;
		int var3 = 0;
		int var4 = 0;
		int var5 = 0;
		int var6 = 0;
		int var7 = 0;
		int var8 = 0;

		for (int var9 = 0; var9 < par1InventoryCrafting.getSizeInventory(); ++var9)
		{
			ItemStack var10 = par1InventoryCrafting.getStackInSlot(var9);

			if (var10 != null)
			{
				if (var10.itemID == Item.gunpowder.itemID)
				{
					++var4;
				}
				else if (var10.itemID == Item.fireworkCharge.itemID)
				{
					++var6;
				}
				else if (var10.itemID == Item.dyePowder.itemID)
				{
					++var5;
				}
				else if (var10.itemID == Item.paper.itemID)
				{
					++var3;
				}
				else if (var10.itemID == Item.lightStoneDust.itemID)
				{
					++var7;
				}
				else if (var10.itemID == Item.diamond.itemID)
				{
					++var7;
				}
				else if (var10.itemID == Item.fireballCharge.itemID)
				{
					++var8;
				}
				else if (var10.itemID == Item.feather.itemID)
				{
					++var8;
				}
				else if (var10.itemID == Item.goldNugget.itemID)
				{
					++var8;
				}
				else
				{
					if (var10.itemID != Item.skull.itemID)
					{
						return false;
					}
					++var8;
				}
			}
		}

		var7 += var5 + var8;

		if (var4 <= 3 && var3 <= 1)
		{
		int var12;
		ItemStack var13;
		NBTTagCompound var15;
		NBTTagCompound var16;

		if (var4 >= 1 && var3 == 1 && var7 == 0)
		{
			this.field_92102_a = new ItemStack(Item.firework);

			if (var6 > 0)
			{
				var15 = new NBTTagCompound();
				var16 = new NBTTagCompound("Fireworks");
				NBTTagList var19 = new NBTTagList("Explosions");

				for (var12 = 0; var12 < par1InventoryCrafting.getSizeInventory(); ++var12)
				{
					var13 = par1InventoryCrafting.getStackInSlot(var12);

					if (var13 != null && var13.itemID == Item.fireworkCharge.itemID && var13.hasTagCompound() && var13.getTagCompound().hasKey("Explosion"))
					{
						var19.appendTag(var13.getTagCompound().getCompoundTag("Explosion"));
					}
				}

				var16.setTag("Explosions", var19);
				var16.setByte("Flight", (byte)var4);
				var15.setTag("Fireworks", var16);
				this.field_92102_a.setTagCompound(var15);
			}

			return true;
		}
		else
		{
		int var21;

		if (var4 == 1 && var3 == 0 && var6 == 0 && var5 > 0 && var8 <= 1)
		{
			this.field_92102_a = new ItemStack(Item.fireworkCharge);
			var15 = new NBTTagCompound();
			var16 = new NBTTagCompound("Explosion");
			byte var17 = 0;
			ArrayList var20 = new ArrayList();

			for (var21 = 0; var21 < par1InventoryCrafting.getSizeInventory(); ++var21)
			{
				ItemStack var14 = par1InventoryCrafting.getStackInSlot(var21);

				if (var14 != null)
				{
					if (var14.itemID == Item.dyePowder.itemID)
					{
						var20.add(Integer.valueOf(ItemDye.dyeColors[var14.getItemDamage()%16]));
					}
					else if (var14.itemID == Item.lightStoneDust.itemID)
					{
						var16.setBoolean("Flicker", true);
					}
					else if (var14.itemID == Item.diamond.itemID)
					{
						var16.setBoolean("Trail", true);
					}
					else if (var14.itemID == Item.fireballCharge.itemID)
					{
						var17 = 1;
					}
					else if (var14.itemID == Item.feather.itemID)
					{
						var17 = 4;
					}
					else if (var14.itemID == Item.goldNugget.itemID)
					{
						var17 = 2;
					}
					else if (var14.itemID == Item.skull.itemID)
					{
						var17 = 3;
					}
				}
			}

			int[] var24 = new int[var20.size()];

			for (int var23 = 0; var23 < var24.length; ++var23)
			{
				var24[var23] = ((Integer)var20.get(var23)).intValue();
			}

			var16.setIntArray("Colors", var24);
			var16.setByte("Type", var17);
			var15.setTag("Explosion", var16);
			this.field_92102_a.setTagCompound(var15);
			return true;
		}
		else if (var4 == 0 && var3 == 0 && var6 == 1 && var5 > 0 && var5 == var7)
		{
			ArrayList var11 = new ArrayList();

			for (var12 = 0; var12 < par1InventoryCrafting.getSizeInventory(); ++var12)
			{
				var13 = par1InventoryCrafting.getStackInSlot(var12);

				if (var13 != null)
				{
					if (var13.itemID == Item.dyePowder.itemID)
					{
						var11.add(Integer.valueOf(ItemDye.dyeColors[var13.getItemDamage()%16]));
					}
					else if (var13.itemID == Item.fireworkCharge.itemID)
					{
						this.field_92102_a = var13.copy();
						this.field_92102_a.stackSize = 1;
					}
				}
			}

			int[] var18 = new int[var11.size()];

			for (var21 = 0; var21 < var18.length; ++var21)
			{
				var18[var21] = ((Integer)var11.get(var21)).intValue();
			}

			if (this.field_92102_a != null && this.field_92102_a.hasTagCompound())
			{
				NBTTagCompound var22 = this.field_92102_a.getTagCompound().getCompoundTag("Explosion");

				if (var22 == null)
				{
					return false;
				}
				else
				{
					var22.setIntArray("FadeColors", var18);
					return true;
				}
				}
				else
				{
					return false;
				}
				}
				else
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
	}

	@Override public boolean HasSecondaryOutput()
	{
		return false;
	}

	/**
	* Returns an Item that is the result of this recipe
	*/
	@Override public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
	{
		return this.field_92102_a.copy();
	}

	/**
	* Returns the size of the recipe area
	*/
	@Override public int getRecipeSize()
	{
		return 10;
	}

	@Override public ItemStack getRecipeOutput()
	{
		return this.field_92102_a;
	}

	@Override public boolean matches(IRecipe var1)
	{
		return false;
	}
}
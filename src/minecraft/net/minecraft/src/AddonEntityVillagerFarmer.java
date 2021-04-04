package net.minecraft.src;

public class AddonEntityVillagerFarmer extends FCEntityVillager {
	public AddonEntityVillagerFarmer(World world) {
		super(world, FCEntityVillager.professionIDFarmer);
	}

	@Override
	protected void checkForProfessionTrades(MerchantRecipeList recipeList) {
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcBlockDirtLoose.blockID, 0.2F, 48, 64, 1);
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, Block.wood.blockID, 0, 0.15F, 32, 48, 1);
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, Block.wood.blockID, 1, 0.15F, 32, 48, 1);
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, Block.wood.blockID, 2, 0.15F, 32, 48, 1);
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, Block.wood.blockID, 3, 0.15F, 32, 48, 1);
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemWool.itemID, 3, 0.2F, 16, 24, 1);
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.dyePowder.itemID, 15, 0.2F, 32, 48, 1);
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemFlour.itemID, 0.2F, 24, 32, 2);
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.sugar.itemID, 0.2F, 10, 20, 2);
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemCocoaBeans.itemID, 0.2F, 10, 16, 2);
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemMushroomBrown.itemID, 0.2F, 10, 16, 2);
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemHempSeeds.itemID, 0.2F, 24, 32, 2);
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.egg.itemID, 0.2F, 12, 12, 2);
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, Block.thinGlass.blockID, 0.2F, 16, 32, 2);
		this.CheckForWishToBuySingleItemTrade(recipeList, Item.bucketMilk.itemID, 0.05F, 1, 2, 2);
		this.CheckForWishToSellMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemWheat.itemID, 0.2F, 8, 16, 2);
		this.CheckForWishToSellMultipleItemsTrade(recipeList, Item.appleRed.itemID, 0.1F, 2, 4, 2);

		if (this.GetDirtyPeasant() > 0)
		{
			this.CheckForWishToBuySingleItemTrade(recipeList, FCBetterThanWolves.fcItemSoap.itemID, 0.2F, 1, 2, 3);
		}

		this.CheckForWishToBuyMultipleItemsTrade(recipeList, Block.melon.blockID, 0.2F, 8, 10, 3);
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcBlockPumpkinFresh.blockID, 0.2F, 10, 16, 3);
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemStumpRemover.itemID, 0.2F, 8, 12, 3);
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemChocolate.itemID, 0.2F, 1, 2, 3);
		this.CheckForWishToBuySingleItemTrade(recipeList, Item.shears.itemID, 0.1F, 1, 1, 3);
		this.CheckForWishToBuySingleItemTrade(recipeList, Item.flintAndSteel.itemID, 0.1F, 1, 1, 3);
		this.CheckForComplexTrade(recipeList, new ItemStack(FCBetterThanWolves.fcItemStake, 2), new ItemStack(Item.silk, this.rand.nextInt(17) + 16), new ItemStack(Item.emerald, 1), 0.1F, 3);
		this.CheckForWishToSellMultipleItemsTrade(recipeList, Item.bread.itemID, 0.2F, 4, 6, 3);
		this.CheckForWishToSellMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemCookedMushroomOmelet.itemID, 0.1F, 8, 12, 3);
		this.CheckForWishToSellMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemCookedScrambledEggs.itemID, 0.1F, 8, 12, 3);
		this.CheckForWishToBuySingleItemTrade(recipeList, FCBetterThanWolves.fcItemBucketCement.itemID, 0.2F, 2, 4, 4);
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcLightBulbOff.blockID, 0.2F, 2, 4, 4);
		this.CheckForWishToSellMultipleItemsTrade(recipeList, Item.cookie.itemID, 0.2F, 8, 16, 4);
		this.CheckForWishToSellMultipleItemsTrade(recipeList, Item.pumpkinPie.itemID, 0.2F, 1, 2, 4);
		this.CheckForWishToSellSingleItemTrade(recipeList, Item.cake.itemID, 0.2F, 2, 4, 4);
		this.CheckForWishToSellSingleItemTrade(recipeList, Block.mycelium.blockID, 0.2F, 10, 20, 5);
		this.CheckForArcaneScrollTrade(recipeList, Enchantment.looting.effectId, 0.2F, 16, 32, 5);
	}

	@Override
	protected MerchantRecipe getProfessionLevelUpTrade(int level) {
		switch (level) {
			case 1:
				return new MerchantRecipe(new ItemStack(Item.hoeIron, 1), new ItemStack(Item.emerald.itemID, 1, 0), -level);
			case 2:
				return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcMillStone, 1), new ItemStack(Item.emerald.itemID, 2, 0), -level);
			case 3:
				return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcItemWaterWheel, 1), new ItemStack(Item.emerald.itemID, 3, 0), -level);
			case 4:
				return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcBlockPlanterSoil, this.rand.nextInt(5) + 8), new ItemStack(Item.emerald.itemID, 4, 0), -level);
			default:
				return null;
		}
	}

	@Override
    protected boolean isInvalidProfessionTrade(MerchantRecipe trade) {
		return trade.getItemToBuy().itemID == FCBetterThanWolves.fcPlanter.blockID;
    }

	@Override
	protected MerchantRecipe getProfessionDefaultTrade() {
		return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcBlockDirtLoose.blockID, this.rand.nextInt(17) + 48, 0), new ItemStack(Item.emerald.itemID, 1, 0), 1);
	}
	
	//CLIENT ONLY
	@Override
	public String getTexture() {
		if (this.GetDirtyPeasant() > 0)
			return "/btwmodtex/fcDirtyPeasant.png";
		return "/mob/villager/farmer.png";
	}
}
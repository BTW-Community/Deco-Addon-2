package net.minecraft.src;

public class AddonEntityVillagerBlacksmith extends FCEntityVillager {
	public AddonEntityVillagerBlacksmith(World world) {
		super(world, FCEntityVillager.professionIDBlacksmith);
	}

	@Override
	protected void checkForProfessionTrades(MerchantRecipeList recipeList) {
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.coal.itemID, 0.2F, 16, 24, 1);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Block.wood.blockID, 2, 0.2F, 32, 48, 1);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemChunkIronOre.itemID, 0.2F, 18, 27, 1);
        this.CheckForWishToBuySingleItemTrade(recipeList, FCBetterThanWolves.fcBlockFurnaceBrickIdle.blockID, 0.2F, 1, 1, 1);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.swordIron.itemID, 0.2F, 4, 6, 1);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.axeIron.itemID, 0.2F, 4, 6, 1);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.pickaxeIron.itemID, 0.2F, 6, 9, 1);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.shovelIron.itemID, 0.2F, 2, 3, 1);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.hoeIron.itemID, 0.2F, 4, 6, 1);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemNethercoal.itemID, 0.2F, 12, 20, 2);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcBBQ.blockID, 0.2F, 2, 3, 2);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemCreeperOysters.itemID, 0.2F, 14, 16, 2);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemChunkGoldOre.itemID, 0.2F, 18, 27, 2);
        this.CheckForWishToBuySingleItemTrade(recipeList, Item.diamond.itemID, 0.2F, 2, 3, 2);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.bootsIron.itemID, 0.2F, 4, 6, 2);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.helmetIron.itemID, 0.2F, 10, 15, 2);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.plateIron.itemID, 0.2F, 16, 24, 2);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.legsIron.itemID, 0.2F, 14, 21, 2);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.swordDiamond.itemID, 0.2F, 8, 12, 3);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.axeDiamond.itemID, 0.2F, 8, 12, 3);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.pickaxeDiamond.itemID, 0.2F, 12, 18, 3);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.shovelDiamond.itemID, 0.2F, 4, 6, 3);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.hoeDiamond.itemID, 0.2F, 8, 12, 3);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemSoulUrn.itemID, 0, 0.2F, 2, 3, 4);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemHaft.itemID, 0, 0.2F, 6, 8, 4);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemMould.itemID, 0.2F, 12, 16, 4);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcBlockMiningCharge.blockID, 0, 0.2F, 4, 6, 4);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.bootsDiamond.itemID, 0.2F, 8, 12, 4);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.helmetDiamond.itemID, 0.2F, 20, 30, 4);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.plateDiamond.itemID, 0.2F, 32, 48, 4);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.legsDiamond.itemID, 0.2F, 28, 42, 4);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemSoulFlux.itemID, 0, 0.2F, 16, 24, 5);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.bootsChain.itemID, 0.2F, 4, 6, 5);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.helmetChain.itemID, 0.2F, 10, 15, 5);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.plateChain.itemID, 0.2F, 16, 24, 5);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.legsChain.itemID, 0.2F, 14, 21, 5);
        this.CheckForWishToSellSingleItemTrade(recipeList, FCBetterThanWolves.fcItemRefinedAxe.itemID, 0.2F, 16, 24, 5);
        this.CheckForWishToSellSingleItemTrade(recipeList, FCBetterThanWolves.fcItemRefinedHoe.itemID, 0.2F, 16, 24, 5);
        this.CheckForWishToSellSingleItemTrade(recipeList, FCBetterThanWolves.fcItemRefinedPickAxe.itemID, 0.2F, 24, 36, 5);
        this.CheckForWishToSellSingleItemTrade(recipeList, FCBetterThanWolves.fcItemRefinedShovel.itemID, 0.2F, 8, 12, 5);
        this.CheckForWishToSellSingleItemTrade(recipeList, FCBetterThanWolves.fcItemRefinedSword.itemID, 0.2F, 16, 24, 5);
        this.CheckForArcaneScrollTrade(recipeList, Enchantment.unbreaking.effectId, 0.2F, 32, 48, 5);
	}

	@Override
	protected MerchantRecipe getProfessionLevelUpTrade(int level) {
		switch (level) {
            case 1:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcBBQ, 1), new ItemStack(Item.emerald.itemID, 1, 0), -level);
            case 2:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcBellows, 1), new ItemStack(Item.emerald.itemID, 2, 0), -level);
            case 3:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcCrucible, 1), new ItemStack(Item.emerald.itemID, 3, 0), -level);
            case 4:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 8), new ItemStack(Item.emerald.itemID, 4, 0), -level);
            default:
                return null;
        }
	}

	@Override
    protected boolean isInvalidProfessionTrade(MerchantRecipe trade) {
		return trade.getItemToBuy().itemID == FCBetterThanWolves.fcAnvil.blockID && trade.getItemToSell().itemID == Item.emerald.itemID;
    }

	@Override
	protected MerchantRecipe getProfessionDefaultTrade() {
		return new MerchantRecipe(new ItemStack(Item.coal.itemID, this.rand.nextInt(9) + 16, 0), new ItemStack(Item.emerald.itemID, 1, 0), 1);
	}
	
	//CLIENT ONLY
	public String getTexture() {
        return "/mob/villager/smith.png";
	}
}
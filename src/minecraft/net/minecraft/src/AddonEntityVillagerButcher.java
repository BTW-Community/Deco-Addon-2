package net.minecraft.src;

public class AddonEntityVillagerButcher extends FCEntityVillager {
	public AddonEntityVillagerButcher(World world) {
		super(world, FCEntityVillager.professionIDButcher);
	}

	@Override
	protected void checkForProfessionTrades(MerchantRecipeList recipeList) {
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.arrow.itemID, 0.2F, 24, 32, 1);
        this.CheckForWishToBuySingleItemTrade(recipeList, Item.shears.itemID, 0.1F, 1, 1, 1);
        this.CheckForWishToBuySingleItemTrade(recipeList, Item.fishingRod.itemID, 0.1F, 1, 1, 1);
        this.CheckForWishToSellMultipleItemsTrade(recipeList, Item.beefRaw.itemID, 0.2F, 7, 9, 1);
        this.CheckForWishToSellMultipleItemsTrade(recipeList, Item.porkRaw.itemID, 0.2F, 8, 11, 1);
        this.CheckForWishToSellMultipleItemsTrade(recipeList, Item.chickenRaw.itemID, 0.2F, 9, 12, 1);
        this.CheckForWishToSellMultipleItemsTrade(recipeList, Item.fishRaw.itemID, 0.2F, 10, 13, 1);
        this.CheckForWishToSellMultipleItemsTrade(recipeList, Item.leather.itemID, 0.2F, 4, 6, 1);
        this.CheckForWishToSellMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemMuttonRaw.itemID, 0.2F, 10, 13, 1);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemDung.itemID, 0.2F, 10, 16, 2);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemWolfRaw.itemID, 0.2F, 6, 8, 2);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemBark.itemID, 1, 0.2F, 48, 64, 2);
        this.CheckForWishToSellMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemSteakAndPotatoes.itemID, 0.2F, 4, 8, 2);
        this.CheckForWishToSellMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemHamAndEggs.itemID, 0.2F, 4, 8, 2);
        this.CheckForWishToSellMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemTastySandwich.itemID, 0.2F, 4, 8, 2);
        this.CheckForWishToSellMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemFishSoup.itemID, 0.2F, 10, 12, 2);
        this.CheckForWishToSellMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemCookedKebab.itemID, 0.2F, 4, 6, 2);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.carrot.itemID, 0.2F, 10, 16, 3);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.potato.itemID, 0.2F, 10, 16, 3);
        this.CheckForWishToBuySingleItemTrade(recipeList, FCBetterThanWolves.fcItemBeastLiverRaw.itemID, 0.2F, 1, 2, 3);
        this.CheckForWishToSellMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemTannedLeatherCut.itemID, 0.2F, 4, 8, 3);
        this.CheckForWishToSellMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemSteakDinner.itemID, 0.2F, 4, 6, 3);
        this.CheckForWishToSellMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemPorkDinner.itemID, 0.2F, 4, 6, 3);
        this.CheckForWishToSellMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemChickenSoup.itemID, 0.2F, 4, 6, 3);
        this.CheckForWishToSellMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemWolfDinner.itemID, 0.2F, 4, 6, 3);
        this.CheckForWishToSellSingleItemTrade(recipeList, Item.saddle.itemID, 0.2F, 6, 8, 3);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemRawMysteryMeat.itemID, 0.2F, 2, 4, 4);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemDynamite.itemID, 0.2F, 4, 6, 5);
        this.CheckForWishToBuySingleItemTrade(recipeList, FCBetterThanWolves.fcItemScrew.itemID, 0.2F, 2, 3, 4);
        this.CheckForWishToBuySingleItemTrade(recipeList, FCBetterThanWolves.fcItemCompositeBow.itemID, 0.2F, 2, 3, 4);
        this.CheckForWishToSellMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemHeartyStew.itemID, 0.2F, 3, 4, 4);
        this.CheckForWishToSellSingleItemTrade(recipeList, FCBetterThanWolves.fcItemArmorTannedBoots.itemID, 0.1F, 2, 3, 4);
        this.CheckForWishToSellSingleItemTrade(recipeList, FCBetterThanWolves.fcItemArmorTannedChest.itemID, 0.1F, 6, 8, 4);
        this.CheckForWishToSellSingleItemTrade(recipeList, FCBetterThanWolves.fcItemArmorTannedHelm.itemID, 0.1F, 3, 4, 4);
        this.CheckForWishToSellSingleItemTrade(recipeList, FCBetterThanWolves.fcItemArmorTannedLeggings.itemID, 0.1F, 4, 6, 4);
        this.CheckForWishToBuySingleItemTrade(recipeList, FCBetterThanWolves.fcItemBattleAxe.itemID, 0.2F, 4, 5, 5);
        this.CheckForWishToBuySingleItemTrade(recipeList, FCBetterThanWolves.fcCompanionCube.blockID, 0.2F, 1, 2, 5);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemBroadheadArrow.itemID, 0.2F, 6, 12, 5);
        this.CheckForComplexTrade(recipeList, new ItemStack(FCBetterThanWolves.fcBlockLightningRod, 1), new ItemStack(FCBetterThanWolves.fcItemSoap, 1), new ItemStack(Item.emerald, this.rand.nextInt(3) + 3), 0.1F, 5);
        this.CheckForArcaneScrollTrade(recipeList, Enchantment.sharpness.effectId, 0.2F, 32, 48, 5);
	}

	@Override
	protected MerchantRecipe getProfessionLevelUpTrade(int level) {
		switch (level) {
            case 1:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcCauldron, 1), new ItemStack(Item.emerald.itemID, 1, 0), -level);
            case 2:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcSaw, 1), new ItemStack(Item.emerald.itemID, 2, 0), -level);
            case 3:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcItemBreedingHarness, 1), new ItemStack(Item.emerald.itemID, 3, 0), -level);
            case 4:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 12), new ItemStack(Item.emerald.itemID, 4, 0), -level);
            default:
                return null;
        }
	}

	@Override
	protected int checkForProfessionMandatoryTrades(int availableTrades, int level) {
		if (level >= 4 && this.AttemptToAddTradeToBuyingList(new MerchantRecipe(new ItemStack(Item.skull, 1, 0), new ItemStack(Item.emerald, this.rand.nextInt(3) + 6), new ItemStack(Item.skull, 1, 1), 3))) {
            availableTrades--;
        }

        return availableTrades;
	}

	@Override
    protected boolean isInvalidProfessionTrade(MerchantRecipe trade) {
    	return trade.getItemToBuy().itemID == FCBetterThanWolves.fcAestheticNonOpaque.blockID && trade.getItemToBuy().getItemDamage() == 12;
    }

	@Override
	protected MerchantRecipe getProfessionDefaultTrade() {
		return new MerchantRecipe(new ItemStack(Item.emerald.itemID, 1, 0), new ItemStack(Item.beefRaw.itemID, this.rand.nextInt(3) + 7, 0), 1);
	}
	
	//CLIENT ONLY
	public String getTexture() {
		if (this.GetCurrentTradeLevel() >= 3)
            return "/btwmodtex/fcButcherLvl.png";
        return "/mob/villager/butcher.png";
	}
}
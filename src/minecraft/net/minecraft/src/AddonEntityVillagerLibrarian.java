package net.minecraft.src;

public class AddonEntityVillagerLibrarian extends FCEntityVillager {
	public AddonEntityVillagerLibrarian(World world) {
		super(world, FCEntityVillager.professionIDLibrarian);
	}

	@Override
	protected void checkForProfessionTrades(MerchantRecipeList recipeList) {
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.paper.itemID, 0.2F, 27, 38, 1);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.dyePowder.itemID, 0, 0.2F, 27, 38, 1);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.feather.itemID, 0.2F, 27, 38, 1);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.book.itemID, 0.2F, 1, 3, 2);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.writableBook.itemID, 0.2F, 1, 1, 2);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Block.bookShelf.blockID, 0.2F, 1, 1, 2);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.netherStalkSeeds.itemID, 0.2F, 16, 24, 2);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.lightStoneDust.itemID, 0.2F, 24, 32, 2);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemNitre.itemID, 0.2F, 32, 48, 2);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemBatWing.itemID, 0.2F, 14, 16, 2);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.spiderEye.itemID, 0.2F, 4, 8, 2);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.redstone.itemID, 0.2F, 32, 48, 2);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemWitchWart.itemID, 0.2F, 6, 10, 3);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemMysteriousGland.itemID, 0.2F, 14, 16, 3);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.fermentedSpiderEye.itemID, 0.2F, 4, 8, 3);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.ghastTear.itemID, 0.2F, 4, 6, 3);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.magmaCream.itemID, 0.2F, 8, 12, 3);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.blazePowder.itemID, 0.2F, 4, 6, 3);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcBlockDetector.blockID, 0.2F, 2, 3, 4);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcBuddyBlock.blockID, 0.2F, 2, 3, 4);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcBlockDispenser.blockID, 0.2F, 2, 3, 4);
        this.CheckForWishToBuySingleItemTrade(recipeList, FCBetterThanWolves.fcLens.blockID, 0.2F, 2, 3, 4);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemBrimstone.itemID, 0.2F, 16, 32, 5);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcAestheticVegetation.blockID, 2, 0.2F, 8, 16, 5);
        this.CheckForWishToBuySingleItemTrade(recipeList, FCBetterThanWolves.fcItemBloodMossSpores.itemID, 0.2F, 2, 3, 5);
        this.CheckForArcaneScrollTrade(recipeList, Enchantment.power.effectId, 0.2F, 32, 48, 5);
	}

	@Override
	protected MerchantRecipe getProfessionLevelUpTrade(int level) {
		switch (level) {
            case 1:
                return new MerchantRecipe(new ItemStack(Item.enchantedBook, 1), new ItemStack(Item.emerald.itemID, 2, 0), -level);
            case 2:
                return new MerchantRecipe(new ItemStack(Item.brewingStand, 1), new ItemStack(Item.emerald.itemID, 2, 0), -level);
            case 3:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcBlockDispenser, 1), new ItemStack(Item.emerald.itemID, 4, 0), -level);
            case 4:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcItemEnderSpectacles, 1), new ItemStack(Item.emerald.itemID, 3, 0), -level);
            default:
                return null;
        }
	}

	@Override
	protected int checkForProfessionMandatoryTrades(int availableTrades, int level) {
		int var2 = this.GetCurrentTradeLevel();

	    if (var2 >= 5 && this.AttemptToAddTradeToBuyingList(new MerchantRecipe(new ItemStack(Item.enderPearl, 1), new ItemStack(Item.emerald, this.rand.nextInt(3) + 6), new ItemStack(Item.eyeOfEnder, 1), 4))) {
	        availableTrades--;
	    }
	
	    return availableTrades;
	}

	@Override
	protected MerchantRecipe getProfessionDefaultTrade() {
		return new MerchantRecipe(new ItemStack(Item.paper.itemID, this.rand.nextInt(12) + 27, 0), new ItemStack(Item.emerald.itemID, 1, 0), 1);
	}
	
	//CLIENT ONLY
	public String getTexture() {
		if (this.GetCurrentTradeLevel() >= 5)
            return "/btwmodtex/fcLibrarianSpecs.png";
        return "/mob/villager/librarian.png";
	}
}
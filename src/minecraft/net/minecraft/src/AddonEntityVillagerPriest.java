package net.minecraft.src;

public class AddonEntityVillagerPriest extends FCEntityVillager {
	public AddonEntityVillagerPriest(World world) {
		super(world, FCEntityVillager.professionIDPriest);
	}

	@Override
	protected void checkForProfessionTrades(MerchantRecipeList recipeList) {
		this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemHemp.itemID, 0.2F, 18, 22, 1);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemMushroomRed.itemID, 0.2F, 10, 16, 1);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Block.cactus.blockID, 0.2F, 32, 64, 1);
        this.CheckForWishToBuySingleItemTrade(recipeList, Item.painting.itemID, 0.1F, 2, 3, 1);
        this.CheckForWishToBuySingleItemTrade(recipeList, Item.flintAndSteel.itemID, 0.1F, 1, 1, 1);
        this.CheckForItemEnchantmentForCostTrade(recipeList, Item.swordIron, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(recipeList, Item.axeIron, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(recipeList, Item.pickaxeIron, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(recipeList, Item.helmetIron, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(recipeList, Item.plateIron, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(recipeList, Item.legsIron, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(recipeList, Item.bootsIron, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(recipeList, Item.swordDiamond, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(recipeList, Item.axeDiamond, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(recipeList, Item.pickaxeDiamond, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(recipeList, Item.helmetDiamond, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(recipeList, Item.plateDiamond, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(recipeList, Item.legsDiamond, 0.05F, 2, 4, 2);
        this.CheckForItemEnchantmentForCostTrade(recipeList, Item.bootsDiamond, 0.05F, 2, 4, 2);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemCandle.itemID, this.rand.nextInt(16), 0.2F, 4, 8, 3);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.skull.itemID, 0, 0.2F, 3, 5, 3);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.skull.itemID, 2, 0.2F, 2, 4, 3);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, Item.skull.itemID, 4, 0.2F, 2, 4, 3);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcAestheticOpaque.blockID, 15, 0.2F, 4, 6, 3);
        this.CheckForWishToBuyMultipleItemsTrade(recipeList, FCBetterThanWolves.fcItemSoulUrn.itemID, 0.2F, 2, 3, 4);
        this.CheckForWishToBuySingleItemTrade(recipeList, FCBetterThanWolves.fcItemCanvas.itemID, 0.2F, 2, 3, 5);
        this.CheckForArcaneScrollTrade(recipeList, Enchantment.fortune.effectId, 0.2F, 48, 64, 5);
	}

	@Override
	protected MerchantRecipe getProfessionLevelUpTrade(int level) {
		switch (level) {
            case 1:
                return new MerchantRecipe(new ItemStack(Block.enchantmentTable, 1), new ItemStack(Item.emerald.itemID, 2, 0), -level);
            case 2:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcBlockArcaneVessel, 1), new ItemStack(Item.emerald.itemID, 2, 0), -level);
            case 3:
                return new MerchantRecipe(new ItemStack(Item.skull.itemID, 1, 1), new ItemStack(Item.emerald.itemID, 3, 0), -level);
            case 4:
                return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcInfernalEnchanter, 1), new ItemStack(Item.emerald.itemID, 4, 0), -level);
            default:
                return null;
        }
	}

	@Override
	protected int checkForProfessionMandatoryTrades(int availableTrades, int level) {
		if (level >= 4) {
            if (this.AttemptToAddTradeToBuyingList(new MerchantRecipe(new ItemStack(Item.skull, 1, 1), new ItemStack(Item.emerald, this.rand.nextInt(3) + 6), new ItemStack(Item.skull, 1, 5), 3))) {
                availableTrades--;
            }

            if (availableTrades >= 0 && this.AttemptToAddTradeToBuyingList(new MerchantRecipe(new ItemStack(Item.netherStar), new ItemStack(FCBetterThanWolves.fcBlockSoulforgeDormant), new ItemStack(FCBetterThanWolves.fcAnvil), 3))) {
                availableTrades--;
            }
        }

        return availableTrades;
	}

	@Override
    protected boolean isInvalidProfessionTrade(MerchantRecipe trade) {
		if (trade.getItemToBuy().itemID == Item.netherStar.itemID)
        {
            if (trade.getSecondItemToBuy() == null && trade.getItemToSell().itemID == Item.emerald.itemID || trade.getSecondItemToBuy() != null && trade.getSecondItemToBuy().itemID == Item.emerald.itemID && trade.getItemToSell().itemID == FCBetterThanWolves.fcAnvil.blockID)
            {
                return true;
            }
        }
        else if (trade.getItemToBuy().itemID == Item.bone.itemID && trade.getItemToBuy().stackSize > 16)
        {
            return true;
        }

        return false;
    }

	@Override
	protected MerchantRecipe getProfessionDefaultTrade() {
		return new MerchantRecipe(new ItemStack(FCBetterThanWolves.fcItemHemp.itemID, this.rand.nextInt(5) + 18, 0), new ItemStack(Item.emerald.itemID, 1, 0), 1);
	}
	
	//CLIENT ONLY
	public String getTexture() {
		if (this.GetCurrentTradeLevel() >= 5)
            return "/btwmodtex/fcPriestLvl.png";
        return "/mob/villager/priest.png";
	}
}
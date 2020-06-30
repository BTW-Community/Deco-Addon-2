package net.minecraft.src;

public class FCItemFood extends ItemFood
{
    public static final int m_iFoodPoisioningStandardDuration = 60;
    public static final float m_fFoodPoisoningStandardChance = 0.3F;
    public static final int m_iFoodPoisioningIncreasedDuration = 60;
    public static final float m_fFoodPoisoningIncreasedChance = 0.8F;
    public static final int m_iDonutHungerHealed = 1;
    public static final float m_fDonutSaturationModifier = 0.5F;
    public static final String m_sDonutItemName = "fcItemDonut";
    public static final int m_iDogFoodHungerHealed = 3;
    public static final float m_fDogFoodSaturationModifier = 0.0F;
    public static final String m_sDogFoodItemName = "fcItemKibble";
    public static final int m_iRawEggHungerHealed = 2;
    public static final float m_fRawEggSaturationModifier = 0.25F;
    public static final String m_sRawEggItemName = "fcItemEggRaw";
    public static final int m_iFriedEggHungerHealed = 3;
    public static final float m_fFriedEggSaturationModifier = 0.25F;
    public static final String m_sFriedEggItemName = "fcItemEggFried";
    public static final int m_iBoiledPotatoHungerHealed = 2;
    public static final float m_fBoiledPotatoSaturationModifier = 0.0F;
    public static final String m_sBoiledPotatoItemName = "fcItemPotatoBoiled";
    public static final int m_iCookedCarrotHungerHealed = 2;
    public static final float m_fCookedCarrotSaturationModifier = 0.0F;
    public static final String m_sCookedCarrotItemName = "fcItemCarrotCooked";
    public static final int m_iTastySandwichHungerHealed = 5;
    public static final float m_fTastySandwichSaturationModifier = 0.25F;
    public static final String m_sTastySandwichItemName = "fcItemSandwichTasty";
    public static final int m_iSteakAndPotatoesHungerHealed = 6;
    public static final float m_fSteakAndPotatoesSaturationModifier = 0.25F;
    public static final String m_sSteakAndPotatoesItemName = "fcItemSteakAndPotatoes";
    public static final int m_iHamAndEggsHungerHealed = 6;
    public static final float m_fHamAndEggsSaturationModifier = 0.25F;
    public static final String m_sHamAndEggsItemName = "fcItemHamAndEggs";
    public static final int m_iSteakDinnerHungerHealed = 8;
    public static final float m_fSteakDinnerSaturationModifier = 0.25F;
    public static final String m_sSteakDinnerItemName = "fcItemDinnerSteak";
    public static final int m_iPorkDinnerHungerHealed = 8;
    public static final float m_fPorkDinnerSaturationModifier = 0.25F;
    public static final String m_sPorkDinnerItemName = "fcItemDinnerPork";
    public static final int m_iWolfDinnerHungerHealed = 8;
    public static final float m_fWolfDinnerSaturationModifier = 0.25F;
    public static final String m_sWolfDinnerItemName = "fcItemDinnerWolf";
    public static final int m_iRawKebabHungerHealed = 6;
    public static final float m_fRawKebabSaturationModifier = 0.25F;
    public static final String m_sRawKebabItemName = "fcItemKebabRaw";
    public static final int m_iCookedKebabHungerHealed = 8;
    public static final float m_fCookedKebabSaturationModifier = 0.25F;
    public static final String m_sCookedKebabItemName = "fcItemKebabCooked";
    public static final int m_iChickenSoupHungerHealed = 8;
    public static final float m_fChickenSoupSaturationModifier = 0.25F;
    public static final String m_sChickenSoupItemName = "fcItemSoupChicken";
    public static final int m_iFishSoupHungerHealed = 5;
    public static final float m_fFishSoupSaturationModifier = 0.25F;
    public static final String m_sFishSoupItemName = "fcItemChowder";
    public static final int m_iHeartyStewHungerHealed = 10;
    public static final float m_fHeartyStewSaturationModifier = 0.25F;
    public static final String m_sHeartyStewItemName = "fcItemStewHearty";
    public static final int m_iRawMushroomOmeletHungerHealed = 3;
    public static final float m_fRawMushroomOmeletSaturationModifier = 0.25F;
    public static final String m_sRawMushroomOmeletItemName = "fcItemMushroomOmletRaw";
    public static final int m_iCookedMushroomOmeletHungerHealed = 4;
    public static final float m_fCookedMushroomOmeletSaturationModifier = 0.25F;
    public static final String m_sCookedMushroomOmeletItemName = "fcItemMushroomOmletCooked";
    public static final int m_iRawScrambledEggsHungerHealed = 3;
    public static final float m_fRawScrambledEggsSaturationModifier = 0.25F;
    public static final String m_sRawScrambledEggsItemName = "fcItemEggScrambledRaw";
    public static final int m_iCookedScrambledEggsHungerHealed = 4;
    public static final float m_fCookedScrambledEggsSaturationModifier = 0.25F;
    public static final String m_sCookedScrambledEggsItemName = "fcItemEggScrambledCooked";
    public static final int m_iCreeperOystersHungerHealed = 2;
    public static final float m_fCreeperOystersSaturationModifier = 0.8F;
    public static final String m_sCreeperOystersItemName = "fcItemCreeperOysters";
    public static final int m_iBatWingHungerHealed = 1;
    public static final float m_fBatWingSaturationModifier = 0.8F;
    public static final String m_sBatWingItemName = "fcItemBatWing";
    public static final int m_iChocolateHungerHealed = 2;
    public static final float m_fChocolateSaturationModifier = 0.5F;
    public static final String m_sChocolateItemName = "fcItemChocolate";
    public static final int m_iMuttonRawHungerHealed = 3;
    public static final int m_iMuttonCookedHungerHealed = 4;
    public static final float m_fMuttonSaturationModifier = 0.25F;
    public static final int m_iBeastLiverRawHungerHealed = 5;
    public static final int m_iBeastLiverCookedHungerHealed = 6;
    public static final float m_fBeastLiverSaturationModifier = 0.5F;
    public static final int m_iChickenRawHungerHealed = 3;
    public static final int m_iChickenCookedHungerHealed = 4;
    public static final float m_fChickenSaturationModifier = 0.25F;
    public static final int m_iBeefRawHungerHealed = 4;
    public static final int m_iBeefCookedHungerHealed = 5;
    public static final float m_fBeefSaturationModifier = 0.25F;
    public static final int m_iFishRawHungerHealed = 3;
    public static final int m_iFishCookedHungerHealed = 4;
    public static final float m_fFishSaturationModifier = 0.25F;
    public static final int m_iPorkChopRawHungerHealed = 4;
    public static final int m_iPorkChopCookedHungerHealed = 5;
    public static final float m_fPorkChopSaturationModifier = 0.25F;
    public static final int m_iMeatCuredHungerHealed = 2;
    public static final float m_fMeatCuredSaturationModifier = 0.25F;
    public static final int m_iMeatBurnedHungerHealed = 2;
    public static final float m_fMeatBurnedSaturationModifier = 0.25F;
    private String m_sIconNameOverride = null;

    public FCItemFood(int var1, int var2, float var3, boolean var4, String var5)
    {
        super(var1, var2, var3, var4);
        this.setUnlocalizedName(var5);
    }

    public FCItemFood(int var1, int var2, float var3, boolean var4, String var5, boolean var6)
    {
        super(var1, var2, var3, var4, var6);
        this.setUnlocalizedName(var5);
    }

    public FCItemFood SetStandardFoodPoisoningEffect()
    {
        this.setPotionEffect(Potion.hunger.id, 60, 0, 0.3F);
        return this;
    }

    public FCItemFood SetIncreasedFoodPoisoningEffect()
    {
        this.setPotionEffect(Potion.hunger.id, 60, 0, 0.8F);
        return this;
    }

    public FCItemFood SetIconName(String var1)
    {
        this.m_sIconNameOverride = var1;
        return this;
    }
}

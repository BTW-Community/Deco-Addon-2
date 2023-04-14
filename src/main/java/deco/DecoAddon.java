package deco;

import btw.AddonHandler;
import btw.BTWAddon;
import btw.BTWMod;
import deco.block.DecoBlockInitializer;
import deco.crafting.recipes.DecoCraftingRecipeList;
import deco.entity.DecoEntityManager;
import deco.item.DecoItemInitializer;

import java.util.Map;

public class DecoAddon extends BTWAddon {
    private static DecoAddon instance;
    
    public static boolean diamondPicksAlwaysCollectStone;

    private DecoAddon() {
        super("Deco Addon", "4.1.0", "Deco");
    }
    
    @Override
    public void preInitialize() {
        this.registerProperty("DisableHardcoreBouncing", "True", "Set the following to false to disable placing blocks while jumping");
        this.registerProperty("DiamondPicksAlwaysCollectStone", "False", "Set the following to true to make diamond pickaxes always collect whole stone blocks, or false to only collect isolated blocks.");
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");

        DecoBlockInitializer.initDecoBlocks();
        DecoItemInitializer.initDecoItems();
        DecoEntityManager.initEntities();
    }

    @Override
    public void postInitialize() {
        DecoBlockInitializer.initAddonOverlapBlocks();
        DecoCraftingRecipeList.initRecipes();
    }
    
    @Override
    public void handleConfigProperties(Map<String, String> propertyValues) {
        BTWMod.allowPlaceWhileJumping = Boolean.parseBoolean(propertyValues.get("DisableHardcoreBouncing"));
        diamondPicksAlwaysCollectStone = Boolean.parseBoolean(propertyValues.get("DiamondPicksAlwaysCollectStone"));
    }

    public static DecoAddon getInstance() {
        if (instance == null)
            instance = new DecoAddon();
        return instance;
    }
}

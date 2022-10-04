package deco;

import btw.AddonHandler;
import btw.BTWAddon;
import deco.block.DecoBlockInitializer;
import deco.crafting.recipes.CraftingRecipeList;
import deco.item.DecoItemInitializer;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class DecoAddon extends BTWAddon {
    private static DecoAddon instance;

    private DecoAddon() {
        super("Deco Addon", "4.0.0", "Deco");
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");

        DecoBlockInitializer.initDecoBlocks();
        DecoItemInitializer.initDecoItems();
    }

    @Override
    public void postInitialize() {
        CraftingRecipeList.initRecipes();
    }

    public static DecoAddon getInstance() {
        if (instance == null)
            instance = new DecoAddon();
        return instance;
    }
}

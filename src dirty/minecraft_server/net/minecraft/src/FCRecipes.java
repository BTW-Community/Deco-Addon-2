package net.minecraft.src;

public abstract class FCRecipes
{
    private static final int m_iIgnoreMetadata = 32767;

    public static void AddAllModRecipes()
    {
        RemoveVanillaRecipes();
        AddClusteredRecipes();
        AddBlockRecipes();
        AddItemRecipes();
        AddDyeRecipes();
        AddAlternateVanillaRecipes();
        AddConversionRecipes();
        AddSmeltingRecipes();
        AddCampfireRecipes();
        AddAnvilRecipes();
        AddCauldronRecipes();
        AddCrucibleRecipes();
        AddMillStoneRecipes();
        AddTuningForkRecipes();
        AddSubBlockRecipes();
        AddLegacyConversionRecipes();
        AddCustomRecipeClasses();
        AddDebugRecipes();
    }

    public static ShapedRecipes AddRecipe(ItemStack var0, Object[] var1)
    {
        return CraftingManager.getInstance().addRecipe(var0, var1);
    }

    public static ShapelessRecipes AddShapelessRecipe(ItemStack var0, Object[] var1)
    {
        return CraftingManager.getInstance().AddShapelessRecipe(var0, var1);
    }

    public static ShapelessRecipes AddShapelessRecipeWithSecondaryOutputIndicator(ItemStack var0, Object[] var1)
    {
        ShapelessRecipes var2 = CraftingManager.getInstance().AddShapelessRecipe(var0, var1);

        if (var2 != null)
        {
            var2.SetHasSecondaryOutput(true);
        }

        return var2;
    }

    public static ShapedRecipes AddShapedRecipeWithCustomClass(Class var0, ItemStack var1, Object[] var2)
    {
        return CraftingManager.getInstance().AddShapedRecipeWithCustomClass(var0, var1, var2);
    }

    public static void RemoveVanillaRecipe(ItemStack var0, Object[] var1)
    {
        CraftingManager.getInstance().RemoveRecipe(var0, var1);
    }

    public static void RemoveVanillaShapelessRecipe(ItemStack var0, Object[] var1)
    {
        CraftingManager.getInstance().RemoveShapelessRecipe(var0, var1);
    }

    public static void AddAnvilRecipe(ItemStack var0, Object[] var1)
    {
        FCCraftingManagerAnvil.getInstance().addRecipe(var0, var1);
    }

    public static void AddShapelessAnvilRecipe(ItemStack var0, Object[] var1)
    {
        FCCraftingManagerAnvil.getInstance().addShapelessRecipe(var0, var1);
    }

    public static void RemoveAnvilRecipe(ItemStack var0, Object[] var1)
    {
        FCCraftingManagerAnvil.getInstance().RemoveRecipe(var0, var1);
    }

    public static void RemoveShapelessAnvilRecipe(ItemStack var0, Object[] var1)
    {
        FCCraftingManagerAnvil.getInstance().RemoveShapelessRecipe(var0, var1);
    }

    public static void AddCauldronRecipe(ItemStack var0, ItemStack[] var1)
    {
        FCCraftingManagerCauldron.getInstance().AddRecipe(var0, var1);
    }

    public static void AddCauldronRecipe(ItemStack[] var0, ItemStack[] var1)
    {
        FCCraftingManagerCauldron.getInstance().AddRecipe(var0, var1);
    }

    public static void AddStokedCauldronRecipe(ItemStack var0, ItemStack[] var1)
    {
        FCCraftingManagerCauldronStoked.getInstance().AddRecipe(var0, var1);
    }

    public static void AddStokedCauldronRecipe(ItemStack[] var0, ItemStack[] var1)
    {
        FCCraftingManagerCauldronStoked.getInstance().AddRecipe(var0, var1);
    }

    public static void AddCrucibleRecipe(ItemStack var0, ItemStack[] var1)
    {
        FCCraftingManagerCrucible.getInstance().AddRecipe(var0, var1);
    }

    public static void AddCrucibleRecipe(ItemStack[] var0, ItemStack[] var1)
    {
        FCCraftingManagerCrucible.getInstance().AddRecipe(var0, var1);
    }

    public static void AddStokedCrucibleRecipe(ItemStack var0, ItemStack[] var1)
    {
        FCCraftingManagerCrucibleStoked.getInstance().AddRecipe(var0, var1);
    }

    public static void AddStokedCrucibleRecipe(ItemStack[] var0, ItemStack[] var1)
    {
        FCCraftingManagerCrucibleStoked.getInstance().AddRecipe(var0, var1);
    }

    public static void AddMillStoneRecipe(ItemStack var0, ItemStack[] var1)
    {
        FCCraftingManagerMillStone.getInstance().AddRecipe(var0, var1);
    }

    public static void AddMillStoneRecipe(ItemStack var0, ItemStack var1)
    {
        FCCraftingManagerMillStone.getInstance().AddRecipe(var0, var1);
    }

    public static void AddMillStoneRecipe(ItemStack[] var0, ItemStack[] var1)
    {
        FCCraftingManagerMillStone.getInstance().AddRecipe(var0, var1);
    }

    public static void AddCampfireRecipe(int var0, ItemStack var1)
    {
        FCCraftingManagerCampfire.instance.AddRecipe(var0, var1);
    }

    private static void AddClusteredRecipes()
    {
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockRottenFleshSlab, 6), new Object[] {"###", '#', FCBetterThanWolves.fcBlockRottenFlesh});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockRottenFlesh), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockRottenFleshSlab), new ItemStack(FCBetterThanWolves.fcBlockRottenFleshSlab)});
        AddShapelessRecipe(new ItemStack(Item.rottenFlesh, 9), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockRottenFlesh)});
        AddShapelessRecipe(new ItemStack(Item.rottenFlesh, 4), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockRottenFleshSlab)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockBoneSlab, 6), new Object[] {"###", '#', new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 15)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 15), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockBoneSlab), new ItemStack(FCBetterThanWolves.fcBlockBoneSlab)});
        AddShapelessRecipe(new ItemStack(Item.bone, 9), new Object[] {new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 15)});
        AddShapelessRecipe(new ItemStack(Item.bone, 4), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockBoneSlab)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockCreeperOystersSlab, 6), new Object[] {"###", '#', FCBetterThanWolves.fcBlockCreeperOysters});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockCreeperOysters), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockCreeperOystersSlab), new ItemStack(FCBetterThanWolves.fcBlockCreeperOystersSlab)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemCreeperOysters, 16), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockCreeperOysters)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemCreeperOysters, 8), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockCreeperOystersSlab)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockSmoothstoneStairs, 6), new Object[] {"#  ", "## ", "###", '#', Block.stone});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockWoodBloodStairs, 6), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Block.planks, 1, 4)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockWoodBloodStairs, 1), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 4)});
        AddRecipe(new ItemStack(Block.woodSingleSlab, 6, 4), new Object[] {"###", '#', new ItemStack(Block.planks, 1, 4)});
        AddEarlyGameRecipes();
        AddToolRecipes();
        AddLooseStoneRecipes();
        AddLooseBrickRecipes();
        AddLooseStoneBrickRecipes();
        AddLooseNetherBrickRecipes();
        AddTorchRecipes();
        AddWickerRecipes();
        AddWoodStairRecipes();
        AddWoolAndKnittingRecipes();
        AddSawDustRecipes();
        AddMeatCuringRecipes();
        AddPaneRecipes();
        AddSnowRecipes();
        AddChickenFeedRecipes();
        AddFishingRecipes();
        AddDirtRecipes();
        AddGravelRecipes();
        AddSandRecipes();
        AddMechanicalRecipes();
        AddOreRecipes();
        AddPastryRecipes();
    }

    private static void AddEarlyGameRecipes()
    {
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemChiselWood), new Object[] {new ItemStack(Item.stick)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemChiselStone), new Object[] {new ItemStack(FCBetterThanWolves.fcItemStone)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemChiselIron), new Object[] {"XX", "XX", 'X', FCBetterThanWolves.fcItemNuggetIron});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemClubWood), new Object[] {"X", "X", 'X', Item.stick});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemClubBone), new Object[] {"X", "X", 'X', Item.bone});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemFireStarterSticks), new Object[] {"XX", 'X', Item.stick});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemFireStarterBow), new Object[] {new ItemStack(Item.stick), new ItemStack(Item.stick), new ItemStack(Item.silk)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemFireStarterBow), new Object[] {new ItemStack(Item.stick), new ItemStack(Item.stick), new ItemStack(FCBetterThanWolves.fcItemHempFibers)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockCampfireUnlit), new Object[] {"XX", "XX", 'X', Item.stick});
        AddShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Item.stick), new Object[] {new ItemStack(Item.bow, 1, 32767)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockFurnaceBrickIdle), new Object[] {"XX", "XX", 'X', FCBetterThanWolves.fcBlockBrickLooseSlab});
        AddShapelessRecipe(new ItemStack(Item.coal), new Object[] {new ItemStack(FCBetterThanWolves.fcItemCoalDust), new ItemStack(FCBetterThanWolves.fcItemCoalDust)});
        AddShapelessRecipe(new ItemStack(Item.clay), new Object[] {new ItemStack(FCBetterThanWolves.fcItemPileClay), new ItemStack(FCBetterThanWolves.fcItemPileClay)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockLadder, 2), new Object[] {"#S#", "###", "#S#", '#', Item.stick, 'S', Item.silk});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockLadder, 2), new Object[] {"#S#", "###", "#S#", '#', Item.stick, 'S', FCBetterThanWolves.fcItemHempFibers});
        AddShapelessRecipe(new ItemStack(Item.arrow), new Object[] {new ItemStack(Item.feather), new ItemStack(Item.stick), new ItemStack(Item.silk), new ItemStack(Item.flint)});
        AddShapelessRecipe(new ItemStack(Item.arrow), new Object[] {new ItemStack(Item.feather), new ItemStack(Item.stick), new ItemStack(FCBetterThanWolves.fcItemHempFibers), new ItemStack(Item.flint)});
    }

    private static void AddToolRecipes()
    {
        AddStoneToolRecipes();
        AddRecipe(new ItemStack(Item.hoeIron), new Object[] {"X#", " #", " #", '#', Item.stick, 'X', Item.ingotIron});
        AddRecipe(new ItemStack(Item.hoeDiamond), new Object[] {"X#", " #", " #", '#', Item.stick, 'X', FCBetterThanWolves.fcItemIngotDiamond});
        AddRecipe(new ItemStack(Item.hoeGold), new Object[] {"X#", " #", " #", '#', Item.stick, 'X', Item.ingotGold});
    }

    private static void AddStoneToolRecipes()
    {
        AddShapelessRecipe(new ItemStack(Item.shovelStone), new Object[] {Item.stick, FCBetterThanWolves.fcItemStone, Item.silk});
        AddShapelessRecipe(new ItemStack(Item.shovelStone), new Object[] {Item.stick, FCBetterThanWolves.fcItemStone, FCBetterThanWolves.fcItemHempFibers});
        AddShapelessRecipe(new ItemStack(Item.axeStone), new Object[] {Item.stick, FCBetterThanWolves.fcItemStone, FCBetterThanWolves.fcItemStone, Item.silk});
        AddShapelessRecipe(new ItemStack(Item.axeStone), new Object[] {Item.stick, FCBetterThanWolves.fcItemStone, FCBetterThanWolves.fcItemStone, FCBetterThanWolves.fcItemHempFibers});
        AddRecipe(new ItemStack(Item.pickaxeStone), new Object[] {"XXX", " #S", " # ", '#', Item.stick, 'X', FCBetterThanWolves.fcItemStone, 'S', Item.silk});
        AddRecipe(new ItemStack(Item.pickaxeStone), new Object[] {"XXX", " #S", " # ", '#', Item.stick, 'X', FCBetterThanWolves.fcItemStone, 'S', FCBetterThanWolves.fcItemHempFibers});
    }

    private static void AddLooseStoneRecipes()
    {
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLoose), new Object[] {new ItemStack(FCBetterThanWolves.fcItemStone), new ItemStack(FCBetterThanWolves.fcItemStone), new ItemStack(FCBetterThanWolves.fcItemStone), new ItemStack(FCBetterThanWolves.fcItemStone), new ItemStack(FCBetterThanWolves.fcItemStone), new ItemStack(FCBetterThanWolves.fcItemStone), new ItemStack(FCBetterThanWolves.fcItemStone), new ItemStack(FCBetterThanWolves.fcItemStone)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLooseSlab), new Object[] {new ItemStack(FCBetterThanWolves.fcItemStone), new ItemStack(FCBetterThanWolves.fcItemStone), new ItemStack(FCBetterThanWolves.fcItemStone), new ItemStack(FCBetterThanWolves.fcItemStone)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLooseStairs), new Object[] {"#  ", "## ", "###", '#', new ItemStack(FCBetterThanWolves.fcItemStone)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLooseStairs, 8), new Object[] {"#  ", "## ", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLoose)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLooseStairs, 4), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLoose)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLooseStairs, 2), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLooseSlab)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLoose), new Object[] {"X", "X", 'X', FCBetterThanWolves.fcBlockCobblestoneLooseSlab});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLooseSlab, 4), new Object[] {"XX", 'X', FCBetterThanWolves.fcBlockCobblestoneLoose});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemStone, 8), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLoose)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemStone, 4), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLooseSlab)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemStone, 6), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockCobblestoneLooseStairs)});
    }

    private static void AddLooseBrickRecipes()
    {
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemBrickUnfired), new Object[] {new ItemStack(Item.clay)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockBrickLoose), new Object[] {new ItemStack(Item.brick), new ItemStack(Item.brick), new ItemStack(Item.brick), new ItemStack(Item.brick), new ItemStack(Item.brick), new ItemStack(Item.brick), new ItemStack(Item.brick), new ItemStack(Item.brick)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockBrickLooseSlab), new Object[] {new ItemStack(Item.brick), new ItemStack(Item.brick), new ItemStack(Item.brick), new ItemStack(Item.brick)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockBrickLooseStairs), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Item.brick)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockBrickLooseStairs, 8), new Object[] {"#  ", "## ", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockBrickLoose)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockBrickLooseStairs, 4), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockBrickLoose)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockBrickLooseStairs, 2), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockBrickLooseSlab)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockBrickLoose), new Object[] {"X", "X", 'X', FCBetterThanWolves.fcBlockBrickLooseSlab});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockBrickLooseSlab, 4), new Object[] {"XX", 'X', FCBetterThanWolves.fcBlockBrickLoose});
        AddShapelessRecipe(new ItemStack(Item.brick, 8), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockBrickLoose)});
        AddShapelessRecipe(new ItemStack(Item.brick, 4), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockBrickLooseSlab)});
        AddShapelessRecipe(new ItemStack(Item.brick, 6), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockBrickLooseStairs)});
    }

    private static void AddLooseStoneBrickRecipes()
    {
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLoose), new Object[] {new ItemStack(FCBetterThanWolves.fcItemStoneBrick), new ItemStack(FCBetterThanWolves.fcItemStoneBrick), new ItemStack(FCBetterThanWolves.fcItemStoneBrick), new ItemStack(FCBetterThanWolves.fcItemStoneBrick)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLooseSlab), new Object[] {new ItemStack(FCBetterThanWolves.fcItemStoneBrick), new ItemStack(FCBetterThanWolves.fcItemStoneBrick)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLooseStairs, 2), new Object[] {"#  ", "## ", "###", '#', new ItemStack(FCBetterThanWolves.fcItemStoneBrick)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLooseStairs, 8), new Object[] {"#  ", "## ", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLoose)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLooseStairs), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcItemStoneBrick)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLooseStairs, 4), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLoose)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLooseStairs, 2), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLooseSlab)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLoose), new Object[] {"X", "X", 'X', FCBetterThanWolves.fcBlockStoneBrickLooseSlab});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLooseSlab, 4), new Object[] {"XX", 'X', FCBetterThanWolves.fcBlockStoneBrickLoose});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 4), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLoose)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 2), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLooseSlab)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 3), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockStoneBrickLooseStairs)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemStone, 2), new Object[] {new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767), new ItemStack(FCBetterThanWolves.fcItemStoneBrick)});
        AddShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(FCBetterThanWolves.fcItemStoneBrick, 4), new Object[] {new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767), new ItemStack(Block.stone)});
    }

    private static void AddLooseNetherBrickRecipes()
    {
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemNetherBrickUnfired), new Object[] {new ItemStack(FCBetterThanWolves.fcItemNetherSludge)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLoose), new Object[] {new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemNetherBrick)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLooseSlab), new Object[] {new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemNetherBrick), new ItemStack(FCBetterThanWolves.fcItemNetherBrick)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLooseStairs), new Object[] {"#  ", "## ", "###", '#', new ItemStack(FCBetterThanWolves.fcItemNetherBrick)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLooseStairs, 8), new Object[] {"#  ", "## ", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLoose)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLooseStairs, 4), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLoose)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLooseStairs, 2), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLooseSlab)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLoose), new Object[] {"X", "X", 'X', FCBetterThanWolves.fcBlockNetherBrickLooseSlab});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLooseSlab, 4), new Object[] {"XX", 'X', FCBetterThanWolves.fcBlockNetherBrickLoose});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemNetherBrick, 8), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLoose)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemNetherBrick, 4), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLooseSlab)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemNetherBrick, 6), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockNetherBrickLooseStairs)});
    }

    private static void AddTorchRecipes()
    {
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockTorchNetherUnlit, 1), new Object[] {"#", "X", '#', FCBetterThanWolves.fcItemNethercoal, 'X', Item.stick});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockTorchFiniteUnlit), new Object[] {"#", "X", '#', new ItemStack(Item.coal, 1, 32767), 'X', Item.stick});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockTorchNetherBurning), new Object[] {new ItemStack(Block.torchWood)});
    }

    private static void AddWickerRecipes()
    {
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemWickerWeaving, 1, 299), new Object[] {"##", "##", '#', Item.reed});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockBasketWicker), new Object[] {"##", "##", '#', FCBetterThanWolves.fcItemWickerPiece});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemWickerPiece, 1), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockBasketWicker)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockHamper), new Object[] {"###", "#P#", "###", '#', FCBetterThanWolves.fcItemWickerPiece, 'P', Block.planks});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockWickerPane), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockGrate), new ItemStack(FCBetterThanWolves.fcItemWickerPiece)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockWicker), new Object[] {"##", "##", '#', FCBetterThanWolves.fcBlockWickerPane});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockWickerSlab), new Object[] {"##", '#', FCBetterThanWolves.fcBlockWickerPane});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockWickerSlab, 4), new Object[] {"##", '#', FCBetterThanWolves.fcBlockWicker});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockWickerPane, 4), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockWicker)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockWickerPane, 2), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockWickerSlab)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockWicker), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockWickerSlab), new ItemStack(FCBetterThanWolves.fcBlockWickerSlab)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockWicker), new Object[] {new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 0)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockWickerSlab), new Object[] {new ItemStack(FCBetterThanWolves.fcAestheticNonOpaque, 1, 5)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockWickerPane), new Object[] {new ItemStack(FCBetterThanWolves.fcItemWickerPaneOld)});
    }

    private static void AddWoodStairRecipes()
    {
        AddRecipe(new ItemStack(Block.stairsWoodOak, 6), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Block.planks, 1, 0)});
        AddRecipe(new ItemStack(Block.stairsWoodBirch, 6), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Block.planks, 1, 2)});
        AddRecipe(new ItemStack(Block.stairsWoodSpruce, 6), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Block.planks, 1, 1)});
        AddRecipe(new ItemStack(Block.stairsWoodJungle, 6), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Block.planks, 1, 3)});
    }

    private static void AddWoolAndKnittingRecipes()
    {
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemKnittingNeedles), new Object[] {new ItemStack(FCBetterThanWolves.fcItemChiselWood, 1, 0), new ItemStack(FCBetterThanWolves.fcItemChiselWood, 1, 0)});
        AddShapedRecipeWithCustomClass(FCRecipesArmorWool.class, new ItemStack(FCBetterThanWolves.fcItemArmorWoolHelm), new Object[] {"##", '#', new ItemStack(FCBetterThanWolves.fcItemWoolKnit, 1, 32767)});
        AddShapedRecipeWithCustomClass(FCRecipesArmorWool.class, new ItemStack(FCBetterThanWolves.fcItemArmorWoolChest), new Object[] {"##", "##", '#', new ItemStack(FCBetterThanWolves.fcItemWoolKnit, 1, 32767)});
        AddShapedRecipeWithCustomClass(FCRecipesArmorWool.class, new ItemStack(FCBetterThanWolves.fcItemArmorWoolLeggings), new Object[] {"##", "# ", '#', new ItemStack(FCBetterThanWolves.fcItemWoolKnit, 1, 32767)});
        AddShapedRecipeWithCustomClass(FCRecipesArmorWool.class, new ItemStack(FCBetterThanWolves.fcItemArmorWoolLeggings), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcItemWoolKnit, 1, 32767)});
        AddShapedRecipeWithCustomClass(FCRecipesWoolBlock.class, new ItemStack(Block.cloth), new Object[] {" # ", "#W#", " # ", '#', new ItemStack(FCBetterThanWolves.fcItemWool, 1, 32767), 'W', new ItemStack(FCBetterThanWolves.fcBlockWicker)});

        for (int var0 = 0; var0 < 16; ++var0)
        {
            AddRecipe(new ItemStack(FCBetterThanWolves.fcWoolSlab, 6, var0), new Object[] {"###", '#', new ItemStack(Block.cloth, 1, var0)});
            AddShapelessRecipe(new ItemStack(Block.cloth, 1, var0), new Object[] {new ItemStack(FCBetterThanWolves.fcWoolSlab, 1, var0), new ItemStack(FCBetterThanWolves.fcWoolSlab, 1, var0)});
        }
    }

    private static void AddSawDustRecipes()
    {
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemSawDust, 2), new Object[] {new ItemStack(FCBetterThanWolves.fcItemFireStarterSticks, 1, 32767)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemSawDust, 2), new Object[] {new ItemStack(FCBetterThanWolves.fcItemFireStarterBow, 1, 32767)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemSawDust, 2), new Object[] {new ItemStack(FCBetterThanWolves.fcItemKnittingNeedles, 1, 32767)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemSawDust, 4), new Object[] {new ItemStack(FCBetterThanWolves.fcItemKnitting, 1, 32767)});
    }

    private static void AddMeatCuringRecipes()
    {
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemMeatCured), new Object[] {new ItemStack(FCBetterThanWolves.fcItemMuttonRaw), new ItemStack(FCBetterThanWolves.fcItemNitre)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemMeatCured), new Object[] {new ItemStack(Item.chickenRaw), new ItemStack(FCBetterThanWolves.fcItemNitre)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemMeatCured), new Object[] {new ItemStack(Item.beefRaw), new ItemStack(FCBetterThanWolves.fcItemNitre)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemMeatCured), new Object[] {new ItemStack(Item.fishRaw), new ItemStack(FCBetterThanWolves.fcItemNitre)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemMeatCured), new Object[] {new ItemStack(Item.porkRaw), new ItemStack(FCBetterThanWolves.fcItemNitre)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemMeatCured), new Object[] {new ItemStack(FCBetterThanWolves.fcItemWolfRaw), new ItemStack(FCBetterThanWolves.fcItemNitre)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemMeatCured), new Object[] {new ItemStack(FCBetterThanWolves.fcItemRawMysteryMeat), new ItemStack(FCBetterThanWolves.fcItemNitre)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemMeatCured), new Object[] {new ItemStack(FCBetterThanWolves.fcItemBeastLiverRaw), new ItemStack(FCBetterThanWolves.fcItemNitre)});
    }

    private static void AddPaneRecipes()
    {
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockGrate, 1), new Object[] {"S#S", "###", "S#S", '#', new ItemStack(Item.stick), 'S', new ItemStack(Item.silk)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockGrate, 1), new Object[] {"S#S", "###", "S#S", '#', new ItemStack(Item.stick), 'S', new ItemStack(FCBetterThanWolves.fcItemHempFibers)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockSlats, 1), new Object[] {"##", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockGrate), new Object[] {new ItemStack(FCBetterThanWolves.fcItemGrateOld)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockSlats), new Object[] {new ItemStack(FCBetterThanWolves.fcItemSlatsOld)});
    }

    private static void AddSnowRecipes()
    {
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockSnowLooseSlab), new Object[] {new ItemStack(Item.snowball), new ItemStack(Item.snowball), new ItemStack(Item.snowball), new ItemStack(Item.snowball)});
        AddShapelessRecipe(new ItemStack(Item.snowball, 4), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockSnowLooseSlab)});
        AddShapelessRecipe(new ItemStack(Item.snowball, 4), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockSnowSolidSlab)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockSnowLoose), new Object[] {new ItemStack(Item.snowball), new ItemStack(Item.snowball), new ItemStack(Item.snowball), new ItemStack(Item.snowball), new ItemStack(Item.snowball), new ItemStack(Item.snowball), new ItemStack(Item.snowball), new ItemStack(Item.snowball)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockSnowLoose), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockSnowLooseSlab), new ItemStack(FCBetterThanWolves.fcBlockSnowLooseSlab)});
        AddShapelessRecipe(new ItemStack(Item.snowball, 8), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockSnowLoose)});
        AddShapelessRecipe(new ItemStack(Item.snowball, 8), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockSnowSolid)});
    }

    private static void AddChickenFeedRecipes()
    {
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemChickenFeed), new Object[] {new ItemStack(Item.dyePowder, 1, 15), new ItemStack(Item.seeds)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemChickenFeed), new Object[] {new ItemStack(Item.dyePowder, 1, 15), new ItemStack(FCBetterThanWolves.fcItemWheatSeeds)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemChickenFeed), new Object[] {new ItemStack(Item.dyePowder, 1, 15), new ItemStack(FCBetterThanWolves.fcItemHempSeeds)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemChickenFeed), new Object[] {new ItemStack(Item.dyePowder, 1, 15), new ItemStack(Item.pumpkinSeeds)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemChickenFeed), new Object[] {new ItemStack(Item.dyePowder, 1, 15), new ItemStack(Item.melonSeeds)});
    }

    private static void AddFishingRecipes()
    {
        AddShapelessRecipe(new ItemStack(Item.fishingRod), new Object[] {new ItemStack(Item.stick), new ItemStack(Item.silk), new ItemStack(Item.silk), new ItemStack(FCBetterThanWolves.fcItemNuggetIron)});
        AddShapelessRecipe(new ItemStack(Item.fishingRod), new Object[] {new ItemStack(Item.stick), new ItemStack(Item.silk), new ItemStack(Item.silk), new ItemStack(FCBetterThanWolves.fcItemFishHookBone)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemCarvingBone, 1, 599), new Object[] {new ItemStack(Item.bone)});
    }

    private static void AddDirtRecipes()
    {
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockDirtLooseSlab, 4), new Object[] {"##", '#', new ItemStack(FCBetterThanWolves.fcBlockDirtLoose)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockDirtLooseSlab, 1), new Object[] {new ItemStack(FCBetterThanWolves.fcItemPileDirt), new ItemStack(FCBetterThanWolves.fcItemPileDirt), new ItemStack(FCBetterThanWolves.fcItemPileDirt), new ItemStack(FCBetterThanWolves.fcItemPileDirt)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockDirtLoose, 1), new Object[] {new ItemStack(FCBetterThanWolves.fcItemPileDirt), new ItemStack(FCBetterThanWolves.fcItemPileDirt), new ItemStack(FCBetterThanWolves.fcItemPileDirt), new ItemStack(FCBetterThanWolves.fcItemPileDirt), new ItemStack(FCBetterThanWolves.fcItemPileDirt), new ItemStack(FCBetterThanWolves.fcItemPileDirt), new ItemStack(FCBetterThanWolves.fcItemPileDirt), new ItemStack(FCBetterThanWolves.fcItemPileDirt)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockDirtLoose), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockDirtLooseSlab), new ItemStack(FCBetterThanWolves.fcBlockDirtLooseSlab)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockDirtSlab, 4), new Object[] {"##", '#', new ItemStack(Block.dirt)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockDirtSlab, 4, 3), new Object[] {"EE", 'E', new ItemStack(FCBetterThanWolves.fcBlockAestheticOpaqueEarth, 1, 6)});
        AddShapelessRecipe(new ItemStack(Block.dirt), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockDirtSlab), new ItemStack(FCBetterThanWolves.fcBlockDirtSlab)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockDirtLoose, 2), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockAestheticOpaqueEarth, 1, 6)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockAestheticOpaqueEarth, 1, 6), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockDirtSlab, 1, 3), new ItemStack(FCBetterThanWolves.fcBlockDirtSlab, 1, 3)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockDirtLoose, 1), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockDirtSlab, 1, 3)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemPileDirt, 8), new Object[] {new ItemStack(Block.dirt, 1)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemPileDirt, 8), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockDirtLoose, 1)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemPileDirt, 4), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockDirtSlab)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemPileDirt, 4), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockDirtLooseSlab, 1)});
    }

    private static void AddGravelRecipes()
    {
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockSlabSandAndGravel, 1, 0), new Object[] {new ItemStack(FCBetterThanWolves.fcItemPileGravel), new ItemStack(FCBetterThanWolves.fcItemPileGravel), new ItemStack(FCBetterThanWolves.fcItemPileGravel), new ItemStack(FCBetterThanWolves.fcItemPileGravel)});
        AddShapelessRecipe(new ItemStack(Block.gravel), new Object[] {new ItemStack(FCBetterThanWolves.fcItemPileGravel), new ItemStack(FCBetterThanWolves.fcItemPileGravel), new ItemStack(FCBetterThanWolves.fcItemPileGravel), new ItemStack(FCBetterThanWolves.fcItemPileGravel), new ItemStack(FCBetterThanWolves.fcItemPileGravel), new ItemStack(FCBetterThanWolves.fcItemPileGravel), new ItemStack(FCBetterThanWolves.fcItemPileGravel), new ItemStack(FCBetterThanWolves.fcItemPileGravel)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemPileGravel, 8), new Object[] {new ItemStack(Block.gravel)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemPileGravel, 4), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockSlabSandAndGravel, 1, 0)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockSlabSandAndGravel, 4, 0), new Object[] {"##", '#', new ItemStack(Block.gravel)});
        AddShapelessRecipe(new ItemStack(Block.gravel), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockSlabSandAndGravel, 1, 0), new ItemStack(FCBetterThanWolves.fcBlockSlabSandAndGravel, 1, 0)});
    }

    private static void AddSandRecipes()
    {
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockSlabSandAndGravel, 1, 1), new Object[] {new ItemStack(FCBetterThanWolves.fcItemPileSand), new ItemStack(FCBetterThanWolves.fcItemPileSand), new ItemStack(FCBetterThanWolves.fcItemPileSand), new ItemStack(FCBetterThanWolves.fcItemPileSand)});
        AddShapelessRecipe(new ItemStack(Block.sand), new Object[] {new ItemStack(FCBetterThanWolves.fcItemPileSand), new ItemStack(FCBetterThanWolves.fcItemPileSand), new ItemStack(FCBetterThanWolves.fcItemPileSand), new ItemStack(FCBetterThanWolves.fcItemPileSand), new ItemStack(FCBetterThanWolves.fcItemPileSand), new ItemStack(FCBetterThanWolves.fcItemPileSand), new ItemStack(FCBetterThanWolves.fcItemPileSand), new ItemStack(FCBetterThanWolves.fcItemPileSand)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemPileSand, 4), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockSlabSandAndGravel, 1, 1)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemPileSand, 8), new Object[] {new ItemStack(Block.sand)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockSlabSandAndGravel, 4, 1), new Object[] {"##", '#', new ItemStack(Block.sand)});
        AddShapelessRecipe(new ItemStack(Block.sand), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockSlabSandAndGravel, 1, 1), new ItemStack(FCBetterThanWolves.fcBlockSlabSandAndGravel, 1, 1)});
    }

    private static void AddMechanicalRecipes()
    {
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockAxle), new Object[] {"#X#", '#', Block.planks, 'X', FCBetterThanWolves.fcItemRope});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockAxle), new Object[] {"#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767), 'X', FCBetterThanWolves.fcItemRope});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockGearBox), new Object[] {"#X#", "XYX", "#X#", '#', Block.planks, 'X', FCBetterThanWolves.fcItemGear, 'Y', FCBetterThanWolves.fcBlockAxle});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockGearBox), new Object[] {"#X#", "XYX", "#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'X', FCBetterThanWolves.fcItemGear, 'Y', FCBetterThanWolves.fcBlockAxle});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockRedstoneClutch), new Object[] {"#X#", "XYX", "#X#", '#', Block.planks, 'X', FCBetterThanWolves.fcItemGear, 'Y', FCBetterThanWolves.fcItemRedstoneLatch});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockRedstoneClutch), new Object[] {"#X#", "XYX", "#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'X', FCBetterThanWolves.fcItemGear, 'Y', FCBetterThanWolves.fcItemRedstoneLatch});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcHandCrank), new Object[] {"  Y", " Y ", "#X#", '#', FCBetterThanWolves.fcItemStoneBrick, 'X', FCBetterThanWolves.fcItemGear, 'Y', Item.stick});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcMillStone), new Object[] {"YYY", "YYY", "YXY", 'X', FCBetterThanWolves.fcItemGear, 'Y', FCBetterThanWolves.fcItemStoneBrick});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcTurntable), new Object[] {"###", "ZXZ", "ZYZ", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'X', Item.pocketSundial, 'Y', FCBetterThanWolves.fcItemGear, 'Z', FCBetterThanWolves.fcItemStoneBrick});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBellows), new Object[] {"###", "XXX", "YZY", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'X', FCBetterThanWolves.fcItemTannedLeather, 'Y', FCBetterThanWolves.fcItemGear, 'Z', FCBetterThanWolves.fcItemBelt});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBellows), new Object[] {"###", "XXX", "YZY", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'X', FCBetterThanWolves.fcItemTannedLeatherCut, 'Y', FCBetterThanWolves.fcItemGear, 'Z', FCBetterThanWolves.fcItemBelt});
    }

    private static void AddOreRecipes()
    {
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemChunkIronOre), new Object[] {new ItemStack(FCBetterThanWolves.fcItemPileIronOre), new ItemStack(FCBetterThanWolves.fcItemPileIronOre)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemChunkIronOre), new Object[] {new ItemStack(Block.oreIron)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockChunkOreStorageIron), new Object[] {"###", "###", "###", '#', FCBetterThanWolves.fcItemChunkIronOre});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemChunkIronOre, 9), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockChunkOreStorageIron)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemChunkGoldOre), new Object[] {new ItemStack(FCBetterThanWolves.fcItemPileGoldOre), new ItemStack(FCBetterThanWolves.fcItemPileGoldOre)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemChunkGoldOre), new Object[] {new ItemStack(Block.oreGold)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockChunkOreStorageGold), new Object[] {"###", "###", "###", '#', FCBetterThanWolves.fcItemChunkGoldOre});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemChunkGoldOre, 9), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockChunkOreStorageGold)});
    }

    private static void AddPastryRecipes()
    {
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemBreadDough), new Object[] {new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(Item.bucketWater)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemPastryUncookedCookies, 1), new Object[] {new ItemStack(FCBetterThanWolves.fcItemChocolate), new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(FCBetterThanWolves.fcItemFlour)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemPastryUncookedPumpkinPie, 1), new Object[] {new ItemStack(Item.sugar), new ItemStack(FCBetterThanWolves.fcBlockPumpkinFresh), new ItemStack(FCBetterThanWolves.fcItemRawEgg), new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(FCBetterThanWolves.fcItemFlour)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemPastryUncookedCake, 1), new Object[] {new ItemStack(Item.sugar), new ItemStack(Item.sugar), new ItemStack(Item.sugar), new ItemStack(Item.bucketMilk), new ItemStack(Item.bucketMilk), new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(FCBetterThanWolves.fcItemRawEgg)});
    }

    private static void AddBlockRecipes()
    {
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockAestheticOpaqueEarth, 1, 7), new Object[] {"###", "###", "###", '#', FCBetterThanWolves.fcItemDung});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 3), new Object[] {"###", "###", "###", '#', FCBetterThanWolves.fcItemConcentratedHellfire});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 4), new Object[] {"###", "###", "###", '#', FCBetterThanWolves.fcItemPadding});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 5), new Object[] {"###", "###", "###", '#', FCBetterThanWolves.fcItemSoap});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 6), new Object[] {"###", "###", "###", '#', FCBetterThanWolves.fcItemRope});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 7), new Object[] {"###", "###", "###", '#', Item.flint});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 14), new Object[] {"###", "###", "###", '#', Item.enderPearl});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockMiningCharge, 2), new Object[] {"XYX", "###", "###", '#', FCBetterThanWolves.fcItemDynamite, 'X', FCBetterThanWolves.fcItemRope, 'Y', Item.slimeBall});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcAestheticVegetation, 1, 0), new Object[] {"###", '#', Block.vine});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcAnvil, 1), new Object[] {new ItemStack(Item.netherStar), new ItemStack(FCBetterThanWolves.fcItemSoulFlux), new ItemStack(FCBetterThanWolves.fcBlockSoulforgeDormant)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcLightBulbOff, 1), new Object[] {" # ", "#X#", " Y ", '#', Block.thinGlass, 'X', FCBetterThanWolves.fcItemFilament, 'Y', Item.redstone});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBBQ), new Object[] {"XXX", "#Z#", "#Y#", '#', FCBetterThanWolves.fcItemStoneBrick, 'X', FCBetterThanWolves.fcItemConcentratedHellfire, 'Y', Item.redstone, 'Z', FCBetterThanWolves.fcItemElement});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcHopper), new Object[] {"# #", "XYX", " Z ", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'X', FCBetterThanWolves.fcItemGear, 'Y', Block.pressurePlatePlanks, 'Z', new ItemStack(FCBetterThanWolves.fcBlockWoodCornerItemStubID, 1, 32767)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcSaw), new Object[] {"YYY", "XZX", "#X#", '#', Block.planks, 'X', FCBetterThanWolves.fcItemGear, 'Y', Item.ingotIron, 'Z', FCBetterThanWolves.fcItemBelt});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcSaw), new Object[] {"YYY", "XZX", "#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'X', FCBetterThanWolves.fcItemGear, 'Y', Item.ingotIron, 'Z', FCBetterThanWolves.fcItemBelt});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcPlatform), new Object[] {"#X#", " # ", "#X#", '#', Block.planks, 'X', FCBetterThanWolves.fcBlockWickerPane});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcPlatform), new Object[] {"X#X", " X ", "X#X", '#', FCBetterThanWolves.fcBlockWickerPane, 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcPulley), new Object[] {"#Y#", "XZX", "#Y#", '#', Block.planks, 'X', FCBetterThanWolves.fcItemGear, 'Y', Item.ingotIron, 'Z', FCBetterThanWolves.fcItemRedstoneLatch});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcPulley), new Object[] {"#Y#", "XZX", "#Y#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'X', FCBetterThanWolves.fcItemGear, 'Y', Item.ingotIron, 'Z', FCBetterThanWolves.fcItemRedstoneLatch});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcCauldron), new Object[] {"#Y#", "#X#", "###", '#', Item.ingotIron, 'X', Item.bucketWater, 'Y', Item.bone});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcDetectorRailWood, 6), new Object[] {"X X", "X#X", "XRX", 'X', Item.ingotIron, 'R', Item.redstone, '#', Block.pressurePlatePlanks});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockDetectorRailSoulforgedSteel, 6), new Object[] {"X X", "X#X", "XRX", 'X', Item.ingotIron, 'R', Item.redstone, '#', FCBetterThanWolves.fcBlockPressurePlateSoulforgedSteel});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockPlanterSoil), new Object[] {new ItemStack(FCBetterThanWolves.fcPlanter), new ItemStack(FCBetterThanWolves.fcBlockDirtLoose)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockPlanterSoil), new Object[] {new ItemStack(FCBetterThanWolves.fcPlanter, 1, 1)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockPlanterSoil), new Object[] {new ItemStack(FCBetterThanWolves.fcPlanter, 1, 2)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcPlanter, 1, 8), new Object[] {new ItemStack(FCBetterThanWolves.fcPlanter), new ItemStack(Block.slowSand)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockScrewPump), new Object[] {"XGX", "WSW", "WgW", 'W', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'g', FCBetterThanWolves.fcItemGear, 'S', FCBetterThanWolves.fcItemScrew, 'G', FCBetterThanWolves.fcBlockGrate, 'X', FCBetterThanWolves.fcItemGlue});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcLens), new Object[] {"GDG", "G G", "G#G", '#', Block.glass, 'G', Item.ingotGold, 'D', Item.diamond});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcLens), new Object[] {"G#G", "G G", "GDG", '#', Block.glass, 'G', Item.ingotGold, 'D', Item.diamond});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 2, 11), new Object[] {"###", "#X#", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767), 'X', FCBetterThanWolves.fcItemGlue});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockWhiteStoneStairs, 6, 0), new Object[] {"#  ", "## ", "###", '#', new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 9)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockWhiteStoneStairs, 6, 8), new Object[] {"#  ", "## ", "###", '#', new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 10)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcAestheticNonOpaque, 6, 10), new Object[] {"###", '#', new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 10)});
        AddShapelessRecipe(new ItemStack(Item.skull.itemID, 1, 5), new Object[] {new ItemStack(FCBetterThanWolves.fcItemSoulUrn), new ItemStack(FCBetterThanWolves.fcItemSoulFlux), new ItemStack(Item.skull.itemID, 1, 1)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockShovel), new Object[] {"#  ", "## ", "###", '#', Item.ingotIron});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockSpikeIron), new Object[] {"n", "n", "I", 'n', FCBetterThanWolves.fcItemNuggetIron, 'I', Item.ingotIron});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockLightningRod), new Object[] {"n", "n", "I", 'n', Item.goldNugget, 'I', Item.ingotGold});
    }

    private static void AddItemRecipes()
    {
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemGear, 2), new Object[] {" X ", "X#X", " X ", '#', Block.planks, 'X', Item.stick});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemRope, 1), new Object[] {"##", "##", "##", '#', FCBetterThanWolves.fcItemHempFibers});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcAnchor), new Object[] {" X ", "###", '#', FCBetterThanWolves.fcItemStoneBrick, 'X', Item.ingotIron});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemWaterWheel), new Object[] {"###", "# #", "###", '#', FCBetterThanWolves.fcItemWoodBlade});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemWindMillBlade), new Object[] {"###", "###", "XXX", '#', FCBetterThanWolves.fcItemHempCloth, 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemWindMillBlade), new Object[] {"###", "###", "XXX", '#', FCBetterThanWolves.fcItemHempCloth, 'X', Block.planks});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemWindMill), new Object[] {" # ", "# #", " # ", '#', FCBetterThanWolves.fcItemWindMillBlade});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemHempCloth, 1), new Object[] {"###", "###", "###", '#', FCBetterThanWolves.fcItemHempFibers});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemDung, 9), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockAestheticOpaqueEarth, 1, 7)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemConcentratedHellfire, 9), new Object[] {new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 3)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemPadding, 9), new Object[] {new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 4)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemSoap, 9), new Object[] {new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 5)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemRope, 9), new Object[] {new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 6)});
        AddShapelessRecipe(new ItemStack(Item.flint, 9), new Object[] {new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 7)});
        AddShapelessRecipe(new ItemStack(Item.enderPearl, 9), new Object[] {new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 14)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemBelt, 1), new Object[] {" # ", "# #", " # ", '#', FCBetterThanWolves.fcItemStrap});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemWoodBlade, 1), new Object[] {"#  ", "#X#", "#  ", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'X', FCBetterThanWolves.fcItemGlue});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemHaft, 1), new Object[] {"Y", "X", "#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767), 'X', FCBetterThanWolves.fcItemGlue, 'Y', FCBetterThanWolves.fcItemStrap});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemCompositeBow, 1), new Object[] {"X#Y", "ZX#", "X#Y", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767), 'X', Item.bone, 'Y', FCBetterThanWolves.fcItemGlue, 'Z', Item.silk});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemBroadheadArrow), new Object[] {new ItemStack(Item.feather), new ItemStack(Item.stick), new ItemStack(Item.silk), new ItemStack(FCBetterThanWolves.fcItemBroadheadArrowhead)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemBroadheadArrow), new Object[] {new ItemStack(Item.feather), new ItemStack(Item.stick), new ItemStack(FCBetterThanWolves.fcItemHempFibers), new ItemStack(FCBetterThanWolves.fcItemBroadheadArrowhead)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorGimpHelm), new Object[] {"###", "#I#", '#', FCBetterThanWolves.fcItemTannedLeather, 'I', Item.ingotIron});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorGimpChest), new Object[] {"# #", "I#I", "###", '#', FCBetterThanWolves.fcItemTannedLeather, 'I', Item.ingotIron});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorGimpLeggings), new Object[] {"#I#", "# #", "# #", '#', FCBetterThanWolves.fcItemTannedLeather, 'I', Item.ingotIron});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorGimpBoots), new Object[] {"# #", "I I", '#', FCBetterThanWolves.fcItemTannedLeather, 'I', Item.ingotIron});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorGimpHelm), new Object[] {"###", "#I#", '#', FCBetterThanWolves.fcItemTannedLeatherCut, 'I', Item.ingotIron});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorGimpChest), new Object[] {"# #", "I#I", "###", '#', FCBetterThanWolves.fcItemTannedLeatherCut, 'I', Item.ingotIron});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorGimpLeggings), new Object[] {"#I#", "# #", "# #", '#', FCBetterThanWolves.fcItemTannedLeatherCut, 'I', Item.ingotIron});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorGimpBoots), new Object[] {"# #", "I I", '#', FCBetterThanWolves.fcItemTannedLeatherCut, 'I', Item.ingotIron});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemPadding), new Object[] {"C", "W", "C", 'C', FCBetterThanWolves.fcItemHempCloth, 'W', new ItemStack(FCBetterThanWolves.fcItemWool, 1, 32767)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemDynamite), new Object[] {"PF", "PN", "PS", 'P', Item.paper, 'F', FCBetterThanWolves.fcItemFuse, 'N', FCBetterThanWolves.fcItemBlastingOil, 'S', FCBetterThanWolves.fcItemSawDust});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemDynamite), new Object[] {"PF", "PN", "PS", 'P', Item.paper, 'F', FCBetterThanWolves.fcItemFuse, 'N', FCBetterThanWolves.fcItemBlastingOil, 'S', FCBetterThanWolves.fcItemSoulDust});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemBreedingHarness), new Object[] {"SLS", "LLL", "SLS", 'S', FCBetterThanWolves.fcItemStrap, 'L', FCBetterThanWolves.fcItemTannedLeather});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemBreedingHarness), new Object[] {"SLS", "LLL", "SLS", 'S', FCBetterThanWolves.fcItemStrap, 'L', FCBetterThanWolves.fcItemTannedLeatherCut});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemCandle, 2, 15), new Object[] {"H", "T", "T", 'H', FCBetterThanWolves.fcItemHempFibers, 'T', FCBetterThanWolves.fcItemTallow});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemScrew), new Object[] {"## ", " ##", "## ", '#', new ItemStack(Item.ingotIron)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemOcularOfEnder, 1, 0), new Object[] {"ggg", "gEg", "ggg", 'g', Item.goldNugget, 'E', Item.enderPearl});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemEnderSpectacles), new Object[] {"OSO", 'S', FCBetterThanWolves.fcItemStrap, 'O', FCBetterThanWolves.fcItemOcularOfEnder});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemStake, 2), new Object[] {"S", "M", 'M', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767), 'S', new ItemStack(Item.silk)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemWindMillVertical), new Object[] {"SSS", "S S", "SSS", 'S', FCBetterThanWolves.fcItemWindMillBlade});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemTastySandwich, 2), new Object[] {new ItemStack(Item.bread), new ItemStack(Item.beefCooked)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemTastySandwich, 2), new Object[] {new ItemStack(Item.bread), new ItemStack(Item.porkCooked)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemTastySandwich, 2), new Object[] {new ItemStack(Item.bread), new ItemStack(Item.chickenCooked)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemTastySandwich, 2), new Object[] {new ItemStack(Item.bread), new ItemStack(Item.fishCooked)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemTastySandwich, 2), new Object[] {new ItemStack(Item.bread), new ItemStack(FCBetterThanWolves.fcItemWolfCooked)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemTastySandwich, 2), new Object[] {new ItemStack(Item.bread), new ItemStack(FCBetterThanWolves.fcItemMuttonCooked)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemTastySandwich, 2), new Object[] {new ItemStack(Item.bread), new ItemStack(FCBetterThanWolves.fcItemCookedMysteryMeat)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemSteakAndPotatoes, 2), new Object[] {new ItemStack(Item.bakedPotato), new ItemStack(Item.beefCooked)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemSteakAndPotatoes, 2), new Object[] {new ItemStack(FCBetterThanWolves.fcItemBoiledPotato), new ItemStack(Item.beefCooked)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemHamAndEggs, 2), new Object[] {new ItemStack(FCBetterThanWolves.fcItemHardBoiledEgg), new ItemStack(Item.porkCooked)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemHamAndEggs, 2), new Object[] {new ItemStack(FCBetterThanWolves.fcItemFriedEgg), new ItemStack(Item.porkCooked)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemSteakDinner, 3), new Object[] {new ItemStack(FCBetterThanWolves.fcItemBoiledPotato), new ItemStack(FCBetterThanWolves.fcItemCookedCarrot), new ItemStack(Item.beefCooked)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemSteakDinner, 3), new Object[] {new ItemStack(Item.bakedPotato), new ItemStack(FCBetterThanWolves.fcItemCookedCarrot), new ItemStack(Item.beefCooked)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemPorkDinner, 3), new Object[] {new ItemStack(FCBetterThanWolves.fcItemBoiledPotato), new ItemStack(FCBetterThanWolves.fcItemCookedCarrot), new ItemStack(Item.porkCooked)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemPorkDinner, 3), new Object[] {new ItemStack(Item.bakedPotato), new ItemStack(FCBetterThanWolves.fcItemCookedCarrot), new ItemStack(Item.porkCooked)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemWolfDinner, 3), new Object[] {new ItemStack(FCBetterThanWolves.fcItemBoiledPotato), new ItemStack(FCBetterThanWolves.fcItemCookedCarrot), new ItemStack(FCBetterThanWolves.fcItemWolfCooked)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemWolfDinner, 3), new Object[] {new ItemStack(Item.bakedPotato), new ItemStack(FCBetterThanWolves.fcItemCookedCarrot), new ItemStack(FCBetterThanWolves.fcItemWolfCooked)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemRawKebab, 3), new Object[] {new ItemStack(FCBetterThanWolves.fcItemMushroomBrown), new ItemStack(Item.carrot), new ItemStack(FCBetterThanWolves.fcItemMuttonRaw), new ItemStack(Item.stick)});
        AddShapelessRecipeWithSecondaryOutputIndicator(new ItemStack(Block.pumpkin), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockPumpkinFresh)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemRawMushroomOmelet, 2), new Object[] {new ItemStack(FCBetterThanWolves.fcItemMushroomBrown), new ItemStack(FCBetterThanWolves.fcItemMushroomBrown), new ItemStack(FCBetterThanWolves.fcItemMushroomBrown), new ItemStack(FCBetterThanWolves.fcItemRawEgg)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemRawScrambledEggs, 2), new Object[] {new ItemStack(FCBetterThanWolves.fcItemRawEgg), new ItemStack(Item.bucketMilk)});
        AddRecipe(new ItemStack(Item.helmetChain), new Object[] {"###", "# #", '#', FCBetterThanWolves.fcItemMail});
        AddRecipe(new ItemStack(Item.plateChain), new Object[] {"# #", "###", "###", '#', FCBetterThanWolves.fcItemMail});
        AddRecipe(new ItemStack(Item.legsChain), new Object[] {"###", "# #", "# #", '#', FCBetterThanWolves.fcItemMail});
        AddRecipe(new ItemStack(Item.bootsChain), new Object[] {"# #", "# #", '#', FCBetterThanWolves.fcItemMail});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorPaddedHelm), new Object[] {"###", "# #", '#', FCBetterThanWolves.fcItemPadding});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorPaddedChest), new Object[] {"# #", "###", "###", '#', FCBetterThanWolves.fcItemPadding});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorPaddedLeggings), new Object[] {"###", "# #", "# #", '#', FCBetterThanWolves.fcItemPadding});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorTannedHelm), new Object[] {"###", "# #", '#', FCBetterThanWolves.fcItemTannedLeather});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorTannedChest), new Object[] {"# #", "###", "###", '#', FCBetterThanWolves.fcItemTannedLeather});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorTannedLeggings), new Object[] {"###", "# #", "# #", '#', FCBetterThanWolves.fcItemTannedLeather});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorTannedBoots), new Object[] {"# #", "# #", '#', FCBetterThanWolves.fcItemTannedLeather});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorTannedHelm), new Object[] {"###", "# #", '#', FCBetterThanWolves.fcItemTannedLeatherCut});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorTannedChest), new Object[] {"# #", "###", "###", '#', FCBetterThanWolves.fcItemTannedLeatherCut});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorTannedLeggings), new Object[] {"###", "# #", "# #", '#', FCBetterThanWolves.fcItemTannedLeatherCut});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorTannedBoots), new Object[] {"# #", "# #", '#', FCBetterThanWolves.fcItemTannedLeatherCut});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemIngotDiamond), new Object[] {new ItemStack(Item.ingotIron), new ItemStack(Item.diamond), new ItemStack(FCBetterThanWolves.fcItemCreeperOysters)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemLeatherCut, 2), new Object[] {new ItemStack(Item.shears, 1, 32767), new ItemStack(Item.leather)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut, 2), new Object[] {new ItemStack(Item.shears, 1, 32767), new ItemStack(FCBetterThanWolves.fcItemTannedLeather)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemScouredLeatherCut, 2), new Object[] {new ItemStack(Item.shears, 1, 32767), new ItemStack(FCBetterThanWolves.fcItemScouredLeather)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemStrap, 4), new Object[] {new ItemStack(Item.shears, 1, 32767), new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemGoldenDung), new Object[] {"ggg", "gDg", "ggg", 'D', FCBetterThanWolves.fcItemDung, 'g', Item.goldNugget});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemRedstoneLatch), new Object[] {"ggg", " r ", 'g', Item.goldNugget, 'r', Item.redstone});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemBucketMilkChocolate), new Object[] {new ItemStack(Item.bucketMilk), new ItemStack(Item.dyePowder, 1, 3)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemStumpRemover, 2), new Object[] {new ItemStack(FCBetterThanWolves.fcItemCreeperOysters), new ItemStack(FCBetterThanWolves.fcItemMushroomRed), new ItemStack(Item.rottenFlesh)});
    }

    private static void AddDyeRecipes()
    {
        int var0;

        for (var0 = 0; var0 < 15; ++var0)
        {
            AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemCandle, 1, var0), new Object[] {new ItemStack(Item.dyePowder, 1, var0), new ItemStack(FCBetterThanWolves.fcItemCandle, 1, 15)});
            AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemWool, 1, var0), new Object[] {new ItemStack(Item.dyePowder, 1, var0), new ItemStack(FCBetterThanWolves.fcItemWool, 1, 15)});
        }

        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemCandle, 1, 3), new Object[] {new ItemStack(FCBetterThanWolves.fcItemDung), new ItemStack(FCBetterThanWolves.fcItemCandle, 1, 15)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemWool, 1, 3), new Object[] {new ItemStack(FCBetterThanWolves.fcItemDung), new ItemStack(FCBetterThanWolves.fcItemWool, 1, 15)});

        for (var0 = 0; var0 < 15; ++var0)
        {
            AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcVase, 1, BlockCloth.getBlockFromDye(var0)), new Object[] {new ItemStack(Item.dyePowder, 1, var0), new ItemStack(Item.itemsList[FCBetterThanWolves.fcVase.blockID], 1, 0)});
            AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcWoolSlab, 1, BlockCloth.getBlockFromDye(var0)), new Object[] {new ItemStack(Item.dyePowder, 1, var0), new ItemStack(Item.itemsList[FCBetterThanWolves.fcWoolSlab.blockID], 1, 0)});
        }

        AddShapelessRecipe(new ItemStack(Block.cloth, 1, 12), new Object[] {new ItemStack(FCBetterThanWolves.fcItemDung), new ItemStack(Item.itemsList[Block.cloth.blockID], 1, 0)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcVase, 1, 12), new Object[] {new ItemStack(FCBetterThanWolves.fcItemDung), new ItemStack(Item.itemsList[FCBetterThanWolves.fcVase.blockID], 1, 0)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcWoolSlab, 1, 12), new Object[] {new ItemStack(FCBetterThanWolves.fcItemDung), new ItemStack(Item.itemsList[FCBetterThanWolves.fcWoolSlab.blockID], 1, 0)});
    }

    private static void AddAlternateVanillaRecipes()
    {
        AddRecipe(new ItemStack(Block.pistonBase, 1), new Object[] {"#I#", "XYX", "XZX", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'I', Item.ingotIron, 'X', FCBetterThanWolves.fcItemStoneBrick, 'Y', FCBetterThanWolves.fcItemSoulUrn, 'Z', FCBetterThanWolves.fcItemRedstoneLatch});
        AddRecipe(new ItemStack(Block.fenceGate, 1), new Object[] {"#X#", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767)});
        AddRecipe(new ItemStack(Block.stairsWoodOak, 1), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 0)});
        AddRecipe(new ItemStack(Block.stairsWoodSpruce, 1), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 1)});
        AddRecipe(new ItemStack(Block.stairsWoodBirch, 1), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 2)});
        AddRecipe(new ItemStack(Block.stairsWoodJungle, 1), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 3)});
        AddRecipe(new ItemStack(Block.stairsStoneBrick), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockStoneBrickMouldingAndDecorative)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockWhiteStoneStairs), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockWhiteStoneMouldingAndDecorative)});
        AddRecipe(new ItemStack(Block.stairsNetherBrick), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockNetherBrickMouldingAndDecorative)});
        AddRecipe(new ItemStack(Block.stairsBrick), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockBrickMouldingAndDecorative)});
        AddRecipe(new ItemStack(Block.stairsSandStone), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockSandstoneMouldingAndDecorative)});
        AddRecipe(new ItemStack(Block.stairsNetherQuartz), new Object[] {"# ", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockMouldingAndDecorativeBlackStone)});
        AddRecipe(new ItemStack(Item.sign, 3), new Object[] {"#", "X", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767)});
        AddRecipe(new ItemStack(Item.doorWood, 1), new Object[] {"##", "##", "##", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767)});
        AddRecipe(new ItemStack(Block.trapdoor, 1), new Object[] {"WW#", "WW#", '#', Item.stick, 'W', Block.planks});
        AddRecipe(new ItemStack(Block.trapdoor, 2), new Object[] {"WW#", "WW#", '#', Item.stick, 'W', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767)});
        AddRecipe(new ItemStack(Item.boat, 1), new Object[] {"# #", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767)});
        AddRecipe(new ItemStack(Block.bookShelf), new Object[] {"###", "XXX", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'X', Item.book});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockChest), new Object[] {"###", "# #", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767)});
        AddRecipe(new ItemStack(Item.minecartCrate, 1), new Object[] {"A", "B", 'A', FCBetterThanWolves.fcBlockChest, 'B', Item.minecartEmpty});
        AddRecipe(new ItemStack(Item.redstoneRepeater, 1), new Object[] {"#X#", "III", '#', Block.torchRedstoneActive, 'X', Item.pocketSundial, 'I', FCBetterThanWolves.fcItemStoneBrick});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcInfernalEnchanter), new Object[] {"CBC", "SES", "SSS", 'S', FCBetterThanWolves.fcItemSteel, 'C', new ItemStack(FCBetterThanWolves.fcItemCandle, 1, 0), 'E', Block.enchantmentTable, 'B', Item.bone});
        AddShapelessRecipe(new ItemStack(Item.stick), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767)});
        AddRecipe(new ItemStack(Block.jukebox, 1), new Object[] {"###", "#X#", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'X', Item.diamond});
        AddRecipe(new ItemStack(Block.music, 1), new Object[] {"###", "#X#", "###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'X', FCBetterThanWolves.fcItemRedstoneLatch});
        AddRecipe(new ItemStack(Block.tnt, 1), new Object[] {"GFG", "GBG", "GGG", 'B', new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 11), 'G', Item.gunpowder, 'F', FCBetterThanWolves.fcItemFuse});
        AddShapelessRecipe(new ItemStack(Item.gunpowder), new Object[] {new ItemStack(FCBetterThanWolves.fcItemNitre), new ItemStack(FCBetterThanWolves.fcItemBrimstone), new ItemStack(FCBetterThanWolves.fcItemCoalDust)});
        AddRecipe(new ItemStack(Block.anvil, 1), new Object[] {"iii", " i ", "iii", 'i', Item.ingotIron});
        AddAnvilRecipe(new ItemStack(Block.stoneBrick, 12, 3), new Object[] {"####", "#  #", "#  #", "####", '#', Block.stoneBrick});
        AddShapelessRecipe(new ItemStack(Item.melon, 5), new Object[] {new ItemStack(Block.melon)});
        AddRecipe(new ItemStack(Item.bowlEmpty, 6), new Object[] {"# #", " # ", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767)});
        AddRecipe(new ItemStack(Item.compass, 1), new Object[] {" # ", "#X#", " # ", '#', FCBetterThanWolves.fcItemNuggetIron, 'X', Item.redstone});
        AddRecipe(new ItemStack(Item.pocketSundial, 1), new Object[] {" # ", "#X#", " # ", '#', Item.goldNugget, 'X', Item.netherQuartz});
        AddShapelessRecipe(new ItemStack(Item.flintAndSteel, 1), new Object[] {new ItemStack(FCBetterThanWolves.fcItemNuggetIron), new ItemStack(Item.flint)});
        AddShapelessRecipe(new ItemStack(Item.clay, 9), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockUnfiredClay)});
        AddRecipe(new ItemStack(Item.bucketEmpty, 1), new Object[] {"# #", "# #", "###", '#', FCBetterThanWolves.fcItemNuggetIron});
        AddShapelessRecipe(new ItemStack(Item.silk, 2), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockWeb)});
        AddRecipe(new ItemStack(Item.helmetLeather), new Object[] {"###", "# #", '#', FCBetterThanWolves.fcItemLeatherCut});
        AddRecipe(new ItemStack(Item.plateLeather), new Object[] {"# #", "###", "###", '#', FCBetterThanWolves.fcItemLeatherCut});
        AddRecipe(new ItemStack(Item.legsLeather), new Object[] {"###", "# #", "# #", '#', FCBetterThanWolves.fcItemLeatherCut});
        AddRecipe(new ItemStack(Item.bootsLeather), new Object[] {"# #", "# #", '#', FCBetterThanWolves.fcItemLeatherCut});
        AddShapelessRecipe(new ItemStack(Item.writableBook, 1), new Object[] {Item.paper, Item.paper, Item.paper, FCBetterThanWolves.fcItemLeatherCut, new ItemStack(Item.dyePowder, 1, 0), Item.feather});
        AddShapelessRecipe(new ItemStack(Item.writableBook, 1), new Object[] {Item.paper, Item.paper, Item.paper, Item.leather, new ItemStack(Item.dyePowder, 1, 0), Item.feather});
        AddRecipe(new ItemStack(Item.itemFrame, 1), new Object[] {"mmm", "mlm", "mmm", 'm', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767), 'l', FCBetterThanWolves.fcItemLeatherCut});
        AddRecipe(new ItemStack(Item.itemFrame, 1), new Object[] {"mmm", "mlm", "mmm", 'm', Item.stick, 'l', FCBetterThanWolves.fcItemLeatherCut});
        AddRecipe(new ItemStack(Item.helmetDiamond), new Object[] {"XXX", "X X", 'X', FCBetterThanWolves.fcItemIngotDiamond});
        AddRecipe(new ItemStack(Item.plateDiamond), new Object[] {"X X", "XXX", "XXX", 'X', FCBetterThanWolves.fcItemIngotDiamond});
        AddRecipe(new ItemStack(Item.legsDiamond), new Object[] {"XXX", "X X", "X X", 'X', FCBetterThanWolves.fcItemIngotDiamond});
        AddRecipe(new ItemStack(Item.bootsDiamond), new Object[] {"X X", "X X", 'X', FCBetterThanWolves.fcItemIngotDiamond});
        AddRecipe(new ItemStack(Item.swordDiamond), new Object[] {"X", "X", "#", '#', Item.stick, 'X', FCBetterThanWolves.fcItemIngotDiamond});
        AddRecipe(new ItemStack(Item.pickaxeDiamond), new Object[] {"XXX", " # ", " # ", '#', Item.stick, 'X', FCBetterThanWolves.fcItemIngotDiamond});
        AddRecipe(new ItemStack(Item.shovelDiamond), new Object[] {"X", "#", "#", '#', Item.stick, 'X', FCBetterThanWolves.fcItemIngotDiamond});
        AddRecipe(new ItemStack(Block.lever, 1), new Object[] {"X", "#", "r", '#', FCBetterThanWolves.fcItemStoneBrick, 'X', Item.stick, 'r', Item.redstone});
        AddRecipe(new ItemStack(Block.stoneButton, 1), new Object[] {"#", "r", '#', new ItemStack(Item.itemsList[FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner.blockID], 1, 1), 'r', Item.redstone});
        AddRecipe(new ItemStack(Block.woodenButton, 1), new Object[] {"#", "r", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodCornerItemStubID, 1, 32767), 'r', Item.redstone});
        AddRecipe(new ItemStack(Block.pressurePlateStone, 1), new Object[] {"#", "r", '#', new ItemStack(Item.itemsList[FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner.blockID], 1, 0), 'r', Item.redstone});
        AddRecipe(new ItemStack(Block.pressurePlatePlanks, 1), new Object[] {"#", "r", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 32767), 'r', Item.redstone});
        AddRecipe(new ItemStack(Item.doorIron, 1), new Object[] {"##r", "## ", "##r", '#', Item.ingotIron, 'r', FCBetterThanWolves.fcItemRedstoneLatch});
        AddRecipe(new ItemStack(Block.dispenser, 1), new Object[] {"###", "#X#", "#R#", '#', FCBetterThanWolves.fcItemStoneBrick, 'X', Item.bow, 'R', FCBetterThanWolves.fcItemRedstoneLatch});
        AddRecipe(new ItemStack(Block.music, 1), new Object[] {"###", "#X#", "###", '#', Block.planks, 'X', FCBetterThanWolves.fcItemRedstoneLatch});
        AddShapelessRecipe(new ItemStack(Block.pumpkinLantern, 1), new Object[] {new ItemStack(Block.pumpkin), new ItemStack(FCBetterThanWolves.fcItemCandle, 1, 32767)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockUnfiredClay, 1), new Object[] {"###", "###", "###", '#', Item.clay});
        AddRecipe(new ItemStack(Item.axeIron), new Object[] {"X ", "X#", " #", '#', Item.stick, 'X', Item.ingotIron});
        AddRecipe(new ItemStack(Item.axeGold), new Object[] {"X ", "X#", " #", '#', Item.stick, 'X', Item.ingotGold});
        AddRecipe(new ItemStack(Item.axeDiamond), new Object[] {"X ", "X#", " #", '#', Item.stick, 'X', FCBetterThanWolves.fcItemIngotDiamond});
        AddShapelessRecipe(new ItemStack(Item.stick, 2), new Object[] {new ItemStack(Block.planks, 1, 32767)});
        AddRecipe(new ItemStack(Item.bed, 1), new Object[] {"###", "XXX", '#', FCBetterThanWolves.fcItemPadding, 'X', Block.planks});
        AddRecipe(new ItemStack(Item.emptyMap, 1), new Object[] {"#S#", "#X#", "#S#", '#', Item.paper, 'X', Item.compass, 'S', FCBetterThanWolves.fcItemSoulUrn});
        AddRecipe(new ItemStack(Item.emptyMap, 1, 1), new Object[] {"###", "#X#", "###", '#', Item.paper, 'X', new ItemStack(Item.emptyMap, 1, 0)});
        AddRecipe(new ItemStack(Item.emptyMap, 1, 2), new Object[] {"###", "#X#", "###", '#', Item.paper, 'X', new ItemStack(Item.emptyMap, 1, 1)});
        AddRecipe(new ItemStack(Item.emptyMap, 1, 3), new Object[] {"###", "#X#", "###", '#', Item.paper, 'X', new ItemStack(Item.emptyMap, 1, 2)});
        AddRecipe(new ItemStack(Item.emptyMap, 1, 4), new Object[] {"###", "#X#", "###", '#', Item.paper, 'X', new ItemStack(Item.emptyMap, 1, 3)});
    }

    private static void AddConversionRecipes()
    {
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemHempFibers, 9), new Object[] {new ItemStack(FCBetterThanWolves.fcItemHempCloth)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemHempFibers, 6), new Object[] {new ItemStack(FCBetterThanWolves.fcItemRope)});
        AddShapelessRecipe(new ItemStack(Block.cobblestone), new Object[] {new ItemStack(Block.stoneSingleSlab, 1, 3), new ItemStack(Block.stoneSingleSlab, 1, 3)});
        AddShapelessRecipe(new ItemStack(Block.brick), new Object[] {new ItemStack(Block.stoneSingleSlab, 1, 4), new ItemStack(Block.stoneSingleSlab, 1, 4)});
        AddShapelessRecipe(new ItemStack(Block.stoneBrick), new Object[] {new ItemStack(Block.stoneSingleSlab, 1, 5), new ItemStack(Block.stoneSingleSlab, 1, 5)});
        AddShapelessRecipe(new ItemStack(Block.netherBrick), new Object[] {new ItemStack(Block.stoneSingleSlab, 1, 6), new ItemStack(Block.stoneSingleSlab, 1, 6)});

        for (int var0 = 0; var0 < 5; ++var0)
        {
            AddShapelessRecipe(new ItemStack(Block.planks, 1, var0), new Object[] {new ItemStack(Block.woodSingleSlab, 1, var0), new ItemStack(Block.woodSingleSlab, 1, var0)});
        }

        AddShapelessRecipe(new ItemStack(Item.clay, 8), new Object[] {new ItemStack(FCBetterThanWolves.fcUnfiredPottery, 1, 0)});
        AddShapelessRecipe(new ItemStack(Item.clay, 6), new Object[] {new ItemStack(FCBetterThanWolves.fcUnfiredPottery, 1, 1)});
        AddShapelessRecipe(new ItemStack(Item.clay, 4), new Object[] {new ItemStack(FCBetterThanWolves.fcUnfiredPottery, 1, 2)});
        AddShapelessRecipe(new ItemStack(Item.clay, 2), new Object[] {new ItemStack(FCBetterThanWolves.fcUnfiredPottery, 1, 3)});
        AddShapelessRecipe(new ItemStack(Item.clay, 1), new Object[] {new ItemStack(FCBetterThanWolves.fcUnfiredPottery, 1, 4)});
        AddRecipe(new ItemStack(Item.ingotIron, 1), new Object[] {"###", "###", "###", '#', new ItemStack(FCBetterThanWolves.fcItemNuggetIron)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 9), new Object[] {new ItemStack(Item.ingotIron)});
        AddRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 1), new Object[] {"###", "###", "###", '#', new ItemStack(FCBetterThanWolves.fcItemNuggetSteel)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetSteel, 9), new Object[] {new ItemStack(FCBetterThanWolves.fcItemSteel)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemPileSoulSand, 4), new Object[] {new ItemStack(Block.slowSand, 1)});
        AddShapelessRecipe(new ItemStack(Block.slowSand, 1), new Object[] {new ItemStack(FCBetterThanWolves.fcItemPileSoulSand), new ItemStack(FCBetterThanWolves.fcItemPileSoulSand), new ItemStack(FCBetterThanWolves.fcItemPileSoulSand), new ItemStack(FCBetterThanWolves.fcItemPileSoulSand)});
        AddShapelessRecipe(new ItemStack(Item.book, 3), new Object[] {new ItemStack(Block.bookShelf)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemWheatSeeds, 2), new Object[] {new ItemStack(FCBetterThanWolves.fcItemWheat)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemWheatSeeds), new Object[] {new ItemStack(Item.seeds)});
    }

    private static void AddSmeltingRecipes()
    {
        FurnaceRecipes.smelting().addSmelting(FCBetterThanWolves.fcItemWolfRaw.itemID, new ItemStack(FCBetterThanWolves.fcItemWolfCooked), 0.0F);
        FurnaceRecipes.smelting().addSmelting(FCBetterThanWolves.fcItemBreadDough.itemID, new ItemStack(Item.bread), 0.0F);
        FurnaceRecipes.smelting().addSmelting(FCBetterThanWolves.fcItemRawEgg.itemID, new ItemStack(FCBetterThanWolves.fcItemFriedEgg), 0.0F);
        FurnaceRecipes.smelting().addSmelting(FCBetterThanWolves.fcItemMuttonRaw.itemID, new ItemStack(FCBetterThanWolves.fcItemMuttonCooked), 0.0F);
        FurnaceRecipes.smelting().addSmelting(Item.carrot.itemID, new ItemStack(FCBetterThanWolves.fcItemCookedCarrot), 0.0F);
        FurnaceRecipes.smelting().addSmelting(FCBetterThanWolves.fcItemRawKebab.itemID, new ItemStack(FCBetterThanWolves.fcItemCookedKebab), 0.0F);
        FurnaceRecipes.smelting().addSmelting(FCBetterThanWolves.fcItemRawMysteryMeat.itemID, new ItemStack(FCBetterThanWolves.fcItemCookedMysteryMeat), 0.0F);
        FurnaceRecipes.smelting().addSmelting(FCBetterThanWolves.fcItemRawScrambledEggs.itemID, new ItemStack(FCBetterThanWolves.fcItemCookedScrambledEggs), 0.0F);
        FurnaceRecipes.smelting().addSmelting(FCBetterThanWolves.fcItemRawMushroomOmelet.itemID, new ItemStack(FCBetterThanWolves.fcItemCookedMushroomOmelet), 0.0F);
        FurnaceRecipes.smelting().addSmelting(FCBetterThanWolves.fcItemPastryUncookedCake.itemID, new ItemStack(Item.cake), 0.0F);
        FurnaceRecipes.smelting().addSmelting(FCBetterThanWolves.fcItemPastryUncookedCookies.itemID, new ItemStack(Item.cookie, 8), 0.0F);
        FurnaceRecipes.smelting().addSmelting(FCBetterThanWolves.fcItemPastryUncookedPumpkinPie.itemID, new ItemStack(Item.pumpkinPie), 0.0F);
        FurnaceRecipes.smelting().addSmelting(FCBetterThanWolves.fcItemBeastLiverRaw.itemID, new ItemStack(FCBetterThanWolves.fcItemBeastLiverCooked), 0.0F);
        FurnaceRecipes.smelting().addSmelting(FCBetterThanWolves.fcItemNetherSludge.itemID, new ItemStack(FCBetterThanWolves.fcItemNetherBrick), 0.0F, 2);
        FurnaceRecipes.smelting().addSmelting(Item.clay.itemID, new ItemStack(Item.brick), 0.0F, 2);
        FurnaceRecipes.smelting().addSmelting(FCBetterThanWolves.fcItemChunkIronOre.itemID, new ItemStack(FCBetterThanWolves.fcItemNuggetIron), 0.0F, 3);
        FurnaceRecipes.smelting().addSmelting(FCBetterThanWolves.fcItemChunkGoldOre.itemID, new ItemStack(Item.goldNugget), 0.0F, 3);
    }

    private static void AddCampfireRecipes()
    {
        AddCampfireRecipe(FCBetterThanWolves.fcItemWolfRaw.itemID, new ItemStack(FCBetterThanWolves.fcItemWolfCooked));
        AddCampfireRecipe(FCBetterThanWolves.fcItemMuttonRaw.itemID, new ItemStack(FCBetterThanWolves.fcItemMuttonCooked));
        AddCampfireRecipe(FCBetterThanWolves.fcItemRawMysteryMeat.itemID, new ItemStack(FCBetterThanWolves.fcItemCookedMysteryMeat));
        AddCampfireRecipe(FCBetterThanWolves.fcItemBeastLiverRaw.itemID, new ItemStack(FCBetterThanWolves.fcItemBeastLiverCooked));
        AddCampfireRecipe(Item.porkRaw.itemID, new ItemStack(Item.porkCooked));
        AddCampfireRecipe(Item.beefRaw.itemID, new ItemStack(Item.beefCooked));
        AddCampfireRecipe(Item.chickenRaw.itemID, new ItemStack(Item.chickenCooked));
        AddCampfireRecipe(Item.fishRaw.itemID, new ItemStack(Item.fishCooked));
    }

    private static void AddAnvilRecipes()
    {
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemRefinedSword, 1), new Object[] {"#", "#", "#", "X", '#', FCBetterThanWolves.fcItemSteel, 'X', FCBetterThanWolves.fcItemHaft});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemRefinedShovel, 1), new Object[] {"#", "X", "X", "X", '#', FCBetterThanWolves.fcItemSteel, 'X', FCBetterThanWolves.fcItemHaft});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemRefinedPickAxe, 1), new Object[] {"###", " X ", " X ", " X ", '#', FCBetterThanWolves.fcItemSteel, 'X', FCBetterThanWolves.fcItemHaft});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemMattock, 1), new Object[] {" ###", "# X ", "  X ", "  X ", '#', FCBetterThanWolves.fcItemSteel, 'X', FCBetterThanWolves.fcItemHaft});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemRefinedHoe, 1), new Object[] {"##", " X", " X", " X", '#', FCBetterThanWolves.fcItemSteel, 'X', FCBetterThanWolves.fcItemHaft});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemBattleAxe, 1), new Object[] {"###", "#X#", " X ", " X ", '#', FCBetterThanWolves.fcItemSteel, 'X', FCBetterThanWolves.fcItemHaft});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemRefinedAxe, 1), new Object[] {"# ", "#X", " X", " X", '#', FCBetterThanWolves.fcItemSteel, 'X', FCBetterThanWolves.fcItemHaft});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemArmorPlate, 1), new Object[] {"#XY#", '#', FCBetterThanWolves.fcItemStrap, 'X', FCBetterThanWolves.fcItemSteel, 'Y', FCBetterThanWolves.fcItemPadding});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemPlateHelm, 1), new Object[] {"####", "#  #", "#  #", " XX ", '#', FCBetterThanWolves.fcItemSteel, 'X', FCBetterThanWolves.fcItemArmorPlate});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemPlateBreastPlate, 1), new Object[] {"X  X", "####", "####", "####", '#', FCBetterThanWolves.fcItemSteel, 'X', FCBetterThanWolves.fcItemArmorPlate});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemPlateLeggings, 1), new Object[] {"####", "X##X", "X  X", "X  X", '#', FCBetterThanWolves.fcItemSteel, 'X', FCBetterThanWolves.fcItemArmorPlate});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemPlateBoots, 1), new Object[] {" ## ", " ## ", "#XX#", '#', FCBetterThanWolves.fcItemSteel, 'X', FCBetterThanWolves.fcItemArmorPlate});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemBroadheadArrowhead, 6), new Object[] {" # ", " # ", "###", " # ", '#', FCBetterThanWolves.fcItemNuggetSteel});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcBlockPressurePlateSoulforgedSteel, 2), new Object[] {"####", " rr ", '#', FCBetterThanWolves.fcItemSteel, 'r', Item.redstone});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemPolishedLapis, 2), new Object[] {"###", "###", "GGG", " R ", '#', new ItemStack(Item.dyePowder, 1, 4), 'G', Item.goldNugget, 'R', Item.redstone});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcBlockDetector), new Object[] {"####", "XTTX", "#YY#", "#YY#", '#', FCBetterThanWolves.fcItemStoneBrick, 'X', FCBetterThanWolves.fcItemPolishedLapis, 'Y', Item.redstone, 'T', Block.torchRedstoneActive});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcBlockDispenser), new Object[] {"####", "#ZZ#", "YTTY", "YXXY", '#', Block.cobblestoneMossy, 'X', Item.redstone, 'Y', FCBetterThanWolves.fcItemStoneBrick, 'Z', FCBetterThanWolves.fcItemSoulUrn, 'T', Block.torchRedstoneActive});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcBuddyBlock, 1), new Object[] {"##X#", "XYY#", "#YYX", "#X##", '#', FCBetterThanWolves.fcItemStoneBrick, 'X', FCBetterThanWolves.fcItemPolishedLapis, 'Y', Block.torchRedstoneActive});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcSoulforgedSteelBlock, 1), new Object[] {"####", "####", "####", "####", '#', FCBetterThanWolves.fcItemSteel});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemTuningFork, 6), new Object[] {"# #", "# #", " # ", " # ", '#', FCBetterThanWolves.fcItemSteel});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemCanvas), new Object[] {"MMMM", "MFFM", "MFFM", "MMMM", 'F', FCBetterThanWolves.fcItemHempCloth, 'M', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 32767)});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 13), new Object[] {"#  #", "#  #", "####", '#', FCBetterThanWolves.fcItemStoneBrick});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemMail, 2), new Object[] {"# # ", " # #", "# # ", " # #", '#', FCBetterThanWolves.fcItemNuggetIron});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcItemMail, 2), new Object[] {" # #", "# # ", " # #", "# # ", '#', FCBetterThanWolves.fcItemNuggetIron});
        AddAnvilRecipe(new ItemStack(FCBetterThanWolves.fcBlockSoulforgeDormant), new Object[] {"####", " #  ", " #  ", "####", '#', Item.ingotGold});
    }

    private static void AddCauldronRecipes()
    {
        AddCauldronRecipe(new ItemStack(Item.gunpowder, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemBrimstone), new ItemStack(FCBetterThanWolves.fcItemNitre), new ItemStack(FCBetterThanWolves.fcItemCoalDust)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemFilament, 1), new ItemStack[] {new ItemStack(Item.lightStoneDust), new ItemStack(Item.redstone), new ItemStack(Item.silk)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemFilament, 1), new ItemStack[] {new ItemStack(Item.lightStoneDust), new ItemStack(Item.redstone), new ItemStack(FCBetterThanWolves.fcItemHempFibers)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemElement, 1), new ItemStack[] {new ItemStack(Item.blazePowder), new ItemStack(Item.redstone), new ItemStack(Item.silk)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemElement, 1), new ItemStack[] {new ItemStack(Item.blazePowder), new ItemStack(Item.redstone), new ItemStack(FCBetterThanWolves.fcItemHempFibers)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemFuse, 2), new ItemStack[] {new ItemStack(Item.gunpowder), new ItemStack(Item.silk)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemFuse, 2), new ItemStack[] {new ItemStack(Item.gunpowder), new ItemStack(FCBetterThanWolves.fcItemHempFibers)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemBlastingOil, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemHellfireDust), new ItemStack(FCBetterThanWolves.fcItemTallow)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemNetherSludge, 8), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemPotash, 1, 32767), new ItemStack(FCBetterThanWolves.fcItemHellfireDust, 8, 32767)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemNethercoal, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemHellfireDust, 1), new ItemStack(FCBetterThanWolves.fcItemCoalDust, 1)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemConcentratedHellfire, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemHellfireDust, 8)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTannedLeather, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemDung, 1), new ItemStack(FCBetterThanWolves.fcItemScouredLeather, 1), new ItemStack(FCBetterThanWolves.fcItemBark, 5, 0)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTannedLeather, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemDung, 1), new ItemStack(FCBetterThanWolves.fcItemScouredLeather, 1), new ItemStack(FCBetterThanWolves.fcItemBark, 3, 1)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTannedLeather, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemDung, 1), new ItemStack(FCBetterThanWolves.fcItemScouredLeather, 1), new ItemStack(FCBetterThanWolves.fcItemBark, 8, 2)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTannedLeather, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemDung, 1), new ItemStack(FCBetterThanWolves.fcItemScouredLeather, 1), new ItemStack(FCBetterThanWolves.fcItemBark, 2, 3)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTannedLeather, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemDung, 1), new ItemStack(FCBetterThanWolves.fcItemScouredLeather, 1), new ItemStack(FCBetterThanWolves.fcItemBark, 8, 4)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemDung, 1), new ItemStack(FCBetterThanWolves.fcItemScouredLeatherCut, 2), new ItemStack(FCBetterThanWolves.fcItemBark, 5, 0)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemDung, 1), new ItemStack(FCBetterThanWolves.fcItemScouredLeatherCut, 2), new ItemStack(FCBetterThanWolves.fcItemBark, 3, 1)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemDung, 1), new ItemStack(FCBetterThanWolves.fcItemScouredLeatherCut, 2), new ItemStack(FCBetterThanWolves.fcItemBark, 8, 2)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemDung, 1), new ItemStack(FCBetterThanWolves.fcItemScouredLeatherCut, 2), new ItemStack(FCBetterThanWolves.fcItemBark, 2, 3)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemDung, 1), new ItemStack(FCBetterThanWolves.fcItemScouredLeatherCut, 2), new ItemStack(FCBetterThanWolves.fcItemBark, 8, 4)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemDonut, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemFlour, 1), new ItemStack(Item.sugar, 1)});

        for (int var0 = 0; var0 < 15; ++var0)
        {
            AddCauldronRecipe(new ItemStack(Block.cloth, 8, BlockCloth.getBlockFromDye(var0)), new ItemStack[] {new ItemStack(Item.dyePowder, 1, var0), new ItemStack(Block.cloth, 8, 0)});
            AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcWoolSlab, 16, BlockCloth.getBlockFromDye(var0)), new ItemStack[] {new ItemStack(Item.dyePowder, 1, var0), new ItemStack(FCBetterThanWolves.fcWoolSlab, 16, 0)});
            AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemWool, 32, var0), new ItemStack[] {new ItemStack(Item.dyePowder, 1, var0), new ItemStack(FCBetterThanWolves.fcItemWool, 32, 15)});
        }

        AddCauldronRecipe(new ItemStack(Block.cloth, 8, 12), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemDung, 1), new ItemStack(Item.itemsList[Block.cloth.blockID], 8, 0)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcWoolSlab, 16, 12), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemDung, 1), new ItemStack(Item.itemsList[FCBetterThanWolves.fcWoolSlab.blockID], 16, 0)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemWool, 32, 3), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemDung, 1), new ItemStack(FCBetterThanWolves.fcItemWool, 32, 15)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcAestheticVegetation, 1, 2), new ItemStack[] {new ItemStack(Block.sapling, 1, 0), new ItemStack(Block.sapling, 1, 1), new ItemStack(Block.sapling, 1, 2), new ItemStack(Block.sapling, 1, 3), new ItemStack(FCBetterThanWolves.fcItemSoulUrn, 8), new ItemStack(Item.netherStalkSeeds)});
        AddCauldronRecipe(new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockDirtLoose), new ItemStack(FCBetterThanWolves.fcItemBloodMossSpores)}, new ItemStack[] {new ItemStack(Block.mycelium), new ItemStack(FCBetterThanWolves.fcItemMushroomBrown), new ItemStack(FCBetterThanWolves.fcItemMushroomRed), new ItemStack(FCBetterThanWolves.fcItemSoulUrn, 8), new ItemStack(FCBetterThanWolves.fcItemDung), new ItemStack(Item.netherStalkSeeds)});
        FCCraftingManagerCauldron.getInstance().AddRecipe(new ItemStack(Block.cloth, 8, 0), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemPotash, 1, 32767), new ItemStack(Block.cloth, 8, 0)}, true);
        FCCraftingManagerCauldron.getInstance().AddRecipe(new ItemStack(FCBetterThanWolves.fcWoolSlab, 16, 0), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemPotash, 1, 32767), new ItemStack(FCBetterThanWolves.fcWoolSlab, 16, 0)}, true);
        FCCraftingManagerCauldron.getInstance().AddRecipe(new ItemStack(FCBetterThanWolves.fcItemWool, 32, 15), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemPotash, 1, 32767), new ItemStack(FCBetterThanWolves.fcItemWool, 32, 15)}, true);
        AddCauldronRecipe(new ItemStack(Item.dyePowder, 1, 2), new ItemStack[] {new ItemStack(Block.cactus)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemBucketCement, 1), new ItemStack[] {new ItemStack(Block.sand), new ItemStack(Block.gravel), new ItemStack(Item.bucketEmpty), new ItemStack(FCBetterThanWolves.fcItemSoulUrn)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemHardBoiledEgg), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemRawEgg)});
        AddCauldronRecipe(new ItemStack(Block.pistonBase, 4), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemSoap), new ItemStack(Block.pistonStickyBase, 4)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemBoiledPotato), new ItemStack[] {new ItemStack(Item.potato)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemBoiledPotato), new ItemStack[] {new ItemStack(Item.bakedPotato)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemCookedCarrot), new ItemStack[] {new ItemStack(Item.carrot)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 4, 13), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemSoap), new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 4, 12)});
        AddCauldronRecipe(new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemFishSoup, 2), new ItemStack(Item.bucketEmpty)}, new ItemStack[] {new ItemStack(Item.bucketMilk), new ItemStack(Item.fishCooked), new ItemStack(Item.bowlEmpty, 2)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemChickenSoup, 3), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemBoiledPotato), new ItemStack(FCBetterThanWolves.fcItemCookedCarrot), new ItemStack(Item.chickenCooked), new ItemStack(Item.bowlEmpty, 3)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemHeartyStew, 5), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemBoiledPotato), new ItemStack(FCBetterThanWolves.fcItemCookedCarrot), new ItemStack(FCBetterThanWolves.fcItemMushroomBrown, 3), new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(Item.beefCooked), new ItemStack(Item.bowlEmpty, 5)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemHeartyStew, 5), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemBoiledPotato), new ItemStack(FCBetterThanWolves.fcItemCookedCarrot), new ItemStack(FCBetterThanWolves.fcItemMushroomBrown, 3), new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(Item.porkCooked), new ItemStack(Item.bowlEmpty, 5)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemHeartyStew, 5), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemBoiledPotato), new ItemStack(FCBetterThanWolves.fcItemCookedCarrot), new ItemStack(FCBetterThanWolves.fcItemMushroomBrown, 3), new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(FCBetterThanWolves.fcItemMuttonCooked), new ItemStack(Item.bowlEmpty, 5)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemHeartyStew, 5), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemBoiledPotato), new ItemStack(FCBetterThanWolves.fcItemCookedCarrot), new ItemStack(FCBetterThanWolves.fcItemMushroomBrown, 3), new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(FCBetterThanWolves.fcItemWolfCooked), new ItemStack(Item.bowlEmpty, 5)});
        AddCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemHeartyStew, 5), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemBoiledPotato), new ItemStack(FCBetterThanWolves.fcItemCookedCarrot), new ItemStack(FCBetterThanWolves.fcItemMushroomBrown, 3), new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(FCBetterThanWolves.fcItemCookedMysteryMeat), new ItemStack(Item.bowlEmpty, 5)});
        AddCauldronRecipe(new ItemStack[] {new ItemStack(Item.bowlSoup, 2), new ItemStack(Item.bucketEmpty)}, new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemMushroomBrown, 3), new ItemStack(Item.bucketMilk), new ItemStack(Item.bowlEmpty, 2)});
        AddCauldronRecipe(new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemChocolate, 2), new ItemStack(Item.bucketEmpty)}, new ItemStack[] {new ItemStack(Item.dyePowder, 1, 3), new ItemStack(Item.sugar), new ItemStack(Item.bucketMilk)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 1), new ItemStack[] {new ItemStack(Item.leather, 1)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemTannedLeather, 1)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemScouredLeather, 1)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemScouredLeatherCut, 2)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemLeatherCut, 2)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemTannedLeatherCut, 2)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemBelt, 2)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemStrap, 8)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 2), new ItemStack[] {new ItemStack(Item.helmetLeather, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 4), new ItemStack[] {new ItemStack(Item.plateLeather, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 3), new ItemStack[] {new ItemStack(Item.legsLeather, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 2), new ItemStack[] {new ItemStack(Item.bootsLeather, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 2), new ItemStack[] {new ItemStack(Item.saddle, 1)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemArmorGimpHelm, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 3), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemArmorGimpChest, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 3), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemArmorGimpLeggings, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemArmorGimpBoots, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 3), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemBreedingHarness, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 1), new ItemStack[] {new ItemStack(Item.book, 2, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 1), new ItemStack[] {new ItemStack(Item.writableBook, 2, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 1), new ItemStack[] {new ItemStack(Item.writtenBook, 2, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 1), new ItemStack[] {new ItemStack(Item.enchantedBook, 2, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemArmorTannedHelm, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 4), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemArmorTannedChest, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 3), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemArmorTannedLeggings, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemGlue, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemArmorTannedBoots, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTallow, 1), new ItemStack[] {new ItemStack(Item.porkCooked, 1)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTallow, 1), new ItemStack[] {new ItemStack(Item.porkRaw, 1)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTallow, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemWolfCooked, 8)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTallow, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemWolfRaw, 8)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTallow, 1), new ItemStack[] {new ItemStack(Item.beefCooked, 4)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTallow, 1), new ItemStack[] {new ItemStack(Item.beefRaw, 4)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTallow, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemMuttonCooked, 4)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTallow, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemMuttonRaw, 4)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTallow, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemRawMysteryMeat, 2)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemTallow, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemCookedMysteryMeat, 2)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemPotash, 1), new ItemStack[] {new ItemStack(Block.wood, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemPotash, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBloodWood, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemPotash, 1), new ItemStack[] {new ItemStack(Block.planks, 6, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemPotash, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 12, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemPotash, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 24, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemPotash, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockWoodCornerItemStubID, 48, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemPotash, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemSawDust, 16)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemPotash, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemSoulDust, 16)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemSoap, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemPotash, 1), new ItemStack(FCBetterThanWolves.fcItemTallow, 1)});
        AddStokedCauldronRecipe(new ItemStack[] {new ItemStack(Item.silk, 2), new ItemStack(Item.stick, 2)}, new ItemStack[] {new ItemStack(Item.bow, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack[] {new ItemStack(Item.stick, 2), new ItemStack(Item.silk, 1), new ItemStack(Item.bone, 1)}, new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemCompositeBow, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack[] {new ItemStack(Item.flint, 1), new ItemStack(Item.stick, 1), new ItemStack(Item.feather, 1)}, new ItemStack[] {new ItemStack(Item.arrow, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack[] {new ItemStack(Item.flint, 1)}, new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemRottenArrow, 1, 32767)});
        AddStokedCauldronRecipe(new ItemStack(FCBetterThanWolves.fcItemDogFood, 2), new ItemStack[] {new ItemStack(Item.rottenFlesh, 4), new ItemStack(Item.dyePowder, 4, 15), new ItemStack(Item.sugar, 1)});
        AddStokedCauldronRecipe(new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemBrimstone, 1), new ItemStack(FCBetterThanWolves.fcItemSoulFlux, 1)}, new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemEnderSlag, 1)});
    }

    private static void AddCrucibleRecipes()
    {
        AddStokedCrucibleRecipe(new ItemStack(Item.ingotGold, 2), new ItemStack[] {new ItemStack(Item.pickaxeGold, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 12), new ItemStack[] {new ItemStack(Item.axeGold, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 12), new ItemStack[] {new ItemStack(Item.swordGold, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 6), new ItemStack[] {new ItemStack(Item.hoeGold, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 6), new ItemStack[] {new ItemStack(Item.shovelGold, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 30), new ItemStack[] {new ItemStack(Item.helmetGold, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 48), new ItemStack[] {new ItemStack(Item.plateGold, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 42), new ItemStack[] {new ItemStack(Item.legsGold, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 24), new ItemStack[] {new ItemStack(Item.bootsGold, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 3), new ItemStack[] {new ItemStack(Item.pocketSundial)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 6), new ItemStack[] {new ItemStack(Block.railPowered)});
        AddStokedCrucibleRecipe(new ItemStack(Item.ingotGold, 9), new ItemStack[] {new ItemStack(Block.blockGold)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 5), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemOcularOfEnder)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 11), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemEnderSpectacles, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 6), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemGoldenDung)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemRedstoneLatch)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockRedstoneClutch)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 2), new ItemStack[] {new ItemStack(Block.music)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 60), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockSoulforgeDormant)});
        AddStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 8), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockLightningRod)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemMail)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 5), new ItemStack[] {new ItemStack(Item.bucketEmpty)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 5), new ItemStack[] {new ItemStack(Item.bucketLava)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 5), new ItemStack[] {new ItemStack(Item.bucketWater)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 5), new ItemStack[] {new ItemStack(Item.bucketMilk)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 5), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemBucketCement)});
        AddStokedCrucibleRecipe(new ItemStack(Item.ingotIron, 2), new ItemStack[] {new ItemStack(Item.pickaxeIron, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 12), new ItemStack[] {new ItemStack(Item.axeIron, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 12), new ItemStack[] {new ItemStack(Item.swordIron, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 6), new ItemStack[] {new ItemStack(Item.hoeIron, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 6), new ItemStack[] {new ItemStack(Item.shovelIron, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 30), new ItemStack[] {new ItemStack(Item.helmetIron, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 48), new ItemStack[] {new ItemStack(Item.plateIron, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 42), new ItemStack[] {new ItemStack(Item.legsIron, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 24), new ItemStack[] {new ItemStack(Item.bootsIron, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 13), new ItemStack[] {new ItemStack(Item.helmetChain, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 21), new ItemStack[] {new ItemStack(Item.plateChain, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 19), new ItemStack[] {new ItemStack(Item.legsChain, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 11), new ItemStack[] {new ItemStack(Item.bootsChain, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 3), new ItemStack[] {new ItemStack(Item.compass)});
        AddStokedCrucibleRecipe(new ItemStack[] {new ItemStack(Item.ingotIron, 4), new ItemStack(Item.goldNugget, 4)}, new ItemStack[] {new ItemStack(Item.doorIron)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 3), new ItemStack[] {new ItemStack(Item.map)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 12), new ItemStack[] {new ItemStack(Item.shears, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 6), new ItemStack[] {new ItemStack(Block.railDetector)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 6), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcDetectorRailWood)});
        AddStokedCrucibleRecipe(new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 6), new ItemStack(FCBetterThanWolves.fcItemNuggetSteel, 3)}, new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockDetectorRailSoulforgedSteel, 1)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 6), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcPulley)});
        AddStokedCrucibleRecipe(new ItemStack(Item.ingotIron, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcSaw)});
        AddStokedCrucibleRecipe(new ItemStack(Item.ingotIron, 9), new ItemStack[] {new ItemStack(Block.blockIron)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 30), new ItemStack[] {new ItemStack(Item.minecartEmpty)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 30), new ItemStack[] {new ItemStack(Item.minecartCrate)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 30), new ItemStack[] {new ItemStack(Item.minecartPowered)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 18), new ItemStack[] {new ItemStack(Block.rail, 8)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 18), new ItemStack[] {new ItemStack(Block.fenceIron, 8)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 42), new ItemStack[] {new ItemStack(Item.cauldron)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 42), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcCauldron)});
        AddStokedCrucibleRecipe(new ItemStack(Item.ingotIron, 4), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemScrew)});
        AddStokedCrucibleRecipe(new ItemStack(Item.ingotIron, 4), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockScrewPump)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 6), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemArmorGimpHelm)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 12), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemArmorGimpChest)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 6), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemArmorGimpLeggings)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 12), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemArmorGimpBoots)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 42), new ItemStack[] {new ItemStack(Block.anvil)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 1), new ItemStack[] {new ItemStack(Block.tripWireSource, 1)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemChiselIron, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 6), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemMetalFragment)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 36), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockShovel)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 8), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockSpikeIron)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemIngotDiamond, 3), new ItemStack[] {new ItemStack(Item.pickaxeDiamond, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemIngotDiamond, 2), new ItemStack[] {new ItemStack(Item.axeDiamond, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemIngotDiamond, 2), new ItemStack[] {new ItemStack(Item.swordDiamond, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemIngotDiamond, 1), new ItemStack[] {new ItemStack(Item.hoeDiamond, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemIngotDiamond, 1), new ItemStack[] {new ItemStack(Item.shovelDiamond, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemIngotDiamond, 5), new ItemStack[] {new ItemStack(Item.helmetDiamond, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemIngotDiamond, 8), new ItemStack[] {new ItemStack(Item.plateDiamond, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemIngotDiamond, 7), new ItemStack[] {new ItemStack(Item.legsDiamond, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemIngotDiamond, 4), new ItemStack[] {new ItemStack(Item.bootsDiamond, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 5), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemBattleAxe, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemRefinedAxe, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 3), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemRefinedPickAxe, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 4), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemMattock, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 3), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemRefinedSword, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemRefinedHoe, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemRefinedShovel, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemArmorPlate)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 10), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemPlateHelm, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 14), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemPlateBreastPlate, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 12), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemPlateLeggings, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 8), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemPlateBoots, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetSteel, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemBroadheadArrowhead, 1)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetSteel, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemBroadheadArrow, 1)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 16), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 2)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 16), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcSoulforgedSteelBlock)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemTuningFork, 1, 32767)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 2), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcBlockPressurePlateSoulforgedSteel, 1)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemNuggetSteel, 12), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcAestheticNonOpaque, 1, 12)});
        AddStokedCrucibleRecipe(new ItemStack(Item.ingotGold, 1), new ItemStack[] {new ItemStack(Item.goldNugget, 9)});
        AddStokedCrucibleRecipe(new ItemStack(Item.ingotIron, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemNuggetIron, 9)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 1), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcItemNuggetSteel, 9)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcItemSteel, 1), new ItemStack[] {new ItemStack(Item.ingotIron, 1), new ItemStack(FCBetterThanWolves.fcItemCoalDust, 1), new ItemStack(FCBetterThanWolves.fcItemSoulUrn, 1), new ItemStack(FCBetterThanWolves.fcItemSoulFlux, 1)});
        AddStokedCrucibleRecipe(new ItemStack(Block.glass, 4), new ItemStack[] {new ItemStack(Block.sand, 8), new ItemStack(Item.netherQuartz)});
        AddStokedCrucibleRecipe(new ItemStack(Block.glass, 3), new ItemStack[] {new ItemStack(Block.thinGlass, 8)});
        AddStokedCrucibleRecipe(new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 9), new ItemStack[] {new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 10)});
    }

    private static void AddMillStoneRecipes()
    {
        AddMillStoneRecipe(new ItemStack(Item.dyePowder, 2, 1), new ItemStack(Block.plantRed));
        AddMillStoneRecipe(new ItemStack(Item.dyePowder, 2, 11), new ItemStack(Block.plantYellow));
        AddMillStoneRecipe(new ItemStack(Item.dyePowder, 1, 3), new ItemStack(FCBetterThanWolves.fcItemCocoaBeans));
        AddMillStoneRecipe(new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(Item.wheat));
        AddMillStoneRecipe(new ItemStack(FCBetterThanWolves.fcItemFlour), new ItemStack(FCBetterThanWolves.fcItemWheat));
        AddMillStoneRecipe(new ItemStack(Item.sugar), new ItemStack(Item.reed));
        AddMillStoneRecipe(new ItemStack(FCBetterThanWolves.fcItemHempFibers, 4, 0), new ItemStack(FCBetterThanWolves.fcItemHemp));
        AddMillStoneRecipe(new ItemStack(FCBetterThanWolves.fcItemScouredLeather), new ItemStack(Item.leather));
        AddMillStoneRecipe(new ItemStack(FCBetterThanWolves.fcItemScouredLeatherCut), new ItemStack(FCBetterThanWolves.fcItemLeatherCut));
        AddMillStoneRecipe(new ItemStack[] {new ItemStack(Item.silk), new ItemStack(Item.silk), new ItemStack(Item.silk), new ItemStack(Item.silk), new ItemStack(Item.silk), new ItemStack(Item.silk), new ItemStack(Item.silk), new ItemStack(Item.silk), new ItemStack(Item.silk), new ItemStack(Item.silk), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.dyePowder, 1, 1), new ItemStack(FCBetterThanWolves.fcItemWolfRaw, 1, 0)}, new ItemStack[] {new ItemStack(FCBetterThanWolves.fcCompanionCube)});
        AddMillStoneRecipe(new ItemStack(FCBetterThanWolves.fcItemWolfRaw, 1, 0), new ItemStack(FCBetterThanWolves.fcCompanionCube, 1, 1));
        AddMillStoneRecipe(new ItemStack(FCBetterThanWolves.fcItemCoalDust, 2), new ItemStack(Item.coal, 1, 32767));
        AddMillStoneRecipe(new ItemStack(Item.dyePowder, 3, 15), new ItemStack(Item.bone));
        AddMillStoneRecipe(new ItemStack(Item.dyePowder, 6, 15), new ItemStack(Item.skull.itemID, 1, 0));
        AddMillStoneRecipe(new ItemStack(Item.dyePowder, 6, 15), new ItemStack(Item.skull.itemID, 1, 1));
        AddMillStoneRecipe(new ItemStack(FCBetterThanWolves.fcItemGroundNetherrack), new ItemStack(Block.netherrack));
    }

    private static void AddTuningForkRecipes()
    {
        for (int var0 = 0; var0 < 24; ++var0)
        {
            AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemTuningFork, 1, var0 + 1), new Object[] {new ItemStack(FCBetterThanWolves.fcItemTuningFork, 1, var0)});
        }

        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemTuningFork, 1, 0), new Object[] {new ItemStack(FCBetterThanWolves.fcItemTuningFork, 1, 24)});
    }

    private static void AddSubBlockRecipes()
    {
        AddWoodSubBlockRecipes();
        AddSubBlockRecipesOfType(Block.stone, 0, FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner, FCBetterThanWolves.fcBlockSmoothStoneMouldingAndDecorative, true);
        AddSubBlockRecipesOfType(Block.stoneBrick, 0, FCBetterThanWolves.fcBlockStoneBrickSidingAndCorner, FCBetterThanWolves.fcBlockStoneBrickMouldingAndDecorative, true);
        AddSubBlockRecipesOfType(FCBetterThanWolves.fcAestheticOpaque, 9, FCBetterThanWolves.fcBlockWhiteStoneSidingAndCorner, FCBetterThanWolves.fcBlockWhiteStoneMouldingAndDecorative, true);
        AddSubBlockRecipesOfType(Block.netherBrick, 0, FCBetterThanWolves.fcBlockNetherBrickSidingAndCorner, FCBetterThanWolves.fcBlockNetherBrickMouldingAndDecorative, false);
        AddRecipe(new ItemStack(Block.netherFence, 2), new Object[] {"###", '#', new ItemStack(FCBetterThanWolves.fcBlockNetherBrickMouldingAndDecorative, 1, 0)});
        AddSubBlockRecipesOfType(Block.brick, 0, FCBetterThanWolves.fcBlockBrickSidingAndCorner, FCBetterThanWolves.fcBlockBrickMouldingAndDecorative, true);
        AddSubBlockRecipesOfType(Block.sandStone, 32767, 0, FCBetterThanWolves.fcBlockSandstoneSidingAndCorner, FCBetterThanWolves.fcBlockSandstoneMouldingAndDecorative, true);
        AddSubBlockRecipesOfType(Block.blockNetherQuartz, 0, FCBetterThanWolves.fcBlockSidingAndCornerBlackStone, FCBetterThanWolves.fcBlockMouldingAndDecorativeBlackStone, true);
    }

    public static void AddSubBlockRecipesOfType(Block var0, int var1, Block var2, Block var3, boolean var4)
    {
        AddSubBlockRecipesOfType(var0, var1, var1, var2, var3, var4);
    }

    public static void AddSubBlockRecipesOfType(Block var0, int var1, int var2, Block var3, Block var4, boolean var5)
    {
        AddAnvilRecipe(new ItemStack(var3, 8, 0), new Object[] {"####", '#', new ItemStack(var0, 1, var1)});
        AddAnvilRecipe(new ItemStack(var4, 8, 0), new Object[] {"####", '#', new ItemStack(var3, 1, 0)});
        AddAnvilRecipe(new ItemStack(var3, 8, 1), new Object[] {"####", '#', new ItemStack(var4, 1, 0)});
        AddRecipe(new ItemStack(var4, 1, 12), new Object[] {"M", "M", "M", 'M', new ItemStack(var4, 1, 0)});
        AddRecipe(new ItemStack(var4, 6, 13), new Object[] {" S ", "###", "###", '#', new ItemStack(var0, 1, var1), 'S', new ItemStack(var3, 8, 0)});
        AddRecipe(new ItemStack(var4, 4, 15), new Object[] {"###", " X ", " X ", '#', new ItemStack(var3, 1, 0), 'X', new ItemStack(var4, 1, 0)});
        AddRecipe(new ItemStack(var3, 4, 12), new Object[] {"###", " X ", '#', new ItemStack(var3, 1, 0), 'X', new ItemStack(var4, 1, 0)});

        if (var5)
        {
            AddRecipe(new ItemStack(var3, 6, 14), new Object[] {"###", "###", '#', new ItemStack(var0, 1, var1)});
            AddRecipe(new ItemStack(var3, 2, 14), new Object[] {"###", '#', new ItemStack(var4, 1, 0)});
        }

        AddShapelessRecipe(new ItemStack(var0, 1, var2), new Object[] {new ItemStack(var3, 1, 0), new ItemStack(var3, 1, 0)});
        AddShapelessRecipe(new ItemStack(var3, 1, 0), new Object[] {new ItemStack(var4, 1, 0), new ItemStack(var4, 1, 0)});
        AddShapelessRecipe(new ItemStack(var4, 1, 0), new Object[] {new ItemStack(var3, 1, 1), new ItemStack(var3, 1, 1)});
    }

    private static void AddWoodSubBlockRecipes()
    {
        for (int var0 = 0; var0 <= 4; ++var0)
        {
            AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 6, FCItemBlockWoodMouldingDecorativeStub.GetItemDamageForType(var0, 1)), new Object[] {" S ", "###", "###", '#', new ItemStack(Block.planks, 1, var0), 'S', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, var0)});
            AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, FCItemBlockWoodMouldingDecorativeStub.GetItemDamageForType(var0, 0)), new Object[] {"M", "M", "M", 'M', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, var0)});
            AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 4, FCItemBlockWoodMouldingDecorativeStub.GetItemDamageForType(var0, 2)), new Object[] {"###", " X ", " X ", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, var0), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, var0)});
            AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 4, FCItemBlockWoodSidingDecorativeStub.GetItemDamageForType(var0, 0)), new Object[] {"###", " X ", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, var0), 'X', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, var0)});

            if (var0 == 0)
            {
                AddRecipe(new ItemStack(Block.fence, 6), new Object[] {"###", "###", '#', new ItemStack(Block.planks, 1, var0)});
                AddRecipe(new ItemStack(Block.fence, 2), new Object[] {"###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, var0)});
            }
            else
            {
                AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 6, FCItemBlockWoodSidingDecorativeStub.GetItemDamageForType(var0, 1)), new Object[] {"###", "###", '#', new ItemStack(Block.planks, 1, var0)});
                AddRecipe(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingDecorativeItemStubID, 2, FCItemBlockWoodSidingDecorativeStub.GetItemDamageForType(var0, 1)), new Object[] {"###", '#', new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, var0)});
            }

            AddShapelessRecipe(new ItemStack(Block.planks, 1, var0), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, var0), new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, var0)});
            AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, var0), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, var0), new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, var0)});
            AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, var0), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockWoodCornerItemStubID, 1, var0), new ItemStack(FCBetterThanWolves.fcBlockWoodCornerItemStubID, 1, var0)});
        }
    }

    private static void AddLegacyConversionRecipes()
    {
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingDecorativeItemStubID, 1, 8), new Object[] {new ItemStack(FCBetterThanWolves.fcAestheticNonOpaque, 1, 4)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockSmoothStoneMouldingAndDecorative, 1, 13), new Object[] {new ItemStack(FCBetterThanWolves.fcAestheticNonOpaque, 1, 2)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockSmoothStoneMouldingAndDecorative, 1, 12), new Object[] {new ItemStack(FCBetterThanWolves.fcAestheticNonOpaque, 1, 1)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockWoodMouldingItemStubID, 1, 0), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockWoodOakMouldingAndDecorative)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner, 1, 1), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockLegacySmoothstoneAndOakCorner, 1, 8)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockWoodCornerItemStubID, 1, 0), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockLegacySmoothstoneAndOakCorner, 1, 0)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockSmoothStoneSidingAndCorner, 1, 0), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockLegacySmoothstoneAndOakSiding, 1, 0)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockWoodSidingItemStubID, 1, 0), new Object[] {new ItemStack(FCBetterThanWolves.fcBlockLegacySmoothstoneAndOakSiding, 1, 1)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockAestheticOpaqueEarth, 1, 7), new Object[] {new ItemStack(FCBetterThanWolves.fcAestheticOpaque, 1, 1)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemMushroomBrown), new Object[] {new ItemStack(Block.mushroomBrown)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcItemMushroomRed), new Object[] {new ItemStack(Block.mushroomRed)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockWorkbench), new Object[] {new ItemStack(Block.workbench)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockChest), new Object[] {new ItemStack(Block.chest)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockWeb), new Object[] {new ItemStack(Block.web)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockUnfiredClay), new Object[] {new ItemStack(Block.blockClay)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockLadder), new Object[] {new ItemStack(Block.ladder)});
        AddShapelessRecipe(new ItemStack(FCBetterThanWolves.fcBlockSnowSolid), new Object[] {new ItemStack(Block.blockSnow)});
    }

    private static void AddCustomRecipeClasses()
    {
        CraftingManager.getInstance().getRecipeList().add(new FCRecipesFishingRodBaiting());
        CraftingManager.getInstance().getRecipeList().add(new FCRecipesLogChopping());
        CraftingManager.getInstance().getRecipeList().add(new FCRecipesKnitting());
    }

    private static void RemoveVanillaRecipes()
    {
        RemoveVanillaRecipe(new ItemStack(Item.bread, 1), new Object[] {"###", '#', Item.wheat});
        RemoveVanillaShapelessRecipe(new ItemStack(Item.dyePowder, 3, 15), new Object[] {Item.bone});
        RemoveVanillaRecipe(new ItemStack(Item.sugar, 1), new Object[] {"#", '#', Item.reed});
        RemoveVanillaRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', Item.egg});
        RemoveVanillaShapelessRecipe(new ItemStack(Item.dyePowder, 2, 11), new Object[] {Block.plantYellow});
        RemoveVanillaShapelessRecipe(new ItemStack(Item.dyePowder, 2, 1), new Object[] {Block.plantRed});
        RemoveVanillaRecipe(new ItemStack(Block.tnt, 1), new Object[] {"X#X", "#X#", "X#X", 'X', Item.gunpowder, '#', Block.sand});
        RemoveVanillaRecipe(new ItemStack(Item.cookie, 8), new Object[] {"#X#", 'X', new ItemStack(Item.dyePowder, 1, 3), '#', Item.wheat});
        RemoveVanillaRecipe(new ItemStack(Block.anvil, 1), new Object[] {"III", " i ", "iii", 'I', Block.blockIron, 'i', Item.ingotIron});
        RemoveVanillaShapelessRecipe(new ItemStack(Item.bowlSoup), new Object[] {Block.mushroomBrown, Block.mushroomRed, Item.bowlEmpty});
        RemoveVanillaRecipe(new ItemStack(Block.melon), new Object[] {"MMM", "MMM", "MMM", 'M', Item.melon});
        RemoveVanillaShapelessRecipe(new ItemStack(Item.pumpkinPie), new Object[] {Block.pumpkin, Item.sugar, Item.egg});
        RemoveVanillaRecipe(new ItemStack(Item.pumpkinSeeds, 4), new Object[] {"M", 'M', Block.pumpkin});
        RemoveVanillaRecipe(new ItemStack(Item.compass, 1), new Object[] {" # ", "#X#", " # ", '#', Item.ingotIron, 'X', Item.redstone});
        RemoveVanillaRecipe(new ItemStack(Item.pocketSundial, 1), new Object[] {" # ", "#X#", " # ", '#', Item.ingotGold, 'X', Item.redstone});
        RemoveVanillaRecipe(new ItemStack(Item.flintAndSteel, 1), new Object[] {"A ", " B", 'A', Item.ingotIron, 'B', Item.flint});
        RemoveVanillaShapelessRecipe(new ItemStack(Item.fermentedSpiderEye), new Object[] {Item.spiderEye, Block.mushroomBrown, Item.sugar});
        RemoveVanillaRecipe(new ItemStack(Block.torchWood, 4), new Object[] {"X", "#", 'X', Item.coal, '#', Item.stick});
        RemoveVanillaRecipe(new ItemStack(Block.torchWood, 4), new Object[] {"X", "#", 'X', new ItemStack(Item.coal, 1, 1), '#', Item.stick});
        RemoveVanillaRecipe(new ItemStack(Item.bucketEmpty, 1), new Object[] {"# #", " # ", '#', Item.ingotIron});
        RemoveVanillaRecipe(new ItemStack(Item.redstoneRepeater, 1), new Object[] {"#X#", "III", '#', Block.torchRedstoneActive, 'X', Item.redstone, 'I', Block.stone});
        RemoveVanillaRecipe(new ItemStack(Block.snow, 6), new Object[] {"###", '#', Block.blockSnow});
        RemoveVanillaRecipe(new ItemStack(Block.dropper, 1), new Object[] {"###", "# #", "#R#", '#', Block.cobblestone, 'R', Item.redstone});
        RemoveVanillaRecipe(new ItemStack(Block.stoneButton, 1), new Object[] {"#", '#', Block.stone});
        RemoveVanillaRecipe(new ItemStack(Block.woodenButton, 1), new Object[] {"#", '#', Block.planks});
        RemoveVanillaRecipe(new ItemStack(Block.pressurePlateStone, 1), new Object[] {"##", '#', Block.stone});
        RemoveVanillaRecipe(new ItemStack(Block.pressurePlatePlanks, 1), new Object[] {"##", '#', Block.planks});
        RemoveVanillaRecipe(new ItemStack(Block.pressurePlateIron, 1), new Object[] {"##", '#', Item.ingotIron});
        RemoveVanillaRecipe(new ItemStack(Block.pressurePlateGold, 1), new Object[] {"##", '#', Item.ingotGold});
        RemoveVanillaRecipe(new ItemStack(Block.daylightSensor), new Object[] {"GGG", "QQQ", "WWW", 'G', Block.glass, 'Q', Item.netherQuartz, 'W', Block.woodSingleSlab});
        RemoveVanillaRecipe(new ItemStack(Block.hopperBlock), new Object[] {"I I", "ICI", " I ", 'I', Item.ingotIron, 'C', Block.chest});
        RemoveVanillaRecipe(new ItemStack(Block.railActivator, 6), new Object[] {"XSX", "X#X", "XSX", 'X', Item.ingotIron, '#', Block.torchRedstoneActive, 'S', Item.stick});
        RemoveVanillaRecipe(new ItemStack(Item.comparator, 1), new Object[] {" # ", "#X#", "III", '#', Block.torchRedstoneActive, 'X', Item.netherQuartz, 'I', Block.stone});
        RemoveVanillaRecipe(new ItemStack(Item.minecartTnt, 1), new Object[] {"A", "B", 'A', Block.tnt, 'B', Item.minecartEmpty});
        RemoveVanillaRecipe(new ItemStack(Item.minecartHopper, 1), new Object[] {"A", "B", 'A', Block.hopperBlock, 'B', Item.minecartEmpty});
        RemoveVanillaRecipe(new ItemStack(Block.chestTrapped), new Object[] {"#-", '#', Block.chest, '-', Block.tripWireSource});
        RemoveVanillaRecipe(new ItemStack(Item.helmetDiamond), new Object[] {"XXX", "X X", 'X', Item.diamond});
        RemoveVanillaRecipe(new ItemStack(Item.plateDiamond), new Object[] {"X X", "XXX", "XXX", 'X', Item.diamond});
        RemoveVanillaRecipe(new ItemStack(Item.legsDiamond), new Object[] {"XXX", "X X", "X X", 'X', Item.diamond});
        RemoveVanillaRecipe(new ItemStack(Item.bootsDiamond), new Object[] {"X X", "X X", 'X', Item.diamond});
        RemoveVanillaRecipe(new ItemStack(Item.swordDiamond), new Object[] {"X", "X", "#", '#', Item.stick, 'X', Item.diamond});
        RemoveVanillaRecipe(new ItemStack(Item.pickaxeDiamond), new Object[] {"XXX", " # ", " # ", '#', Item.stick, 'X', Item.diamond});
        RemoveVanillaRecipe(new ItemStack(Item.shovelDiamond), new Object[] {"X", "#", "#", '#', Item.stick, 'X', Item.diamond});
        RemoveVanillaRecipe(new ItemStack(Item.hoeDiamond), new Object[] {"XX", " #", " #", '#', Item.stick, 'X', Item.diamond});
        RemoveVanillaRecipe(new ItemStack(Item.fishingRod, 1), new Object[] {"  #", " #X", "# X", '#', Item.stick, 'X', Item.silk});
        RemoveVanillaRecipe(new ItemStack(Block.cloth, 1), new Object[] {"##", "##", '#', Item.silk});
        RemoveVanillaRecipe(new ItemStack(Block.planks, 4, 0), new Object[] {"#", '#', new ItemStack(Block.wood, 1, 0)});
        RemoveVanillaRecipe(new ItemStack(Block.planks, 4, 1), new Object[] {"#", '#', new ItemStack(Block.wood, 1, 1)});
        RemoveVanillaRecipe(new ItemStack(Block.planks, 4, 2), new Object[] {"#", '#', new ItemStack(Block.wood, 1, 2)});
        RemoveVanillaRecipe(new ItemStack(Block.planks, 4, 3), new Object[] {"#", '#', new ItemStack(Block.wood, 1, 3)});
        RemoveVanillaRecipe(new ItemStack(Block.lever, 1), new Object[] {"X", "#", '#', Block.cobblestone, 'X', Item.stick});
        RemoveVanillaRecipe(new ItemStack(Item.doorIron, 1), new Object[] {"##", "##", "##", '#', Item.ingotIron});
        RemoveVanillaRecipe(new ItemStack(Block.tripWireSource, 2), new Object[] {"I", "S", "#", '#', Block.planks, 'S', Item.stick, 'I', Item.ingotIron});
        RemoveVanillaRecipe(new ItemStack(Block.dispenser, 1), new Object[] {"###", "#X#", "#R#", '#', Block.cobblestone, 'X', Item.bow, 'R', Item.redstone});
        RemoveVanillaRecipe(new ItemStack(Block.music, 1), new Object[] {"###", "#X#", "###", '#', Block.planks, 'X', Item.redstone});
        RemoveVanillaRecipe(new ItemStack(Block.enchantmentTable, 1), new Object[] {" B ", "D#D", "###", '#', Block.obsidian, 'B', Item.book, 'D', Item.diamond});
        RemoveVanillaRecipe(new ItemStack(Item.swordWood), new Object[] {"X", "X", "#", '#', Item.stick, 'X', Block.planks});
        RemoveVanillaRecipe(new ItemStack(Item.axeWood), new Object[] {"XX", "X#", " #", '#', Item.stick, 'X', Block.planks});
        RemoveVanillaRecipe(new ItemStack(Item.pickaxeWood), new Object[] {"XXX", " # ", " # ", '#', Item.stick, 'X', Block.planks});
        RemoveVanillaRecipe(new ItemStack(Item.shovelWood), new Object[] {"X", "#", "#", '#', Item.stick, 'X', Block.planks});
        RemoveVanillaRecipe(new ItemStack(Item.hoeWood), new Object[] {"XX", " #", " #", '#', Item.stick, 'X', Block.planks});
        RemoveVanillaRecipe(new ItemStack(Item.swordStone), new Object[] {"X", "X", "#", '#', Item.stick, 'X', Block.cobblestone});
        RemoveVanillaRecipe(new ItemStack(Item.axeStone), new Object[] {"XX", "X#", " #", '#', Item.stick, 'X', Block.cobblestone});
        RemoveVanillaRecipe(new ItemStack(Item.pickaxeStone), new Object[] {"XXX", " # ", " # ", '#', Item.stick, 'X', Block.cobblestone});
        RemoveVanillaRecipe(new ItemStack(Item.shovelStone), new Object[] {"X", "#", "#", '#', Item.stick, 'X', Block.cobblestone});
        RemoveVanillaRecipe(new ItemStack(Item.hoeStone), new Object[] {"XX", " #", " #", '#', Item.stick, 'X', Block.cobblestone});
        RemoveVanillaRecipe(new ItemStack(Item.axeIron), new Object[] {"XX", "X#", " #", '#', Item.stick, 'X', Item.ingotIron});
        RemoveVanillaRecipe(new ItemStack(Item.hoeIron), new Object[] {"XX", " #", " #", '#', Item.stick, 'X', Item.ingotIron});
        RemoveVanillaRecipe(new ItemStack(Item.axeGold), new Object[] {"XX", "X#", " #", '#', Item.stick, 'X', Item.ingotGold});
        RemoveVanillaRecipe(new ItemStack(Item.hoeGold), new Object[] {"XX", " #", " #", '#', Item.stick, 'X', Item.ingotGold});
        RemoveVanillaRecipe(new ItemStack(Item.axeDiamond), new Object[] {"XX", "X#", " #", '#', Item.stick, 'X', Item.diamond});
        RemoveVanillaRecipe(new ItemStack(Item.arrow, 4), new Object[] {"X", "#", "Y", 'Y', Item.feather, 'X', Item.flint, '#', Item.stick});
        RemoveVanillaRecipe(new ItemStack(Block.pistonBase, 1), new Object[] {"TTT", "#X#", "#R#", '#', Block.cobblestone, 'X', Item.ingotIron, 'R', Item.redstone, 'T', Block.planks});
        RemoveVanillaRecipe(new ItemStack(Item.brewingStand, 1), new Object[] {" B ", "###", '#', Block.cobblestone, 'B', Item.blazeRod});
        RemoveVanillaRecipe(new ItemStack(Item.emptyMap, 1), new Object[] {"###", "#X#", "###", '#', Item.paper, 'X', Item.compass});
        RemoveVanillaShapelessRecipe(new ItemStack(Item.eyeOfEnder, 1), new Object[] {Item.enderPearl, Item.blazePowder});
        RemoveVanillaRecipe(new ItemStack(Item.bed, 1), new Object[] {"###", "XXX", '#', Block.cloth, 'X', Block.planks});
        RemoveVanillaRecipe(new ItemStack(Block.fence, 2), new Object[] {"###", "###", '#', Item.stick});
        RemoveVanillaRecipe(new ItemStack(Block.trapdoor, 2), new Object[] {"###", "###", '#', Block.planks});
        RemoveVanillaRecipe(new ItemStack(Block.pumpkinLantern, 1), new Object[] {"A", "B", 'A', Block.pumpkin, 'B', Block.torchWood});
        RemoveVanillaRecipe(new ItemStack(Block.blockClay, 1), new Object[] {"##", "##", '#', Item.clay});
        RemoveVanillaRecipe(new ItemStack(Block.brick, 1), new Object[] {"##", "##", '#', Item.brick});
        RemoveVanillaRecipe(new ItemStack(Block.ladder, 3), new Object[] {"# #", "###", "# #", '#', Item.stick});
        RemoveVanillaRecipe(new ItemStack(Block.furnaceIdle), new Object[] {"###", "# #", "###", '#', Block.cobblestone});
        RemoveVanillaRecipe(new ItemStack(Block.sandStone), new Object[] {"##", "##", '#', Block.sand});
        RemoveVanillaRecipe(new ItemStack(Block.blockSnow, 1), new Object[] {"##", "##", '#', Item.snowball});
        RemoveVanillaRecipe(new ItemStack(Block.stairsWoodOak, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Block.planks, 1, 0)});
        RemoveVanillaRecipe(new ItemStack(Block.stairsWoodBirch, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Block.planks, 1, 2)});
        RemoveVanillaRecipe(new ItemStack(Block.stairsWoodSpruce, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Block.planks, 1, 1)});
        RemoveVanillaRecipe(new ItemStack(Block.stairsWoodJungle, 4), new Object[] {"#  ", "## ", "###", '#', new ItemStack(Block.planks, 1, 3)});
        RemoveVanillaRecipe(new ItemStack(Block.workbench), new Object[] {"##", "##", '#', Block.planks});
        RemoveVanillaRecipe(new ItemStack(Block.chest), new Object[] {"###", "# #", "###", '#', Block.planks});
        RemoveVanillaShapelessRecipe(new ItemStack(Item.book, 1), new Object[] {Item.paper, Item.paper, Item.paper, Item.leather});
        RemoveVanillaRecipe(new ItemStack(Block.stoneBrick, 4), new Object[] {"##", "##", '#', Block.stone});
        FurnaceRecipes.smelting().getSmeltingList().remove(Integer.valueOf(Block.oreIron.blockID));
        FurnaceRecipes.smelting().getSmeltingList().remove(Integer.valueOf(Block.oreGold.blockID));
        FurnaceRecipes.smelting().getSmeltingList().remove(Integer.valueOf(Block.oreDiamond.blockID));
        FurnaceRecipes.smelting().getSmeltingList().remove(Integer.valueOf(Block.sand.blockID));
        FurnaceRecipes.smelting().getSmeltingList().remove(Integer.valueOf(Block.cobblestone.blockID));
        FurnaceRecipes.smelting().getSmeltingList().remove(Integer.valueOf(Item.clay.itemID));
        FurnaceRecipes.smelting().getSmeltingList().remove(Integer.valueOf(Block.wood.blockID));
        FurnaceRecipes.smelting().getSmeltingList().remove(Integer.valueOf(Block.oreEmerald.blockID));
        FurnaceRecipes.smelting().getSmeltingList().remove(Integer.valueOf(Block.netherrack.blockID));
        FurnaceRecipes.smelting().getSmeltingList().remove(Integer.valueOf(Block.oreCoal.blockID));
        FurnaceRecipes.smelting().getSmeltingList().remove(Integer.valueOf(Block.oreRedstone.blockID));
        FurnaceRecipes.smelting().getSmeltingList().remove(Integer.valueOf(Block.oreLapis.blockID));
        FurnaceRecipes.smelting().getSmeltingList().remove(Integer.valueOf(Block.oreNetherQuartz.blockID));
        FurnaceRecipes.smelting().getSmeltingList().remove(Integer.valueOf(Block.glass.blockID));
    }

    private static void AddDebugRecipes() {}
}

package net.minecraft.src;

public class FCBlockBookshelf extends Block
{
    public FCBlockBookshelf(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialPlanks);
        this.setHardness(1.5F);
        this.SetAxesEffectiveOn();
        this.SetBuoyant();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.WOOD_BASED_BLOCK);
        this.SetFireProperties(FCEnumFlammability.BOOKSHELVES);
        this.setStepSound(soundWoodFootstep);
        this.setUnlocalizedName("bookshelf");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public int GetHarvestToolLevel(IBlockAccess var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 2, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, Item.book.itemID, 3, 0, var6);
        return true;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var1 < 2 ? Block.planks.getBlockTextureFromSide(var1) : super.getIcon(var1, var2);
    }
}

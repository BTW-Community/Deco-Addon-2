package net.minecraft.src;

public class FCBlockWickerSlab extends FCBlockSlab
{
    public FCBlockWickerSlab(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialWicker);
        this.setHardness(0.5F);
        this.SetAxesEffectiveOn();
        this.SetBuoyant();
        this.SetFireProperties(FCEnumFlammability.WICKER);
        this.setStepSound(soundGrassFootstep);
        this.setUnlocalizedName("fcBlockWickerSlab");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public boolean DoesBlockBreakSaw(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return FCBetterThanWolves.fcBlockWickerPane.blockID;
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public boolean DropComponentItemsOnBadBreak(World var1, int var2, int var3, int var4, int var5, float var6)
    {
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemWickerPiece.itemID, 1, 0, var6);
        this.DropItemsIndividualy(var1, var2, var3, var4, FCBetterThanWolves.fcItemSawDust.itemID, 3, 0, var6);
        return true;
    }

    public int GetCombinedBlockID(int var1)
    {
        return FCBetterThanWolves.fcBlockWicker.blockID;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("fcBlockWicker");
    }
}

package net.minecraft.src;

public class FCBlockRottenFleshSlab extends FCBlockSlab
{
    public FCBlockRottenFleshSlab(int var1)
    {
        super(var1, Material.ground);
        this.setHardness(0.6F);
        this.SetShovelsEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.setStepSound(FCBetterThanWolves.fcStepSoundSquish);
        this.setUnlocalizedName("fcBlockRottenFleshSlab");
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    public boolean DoesBlockBreakSaw(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return Item.rottenFlesh.itemID;
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public int GetCombinedBlockID(int var1)
    {
        return FCBetterThanWolves.fcBlockRottenFlesh.blockID;
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }
}

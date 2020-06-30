package net.minecraft.src;

public class FCBlockCreeperOysters extends Block
{
    public static final float m_fHardness = 0.6F;

    public FCBlockCreeperOysters(int var1)
    {
        super(var1, Material.ground);
        this.setHardness(0.6F);
        this.SetShovelsEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.setStepSound(FCBetterThanWolves.fcStepSoundSquish);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("fcBlockCreeperOysters");
    }

    public boolean DoesBlockBreakSaw(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return FCBetterThanWolves.fcItemCreeperOysters.itemID;
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 8;
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }
}

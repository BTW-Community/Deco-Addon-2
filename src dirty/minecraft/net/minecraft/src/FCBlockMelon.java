package net.minecraft.src;

public class FCBlockMelon extends FCBlockGourd
{
    protected FCBlockMelon(int var1)
    {
        super(var1);
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return Item.melon.itemID;
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 5;
    }

    protected Item ItemToDropOnExplode()
    {
        return Item.melonSeeds;
    }

    protected int ItemCountToDropOnExplode()
    {
        return 5;
    }

    protected int AuxFXIDOnExplode()
    {
        return 2249;
    }

    protected DamageSource GetFallDamageSource()
    {
        return FCDamageSourceCustom.m_DamageSourceMelon;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("melon_side");
        this.m_IconTop = var1.registerIcon("melon_top");
    }
}

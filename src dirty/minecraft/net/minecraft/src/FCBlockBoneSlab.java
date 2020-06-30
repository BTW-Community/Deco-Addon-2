package net.minecraft.src;

public class FCBlockBoneSlab extends FCBlockSlab
{
    private Icon m_IconBoneSide;

    public FCBlockBoneSlab(int var1)
    {
        super(var1, FCBetterThanWolves.fcMaterialMiscellaneous);
        this.setHardness(2.0F);
        this.SetPicksEffectiveOn(true);
        this.SetBuoyancy(1.0F);
        this.setStepSound(soundGravelFootstep);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setUnlocalizedName("fcBlockBoneSlab");
    }

    public boolean DoesBlockBreakSaw(World var1, int var2, int var3, int var4)
    {
        return false;
    }

    public int GetItemIDDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return Item.bone.itemID;
    }

    public int GetItemCountDroppedOnSaw(World var1, int var2, int var3, int var4)
    {
        return 2;
    }

    public int GetCombinedBlockID(int var1)
    {
        return FCBetterThanWolves.fcAestheticOpaque.blockID;
    }

    public int GetCombinedMetadata(int var1)
    {
        return 15;
    }

    public boolean CanBePistonShoveled(World var1, int var2, int var3, int var4)
    {
        return true;
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister var1)
    {
        this.blockIcon = var1.registerIcon("fcBlockBoneSlab_top");
        this.m_IconBoneSide = var1.registerIcon("fcBlockBoneSlab_side");
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int var1, int var2)
    {
        return var1 >= 2 ? this.m_IconBoneSide : this.blockIcon;
    }
}

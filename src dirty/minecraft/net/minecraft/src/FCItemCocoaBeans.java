package net.minecraft.src;

public class FCItemCocoaBeans extends FCItemFood
{
    public static final int m_iHungerHealed = 1;
    public static final float m_fSaturationModifier = 0.0F;
    public static final String m_sItemName = "fcItemCocoaBeans";

    public FCItemCocoaBeans(int var1)
    {
        super(var1, 1, 0.0F, false, "fcItemCocoaBeans");
        this.SetBellowsBlowDistance(1);
        this.SetFilterableProperties(2);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (!this.AttemptPlaceOn(var3, var4, var5, var6, var7, var8, var9, var10))
        {
            return false;
        }
        else
        {
            if (var2 == null || !var2.capabilities.isCreativeMode)
            {
                --var1.stackSize;
            }

            return true;
        }
    }

    protected boolean AttemptPlaceOn(World var1, int var2, int var3, int var4, int var5, float var6, float var7, float var8)
    {
        int var9 = var1.getBlockId(var2, var3, var4);
        int var10 = var1.getBlockMetadata(var2, var3, var4);

        if (var5 >= 2 && var9 == Block.wood.blockID && BlockLog.limitToValidMetadata(var10) == 3)
        {
            FCUtilsBlockPos var11 = new FCUtilsBlockPos(var2, var3, var4, var5);

            if (var1.isAirBlock(var11.i, var11.j, var11.k))
            {
                int var12 = Block.cocoaPlant.blockID;
                int var13 = Block.blocksList[var12].onBlockPlaced(var1, var11.i, var11.j, var11.k, var5, var6, var7, var8, 0);
                var1.setBlockAndMetadataWithNotify(var11.i, var11.j, var11.k, var12, var13);
                return true;
            }
        }

        return false;
    }

    public int GetHungerRestored()
    {
        return this.getHealAmount();
    }

    public boolean OnItemUsedByBlockDispenser(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var3, var4, var5, var6);
        var7.AddFacingAsOffset(var6);

        if (this.AttemptPlaceOn(var2, var7.i, var7.j, var7.k, Block.GetOppositeFacing(var6), 0.0F, 0.0F, 0.0F))
        {
            var2.playAuxSFX(2236, var3, var4, var5, Block.cocoaPlant.blockID);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void registerIcons(IconRegister var1)
    {
        this.itemIcon = var1.registerIcon("dyePowder_brown");
    }
}

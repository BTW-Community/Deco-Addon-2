package net.minecraft.src;

public class FCItemMushroom extends FCItemFood
{
    public static final int m_iBrownMushroomHungerHealed = 1;
    public static final float m_fBrownMushroomSaturationModifier = 0.0F;
    public static final String m_sBrownMushroomItemName = "fcItemMushroomBrown";
    public static final int m_iRedMushroomHungerHealed = 1;
    public static final float m_fRedMushroomSaturationModifier = 0.0F;
    public static final String m_sRedMushroomItemName = "fcItemMushroomRed";
    public final int m_iPlacedBlockID;

    public FCItemMushroom(int var1, int var2, float var3, String var4, int var5)
    {
        super(var1, var2, var3, false, var4);
        this.m_iPlacedBlockID = var5;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (var7 == 1 && (var2 == null || var2.canPlayerEdit(var4, var5, var6, var7, var1) && var2.canPlayerEdit(var4, var5 + 1, var6, var7, var1)))
        {
            Block var11 = Block.blocksList[this.m_iPlacedBlockID];

            if (var3.isAirBlock(var4, var5 + 1, var6) && var11 != null && var11.canPlaceBlockAt(var3, var4, var5 + 1, var6))
            {
                var3.setBlockWithNotify(var4, var5 + 1, var6, this.m_iPlacedBlockID);
                var3.playSoundEffect((double)var4 + 0.5D, (double)var5 + 0.5D, (double)var6 + 0.5D, Block.soundGrassFootstep.getStepSound(), (Block.soundGrassFootstep.getVolume() + 1.0F) / 2.0F, Block.soundGrassFootstep.getPitch() * 0.8F);
                --var1.stackSize;
                return true;
            }
        }

        return false;
    }

    public boolean OnItemUsedByBlockDispenser(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        FCUtilsBlockPos var7 = new FCUtilsBlockPos(var3, var4, var5, var6);
        Block var8 = Block.blocksList[this.m_iPlacedBlockID];

        if (var2.isAirBlock(var7.i, var7.j, var7.k) && var8 != null && var8.canPlaceBlockAt(var2, var7.i, var7.j, var7.k))
        {
            var2.setBlockWithNotify(var7.i, var7.j, var7.k, this.m_iPlacedBlockID);
            var2.playSoundEffect((double)var7.i + 0.5D, (double)var7.j + 0.5D, (double)var7.k + 0.5D, Block.soundGrassFootstep.getStepSound(), (Block.soundGrassFootstep.getVolume() + 1.0F) / 2.0F, Block.soundGrassFootstep.getPitch() * 0.8F);
            return true;
        }
        else
        {
            return false;
        }
    }

    public int GetHungerRestored()
    {
        return this.getHealAmount();
    }
}

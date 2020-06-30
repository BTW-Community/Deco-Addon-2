package net.minecraft.src;

public class FCItemRope extends Item
{
    public FCItemRope(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.setUnlocalizedName("fcItemRope");
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (var1.stackSize == 0)
        {
            return false;
        }
        else
        {
            int var11 = var3.getBlockId(var4, var5, var6);

            if (var11 == FCBetterThanWolves.fcAnchor.blockID && ((FCBlockAnchor)FCBetterThanWolves.fcAnchor).GetFacing(var3, var4, var5, var6) != 1 || var11 == FCBetterThanWolves.fcRopeBlock.blockID)
            {
                for (int var12 = var5 - 1; var12 >= 0; --var12)
                {
                    int var13 = var3.getBlockId(var4, var12, var6);

                    if (FCUtilsWorld.IsReplaceableBlock(var3, var4, var12, var6))
                    {
                        int var14 = FCBetterThanWolves.fcRopeBlock.onBlockPlaced(var3, var4, var12, var6, var7, 0.0F, 0.0F, 0.0F, 0);
                        var14 = FCBetterThanWolves.fcRopeBlock.PreBlockPlacedBy(var3, var4, var12, var6, var14, var2);

                        if (var3.setBlockAndMetadataWithNotify(var4, var12, var6, FCBetterThanWolves.fcRopeBlock.blockID, var14))
                        {
                            FCBetterThanWolves.fcRopeBlock.onBlockPlacedBy(var3, var4, var12, var6, var2, var1);
                            FCBetterThanWolves.fcRopeBlock.onPostBlockPlaced(var3, var4, var12, var6, var14);
                            var3.playSoundEffect((double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), FCBetterThanWolves.fcRopeBlock.stepSound.getStepSound(), (FCBetterThanWolves.fcRopeBlock.stepSound.getVolume() + 1.0F) / 2.0F, FCBetterThanWolves.fcRopeBlock.stepSound.getPitch() * 0.8F);
                            --var1.stackSize;
                            return true;
                        }

                        return false;
                    }

                    if (var13 != FCBetterThanWolves.fcRopeBlock.blockID)
                    {
                        return false;
                    }
                }
            }

            return false;
        }
    }
}

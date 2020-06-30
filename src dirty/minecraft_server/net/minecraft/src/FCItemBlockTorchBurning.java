package net.minecraft.src;

public class FCItemBlockTorchBurning extends ItemBlock
{
    public FCItemBlockTorchBurning(int var1)
    {
        super(var1);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        return var2.canPlayerEdit(var4, var5, var6, var7, var1) && this.AttemptToLightBlock(var1, var3, var4, var5, var6, var7) ? true : super.onItemUse(var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
    }

    public boolean GetCanItemStartFireOnUse(int var1)
    {
        return true;
    }

    public void onUpdate(ItemStack var1, World var2, EntityPlayer var3, int var4, boolean var5)
    {
        if (!var2.isRemote && var1.stackSize > 0 && var3.isInWater() && var3.isInsideOfMaterial(Material.water))
        {
            int var6 = MathHelper.floor_double(var3.posX);
            int var7 = MathHelper.floor_double(var3.posY) + 1;
            int var8 = MathHelper.floor_double(var3.posZ);
            var2.playAuxSFX(1004, var6, var7, var8, 0);
            var1.itemID = FCBetterThanWolves.fcBlockTorchNetherUnlit.blockID;
        }
    }

    protected boolean AttemptToLightBlock(ItemStack var1, World var2, int var3, int var4, int var5, int var6)
    {
        int var7 = var2.getBlockId(var3, var4, var5);
        Block var8 = Block.blocksList[var7];

        if (var8 != null && var8.GetCanBeSetOnFireDirectlyByItem(var2, var3, var4, var5))
        {
            if (!var2.isRemote)
            {
                var8.SetOnFireDirectly(var2, var3, var4, var5);
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}

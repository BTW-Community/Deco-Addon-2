package net.minecraft.src;

public class FCBlockNote extends BlockNote
{
    public FCBlockNote(int var1)
    {
        super(var1);
        this.setHardness(0.8F);
        this.SetAxesEffectiveOn();
        this.SetBuoyant();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.WOOD_BASED_BLOCK);
        this.setUnlocalizedName("musicBlock");
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World var1, int var2, int var3, int var4, EntityPlayer var5, int var6, float var7, float var8, float var9)
    {
        if (!var1.isRemote)
        {
            ItemStack var10 = var5.getCurrentEquippedItem();

            if (var10 != null && var10.getItem().itemID == FCBetterThanWolves.fcItemTuningFork.itemID)
            {
                TileEntityNote var11 = (TileEntityNote)var1.getBlockTileEntity(var2, var3, var4);

                if (var11 != null)
                {
                    var11.note = (byte)var10.getItemDamage();
                    var11.triggerNote(var1, var2, var3, var4);
                }

                return true;
            }
        }

        return super.onBlockActivated(var1, var2, var3, var4, var5, var6, var7, var8, var9);
    }

    public boolean IsIncineratedInCrucible()
    {
        return false;
    }
}

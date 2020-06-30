package net.minecraft.src;

public class FCItemBlockDeadWeight extends ItemBlock
{
    public FCItemBlockDeadWeight(int var1)
    {
        super(var1);
    }

    public void OnUsedInCrafting(EntityPlayer var1, ItemStack var2)
    {
        if (var1.m_iTimesCraftedThisTick == 0)
        {
            var1.playSound("random.anvil_land", 0.3F, var1.worldObj.rand.nextFloat() * 0.1F + 0.9F);
        }
    }

    protected void PlayPlaceSound(World var1, int var2, int var3, int var4, Block var5)
    {
        var1.playSoundEffect((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "random.anvil_use", 0.5F, var1.rand.nextFloat() * 0.05F + 0.7F);
    }

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var3.m_iTimesCraftedThisTick == 0 && var2.isRemote)
        {
            var3.playSound("random.anvil_use", 0.5F, var2.rand.nextFloat() * 0.25F + 0.75F);
        }

        super.onCreated(var1, var2, var3);
    }
}

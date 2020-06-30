package net.minecraft.src;

public class FCItemBlockMillStone extends ItemBlock
{
    public FCItemBlockMillStone(int var1)
    {
        super(var1);
    }

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var3.m_iTimesCraftedThisTick == 0 && var2.isRemote)
        {
            var3.playSound("random.anvil_break", 0.5F, var2.rand.nextFloat() * 0.25F + 1.25F);
        }

        super.onCreated(var1, var2, var3);
    }
}

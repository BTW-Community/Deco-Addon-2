package net.minecraft.src;

public class FCItemWaterWheel extends Item
{
    public FCItemWaterWheel(int var1)
    {
        super(var1);
        this.maxStackSize = 1;
        this.SetBuoyant();
        this.setUnlocalizedName("fcItemWaterWheel");
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        int var11 = var3.getBlockId(var4, var5, var6);

        if (var11 == FCBetterThanWolves.fcBlockAxle.blockID)
        {
            int var12 = ((FCBlockAxle)((FCBlockAxle)FCBetterThanWolves.fcBlockAxle)).GetAxisAlignment(var3, var4, var5, var6);

            if (var12 != 0)
            {
                boolean var13 = false;

                if (var12 == 2)
                {
                    var13 = true;
                }

                FCEntityWaterWheel var14 = new FCEntityWaterWheel(var3, (double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), var13);

                if (var14.ValidateAreaAroundDevice())
                {
                    if (var14.IsClearOfBlockingEntities())
                    {
                        if (!var3.isRemote)
                        {
                            var14.setRotationSpeed(var14.ComputeRotation());
                            var3.spawnEntityInWorld(var14);
                        }

                        --var1.stackSize;
                        return true;
                    }

                    if (var3.isRemote)
                    {
                        var2.addChatMessage("Water Wheel placement is obstructed by something, or by you");
                    }
                }
                else if (var3.isRemote)
                {
                    var2.addChatMessage("Not enough room to place Water Wheel");
                }
            }
        }

        return false;
    }
}

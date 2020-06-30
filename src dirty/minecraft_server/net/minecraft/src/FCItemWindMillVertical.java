package net.minecraft.src;

public class FCItemWindMillVertical extends Item
{
    public FCItemWindMillVertical(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.maxStackSize = 1;
        this.setUnlocalizedName("fcItemWindMillVertical");
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

            if (var12 == 0)
            {
                if (var2.rotationPitch <= 0.0F)
                {
                    var5 += 3;
                }
                else
                {
                    var5 -= 3;
                }

                if (!this.CheckForSupportingAxles(var3, var4, var5, var6))
                {
                    if (var3.isRemote)
                    {
                        var2.addChatMessage("Too few vertical Axles in column to place Wind Mill here");
                    }
                }
                else
                {
                    FCEntityWindMillVertical var13 = new FCEntityWindMillVertical(var3, (double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F));

                    if (var13.ValidateAreaAroundDevice())
                    {
                        if (var13.IsClearOfBlockingEntities())
                        {
                            if (!var3.isRemote)
                            {
                                var13.setRotationSpeed(var13.ComputeRotation());
                                var3.spawnEntityInWorld(var13);
                            }

                            --var1.stackSize;
                        }
                        else if (var3.isRemote)
                        {
                            var2.addChatMessage("Wind Mill placement is obstructed by something, or by you");
                        }
                    }
                    else if (var3.isRemote)
                    {
                        var2.addChatMessage("Not enough room to place Wind Mill (They are friggin HUGE!)");
                    }
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean CheckForSupportingAxles(World var1, int var2, int var3, int var4)
    {
        for (int var5 = var3 - 3; var5 <= var3 + 3; ++var5)
        {
            int var6 = var1.getBlockId(var2, var5, var4);

            if (var6 != FCBetterThanWolves.fcBlockAxle.blockID)
            {
                return false;
            }

            int var7 = ((FCBlockAxle)((FCBlockAxle)FCBetterThanWolves.fcBlockAxle)).GetAxisAlignment(var1, var2, var5, var4);

            if (var7 != 0)
            {
                return false;
            }
        }

        return true;
    }
}

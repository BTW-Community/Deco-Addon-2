package net.minecraft.src;

public class FCItemBlockTorchFiniteBurning extends FCItemBlockTorchBurning
{
    private static final float m_fChanceOfGoingOutFromRain = 0.0025F;
    public static final int m_iMaxDamage = 32;
    public static final float m_fDamageToBurnTimeRatio = 0.0013333333F;
    public static final int m_iSputterDamage = 32;
    private Icon m_iconSputtering;

    public FCItemBlockTorchFiniteBurning(int var1)
    {
        super(var1);
        this.maxStackSize = 1;
        this.setMaxDamage(32);
        this.setUnlocalizedName("fcBlockTorchFiniteBurning");
    }

    public void onUpdate(ItemStack var1, World var2, EntityPlayer var3, int var4, boolean var5)
    {
        if (!var2.isRemote && var1.stackSize > 0 && var1.hasTagCompound() && var1.getTagCompound().hasKey("outTime"))
        {
            long var6 = var1.getTagCompound().getLong("outTime");
            int var8 = (int)(var6 - FCUtilsWorld.GetOverworldTimeServerOnly());
            int var9;
            int var10;
            int var11;

            if (var8 > 0 && var8 <= 24000)
            {
                if ((!var3.isInWater() || !var3.isInsideOfMaterial(Material.water)) && (!var3.IsBeingRainedOn() || var3.worldObj.rand.nextFloat() > 0.0025F))
                {
                    var9 = (int)(0.0013333333F * (float)(24000 - var8));
                    var9 = MathHelper.clamp_int(var9, 1, 31);

                    if (var9 != var1.getItemDamage())
                    {
                        var1.setItemDamage(var9);
                    }
                }
                else
                {
                    var9 = MathHelper.floor_double(var3.posX);
                    var10 = MathHelper.floor_double(var3.posY) + 1;
                    var11 = MathHelper.floor_double(var3.posZ);
                    var2.playAuxSFX(1004, var9, var10, var11, 0);
                    --var1.stackSize;

                    if (var1.stackSize <= 0)
                    {
                        var3.inventory.mainInventory[var4] = null;
                    }
                }
            }
            else
            {
                var9 = MathHelper.floor_double(var3.posX);
                var10 = MathHelper.floor_double(var3.posY) + 1;
                var11 = MathHelper.floor_double(var3.posZ);
                var2.playAuxSFX(1004, var9, var10, var11, 0);
                --var1.stackSize;

                if (var1.stackSize <= 0)
                {
                    var3.inventory.mainInventory[var4] = null;
                }
            }
        }
    }

    public boolean IgnoreDamageWhenComparingDuringUse()
    {
        return true;
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int var1)
    {
        return var1 >= 32 ? this.m_iconSputtering : super.getIconFromDamage(var1);
    }

    public void registerIcons(IconRegister var1)
    {
        super.registerIcons(var1);
        this.m_iconSputtering = var1.registerIcon("fcBlockTorchFiniteSputtering");
    }
}
